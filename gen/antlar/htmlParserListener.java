// Generated from E:/aya/study/مترجمات/‏‏‏‏untitled1 - نسخة - نسخة/src/antlar/htmlParser.g4 by ANTLR 4.13.2
package antlar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link htmlParser}.
 */
public interface htmlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link htmlParser#template}.
	 * @param ctx the parse tree
	 */
	void enterTemplate(htmlParser.TemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#template}.
	 * @param ctx the parse tree
	 */
	void exitTemplate(htmlParser.TemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(htmlParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(htmlParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void enterHtml_element(htmlParser.Html_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void exitHtml_element(htmlParser.Html_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#html_open_tag}.
	 * @param ctx the parse tree
	 */
	void enterHtml_open_tag(htmlParser.Html_open_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#html_open_tag}.
	 * @param ctx the parse tree
	 */
	void exitHtml_open_tag(htmlParser.Html_open_tagContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#html_close_tag}.
	 * @param ctx the parse tree
	 */
	void enterHtml_close_tag(htmlParser.Html_close_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#html_close_tag}.
	 * @param ctx the parse tree
	 */
	void exitHtml_close_tag(htmlParser.Html_close_tagContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#html_self_closing_tag}.
	 * @param ctx the parse tree
	 */
	void enterHtml_self_closing_tag(htmlParser.Html_self_closing_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#html_self_closing_tag}.
	 * @param ctx the parse tree
	 */
	void exitHtml_self_closing_tag(htmlParser.Html_self_closing_tagContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#attribute_list}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_list(htmlParser.Attribute_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#attribute_list}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_list(htmlParser.Attribute_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(htmlParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(htmlParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_value(htmlParser.Attribute_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_value(htmlParser.Attribute_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#jinja_statement}.
	 * @param ctx the parse tree
	 */
	void enterJinja_statement(htmlParser.Jinja_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#jinja_statement}.
	 * @param ctx the parse tree
	 */
	void exitJinja_statement(htmlParser.Jinja_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void enterJinja_stmt_body(htmlParser.Jinja_stmt_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void exitJinja_stmt_body(htmlParser.Jinja_stmt_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#extends_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExtends_stmt(htmlParser.Extends_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#extends_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExtends_stmt(htmlParser.Extends_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#block_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBlock_stmt(htmlParser.Block_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#block_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBlock_stmt(htmlParser.Block_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#endblock_stmt}.
	 * @param ctx the parse tree
	 */
	void enterEndblock_stmt(htmlParser.Endblock_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#endblock_stmt}.
	 * @param ctx the parse tree
	 */
	void exitEndblock_stmt(htmlParser.Endblock_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFor_stmt(htmlParser.For_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFor_stmt(htmlParser.For_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#endfor_stmt}.
	 * @param ctx the parse tree
	 */
	void enterEndfor_stmt(htmlParser.Endfor_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#endfor_stmt}.
	 * @param ctx the parse tree
	 */
	void exitEndfor_stmt(htmlParser.Endfor_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#jinja_expression}.
	 * @param ctx the parse tree
	 */
	void enterJinja_expression(htmlParser.Jinja_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#jinja_expression}.
	 * @param ctx the parse tree
	 */
	void exitJinja_expression(htmlParser.Jinja_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(htmlParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(htmlParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(htmlParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(htmlParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void enterArgument_list(htmlParser.Argument_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void exitArgument_list(htmlParser.Argument_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(htmlParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(htmlParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(htmlParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(htmlParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#style_element}.
	 * @param ctx the parse tree
	 */
	void enterStyle_element(htmlParser.Style_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#style_element}.
	 * @param ctx the parse tree
	 */
	void exitStyle_element(htmlParser.Style_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#html_void_element}.
	 * @param ctx the parse tree
	 */
	void enterHtml_void_element(htmlParser.Html_void_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#html_void_element}.
	 * @param ctx the parse tree
	 */
	void exitHtml_void_element(htmlParser.Html_void_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#doctype}.
	 * @param ctx the parse tree
	 */
	void enterDoctype(htmlParser.DoctypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#doctype}.
	 * @param ctx the parse tree
	 */
	void exitDoctype(htmlParser.DoctypeContext ctx);
}