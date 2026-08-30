package pyast;

/**
 * يمثل list comprehension: {@code [expr for var in iterable if condition]}.
 * يقابل البديل {@code #ListComp} في قاعدة {@code listExpr}.
 *
 * <pre>
 *   ListComp (line 7)
 *     element:
 *       NameExpr: x (line 7)
 *     var: x
 *     iterable:
 *       NameExpr: items (line 7)
 *     filter:                      ← موجود فقط إذا كان هناك if
 *       BinaryExpr: > (line 7)
 *         ...
 * </pre>
 */
public class ListComp extends Expr {

    private final Expr element;
    private final String variable;
    private final Expr iterable;
    private final Expr filter;   // null إذا لم يوجد شرط if

    public ListComp(int line, Expr element, String variable, Expr iterable, Expr filter) {
        super(line);
        this.element  = element;
        this.variable = variable;
        this.iterable = iterable;
        this.filter   = filter;
    }

    public Expr getElement()   { return element; }
    public String getVariable(){ return variable; }
    public Expr getIterable()  { return iterable; }
    public Expr getFilter()    { return filter; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("ListComp (line ").append(line).append(")\n");
        sb.append(pad(indent + 1)).append("element:\n");
        sb.append(element.toString(indent + 2));
        sb.append(pad(indent + 1)).append("var: ").append(variable).append("\n");
        sb.append(pad(indent + 1)).append("iterable:\n");
        sb.append(iterable.toString(indent + 2));
        if (filter != null) {
            sb.append(pad(indent + 1)).append("filter:\n");
            sb.append(filter.toString(indent + 2));
        }
        return sb.toString();
    }
}
