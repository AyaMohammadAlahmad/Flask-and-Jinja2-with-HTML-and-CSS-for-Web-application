package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 1: استخدام متغير (اسم) غير معرّف في أي تعبير.
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   return undefined_variable
 * </pre>
 */
public class UndefinedNameCheck {

    /**
     * يتحقق من أن الاسم معرّف في الجدول (محلياً أو بأحد الآباء).
     *
     * @param name  الاسم المراد فحصه
     * @param line  رقم السطر لتقرير الخطأ
     * @param table جدول الرموز الحالي
     * @return {@link CompilerError} إذا كان الاسم غير معرّف، أو {@code null} إذا كان سليماً
     */
    public static CompilerError check(String name, int line, pysemantic.SymbolTable table) {
        pysemantic.Symbol sym = table.lookup(name);
        if (sym == null) {
            return CompilerError.semantic(line, "Name '" + name + "' is not defined");
        }
        return null;
    }
}
