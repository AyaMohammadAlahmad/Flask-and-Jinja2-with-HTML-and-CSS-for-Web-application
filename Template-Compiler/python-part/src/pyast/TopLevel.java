package pyast;

/**
 * الكلاس المجرد لأي عنصر يقع مباشرةً على مستوى البرنامج.
 * ترث منه: Import, DecoratedFunction, TopFunction, TopStatement.
 */
public abstract class TopLevel extends ASTNode {
    protected TopLevel(int line) {
        super(line);
    }
}
