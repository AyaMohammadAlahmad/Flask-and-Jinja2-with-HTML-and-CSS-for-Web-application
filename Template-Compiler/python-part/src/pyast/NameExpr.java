package pyast;

/** يمثل متغيراً باسمه: {@code x}، {@code product_id}. بديل {@code #NameExpr}. */
public class NameExpr extends Expr {
    private final String name;
    public NameExpr(int line, String name) { super(line); this.name = name; }
    public String getName() { return name; }
    @Override
    public String toString(int indent) {
        return pad(indent) + "NameExpr: " + name + " (line " + line + ")\n";
    }
}
