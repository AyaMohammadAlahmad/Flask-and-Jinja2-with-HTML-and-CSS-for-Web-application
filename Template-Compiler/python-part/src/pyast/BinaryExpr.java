package pyast;

/**
 * يمثل أي عملية ثنائية (binary).
 * يغطي البدائل التالية في قاعدة {@code expr}:
 * <ul>
 *   <li>{@code #PowExpr}     — {@code **}</li>
 *   <li>{@code #MulExpr}     — {@code *  /  %}</li>
 *   <li>{@code #AddExpr}     — {@code +  -}</li>
 *   <li>{@code #CompareExpr} — {@code ==  !=  <  >  <=  >=}</li>
 *   <li>{@code #IsExpr}      — {@code is}</li>
 *   <li>{@code #IsNotExpr}   — {@code is not}</li>
 *   <li>{@code #AndExpr}     — {@code and}</li>
 *   <li>{@code #OrExpr}      — {@code or}</li>
 * </ul>
 *
 * <pre>
 *   BinaryExpr: + (line 9)
 *     left:
 *       NameExpr: a (line 9)
 *     right:
 *       NumberLiteral: 1 (line 9)
 * </pre>
 */
public class BinaryExpr extends Expr {

    private final String op;
    private final Expr left;
    private final Expr right;

    public BinaryExpr(int line, String op, Expr left, Expr right) {
        super(line);
        this.op    = op;
        this.left  = left;
        this.right = right;
    }

    public String getOp()  { return op; }
    public Expr getLeft()  { return left; }
    public Expr getRight() { return right; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("BinaryExpr: ").append(op)
          .append(" (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("left:\n");
        sb.append(left.toString(indent + 2));
        sb.append(pad(indent + 1)).append("right:\n");
        sb.append(right.toString(indent + 2));
        return sb.toString();
    }
}
