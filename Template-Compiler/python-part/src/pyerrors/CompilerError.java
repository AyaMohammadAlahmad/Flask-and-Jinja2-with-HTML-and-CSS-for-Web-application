package pyerrors;

/**
 * تمثيل موحّد لأي خطأ يصدر عن مرحلة Python بالمترجم — سواء كان
 * Lexical (رمز غير معروف)، Syntax (بنية غير صحيحة)، أو Semantic (خطأ دلالي).
 *
 * <p>الهدف من هالكلاس هو توحيد شكل الخطأ بدل ما كل مرحلة تطبع نص حر
 * بصيغتها الخاصة — حتى نقدر نجمعهم كلهم بقائمة وحدة ونكتبهم بتقرير
 * {@code semantic_report.txt} بشكل منظم ومصنّف.</p>
 *
 * <p>هاد الكلاس جديد بالكامل ولا يعدّل أو يمس أي كلاس موجود مسبقاً
 * (لا AST، لا SymbolTable، لا Lexer/Parser).</p>
 */
public final class CompilerError {

    private final ErrorType type;
    private final int line;
    private final int charPositionInLine; // -1 إذا غير معروف (مثلاً أخطاء دلالية)
    private final String message;

    public CompilerError(ErrorType type, int line, int charPositionInLine, String message) {
        this.type = type;
        this.line = line;
        this.charPositionInLine = charPositionInLine;
        this.message = message;
    }

    /** بناء مختصر لخطأ دلالي (ما إله عمود محدد ضمن السطر). */
    public static CompilerError semantic(int line, String message) {
        return new CompilerError(ErrorType.SEMANTIC, line, -1, message);
    }

    public ErrorType getType() { return type; }
    public int getLine() { return line; }
    public int getCharPositionInLine() { return charPositionInLine; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        String pos = (charPositionInLine >= 0)
                ? "Line " + line + ":" + charPositionInLine
                : "Line " + line;
        return "[" + type + "] " + pos + " -> " + message;
    }
}
