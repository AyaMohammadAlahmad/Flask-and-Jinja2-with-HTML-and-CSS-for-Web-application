package AST;

import java.util.ArrayList;
import java.util.List;

/**
 * HTML DOM layer (CSS sub-layer) — one CSS rule: selectors + declarations.
 */
public class CSSRuleNode extends ASTNode {

    private final List<CSSSelectorNode> selectors;
    private final List<CSSDeclarationNode> declarations;

    public CSSRuleNode(int line) {
        super(line);
        this.selectors = new ArrayList<>();
        this.declarations = new ArrayList<>();
    }

    public void addSelector(CSSSelectorNode selector) {
        selectors.add(selector);
    }

    public void addDeclaration(CSSDeclarationNode declaration) {
        declarations.add(declaration);
    }

    public List<CSSSelectorNode> getSelectors() {
        return selectors;
    }

    public List<CSSDeclarationNode> getDeclarations() {
        return declarations;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent).append("CSSRule\n");
        for (CSSSelectorNode s : selectors) {
            sb.append(s.print(indent + "  "));
        }
        for (CSSDeclarationNode d : declarations) {
            sb.append(d.print(indent + "  "));
        }
        return sb.toString();
    }
}
