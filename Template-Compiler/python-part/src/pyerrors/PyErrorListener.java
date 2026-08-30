package pyerrors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * الـ ErrorListener الموحّد لمرحلة Python، مبني على {@link BaseErrorListener}
 * الرسمي بمكتبة ANTLR.
 *
 * <p>يُستخدم نفس الكائن لكل من الـ Lexer والـ Parser (عبر
 * {@code removeErrorListeners()} ثم {@code addErrorListener(listener)})
 * فيلتقط:</p>
 * <ul>
 *   <li>أخطاء Lexical — لما {@code recognizer} يكون {@link Lexer}
 *       (عادة رسالتها تحتوي "token recognition error")</li>
 *   <li>أخطاء Syntax — لما {@code recognizer} يكون Parser (أي بنية غير متوقعة)</li>
 * </ul>
 *
 * <p>هاد الكلاس <b>لا يوقف</b> عملية الـ Parsing (سلوك ANTLR الافتراضي
 * بمحاولة الاستمرار/الاسترجاع Error Recovery)، هو فقط يجمع الأخطاء بقائمة
 * بدل ما تُطبع مباشرة على {@code System.err} وتضيع.</p>
 */
public class PyErrorListener extends BaseErrorListener {

    private final List<CompilerError> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                             Object offendingSymbol,
                             int line,
                             int charPositionInLine,
                             String msg,
                             RecognitionException e) {

        ErrorType type = (recognizer instanceof Lexer) ? ErrorType.LEXICAL : ErrorType.SYNTAX;
        errors.add(new CompilerError(type, line, charPositionInLine, msg));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<CompilerError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    /** يطبع كل الأخطاء الملتقطة على stdout — مفيد أثناء التطوير/الديباغ. */
    public void printErrors() {
        for (CompilerError err : errors) {
            System.out.println("❌ " + err);
        }
    }
}
