package pyast;

/**
 * يمثل حلقة {@code for variable in iterable:}
 * يقابل البديل {@code #For} في قاعدة {@code statement}.
 *
 * <pre>
 *   For (line 15)
 *     var: item
 *     iterable:
 *       NameExpr: products (line 15)
 *     BlockRule (line 16)
 *       ...
 * </pre>
 */
public class For extends Stmt {

    private final String variable;
    private final Expr iterable;
    private final Block body;

    public For(int line, String variable, Expr iterable, Block body) {
        super(line);
        this.variable = variable;
        this.iterable = iterable;
        this.body     = body;
    }

    public String getVariable() { return variable; }
    public Expr getIterable()   { return iterable; }
    public Block getBody()      { return body; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("For (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("var: ").append(variable).append("\n");
        sb.append(pad(indent + 1)).append("iterable:\n");
        sb.append(iterable.toString(indent + 2));
        sb.append(body.toString(indent + 1));
        return sb.toString();
    }
}
