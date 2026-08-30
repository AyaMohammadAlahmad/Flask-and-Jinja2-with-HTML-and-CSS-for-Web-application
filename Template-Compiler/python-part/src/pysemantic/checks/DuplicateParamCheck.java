package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 3: تكرار اسم معامل (parameter) داخل نفس الدالة.
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   def foo(x, x):
 *       return x
 * </pre>
 */
public class DuplicateParamCheck {

    /**
     * يتحقق من عدم وجود اسم المعامل محلياً داخل نطاق الدالة قبل تعريفه.
     *
     * @param paramName    اسم المعامل المراد تعريفه
     * @param functionName اسم الدالة (لرسالة الخطأ فقط)
     * @param line         رقم السطر
     * @param table        جدول الرموز الحالي (داخل نطاق الدالة)
     * @return {@link CompilerError} لو الاسم مكرر، أو {@code null} لو سليم
     */
    public static CompilerError check(String paramName, String functionName,
                                       int line, pysemantic.SymbolTable table) {
        if (table.containsLocal(paramName)) {
            return CompilerError.semantic(line,
                "Parameter '" + paramName + "' is already defined in function '"
                + functionName + "'");
        }
        return null;
    }
}
