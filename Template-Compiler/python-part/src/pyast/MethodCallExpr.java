package pyast;

import java.util.List;

/**
 * يمثل استدعاء method مدمجاً: {@code obj.method(args)}.
 * يقابل البديل {@code #MethodCallExpr} في قاعدة {@code expr}.
 * (مُدمج في قاعدة واحدة لتفادي AttrExpr + CallExpr متداخلتين).
 *
 * <pre>
 *   MethodCallExpr: .append (line 11)
 *     object:
 *       NameExpr: products (line 11)
 *     args:
 *       PositionalArg (line 11)
 *         NameExpr: item (line 11)
 * </pre>
 */
public class MethodCallExpr extends Expr {

    private final Expr object;
    private final String method;
    private final List<Argument> args;

    public MethodCallExpr(int line, Expr object, String method, List<Argument> args) {
        super(line);
        this.object = object;
        this.method = method;
        this.args   = args;
    }

    public Expr getObject()         { return object; }
    public String getMethod()       { return method; }
    public List<Argument> getArgs() { return args; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("MethodCallExpr: .").append(method)
          .append(" (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("object:\n");
        sb.append(object.toString(indent + 2));
        if (!args.isEmpty()) {
            sb.append(pad(indent + 1)).append("args:\n");
            for (Argument arg : args) {
                sb.append(arg.toString(indent + 2));
            }
        }
        return sb.toString();
    }
}
