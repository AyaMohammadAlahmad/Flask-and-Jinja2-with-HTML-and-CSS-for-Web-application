package pyast;

/**
 * الكلاس المجرد لجميع الجمل (Statements).
 * ترث منه: Assign, AugAssign, Return, If, For, While, Break, ExprStmt.
 */
public abstract class Stmt extends ASTNode {
    protected Stmt(int line) {
        super(line);
    }
}
