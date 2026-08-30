package AST;

/**
 * Template layer — a Jinja2 output expression delimited by {{ }}.
 * Wraps an ASTExpression (variable, function call, or string literal)
 * and can appear anywhere inside HTML content or as an attribute value
 * (via AttributeJinjaExpressionNode).
 */
public class ASTJinjaExpression extends ContentNode {

    private final ASTExpression expression;

    public ASTJinjaExpression(ASTExpression expression, int line) {
        super(line);
        this.expression = expression;
    }

    public ASTExpression getExpression() {
        return expression;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("JinjaExpression")
                .append(" (line ")
                .append(line)
                .append(")\n");
        if (expression != null) {
            sb.append(expression.print(indent + "  "));
        }
        return sb.toString();
    }
}
