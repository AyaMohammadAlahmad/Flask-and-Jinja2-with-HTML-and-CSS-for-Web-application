package pyast;

import java.util.List;

/**
 * يمثل جملة {@code if / elif / else}.
 * يقابل البديل {@code #If} في قاعدة {@code statement}.
 *
 * <pre>
 *   If (line 10)
 *     condition:
 *       NameExpr: x (line 10)
 *     then:
 *       BlockRule (line 11)
 *     ElifClause (line 13)
 *       condition:
 *         NameExpr: y (line 13)
 *       body:
 *         BlockRule (line 14)
 *     else:
 *       BlockRule (line 16)
 * </pre>
 */
public class If extends Stmt {

    /**
     * يمثل فقرة {@code elif condition: block} واحدة.
     */
    public static class ElifClause {
        public final Expr  condition;
        public final Block body;
        public final int   line;

        public ElifClause(int line, Expr condition, Block body) {
            this.line      = line;
            this.condition = condition;
            this.body      = body;
        }
    }

    private final Expr condition;
    private final Block thenBlock;
    private final List<ElifClause> elifClauses;
    private final Block elseBlock;   // null إذا لم يوجد else

    public If(int line, Expr condition, Block thenBlock,
              List<ElifClause> elifClauses, Block elseBlock) {
        super(line);
        this.condition   = condition;
        this.thenBlock   = thenBlock;
        this.elifClauses = elifClauses;
        this.elseBlock   = elseBlock;
    }

    public Expr getCondition()            { return condition; }
    public Block getThenBlock()           { return thenBlock; }
    public List<ElifClause> getElifClauses() { return elifClauses; }
    public Block getElseBlock()           { return elseBlock; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("If (line ").append(line).append(")\n");

        sb.append(pad(indent + 1)).append("condition:\n");
        sb.append(condition.toString(indent + 2));

        sb.append(pad(indent + 1)).append("then:\n");
        sb.append(thenBlock.toString(indent + 2));

        for (ElifClause elif : elifClauses) {
            sb.append(pad(indent + 1)).append("ElifClause (line ").append(elif.line).append(")\n");
            sb.append(pad(indent + 2)).append("condition:\n");
            sb.append(elif.condition.toString(indent + 3));
            sb.append(pad(indent + 2)).append("body:\n");
            sb.append(elif.body.toString(indent + 3));
        }

        if (elseBlock != null) {
            sb.append(pad(indent + 1)).append("else:\n");
            sb.append(elseBlock.toString(indent + 2));
        }
        return sb.toString();
    }
}
