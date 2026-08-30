package pysemantic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * يمثل نطاقاً (Scope) واحداً في الشجرة الهرمية للنطاقات.
 *
 * <p>كل نطاق يحتوي على:</p>
 * <ul>
 *   <li>جدول رموز محلي {@code Map<String, Symbol>}</li>
 *   <li>مؤشر للنطاق الأب {@code parent} — يكون {@code null} في الـ Global Scope</li>
 *   <li>قائمة النطاقات الأبناء {@code children} — <b>[Phase 3]</b> أُضيفت لدعم
 *       طباعة/تصدير شجرة النطاقات كاملة (كانت مفقودة سابقاً؛ الـ Scope كان
 *       يعرف أباه فقط وليس أبناءه، فكانت الشجرة الكاملة غير قابلة للـ traversal
 *       من الجذر للأسفل)</li>
 *   <li>اسم وصفي {@code scopeName} لتسهيل الـ debugging</li>
 * </ul>
 *
 * <p>مثال على الهيكل الهرمي:</p>
 * <pre>
 * GlobalScope
 * ├── Flask      (IMPORT)
 * ├── app        (VARIABLE)
 * └── index      (FUNCTION)
 *     └── FunctionScope: index
 *         └── product_id  (VARIABLE)
 * </pre>
 */
public class Scope {

    // ── الحقول ──────────────────────────────────────────────────────────
    private final String              scopeName;
    private final pysemantic.Scope parent;
    private final Map<String, Symbol> symbols = new LinkedHashMap<>();

    /** [Phase 3] النطاقات الأبناء — تُملأ تلقائياً عند إنشاء أي Scope إله parent. */
    private final List<pysemantic.Scope> children = new ArrayList<>();

    // ── البناء ──────────────────────────────────────────────────────────

    /**
     * ينشئ نطاقاً جديداً.
     *
     * @param scopeName اسم وصفي (e.g. "global", "function:index")
     * @param parent    النطاق الأب — {@code null} إذا كان هذا الـ Global Scope
     */
    public Scope(String scopeName, pysemantic.Scope parent) {
        this.scopeName = scopeName;
        this.parent    = parent;
        // [Phase 3] تسجيل هذا النطاق كابن لدى أبيه، حتى تصير الشجرة قابلة
        // للـ traversal من الجذر (نفس الفكرة المطبّقة أصلاً بجانب HTML/Jinja).
        if (parent != null) {
            parent.children.add(this);
        }
    }

    // ── العمليات الأساسية ────────────────────────────────────────────────

    /**
     * يضيف رمزاً جديداً في هذا النطاق تحديداً.
     * لا يتحقق من التكرار — المتصل مسؤول عن ذلك عبر {@link #containsLocal}.
     *
     * @param sym الرمز المراد تعريفه
     */
    public void define(Symbol sym) {
        symbols.put(sym.getName(), sym);
    }

    /**
     * يبحث عن رمز بالاسم في هذا النطاق أولاً.
     * إذا لم يجده يصعد للنطاق الأب تلقائياً (Lexical Scoping).
     *
     * @param name اسم الرمز
     * @return الرمز إذا وُجد، أو {@code null} إذا لم يُعثر عليه في أي نطاق
     */
    public Symbol lookup(String name) {
        Symbol sym = symbols.get(name);
        if (sym != null) return sym;                     // وجدناه هنا
        if (parent != null) return parent.lookup(name); // نصعد للأب
        return null;                                     // لم نجده في أي نطاق
    }

    /**
     * يتحقق من وجود الرمز في هذا النطاق فقط (بدون صعود للأب).
     * مفيد لاكتشاف التعريف المكرر في نفس النطاق.
     *
     * @param name اسم الرمز
     * @return {@code true} إذا كان الرمز معرّفاً محلياً
     */
    public boolean containsLocal(String name) {
        return symbols.containsKey(name);
    }

    // ── Getters ──────────────────────────────────────────────────────────

    public String              getScopeName() { return scopeName; }
    public pysemantic.Scope getParent()    { return parent; }
    public Collection<Symbol>  getSymbols()   { return Collections.unmodifiableCollection(symbols.values()); }

    /** [Phase 3] النطاقات الأبناء المباشرة لهذا النطاق. */
    public List<pysemantic.Scope> getChildren() { return Collections.unmodifiableList(children); }

    // ── طباعة النطاق للـ debugging ───────────────────────────────────────

    /**
     * يطبع محتوى هذا النطاق فقط (بدون الأبناء) بشكل شجري متداخل.
     * (سلوك أصلي، لم يتغيّر — أُبقي كما هو حتى لا ينكسر أي استدعاء قديم له.)
     *
     * @param indent عمق الإزاحة
     */
    public String toString(int indent) {
        String pad = "  ".repeat(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(pad).append("Scope [").append(scopeName).append("]\n");
        for (Symbol sym : symbols.values()) {
            sb.append(pad).append("  |-- ")
              .append(sym.getName())
              .append(" : ").append(sym.getType())
              .append(" (line ").append(sym.getLine()).append(")\n");
        }
        return sb.toString();
    }

    /**
     * [Phase 3 — جديد] يطبع هذا النطاق <b>وكل أبنائه بشكل تكراري</b>،
     * بنفس الفكرة المطبّقة أصلاً في {@code Semantic.SymbolTable.printHierarchy}
     * بجانب HTML/Jinja. يُستخدم لتصدير جدول رموز Python الكامل إلى
     * {@code compiler_output/symbol_table_python.txt}.
     *
     * @param indent عمق الإزاحة الحالي
     */
    public String toHierarchyString(int indent) {
        StringBuilder sb = new StringBuilder(toString(indent));
        for (pysemantic.Scope child : children) {
            sb.append(child.toHierarchyString(indent + 1));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(0);
    }
}
