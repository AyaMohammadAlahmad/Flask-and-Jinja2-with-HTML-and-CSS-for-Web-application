// Generated from E:/aya/study/مشروع كومبايلر/‏‏‏‏untitled1 - نسخة - نسخة/Template-Compiler/java-part/src/antlar/htmlParser.g4 by ANTLR 4.13.2
package antlar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link htmlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface htmlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link htmlParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(htmlParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(htmlParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NormalElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalElement(htmlParser.NormalElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelfClosingElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfClosingElement(htmlParser.SelfClosingElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidElement(htmlParser.VoidElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#html_open_tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtml_open_tag(htmlParser.Html_open_tagContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#html_close_tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtml_close_tag(htmlParser.Html_close_tagContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#html_self_closing_tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtml_self_closing_tag(htmlParser.Html_self_closing_tagContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#attribute_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_list(htmlParser.Attribute_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(htmlParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringAttribute}
	 * labeled alternative in {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAttribute(htmlParser.StringAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JinjaAttribute}
	 * labeled alternative in {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaAttribute(htmlParser.JinjaAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#jinja_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_statement(htmlParser.Jinja_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExtendsStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsStatement(htmlParser.ExtendsStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(htmlParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EndblockStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndblockStatement(htmlParser.EndblockStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(htmlParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EndforStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndforStatement(htmlParser.EndforStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#extends_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtends_stmt(htmlParser.Extends_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#block_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_stmt(htmlParser.Block_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#endblock_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndblock_stmt(htmlParser.Endblock_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(htmlParser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#endfor_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndfor_stmt(htmlParser.Endfor_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#jinja_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_expression(htmlParser.Jinja_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionExpression(htmlParser.FunctionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VariableExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpression(htmlParser.VariableExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpression(htmlParser.StringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(htmlParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_list(htmlParser.Argument_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringArgument(htmlParser.StringArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NamedArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedArgument(htmlParser.NamedArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArgument(htmlParser.ExpressionArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(htmlParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#style_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyle_element(htmlParser.Style_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#css_stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCss_stylesheet(htmlParser.Css_stylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#css_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCss_rule(htmlParser.Css_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#css_selector_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCss_selector_list(htmlParser.Css_selector_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ElementSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementSelector(htmlParser.ElementSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassSelector(htmlParser.ClassSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdSelector(htmlParser.IdSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniversalSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversalSelector(htmlParser.UniversalSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#css_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCss_declaration(htmlParser.Css_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#css_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCss_property(htmlParser.Css_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#css_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCss_value(htmlParser.Css_valueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierValue(htmlParser.IdentifierValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberValue(htmlParser.NumberValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(htmlParser.StringValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ColorValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorValue(htmlParser.ColorValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#html_void_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtml_void_element(htmlParser.Html_void_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#doctype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoctype(htmlParser.DoctypeContext ctx);
}