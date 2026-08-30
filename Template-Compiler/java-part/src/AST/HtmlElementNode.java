package AST;

import java.util.ArrayList;
import java.util.List;

/**
 * HTML DOM layer — represents one HTML element such as <div>, <p>, or <img>.
 *
 * Children are ContentNode instances (other elements, text, or template
 * constructs). Jinja blocks are NOT stored as children here; they live
 * in the template layer (ASTBlock / TemplateNode) and are added to
 * ASTTemplate.contents, not inside an HtmlElementNode child list.
 */
public class HtmlElementNode extends ContentNode {

    private final String tagName;
    private final boolean selfClosing;
    private final List<AttributeNode> attributes;
    private final List<ContentNode> children;

    public HtmlElementNode(String tagName, boolean selfClosing, int line) {
        super(line);
        this.tagName = tagName;
        this.selfClosing = selfClosing;
        this.attributes = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public String getTagName() {
        return tagName;
    }

    public boolean isSelfClosing() {
        return selfClosing;
    }

    public List<AttributeNode> getAttributes() {
        return attributes;
    }

    public List<ContentNode> getChildren() {
        return children;
    }

    public void addAttribute(AttributeNode attr) {
        attributes.add(attr);
    }

    public void addChild(ContentNode node) {
        children.add(node);
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("HtmlElementNode: <")
                .append(tagName)
                .append(selfClosing ? " />" : ">")
                .append(" (line ")
                .append(line)
                .append(")\n");
        for (AttributeNode attr : attributes) {
            sb.append(attr.print(indent + "  "));
        }
        for (ContentNode child : children) {
            sb.append(child.print(indent + "  "));
        }
        return sb.toString();
    }
}
