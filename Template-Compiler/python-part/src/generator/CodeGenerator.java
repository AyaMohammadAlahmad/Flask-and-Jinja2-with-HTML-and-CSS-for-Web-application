package generator;

import pyast.*;
import AST.ASTNode;
import AST.ASTTemplate;
import Semantic.FunctionRegistry;
import Semantic.SemanticAnalyzer;
import Semantic.SemanticError;
import Visitor.ASTVisitor;
import Visitor.HtmlSymbolTableVisitor;
import antlar.htmlLexer;
import antlar.htmlParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import render.JinjaRenderer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * مرحلة توليد الكود (Code Generation Phase).
 *
 * <p>يمشي على شجرة الـ Python AST بنمط Visitor يدوي (نفس أسلوب
 * {@code SemanticAnalyzer})، ويقوم بمهمتين:</p>
 *
 * <ol>
 *   <li><b>استخراج البيانات</b> — يبحث عن تعيينات متغير {@code products}
 *       ويحوّل كل {@code DictExpr} داخل القائمة إلى {@link ProductData}.</li>
 *   <li><b>توليد HTML/Jinja2</b> — ينتج 4 ملفات HTML جاهزة لـ Flask:
 *       <ul>
 *         <li>{@code products.html}        — عرض كل المنتجات</li>
 *         <li>{@code add_product.html}     — إضافة منتج جديد</li>
 *         <li>{@code product_detail.html}  — تفاصيل منتج</li>
 *         <li>{@code base.html}            — القالب الأساسي المشترك</li>
 *       </ul>
 *   </li>
 * </ol>
 *
 * <p>نمط الاستخدام:</p>
 * <pre>
 *   Program program = (Program) new ASTBuilder().visit(parseTree);
 *   CodeGenerator generator = new CodeGenerator();
 *   GeneratorContext ctx = generator.generate(program);
 *   ctx.getGeneratedFiles().forEach((name, content) -> writeFile(name, content));
 * </pre>
 */
public class CodeGenerator {

    private final GeneratorContext ctx = new GeneratorContext();

    public GeneratorContext getContext() {
        return ctx;
    }

    // ═══════════════════════════════════════════════════════
    //  نقطة الدخول
    // ═══════════════════════════════════════════════════════

    /**
     * يبدأ عملية التوليد الكاملة من جذر الـ AST.
     *
     * @param program جذر Python AST
     * @return {@link GeneratorContext} يحتوي المنتجات المستخرجة والملفات المُولَّدة
     */
    public GeneratorContext generate(Program program) {
        return generate(program, "templates");
    }

    /**
     * @param program      جذر Python AST
     * @param templatesDir المجلد الذي يحوي ملفات .jinja المدخلة (Jinja Parser input)
     */
    public GeneratorContext generate(Program program, String templatesDir) {
        ctx.log("Starting Code Generation...");

        // المرحلة 1: استخراج البيانات من Python AST → Context Data
        extractData(program);
        ctx.log("Extracted " + ctx.getProducts().size() + " product(s) from Python AST.");

        // المرحلة 2: معالجة قوالب Jinja الحقيقية عبر Jinja Parser/AST + Renderer
        renderJinjaTemplates(templatesDir);

        ctx.log("Code Generation complete. Generated " + ctx.getGeneratedFiles().size() + " file(s).");
        return ctx;
    }

    // ═══════════════════════════════════════════════════════
    //  المرحلة 2 — معالجة قوالب Jinja عبر Jinja Parser/AST الحقيقي
    // ═══════════════════════════════════════════════════════

    /**
     * لكل قالب .jinja داخل templatesDir:
     *   1) Lex + Parse عبر antlar.htmlLexer/htmlParser (نفس الـ Parser المستخدم في HtmlRunner)
     *   2) بناء Jinja AST عبر Visitor.ASTVisitor (نفس الشجرة المستخدمة في التحقق الدلالي)
     *   3) فحص دلالي عبر Semantic.SemanticAnalyzer (يُسجَّل في compiler_output/semantic_report.txt)
     *   4) [Phase 3] تصدير جدول رموز القالب (يُسجَّل في compiler_output/symbol_table_jinja.txt)
     *   5) Rendering فعلي (variable substitution + for loops) عبر {@link JinjaRenderer}
     *      مقابل Context Data المستخرجة من Python AST.
     *
     * الأسماء المولَّدة (index.html / add_product.html / product_detail.html) هي
     * الناتج الحقيقي لمرحلة Code Generation المطلوب في output/ (انظر التقرير النهائي
     * لشرح لماذا "edit_product" لم يُستخدم كاسم: التطبيق الحالي لا يدعم تعديلاً
     * فعلياً، فقط عرض/إضافة/حذف، والمتطلبات تسمح بذلك صراحة).
     */
    public void renderJinjaTemplates(String templatesDir) {
        Map<String, String> fileMap = new LinkedHashMap<>();
        fileMap.put("index.jinja", "index.html");
        fileMap.put("add_product.jinja", "add_product.html");
        fileMap.put("product_detail.jinja", "product_detail.html");

        // Context Data المشتركة القادمة من Python AST
        List<Map<String, Object>> productMaps = new ArrayList<>();
        for (ProductData p : ctx.getProducts()) {
            productMaps.add(new LinkedHashMap<>(p.getFields()));
        }
        Map<String, Object> baseContext = new LinkedHashMap<>();
        baseContext.put("products", productMaps);
        // صفحة تفاصيل المنتج تحتاج متغير "product" مفرد — نستخدم أول منتج متوفر كمعاينة
        if (!productMaps.isEmpty()) {
            baseContext.put("product", productMaps.get(0));
        } else {
            baseContext.put("product", new LinkedHashMap<String, Object>());
        }

        for (Map.Entry<String, String> entry : fileMap.entrySet()) {
            String jinjaFile = entry.getKey();
            String outFile = entry.getValue();
            Path path = Paths.get(templatesDir, jinjaFile);

            if (!Files.exists(path)) {
                ctx.log("WARNING: template not found: " + path + " — skipped.");
                continue;
            }

            try {
                // ── Jinja Parser: Lex + Parse ─────────────────────────────
                CharStream input = CharStreams.fromPath(path);
                htmlLexer lexer = new htmlLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                htmlParser parser = new htmlParser(tokens);
                ParseTree tree = parser.template();

                // ── Jinja AST ──────────────────────────────────────────────
                ASTVisitor astVisitor = new ASTVisitor();
                ASTNode astRoot = astVisitor.visit(tree);
                ctx.addJinjaAst(jinjaFile, astRoot != null ? astRoot.print("") : "(empty)");

                // ── Semantic Analysis على قالب الـ Jinja (يُسجَّل في semantic_report) ──
                if (astRoot instanceof ASTTemplate template) {
                    HtmlSymbolTableVisitor stVisitor = new HtmlSymbolTableVisitor();
                    FunctionRegistry.populate(stVisitor.getSymbolTable());
                    stVisitor.visit(astRoot);

                    // [Phase 3] تصدير جدول رموز هذا القالب بدل ما يضيع بعد نهاية الحلقة
                    ctx.addJinjaSymbolTable(jinjaFile, stVisitor.getSymbolTable().toHierarchyString(""));

                    SemanticAnalyzer analyzer = new SemanticAnalyzer(stVisitor.getSymbolTable());
                    analyzer.analyze(template);
                    for (SemanticError err : analyzer.getErrors()) {
                        ctx.addJinjaSemanticError(jinjaFile, err.toString());
                    }

                    // ── Generation: Variable substitution + for loops حقيقية ──
                    String html = JinjaRenderer.render(template, baseContext);
                    ctx.addFile(outFile, html);
                    ctx.log("Rendered template '" + jinjaFile + "' -> '" + outFile
                            + "' using context data: products=" + productMaps.size()
                            + ", product=" + (productMaps.isEmpty() ? "(empty)" : "yes"));
                }
            } catch (IOException e) {
                ctx.log("ERROR: failed to read template " + path + ": " + e.getMessage());
            } catch (Exception e) {
                ctx.log("ERROR: failed to process template " + path + ": " + e.getMessage());
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    //  المرحلة 1 — استخراج البيانات من Python AST
    // ═══════════════════════════════════════════════════════

    private void extractData(Program program) {
        for (TopLevel top : program.getStatements()) {
            visitTopLevel(top);
        }
    }

    private void visitTopLevel(TopLevel top) {
        if (top instanceof TopStatement ts) {
            visitStatement(ts.getStatement());
        } else if (top instanceof TopFunction tf) {
            // نتجاهل دوال Flask مثل index() و show_products()
            // لأن البيانات موجودة بالـ top-level assignments
        } else if (top instanceof DecoratedFunction df) {
            // نتجاهل الدوال المزيّنة بـ @app.route(...)
        }
        // imports لا تحتوي بيانات نحتاجها
    }

    private void visitStatement(Stmt stmt) {
        if (stmt instanceof Assign assign) {
            visitAssign(assign);
        }
        // باقي الجمل (If/For/While...) لا تحتوي top-level data بحاجة للاستخراج
    }

    /**
     * يزور جملة الإسناد ويبحث عن: {@code products = [...]}
     */
    private void visitAssign(Assign assign) {
        AssignTarget target = assign.getTarget();

        // نبحث فقط عن: products = [...]
        if (!target.getBase().equals("products")) return;
        if (!(assign.getValue() instanceof ListExpr listExpr)) return;

        ctx.log("Found 'products' assignment at line " + assign.getLine());

        for (Expr element : listExpr.getElements()) {
            if (element instanceof DictExpr dictExpr) {
                ProductData product = extractProduct(dictExpr);
                ctx.addProduct(product);
                ctx.log("  Extracted product: " + product);
            }
        }
    }

    /**
     * يحوّل {@code DictExpr} إلى {@link ProductData}.
     *
     * <pre>
     * Python AST:
     *   {"id": 1, "name": "Laptop", "price": 1200, "image": "...", "details": "..."}
     *
     * → ProductData {id=1, name=Laptop, price=1200, image=..., details=...}
     * </pre>
     */
    private ProductData extractProduct(DictExpr dictExpr) {
        ProductData product = new ProductData();
        for (DictExpr.DictItem item : dictExpr.getItems()) {
            String key   = extractStringValue(item.getKey());
            String value = extractStringValue(item.getValue());
            if (key != null) {
                product.set(key, value != null ? value : "");
            }
        }
        return product;
    }

    /** يستخرج القيمة النصية من أي نوع Literal. */
    private String extractStringValue(Expr expr) {
        if (expr instanceof StringLiteral s) {
            // إزالة علامتي التنصيص من القيمة
            String raw = s.getValue();
            if ((raw.startsWith("\"") && raw.endsWith("\""))
             || (raw.startsWith("'")  && raw.endsWith("'"))) {
                return raw.substring(1, raw.length() - 1);
            }
            return raw;
        }
        if (expr instanceof NumberLiteral n) return n.getValue();
        if (expr instanceof BoolLiteral b)   return String.valueOf(b.getValue());
        return null;
    }
}
