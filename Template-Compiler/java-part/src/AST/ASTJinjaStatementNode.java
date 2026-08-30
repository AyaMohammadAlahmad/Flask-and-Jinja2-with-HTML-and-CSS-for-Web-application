package AST;

/**
 * Template layer — a concrete Jinja2 statement for statement types that do
 * not require dedicated subclasses (e.g. {% extends "base.html" %},
 * {% endfor %}).
 *
 * The statementType field (inherited) holds the leading keyword,
 * and content holds the remainder of the statement body.
 */
public class ASTJinjaStatementNode extends ASTJinjaStatement {

    /** Full statement body text, e.g. 'extends "base.html"'. */
    private final String content;

    public ASTJinjaStatementNode(String statementType, String content, int line) {
        super(statementType, line);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String print(String indent) {
        return indent
                + "JinjaStatement["
                + getStatementType()
                + "]: "
                + content
                + " (line "
                + line
                + ")\n";
    }
}
