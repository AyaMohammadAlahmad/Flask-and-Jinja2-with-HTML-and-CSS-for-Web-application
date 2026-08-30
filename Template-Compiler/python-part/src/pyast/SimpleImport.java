package pyast;

/**
 * يمثل: {@code import os.path}
 * بديل {@code #SimpleImport} في قاعدة {@code importStmt}.
 *
 * <pre>
 *   TopImport: SimpleImport (line 2)
 *     module: os.path
 * </pre>
 */
public class SimpleImport extends TopLevel {

    private final String module;   // dottedName كامل مفصولاً بنقطة

    public SimpleImport(int line, String module) {
        super(line);
        this.module = module;
    }

    public String getModule() { return module; }

    @Override
    public String toString(int indent) {
        return pad(indent) + "TopImport: SimpleImport (line " + line + ")\n"
             + pad(indent + 1) + "module: " + module + "\n";
    }
}
