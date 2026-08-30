package pyast;

/** يمثل الوصول إلى خاصية: {@code obj.attr}. بديل {@code #AttrExpr}. */
public class AttrExpr extends Expr {
    private final Expr object;
    private final String attr;

    public AttrExpr(int line, Expr object, String attr) {
        super(line);
        this.object = object;
        this.attr   = attr;
    }

    public Expr getObject() { return object; }
    public String getAttr() { return attr; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("AttrExpr: .").append(attr)
          .append(" (line ").append(line).append(")\n");
        sb.append(object.toString(indent + 1));
        return sb.toString();
    }
}
