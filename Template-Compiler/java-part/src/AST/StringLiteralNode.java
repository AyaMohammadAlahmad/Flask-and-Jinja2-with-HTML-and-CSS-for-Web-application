package AST;

/**
 * Expression layer — a literal string value, e.g. "hello" used
 * as an attribute value or function argument.
 */
public class StringLiteralNode extends ASTExpression {

    private final String value;

    public StringLiteralNode(String value, int line) {
        super(line);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String print(String indent) {
        return indent
                + "StringLiteral: "
                + value
                + " (line "
                + line
                + ")\n";
    }
}
