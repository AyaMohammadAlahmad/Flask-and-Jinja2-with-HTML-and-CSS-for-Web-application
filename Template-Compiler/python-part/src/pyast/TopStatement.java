package pyast;

/**
 * يلف أي {@link Stmt} ليقع على مستوى البرنامج.
 * بديل {@code #TopStatement} في قاعدة {@code topLevel}.
 *
 * <pre>
 *   TopStatement (line 20)
 *     AssignNode (line 20)
 *       ...
 * </pre>
 */
public class TopStatement extends TopLevel {

    private final Stmt statement;

    public TopStatement(int line, Stmt statement) {
        super(line);
        this.statement = statement;
    }

    public Stmt getStatement() { return statement; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("TopStatement (line ").append(line).append(")\n");
        sb.append(statement.toString(indent + 1));
        return sb.toString();
    }
}
