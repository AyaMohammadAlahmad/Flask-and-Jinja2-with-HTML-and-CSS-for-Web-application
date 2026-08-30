package AST;

import java.util.ArrayList;
import java.util.List;

/**
 * Template layer — root node of the AST for one template file.
 *
 * Direct children are ContentNode instances: HTML elements, text,
 * Jinja expressions, Jinja statements, and Jinja blocks.
 * CSS style elements (ASTStyle) are also stored here.
 */
public class ASTTemplate extends ASTNode {

    private final List<ContentNode> contents;

    public ASTTemplate(int line) {
        super(line);
        this.contents = new ArrayList<>();
    }

    public void addContent(ContentNode node) {
        contents.add(node);
    }

    public List<ContentNode> getContents() {
        return contents;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("ASTTemplate (line ")
                .append(line)
                .append(")\n");
        for (ContentNode child : contents) {
            sb.append(child.print(indent + "  "));
        }
        return sb.toString();
    }
}
