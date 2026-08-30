package generator;

import java.util.ArrayList;
import java.util.List;

/**
 * الحالة المشتركة التي يحملها الـ CodeGenerator أثناء زيارة الـ AST.
 *
 * <p>يجمع:</p>
 * <ul>
 *   <li>قائمة المنتجات المستخرجة من Python AST</li>
 *   <li>الملفات HTML المُولَّدة (اسم الملف → المحتوى)</li>
 *   <li>رسائل الـ log لتتبع خطوات التوليد</li>
 * </ul>
 */
public class GeneratorContext {

    private final List<ProductData>        products     = new ArrayList<>();
    private final java.util.Map<String, String> generatedFiles = new java.util.LinkedHashMap<>();
    private final List<String>             log          = new ArrayList<>();

    /** شجرة كل قالب Jinja تم تحليلها (اسم القالب → نص الشجرة)، لأجل compiler_output/ast_jinja.json */
    private final java.util.Map<String, String> jinjaAsts = new java.util.LinkedHashMap<>();

    /** أخطاء التحقق الدلالي لقوالب Jinja (اسم القالب → رسائل الخطأ)، لأجل semantic_report.txt */
    private final List<String> jinjaSemanticErrors = new ArrayList<>();

    /**
     * [Phase 3] جدول رموز كل قالب Jinja تم بناؤه (اسم القالب → نص الشجرة الهرمية)،
     * لأجل compiler_output/symbol_table_jinja.txt — كان موجوداً بالذاكرة أثناء
     * التوليد ولكن يُهمَل فوراً بدون تصدير.
     */
    private final java.util.Map<String, String> jinjaSymbolTables = new java.util.LinkedHashMap<>();

    // ── Products ──────────────────────────────────────────────────────────

    public void addProduct(ProductData p)        { products.add(p); }
    public List<ProductData> getProducts()       { return products; }
    public boolean hasProducts()                 { return !products.isEmpty(); }

    // ── Generated Files ───────────────────────────────────────────────────

    public void addFile(String filename, String content) {
        generatedFiles.put(filename, content);
    }

    public java.util.Map<String, String> getGeneratedFiles() {
        return generatedFiles;
    }

    // ── Jinja AST (لأجل compiler_output/ast_jinja.json) ─────────────────────

    public void addJinjaAst(String templateName, String astTreeText) {
        jinjaAsts.put(templateName, astTreeText);
    }

    public java.util.Map<String, String> getJinjaAsts() {
        return jinjaAsts;
    }

    // ── Jinja Semantic Errors (لأجل semantic_report.txt) ────────────────────

    public void addJinjaSemanticError(String templateName, String errorText) {
        jinjaSemanticErrors.add("[" + templateName + "] " + errorText);
    }

    public List<String> getJinjaSemanticErrors() {
        return jinjaSemanticErrors;
    }

    // ── Jinja Symbol Tables (لأجل compiler_output/symbol_table_jinja.txt) [Phase 3] ──

    public void addJinjaSymbolTable(String templateName, String hierarchyText) {
        jinjaSymbolTables.put(templateName, hierarchyText);
    }

    public java.util.Map<String, String> getJinjaSymbolTables() {
        return jinjaSymbolTables;
    }

    // ── Log ───────────────────────────────────────────────────────────────

    public void log(String message) {
        log.add("[CodeGen] " + message);
    }

    public List<String> getLog() {
        return log;
    }

    public void printLog() {
        log.forEach(System.out::println);
    }
}
