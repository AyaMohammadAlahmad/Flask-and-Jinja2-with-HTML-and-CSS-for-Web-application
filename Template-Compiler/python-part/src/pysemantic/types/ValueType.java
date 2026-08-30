package pysemantic.types;

/**
 * أنواع القيم الممكنة في اللغة خلال مرحلة التحليل الدلالي.
 *
 * <p>{@code UNKNOWN} هو النوع الافتراضي لأي تعبير لا يمكن تحديد نوعه
 * وقت الترجمة (مثل نتيجة استدعاء دالة، أو متغير جاء من خارج النطاق).
 * عند وجود {@code UNKNOWN} في أي طرف من عملية، لا يُطلق أي خطأ
 * لتجنب الـ false positives.</p>
 */
public enum ValueType {

    STRING,   // "hello", 'world'
    NUMBER,   // 42, 3.14, 1e3
    BOOL,     // True, False
    NONE,     // None
    LIST,     // [1, 2, 3]
    DICT,     // {"key": val}
    UNKNOWN   // لا يمكن تحديد النوع وقت الترجمة
}
