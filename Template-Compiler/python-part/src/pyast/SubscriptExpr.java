package pyast;

/**
 * يمثل الوصول بالفهرس: {@code obj[key]}.
 * يقابل البديل {@code #SubscriptExpr} في قاعدة {@code expr}.
 *
 * <pre>
 *   SubscriptExpr (line 10)
 *     object:
 *       NameExpr: products (line 10)
 *     index:
 *       NumberLiteral: 0 (line 10)
 * </pre>
 */
public class SubscriptExpr extends Expr {

    private final Expr object;
    private final Expr index;

    public SubscriptExpr(int line, Expr object, Expr index) {
        super(line);
        this.object = object;
        this.index  = index;
    }

    public Expr getObject() { return object; }
    public Expr getIndex()  { return index; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("SubscriptExpr (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("object:\n");
        sb.append(object.toString(indent + 2));
        sb.append(pad(indent + 1)).append("index:\n");
        sb.append(index.toString(indent + 2));
        return sb.toString();
    }
}
