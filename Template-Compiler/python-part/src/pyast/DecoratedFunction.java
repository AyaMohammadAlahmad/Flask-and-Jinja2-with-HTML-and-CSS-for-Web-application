package pyast;

import java.util.List;

/**
 * يمثل دالة مزيّنة بواحد أو أكثر من الـ decorators.
 * بديل {@code #TopDecoratedFunction} في قاعدة {@code topLevel}.
 *
 * <pre>
 *   TopDecoratedFunction (line 3)
 *     DecoratorNode: @app.route (line 3)
 *     FunctionDefNode: index (line 4)
 *       ...
 * </pre>
 */
public class DecoratedFunction extends TopLevel {

    private final List<Decorator> decorators;
    private final FunctionDef function;

    public DecoratedFunction(int line, List<Decorator> decorators, FunctionDef function) {
        super(line);
        this.decorators = decorators;
        this.function   = function;
    }

    public List<Decorator> getDecorators() { return decorators; }
    public FunctionDef getFunction()        { return function; }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(pad(indent)).append("TopDecoratedFunction (line ").append(line).append(")\n");
        for (Decorator dec : decorators) {
            sb.append(dec.toString(indent + 1));
        }
        sb.append(function.toString(indent + 1));
        return sb.toString();
    }
}
