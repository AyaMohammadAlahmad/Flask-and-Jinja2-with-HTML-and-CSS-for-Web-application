package AST;

import java.util.ArrayList;
import java.util.List;

/**
 * Expression layer — a dotted variable reference, e.g. product.name or loop.index.
 */
public class ASTVariable extends ASTExpression {

    private final List<String> identifiers;

    public ASTVariable(int line) {
        super(line);
        this.identifiers = new ArrayList<>();
    }

    public void addIdentifier(String id) {
        identifiers.add(id);
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    /** Returns the full dotted name, e.g. "product.name". */
    public String getFullName() {
        return String.join(".", identifiers);
    }

    @Override
    public String print(String indent) {
        return indent
                + "Variable: "
                + getFullName()
                + " (line "
                + line
                + ")\n";
    }
}
