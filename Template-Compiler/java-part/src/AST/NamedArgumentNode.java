package AST;

/**
 * Expression layer — a named keyword argument in a function call,
 * e.g. endpoint='index' in url_for('index', id=product.id).
 */
public class NamedArgumentNode extends ASTNode {

    private final String name;
    private final ASTExpression value;

    public NamedArgumentNode(String name, ASTExpression value, int line) {
        super(line);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public ASTExpression getValue() {
        return value;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("NamedArgument: ")
                .append(name)
                .append("\n");
        if (value != null) {
            sb.append(value.print(indent + "  "));
        }
        return sb.toString();
    }
}
