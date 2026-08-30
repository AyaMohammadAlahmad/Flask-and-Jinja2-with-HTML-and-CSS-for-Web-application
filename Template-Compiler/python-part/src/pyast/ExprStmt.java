package pyast;

/**
 * يمثل تعبيراً يُستخدم كجملة منفردة (bare call أو method call).
 * يقابل البديل {@code #ExprStmt} في قاعدة {@code statement}.
 *
 * <pre>
 *   ExprStmt (line 8)
 *     MethodCallExpr: .run (line 8)
 *       ...
 * </pre>
 */
public class ExprStmt extends Stmt {

    private final Expr expr;

    public ExprStmt(int line, Expr expr) {
        super(line);
        this.expr = expr;
    }

    public Expr getExpr() { return expr; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("ExprStmt (line ").append(line).append(")\n");
        sb.append(expr.toString(indent + 1));
        return sb.toString();
    }
}
