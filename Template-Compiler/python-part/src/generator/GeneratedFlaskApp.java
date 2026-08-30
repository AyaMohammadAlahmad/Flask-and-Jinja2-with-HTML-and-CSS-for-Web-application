package generator;

import java.util.List;

/**
 * يولّد ملف {@code app.py} كامل لتطبيق Flask يتضمن 4 مسارات:
 * <ul>
 *   <li>GET  {@code /}                          → redirect لصفحة المنتجات</li>
 *   <li>GET  {@code /products}                  → عرض كل المنتجات</li>
 *   <li>GET|POST {@code /products/add}          → إضافة منتج</li>
 *   <li>GET  {@code /products/<int:product_id>} → تفاصيل منتج</li>
 *   <li>POST {@code /products/<int:product_id>/delete} → حذف منتج</li>
 * </ul>
 *
 * <p>البيانات الأولية (initial products) تُحقن من {@link GeneratorContext}
 * المُستخرجة من الـ Python AST.</p>
 */
public class GeneratedFlaskApp {

    /**
     * يولّد محتوى ملف {@code app.py} كاملاً بناءً على البيانات المستخرجة.
     *
     * @param ctx  السياق يحتوي المنتجات المستخرجة من Python AST
     * @return نص {@code app.py} جاهز للكتابة على الملف
     */
    public static String generate(GeneratorContext ctx) {
        return generate(ctx, 8090);
    }

    /**
     * @param ctx            السياق يحتوي المنتجات المستخرجة من Python AST
     * @param javaServerPort المنفذ الذي يستمع عليه Java Regeneration Server
     */
    public static String generate(GeneratorContext ctx, int javaServerPort) {
        StringBuilder sb = new StringBuilder();

        // ── Imports ───────────────────────────────────────────────────────
        sb.append("import json\n");
        sb.append("import os\n");
        sb.append("import urllib.request\n");
        sb.append("import urllib.parse\n");
        sb.append("from flask import Flask, render_template, request, redirect, url_for, flash\n\n");
        sb.append("app = Flask(__name__)\n");
        sb.append("app.secret_key = 'generated-secret-key-2025'\n\n");
        sb.append("JAVA_SERVER = \"http://localhost:").append(javaServerPort).append("\"\n");
        sb.append("DATA_FILE = os.path.join(os.path.dirname(__file__), \"data\", \"products.json\")\n\n");

        // ── Initial Products Data (injected from Python AST, seeded once) ──
        sb.append("# ── Products data (Context Data المستخرجة أصلاً من Python AST) ──\n");
        sb.append("_INITIAL_PRODUCTS = [\n");

        List<ProductData> products = ctx.getProducts();
        for (int i = 0; i < products.size(); i++) {
            ProductData p = products.get(i);
            sb.append("    {\n");
            for (var entry : p.getFields().entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                boolean isNumeric = val.matches("-?\\d+(\\.\\d+)?");
                if (isNumeric) {
                    sb.append("        \"").append(key).append("\": ").append(val).append(",\n");
                } else {
                    sb.append("        \"").append(key).append("\": \"")
                      .append(val.replace("\"", "\\\"")).append("\",\n");
                }
            }
            sb.append("    }");
            if (i < products.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]\n\n");

        // ── Data helpers ─────────────────────────────────────────────────
        sb.append("""
def load_products():
    \"\"\"مصدر الحقيقة الوحيد للبيانات: data/products.json.
    Java Regeneration Server يكتب/يقرأ من نفس الملف، لذا يبقى Flask
    والمولّد Java متوافقين دوماً (انظر قسم Regeneration في التقرير).\"\"\"
    if os.path.exists(DATA_FILE):
        try:
            with open(DATA_FILE, "r", encoding="utf-8") as f:
                return json.load(f)
        except (json.JSONDecodeError, OSError):
            pass
    return list(_INITIAL_PRODUCTS)


def call_java_server(path, payload):
    \"\"\"يرسل عملية Add/Delete إلى Java Server، الذي يحدّث data/products.json
    وينفّذ Regeneration لملفات output/*.html. إن تعذّر الاتصال (السيرفر غير
    مُشغَّل) نعيد False بهدوء بدل تعطيل تطبيق Flask بالكامل.\"\"\"
    try:
        data = urllib.parse.urlencode(payload).encode("utf-8")
        req = urllib.request.Request(JAVA_SERVER + path, data=data, method="POST")
        with urllib.request.urlopen(req, timeout=5) as resp:
            return resp.status == 200
    except Exception as e:
        print(f"[app.py] WARNING: Java server unreachable ({e}). "
              f"Falling back to local-only update — no regeneration will run.")
        return False


# ── Routes ──────────────────────────────────────────────────────────────

@app.route("/")
def index():
    return redirect(url_for("show_products"))


@app.route("/products")
def show_products():
    return render_template("products.html", products=load_products())


@app.route("/products/add", methods=["GET", "POST"])
def add_product():
    if request.method == "POST":
        name    = request.form.get("name", "").strip()
        price   = request.form.get("price", "0").strip()
        image   = request.form.get("image", "").strip() or "/static/images/default-product.svg"
        details = request.form.get("details", "").strip()

        if not name or not price:
            flash("Name and price are required.")
            return redirect(url_for("add_product"))

        ok = call_java_server("/products/add", {
            "name": name, "price": price, "image": image, "details": details
        })

        if not ok:
            # احتياطي محلي إن كان الـ Java Server متوقفاً — نضيف مباشرة لملف البيانات
            products = load_products()
            new_id = (products[-1]["id"] + 1) if products else 1
            try:
                price_val = float(price)
            except ValueError:
                price_val = price
            products.append({"id": new_id, "name": name, "price": price_val, "image": image, "details": details})
            os.makedirs(os.path.dirname(DATA_FILE), exist_ok=True)
            with open(DATA_FILE, "w", encoding="utf-8") as f:
                json.dump(products, f, indent=2)

        flash(f"Product '{name}' added successfully!")
        return redirect(url_for("show_products"))

    return render_template("add_product.html")


@app.route("/products/<int:product_id>")
def product_detail(product_id):
    products = load_products()
    product = next((p for p in products if p["id"] == product_id), None)
    if product is None:
        return render_template("product_detail.html", product=None), 404
    return render_template("product_detail.html", product=product)


@app.route("/products/<int:product_id>/delete", methods=["POST"])
def delete_product(product_id):
    ok = call_java_server("/products/delete", {"id": str(product_id)})

    if not ok:
        products = load_products()
        products = [p for p in products if p["id"] != product_id]
        os.makedirs(os.path.dirname(DATA_FILE), exist_ok=True)
        with open(DATA_FILE, "w", encoding="utf-8") as f:
            json.dump(products, f, indent=2)

    flash("Product deleted successfully.")
    return redirect(url_for("show_products"))


# ── Entry Point ──────────────────────────────────────────────────────────

if __name__ == "__main__":
    os.makedirs(os.path.dirname(DATA_FILE), exist_ok=True)
    if not os.path.exists(DATA_FILE):
        with open(DATA_FILE, "w", encoding="utf-8") as f:
            json.dump(_INITIAL_PRODUCTS, f, indent=2)
    app.run(debug=True, use_reloader=False)
""");

        return sb.toString();
    }
}
