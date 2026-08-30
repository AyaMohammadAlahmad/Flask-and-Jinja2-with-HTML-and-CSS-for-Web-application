package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 9: استخدام {@code return} خارج أي دالة.
 *
 * <p>يحتاج هذا الفحص "عمق الدالة الحالي" (functionDepth) الذي تديره
 * {@code SemanticAnalyzer} أثناء المشي على الشجرة — نفس فكرة
 * {@link BreakOutsideLoopCheck} مع loopDepth.</p>
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   return "hello"    ← خارج أي دالة
 *
 *   def foo():
 *       return "hello"  ← صح
 * </pre>
 */
public class ReturnOutsideFunctionCheck {

    /**
     * @param functionDepth عدد الدوال المتداخلة التي نحن بداخلها حالياً
     *                      (0 = خارج أي دالة)
     * @param line          رقم السطر
     * @return {@link CompilerError} لو {@code functionDepth == 0}،
     *         أو {@code null} لو داخل دالة
     */
    public static CompilerError check(int functionDepth, int line) {
        if (functionDepth == 0) {
            return CompilerError.semantic(line, "'return' used outside of a function");
        }
        return null;
    }
}
