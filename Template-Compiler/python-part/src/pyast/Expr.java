package pyast;

/**
 * الكلاس المجرد لجميع التعبيرات (Expressions).
 * ترث منه جميع نودز التعبيرات في برج الأولويات.
 */
public abstract class Expr extends ASTNode {
    protected Expr(int line) {
        super(line);
    }
}
