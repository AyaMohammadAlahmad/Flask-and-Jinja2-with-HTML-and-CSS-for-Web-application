package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 8: محاولة إسناد قيمة عادية (عبر {@code =}) فوق اسم معرّف مسبقاً كدالة
 * في نفس النطاق.
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   def foo():
 *       return 1
 *
 *   foo = 99
 * </pre>
 */
public class ReassignFunctionCheck {

    /**
     * @param varName اسم المتغير المُسنَد إليه (الطرف الأيسر)
     * @param line    رقم السطر
     * @param table   جدول الرموز الحالي
     * @return {@link CompilerError} لو الاسم دالة معرّفة محلياً، أو {@code null} لو سليم
     */
    public static CompilerError check(String varName, int line, pysemantic.SymbolTable table) {
        pysemantic.Symbol existing = table.lookup(varName);

        boolean isExistingFunction = existing != null
                                   && existing.getType() == pysemantic.SymbolType.FUNCTION
                                   && table.containsLocal(varName);

        if (isExistingFunction) {
            return CompilerError.semantic(line,
                "Cannot assign to '" + varName + "': it is already defined as a function");
        }
        return null;
    }
}
