package pyast;

/**
 * يمثل {@code True} أو {@code False}.
 * بديل {@code #LiteralExpr} ← {@code TRUE | FALSE} في قاعدة {@code literal}.
 *
 * <pre>
 *   BoolLiteral: true (line 6)
 * </pre>
 */
public class BoolLiteral extends Expr {
    private final boolean value;
    public BoolLiteral(int line, boolean value) { super(line); this.value = value; }
    public boolean getValue() { return value; }
    @Override
    public String toString(int indent) {
        return pad(indent) + "BoolLiteral: " + value + " (line " + line + ")\n";
    }
}
