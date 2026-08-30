package pyast;

import java.util.List;

/**
 * يمثل كتلة (block) من الجمل المتتالية.
 * يقابل البديل {@code #BlockRule} في قاعدة {@code block}.
 *
 * <pre>
 *   BlockRule (line 5)
 *     ReturnS (line 5)
 *       ...
 * </pre>
 */
public class Block extends ASTNode {

    private final List<Stmt> statements;

    public Block(int line, List<Stmt> statements) {
        super(line);
        this.statements = statements;
    }

    public List<Stmt> getStatements() { return statements; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("BlockRule (line ").append(line).append(")\n");
        for (Stmt stmt : statements) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb.toString();
    }
}
