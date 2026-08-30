package pyast;

/**
 * يمثل الإسناد البسيط: {@code x = expr}
 * بديل {@code #SimpleAssign} في قاعدة {@code assignment}.
 *
 * <pre>
 *   AssignNode (line 7)
 *     AssignTarget: app.config['SECRET_KEY'] (line 7)
 *     StringLiteral: 'mysecret' (line 7)
 * </pre>
 */
public class Assign extends Stmt {

    private final AssignTarget target;
    private final Expr value;

    public Assign(int line, AssignTarget target, Expr value) {
        super(line);
        this.target = target;
        this.value  = value;
    }

    public AssignTarget getTarget() { return target; }
    public Expr getValue()          { return value; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("AssignNode (line ").append(line).append(")\n");
        sb.append(target.toString(indent + 1));
        sb.append(value.toString(indent + 1));
        return sb.toString();
    }
}
