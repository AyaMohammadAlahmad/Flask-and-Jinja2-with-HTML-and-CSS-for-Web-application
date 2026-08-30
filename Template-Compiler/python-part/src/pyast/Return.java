package pyast;

import java.util.List;

/**
 * يمثل جملة الإرجاع.
 * الأشكال الممكنة وفق قاعدة {@code returnStmt}:
 * <ul>
 *   <li>{@code return}               — values فارغة</li>
 *   <li>{@code return expr}          — قيمة واحدة</li>
 *   <li>{@code return expr, expr}    — tuple return (e.g. "Not found", 404)</li>
 * </ul>
 *
 * <pre>
 *   ReturnS (line 5)
 *     StringLiteral: 'Hello' (line 5)
 * </pre>
 */
public class Return extends Stmt {

    private final List<Expr> values;

    public Return(int line, List<Expr> values) {
        super(line);
        this.values = values;
    }

    public List<Expr> getValues() { return values; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("ReturnS (line ").append(line).append(")\n");
        for (Expr v : values) {
            sb.append(v.toString(indent + 1));
        }
        return sb.toString();
    }
}
