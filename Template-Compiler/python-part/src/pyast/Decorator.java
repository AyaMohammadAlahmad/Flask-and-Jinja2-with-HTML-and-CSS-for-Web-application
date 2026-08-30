package pyast;

import java.util.List;

/**
 * يمثل سطر الـ decorator.
 * مثال: {@code @app.route("/", methods=["GET","POST"])}
 *
 * <pre>
 *   Decorator: @app.route (line 3)
 *     PositionalArg (line 3)
 *       StringLiteral: '/' (line 3)
 *     KeywordArg: methods (line 3)
 *       ListExpr (line 3)
 *         ...
 * </pre>
 */
public class Decorator extends ASTNode {

    private final String name;             // e.g. "app.route"
    private final List<Argument> args;     // قد تكون فارغة

    public Decorator(int line, String name, List<Argument> args) {
        super(line);
        this.name = name;
        this.args = args;
    }

    public String getName()          { return name; }
    public List<Argument> getArgs()  { return args; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("DecoratorNode: @").append(name)
          .append(" (line ").append(line).append(")\n");
        for (Argument arg : args) {
            sb.append(arg.toString(indent + 1));
        }
        return sb.toString();
    }
}
