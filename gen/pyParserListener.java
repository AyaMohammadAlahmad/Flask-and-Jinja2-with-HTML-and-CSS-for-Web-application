// Generated from C:/Users/MatriXat/Documents/‏‏‏‏untitled1 - نسخة - نسخة/Template-Compiler/python-part/grammar/pyParser.g4 by ANTLR 4.13.2

package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link pyParser}.
 */
public interface pyParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code ProgramRule}
	 * labeled alternative in {@link pyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgramRule(pyParser.ProgramRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ProgramRule}
	 * labeled alternative in {@link pyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgramRule(pyParser.ProgramRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TopImport}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void enterTopImport(pyParser.TopImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TopImport}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void exitTopImport(pyParser.TopImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TopDecoratedFunction}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void enterTopDecoratedFunction(pyParser.TopDecoratedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TopDecoratedFunction}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void exitTopDecoratedFunction(pyParser.TopDecoratedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TopFunction}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void enterTopFunction(pyParser.TopFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TopFunction}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void exitTopFunction(pyParser.TopFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TopStatement}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void enterTopStatement(pyParser.TopStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TopStatement}
	 * labeled alternative in {@link pyParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void exitTopStatement(pyParser.TopStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FromImport}
	 * labeled alternative in {@link pyParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void enterFromImport(pyParser.FromImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FromImport}
	 * labeled alternative in {@link pyParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void exitFromImport(pyParser.FromImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleImport}
	 * labeled alternative in {@link pyParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleImport(pyParser.SimpleImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleImport}
	 * labeled alternative in {@link pyParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleImport(pyParser.SimpleImportContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#dottedName}.
	 * @param ctx the parse tree
	 */
	void enterDottedName(pyParser.DottedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#dottedName}.
	 * @param ctx the parse tree
	 */
	void exitDottedName(pyParser.DottedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#importList}.
	 * @param ctx the parse tree
	 */
	void enterImportList(pyParser.ImportListContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#importList}.
	 * @param ctx the parse tree
	 */
	void exitImportList(pyParser.ImportListContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#decorator}.
	 * @param ctx the parse tree
	 */
	void enterDecorator(pyParser.DecoratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#decorator}.
	 * @param ctx the parse tree
	 */
	void exitDecorator(pyParser.DecoratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(pyParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(pyParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(pyParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(pyParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(pyParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(pyParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code KeywordArg}
	 * labeled alternative in {@link pyParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterKeywordArg(pyParser.KeywordArgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code KeywordArg}
	 * labeled alternative in {@link pyParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitKeywordArg(pyParser.KeywordArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PositionalArg}
	 * labeled alternative in {@link pyParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterPositionalArg(pyParser.PositionalArgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PositionalArg}
	 * labeled alternative in {@link pyParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitPositionalArg(pyParser.PositionalArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockRule}
	 * labeled alternative in {@link pyParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlockRule(pyParser.BlockRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockRule}
	 * labeled alternative in {@link pyParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlockRule(pyParser.BlockRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(pyParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(pyParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnS}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnS(pyParser.ReturnSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnS}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnS(pyParser.ReturnSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIf(pyParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIf(pyParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code For}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFor(pyParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code For}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFor(pyParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code While}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile(pyParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code While}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile(pyParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(pyParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(pyParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(pyParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link pyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(pyParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#assignTarget}.
	 * @param ctx the parse tree
	 */
	void enterAssignTarget(pyParser.AssignTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#assignTarget}.
	 * @param ctx the parse tree
	 */
	void exitAssignTarget(pyParser.AssignTargetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleAssign}
	 * labeled alternative in {@link pyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterSimpleAssign(pyParser.SimpleAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleAssign}
	 * labeled alternative in {@link pyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitSimpleAssign(pyParser.SimpleAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AugAssign}
	 * labeled alternative in {@link pyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAugAssign(pyParser.AugAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AugAssign}
	 * labeled alternative in {@link pyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAugAssign(pyParser.AugAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(pyParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(pyParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(pyParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(pyParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(pyParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(pyParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(pyParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(pyParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DictE}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDictE(pyParser.DictEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DictE}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDictE(pyParser.DictEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(pyParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(pyParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IsNotExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIsNotExpr(pyParser.IsNotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IsNotExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIsNotExpr(pyParser.IsNotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(pyParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(pyParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubscriptExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpr(pyParser.SubscriptExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubscriptExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpr(pyParser.SubscriptExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListE}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterListE(pyParser.ListEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListE}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitListE(pyParser.ListEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PowExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(pyParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PowExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(pyParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NameExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNameExpr(pyParser.NameExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NameExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNameExpr(pyParser.NameExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(pyParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(pyParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(pyParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(pyParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(pyParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(pyParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpr(pyParser.CompareExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpr(pyParser.CompareExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IsExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIsExpr(pyParser.IsExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IsExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIsExpr(pyParser.IsExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(pyParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(pyParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(pyParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(pyParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(pyParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(pyParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(pyParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(pyParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpr(pyParser.TernaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpr(pyParser.TernaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpr(pyParser.MethodCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpr(pyParser.MethodCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AttrExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAttrExpr(pyParser.AttrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AttrExpr}
	 * labeled alternative in {@link pyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAttrExpr(pyParser.AttrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PlainList}
	 * labeled alternative in {@link pyParser#listExpr}.
	 * @param ctx the parse tree
	 */
	void enterPlainList(pyParser.PlainListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PlainList}
	 * labeled alternative in {@link pyParser#listExpr}.
	 * @param ctx the parse tree
	 */
	void exitPlainList(pyParser.PlainListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListComp}
	 * labeled alternative in {@link pyParser#listExpr}.
	 * @param ctx the parse tree
	 */
	void enterListComp(pyParser.ListCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListComp}
	 * labeled alternative in {@link pyParser#listExpr}.
	 * @param ctx the parse tree
	 */
	void exitListComp(pyParser.ListCompContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#dictExpr}.
	 * @param ctx the parse tree
	 */
	void enterDictExpr(pyParser.DictExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#dictExpr}.
	 * @param ctx the parse tree
	 */
	void exitDictExpr(pyParser.DictExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#dictItem}.
	 * @param ctx the parse tree
	 */
	void enterDictItem(pyParser.DictItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#dictItem}.
	 * @param ctx the parse tree
	 */
	void exitDictItem(pyParser.DictItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link pyParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(pyParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link pyParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(pyParser.LiteralContext ctx);
}