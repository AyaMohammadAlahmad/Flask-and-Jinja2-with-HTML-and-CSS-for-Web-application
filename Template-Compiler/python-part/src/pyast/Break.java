package pyast;

/**
 * يمثل جملة {@code break}.
 * يقابل البديل {@code #BreakStmt} في قاعدة {@code statement}.
 *
 * <pre>
 *   BreakStmt (line 18)
 * </pre>
 */
public class Break extends Stmt {

    public Break(int line) {
        super(line);
    }

    @Override
    public String toString(int indent) {
        return pad(indent) + "BreakStmt (line " + line + ")\n";
    }
}
