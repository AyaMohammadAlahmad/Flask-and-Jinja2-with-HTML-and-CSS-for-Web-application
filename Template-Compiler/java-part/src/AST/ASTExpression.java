package AST;

/**
 * Base class for all expression nodes in the template expression layer.
 * Expressions appear inside {{ }} Jinja delimiters or as attribute values.
 */
public abstract class ASTExpression extends ASTNode {

    public ASTExpression(int line) {
        super(line);
    }
}
