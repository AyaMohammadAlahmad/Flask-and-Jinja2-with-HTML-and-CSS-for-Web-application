package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import generator.CodeGenerator;
import generator.GeneratorContext;
import generator.ProductData;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <b>Java Regeneration Server</b> — الجزء المطلوب صراحة في البند 6 من
 * المتطلبات ("Java هي يلي بتستمع للمتغيرات وبتعمل regeneration").
 *
 * <p><b>ملاحظة مهمة (تعارض تم توثيقه في التقرير النهائي):</b> لم يكن هناك أي
 * Java Server موجود فعلياً في المشروع المرفق رغم إشارة المتطلبات إليه كـ
 * "الموجود في المشروع" — تم فحص كامل الشجرتين (java-part و python-part) ولم
 * يُعثر على أي كود شبكة/سيرفر. لذلك تم إنشاء هذا الكلاس كجزء جديد يلتزم
 * بالمتطلب الوظيفي (الاستماع + Regeneration)، باستخدام
 * {@code com.sun.net.httpserver.HttpServer} المدمج في الـ JDK فقط — بدون أي
 * dependency خارجية إضافية (بند 8).</p>
 *
 * <h2>آلية العمل</h2>
 * <ol>
 *   <li>Flask (عبر {@code app.py} المولَّد) يستدعي {@code POST /products/add}
 *       أو {@code POST /products/delete} على هذا السيرفر عند تفاعل المستخدم.</li>
 *   <li>هذا السيرفر يحدّث مصدر الحقيقة الوحيد للبيانات: {@code output/data/products.json}.</li>
 *   <li>يُعاد استخدام {@link CodeGenerator#renderJinjaTemplates(String)} (نفس
 *       مسار Jinja Parser → Jinja AST → Generation المستخدم في التحويل
 *       الأصلي) لإعادة توليد {@code output/index.html} و
 *       {@code output/product_detail.html} فوراً من البيانات الجديدة —
 *       بدون إعادة تشغيل Python Parser (لأن البيانات لم تعد مصدرها app.py
 *       بل data/products.json بعد أول توليد، تماماً كما هو موصوف في مسار
 *       Regeneration ببند 7).</li>
 *   <li>يُضاف سطر إلى {@code compiler_output/generation_log.txt} يوضّح سبب
 *       ونتيجة الـ regeneration.</li>
 * </ol>
 */
public class RegenServer {

    private final int port;
    private final String outputDir;
    private final String templatesDir;
    private final Path dataFile;
    private final Path genLog;

    public RegenServer(int port, String outputDir, String templatesDir) {
        this.port = port;
        this.outputDir = outputDir;
        this.templatesDir = templatesDir;
        this.dataFile = Paths.get(outputDir, "data", "products.json");
        this.genLog = Paths.get("compiler_output", "generation_log.txt");
    }

    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8090;
        String outputDir = args.length > 1 ? args[1] : "output";
        String templatesDir = args.length > 2 ? args[2] : "templates";
        new RegenServer(port, outputDir, templatesDir).start();
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/products/add", this::handleAdd);
        server.createContext("/products/delete", this::handleDelete);
        server.createContext("/health", ex -> respond(ex, 200, "OK"));
        server.setExecutor(null);
        server.start();
        System.out.println("[JavaServer] Regeneration server listening on http://localhost:" + port);
        System.out.println("[JavaServer] Data file: " + dataFile.toAbsolutePath());
        appendLog("[JavaServer] Started on port " + port + ". Listening for Add/Delete events.");
    }

    // ── Handlers ─────────────────────────────────────────────────────────

    private void handleAdd(HttpExchange ex) throws IOException {
        if (!"POST".equals(ex.getRequestMethod())) { respond(ex, 405, "Method Not Allowed"); return; }
        Map<String, String> form = parseForm(ex);

        List<Map<String, Object>> products = loadProducts();
        long newId = products.stream()
                .mapToLong(p -> parseLongSafe(String.valueOf(p.getOrDefault("id", "0"))))
                .max().orElse(0) + 1;

        Map<String, Object> product = new LinkedHashMap<>();
        product.put("id", String.valueOf(newId));
        product.put("name", form.getOrDefault("name", ""));
        product.put("price", form.getOrDefault("price", "0"));
        product.put("image", form.getOrDefault("image", "/static/images/default-product.svg"));
        product.put("details", form.getOrDefault("details", ""));
        products.add(product);

        saveProducts(products);
        regenerate("ADD product id=" + newId + " name='" + product.get("name") + "'", products);
        respond(ex, 200, "Added product id=" + newId);
    }

    private void handleDelete(HttpExchange ex) throws IOException {
        if (!"POST".equals(ex.getRequestMethod())) { respond(ex, 405, "Method Not Allowed"); return; }
        Map<String, String> form = parseForm(ex);
        String id = form.getOrDefault("id", "");

        List<Map<String, Object>> products = loadProducts();
        int before = products.size();
        products = products.stream()
                .filter(p -> !String.valueOf(p.getOrDefault("id", "")).equals(id))
                .collect(Collectors.toList());
        int after = products.size();

        saveProducts(products);
        regenerate("DELETE product id=" + id + " (removed " + (before - after) + " item(s))", products);
        respond(ex, 200, "Deleted product id=" + id);
    }

    // ── Regeneration ─────────────────────────────────────────────────────

    private void regenerate(String reason, List<Map<String, Object>> products) {
        try {
            CodeGenerator generator = new CodeGenerator();
            GeneratorContext ctx = generator.getContext();
            for (Map<String, Object> p : products) {
                ProductData pd = new ProductData();
                for (Map.Entry<String, Object> e : p.entrySet()) {
                    pd.set(e.getKey(), String.valueOf(e.getValue()));
                }
                ctx.addProduct(pd);
            }
            generator.renderJinjaTemplates(templatesDir);

            Path outDir = Paths.get(outputDir);
            Files.createDirectories(outDir);
            for (Map.Entry<String, String> file : ctx.getGeneratedFiles().entrySet()) {
                Files.writeString(outDir.resolve(file.getKey()), file.getValue());
            }

            appendLog("[Regeneration] Triggered by: " + reason);
            appendLog("[Regeneration]   Products in context: " + products.size());
            appendLog("[Regeneration]   Files regenerated: " + String.join(", ", ctx.getGeneratedFiles().keySet()));
            appendLog("[Regeneration]   Status: SUCCESS");
            System.out.println("[JavaServer] Regenerated output/*.html — reason: " + reason);
        } catch (Exception e) {
            appendLog("[Regeneration] Triggered by: " + reason);
            appendLog("[Regeneration]   Status: FAILED — " + e.getMessage());
            System.out.println("[JavaServer] ERROR during regeneration: " + e.getMessage());
        }
    }

    // ── Data persistence ─────────────────────────────────────────────────

    private List<Map<String, Object>> loadProducts() {
        try {
            if (Files.exists(dataFile)) {
                return new java.util.ArrayList<>(SimpleJson.readProducts(Files.readString(dataFile)));
            }
        } catch (IOException e) {
            System.out.println("[JavaServer] WARNING: could not read data file: " + e.getMessage());
        }
        return new java.util.ArrayList<>();
    }

    private void saveProducts(List<Map<String, Object>> products) {
        try {
            Files.createDirectories(dataFile.getParent());
            Files.writeString(dataFile, SimpleJson.writeProducts(products));
        } catch (IOException e) {
            System.out.println("[JavaServer] ERROR: could not write data file: " + e.getMessage());
        }
    }

    private void appendLog(String line) {
        try {
            Files.createDirectories(genLog.getParent());
            Files.writeString(genLog, line + "\n",
                    java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException ignored) {
            // لا نعطّل السيرفر لو فشلت كتابة اللوج
        }
    }

    // ── HTTP helpers ─────────────────────────────────────────────────────

    private Map<String, String> parseForm(HttpExchange ex) throws IOException {
        String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> result = new LinkedHashMap<>();
        for (String pair : body.split("&")) {
            if (pair.isEmpty()) continue;
            String[] kv = pair.split("=", 2);
            String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String value = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "";
            result.put(key, value);
        }
        return result;
    }

    private void respond(HttpExchange ex, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static long parseLongSafe(String s) {
        try { return Long.parseLong(s.trim()); } catch (Exception e) { return 0; }
    }
}
