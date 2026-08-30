package python;

import antlr.pyLexer;
import antlr.pyParser;
import pyast.Program;
import generator.CodeGenerator;
import generator.GeneratedFlaskApp;
import generator.GeneratorContext;
import generator.GeneratorFileWriter;
import pyerrors.CompilerError;
import pyerrors.PyErrorListener;
import pyvisitor.ASTBuilder;
import pyvisitor.ASTPrinter;
import pyvisitor.SemanticAnalyzer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collections;

/**
 * نقطة تشغيل مرحلة Python للمترجم.
 *
 * <p>يمر بالمراحل بالترتيب:</p>
 * <ol>
 *   <li>Lexing & Parsing (ANTLR) — مع {@link PyErrorListener} موحّد يلتقط
 *       أخطاء Lexical و Syntax بدل ما تضيع على stderr [Phase 2]</li>
 *   <li>AST Building</li>
 *   <li>AST Printing</li>
 *   <li>Semantic Analysis — وتصدير جدول الرموز الهرمي الكامل [Phase 3]</li>
 *   <li>Code Generation → ملفات HTML/Jinja2 + app.py</li>
 * </ol>
 *
 * <p><b>ملاحظة مهمة:</b> إذا التُقطت أخطاء Lexical/Syntax، ما بيكمل
 * المترجم لمرحلة بناء الـ AST والتوليد (لأنه الشجرة الناتجة من parse tree
 * فيه أخطاء تكون غير موثوقة) — بس بيكتب {@code semantic_report.txt} موثّقاً
 * فيه هالأخطاء بدل ما البرنامج يفشل بصمت أو بـ stack trace غير مفهوم.</p>
 */
public class PythonRunner {

    // ── Entry Point (الميثود التي تم إضافتها لتشغيل الكلاس مباشرة) ─────────────
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("❌ Error: Missing input file argument.");
            System.out.println("Usage: java python.PythonRunner <path-to-python-file> [output-dir] [templates-dir]");
            return;
        }

        String inputPath    = args[0];
        String outputDir    = (args.length > 1) ? args[1] : "output";
        String templatesDir = (args.length > 2) ? args[2] : "templates";

        try {
            run(inputPath, outputDir, templatesDir);
        } catch (Exception e) {
            System.err.println("❌ Unhandled Exception in PythonRunner:");
            e.printStackTrace();
        }
    }

    public static void run(String inputPath) throws Exception {
        run(inputPath, "output", "templates");
    }

    public static void run(String inputPath, String outputDir) throws Exception {
        run(inputPath, outputDir, "templates");
    }

    /**
     * @param inputPath    مسار ملف Flask/Python المدخل
     * @param outputDir    مجلد ناتج التوليد (output/)
     * @param templatesDir مجلد قوالب .jinja المدخلة (Jinja Parser input) — قابل للتحديد
     *                     صراحة لأنه ليس بالضرورة نسبياً لمكان تشغيل الأمر نفسه
     *                     (مثلاً لما يكون templates/ جوّا Template-Compiler/ بينما التشغيل
     *                     يصير من مجلد الـ workproject الجذري).
     */
    public static void run(String inputPath, String outputDir, String templatesDir) throws Exception {

        // ── 1. Lexing & Parsing ──────────────────────────────────────────
        PyErrorListener errorListener = new PyErrorListener();

        CharStream input         = CharStreams.fromFileName(inputPath);
        pyLexer lexer            = new pyLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        pyParser parser          = new pyParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree parseTree      = parser.program();

        // ── 1.b. توقف مبكر ومنظّم إذا في أخطاء Lexical/Syntax ─────────────
        if (errorListener.hasErrors()) {
            System.out.println("\n==== LEXICAL / SYNTAX ANALYSIS RESULT ====");
            System.out.println("❌ Found " + errorListener.getErrors().size() + " lexical/syntax error(s):");
            errorListener.printErrors();
            System.out.println("===========================================");
            System.out.println("⚠️ Code Generation skipped — fix the above errors first.");

            // نكتب semantic_report.txt موثّقاً فيه هالأخطاء بدل ما نتوقف بصمت
            GeneratorFileWriter writer = new GeneratorFileWriter(outputDir);
            writer.writeErrorOnlyReport(errorListener.getErrors(), Collections.emptyList());
            return;
        }

        // ── 2. Build AST ─────────────────────────────────────────────────
        ASTBuilder builder = new ASTBuilder();
        Program program    = (Program) builder.visit(parseTree);

        // ── 3. Print AST ─────────────────────────────────────────────────
        ASTPrinter.print(program);

        // ── 4. Semantic Analysis ─────────────────────────────────────────
        SemanticAnalyzer analyzer = new SemanticAnalyzer();
        analyzer.analyze(program);

        System.out.println("\n==== SEMANTIC ANALYSIS RESULT ====");
        if (analyzer.hasErrors()) {
            System.out.println("❌ Found " + analyzer.getErrors().size() + " semantic error(s):");
            analyzer.printErrors();
        } else {
            System.out.println("✅ No semantic errors found.");
        }
        System.out.println("===================================");

        // ── 4.b. [Phase 3] طباعة جدول رموز Python الهرمي الكامل ───────────
        // ملاحظة: نستخدم toHierarchyString() الجديدة (تمشي على الأبناء كمان)
        // وليس toString() القديمة (كانت بتطبع الـ Global Scope فقط بدون أبنائه).
        System.out.println("\n==== PYTHON SYMBOL TABLE ====");
        String pythonSymbolTableText =
                analyzer.getSymbolTable().getGlobalScope().toHierarchyString(0);
        System.out.println(pythonSymbolTableText);
        System.out.println("==============================");

        // ── 5. Code Generation ───────────────────────────────────────────
        System.out.println("\n==== CODE GENERATION ====");

        try {
            CodeGenerator generator  = new CodeGenerator();
            GeneratorContext ctx     = generator.generate(program, templatesDir);

            ctx.printLog();

            // توليد app.py بحقن البيانات المستخرجة (يستمع لـ Java Server على 8090)
            String appCode = GeneratedFlaskApp.generate(ctx, 8090);

            // بذر data/products.json بنفس البيانات المستخرجة من Python AST، بحيث
            // يبدأ Java Regeneration Server من نفس الحالة التي أنتجها أول توليد
            seedProductsDataFile(outputDir, ctx);

            // كتابة كل الملفات على القرص (ناتج التوليد + الملفات الداعمة + compiler_output/)
            // لا يوجد أخطاء Lexical/Syntax هون (توقفنا فوق لو كانت موجودة)، فنمرر قائمة فاضية
            GeneratorFileWriter writer = new GeneratorFileWriter(outputDir);
            writer.writeAll(ctx, appCode, ASTPrinter.toText(program),
                    analyzer.getErrors(), Collections.<CompilerError>emptyList(),
                    pythonSymbolTableText, ".");

            System.out.println("\n✅ Code Generation complete!");
            System.out.println("   Output directory: " + outputDir);
            System.out.println("   Compiler reports:  compiler_output/");
            System.out.println("   Run the app with:");
            System.out.println("     1) java -cp <classpath> server.RegenServer 8090 " + outputDir + " " + templatesDir);
            System.out.println("     2) cd " + outputDir + " && python app.py");

        } catch (Exception e) {
            System.out.println("❌ Code Generation failed!");
            System.out.println("   Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** يكتب data/products.json الأولي من نفس البيانات المستخرجة من Python AST. */
    private static void seedProductsDataFile(String outputDir, GeneratorContext ctx) throws java.io.IOException {
        java.util.List<java.util.Map<String, Object>> products = new java.util.ArrayList<>();
        for (generator.ProductData p : ctx.getProducts()) {
            products.add(new java.util.LinkedHashMap<>(p.getFields()));
        }
        java.nio.file.Path dataFile = java.nio.file.Paths.get(outputDir, "data", "products.json");
        java.nio.file.Files.createDirectories(dataFile.getParent());
        if (!java.nio.file.Files.exists(dataFile)) {
            java.nio.file.Files.writeString(dataFile, server.SimpleJson.writeProducts(products));
        }
    }
}