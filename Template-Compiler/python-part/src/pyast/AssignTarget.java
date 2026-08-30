package pyast;

import java.util.List;

/**
 * يمثل الجانب الأيسر من الإسناد (lvalue).
 * الصور الممكنة وفق قاعدة {@code assignTarget}:
 * <ul>
 *   <li>{@code x}                  → base="x", attrs=[], subscript=null</li>
 *   <li>{@code app.config}         → base="app", attrs=["config"], subscript=null</li>
 *   <li>{@code items[0]}           → base="items", attrs=[], subscript=NumberLiteral(0)</li>
 *   <li>{@code app.config['KEY']}  → base="app", attrs=["config"], subscript=StringLiteral('KEY')</li>
 * </ul>
 */
public class AssignTarget extends ASTNode {

    private final String base;
    private final List<String> attrs;
    private final Expr subscript;   // null إذا لم يوجد subscript

    public AssignTarget(int line, String base, List<String> attrs, Expr subscript) {
        super(line);
        this.base      = base;
        this.attrs     = attrs;
        this.subscript = subscript;
    }

    public String getBase()          { return base; }
    public List<String> getAttrs()   { return attrs; }
    public Expr getSubscript()       { return subscript; }

    /** يبني التمثيل النصي الكامل للـ lvalue، e.g. {@code app.config['KEY']}. */
    public String fullTarget() {
        StringBuilder sb = new StringBuilder(base);
        for (String a : attrs) sb.append('.').append(a);
        if (subscript != null) sb.append('[').append(subscript).append(']');
        return sb.toString();
    }

    @Override
    public String toString(int indent) {
        return pad(indent) + "AssignTarget: " + fullTarget()
             + " (line " + line + ")\n";
    }
}
