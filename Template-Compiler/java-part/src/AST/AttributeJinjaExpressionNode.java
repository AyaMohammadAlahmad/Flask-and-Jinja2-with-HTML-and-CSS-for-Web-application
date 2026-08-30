package AST;

/**
 * Expression layer — wraps a Jinja expression that appears as the value
 * of an HTML attribute, e.g. href="{{ url_for('index') }}".
 *
 * This is an ASTExpression so it fits cleanly into AttributeNode.value
 * alongside StringLiteralNode without any special-casing outside the
 * expression hierarchy.
 */
public class AttributeJinjaExpressionNode extends ASTExpression {

    private final ASTJinjaExpression expression;

    public AttributeJinjaExpressionNode(ASTJinjaExpression expression, int line) {
        super(line);
        this.expression = expression;
    }

    public ASTJinjaExpression getExpression() {
        return expression;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("AttributeJinjaExpression")
                .append(" (line ")
                .append(line)
                .append(")\n");
        sb.append(expression.print(indent + "  "));
        return sb.toString();
    }
}
