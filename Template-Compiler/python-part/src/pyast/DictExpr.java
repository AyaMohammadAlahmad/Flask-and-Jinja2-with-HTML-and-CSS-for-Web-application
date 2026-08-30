package pyast;

import java.util.List;

/**
 * يمثل قاموساً: {@code {"name": x, "age": 30}}.
 * يقابل قاعدة {@code dictExpr}.
 *
 * <pre>
 *   DictExpr (line 8)
 *     DictItem (line 8)
 *       key:
 *         StringLiteral: 'name' (line 8)
 *       value:
 *         NameExpr: x (line 8)
 * </pre>
 */
public class DictExpr extends Expr {

    /**
     * يمثل زوج key:value واحداً في القاموس.
     * يقابل قاعدة {@code dictItem}.
     */
    public static class DictItem extends ASTNode {
        private final Expr key;
        private final Expr value;

        public DictItem(int line, Expr key, Expr value) {
            super(line);
            this.key   = key;
            this.value = value;
        }

        public Expr getKey()   { return key; }
        public Expr getValue() { return value; }

        @Override
        public String toString(int indent) {
            StringBuilder sb = new StringBuilder();
            sb.append(pad(indent)).append("DictItem (line ").append(line).append(")\n");
            sb.append(pad(indent + 1)).append("key:\n");
            sb.append(key.toString(indent + 2));
            sb.append(pad(indent + 1)).append("value:\n");
            sb.append(value.toString(indent + 2));
            return sb.toString();
        }
    }

    private final List<DictItem> items;

    public DictExpr(int line, List<DictItem> items) {
        super(line);
        this.items = items;
    }

    public List<DictItem> getItems() { return items; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("DictExpr (line ").append(line).append(")\n");
        for (DictItem item : items) {
            sb.append(item.toString(indent + 1));
        }
        return sb.toString();
    }
}
