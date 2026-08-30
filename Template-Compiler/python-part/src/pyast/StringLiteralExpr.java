package pyast;

public class StringLiteralExpr extends Expression{
    private final String value;
    public StringLiteralExpr(int line, String value){
        super(line);
        this.value=value;
    }
    public String getValue(){return value;}

    @Override
    public String toString(int indent) {
        return pad(indent) + "StringLiteral: " + value + " (line " + line + ")\n";    }
}
