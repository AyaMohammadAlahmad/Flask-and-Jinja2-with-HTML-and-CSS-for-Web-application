package pyast;

/**
 * يمثل الثابت {@code None}.
 * بديل {@code #LiteralExpr} ← {@code NONE} في قاعدة {@code literal}.
 *
 * <pre>
 *   NoneLiteral (line 7)
 * </pre>
 */
public class NoneLiteral extends Expr {
    public NoneLiteral(int line) { super(line); }
    @Override
    public String toString(int indent) {
        return pad(indent) + "NoneLiteral (line " + line + ")\n";
    }
}
