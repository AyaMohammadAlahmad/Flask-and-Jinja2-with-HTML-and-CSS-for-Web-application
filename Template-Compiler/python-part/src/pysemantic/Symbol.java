package pysemantic;

/**
 * يمثل رمزاً واحداً في جدول الرموز.
 *
 * <p>كل رمز يخزن:</p>
 * <ul>
 *   <li>{@code name}  — الاسم كما ورد في الكود</li>
 *   <li>{@code type}  — نوع الرمز (VARIABLE / FUNCTION / IMPORT)</li>
 *   <li>{@code line}  — رقم السطر لتعقب الأخطاء لاحقاً</li>
 * </ul>
 */
public class Symbol {

    private final String     name;
    private final SymbolType type;
    private final int        line;

    public Symbol(String name, SymbolType type, int line) {
        this.name = name;
        this.type = type;
        this.line = line;
    }

    public String     getName() { return name; }
    public SymbolType getType() { return type; }
    public int        getLine() { return line; }

    @Override
    public String toString() {
        return "Symbol{name='" + name + "', type=" + type + ", line=" + line + "}";
    }
}
