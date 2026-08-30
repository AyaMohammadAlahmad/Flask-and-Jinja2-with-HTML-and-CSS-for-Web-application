package AST;

/**
 * Template layer — base for all Jinja2 control statements delimited by {% %}.
 * Concrete subclasses represent specific statement types (extends, for, etc.).
 */
public abstract class ASTJinjaStatement extends ContentNode {

    private final String statementType;

    public ASTJinjaStatement(String statementType, int line) {
        super(line);
        this.statementType = statementType;
    }

    public String getStatementType() {
        return statementType;
    }
}
