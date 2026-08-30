package pyast;

/**
 * يمثل تعبيراً بين قوسين: {@code (expr)}.
 * يقابل البديل {@code #ParenExpr} في قاعدة {@code expr}.
 * يُحتفظ به في الشجرة لتتبع السياق الأصلي بدقة.
 *
 * <pre>
 *   ParenExpr (line 4)
 *     BinaryExpr: + (line 4)
 *       ...
 * </pre>
 */
public class ParenExpr extends Expr {

    private final Expr inner;

    public ParenExpr(int line, Expr inner) {
        super(line);
        this.inner = inner;
    }

    public Expr getInner() { return inner; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("ParenExpr (line ").append(line).append(")\n");
        sb.append(inner.toString(indent + 1));
        return sb.toString();
    }
}
