package pyvisitor;

import pyast.*;
import pyerrors.CompilerError;
import pysemantic.Symbol;
import pysemantic.SymbolTable;
import pysemantic.SymbolType;
import pysemantic.checks.*;
import pysemantic.types.TypeInference;
import pysemantic.types.ValueType;

import java.util.List;

/**
 * المحلل الدلالي — يمشي على شجرة الـ AST المخصصة (وليس الـ ParseTree)
 * ويملأ جدول الرموز {@link SymbolTable}.
 *
 * <p>هذا الكلاس <b>لا يحتوي منطق الفحص نفسه</b> — كل خطأ له كلاس مستقل
 * في {@code package semantic.checks}. مهمة هذا الكلاس فقط هي:</p>
 * <ol>
 *   <li>المشي على الشجرة (Traversal)</li>
 *   <li>إدارة النطاقات (enterScope / exitScope)</li>
 *   <li>استدعاء كلاس الـ Check المناسب في كل نقطة، وتجميع الأخطاء</li>
 * </ol>
 *
 * <p>الأخطاء المغطاة (كل واحد بكلاس مستقل في {@code semantic.checks}):</p>
 * <table border="1">
 *   <tr><th>#</th><th>الخطأ</th><th>الكلاس المسؤول</th></tr>
 *   <tr><td>1</td><td>متغير غير معرّف</td><td>{@link UndefinedNameCheck}</td></tr>
 *   <tr><td>2</td><td>إعادة تعريف دالة</td><td>{@link DuplicateFunctionCheck}</td></tr>
 *   <tr><td>3</td><td>تكرار اسم معامل</td><td>{@link DuplicateParamCheck}</td></tr>
 *   <tr><td>4</td><td>+= على متغير غير معرّف</td><td>{@link UndefinedAugAssignCheck}</td></tr>
 *   <tr><td>5</td><td>استدعاء اسم غير معرّف</td><td>{@link CallUndefinedCheck}</td></tr>
 *   <tr><td>6</td><td>استدعاء متغير كدالة</td><td>{@link CallNonFunctionCheck}</td></tr>
 *   <tr><td>7</td><td>break خارج حلقة</td><td>{@link BreakOutsideLoopCheck}</td></tr>
 *   <tr><td>8</td><td>إسناد فوق اسم دالة</td><td>{@link ReassignFunctionCheck}</td></tr>
 *   <tr><td>9</td><td>return خارج دالة</td><td>{@link ReturnOutsideFunctionCheck}</td></tr>
 *   <tr><td>10</td><td>تغطية اسم مستورد</td><td>{@link ShadowImportCheck}</td></tr>
 *   <tr><td>11</td><td>تعارض الأنواع</td><td>{@link TypeMismatchCheck}</td></tr>
 * </table>
 *
 * <p>الأسماء المعرّفة مسبقاً في الـ Global Scope (لا تُعتبر "غير معرّفة"):</p>
 * <ul>
 *   <li>{@code FLASK_BUILTINS}  — رموز خاصة بـ Flask (request, render_template...)</li>
 *   <li>{@code PYTHON_BUILTINS} — دوال Python المدمجة (float, int, str, len...)</li>
 * </ul>
 *
 * <p>نمط الاستخدام:</p>
 * <pre>
 *   Program program = (Program) new ASTBuilder().visit(parseTree);
 *   SemanticAnalyzer analyzer = new SemanticAnalyzer();
 *   analyzer.analyze(program);
 *
 *   if (analyzer.hasErrors()) {
 *       analyzer.printErrors();
 *   }
 * </pre>
 */
public class SemanticAnalyzer {

    private final SymbolTable      table  = new SymbolTable();
    private final List<String>     errors = new java.util.ArrayList<>();

    /** عدّاد لتتبع عمق التداخل داخل حلقات for/while — يُستخدم في BreakOutsideLoopCheck. */
    private int loopDepth = 0;

    /** عدّاد لتتبع عمق التداخل داخل الدوال — يُستخدم في ReturnOutsideFunctionCheck. */
    private int functionDepth = 0;

    /** أسماء Flask السحرية التي يجب أن تكون معرّفة مسبقاً في الـ Global Scope. */
    private static final String[] FLASK_BUILTINS = {
        "request", "render_template", "redirect", "url_for",
        "jsonify", "session", "abort", "flash", "g",
        "__name__", "Flask"
    };

    /**
     * دوال Python المدمجة (Built-in Functions) التي تنتمي للغة نفسها
     * وليس لـ Flask تحديداً — مفصولة في قائمة مستقلة عن {@link #FLASK_BUILTINS}
     * حفاظاً على وضوح المصدر (Separation of Concerns) ولتسهيل التوسعة لاحقاً.
     */
    private static final String[] PYTHON_BUILTINS = {
        "float", "int", "str", "bool", "len",
        "print", "range", "list", "dict", "abs",
        "min", "max", "sum", "sorted", "enumerate"
    };

    // ═══════════════════════════════════════════════════════
    //  نقطة الدخول
    // ═══════════════════════════════════════════════════════

    public void analyze(Program program) {
        predefineFlaskBuiltins();
        predefinePythonBuiltins();
        visitProgram(program);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void printErrors() {
        for (String err : errors) {
            System.out.println(err);
        }
    }

    public SymbolTable getSymbolTable() {
        return table;
    }

    /** يضيف نتيجة Check إلى قائمة الأخطاء إذا لم تكن {@code null}. */
    private void report(CompilerError err) {
        if (err != null) {
            errors.add(err.toString());
        }
    }

    // ═══════════════════════════════════════════════════════
    //  تجهيز الرموز السحرية لـ Flask
    // ═══════════════════════════════════════════════════════

    private void predefineFlaskBuiltins() {
        for (String name : FLASK_BUILTINS) {
            table.defineGlobal(new Symbol(name, SymbolType.IMPORT, 0));
        }
    }

    // ═══════════════════════════════════════════════════════
    //  تجهيز دوال Python المدمجة (built-in functions)
    // ═══════════════════════════════════════════════════════

    /**
     * يعرّف دوال Python الأساسية في الـ Global Scope كنوع {@link SymbolType#FUNCTION}،
     * حتى يتم التعامل معها بشكل سليم من قِبل {@code CallNonFunctionCheck}
     * (بمعنى: لو حاول أحد يستخدمها كمتغير لاحقاً، يُكتشف الخطأ بنفس آلية أي دالة أخرى).
     */
    private void predefinePythonBuiltins() {
        for (String name : PYTHON_BUILTINS) {
            table.defineGlobal(new Symbol(name, SymbolType.FUNCTION, 0));
        }
    }

    // ═══════════════════════════════════════════════════════
    //  Program / TopLevel
    // ═══════════════════════════════════════════════════════

    private void visitProgram(Program program) {
        for (TopLevel top : program.getStatements()) {
            visitTopLevel(top);
        }
    }

    private void visitTopLevel(TopLevel top) {
        if (top instanceof FromImport fi) {
            visitFromImport(fi);
        } else if (top instanceof SimpleImport si) {
            visitSimpleImport(si);
        } else if (top instanceof DecoratedFunction df) {
            visitDecoratedFunction(df);
        } else if (top instanceof TopFunction tf) {
            visitFunctionDef(tf.getFunction());
        } else if (top instanceof TopStatement ts) {
            visitStatement(ts.getStatement());
        }
    }

    // ═══════════════════════════════════════════════════════
    //  Imports
    // ═══════════════════════════════════════════════════════

    private void visitFromImport(FromImport node) {
        for (String name : node.getNames()) {
            if (!table.getGlobalScope().containsLocal(name)) {
                table.defineGlobal(new Symbol(name, SymbolType.IMPORT, node.getLine()));
            }
        }
    }

    private void visitSimpleImport(SimpleImport node) {
        String topName = node.getModule().split("\\.")[0];
        if (!table.getGlobalScope().containsLocal(topName)) {
            table.defineGlobal(new Symbol(topName, SymbolType.IMPORT, node.getLine()));
        }
    }

    // ═══════════════════════════════════════════════════════
    //  Decorated Function
    // ═══════════════════════════════════════════════════════

    private void visitDecoratedFunction(DecoratedFunction node) {
        for (Decorator dec : node.getDecorators()) {
            for (Argument arg : dec.getArgs()) {
                visitExpr(arg.getValue());
            }
        }
        visitFunctionDef(node.getFunction());
    }

    // ═══════════════════════════════════════════════════════
    //  FunctionDef — إدارة النطاقات + الخطأ 2 و 3
    // ═══════════════════════════════════════════════════════

    private void visitFunctionDef(FunctionDef node) {

        // ── الخطأ 2: إعادة تعريف دالة في نفس النطاق ──────────────────
        report(DuplicateFunctionCheck.check(node.getName(), node.getLine(), table));

        if (!table.containsLocal(node.getName())) {
            table.define(new Symbol(node.getName(), SymbolType.FUNCTION, node.getLine()));
        }

        table.enterScope("function:" + node.getName());

        // ── الخطأ 3: تكرار اسم معامل داخل نفس الدالة ─────────────────
        for (String param : node.getParams()) {
            report(DuplicateParamCheck.check(param, node.getName(), node.getLine(), table));

            if (!table.containsLocal(param)) {
                table.define(new Symbol(param, SymbolType.VARIABLE, node.getLine()));
            }
        }

        functionDepth++;
        visitBlock(node.getBody());
        functionDepth--;

        table.exitScope();
    }

    // ═══════════════════════════════════════════════════════
    //  Block / Statements
    // ═══════════════════════════════════════════════════════

    private void visitBlock(Block block) {
        for (Stmt stmt : block.getStatements()) {
            visitStatement(stmt);
        }
    }

    private void visitStatement(Stmt stmt) {
        if (stmt instanceof Assign a) {
            visitAssign(a);
        } else if (stmt instanceof AugAssign aa) {
            visitAugAssign(aa);
        } else if (stmt instanceof Return r) {
            visitReturn(r);
        } else if (stmt instanceof If i) {
            visitIf(i);
        } else if (stmt instanceof For f) {
            visitFor(f);
        } else if (stmt instanceof While w) {
            visitWhile(w);
        } else if (stmt instanceof Break br) {
            visitBreak(br);
        } else if (stmt instanceof ExprStmt es) {
            visitExpr(es.getExpr());
        }
    }

    // ═══════════════════════════════════════════════════════
    //  Assignment — الخطأ 8
    // ═══════════════════════════════════════════════════════

    private void visitAssign(Assign node) {
        AssignTarget target = node.getTarget();

        visitExpr(node.getValue());

        boolean isSimpleIdentifier = target.getAttrs().isEmpty() && target.getSubscript() == null;

        if (isSimpleIdentifier) {
            String varName = target.getBase();

            // ── الخطأ 10: تغطية اسم مستورد (Shadow Import) ────────────
            report(ShadowImportCheck.check(varName, node.getLine(), table));

            // ── الخطأ 8: إسناد فوق اسم دالة معرّفة ───────────────────
            report(ReassignFunctionCheck.check(varName, node.getLine(), table));

            if (!table.containsLocal(varName)) {
                table.define(new Symbol(varName, SymbolType.VARIABLE, node.getLine()));
            }
        } else {
            // الحالة المركّبة، e.g. app.config['KEY'] = ...
            report(UndefinedNameCheck.check(target.getBase(), node.getLine(), table));

            if (target.getSubscript() != null) {
                visitExpr(target.getSubscript());
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    //  AugAssign — الخطأ 4
    // ═══════════════════════════════════════════════════════

    private void visitAugAssign(AugAssign node) {
        CompilerError err = UndefinedAugAssignCheck.check(node.getVarName(), node.getLine(), table);

        if (err != null) {
            // المتغير غير معرّف — نسجّل الخطأ ونوقف الفحص هنا
            // لا نزور الـ value عشان ما يتكرر الخطأ من visitNameExpr
            report(err);
        } else {
            // المتغير معرّف — نزور الـ value بأمان
            visitExpr(node.getValue());
        }
    }

    // ═══════════════════════════════════════════════════════
    //  Return / If / For / While
    // ═══════════════════════════════════════════════════════

    private void visitReturn(Return node) {
        // ── الخطأ 9: return خارج دالة ──────────────────────────────────
        report(ReturnOutsideFunctionCheck.check(functionDepth, node.getLine()));

        for (Expr value : node.getValues()) {
            visitExpr(value);
        }
    }

    private void visitIf(If node) {
        visitExpr(node.getCondition());
        visitBlock(node.getThenBlock());

        for (If.ElifClause elif : node.getElifClauses()) {
            visitExpr(elif.condition);
            visitBlock(elif.body);
        }

        if (node.getElseBlock() != null) {
            visitBlock(node.getElseBlock());
        }
    }

    private void visitFor(For node) {
        visitExpr(node.getIterable());

        if (!table.containsLocal(node.getVariable())) {
            table.define(new Symbol(node.getVariable(), SymbolType.VARIABLE, node.getLine()));
        }

        loopDepth++;
        visitBlock(node.getBody());
        loopDepth--;
    }

    private void visitWhile(While node) {
        visitExpr(node.getCondition());

        loopDepth++;
        visitBlock(node.getBody());
        loopDepth--;
    }

    // ═══════════════════════════════════════════════════════
    //  Break — الخطأ 7
    // ═══════════════════════════════════════════════════════

    private void visitBreak(Break node) {
        report(BreakOutsideLoopCheck.check(loopDepth, node.getLine()));
    }

    // ═══════════════════════════════════════════════════════
    //  Expressions
    // ═══════════════════════════════════════════════════════

    private void visitExpr(Expr expr) {
        if (expr == null) return;

        if (expr instanceof NameExpr n) {
            visitNameExpr(n);

        } else if (expr instanceof BinaryExpr b) {
            // ── الخطأ 11: Type Mismatch ────────────────────────────────
            // نفحص أولاً قبل ما نزور الأبناء (لأن الفحص يحتاج كلا الطرفين كـ Expr)
            report(TypeMismatchCheck.check(b, table));
            visitExpr(b.getLeft());
            visitExpr(b.getRight());

        } else if (expr instanceof UnaryExpr u) {
            visitExpr(u.getOperand());

        } else if (expr instanceof TernaryExpr t) {
            visitExpr(t.getThenExpr());
            visitExpr(t.getCondition());
            visitExpr(t.getElseExpr());

        } else if (expr instanceof CallExpr c) {
            visitCallExpr(c);

        } else if (expr instanceof MethodCallExpr m) {
            visitExpr(m.getObject());
            for (Argument arg : m.getArgs()) {
                visitExpr(arg.getValue());
            }

        } else if (expr instanceof AttrExpr a) {
            visitExpr(a.getObject());

        } else if (expr instanceof SubscriptExpr s) {
            visitExpr(s.getObject());
            visitExpr(s.getIndex());

        } else if (expr instanceof ParenExpr p) {
            visitExpr(p.getInner());

        } else if (expr instanceof ListExpr l) {
            for (Expr el : l.getElements()) {
                visitExpr(el);
            }

        } else if (expr instanceof ListComp lc) {
            visitExpr(lc.getIterable());
            if (!table.containsLocal(lc.getVariable())) {
                table.define(new Symbol(lc.getVariable(), SymbolType.VARIABLE, lc.getLine()));
            }
            visitExpr(lc.getElement());
            if (lc.getFilter() != null) visitExpr(lc.getFilter());

        } else if (expr instanceof DictExpr d) {
            for (DictExpr.DictItem item : d.getItems()) {
                visitExpr(item.getKey());
                visitExpr(item.getValue());
            }
        }
        // StringLiteral / NumberLiteral / BoolLiteral / NoneLiteral → لا فحص لازم
    }

    // ═══════════════════════════════════════════════════════
    //  NameExpr — الخطأ 1
    // ═══════════════════════════════════════════════════════

    private void visitNameExpr(NameExpr node) {
        report(UndefinedNameCheck.check(node.getName(), node.getLine(), table));
    }

    // ═══════════════════════════════════════════════════════
    //  CallExpr — الخطأ 5 و 6
    // ═══════════════════════════════════════════════════════

    private void visitCallExpr(CallExpr node) {

        if (node.getCallee() instanceof NameExpr nameExpr) {
            String calleeName = nameExpr.getName();

            CompilerError undefinedErr =
                CallUndefinedCheck.check(calleeName, node.getLine(), table);

            if (undefinedErr != null) {
                report(undefinedErr);
            } else {
                // الاسم موجود — نتحقق أنه فعلاً دالة وليس متغيراً
                report(CallNonFunctionCheck.check(calleeName, node.getLine(), table));
            }

        } else {
            visitExpr(node.getCallee());
        }

        for (Argument arg : node.getArgs()) {
            visitExpr(arg.getValue());
        }
    }
}
