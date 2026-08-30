// Generated from E:/aya/study/مترجمات/‏‏‏‏untitled1 - نسخة - نسخة/src/antlar/htmlParser.g4 by ANTLR 4.13.2
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
	 * Visit a parse tree produced by {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtml_element(htmlParser.Html_elementContext ctx);
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
	 * Visit a parse tree produced by {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_value(htmlParser.Attribute_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#jinja_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_statement(htmlParser.Jinja_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_stmt_body(htmlParser.Jinja_stmt_bodyContext ctx);
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
	 * Visit a parse tree produced by {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(htmlParser.ExpressionContext ctx);
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
	 * Visit a parse tree produced by {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(htmlParser.ArgumentContext ctx);
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