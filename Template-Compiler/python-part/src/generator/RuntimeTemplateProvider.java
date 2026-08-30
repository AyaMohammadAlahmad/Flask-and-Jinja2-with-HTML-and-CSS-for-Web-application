package generator;

/**
 * يولّد قوالب Jinja2 "حقيقية وكاملة" (بدون قيود الـ grammar الخاص بالمترجم)
 * لتُستخدم من Flask نفسه في وقت التشغيل عبر {@code render_template(...)}.
 *
 * <p><b>لماذا هذا الكلاس منفصل عن {@link render.JinjaRenderer}؟</b><br>
 * الـ Jinja Parser/AST الخاص بالمشروع (antlar.htmlParser) لا يدعم حالياً
 * {@code {% if %}}, {@code {% with %}}, أو string concatenation داخل الـ
 * attributes — وهذه ضرورية لتطبيق Flask تفاعلي حقيقي (رسائل flash، توليد
 * روابط ديناميكية لكل منتج عبر {@code url_for(...)}). لذلك:</p>
 * <ul>
 *   <li>قوالب {@code templates/*.jinja} (المدخلة لمرحلة Code Generation في
 *       compiler_output/output) تُعالَج فعلياً عبر Jinja Parser/AST الخاص
 *       بالمشروع — هذا هو الناتج الحقيقي المطلوب في output/*.html.</li>
 *   <li>قوالب {@code output/templates/*.html} هنا هي "ملفات دعم تشغيلي"
 *       لتطبيق Flask الفعلي (تماماً مثل app.py وstyle.css) — تُستخدم من
 *       محرّك Jinja2 الحقيقي التابع لـ Flask نفسه (مكتبة Python)، وليس من
 *       مترجمنا. هذا الفصل موثّق في التقرير النهائي كأحد قرارات حل التعارض
 *       (البند 16 من المتطلبات).</li>
 * </ul>
 */
public final class RuntimeTemplateProvider {

    private RuntimeTemplateProvider() {}

    public static String productsHtml() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Products</title>
    <link rel="stylesheet" href="{{ url_for('static', filename='style.css') }}">
</head>
<body>
<nav>
    <span>MyShop</span>
    <div>
        <a href="{{ url_for('show_products') }}">Products</a>
        <a href="{{ url_for('add_product') }}">+ Add Product</a>
    </div>
</nav>
<div class="container">
    {% with messages = get_flashed_messages() %}
        {% if messages %}
            {% for message in messages %}
                <div class="flash-msg">{{ message }}</div>
            {% endfor %}
        {% endif %}
    {% endwith %}

    <h1>Our Products</h1>
    {% if products %}
    <div class="productsgrid">
        {% for product in products %}
        <div class="productcard">
            {% if product.image %}
            <img class="productimg" src="{{ product.image }}" alt="{{ product.name }}">
            {% endif %}
            <h3>{{ product.name }}</h3>
            <p class="price">${{ product.price }}</p>
            <p>{{ product.details }}</p>
            <div>
                <a class="btn btn-primary" href="{{ url_for('product_detail', product_id=product.id) }}">View</a>
                <form method="POST" action="{{ url_for('delete_product', product_id=product.id) }}" style="display:inline;" data-confirm="Delete this product?">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
        {% endfor %}
    </div>
    {% else %}
    <p>No products yet. <a href="{{ url_for('add_product') }}">Add the first one!</a></p>
    {% endif %}
</div>
<script src="{{ url_for('static', filename='script.js') }}"></script>
</body>
</html>
""";
    }

    public static String addProductHtml() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" href="{{ url_for('static', filename='style.css') }}">
</head>
<body>
<nav>
    <span>MyShop</span>
    <div>
        <a href="{{ url_for('show_products') }}">Products</a>
        <a href="{{ url_for('add_product') }}">+ Add Product</a>
    </div>
</nav>
<div class="container">
    {% with messages = get_flashed_messages() %}
        {% if messages %}
            {% for message in messages %}
                <div class="flash-msg">{{ message }}</div>
            {% endfor %}
        {% endif %}
    {% endwith %}

    <h1>Add New Product</h1>
    <form method="POST" action="{{ url_for('add_product') }}">
        <label for="name">Product Name</label>
        <input type="text" id="name" name="name" required>
        <label for="price">Price ($)</label>
        <input type="number" id="price" name="price" min="0" step="0.01" required>
        <label for="image">Image URL</label>
        <input type="text" id="image" name="image" placeholder="/static/images/example.svg or https://...">
        <label for="details">Details</label>
        <textarea id="details" name="details"></textarea>
        <button type="submit">Add Product</button>
    </form>
</div>
<script src="{{ url_for('static', filename='script.js') }}"></script>
</body>
</html>
""";
    }

    public static String productDetailHtml() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Detail</title>
    <link rel="stylesheet" href="{{ url_for('static', filename='style.css') }}">
</head>
<body>
<nav>
    <span>MyShop</span>
    <div>
        <a href="{{ url_for('show_products') }}">Products</a>
        <a href="{{ url_for('add_product') }}">+ Add Product</a>
    </div>
</nav>
<div class="container">
    {% if product %}
    <h1>{{ product.name }}</h1>
    {% if product.image %}
    <img class="productimg-large" src="{{ product.image }}" alt="{{ product.name }}">
    {% endif %}
    <p class="price">${{ product.price }}</p>
    <p>{{ product.details }}</p>
    <a class="btn btn-primary" href="{{ url_for('show_products') }}">Back</a>
    <form method="POST" action="{{ url_for('delete_product', product_id=product.id) }}" style="display:inline;" data-confirm="Delete this product?">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
    {% else %}
    <h2>Product not found</h2>
    <a class="btn btn-primary" href="{{ url_for('show_products') }}">Back to Products</a>
    {% endif %}
</div>
<script src="{{ url_for('static', filename='script.js') }}"></script>
</body>
</html>
""";
    }
}
