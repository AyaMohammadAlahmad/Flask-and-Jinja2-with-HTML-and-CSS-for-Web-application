package server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * قارئ/كاتب JSON بسيط جداً — يكفي فقط لبنية بيانات هذا المشروع
 * (قائمة كائنات مسطحة، حقول نصية/رقمية). لا توجد مكتبة JSON خارجية
 * متاحة في هذه البيئة (لا يوجد وصول لـ Maven Central)، لذا هذا كافٍ
 * وأبسط من إضافة تبعية غير ضرورية (بند 8 من المتطلبات).
 */
public final class SimpleJson {

    private SimpleJson() {}

    // ── Writing ──────────────────────────────────────────────────────────

    public static String writeProducts(List<Map<String, Object>> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < products.size(); i++) {
            Map<String, Object> p = products.get(i);
            sb.append("  {\n");
            int j = 0;
            for (Map.Entry<String, Object> e : p.entrySet()) {
                sb.append("    \"").append(escape(e.getKey())).append("\": ");
                sb.append(writeValue(e.getValue()));
                if (++j < p.size()) sb.append(",");
                sb.append("\n");
            }
            sb.append("  }");
            if (i < products.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    private static String writeValue(Object v) {
        if (v == null) return "null";
        if (v instanceof Number) return v.toString();
        String s = String.valueOf(v);
        if (s.matches("-?\\d+(\\.\\d+)?")) return s; // رقم مخزَّن كنص
        return "\"" + escape(s) + "\"";
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }

    // ── Reading (parser بسيط لمصفوفة كائنات مسطحة فقط) ─────────────────────

    public static List<Map<String, Object>> readProducts(String json) {
        List<Map<String, Object>> result = new ArrayList<>();
        Parser p = new Parser(json);
        p.skipWs();
        if (p.peek() != '[') return result;
        p.next();
        p.skipWs();
        if (p.peek() == ']') return result;
        while (true) {
            p.skipWs();
            Map<String, Object> obj = p.parseObject();
            result.add(obj);
            p.skipWs();
            char c = p.next();
            if (c == ']') break;
            if (c != ',') throw new IllegalStateException("Malformed JSON near: " + p.context());
        }
        return result;
    }

    private static final class Parser {
        private final String s;
        private int i = 0;

        Parser(String s) { this.s = s; }

        char peek() { return i < s.length() ? s.charAt(i) : '\0'; }
        char next() { return s.charAt(i++); }
        String context() { return s.substring(Math.max(0, i - 20), Math.min(s.length(), i + 20)); }

        void skipWs() {
            while (i < s.length() && Character.isWhitespace(s.charAt(i))) i++;
        }

        Map<String, Object> parseObject() {
            Map<String, Object> map = new LinkedHashMap<>();
            skipWs();
            if (next() != '{') throw new IllegalStateException("Expected '{' near: " + context());
            skipWs();
            if (peek() == '}') { i++; return map; }
            while (true) {
                skipWs();
                String key = parseString();
                skipWs();
                if (next() != ':') throw new IllegalStateException("Expected ':' near: " + context());
                skipWs();
                Object value = parseValue();
                map.put(key, value);
                skipWs();
                char c = next();
                if (c == '}') break;
                if (c != ',') throw new IllegalStateException("Expected ',' or '}' near: " + context());
            }
            return map;
        }

        Object parseValue() {
            skipWs();
            char c = peek();
            if (c == '"') return parseString();
            if (c == '{') return parseObject();
            if (s.startsWith("true", i)) { i += 4; return "true"; }
            if (s.startsWith("false", i)) { i += 5; return "false"; }
            if (s.startsWith("null", i)) { i += 4; return ""; }
            // number
            int start = i;
            while (i < s.length() && "-+.0123456789eE".indexOf(s.charAt(i)) >= 0) i++;
            return s.substring(start, i);
        }

        String parseString() {
            skipWs();
            if (next() != '"') throw new IllegalStateException("Expected string near: " + context());
            StringBuilder sb = new StringBuilder();
            while (true) {
                char c = next();
                if (c == '"') break;
                if (c == '\\') {
                    char esc = next();
                    switch (esc) {
                        case 'n' -> sb.append('\n');
                        case 't' -> sb.append('\t');
                        case '"' -> sb.append('"');
                        case '\\' -> sb.append('\\');
                        case '/' -> sb.append('/');
                        default -> sb.append(esc);
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }
}
