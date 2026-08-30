package pyast;

import java.util.List;

/**
 * يمثل استدعاء دالة: {@code func(arg1, key=val)}.
 * يقابل البديل {@code #CallExpr} في قاعدة {@code expr}.
 *
 * <pre>
 *   CallExpr (line 8)
 *     callee:
 *       NameExpr: render_template (line 8)
 *     args:
 *       PositionalArg (line 8)
 *         StringLiteral: 'index.html' (line 8)
 * </pre>
 */
public class CallExpr extends Expr {

    private final Expr callee;
    private final List<Argument> args;

    public CallExpr(int line, Expr callee, List<Argument> args) {
        super(line);
        this.callee = callee;
        this.args   = args;
    }

    public Expr getCallee()         { return callee; }
    public List<Argument> getArgs() { return args; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("CallExpr (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("callee:\n");
        sb.append(callee.toString(indent + 2));
        if (!args.isEmpty()) {
            sb.append(pad(indent + 1)).append("args:\n");
            for (Argument arg : args) {
                sb.append(arg.toString(indent + 2));
            }
        }
        return sb.toString();
    }
}
