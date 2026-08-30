// Generated from E:/aya/study/مشروع كومبايلر/‏‏‏‏untitled1 - نسخة - نسخة/Template-Compiler/java-part/src/antlar/htmlParser.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by the {@code NormalElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void enterNormalElement(htmlParser.NormalElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NormalElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void exitNormalElement(htmlParser.NormalElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelfClosingElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void enterSelfClosingElement(htmlParser.SelfClosingElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelfClosingElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void exitSelfClosingElement(htmlParser.SelfClosingElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void enterVoidElement(htmlParser.VoidElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidElement}
	 * labeled alternative in {@link htmlParser#html_element}.
	 * @param ctx the parse tree
	 */
	void exitVoidElement(htmlParser.VoidElementContext ctx);
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
	 * Enter a parse tree produced by the {@code StringAttribute}
	 * labeled alternative in {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void enterStringAttribute(htmlParser.StringAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringAttribute}
	 * labeled alternative in {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void exitStringAttribute(htmlParser.StringAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JinjaAttribute}
	 * labeled alternative in {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void enterJinjaAttribute(htmlParser.JinjaAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JinjaAttribute}
	 * labeled alternative in {@link htmlParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void exitJinjaAttribute(htmlParser.JinjaAttributeContext ctx);
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
	 * Enter a parse tree produced by the {@code ExtendsStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void enterExtendsStatement(htmlParser.ExtendsStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExtendsStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void exitExtendsStatement(htmlParser.ExtendsStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(htmlParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(htmlParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EndblockStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void enterEndblockStatement(htmlParser.EndblockStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EndblockStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void exitEndblockStatement(htmlParser.EndblockStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(htmlParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(htmlParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EndforStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void enterEndforStatement(htmlParser.EndforStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EndforStatement}
	 * labeled alternative in {@link htmlParser#jinja_stmt_body}.
	 * @param ctx the parse tree
	 */
	void exitEndforStatement(htmlParser.EndforStatementContext ctx);
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
	 * Enter a parse tree produced by the {@code FunctionExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpression(htmlParser.FunctionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpression(htmlParser.FunctionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(htmlParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(htmlParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(htmlParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringExpression}
	 * labeled alternative in {@link htmlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(htmlParser.StringExpressionContext ctx);
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
	 * Enter a parse tree produced by the {@code StringArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterStringArgument(htmlParser.StringArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitStringArgument(htmlParser.StringArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NamedArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterNamedArgument(htmlParser.NamedArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NamedArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitNamedArgument(htmlParser.NamedArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArgument(htmlParser.ExpressionArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionArgument}
	 * labeled alternative in {@link htmlParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArgument(htmlParser.ExpressionArgumentContext ctx);
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
	 * Enter a parse tree produced by {@link htmlParser#css_stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterCss_stylesheet(htmlParser.Css_stylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#css_stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitCss_stylesheet(htmlParser.Css_stylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#css_rule}.
	 * @param ctx the parse tree
	 */
	void enterCss_rule(htmlParser.Css_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#css_rule}.
	 * @param ctx the parse tree
	 */
	void exitCss_rule(htmlParser.Css_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#css_selector_list}.
	 * @param ctx the parse tree
	 */
	void enterCss_selector_list(htmlParser.Css_selector_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#css_selector_list}.
	 * @param ctx the parse tree
	 */
	void exitCss_selector_list(htmlParser.Css_selector_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ElementSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterElementSelector(htmlParser.ElementSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ElementSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitElementSelector(htmlParser.ElementSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterClassSelector(htmlParser.ClassSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitClassSelector(htmlParser.ClassSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterIdSelector(htmlParser.IdSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitIdSelector(htmlParser.IdSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UniversalSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void enterUniversalSelector(htmlParser.UniversalSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UniversalSelector}
	 * labeled alternative in {@link htmlParser#css_selector}.
	 * @param ctx the parse tree
	 */
	void exitUniversalSelector(htmlParser.UniversalSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#css_declaration}.
	 * @param ctx the parse tree
	 */
	void enterCss_declaration(htmlParser.Css_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#css_declaration}.
	 * @param ctx the parse tree
	 */
	void exitCss_declaration(htmlParser.Css_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#css_property}.
	 * @param ctx the parse tree
	 */
	void enterCss_property(htmlParser.Css_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#css_property}.
	 * @param ctx the parse tree
	 */
	void exitCss_property(htmlParser.Css_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link htmlParser#css_value}.
	 * @param ctx the parse tree
	 */
	void enterCss_value(htmlParser.Css_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link htmlParser#css_value}.
	 * @param ctx the parse tree
	 */
	void exitCss_value(htmlParser.Css_valueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierValue(htmlParser.IdentifierValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierValue(htmlParser.IdentifierValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void enterNumberValue(htmlParser.NumberValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void exitNumberValue(htmlParser.NumberValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(htmlParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(htmlParser.StringValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ColorValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void enterColorValue(htmlParser.ColorValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ColorValue}
	 * labeled alternative in {@link htmlParser#css_value_item}.
	 * @param ctx the parse tree
	 */
	void exitColorValue(htmlParser.ColorValueContext ctx);
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