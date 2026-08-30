package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 7: استخدام {@code break} خارج أي حلقة {@code for}/{@code while}.
 *
 * <p>يحتاج هذا الفحص "عمق الحلقة الحالي" (loopDepth) الذي تديره
 * {@code SemanticAnalyzer} نفسها أثناء المشي على الشجرة، لذلك يُمرَّر
 * كمعامل بدل أن يخزّنه هذا الكلاس بنفسه (الكلاس بلا حالة / stateless).</p>
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   def foo():
 *       break
 * </pre>
 */
public class BreakOutsideLoopCheck {

    /**
     * @param loopDepth عدد الحلقات المتداخلة التي نحن بداخلها حالياً (0 = خارج أي حلقة)
     * @param line      رقم السطر
     * @return {@link CompilerError} لو loopDepth == 0، أو {@code null} لو داخل حلقة
     */
    public static CompilerError check(int loopDepth, int line) {
        if (loopDepth == 0) {
            return CompilerError.semantic(line, "'break' used outside of a loop");
        }
        return null;
    }
}
