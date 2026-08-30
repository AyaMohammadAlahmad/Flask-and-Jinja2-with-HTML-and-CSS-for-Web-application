package pyast;

import java.util.List;

/**
 * جذر الشجرة — يقابل قاعدة {@code program} في الـ grammar.
 *
 * <pre>
 * ProgramNode (line 1)
 *   TopImport: FromImport (line 1)
 *   TopDecoratedFunction (line 3)
 *     ...
 * </pre>
 */
public class Program extends ASTNode {

    private final List<TopLevel> statements;

    public Program(int line, List<TopLevel> statements) {
        super(line);
        this.statements = statements;
    }

    public List<TopLevel> getStatements() {
        return statements;
    }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("ProgramNode (line ").append(line).append(")\n");
        for (TopLevel stmt : statements) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb.toString();
    }
}
