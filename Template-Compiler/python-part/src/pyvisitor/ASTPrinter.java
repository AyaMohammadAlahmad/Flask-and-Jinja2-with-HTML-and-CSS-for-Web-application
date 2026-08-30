package pyvisitor;
import pyast.*;
/**
 * يطبع الـ AST بتنسيق شجري متداخل مريح للقراءة.
 *
 * <p>يعتمد على الدالة {@code toString(int indent)} الموجودة في كل نود،
 * ويُضيف رأساً وذيلاً مميزَين لتسهيل التعرف على الإخراج.</p>
 *
 * <p>نمط الاستخدام:</p>
 * <pre>
 *   Program program = (Program) new ASTBuilder().visit(parseTree);
 *   ASTPrinter.print(program);
 * </pre>
 *
 * <p>مثال على الإخراج:</p>
 * <pre>
 * ==== PYTHON FLASK AST ====
 * ProgramNode (line 1)
 *   TopImport: FromImport (line 1)
 *     module : flask
 *     names  : Flask, render_template
 *   TopDecoratedFunction (line 3)
 *     DecoratorNode: @app.route (line 3)
 *       PositionalArg (line 3)
 *         StringLiteral: '/' (line 3)
 *     FunctionDefNode: index (line 4)
 *       BlockRule (line 5)
 *         ReturnS (line 5)
 *           StringLiteral: 'Hello' (line 5)
 * ==========================
 * </pre>
 */
public class ASTPrinter {

    private static final String HEADER = "==== PYTHON FLASK AST ====";
    private static final String FOOTER = "==========================";

    /** طباعة الشجرة كاملةً على الـ stdout. */
    public static void print(Program program) {
        System.out.println(HEADER);
        System.out.print(program.toString(0));
        System.out.println(FOOTER);
    }

    /**
     * إعادة الشجرة كاملةً كنص واحد (مفيد للاختبارات أو الحفظ في ملف).
     */
    public static String toText(Program program) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER).append("\n");
        sb.append(program.toString(0));
        sb.append(FOOTER).append("\n");
        return sb.toString();
    }

    /** منع إنشاء نسخ من هذا الكلاس (Utility class). */
    private ASTPrinter() {}
}
