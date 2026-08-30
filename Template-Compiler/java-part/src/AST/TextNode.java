package AST;

/**
 * HTML DOM layer — raw text content between HTML tags.
 */
public class TextNode extends ContentNode {

    private final String text;

    public TextNode(String text, int line) {
        super(line);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String print(String indent) {
        return indent
                + "TextNode: "
                + text
                + " (line "
                + line
                + ")\n";
    }
}
