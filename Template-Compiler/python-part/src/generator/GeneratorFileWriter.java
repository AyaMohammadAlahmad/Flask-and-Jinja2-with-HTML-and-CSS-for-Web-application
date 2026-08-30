package generator;

import pyerrors.CompilerError;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * يكتب الملفات المُولَّدة على القرص، مع فصل واضح بين:
 *
 * <pre>
 * output/                      ← ناتج Code Generation الحقيقي (Translator Output)
 * ├── index.html                 (من Jinja Parser/AST + JinjaRenderer)
 * ├── add_product.html
 * ├── product_detail.html
 * ├── app.py                     ← ملف داعم (Runtime Support) — يُنسخ كما هو
 * ├── style.css                  ← ملف داعم — يُنسخ كما هو
 * ├── script.js                  ← ملف داعم — يُنسخ كما هو
 * └── templates/                 ← ملفات تشغيل Flask (Jinja2 حقيقي غير مقيّد)
 *     ├── products.html
 *     ├── add_product.html
 *     └── product_detail.html
 *
 * compiler_output/              ← تقارير المترجم (منفصلة تماماً عن output/)
 * ├── ast_python.json
 * ├── ast_jinja.json
 * ├── semantic_report.txt        ← يغطي Lexical + Syntax + Semantic (Phase 2)
 * ├── symbol_table_python.txt    ← [Phase 3] جديد
 * ├── symbol_table_jinja.txt     ← [Phase 3] جديد
 * └── generation_log.txt
 * </pre>
 */
public class GeneratorFileWriter {

    private final String outputDir;
    private final String compilerOutputDir;

    public GeneratorFileWriter(String outputDir) {
        this(outputDir, "compiler_output");
    }

    public GeneratorFileWriter(String outputDir, String compilerOutputDir) {
        this.outputDir = outputDir;
        this.compilerOutputDir = compilerOutputDir;
    }

    /**
     * يكتب جميع الملفات: ناتج التوليد الحقيقي، ملفات تشغيل Flask، الملفات
     * الداعمة، وتقارير compiler_output/.
     *
     * @param ctx                       السياق (يحوي الملفات المولَّدة الحقيقية + المنتجات + السجلات
     *                                  + [Phase 3] جداول رموز Jinja لكل قالب)
     * @param appCode                   محتوى app.py (ملف داعم للتشغيل)
     * @param pythonAstText             نص شجرة Python AST (لأجل ast_python.json)
     * @param pythonSemanticErrors      أخطاء التحقق الدلالي لـ Python (نصوص جاهزة كما كانت)
     * @param pythonLexicalSyntaxErrors أخطاء Lexical/Syntax الملتقطة عبر PyErrorListener (Phase 2)
     * @param pythonSymbolTableText     [Phase 3] نص جدول رموز Python الهرمي الكامل
     *                                  (من {@code pyvisitor.SemanticAnalyzer.getSymbolTable().toString()})
     * @param supportFilesSourceDir     المجلد الذي يحوي style.css / script.js الأصليين لنسخهما
     */
    public void writeAll(GeneratorContext ctx,
                          String appCode,
                          String pythonAstText,
                          List<String> pythonSemanticErrors,
                          List<CompilerError> pythonLexicalSyntaxErrors,
                          String pythonSymbolTableText,
                          String supportFilesSourceDir) throws IOException {

        Path out = Paths.get(outputDir);
        Files.createDirectories(out);

        // ── 1) ناتج Code Generation الحقيقي (flat، مباشرة تحت output/) ──────
        Map<String, String> generated = ctx.getGeneratedFiles();
        for (Map.Entry<String, String> entry : generated.entrySet()) {
            Path filePath = out.resolve(entry.getKey());
            Files.writeString(filePath, entry.getValue());
            System.out.println("[FileWriter] Written (generation output): " + filePath.toAbsolutePath());
        }

        // ── 2) ملفات دعم التشغيل (Runtime Support) ─────────────────────────
        Path appPy = out.resolve("app.py");
        Files.writeString(appPy, appCode);
        System.out.println("[FileWriter] Written (runtime support): " + appPy.toAbsolutePath());

        copySupportFile(supportFilesSourceDir, "style.css", out);
        copySupportFile(supportFilesSourceDir, "script.js", out);

        // نسخة إضافية داخل static/ لأن Flask يخدم url_for('static', ...) من هناك افتراضياً
        Path staticDir = out.resolve("static");
        Files.createDirectories(staticDir);
        copySupportFile(supportFilesSourceDir, "style.css", staticDir);
        copySupportFile(supportFilesSourceDir, "script.js", staticDir);

        // ── 3) قوالب تشغيل Flask (Jinja2 حقيقي كامل، غير مُقيَّد بالـ grammar) ──
        Path templatesDir = out.resolve("templates");
        Files.createDirectories(templatesDir);
        writeIfChanged(templatesDir.resolve("products.html"), RuntimeTemplateProvider.productsHtml());
        writeIfChanged(templatesDir.resolve("add_product.html"), RuntimeTemplateProvider.addProductHtml());
        writeIfChanged(templatesDir.resolve("product_detail.html"), RuntimeTemplateProvider.productDetailHtml());

        // ── 4) compiler_output/ — تقارير المترجم (منفصلة عن output/) ────────
        writeCompilerOutput(ctx, pythonAstText, pythonSemanticErrors, pythonLexicalSyntaxErrors, pythonSymbolTableText);
    }

    /**
     * مسار مختصر لما تفشل مرحلة Lexing/Parsing قبل ما نوصل حتى لبناء AST —
     * بيكتب فقط {@code semantic_report.txt} موثّقاً فيه أخطاء Lexical/Syntax،
     * بدون محاولة كتابة ast_python.json أو جداول الرموز (لأنه ببساطة مش موجودين بعد).
     */
    public void writeErrorOnlyReport(List<CompilerError> lexicalSyntaxErrors,
                                      List<String> semanticErrors) throws IOException {
        Path co = Paths.get(compilerOutputDir);
        Files.createDirectories(co);

        Path semReport = co.resolve("semantic_report.txt");
        Files.writeString(semReport, buildReportText(lexicalSyntaxErrors, semanticErrors, Collections.emptyList()));
        System.out.println("[FileWriter] Written (compiler_output, error-only): " + semReport.toAbsolutePath());
    }

    private void copySupportFile(String sourceDir, String filename, Path outDir) throws IOException {
        Path src = Paths.get(sourceDir, filename);
        if (Files.exists(src)) {
            Path dest = outDir.resolve(filename);
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("[FileWriter] Copied (support file): " + dest.toAbsolutePath());
        } else {
            System.out.println("[FileWriter] WARNING: support file not found, skipped: " + src);
        }
    }

    private void writeIfChanged(Path path, String content) throws IOException {
        Files.writeString(path, content);
        System.out.println("[FileWriter] Written (runtime template): " + path.toAbsolutePath());
    }

    private void writeCompilerOutput(GeneratorContext ctx,
                                      String pythonAstText,
                                      List<String> pythonSemanticErrors,
                                      List<CompilerError> pythonLexicalSyntaxErrors,
                                      String pythonSymbolTableText) throws IOException {
        Path co = Paths.get(compilerOutputDir);
        Files.createDirectories(co);

        // ast_python.json
        Path astPython = co.resolve("ast_python.json");
        Files.writeString(astPython, jsonWrap("python_ast", pythonAstText));
        System.out.println("[FileWriter] Written (compiler_output): " + astPython.toAbsolutePath());

        // ast_jinja.json
        StringBuilder jinjaJson = new StringBuilder();
        jinjaJson.append("{\n  \"jinja_templates\": [\n");
        int i = 0;
        int n = ctx.getJinjaAsts().size();
        for (Map.Entry<String, String> e : ctx.getJinjaAsts().entrySet()) {
            jinjaJson.append("    {\n");
            jinjaJson.append("      \"template\": \"").append(jsonEscape(e.getKey())).append("\",\n");
            jinjaJson.append("      \"ast_tree\": \"").append(jsonEscape(e.getValue())).append("\"\n");
            jinjaJson.append("    }");
            jinjaJson.append(++i < n ? ",\n" : "\n");
        }
        jinjaJson.append("  ]\n}\n");
        Path astJinja = co.resolve("ast_jinja.json");
        Files.writeString(astJinja, jinjaJson.toString());
        System.out.println("[FileWriter] Written (compiler_output): " + astJinja.toAbsolutePath());

        // semantic_report.txt — يغطي Lexical + Syntax + Semantic (Python) + Semantic (Jinja)
        Path semReport = co.resolve("semantic_report.txt");
        Files.writeString(semReport, buildReportText(
                pythonLexicalSyntaxErrors, pythonSemanticErrors, ctx.getJinjaSemanticErrors()));
        System.out.println("[FileWriter] Written (compiler_output): " + semReport.toAbsolutePath());

        // symbol_table_python.txt — [Phase 3] جديد
        Path symTablePython = co.resolve("symbol_table_python.txt");
        Files.writeString(symTablePython,
                "=== PYTHON SYMBOL TABLE ===\n\n" + (pythonSymbolTableText != null ? pythonSymbolTableText : "(empty)\n"));
        System.out.println("[FileWriter] Written (compiler_output): " + symTablePython.toAbsolutePath());

        // symbol_table_jinja.txt — [Phase 3] جديد (جدول رموز منفصل لكل قالب)
        StringBuilder jinjaSym = new StringBuilder();
        jinjaSym.append("=== JINJA SYMBOL TABLES ===\n\n");
        if (ctx.getJinjaSymbolTables().isEmpty()) {
            jinjaSym.append("(no templates processed)\n");
        } else {
            for (Map.Entry<String, String> e : ctx.getJinjaSymbolTables().entrySet()) {
                jinjaSym.append("-- ").append(e.getKey()).append(" --\n");
                jinjaSym.append(e.getValue()).append("\n");
            }
        }
        Path symTableJinja = co.resolve("symbol_table_jinja.txt");
        Files.writeString(symTableJinja, jinjaSym.toString());
        System.out.println("[FileWriter] Written (compiler_output): " + symTableJinja.toAbsolutePath());

        // generation_log.txt
        StringBuilder log = new StringBuilder();
        for (String line : ctx.getLog()) log.append(line).append("\n");
        Path genLog = co.resolve("generation_log.txt");
        Files.writeString(genLog, log.toString());
        System.out.println("[FileWriter] Written (compiler_output): " + genLog.toAbsolutePath());
    }

    /**
     * يبني نص تقرير موحّد بأربعة أقسام واضحة، بترتيب يعكس مراحل المترجم
     * الفعلية: Lexical/Syntax أولاً (لأنها تمنع أي مرحلة بعدها)، ثم Semantic
     * لكل من Python و Jinja.
     */
    private static String buildReportText(List<CompilerError> lexicalSyntaxErrors,
                                           List<String> pythonSemanticErrors,
                                           List<String> jinjaSemanticErrors) {
        StringBuilder report = new StringBuilder();
        report.append("=== COMPILER ERROR REPORT ===\n\n");

        report.append("-- Lexical / Syntax analysis (Python) --\n");
        if (lexicalSyntaxErrors == null || lexicalSyntaxErrors.isEmpty()) {
            report.append("No lexical or syntax errors found.\n");
        } else {
            for (CompilerError err : lexicalSyntaxErrors) {
                report.append(err.toString()).append("\n");
            }
        }

        report.append("\n-- Semantic analysis (Python) --\n");
        if (pythonSemanticErrors == null || pythonSemanticErrors.isEmpty()) {
            report.append("No semantic errors found.\n");
        } else {
            for (String err : pythonSemanticErrors) report.append(err).append("\n");
        }

        report.append("\n-- Semantic analysis (Jinja template) --\n");
        if (jinjaSemanticErrors == null || jinjaSemanticErrors.isEmpty()) {
            report.append("No semantic errors found.\n");
        } else {
            for (String err : jinjaSemanticErrors) report.append(err).append("\n");
        }

        return report.toString();
    }

    private static String jsonWrap(String key, String text) {
        return "{\n  \"" + key + "\": \"" + jsonEscape(text) + "\"\n}\n";
    }

    private static String jsonEscape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");
    }
}
