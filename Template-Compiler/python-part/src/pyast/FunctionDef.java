package pyast;

import java.util.List;

/**
 * يمثل تعريف دالة: {@code def index(product_id):}
 *
 * <pre>
 *   FunctionDefNode: index (line 4)
 *     params: product_id
 *     BlockRule (line 5)
 *       ...
 * </pre>
 */
public class FunctionDef extends ASTNode {

    private final String name;
    private final List<String> params;
    private final Block body;

    public FunctionDef(int line, String name, List<String> params, Block body) {
        super(line);
        this.name   = name;
        this.params = params;
        this.body   = body;
    }

    public String getName()         { return name; }
    public List<String> getParams() { return params; }
    public Block getBody()          { return body; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("FunctionDefNode: ").append(name)
          .append(" (line ").append(line).append(")\n");
        if (!params.isEmpty()) {
            sb.append(pad(indent + 1)).append("params: ")
              .append(String.join(", ", params)).append("\n");
        }
        sb.append(body.toString(indent + 1));
        return sb.toString();
    }
}
