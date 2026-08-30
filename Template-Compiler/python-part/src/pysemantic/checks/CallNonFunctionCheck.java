package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 6: استدعاء اسم معرّف فعلاً، لكنه متغير عادي وليس دالة.
 *
 * <p>هذا الفحص يُستدعى فقط بعد أن نتأكد أن الاسم موجود
 * (أي بعد نجاح {@link CallUndefinedCheck})، لذلك من المتوقع ألا تكون
 * النتيجة {@code null} عند استدعاء هذا الكلاس.</p>
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   def foo():
 *       x = 5
 *       return x()
 * </pre>
 */
public class CallNonFunctionCheck {

    /**
     * @param calleeName اسم العنصر المُستدعى
     * @param line       رقم السطر
     * @param table      جدول الرموز الحالي
     * @return {@link CompilerError} لو الاسم متغير عادي وليس دالة، أو {@code null} لو سليم
     */
    public static CompilerError check(String calleeName, int line, pysemantic.SymbolTable table) {
        pysemantic.Symbol sym = table.lookup(calleeName);

        // لو الاسم غير موجود أصلاً، هذا ليس مسؤولية هذا الفحص
        // (مسؤولية CallUndefinedCheck)
        if (sym == null) return null;

        if (sym.getType() == pysemantic.SymbolType.VARIABLE) {
            return CompilerError.semantic(line,
                "'" + calleeName + "' is not callable (it is a variable)");
        }
        return null;
    }
}
