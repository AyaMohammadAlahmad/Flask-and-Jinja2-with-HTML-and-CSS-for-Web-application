package pyast;

/**
 * يمثل رقماً: {@code 42}، {@code 3.14}، {@code 1e3}.
 * بديل {@code #LiteralExpr} ← {@code NumberLiteral} في قاعدة {@code literal}.
 *
 * <pre>
 *   NumberLiteral: 404 (line 9)
 * </pre>
 */
public class NumberLiteral extends Expr {
    private final String value;   // النص الأصلي كما ورد في الكود
    public NumberLiteral(int line, String value) { super(line); this.value = value; }
    public String getValue() { return value; }
    @Override
    public String toString(int indent) {
        return pad(indent) + "NumberLiteral: " + value + " (line " + line + ")\n";
    }
}
