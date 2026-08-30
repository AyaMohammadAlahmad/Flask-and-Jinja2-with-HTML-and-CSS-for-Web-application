package pysemantic.checks;

import pyerrors.CompilerError;

import pyast.BinaryExpr;
import pysemantic.types.TypeInference;
import pysemantic.types.ValueType;

/**
 * الخطأ 11: تطبيق عملية على نوعين غير متوافقين (Type Mismatch).
 *
 * <p>يعتمد على {@link TypeInference} لاستنتاج نوع كل طرف،
 * ثم يتحقق من توافقهما مع العملية.</p>
 *
 * <p>قاعدة الأمان: إذا كان أي طرف {@link ValueType#UNKNOWN}
 * (مثل نتيجة استدعاء دالة أو متغير غير معروف النوع)،
 * لا يُطلق أي خطأ لتجنب الـ false positives.</p>
 *
 * <p>أمثلة تسبب الخطأ:</p>
 * <pre>
 *   x = "hello" + 5       ← STRING + NUMBER
 *   y = True + "world"    ← BOOL   + STRING
 *   z = 1 == "one"        ← NUMBER == STRING
 *   w = "hi" - "there"    ← STRING - STRING (- لا تدعم String)
 * </pre>
 *
 * <p>أمثلة لا تسبب خطأ:</p>
 * <pre>
 *   a = 1 + 2             ← NUMBER + NUMBER ✅
 *   b = "hi" + "!"        ← STRING + STRING ✅
 *   c = foo() + 5         ← UNKNOWN + NUMBER → نسكت ✅
 * </pre>
 */
public class TypeMismatchCheck {

    /**
     * @param expr  التعبير الثنائي المراد فحصه
     * @param table جدول الرموز الحالي
     * @return {@link CompilerError} لو كان هناك تعارض بين النوعين، أو {@code null} لو سليم
     */
    public static CompilerError check(BinaryExpr expr, pysemantic.SymbolTable table) {

        ValueType left  = TypeInference.infer(expr.getLeft(),  table);
        ValueType right = TypeInference.infer(expr.getRight(), table);

        // لو أي طرف UNKNOWN → لا نحكم (تجنب false positives)
        if (left == ValueType.UNKNOWN || right == ValueType.UNKNOWN) {
            return null;
        }

        // نجرّب نستنتج نوع الناتج
        // inferBinary ترجع null لو كانت العملية غير متوافقة
        ValueType result = TypeInference.inferBinary(left, expr.getOp(), right);

        if (result == null) {
            return CompilerError.semantic(expr.getLine(),
                "Type mismatch: cannot apply '" + expr.getOp()
                + "' to " + left + " and " + right);
        }
        return null;
    }
}
