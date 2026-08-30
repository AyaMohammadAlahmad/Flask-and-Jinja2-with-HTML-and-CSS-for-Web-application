package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 5: استدعاء اسم غير معرّف أصلاً كدالة.
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   def foo():
 *       return some_undefined_function()
 * </pre>
 */
public class CallUndefinedCheck {

    /**
     * يتحقق فقط من وجود الاسم في الجدول — لا يتحقق من نوعه (هذا من مهمة
     * {@link CallNonFunctionCheck}).
     *
     * @param calleeName اسم العنصر المُستدعى
     * @param line       رقم السطر
     * @param table      جدول الرموز الحالي
     * @return {@link CompilerError} لو الاسم غير معرّف، أو {@code null} لو موجود
     */
    public static CompilerError check(String calleeName, int line, pysemantic.SymbolTable table) {
        pysemantic.Symbol sym = table.lookup(calleeName);
        if (sym == null) {
            return CompilerError.semantic(line, "Call to undefined name '" + calleeName + "'");
        }
        return null;
    }
}
