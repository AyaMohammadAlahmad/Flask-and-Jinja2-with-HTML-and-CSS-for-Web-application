package generator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * يمثل بيانات منتج واحد مستخرجة من الـ Python AST.
 *
 * <p>الـ CodeGenerator يملأ هذا الكلاس عند زيارة
 * {@code DictExpr} بداخل {@code ListExpr} المُسنَد
 * لمتغير اسمه {@code products}.</p>
 *
 * <pre>
 * Python AST Source:
 *   products = [
 *       {"id": 1, "name": "Laptop", "price": 1200, ...},
 *       ...
 *   ]
 * </pre>
 */
public class ProductData {

    private final Map<String, String> fields = new LinkedHashMap<>();

    public void set(String key, String value) {
        fields.put(key, value);
    }

    public String get(String key) {
        return fields.getOrDefault(key, "");
    }

    public Map<String, String> getFields() {
        return fields;
    }

    /** يعيد تمثيلاً نصياً لسهولة الـ debugging. */
    @Override
    public String toString() {
        return "ProductData" + fields;
    }
}
