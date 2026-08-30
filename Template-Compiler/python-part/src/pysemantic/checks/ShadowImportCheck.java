package pysemantic.checks;

import pyerrors.CompilerError;

/**
 * الخطأ 10: تعريف متغير باسم يُطابق رمزاً مستورداً ({@code IMPORT})
 * في نفس النطاق — يُعرف بـ "Shadow Import".
 *
 * <p>الفرق عن الخطأ 8 ({@link ReassignFunctionCheck}):</p>
 * <ul>
 *   <li>الخطأ 8 → الاسم كان {@code FUNCTION}</li>
 *   <li>الخطأ 10 → الاسم كان {@code IMPORT}</li>
 * </ul>
 *
 * <p>مثال يسبب الخطأ:</p>
 * <pre>
 *   from flask import Flask
 *
 *   Flask = "something"   ← يُغطّي (يـ Shadow) الـ import
 *   render_template = 5   ← نفس المشكلة
 * </pre>
 */
public class ShadowImportCheck {

    /**
     * @param varName اسم المتغير المُسنَد إليه (الطرف الأيسر)
     * @param line    رقم السطر
     * @param table   جدول الرموز الحالي
     * @return {@link CompilerError} لو الاسم import معرّف مسبقاً، أو {@code null} لو سليم
     */
    public static CompilerError check(String varName, int line, pysemantic.SymbolTable table) {
        pysemantic.Symbol existing = table.lookup(varName);

        boolean isShadowingImport = existing != null
                                 && existing.getType() == pysemantic.SymbolType.IMPORT;

        if (isShadowingImport) {
            return CompilerError.semantic(line,
                "Cannot assign to '" + varName
                + "': it shadows an imported name");
        }
        return null;
    }
}
