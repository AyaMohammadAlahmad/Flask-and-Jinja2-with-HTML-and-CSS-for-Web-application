package AST;

import java.util.ArrayList;
import java.util.List;

/**
 * Template layer — a Jinja2 {% block name %} ... {% endblock %} section.
 *
 * ASTBlock is a ContentNode (not an HtmlElementNode child), so it lives
 * at the template layer alongside HTML elements in ASTTemplate.contents
 * (or alongside HTML elements inside an enclosing block, when nested).
 *
 * Top-level content that appears as a sibling of the block in the source
 * template (i.e. between {% block %} and {% endblock %} at the template's
 * own content* level) is stored as a child of this block. HTML elements
 * that are themselves nested inside other HTML elements (e.g. a <tr> inside
 * a <table>) keep their normal nesting — they are children of their actual
 * containing element, not flattened into the block. The block only acts as
 * the parent for the top-level content directly between its open/close tags.
 */
public class ASTBlock extends ContentNode {

    private final String blockName;
    private final List<ContentNode> contents;

    public ASTBlock(String blockName, int line) {
        super(line);
        this.blockName = blockName;
        this.contents = new ArrayList<>();
    }

    public String getBlockName() {
        return blockName;
    }

    public List<ContentNode> getContents() {
        return contents;
    }

    public void addContent(ContentNode node) {
        contents.add(node);
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("ASTBlock: ")
                .append(blockName)
                .append(" (line ")
                .append(line)
                .append(")\n");
        for (ContentNode child : contents) {
            sb.append(child.print(indent + "  "));
        }
        return sb.toString();
    }
}
