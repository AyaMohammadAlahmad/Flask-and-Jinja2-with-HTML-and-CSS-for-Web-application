package pysemantic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * المنسّق المسؤول عن إدارة شجرة النطاقات بالكامل.
 *
 * <p>يحتفظ بـ:</p>
 * <ul>
 *   <li>{@code globalScope} — النطاق الجذري، يبقى موجوداً طوال عمر التحليل</li>
 *   <li>{@code scopeStack}  — مكدّس (Stack) للنطاقات الحالية، يسمح بالدخول
 *       والخروج من النطاقات المتداخلة (دوال داخل دوال مستقبلاً)</li>
 * </ul>
 *
 * <p>نمط الاستخدام النموذجي داخل الـ Visitor:</p>
 * <pre>
 *   SymbolTable table = new SymbolTable();
 *
 *   // عند الدخول لجسم دالة
 *   table.enterScope("function:index");
 *   table.define(new Symbol("product_id", SymbolType.VARIABLE, line));
 *   // ... زيارة الجسم ...
 *   table.exitScope();
 * </pre>
 */
public class SymbolTable {

    private final Scope        globalScope;
    private final Deque<Scope> scopeStack = new ArrayDeque<>();

    /**
     * ينشئ جدول الرموز ويهيّئ الـ Global Scope كنطاق حالي مبدئياً.
     */
    public SymbolTable() {
        this.globalScope = new Scope("global", null);
        scopeStack.push(globalScope);
    }

    // ── إدارة النطاقات ───────────────────────────────────────────────────

    /**
     * يدخل نطاقاً جديداً (مثلاً عند بداية جسم دالة) ويجعله النطاق الحالي.
     * النطاق الجديد يأخذ النطاق الحالي كأب له تلقائياً.
     *
     * @param scopeName اسم وصفي للنطاق الجديد، e.g. "function:index"
     * @return النطاق الجديد الذي تم إنشاؤه ودخوله
     */
    public Scope enterScope(String scopeName) {
        Scope child = new Scope(scopeName, getCurrentScope());
        scopeStack.push(child);
        return child;
    }

    /**
     * يخرج من النطاق الحالي ويعود للنطاق الأب.
     * لا يُسمح بالخروج من الـ Global Scope.
     */
    public void exitScope() {
        if (scopeStack.size() <= 1) {
            throw new IllegalStateException("Cannot exit the global scope.");
        }
        scopeStack.pop();
    }

    /**
     * @return النطاق الحالي (أعلى المكدّس)
     */
    public Scope getCurrentScope() {
        return scopeStack.peek();
    }

    /**
     * @return الـ Global Scope مباشرةً (بغض النظر عن النطاق الحالي)
     */
    public Scope getGlobalScope() {
        return globalScope;
    }

    // ── اختصارات مريحة تُفوَّض للنطاق الحالي ─────────────────────────────

    /** يعرّف رمزاً في النطاق الحالي. */
    public void define(Symbol sym) {
        getCurrentScope().define(sym);
    }

    /** يبحث عن رمز بدءاً من النطاق الحالي صعوداً حتى الـ Global Scope. */
    public Symbol lookup(String name) {
        return getCurrentScope().lookup(name);
    }

    /** يتحقق من وجود الرمز في النطاق الحالي فقط (بدون صعود). */
    public boolean containsLocal(String name) {
        return getCurrentScope().containsLocal(name);
    }

    /** يعرّف رمزاً مباشرة في الـ Global Scope بغض النظر عن النطاق الحالي. */
    public void defineGlobal(Symbol sym) {
        globalScope.define(sym);
    }

    // ── طباعة كامل شجرة النطاقات للـ debugging ──────────────────────────

    /**
     * يطبع الـ Global Scope وكل ما تبقى في المكدّس (مفيد أثناء التحليل
     * لمعرفة حالة النطاقات في أي لحظة).
     */
    @Override
    public String toString() {
        return globalScope.toString(0);
    }
}
