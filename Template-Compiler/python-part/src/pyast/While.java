package pyast;

/**
 * يمثل حلقة {@code while condition:}
 * يقابل البديل {@code #While} في قاعدة {@code statement}.
 *
 * <pre>
 *   While (line 20)
 *     condition:
 *       NameExpr: running (line 20)
 *     BlockRule (line 21)
 *       ...
 * </pre>
 */
public class While extends Stmt {

    private final Expr condition;
    private final Block body;

    public While(int line, Expr condition, Block body) {
        super(line);
        this.condition = condition;
        this.body      = body;
    }

    public Expr getCondition() { return condition; }
    public Block getBody()     { return body; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("While (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("condition:\n");
        sb.append(condition.toString(indent + 2));
        sb.append(body.toString(indent + 1));
        return sb.toString();
    }
}
