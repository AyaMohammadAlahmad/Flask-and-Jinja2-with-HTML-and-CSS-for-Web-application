package AST;

public abstract class ASTNode {

    protected int line;

    public ASTNode(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public abstract String print(String indent);
}
