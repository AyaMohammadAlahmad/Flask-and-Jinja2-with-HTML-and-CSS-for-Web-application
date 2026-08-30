package pyast;

/**
 * يمثل الإسناد المركّب: {@code x += 1}، {@code count -= step}.
 * بديل {@code #AugAssign} في قاعدة {@code assignment}.
 *
 * <pre>
 *   AugAssignNode: += (line 12)
 *     target: count
 *     NumberLiteral: 1 (line 12)
 * </pre>
 */
public class AugAssign extends Stmt {

    private final String varName;  // Identifier فقط (كما في الـ grammar)
    private final String op;       // "+=" | "-=" | "*=" | "/=" | "%=" | "**="
    private final Expr value;

    public AugAssign(int line, String varName, String op, Expr value) {
        super(line);
        this.varName = varName;
        this.op      = op;
        this.value   = value;
    }

    public String getVarName() { return varName; }
    public String getOp()      { return op; }
    public Expr getValue()     { return value; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("AugAssignNode: ").append(op)
          .append(" (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("target: ").append(varName).append("\n");
        sb.append(value.toString(indent + 1));
        return sb.toString();
    }
}
