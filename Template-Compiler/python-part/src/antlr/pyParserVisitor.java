// Generated from C:/‏‏‏‏مترجمات/‏‏‏‏untitled1 - نسخة - نسخة/Template-Compiler/python-part/grammar/pyParser.g4 by ANTLR 4.13.2

package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link pyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface pyParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code ProgramRule}
	 * labeled alternative in {@link pyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramRule(pyParser.ProgramRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TopImport}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopImport(pyParser.TopImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TopDecoratedFunction}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopDecoratedFunction(pyParser.TopDecoratedFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TopFunction}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopFunction(pyParser.TopFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TopStatement}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopStatement(pyParser.TopStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FromImport}
	 * labeled alternative in {@link pyParser#importStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromImport(pyParser.FromImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleImport}
	 * labeled alternative in {@link pyParser#importStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleImport(pyParser.SimpleImportContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#dottedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedName(pyParser.DottedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#importList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportList(pyParser.ImportListContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#decorator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorator(pyParser.DecoratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#functionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDef(pyParser.FunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(pyParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(pyParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code KeywordArg}
	 * labeled alternative in {@link pyParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordArg(pyParser.KeywordArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PositionalArg}
	 * labeled alternative in {@link pyParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionalArg(pyParser.PositionalArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockRule}
	 * labeled alternative in {@link pyParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockRule(pyParser.BlockRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(pyParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnS}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnS(pyParser.ReturnSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(pyParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code For}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(pyParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(pyParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(pyParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(pyParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#assignTarget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignTarget(pyParser.AssignTargetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleAssign}
	 * labeled alternative in {@link pyParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleAssign(pyParser.SimpleAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AugAssign}
	 * labeled alternative in {@link pyParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugAssign(pyParser.AugAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(pyParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(pyParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(pyParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(pyParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DictE}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictE(pyParser.DictEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpr(pyParser.MulExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IsNotExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNotExpr(pyParser.IsNotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(pyParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubscriptExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptExpr(pyParser.SubscriptExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListE}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListE(pyParser.ListEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PowExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(pyParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NameExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameExpr(pyParser.NameExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(pyParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(pyParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(pyParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpr(pyParser.CompareExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IsExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsExpr(pyParser.IsExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(pyParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(pyParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(pyParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(pyParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TernaryExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpr(pyParser.TernaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallExpr(pyParser.MethodCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AttrExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrExpr(pyParser.AttrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PlainList}
	 * labeled alternative in {@link pyParser#listExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlainList(pyParser.PlainListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListComp}
	 * labeled alternative in {@link pyParser#listExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListComp(pyParser.ListCompContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#dictExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictExpr(pyParser.DictExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#dictItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictItem(pyParser.DictItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link pyParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(pyParser.LiteralContext ctx);
}