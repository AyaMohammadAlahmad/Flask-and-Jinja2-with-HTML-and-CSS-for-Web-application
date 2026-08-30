package AST;

/**
 * HTML DOM layer (CSS sub-layer) — a single CSS property/value declaration,
 * e.g. "color: red".
 */
public class CSSDeclarationNode extends ASTNode {

    private final String property;
    private final String value;

    public CSSDeclarationNode(String property, String value, int line) {
        super(line);
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String print(String indent) {
        return indent
                + "CSSDeclaration: "
                + property
                + " = "
                + value
                + "\n";
    }
}
