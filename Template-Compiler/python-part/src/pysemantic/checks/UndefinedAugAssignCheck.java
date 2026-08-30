package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 4: استخدام {@code +=} (أو أي إسناد مركّب) على متغير غير معرّف مسبقاً.
 *
 * <p>عكس {@code =} العادية، فإن {@code +=} لا يصح أن تُنشئ متغيراً جديداً —
 * المتغير لازم يكون موجوداً مسبقاً لأن العملية تعتمد على قيمته الحالية.</p>
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   def foo():
 *       count += 1
 * </pre>
 */
public class UndefinedAugAssignCheck {

    /**
     * @param varName اسم المتغير المُسنَد إليه
     * @param line    رقم السطر
     * @param table   جدول الرموز الحالي
     * @return {@link CompilerError} لو المتغير غير معرّف، أو {@code null} لو سليم
     */
    public static CompilerError check(String varName, int line, pysemantic.SymbolTable table) {
        pysemantic.Symbol sym = table.lookup(varName);
        if (sym == null) {
            return CompilerError.semantic(line, "Name '" + varName + "' is not defined");
        }
        return null;
    }
}
