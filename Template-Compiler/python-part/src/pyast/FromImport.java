package pyast;

import java.util.List;

/**
 * يمثل: {@code from flask import Flask, render_template}
 * بديل {@code #FromImport} في قاعدة {@code importStmt}.
 *
 * <pre>
 *   TopImport: FromImport (line 1)
 *     module : flask
 *     names  : Flask, render_template
 * </pre>
 */
public class FromImport extends TopLevel {

    private final String module;
    private final List<String> names;

    public FromImport(int line, String module, List<String> names) {
        super(line);
        this.module = module;
        this.names  = names;
    }

    public String getModule()      { return module; }
    public List<String> getNames() { return names; }

    @Override
    public String toString(int indent) {
        return pad(indent) + "TopImport: FromImport (line " + line + ")\n"
             + pad(indent + 1) + "module : " + module + "\n"
             + pad(indent + 1) + "names  : " + String.join(", ", names) + "\n";
    }
}
