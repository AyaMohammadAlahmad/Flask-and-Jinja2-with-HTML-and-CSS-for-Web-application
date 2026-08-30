package pyast;

import java.util.List;

/**
 * يمثل قائمة بسيطة: {@code [1, 2, 3]}.
 * يقابل البديل {@code #PlainList} في قاعدة {@code listExpr}.
 *
 * <pre>
 *   ListExpr (line 6)
 *     NumberLiteral: 1 (line 6)
 *     NumberLiteral: 2 (line 6)
 *     NumberLiteral: 3 (line 6)
 * </pre>
 */
public class ListExpr extends Expr {

    private final List<Expr> elements;

    public ListExpr(int line, List<Expr> elements) {
        super(line);
        this.elements = elements;
    }

    public List<Expr> getElements() { return elements; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("ListExpr (line ").append(line).append(")\n");
        for (Expr el : elements) {
            sb.append(el.toString(indent + 1));
        }
        return sb.toString();
    }
}
