package pyerrors;

/**
 * تصنيف نوع الخطأ بمرحلة Python:
 * <ul>
 *   <li>{@code LEXICAL}  — رمز/حرف غير معروف بمرحلة الـ Lexer (token recognition error)</li>
 *   <li>{@code SYNTAX}   — بنية غير متوافقة مع الـ Grammar بمرحلة الـ Parser</li>
 *   <li>{@code SEMANTIC} — خطأ دلالي (يصدر من pysemantic.checks عبر SemanticAnalyzer)</li>
 * </ul>
 */
public enum ErrorType {
    LEXICAL,
    SYNTAX,
    SEMANTIC
}
