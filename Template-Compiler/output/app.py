import json
import os
import urllib.request
import urllib.parse
from flask import Flask, render_template, request, redirect, url_for, flash

app = Flask(__name__)
app.secret_key = 'generated-secret-key-2025'

JAVA_SERVER = "http://localhost:8090"
DATA_FILE = os.path.join(os.path.dirname(__file__), "data", "products.json")

# ── Products data (Context Data المستخرجة أصلاً من Python AST) ──
_INITIAL_PRODUCTS = [
    {
        "id": 1,
        "name": "Laptop",
        "price": 1200,
        "image": "/static/images/laptop.svg",
        "details": "Simple laptop for work and study.",
    },
    {
        "id": 2,
        "name": "Headphones",
        "price": 150,
        "image": "/static/images/headphones.svg",
        "details": "Over-ear headphones with clear sound.",
    }
]

def load_products():
    """مصدر الحقيقة الوحيد للبيانات: data/products.json.
    Java Regeneration Server يكتب/يقرأ من نفس الملف، لذا يبقى Flask
    والمولّد Java متوافقين دوماً (انظر قسم Regeneration في التقرير)."""
    if os.path.exists(DATA_FILE):
        try:
            with open(DATA_FILE, "r", encoding="utf-8") as f:
                return json.load(f)
        except (json.JSONDecodeError, OSError):
            pass
    return list(_INITIAL_PRODUCTS)


def call_java_server(path, payload):
    """يرسل عملية Add/Delete إلى Java Server، الذي يحدّث data/products.json
    وينفّذ Regeneration لملفات output/*.html. إن تعذّر الاتصال (السيرفر غير
    مُشغَّل) نعيد False بهدوء بدل تعطيل تطبيق Flask بالكامل."""
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
        details = request.form.get("details", "").strip()

        if not name or not price:
            flash("Name and price are required.")
            return redirect(url_for("add_product"))

        ok = call_java_server("/products/add", {
            "name": name, "price": price, "details": details
        })

        if not ok:
            # احتياطي محلي إن كان الـ Java Server متوقفاً — نضيف مباشرة لملف البيانات
            products = load_products()
            new_id = (products[-1]["id"] + 1) if products else 1
            try:
                price_val = float(price)
            except ValueError:
                price_val = price
            products.append({"id": new_id, "name": name, "price": price_val, "details": details})
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
