package pyast;

/**
 * يمثل وسيطة واحدة في استدعاء دالة أو decorator.
 *
 * <ul>
 *   <li>وسيطة موضعية (PositionalArg): {@code keyword == null}</li>
 *   <li>وسيطة مفتاحية (KeywordArg):   {@code keyword != null}</li>
 * </ul>
 *
 * <pre>
 *   PositionalArg (line 3)
 *     StringLiteral: '/' (line 3)
 *
 *   KeywordArg: methods (line 3)
 *     ListExpr (line 3)
 * </pre>
 */
public class Argument extends ASTNode {

    private final String keyword;   // null لو كانت positional
    private final Expr value;

    /** Positional argument */
    public Argument(int line, Expr value) {
        super(line);
        this.keyword = null;
        this.value   = value;
    }

    /** Keyword argument */
    public Argument(int line, String keyword, Expr value) {
        super(line);
        this.keyword = keyword;
        this.value   = value;
    }

    public boolean isKeyword()  { return keyword != null; }
    public String getKeyword()  { return keyword; }
    public Expr getValue()      { return value; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        if (isKeyword()) {
            sb.append(pad(indent)).append("KeywordArg: ").append(keyword)
              .append(" (line ").append(line).append(")\n");
        } else {
            sb.append(pad(indent)).append("PositionalArg (line ").append(line).append(")\n");
        }
        sb.append(value.toString(indent + 1));
        return sb.toString();
    }
}
