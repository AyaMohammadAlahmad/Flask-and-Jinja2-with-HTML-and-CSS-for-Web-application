package AST;

/**
 * Base class for any node that can appear as content inside an HTML element
 * or at the top level of a template. Separates DOM/template content from
 * pure expression nodes.
 */
public abstract class ContentNode extends ASTNode {

    public ContentNode(int line) {
        super(line);
    }
}
