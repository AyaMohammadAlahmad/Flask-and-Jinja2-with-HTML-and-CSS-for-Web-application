package pysemantic.types;

import pyast.*;

/**
 * يستنتج نوع ({@link ValueType}) أي تعبير ({@link Expr}) وقت الترجمة.
 *
 * <p>قواعد الاستنتاج:</p>
 * <ul>
 *   <li>الثوابت (Literals) — نوعها معروف مباشرة من النوع نفسه</li>
 *   <li>الأسماء (NameExpr) — نبحث عن النوع بجدول الرموز (غير مدعوم حالياً → UNKNOWN)</li>
 *   <li>العمليات الثنائية (BinaryExpr) — نستنتج نوع الناتج من نوعي الطرفين والعملية</li>
 *   <li>أي شيء آخر — UNKNOWN (آمن: لا يطلق أخطاء كاذبة)</li>
 * </ul>
 */
public class TypeInference {

    /**
     * يستنتج نوع التعبير.
     *
     * @param expr  التعبير المراد استنتاج نوعه
     * @param table جدول الرموز الحالي (للبحث عن أنواع المتغيرات مستقبلاً)
     * @return نوع التعبير، أو {@link ValueType#UNKNOWN} إذا تعذّر الاستنتاج
     */
    public static ValueType infer(Expr expr, pysemantic.SymbolTable table) {
        if (expr == null) return ValueType.UNKNOWN;

        // ── الثوابت — نوعها واضح مباشرة ────────────────────────────────
        if (expr instanceof StringLiteral)  return ValueType.STRING;
        if (expr instanceof NumberLiteral)  return ValueType.NUMBER;
        if (expr instanceof BoolLiteral)    return ValueType.BOOL;
        if (expr instanceof NoneLiteral)    return ValueType.NONE;
        if (expr instanceof ListExpr)       return ValueType.LIST;
        if (expr instanceof DictExpr)       return ValueType.DICT;

        // ── الأسماء — نبحث عن نوعها بجدول الرموز ───────────────────────
        // حالياً الجدول يخزن SymbolType (VARIABLE/FUNCTION/IMPORT)
        // وليس ValueType — لذلك نرجع UNKNOWN (آمن، لا يطلق أخطاء كاذبة)
        if (expr instanceof NameExpr) return ValueType.UNKNOWN;

        // ── العمليات الثنائية ────────────────────────────────────────────
        if (expr instanceof BinaryExpr b) {
            ValueType left  = infer(b.getLeft(),  table);
            ValueType right = infer(b.getRight(), table);
            return inferBinary(left, b.getOp(), right);
        }

        // ── العمليات الأحادية ────────────────────────────────────────────
        if (expr instanceof UnaryExpr u) {
            ValueType operand = infer(u.getOperand(), table);
            if (u.getOp().equals("not"))            return ValueType.BOOL;
            if (operand == ValueType.NUMBER)         return ValueType.NUMBER;
            return ValueType.UNKNOWN;
        }

        // ── التعبير الثلاثي ──────────────────────────────────────────────
        if (expr instanceof TernaryExpr t) {
            ValueType thenType = infer(t.getThenExpr(), table);
            ValueType elseType = infer(t.getElseExpr(), table);
            // لو الطرفان بنفس النوع، الناتج نفس النوع
            if (thenType == elseType) return thenType;
            return ValueType.UNKNOWN;
        }

        // ── القوسين ──────────────────────────────────────────────────────
        if (expr instanceof ParenExpr p) return infer(p.getInner(), table);

        // ── أي شيء آخر (CallExpr, MethodCallExpr, SubscriptExpr, AttrExpr)
        // نوع الناتج غير معروف وقت الترجمة
        return ValueType.UNKNOWN;
    }

    // ═══════════════════════════════════════════════════════
    //  قواعد توافق العمليات الثنائية
    // ═══════════════════════════════════════════════════════

    /**
     * يحدد نوع ناتج عملية ثنائية بناءً على نوعي الطرفين والمعامل.
     *
     * <p>إذا كان أحد الطرفين {@link ValueType#UNKNOWN}، يُرجع {@code UNKNOWN}
     * لتجنب الأخطاء الكاذبة.</p>
     *
     * @return نوع الناتج، أو {@code null} إذا كانت العملية غير متوافقة (Type Mismatch)
     */
    public static ValueType inferBinary(ValueType left, String op, ValueType right) {

        // لو أي طرف UNKNOWN → لا نحكم
        if (left == ValueType.UNKNOWN || right == ValueType.UNKNOWN) {
            return ValueType.UNKNOWN;
        }

        switch (op) {

            // ── العمليات الحسابية ────────────────────────────────────────
            case "+":
                if (left == ValueType.NUMBER && right == ValueType.NUMBER) return ValueType.NUMBER;
                if (left == ValueType.STRING && right == ValueType.STRING) return ValueType.STRING;
                return null;  // Type Mismatch: STRING+NUMBER, BOOL+NUMBER, إلخ

            case "-": case "*": case "/": case "%": case "**":
                if (left == ValueType.NUMBER && right == ValueType.NUMBER) return ValueType.NUMBER;
                return null;  // Type Mismatch

            // ── المقارنات — نفس النوع فقط ───────────────────────────────
            case "==": case "!=":
                // == و != مسموح بينهم بأي نوعين متساويين
                if (left == right) return ValueType.BOOL;
                return null;  // Type Mismatch

            case "<": case ">": case "<=": case ">=":
                // المقارنات الترتيبية: أرقام فقط
                if (left == ValueType.NUMBER && right == ValueType.NUMBER) return ValueType.BOOL;
                return null;  // Type Mismatch

            // ── العمليات المنطقية ────────────────────────────────────────
            case "and": case "or":
                return ValueType.BOOL;   // دائماً Bool

            // ── is / is not ─────────────────────────────────────────────
            case "is": case "is not":
                return ValueType.BOOL;   // دائماً Bool (مقارنة هوية)

            default:
                return ValueType.UNKNOWN;
        }
    }
}
