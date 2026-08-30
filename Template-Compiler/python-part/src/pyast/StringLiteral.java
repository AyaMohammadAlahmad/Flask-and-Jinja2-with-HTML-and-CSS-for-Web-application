package pyast;

/**
 * يمثل ثابتاً نصياً: {@code "hello"} أو {@code 'world'}.
 * بديل {@code #LiteralExpr} ← {@code StringLiteral} في قاعدة {@code literal}.
 *
 * <pre>
 *   StringLiteral: 'Hello' (line 5)
 * </pre>
 */
public class StringLiteral extends Expr {
    private final String value;   // النص الأصلي مع علامتي التنصيص
    public StringLiteral(int line, String value) { super(line); this.value = value; }
    public String getValue() { return value; }
    @Override
    public String toString(int indent) {
        return pad(indent) + "StringLiteral: " + value + " (line " + line + ")\n";
    }
}
