package pyast;

/**
 * يمثل التعبير الثلاثي: {@code value if condition else other}.
 * يقابل البديل {@code #TernaryExpr} في قاعدة {@code expr}.
 *
 * <pre>
 *   TernaryExpr (line 5)
 *     thenExpr:
 *       StringLiteral: 'yes' (line 5)
 *     condition:
 *       NameExpr: flag (line 5)
 *     elseExpr:
 *       StringLiteral: 'no' (line 5)
 * </pre>
 */
public class TernaryExpr extends Expr {

    private final Expr thenExpr;   // الجزء الذي يأتي قبل if
    private final Expr condition;
    private final Expr elseExpr;

    public TernaryExpr(int line, Expr thenExpr, Expr condition, Expr elseExpr) {
        super(line);
        this.thenExpr  = thenExpr;
        this.condition = condition;
        this.elseExpr  = elseExpr;
    }

    public Expr getThenExpr()  { return thenExpr; }
    public Expr getCondition() { return condition; }
    public Expr getElseExpr()  { return elseExpr; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("TernaryExpr (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("thenExpr:\n");
        sb.append(thenExpr.toString(indent + 2));
        sb.append(pad(indent + 1)).append("condition:\n");
        sb.append(condition.toString(indent + 2));
        sb.append(pad(indent + 1)).append("elseExpr:\n");
        sb.append(elseExpr.toString(indent + 2));
        return sb.toString();
    }
}
