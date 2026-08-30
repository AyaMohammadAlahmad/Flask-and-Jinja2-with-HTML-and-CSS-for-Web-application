package pysemantic.checks;

/**
 * يمثل خطأً دلالياً واحداً: رقم السطر + الرسالة.
 *
 * <p>كل كلاس Check يُعيد كائناً من هذا النوع عند اكتشاف خطأ،
 * أو {@code null} إذا لم يجد أي مشكلة.</p>
 */
public class SemanticError {

    private final int    line;
    private final String message;

    public SemanticError(int line, String message) {
        this.line    = line;
        this.message = message;
    }

    public int getLine()       { return line; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return "Semantic Error (Line " + line + "): " + message;
    }
}
