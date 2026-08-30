package AST;

/**
 * HTML DOM layer — a single attribute on an HTML element.
 * The value is always an ASTExpression: either a StringLiteralNode
 * for plain string values, or an AttributeJinjaExpressionNode when
 * the attribute value contains a Jinja expression such as {{ var }}.
 *
 * This unifies the attribute value model so callers never need to
 * distinguish between string and Jinja attribute values by type-checking
 * outside of the expression hierarchy.
 */
public class AttributeNode extends ASTNode {

    private final String name;
    private final ASTExpression value;

    public AttributeNode(String name, ASTExpression value, int line) {
        super(line);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    /** May be null for boolean attributes (e.g. <input disabled>). */
    public ASTExpression getValue() {
        return value;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("AttributeNode: ")
                .append(name)
                .append(" =");
        if (value != null) {
            sb.append("\n").append(value.print(indent + "  "));
        } else {
            sb.append(" (boolean)\n");
        }
        return sb.toString();
    }
}
