package AST;

/**
 * HTML DOM layer (CSS sub-layer) — a single CSS selector, e.g. ".container".
 */
public class CSSSelectorNode extends ASTNode {

    private final String selector;

    public CSSSelectorNode(String selector, int line) {
        super(line);
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

    @Override
    public String print(String indent) {
        return indent + "CSSSelector: " + selector + "\n";
    }
}
