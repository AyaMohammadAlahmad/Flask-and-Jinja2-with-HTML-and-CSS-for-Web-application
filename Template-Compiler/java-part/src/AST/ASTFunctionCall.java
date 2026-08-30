package AST;

import java.util.ArrayList;
import java.util.List;

/**
 * Expression layer — a Jinja2 function call such as url_for('index') or
 * url_for('product', id=product.id).
 * Arguments may be positional (ASTExpression) or named (NamedArgumentNode).
 */
public class ASTFunctionCall extends ASTExpression {

    private final String functionName;
    private final List<ASTNode> arguments;

    public ASTFunctionCall(String functionName, int line) {
        super(line);
        this.functionName = functionName;
        this.arguments = new ArrayList<>();
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<ASTNode> getArguments() {
        return arguments;
    }

    public void addArgument(ASTNode arg) {
        arguments.add(arg);
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent)
                .append("FunctionCall: ")
                .append(functionName)
                .append(" (line ")
                .append(line)
                .append(")\n");
        for (ASTNode arg : arguments) {
            sb.append(arg.print(indent + "  "));
        }
        return sb.toString();
    }
}
