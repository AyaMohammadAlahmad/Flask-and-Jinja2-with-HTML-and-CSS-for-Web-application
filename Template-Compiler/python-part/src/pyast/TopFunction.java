package pyast;

/**
 * يمثل دالة على مستوى البرنامج بدون decorator.
 * بديل {@code #TopFunction} في قاعدة {@code topLevel}.
 *
 * <pre>
 *   TopFunction (line 10)
 *     FunctionDefNode: helper (line 10)
 *       ...
 * </pre>
 */
public class TopFunction extends TopLevel {

    private final FunctionDef function;

    public TopFunction(int line, FunctionDef function) {
        super(line);
        this.function = function;
    }

    public FunctionDef getFunction() { return function; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("TopFunction (line ").append(line).append(")\n");
        sb.append(function.toString(indent + 1));
        return sb.toString();
    }
}
