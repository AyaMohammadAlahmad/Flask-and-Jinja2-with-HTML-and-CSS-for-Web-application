package AST;

/**
 * HTML DOM layer — represents a <!doctype html> declaration.
 */
public class DoctypeNode extends ContentNode {

    private final String value;

    public DoctypeNode(String value, int line) {
        super(line);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String print(String indent) {
        return indent
                + "DoctypeNode"
                + " (line "
                + line
                + ")\n";
    }
}
