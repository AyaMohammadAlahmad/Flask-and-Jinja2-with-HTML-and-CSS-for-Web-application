package pyast;

public class UnaryExpr extends Expr {

    private final String op;       // "-" | "+" | "not"
    private final Expr operand;

    public UnaryExpr(int line, String op, Expr operand) {
        super(line);
        this.op      = op;
        this.operand = operand;
    }

    public String getOp()    { return op; }
    public Expr getOperand() { return operand; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("UnaryExpr: ").append(op)
          .append(" (line ").append(line).append(")\n");
        sb.append(operand.toString(indent + 1));
        return sb.toString();
    }
}
