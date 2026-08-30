package render;

import AST.*;

import java.util.List;
import java.util.Map;

/**
 * محرك Rendering حقيقي لمرحلة Jinja Processing.
 *
 * <p>هذا الكلاس هو الحلقة الناقصة التي كانت موجودة في المشروع الأصلي:
 * الـ Jinja Parser/AST (antlar.htmlParser + Visitor.ASTVisitor) كانا موجودين
 * فقط لأجل بناء الشجرة والتحقق الدلالي (عبر HtmlRunner)، لكن لم يكن هناك أي
 * مسار يمشي على تلك الشجرة وينفّذ فعلياً variable substitution أو تكرار
 * {% for %} باستخدام Context Data قادمة من Python AST.</p>
 *
 * <p>هذا الكلاس يزور {@link ASTTemplate} (نفس الشجرة التي يبنيها
 * {@code Visitor.ASTVisitor} ويتحقق منها {@code Semantic.SemanticAnalyzer})
 * وينتج نص HTML نهائي بديلاً عن أي HTML ثابت.</p>
 *
 * <h2>كيف يتم التعامل مع {@code {% for %} ... {% endfor %}}</h2>
 * الـ grammar الحالي (antlar/htmlParser.g4) يمثّل for/endfor كعقدتين مسطحتين
 * (ASTJinjaStatementNode) ضمن نفس قائمة الأبناء (نفس النمط المستخدم فعلياً في
 * {@code Semantic.SemanticAnalyzer} لقاعدة UNUSED_LOOP_VARIABLE — انظر التعليق
 * هناك). هذا الكلاس يعيد استخدام نفس الفكرة: يبحث عن أول {@code endfor} يلي
 * كل {@code for} ضمن نفس القائمة (بمراعاة التداخل)، ويعتبر كل ما بينهما هو
 * جسم الحلقة، ثم يكرره لكل عنصر في القائمة القادمة من الـ Context Data.
 */
public final class JinjaRenderer {

    private JinjaRenderer() {}

    /** يرندر شجرة قالب كاملة مقابل بيانات Context معيّنة. */
    public static String render(ASTTemplate template, Map<String, Object> context) {
        StringBuilder out = new StringBuilder();
        renderContents(template.getContents(), context, out);
        return out.toString();
    }

    // ── جسم أي قائمة محتوى (template root / block / html element) ─────────

    private static void renderContents(List<ContentNode> contents, Map<String, Object> ctx, StringBuilder out) {
        int i = 0;
        while (i < contents.size()) {
            ContentNode node = contents.get(i);

            if (isForStart(node)) {
                int endIdx = findMatchingEndfor(contents, i);
                renderForLoop((ASTJinjaStatementNode) node, contents.subList(i + 1, endIdx), ctx, out);
                i = endIdx + 1; // تخطي {% endfor %} نفسها
                continue;
            }

            if (isEndfor(node)) {
                // endfor يتيمة (بدون for مطابق) — تجاهلها دفاعياً
                i++;
                continue;
            }

            renderNode(node, ctx, out);
            i++;
        }
    }

    private static boolean isForStart(ContentNode node) {
        return node instanceof ASTJinjaStatementNode s && "for".equals(s.getStatementType());
    }

    private static boolean isEndfor(ContentNode node) {
        return node instanceof ASTJinjaStatementNode s && "endfor".equals(s.getStatementType());
    }

    /** يبحث عن اندكس {@code endfor} المطابقة لـ {@code for} عند forIndex، بمراعاة التداخل. */
    private static int findMatchingEndfor(List<ContentNode> contents, int forIndex) {
        int depth = 0;
        for (int j = forIndex; j < contents.size(); j++) {
            ContentNode n = contents.get(j);
            if (isForStart(n)) depth++;
            else if (isEndfor(n)) {
                depth--;
                if (depth == 0) return j;
            }
        }
        throw new IllegalStateException(
                "Unmatched {% for %} without {% endfor %} at line " + contents.get(forIndex).getLine());
    }

    @SuppressWarnings("unchecked")
    private static void renderForLoop(ASTJinjaStatementNode forStmt, List<ContentNode> body,
                                       Map<String, Object> ctx, StringBuilder out) {
        // content format: "iterVar in iterableVar" (نفس الصيغة التي يبنيها ASTVisitor.visitForStatement)
        String[] parts = forStmt.getContent().trim().split("\\s+in\\s+", 2);
        if (parts.length != 2) return;

        String iterVar = parts[0].trim();
        String iterableName = parts[1].trim();

        Object iterableRaw = resolveDotted(iterableName, ctx);
        if (!(iterableRaw instanceof List<?> list)) {
            // لا يوجد بيانات لهذه القائمة — نعتبرها فارغة، لا نطبع شيئاً (سلوك آمن)
            return;
        }

        for (Object item : list) {
            Map<String, Object> loopCtx = new java.util.HashMap<>(ctx);
            loopCtx.put(iterVar, item);
            renderContents(body, loopCtx, out);
        }
    }

    // ── عقدة مفردة ───────────────────────────────────────────────────────

    private static void renderNode(ContentNode node, Map<String, Object> ctx, StringBuilder out) {
        if (node instanceof DoctypeNode) {
            out.append("<!doctype html>");
        } else if (node instanceof TextNode text) {
            out.append(text.getText());
        } else if (node instanceof ASTJinjaExpression expr) {
            out.append(escapeHtml(evalToString(expr.getExpression(), ctx)));
        } else if (node instanceof ASTBlock block) {
            // لا يوجد template inheritance حقيقي في هذا المولّد (الشجرة لا تحوي extends
            // فعلي)؛ نعامل محتوى الـ block كمرور مباشر (pass-through) حفاظاً على الترتيب.
            renderContents(block.getContents(), ctx, out);
        } else if (node instanceof HtmlElementNode el) {
            renderElement(el, ctx, out);
        } else if (node instanceof ASTJinjaStatementNode stmt) {
            // extends أو أي statement آخر بدون تأثير على النص النهائي
            if (!"extends".equals(stmt.getStatementType())) {
                // نوع غير مدعوم بالتوليد — تجاهل آمن
            }
        }
    }

    private static void renderElement(HtmlElementNode el, Map<String, Object> ctx, StringBuilder out) {
        out.append('<').append(el.getTagName());
        for (AttributeNode attr : el.getAttributes()) {
            out.append(' ').append(attr.getName());
            ASTExpression value = attr.getValue();
            if (value != null) {
                out.append("=\"").append(escapeAttr(evalAttribute(value, ctx))).append('"');
            }
        }
        boolean voidTag = isVoidTag(el.getTagName());
        if (el.isSelfClosing() || voidTag) {
            out.append('>');
            return;
        }
        out.append('>');
        renderContents(el.getChildren(), ctx, out);
        out.append("</").append(el.getTagName()).append('>');
    }

    private static boolean isVoidTag(String tag) {
        return switch (tag) {
            case "img", "br", "hr", "meta", "link", "input" -> true;
            default -> false;
        };
    }

    // ── تقييم التعابير (Expression evaluation) ──────────────────────────

    private static String evalAttribute(ASTExpression value, Map<String, Object> ctx) {
        if (value instanceof StringLiteralNode s) {
            return s.getValue();
        }
        if (value instanceof AttributeJinjaExpressionNode j) {
            return evalToString(j.getExpression().getExpression(), ctx);
        }
        return "";
    }

    private static String evalToString(ASTExpression expr, Map<String, Object> ctx) {
        Object val = eval(expr, ctx);
        return val == null ? "" : String.valueOf(val);
    }

    private static Object eval(ASTExpression expr, Map<String, Object> ctx) {
        if (expr instanceof ASTVariable v) {
            return resolveDotted(v.getFullName(), ctx);
        }
        if (expr instanceof StringLiteralNode s) {
            String raw = s.getValue();
            if (raw.length() >= 2 && (raw.startsWith("\"") || raw.startsWith("'"))) {
                return raw.substring(1, raw.length() - 1);
            }
            return raw;
        }
        if (expr instanceof ASTFunctionCall fc) {
            return evalFunctionCall(fc, ctx);
        }
        return null;
    }

    // ── دوال Jinja/Flask مدعومة (url_for) ────────────────────────────────

    /**
     * جدول الـ routes الثابت المطابق لـ {@code app.py} المولَّد (نفس الأسماء
     * والمسارات المعرّفة بـ {@code @app.route(...)}). يُستخدم لتحويل
     * {@code url_for('endpoint', ...)} إلى مسار حقيقي بدل نص فاضٍ.
     */
    private static final Map<String, String> ROUTES = Map.of(
            "index",           "/",
            "show_products",   "/products",
            "add_product",     "/products/add",
            "product_detail",  "/products/{product_id}",
            "delete_product",  "/products/{product_id}/delete"
    );

    private static Object evalFunctionCall(ASTFunctionCall fc, Map<String, Object> ctx) {
        if ("url_for".equals(fc.getFunctionName())) {
            return evalUrlFor(fc, ctx);
        }
        // أي دالة أخرى غير مدعومة حالياً — نفس السلوك الآمن السابق (تجاهل بدل فشل).
        return "";
    }

    /**
     * يقيّم {@code url_for('endpoint', key=value, ...)} إلى مسار حقيقي:
     * <ul>
     *   <li>{@code url_for('static', filename='style.css')} → {@code /static/style.css}</li>
     *   <li>{@code url_for('product_detail', product_id=product.id)} → {@code /products/3}</li>
     *   <li>غير ذلك → يُستبدل كل {@code {param}} في قالب المسار من {@link #ROUTES}
     *       بالقيمة المقابلة من الوسائط المسمّاة (named arguments).</li>
     * </ul>
     */
    private static Object evalUrlFor(ASTFunctionCall fc, Map<String, Object> ctx) {
        List<ASTNode> args = fc.getArguments();
        if (args.isEmpty()) return "";

        String endpoint = null;
        Map<String, String> params = new java.util.LinkedHashMap<>();

        for (int i = 0; i < args.size(); i++) {
            ASTNode arg = args.get(i);
            if (arg instanceof NamedArgumentNode named) {
                params.put(named.getName(), evalToString(named.getValue(), ctx));
            } else if (i == 0 && arg instanceof ASTExpression exprArg) {
                // الوسيط الأول الموضعي هو اسم الـ endpoint
                Object val = eval(exprArg, ctx);
                endpoint = (val == null) ? null : String.valueOf(val);
            }
        }

        if (endpoint == null) return "";

        if ("static".equals(endpoint)) {
            return "/static/" + params.getOrDefault("filename", "");
        }

        String routeTemplate = ROUTES.get(endpoint);
        if (routeTemplate == null) return "";

        String url = routeTemplate;
        for (Map.Entry<String, String> e : params.entrySet()) {
            url = url.replace("{" + e.getKey() + "}", e.getValue());
        }
        return url;
    }

    @SuppressWarnings("unchecked")
    private static Object resolveDotted(String dottedName, Map<String, Object> ctx) {
        String[] parts = dottedName.split("\\.");
        Object current = ctx.get(parts[0]);
        for (int i = 1; i < parts.length && current != null; i++) {
            if (current instanceof Map<?, ?> map) {
                current = ((Map<String, Object>) map).get(parts[i]);
            } else {
                return null;
            }
        }
        return current;
    }

    // ── HTML escaping بسيط ───────────────────────────────────────────────

    private static String escapeHtml(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private static String escapeAttr(String s) {
        return escapeHtml(s).replace("\"", "&quot;");
    }
}
