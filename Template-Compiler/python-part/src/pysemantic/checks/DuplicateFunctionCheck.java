package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 2: تعريف دالة باسم موجود مسبقاً في نفس النطاق.
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   def foo(): return 1
 *   def foo(): return 2
 * </pre>
 */
public class DuplicateFunctionCheck {

    /**
     * يتحقق من عدم وجود اسم الدالة محلياً في النطاق الحالي قبل تعريفها.
     *
     * @param functionName اسم الدالة الجديدة
     * @param line         رقم السطر
     * @param table        جدول الرموز الحالي
     * @return {@link CompilerError} لو الاسم مكرر محلياً، أو {@code null} لو سليم
     */
    public static CompilerError check(String functionName, int line, pysemantic.SymbolTable table) {
        if (table.containsLocal(functionName)) {
            return CompilerError.semantic(line,
                "Function '" + functionName + "' is already defined in this scope");
        }
        return null;
    }
}
