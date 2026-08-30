# تقرير نهائي — تعديل مرحلة Code Generation في مشروع Template-Compiler

## 0) ملخص تنفيذي

تم فحص المشروع المرفق بالكامل (شجرتي `java-part` و`python-part`)، وتبيّن أن:

- الـ Jinja Parser/AST (ANTLR + `Visitor.ASTVisitor` + `Semantic.SemanticAnalyzer`)
  كانا موجودين فعلاً لكن **غير مستخدَمين إطلاقاً** في التوليد — كانت
  `CodeGenerator.java` تنتج HTML كنصوص Java ثابتة (hardcoded string literals)،
  دون أي علاقة بالـ Jinja Parser الحقيقي.
- **لا يوجد أي Java Server في المشروع الأصلي** رغم أن المتطلبات تشير إليه
  كـ"موجود". تم فحص الشجرتين بالكامل (`grep` عن Server/Socket/HttpServer) ولم
  يُعثر على أي كود شبكة.
- الـ Jinja grammar (`antlar/htmlParser.g4`) **لا يدعم `{% if %}` أو
  `{% with %}`** — فقط `extends` و`block`/`endblock` و`for`/`endfor`. القوالب
  القديمة كانت تستخدم `{% if %}` بكثرة، أي أنها لم تكن لتُحلَّل فعلياً عبر
  المحلّل الخاص بالمشروع لو مُرِّرت له.

تم حل هذه التعارضات بأقل تغيير ممكن (تفصيل في القسم 6)، وتم **تشغيل المشروع
فعلياً واختبار التوليد وإعادة التوليد** (Add/Delete) حتى النهاية — وليس مجرد
كتابة كود لم يُختبر.

---

## 1) الملفات التي عُدِّلت/أُضيفت

### ملفات جديدة
| الملف | الغرض |
|---|---|
| `Template-Compiler/java-part/src/render/JinjaRenderer.java` | محرّك Rendering حقيقي يمشي على Jinja AST وينفّذ variable substitution + for loops |
| `Template-Compiler/java-part/src/server/RegenServer.java` | **Java Server** الجديد (لم يكن موجوداً) — HTTP server مبني على `com.sun.net.httpserver` فقط (بدون أي dependency خارجية) |
| `Template-Compiler/java-part/src/server/SimpleJson.java` | قارئ/كاتب JSON بسيط لقائمة المنتجات (لا توجد مكتبة JSON خارجية متاحة في البيئة) |
| `Template-Compiler/python-part/src/generator/RuntimeTemplateProvider.java` | قوالب Jinja2 حقيقية كاملة (غير مقيّدة) لتشغيل Flask فعلياً — منفصلة عن مسار المترجم |
| `templates/index.jinja`, `templates/add_product.jinja`, `templates/product_detail.jinja` | ملفات الإدخال الفعلية لمرحلة Jinja Parsing (لم تكن موجودة سابقاً كملفات مستقلة) |
| `app.py` (جذر المشروع) | ملف إدخال Python يطابق مثال المتطلبات تماماً (Phone/300, Laptop/800) |
| `style.css`, `script.js` (جذر المشروع) | ملفات داعمة |

### ملفات مُعدَّلة
| الملف | التعديل |
|---|---|
| `generator/CodeGenerator.java` | حُذفت 4 دوال كانت تولّد HTML كنصوص ثابتة (`generateBaseTemplate`, `generateProductsPage`, `generateAddProductPage`, `generateProductDetailPage`) — هذا بالضبط الخلل الذي تصفه المتطلبات ("ليس مجرد توليد HTML ثابت"). استُبدلت بـ `renderJinjaTemplates()` التي تُحلِّل ملفات `.jinja` فعلياً عبر `htmlLexer`/`htmlParser`/`ASTVisitor`، تُشغِّل `SemanticAnalyzer` عليها، ثم تُنفِّذ Rendering حقيقياً عبر `JinjaRenderer`. مرحلة استخراج البيانات من Python AST (`extractData`) **لم تُحذف** كما هي. |
| `generator/GeneratorContext.java` | أُضيفت حقول/دوال لتخزين شجرة Jinja AST النصية والأخطاء الدلالية لكل قالب (لأجل `compiler_output/`) |
| `generator/GeneratorFileWriter.java` | أُعيدت هيكلته بالكامل: يفصل الآن بوضوح بين ناتج Code Generation الحقيقي (`output/*.html` المسطّحة) وملفات دعم التشغيل (`app.py`, `style.css`, `script.js`, ونسخة في `static/`) وقوالب تشغيل Flask (`output/templates/`) وتقارير `compiler_output/` |
| `generator/GeneratedFlaskApp.java` | مُعاد كتابته: بيانات المنتجات تُقرأ الآن من `data/products.json` (مصدر حقيقة مشترك مع Java Server)، ومسارات Add/Delete تستدعي Java Server عبر HTTP بدل التعديل المباشر في الذاكرة، مع fallback محلي آمن لو كان السيرفر متوقفاً |
| `python/PythonRunner.java` | يُنسِّق الآن كتابة `compiler_output/` (AST نصوص + أخطاء دلالية + سجل) بالإضافة إلى بذر `data/products.json` الأولي من نفس بيانات Python AST |

### لم يُحذف أو يُعدَّل (كما طُلب)
- Python Parser/AST/Semantic Analysis (`pyast`, `pyvisitor`, `pysemantic`) — بلا أي تغيير.
- Jinja Parser/AST/Semantic Analysis الأصلي (`AST`, `Visitor`, `Semantic`, `antlar`) — بلا أي تغيير في الملفات نفسها (فقط أُعيد استخدامها فعلياً بدل تجاهلها).
- `HtmlRunner.java`, `Main.java` — بلا تغيير.

---

## 2) ما الذي تم تنفيذه في Code Generation

المسار الفعلي الآن لكل قالب `.jinja`:

```
templates/*.jinja
   → htmlLexer + htmlParser (ANTLR)     [Jinja Parser]
   → ASTVisitor.visit(tree)              [Jinja AST]
   → HtmlSymbolTableVisitor + SemanticAnalyzer   [فحص دلالي حقيقي]
   → JinjaRenderer.render(ast, contextData)      [Generation فعلي]
   → output/index.html / add_product.html / product_detail.html
```

`JinjaRenderer` يدعم بالضبط ما تطلبه المتطلبات (بند 12): `{{ variable }}`،
`{{ product.field }}`، و`{% for x in y %} ... {% endfor %}` — عبر إيجاد
`endfor` المطابقة ضمن نفس قائمة الأبناء (بنفس الفكرة التي يعتمدها
`SemanticAnalyzer` الأصلي لفحص "unused loop variable").

**لم يُستخدم Symbol Table في مرحلة Generation** (بند 5) — الـ context data
تأتي حصراً من `List<ProductData>` المستخرجة من Python AST، والـ Symbol Table
يُستخدم فقط داخل `SemanticAnalyzer` للتحقق الدلالي كما هو مصمَّم أصلاً.

---

## 3) كيف تم ربط Python Context مع Jinja

1. `CodeGenerator.extractData()` يزور Python AST بحثاً عن `products = [...]`
   ويحوّل كل `DictExpr` إلى `ProductData` (بلا تغيير عن الأصل).
2. `renderJinjaTemplates()` يحوّل `List<ProductData>` إلى
   `List<Map<String,Object>>` ويبنيها في `Map<String,Object> context` تحت
   المفتاحين `products` و`product` (الأخير لصفحة التفاصيل، يستخدم أول منتج
   كمعاينة عند التوليد الأول — انظر القيود في القسم 7).
3. هذا الـ context يُمرَّر مباشرة إلى `JinjaRenderer.render(template, context)`.

---

## 4) كيف تم تنفيذ Java-based Regeneration

**تعارض موثَّق (بند 16):** لا يوجد Java Server في المشروع الأصلي رغم إشارة
المتطلبات إليه. تم إنشاء `server.RegenServer` كسيرفر HTTP خفيف باستخدام
`com.sun.net.httpserver.HttpServer` المدمج في الـ JDK فقط (بدون أي مكتبة
خارجية، التزاماً ببند 8).

آلية العمل:

1. `app.py` المولَّد يستدعي `POST http://localhost:8090/products/add` أو
   `/products/delete` عند كل عملية من المستخدم.
2. `RegenServer` يحدّث `output/data/products.json` (مصدر الحقيقة الوحيد
   المشترك بين Flask وJava).
3. يُعاد استخدام **نفس** `CodeGenerator.renderJinjaTemplates()` (نفس مسار
   Jinja Parser → AST → Generation الأصلي) لإعادة توليد `output/*.html` من
   البيانات الجديدة مباشرة — دون إعادة تشغيل Python Parser (البيانات بعد أول
   توليد مصدرها `products.json` لا `app.py`).
4. يُسجَّل سطر في `compiler_output/generation_log.txt` بسبب ونتيجة كل
   Regeneration.

---

## 5) كيف يعمل Add/Delete

- **Add**: Flask يستقبل نموذج الإضافة → يرسل POST إلى `RegenServer` →
  السيرفر يولّد `id` جديداً، يحدّث `products.json`، يستدعي Regeneration →
  يعيد Flask توجيه المستخدم ويقرأ `products.json` المحدَّث لعرض الصفحة.
- **Delete**: نفس المسار عبر `/products/delete` مع تمرير `id` فقط.
- في كلتا الحالتين، لو تعذّر الاتصال بـ Java Server، يقوم `app.py` بتحديث
  `products.json` محلياً كـfallback حتى لا يتعطل التطبيق بالكامل (لكن بدون
  إعادة توليد `output/*.html` في هذه الحالة الاستثنائية فقط).

---

## 6) قرارات حل التعارض (بند 16 من المتطلبات)

| التعارض | القرار | السبب |
|---|---|---|
| لا يوجد Java Server فعلي | إنشاء `RegenServer` جديد بأبسط شكل ممكن (JDK فقط) | المتطلب الوظيفي (استماع + regeneration) صريح ولا بديل عنه |
| Grammar لا يدعم `{% if %}` | القوالب المدخلة لمرحلة المترجم (`templates/*.jinja`) صُمِّمت لتستخدم فقط ما يدعمه الـ grammar فعلياً (متغيرات + for) | تجنّب تعديل الـ grammar/ANTLR لتقليل التغيير، ويطابق تماماً ما يطلبه البند 12 حرفياً |
| Flask يحتاج `{% if %}`, `url_for`, رسائل flash | فُصلت قوالب تشغيل Flask (`output/templates/*.html`) عن قوالب المترجم، وتُعامَل كملف دعم تشغيلي (مثل `app.py`) وليست "ناتج Code Generation" | ينسجم مع تمييز المتطلبات نفسها بين "Compiler output" و"Runtime support" (بند 15) |
| اسم `edit_product.jinja` في المثال | استُخدم `product_detail.jinja`/`.html` بدلاً منه | التطبيق الأصلي لا يدعم تعديل بيانات المنتج فعلياً (فقط عرض/إضافة/حذف)، والمتطلبات نفسها تسمح بذلك صراحة ("تعديل البيانات إذا كانت البنية الحالية تدعم ذلك") |

---

## 7) أين يتم إنشاء output/ وcompiler_output/

- `output/` تُنشَأ بواسطة `GeneratorFileWriter.writeAll(...)` — تُستدعى من
  `PythonRunner.run(...)` عند أول توليد، **وأيضاً** من
  `RegenServer.regenerate(...)` عند كل Add/Delete (لملفات `*.html` فقط، دون
  إعادة كتابة `app.py`/الملفات الداعمة في كل مرة).
- `compiler_output/` تُنشَأ في `GeneratorFileWriter.writeCompilerOutput(...)`
  عند أول توليد، وتُحدَّث `generation_log.txt` فقط (append) عند كل
  Regeneration من `RegenServer`.

---

## 8) كيف تم اختبار المشروع (تنفيذ فعلي، ليس افتراضياً)

تم تجميع المشروع بالكامل (`javac`، 115 ملف Java، صفر أخطاء)، ثم:

1. **Initial generation**: تشغيل `Main python app.py` مع بيانات المثال
   (Phone/300, Laptop/800) تماماً كما في المتطلبات → تأكّد إنشاء
   `output/{index,add_product,product_detail}.html` و`compiler_output/`
   الأربعة، والتحقق يدوياً أن أسماء وأسعار المنتجين ظهرت بشكل صحيح داخل HTML
   الناتج فعلياً من الـ for loop (وليس نصاً ثابتاً).
2. **تشغيل السيرفرين**: `RegenServer` على المنفذ 8090، و`python app.py` على
   المنفذ 5000 (بعد تعطيل `use_reloader` لتفادي تعارضه مع كتابات Java
   المتكررة على نفس المجلد — خلل حقيقي اكتُشف ثم أُصلح أثناء الاختبار).
3. **Add**: أُرسل طلب POST فعلي لإضافة "Headphones" ثم "Tablet" عبر
   `curl` على مسار Flask الحقيقي → تحقّقت النتائج الثلاث: `products.json`
   تحدّث، `output/index.html` تحدّث (المنتج الجديد ظهر)، وصفحة Flask الحيّة
   `/products` عرضت المنتج الجديد فوراً.
4. **Delete**: حُذف "Phone" (`id=1`) عبر `curl` فعلي على
   `/products/1/delete` → تحقّقت النتائج الثلاث بالمقابل (اختفى من الثلاثة
   مصادر: JSON، output/index.html، وصفحة Flask الحيّة).
5. تم اكتشاف وإصلاح خلل حقيقي أثناء الاختبار: ملفات `style.css`/`script.js`
   كانت تُعيد 404 من Flask لأنها لم تكن منسوخة إلى `static/` — تم إصلاحه في
   `GeneratorFileWriter` والتحقق من أن `curl /static/style.css` يعيد 200.
6. تم فحص `compiler_output/generation_log.txt` والتأكد من وجود سطرين
   واضحين لعمليتي الـ Regeneration (ADD وDELETE) بالسبب والنتيجة.

---

## 9) أوامر التشغيل النهائية خطوة بخطوة

```bash
# 1) تجميع المشروع (من جذر work_project)
find Template-Compiler/java-part/src Template-Compiler/python-part/src -name "*.java" > sources.txt
javac -encoding UTF-8 -cp dependencies/antlr-4.13.2-complete.jar -d build/classes @sources.txt

# 2) تشغيل المترجم (Python parsing → AST → Semantic → Jinja Generation)
java -cp "build/classes:dependencies/antlr-4.13.2-complete.jar" Main python app.py
# ينتج: output/  و  compiler_output/

# 3) تشغيل Java Regeneration Server (منفذ 8090)
java -cp "build/classes:dependencies/antlr-4.13.2-complete.jar" server.RegenServer 8090 output templates

# 4) في نافذة طرفية أخرى: تشغيل تطبيق Flask التفاعلي
cd output
pip install flask --break-system-packages   # إن لم تكن مثبّتة
python app.py
# التطبيق على: http://localhost:5000/products
```

عند إضافة/حذف منتج من المتصفح، Flask يستدعي Java Server تلقائياً، وتتحدّث
`output/index.html` و`output/product_detail.html` فوراً.

---

## 10) القيود المتبقية (Limitations)

1. **لا يوجد تعديل (Edit) حقيقي للمنتج** — التطبيق الأصلي كان يدعم فقط
   عرض/إضافة/حذف، ولم يُضَف Edit لتفادي توسيع الـ scope بلا داعٍ (مسموح
   صراحة في المتطلبات).
2. **صفحة `product_detail.html` الناتجة من المترجم (وليس من Flask) هي
   معاينة لأول منتج فقط** عند كل توليد/إعادة توليد — لأنها snapshot لحظة
   واحدة وليست موجّهة لمنتج معيّن بالطلب (خلافاً لصفحة Flask الحيّة
   `/products/<id>` التي تعمل لأي منتج فعلياً).
3. **الـ grammar لا يدعم `{% if %}`**، لذا قوالب المترجم (`templates/*.jinja`)
   لا تحتوي حالة "لا توجد منتجات" (empty state) — تُعرَض شبكة فارغة فقط.
4. **JSON parser/writer في `SimpleJson.java` مبسّط** (مصمَّم فقط لبنية
   القائمة المسطّحة لهذا المشروع)، وليس مكتبة JSON عامة.
5. **لا يوجد تشفير/مصادقة على اتصال Flask↔Java** (على `localhost` فقط،
   مقبول لمشروع تعليمي، لكن غير مناسب للإنتاج).
6. لو تعذّر الاتصال بـ Java Server، تُحدَّث البيانات محلياً في Flask دون
   Regeneration فعلي لـ `output/*.html` حتى يعود Java Server للعمل.
