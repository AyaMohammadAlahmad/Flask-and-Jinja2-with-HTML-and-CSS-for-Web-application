package pyast;

/**
 * الكلاس الجذر لجميع نودز الـ AST.
 * يخزن رقم السطر لتسهيل تتبع الأخطاء.
 */
public abstract class ASTNode {

    protected final int line;

    protected ASTNode(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    /**
     * كل نود يطبع نفسه بشكل شجري متداخل.
     * @param indent عمق الإزاحة الحالي (0 = جذر)
     */
    public abstract String toString(int indent);

    /** ينتج سلسلة من المسافات (2 مسافة لكل مستوى). */
    protected static String pad(int indent) {
        return "  ".repeat(indent);
    }

    @Override
    public String toString() {
        return toString(0);
    }
}
