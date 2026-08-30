// Generated from E:/aya/study/مشروع كومبايلر/‏‏‏‏untitled1 - نسخة - نسخة/Template-Compiler/java-part/src/antlar/htmlParser.g4 by ANTLR 4.13.2
package antlar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class htmlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOCTYPE=1, STYLE_OPEN=2, JINJA_STMT_OPEN=3, JINJA_EXPR_OPEN=4, LT=5, TEXT=6, 
		WS=7, VOID_TAG=8, GT=9, SLASH=10, EQ=11, IDENTIFIER=12, STRING=13, TAG_WS=14, 
		JINJA_STMT_CLOSE=15, EXTENDS=16, BLOCK=17, ENDBLOCK=18, FOR=19, ENDFOR=20, 
		IN=21, IDENTIFIER_J=22, STRING_J=23, JINJA_WS=24, JINJA_EXPR_CLOSE=25, 
		LPAREN=26, RPAREN=27, COMMA=28, DOT_J=29, EQ_J=30, IDENTIFIER_E=31, STRING_E=32, 
		NUMBER=33, JINJA_EXPR_WS=34, STYLE_CLOSE=35, LBRACE=36, RBRACE=37, COLON=38, 
		SEMI=39, COMMA_CSS=40, DOT_CSS=41, HASH=42, STAR=43, COLOR=44, NUMBER_CSS=45, 
		IDENTIFIER_CSS=46, STRING_CSS=47, CSS_COMMENT=48, CSS_WS=49;
	public static final int
		RULE_template = 0, RULE_content = 1, RULE_html_element = 2, RULE_html_open_tag = 3, 
		RULE_html_close_tag = 4, RULE_html_self_closing_tag = 5, RULE_attribute_list = 6, 
		RULE_attribute = 7, RULE_attribute_value = 8, RULE_jinja_statement = 9, 
		RULE_jinja_stmt_body = 10, RULE_extends_stmt = 11, RULE_block_stmt = 12, 
		RULE_endblock_stmt = 13, RULE_for_stmt = 14, RULE_endfor_stmt = 15, RULE_jinja_expression = 16, 
		RULE_expression = 17, RULE_function_call = 18, RULE_argument_list = 19, 
		RULE_argument = 20, RULE_variable = 21, RULE_style_element = 22, RULE_css_stylesheet = 23, 
		RULE_css_rule = 24, RULE_css_selector_list = 25, RULE_css_selector = 26, 
		RULE_css_declaration = 27, RULE_css_property = 28, RULE_css_value = 29, 
		RULE_css_value_item = 30, RULE_html_void_element = 31, RULE_doctype = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"template", "content", "html_element", "html_open_tag", "html_close_tag", 
			"html_self_closing_tag", "attribute_list", "attribute", "attribute_value", 
			"jinja_statement", "jinja_stmt_body", "extends_stmt", "block_stmt", "endblock_stmt", 
			"for_stmt", "endfor_stmt", "jinja_expression", "expression", "function_call", 
			"argument_list", "argument", "variable", "style_element", "css_stylesheet", 
			"css_rule", "css_selector_list", "css_selector", "css_declaration", "css_property", 
			"css_value", "css_value_item", "html_void_element", "doctype"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'{%'", "'{{'", "'<'", null, null, null, "'>'", "'/'", 
			null, null, null, null, "'%}'", "'extends'", "'block'", "'endblock'", 
			"'for'", "'endfor'", "'in'", null, null, null, "'}}'", "'('", "')'", 
			null, null, null, null, null, null, null, "'</style>'", "'{'", "'}'", 
			"':'", "';'", null, null, "'#'", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DOCTYPE", "STYLE_OPEN", "JINJA_STMT_OPEN", "JINJA_EXPR_OPEN", 
			"LT", "TEXT", "WS", "VOID_TAG", "GT", "SLASH", "EQ", "IDENTIFIER", "STRING", 
			"TAG_WS", "JINJA_STMT_CLOSE", "EXTENDS", "BLOCK", "ENDBLOCK", "FOR", 
			"ENDFOR", "IN", "IDENTIFIER_J", "STRING_J", "JINJA_WS", "JINJA_EXPR_CLOSE", 
			"LPAREN", "RPAREN", "COMMA", "DOT_J", "EQ_J", "IDENTIFIER_E", "STRING_E", 
			"NUMBER", "JINJA_EXPR_WS", "STYLE_CLOSE", "LBRACE", "RBRACE", "COLON", 
			"SEMI", "COMMA_CSS", "DOT_CSS", "HASH", "STAR", "COLOR", "NUMBER_CSS", 
			"IDENTIFIER_CSS", "STRING_CSS", "CSS_COMMENT", "CSS_WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "htmlParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public htmlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TemplateContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(htmlParser.EOF, 0); }
		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}
		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class,i);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitTemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126L) != 0)) {
				{
				{
				setState(66);
				content();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContentContext extends ParserRuleContext {
		public DoctypeContext doctype() {
			return getRuleContext(DoctypeContext.class,0);
		}
		public Html_elementContext html_element() {
			return getRuleContext(Html_elementContext.class,0);
		}
		public Style_elementContext style_element() {
			return getRuleContext(Style_elementContext.class,0);
		}
		public Jinja_statementContext jinja_statement() {
			return getRuleContext(Jinja_statementContext.class,0);
		}
		public Jinja_expressionContext jinja_expression() {
			return getRuleContext(Jinja_expressionContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(htmlParser.TEXT, 0); }
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_content);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOCTYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				doctype();
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				html_element();
				}
				break;
			case STYLE_OPEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				style_element();
				}
				break;
			case JINJA_STMT_OPEN:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				jinja_statement();
				}
				break;
			case JINJA_EXPR_OPEN:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				jinja_expression();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Html_elementContext extends ParserRuleContext {
		public Html_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_element; }
	 
		public Html_elementContext() { }
		public void copyFrom(Html_elementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VoidElementContext extends Html_elementContext {
		public Html_void_elementContext html_void_element() {
			return getRuleContext(Html_void_elementContext.class,0);
		}
		public VoidElementContext(Html_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterVoidElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitVoidElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitVoidElement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelfClosingElementContext extends Html_elementContext {
		public Html_self_closing_tagContext html_self_closing_tag() {
			return getRuleContext(Html_self_closing_tagContext.class,0);
		}
		public SelfClosingElementContext(Html_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterSelfClosingElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitSelfClosingElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitSelfClosingElement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NormalElementContext extends Html_elementContext {
		public Html_open_tagContext html_open_tag() {
			return getRuleContext(Html_open_tagContext.class,0);
		}
		public Html_close_tagContext html_close_tag() {
			return getRuleContext(Html_close_tagContext.class,0);
		}
		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}
		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class,i);
		}
		public NormalElementContext(Html_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterNormalElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitNormalElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitNormalElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Html_elementContext html_element() throws RecognitionException {
		Html_elementContext _localctx = new Html_elementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_html_element);
		try {
			int _alt;
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new NormalElementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				html_open_tag();
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(83);
						content();
						}
						} 
					}
					setState(88);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				setState(89);
				html_close_tag();
				}
				break;
			case 2:
				_localctx = new SelfClosingElementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				html_self_closing_tag();
				}
				break;
			case 3:
				_localctx = new VoidElementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				html_void_element();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Html_open_tagContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(htmlParser.LT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(htmlParser.IDENTIFIER, 0); }
		public TerminalNode GT() { return getToken(htmlParser.GT, 0); }
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public Html_open_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_open_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterHtml_open_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitHtml_open_tag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitHtml_open_tag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Html_open_tagContext html_open_tag() throws RecognitionException {
		Html_open_tagContext _localctx = new Html_open_tagContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_html_open_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(LT);
			setState(96);
			match(IDENTIFIER);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(97);
				attribute_list();
				}
			}

			setState(100);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Html_close_tagContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(htmlParser.LT, 0); }
		public TerminalNode SLASH() { return getToken(htmlParser.SLASH, 0); }
		public TerminalNode IDENTIFIER() { return getToken(htmlParser.IDENTIFIER, 0); }
		public TerminalNode GT() { return getToken(htmlParser.GT, 0); }
		public Html_close_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_close_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterHtml_close_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitHtml_close_tag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitHtml_close_tag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Html_close_tagContext html_close_tag() throws RecognitionException {
		Html_close_tagContext _localctx = new Html_close_tagContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_html_close_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(LT);
			setState(103);
			match(SLASH);
			setState(104);
			match(IDENTIFIER);
			setState(105);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Html_self_closing_tagContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(htmlParser.LT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(htmlParser.IDENTIFIER, 0); }
		public TerminalNode SLASH() { return getToken(htmlParser.SLASH, 0); }
		public TerminalNode GT() { return getToken(htmlParser.GT, 0); }
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public Html_self_closing_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_self_closing_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterHtml_self_closing_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitHtml_self_closing_tag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitHtml_self_closing_tag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Html_self_closing_tagContext html_self_closing_tag() throws RecognitionException {
		Html_self_closing_tagContext _localctx = new Html_self_closing_tagContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_html_self_closing_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(LT);
			setState(108);
			match(IDENTIFIER);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(109);
				attribute_list();
				}
			}

			setState(112);
			match(SLASH);
			setState(113);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Attribute_listContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public Attribute_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterAttribute_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitAttribute_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitAttribute_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attribute_listContext attribute_list() throws RecognitionException {
		Attribute_listContext _localctx = new Attribute_listContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_attribute_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(115);
				attribute();
				}
				}
				setState(118); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(htmlParser.IDENTIFIER, 0); }
		public TerminalNode EQ() { return getToken(htmlParser.EQ, 0); }
		public Attribute_valueContext attribute_value() {
			return getRuleContext(Attribute_valueContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(IDENTIFIER);
			setState(121);
			match(EQ);
			setState(122);
			attribute_value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Attribute_valueContext extends ParserRuleContext {
		public Attribute_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_value; }
	 
		public Attribute_valueContext() { }
		public void copyFrom(Attribute_valueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringAttributeContext extends Attribute_valueContext {
		public TerminalNode STRING() { return getToken(htmlParser.STRING, 0); }
		public StringAttributeContext(Attribute_valueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterStringAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitStringAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitStringAttribute(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class JinjaAttributeContext extends Attribute_valueContext {
		public Jinja_expressionContext jinja_expression() {
			return getRuleContext(Jinja_expressionContext.class,0);
		}
		public JinjaAttributeContext(Attribute_valueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterJinjaAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitJinjaAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitJinjaAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attribute_valueContext attribute_value() throws RecognitionException {
		Attribute_valueContext _localctx = new Attribute_valueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_attribute_value);
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new StringAttributeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				match(STRING);
				}
				break;
			case JINJA_EXPR_OPEN:
				_localctx = new JinjaAttributeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				jinja_expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_statementContext extends ParserRuleContext {
		public TerminalNode JINJA_STMT_OPEN() { return getToken(htmlParser.JINJA_STMT_OPEN, 0); }
		public Jinja_stmt_bodyContext jinja_stmt_body() {
			return getRuleContext(Jinja_stmt_bodyContext.class,0);
		}
		public TerminalNode JINJA_STMT_CLOSE() { return getToken(htmlParser.JINJA_STMT_CLOSE, 0); }
		public Jinja_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jinja_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterJinja_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitJinja_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitJinja_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Jinja_statementContext jinja_statement() throws RecognitionException {
		Jinja_statementContext _localctx = new Jinja_statementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_jinja_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(JINJA_STMT_OPEN);
			setState(129);
			jinja_stmt_body();
			setState(130);
			match(JINJA_STMT_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_stmt_bodyContext extends ParserRuleContext {
		public Jinja_stmt_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jinja_stmt_body; }
	 
		public Jinja_stmt_bodyContext() { }
		public void copyFrom(Jinja_stmt_bodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EndblockStatementContext extends Jinja_stmt_bodyContext {
		public Endblock_stmtContext endblock_stmt() {
			return getRuleContext(Endblock_stmtContext.class,0);
		}
		public EndblockStatementContext(Jinja_stmt_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterEndblockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitEndblockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitEndblockStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatementContext extends Jinja_stmt_bodyContext {
		public Block_stmtContext block_stmt() {
			return getRuleContext(Block_stmtContext.class,0);
		}
		public BlockStatementContext(Jinja_stmt_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExtendsStatementContext extends Jinja_stmt_bodyContext {
		public Extends_stmtContext extends_stmt() {
			return getRuleContext(Extends_stmtContext.class,0);
		}
		public ExtendsStatementContext(Jinja_stmt_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterExtendsStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitExtendsStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitExtendsStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends Jinja_stmt_bodyContext {
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public ForStatementContext(Jinja_stmt_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EndforStatementContext extends Jinja_stmt_bodyContext {
		public Endfor_stmtContext endfor_stmt() {
			return getRuleContext(Endfor_stmtContext.class,0);
		}
		public EndforStatementContext(Jinja_stmt_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterEndforStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitEndforStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitEndforStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Jinja_stmt_bodyContext jinja_stmt_body() throws RecognitionException {
		Jinja_stmt_bodyContext _localctx = new Jinja_stmt_bodyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_jinja_stmt_body);
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXTENDS:
				_localctx = new ExtendsStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				extends_stmt();
				}
				break;
			case BLOCK:
				_localctx = new BlockStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				block_stmt();
				}
				break;
			case ENDBLOCK:
				_localctx = new EndblockStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				endblock_stmt();
				}
				break;
			case FOR:
				_localctx = new ForStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(135);
				for_stmt();
				}
				break;
			case ENDFOR:
				_localctx = new EndforStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(136);
				endfor_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Extends_stmtContext extends ParserRuleContext {
		public TerminalNode EXTENDS() { return getToken(htmlParser.EXTENDS, 0); }
		public TerminalNode STRING_J() { return getToken(htmlParser.STRING_J, 0); }
		public Extends_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extends_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterExtends_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitExtends_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitExtends_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Extends_stmtContext extends_stmt() throws RecognitionException {
		Extends_stmtContext _localctx = new Extends_stmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_extends_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(EXTENDS);
			setState(140);
			match(STRING_J);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Block_stmtContext extends ParserRuleContext {
		public TerminalNode BLOCK() { return getToken(htmlParser.BLOCK, 0); }
		public TerminalNode IDENTIFIER_J() { return getToken(htmlParser.IDENTIFIER_J, 0); }
		public Block_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterBlock_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitBlock_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitBlock_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Block_stmtContext block_stmt() throws RecognitionException {
		Block_stmtContext _localctx = new Block_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_block_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(BLOCK);
			setState(143);
			match(IDENTIFIER_J);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Endblock_stmtContext extends ParserRuleContext {
		public TerminalNode ENDBLOCK() { return getToken(htmlParser.ENDBLOCK, 0); }
		public Endblock_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endblock_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterEndblock_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitEndblock_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitEndblock_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Endblock_stmtContext endblock_stmt() throws RecognitionException {
		Endblock_stmtContext _localctx = new Endblock_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_endblock_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(ENDBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class For_stmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(htmlParser.FOR, 0); }
		public List<TerminalNode> IDENTIFIER_J() { return getTokens(htmlParser.IDENTIFIER_J); }
		public TerminalNode IDENTIFIER_J(int i) {
			return getToken(htmlParser.IDENTIFIER_J, i);
		}
		public TerminalNode IN() { return getToken(htmlParser.IN, 0); }
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterFor_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitFor_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitFor_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_for_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(FOR);
			setState(148);
			match(IDENTIFIER_J);
			setState(149);
			match(IN);
			setState(150);
			match(IDENTIFIER_J);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Endfor_stmtContext extends ParserRuleContext {
		public TerminalNode ENDFOR() { return getToken(htmlParser.ENDFOR, 0); }
		public Endfor_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endfor_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterEndfor_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitEndfor_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitEndfor_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Endfor_stmtContext endfor_stmt() throws RecognitionException {
		Endfor_stmtContext _localctx = new Endfor_stmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_endfor_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(ENDFOR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_expressionContext extends ParserRuleContext {
		public TerminalNode JINJA_EXPR_OPEN() { return getToken(htmlParser.JINJA_EXPR_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode JINJA_EXPR_CLOSE() { return getToken(htmlParser.JINJA_EXPR_CLOSE, 0); }
		public Jinja_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jinja_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterJinja_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitJinja_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitJinja_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Jinja_expressionContext jinja_expression() throws RecognitionException {
		Jinja_expressionContext _localctx = new Jinja_expressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_jinja_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(JINJA_EXPR_OPEN);
			setState(155);
			expression();
			setState(156);
			match(JINJA_EXPR_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionExpressionContext extends ExpressionContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public FunctionExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitFunctionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringExpressionContext extends ExpressionContext {
		public TerminalNode STRING_E() { return getToken(htmlParser.STRING_E, 0); }
		public StringExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterStringExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitStringExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitStringExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VariableExpressionContext extends ExpressionContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterVariableExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitVariableExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitVariableExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expression);
		try {
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new FunctionExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				function_call();
				}
				break;
			case 2:
				_localctx = new VariableExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				variable();
				}
				break;
			case 3:
				_localctx = new StringExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				match(STRING_E);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER_E() { return getToken(htmlParser.IDENTIFIER_E, 0); }
		public TerminalNode LPAREN() { return getToken(htmlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(htmlParser.RPAREN, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(IDENTIFIER_E);
			setState(164);
			match(LPAREN);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER_E || _la==STRING_E) {
				{
				setState(165);
				argument_list();
				}
			}

			setState(168);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Argument_listContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(htmlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(htmlParser.COMMA, i);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterArgument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitArgument_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitArgument_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			argument();
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(171);
				match(COMMA);
				setState(172);
				argument();
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	 
		public ArgumentContext() { }
		public void copyFrom(ArgumentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NamedArgumentContext extends ArgumentContext {
		public TerminalNode IDENTIFIER_E() { return getToken(htmlParser.IDENTIFIER_E, 0); }
		public TerminalNode EQ_J() { return getToken(htmlParser.EQ_J, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NamedArgumentContext(ArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterNamedArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitNamedArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitNamedArgument(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionArgumentContext extends ArgumentContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionArgumentContext(ArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterExpressionArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitExpressionArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitExpressionArgument(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringArgumentContext extends ArgumentContext {
		public TerminalNode STRING_E() { return getToken(htmlParser.STRING_E, 0); }
		public StringArgumentContext(ArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterStringArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitStringArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitStringArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_argument);
		try {
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new StringArgumentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				match(STRING_E);
				}
				break;
			case 2:
				_localctx = new NamedArgumentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(IDENTIFIER_E);
				setState(180);
				match(EQ_J);
				setState(181);
				expression();
				}
				break;
			case 3:
				_localctx = new ExpressionArgumentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(182);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER_E() { return getTokens(htmlParser.IDENTIFIER_E); }
		public TerminalNode IDENTIFIER_E(int i) {
			return getToken(htmlParser.IDENTIFIER_E, i);
		}
		public List<TerminalNode> DOT_J() { return getTokens(htmlParser.DOT_J); }
		public TerminalNode DOT_J(int i) {
			return getToken(htmlParser.DOT_J, i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(IDENTIFIER_E);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT_J) {
				{
				{
				setState(186);
				match(DOT_J);
				setState(187);
				match(IDENTIFIER_E);
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Style_elementContext extends ParserRuleContext {
		public TerminalNode STYLE_OPEN() { return getToken(htmlParser.STYLE_OPEN, 0); }
		public Css_stylesheetContext css_stylesheet() {
			return getRuleContext(Css_stylesheetContext.class,0);
		}
		public TerminalNode STYLE_CLOSE() { return getToken(htmlParser.STYLE_CLOSE, 0); }
		public Style_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_style_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterStyle_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitStyle_element(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitStyle_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Style_elementContext style_element() throws RecognitionException {
		Style_elementContext _localctx = new Style_elementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_style_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(STYLE_OPEN);
			setState(194);
			css_stylesheet();
			setState(195);
			match(STYLE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_stylesheetContext extends ParserRuleContext {
		public List<Css_ruleContext> css_rule() {
			return getRuleContexts(Css_ruleContext.class);
		}
		public Css_ruleContext css_rule(int i) {
			return getRuleContext(Css_ruleContext.class,i);
		}
		public Css_stylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_stylesheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterCss_stylesheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitCss_stylesheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitCss_stylesheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_stylesheetContext css_stylesheet() throws RecognitionException {
		Css_stylesheetContext _localctx = new Css_stylesheetContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_css_stylesheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 85761906966528L) != 0)) {
				{
				{
				setState(197);
				css_rule();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_ruleContext extends ParserRuleContext {
		public Css_selector_listContext css_selector_list() {
			return getRuleContext(Css_selector_listContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(htmlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(htmlParser.RBRACE, 0); }
		public List<Css_declarationContext> css_declaration() {
			return getRuleContexts(Css_declarationContext.class);
		}
		public Css_declarationContext css_declaration(int i) {
			return getRuleContext(Css_declarationContext.class,i);
		}
		public Css_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterCss_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitCss_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitCss_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_ruleContext css_rule() throws RecognitionException {
		Css_ruleContext _localctx = new Css_ruleContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_css_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			css_selector_list();
			setState(204);
			match(LBRACE);
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER_CSS) {
				{
				{
				setState(205);
				css_declaration();
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(211);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_selector_listContext extends ParserRuleContext {
		public List<Css_selectorContext> css_selector() {
			return getRuleContexts(Css_selectorContext.class);
		}
		public Css_selectorContext css_selector(int i) {
			return getRuleContext(Css_selectorContext.class,i);
		}
		public List<TerminalNode> COMMA_CSS() { return getTokens(htmlParser.COMMA_CSS); }
		public TerminalNode COMMA_CSS(int i) {
			return getToken(htmlParser.COMMA_CSS, i);
		}
		public Css_selector_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_selector_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterCss_selector_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitCss_selector_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitCss_selector_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_selector_listContext css_selector_list() throws RecognitionException {
		Css_selector_listContext _localctx = new Css_selector_listContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_css_selector_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			css_selector();
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA_CSS) {
				{
				{
				setState(214);
				match(COMMA_CSS);
				setState(215);
				css_selector();
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_selectorContext extends ParserRuleContext {
		public Css_selectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_selector; }
	 
		public Css_selectorContext() { }
		public void copyFrom(Css_selectorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UniversalSelectorContext extends Css_selectorContext {
		public TerminalNode STAR() { return getToken(htmlParser.STAR, 0); }
		public UniversalSelectorContext(Css_selectorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterUniversalSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitUniversalSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitUniversalSelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdSelectorContext extends Css_selectorContext {
		public TerminalNode HASH() { return getToken(htmlParser.HASH, 0); }
		public TerminalNode IDENTIFIER_CSS() { return getToken(htmlParser.IDENTIFIER_CSS, 0); }
		public IdSelectorContext(Css_selectorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterIdSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitIdSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitIdSelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ClassSelectorContext extends Css_selectorContext {
		public TerminalNode DOT_CSS() { return getToken(htmlParser.DOT_CSS, 0); }
		public TerminalNode IDENTIFIER_CSS() { return getToken(htmlParser.IDENTIFIER_CSS, 0); }
		public ClassSelectorContext(Css_selectorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterClassSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitClassSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitClassSelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ElementSelectorContext extends Css_selectorContext {
		public TerminalNode IDENTIFIER_CSS() { return getToken(htmlParser.IDENTIFIER_CSS, 0); }
		public ElementSelectorContext(Css_selectorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterElementSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitElementSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitElementSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_selectorContext css_selector() throws RecognitionException {
		Css_selectorContext _localctx = new Css_selectorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_css_selector);
		try {
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER_CSS:
				_localctx = new ElementSelectorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				match(IDENTIFIER_CSS);
				}
				break;
			case DOT_CSS:
				_localctx = new ClassSelectorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				match(DOT_CSS);
				setState(223);
				match(IDENTIFIER_CSS);
				}
				break;
			case HASH:
				_localctx = new IdSelectorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				match(HASH);
				setState(225);
				match(IDENTIFIER_CSS);
				}
				break;
			case STAR:
				_localctx = new UniversalSelectorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(226);
				match(STAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_declarationContext extends ParserRuleContext {
		public Css_propertyContext css_property() {
			return getRuleContext(Css_propertyContext.class,0);
		}
		public TerminalNode COLON() { return getToken(htmlParser.COLON, 0); }
		public Css_valueContext css_value() {
			return getRuleContext(Css_valueContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(htmlParser.SEMI, 0); }
		public Css_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterCss_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitCss_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitCss_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_declarationContext css_declaration() throws RecognitionException {
		Css_declarationContext _localctx = new Css_declarationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_css_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			css_property();
			setState(230);
			match(COLON);
			setState(231);
			css_value();
			setState(232);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_propertyContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER_CSS() { return getToken(htmlParser.IDENTIFIER_CSS, 0); }
		public Css_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterCss_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitCss_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitCss_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_propertyContext css_property() throws RecognitionException {
		Css_propertyContext _localctx = new Css_propertyContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_css_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(IDENTIFIER_CSS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_valueContext extends ParserRuleContext {
		public List<Css_value_itemContext> css_value_item() {
			return getRuleContexts(Css_value_itemContext.class);
		}
		public Css_value_itemContext css_value_item(int i) {
			return getRuleContext(Css_value_itemContext.class,i);
		}
		public Css_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterCss_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitCss_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitCss_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_valueContext css_value() throws RecognitionException {
		Css_valueContext _localctx = new Css_valueContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_css_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(236);
				css_value_item();
				}
				}
				setState(239); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 263882790666240L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Css_value_itemContext extends ParserRuleContext {
		public Css_value_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_css_value_item; }
	 
		public Css_value_itemContext() { }
		public void copyFrom(Css_value_itemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberValueContext extends Css_value_itemContext {
		public TerminalNode NUMBER_CSS() { return getToken(htmlParser.NUMBER_CSS, 0); }
		public NumberValueContext(Css_value_itemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterNumberValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitNumberValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitNumberValue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ColorValueContext extends Css_value_itemContext {
		public TerminalNode COLOR() { return getToken(htmlParser.COLOR, 0); }
		public ColorValueContext(Css_value_itemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterColorValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitColorValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitColorValue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierValueContext extends Css_value_itemContext {
		public TerminalNode IDENTIFIER_CSS() { return getToken(htmlParser.IDENTIFIER_CSS, 0); }
		public IdentifierValueContext(Css_value_itemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterIdentifierValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitIdentifierValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitIdentifierValue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringValueContext extends Css_value_itemContext {
		public TerminalNode STRING_CSS() { return getToken(htmlParser.STRING_CSS, 0); }
		public StringValueContext(Css_value_itemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterStringValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitStringValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitStringValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Css_value_itemContext css_value_item() throws RecognitionException {
		Css_value_itemContext _localctx = new Css_value_itemContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_css_value_item);
		try {
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER_CSS:
				_localctx = new IdentifierValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				match(IDENTIFIER_CSS);
				}
				break;
			case NUMBER_CSS:
				_localctx = new NumberValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				match(NUMBER_CSS);
				}
				break;
			case STRING_CSS:
				_localctx = new StringValueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				match(STRING_CSS);
				}
				break;
			case COLOR:
				_localctx = new ColorValueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(244);
				match(COLOR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Html_void_elementContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(htmlParser.LT, 0); }
		public TerminalNode VOID_TAG() { return getToken(htmlParser.VOID_TAG, 0); }
		public TerminalNode GT() { return getToken(htmlParser.GT, 0); }
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public Html_void_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_void_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterHtml_void_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitHtml_void_element(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitHtml_void_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Html_void_elementContext html_void_element() throws RecognitionException {
		Html_void_elementContext _localctx = new Html_void_elementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_html_void_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(LT);
			setState(248);
			match(VOID_TAG);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(249);
				attribute_list();
				}
			}

			setState(252);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DoctypeContext extends ParserRuleContext {
		public TerminalNode DOCTYPE() { return getToken(htmlParser.DOCTYPE, 0); }
		public DoctypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doctype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterDoctype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitDoctype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitDoctype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoctypeContext doctype() throws RecognitionException {
		DoctypeContext _localctx = new DoctypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_doctype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(DOCTYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u00011\u0101\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0001\u0000\u0005\u0000D\b\u0000"+
		"\n\u0000\f\u0000G\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001Q\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0005\u0002U\b\u0002\n\u0002\f\u0002X\t\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002^\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003c\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005o\b\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0004\u0006u\b\u0006\u000b\u0006"+
		"\f\u0006v\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0003\b\u007f\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u008a\b\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u00a2\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00a7"+
		"\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u00ae\b\u0013\n\u0013\f\u0013\u00b1\t\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00b8\b\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0005\u0015\u00bd\b\u0015\n\u0015\f\u0015\u00c0"+
		"\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0005"+
		"\u0017\u00c7\b\u0017\n\u0017\f\u0017\u00ca\t\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0005\u0018\u00cf\b\u0018\n\u0018\f\u0018\u00d2\t\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u00d9"+
		"\b\u0019\n\u0019\f\u0019\u00dc\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u00e4\b\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0004\u001d\u00ee\b\u001d\u000b\u001d\f\u001d\u00ef\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u00f6\b\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u00fb\b\u001f\u0001\u001f\u0001"+
		"\u001f\u0001 \u0001 \u0001 \u0000\u0000!\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:"+
		"<>@\u0000\u0000\u0102\u0000E\u0001\u0000\u0000\u0000\u0002P\u0001\u0000"+
		"\u0000\u0000\u0004]\u0001\u0000\u0000\u0000\u0006_\u0001\u0000\u0000\u0000"+
		"\bf\u0001\u0000\u0000\u0000\nk\u0001\u0000\u0000\u0000\ft\u0001\u0000"+
		"\u0000\u0000\u000ex\u0001\u0000\u0000\u0000\u0010~\u0001\u0000\u0000\u0000"+
		"\u0012\u0080\u0001\u0000\u0000\u0000\u0014\u0089\u0001\u0000\u0000\u0000"+
		"\u0016\u008b\u0001\u0000\u0000\u0000\u0018\u008e\u0001\u0000\u0000\u0000"+
		"\u001a\u0091\u0001\u0000\u0000\u0000\u001c\u0093\u0001\u0000\u0000\u0000"+
		"\u001e\u0098\u0001\u0000\u0000\u0000 \u009a\u0001\u0000\u0000\u0000\""+
		"\u00a1\u0001\u0000\u0000\u0000$\u00a3\u0001\u0000\u0000\u0000&\u00aa\u0001"+
		"\u0000\u0000\u0000(\u00b7\u0001\u0000\u0000\u0000*\u00b9\u0001\u0000\u0000"+
		"\u0000,\u00c1\u0001\u0000\u0000\u0000.\u00c8\u0001\u0000\u0000\u00000"+
		"\u00cb\u0001\u0000\u0000\u00002\u00d5\u0001\u0000\u0000\u00004\u00e3\u0001"+
		"\u0000\u0000\u00006\u00e5\u0001\u0000\u0000\u00008\u00ea\u0001\u0000\u0000"+
		"\u0000:\u00ed\u0001\u0000\u0000\u0000<\u00f5\u0001\u0000\u0000\u0000>"+
		"\u00f7\u0001\u0000\u0000\u0000@\u00fe\u0001\u0000\u0000\u0000BD\u0003"+
		"\u0002\u0001\u0000CB\u0001\u0000\u0000\u0000DG\u0001\u0000\u0000\u0000"+
		"EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000FH\u0001\u0000\u0000"+
		"\u0000GE\u0001\u0000\u0000\u0000HI\u0005\u0000\u0000\u0001I\u0001\u0001"+
		"\u0000\u0000\u0000JQ\u0003@ \u0000KQ\u0003\u0004\u0002\u0000LQ\u0003,"+
		"\u0016\u0000MQ\u0003\u0012\t\u0000NQ\u0003 \u0010\u0000OQ\u0005\u0006"+
		"\u0000\u0000PJ\u0001\u0000\u0000\u0000PK\u0001\u0000\u0000\u0000PL\u0001"+
		"\u0000\u0000\u0000PM\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000"+
		"PO\u0001\u0000\u0000\u0000Q\u0003\u0001\u0000\u0000\u0000RV\u0003\u0006"+
		"\u0003\u0000SU\u0003\u0002\u0001\u0000TS\u0001\u0000\u0000\u0000UX\u0001"+
		"\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000"+
		"WY\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0003\b\u0004\u0000"+
		"Z^\u0001\u0000\u0000\u0000[^\u0003\n\u0005\u0000\\^\u0003>\u001f\u0000"+
		"]R\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]\\\u0001\u0000\u0000"+
		"\u0000^\u0005\u0001\u0000\u0000\u0000_`\u0005\u0005\u0000\u0000`b\u0005"+
		"\f\u0000\u0000ac\u0003\f\u0006\u0000ba\u0001\u0000\u0000\u0000bc\u0001"+
		"\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0005\t\u0000\u0000e\u0007"+
		"\u0001\u0000\u0000\u0000fg\u0005\u0005\u0000\u0000gh\u0005\n\u0000\u0000"+
		"hi\u0005\f\u0000\u0000ij\u0005\t\u0000\u0000j\t\u0001\u0000\u0000\u0000"+
		"kl\u0005\u0005\u0000\u0000ln\u0005\f\u0000\u0000mo\u0003\f\u0006\u0000"+
		"nm\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000op\u0001\u0000\u0000"+
		"\u0000pq\u0005\n\u0000\u0000qr\u0005\t\u0000\u0000r\u000b\u0001\u0000"+
		"\u0000\u0000su\u0003\u000e\u0007\u0000ts\u0001\u0000\u0000\u0000uv\u0001"+
		"\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000"+
		"w\r\u0001\u0000\u0000\u0000xy\u0005\f\u0000\u0000yz\u0005\u000b\u0000"+
		"\u0000z{\u0003\u0010\b\u0000{\u000f\u0001\u0000\u0000\u0000|\u007f\u0005"+
		"\r\u0000\u0000}\u007f\u0003 \u0010\u0000~|\u0001\u0000\u0000\u0000~}\u0001"+
		"\u0000\u0000\u0000\u007f\u0011\u0001\u0000\u0000\u0000\u0080\u0081\u0005"+
		"\u0003\u0000\u0000\u0081\u0082\u0003\u0014\n\u0000\u0082\u0083\u0005\u000f"+
		"\u0000\u0000\u0083\u0013\u0001\u0000\u0000\u0000\u0084\u008a\u0003\u0016"+
		"\u000b\u0000\u0085\u008a\u0003\u0018\f\u0000\u0086\u008a\u0003\u001a\r"+
		"\u0000\u0087\u008a\u0003\u001c\u000e\u0000\u0088\u008a\u0003\u001e\u000f"+
		"\u0000\u0089\u0084\u0001\u0000\u0000\u0000\u0089\u0085\u0001\u0000\u0000"+
		"\u0000\u0089\u0086\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000"+
		"\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u008a\u0015\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0005\u0010\u0000\u0000\u008c\u008d\u0005\u0017\u0000"+
		"\u0000\u008d\u0017\u0001\u0000\u0000\u0000\u008e\u008f\u0005\u0011\u0000"+
		"\u0000\u008f\u0090\u0005\u0016\u0000\u0000\u0090\u0019\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0005\u0012\u0000\u0000\u0092\u001b\u0001\u0000\u0000"+
		"\u0000\u0093\u0094\u0005\u0013\u0000\u0000\u0094\u0095\u0005\u0016\u0000"+
		"\u0000\u0095\u0096\u0005\u0015\u0000\u0000\u0096\u0097\u0005\u0016\u0000"+
		"\u0000\u0097\u001d\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0014\u0000"+
		"\u0000\u0099\u001f\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u0004\u0000"+
		"\u0000\u009b\u009c\u0003\"\u0011\u0000\u009c\u009d\u0005\u0019\u0000\u0000"+
		"\u009d!\u0001\u0000\u0000\u0000\u009e\u00a2\u0003$\u0012\u0000\u009f\u00a2"+
		"\u0003*\u0015\u0000\u00a0\u00a2\u0005 \u0000\u0000\u00a1\u009e\u0001\u0000"+
		"\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a2#\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\u001f\u0000"+
		"\u0000\u00a4\u00a6\u0005\u001a\u0000\u0000\u00a5\u00a7\u0003&\u0013\u0000"+
		"\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u001b\u0000\u0000"+
		"\u00a9%\u0001\u0000\u0000\u0000\u00aa\u00af\u0003(\u0014\u0000\u00ab\u00ac"+
		"\u0005\u001c\u0000\u0000\u00ac\u00ae\u0003(\u0014\u0000\u00ad\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000\u0000\u00af\u00ad\u0001"+
		"\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\'\u0001\u0000"+
		"\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b2\u00b8\u0005 \u0000"+
		"\u0000\u00b3\u00b4\u0005\u001f\u0000\u0000\u00b4\u00b5\u0005\u001e\u0000"+
		"\u0000\u00b5\u00b8\u0003\"\u0011\u0000\u00b6\u00b8\u0003\"\u0011\u0000"+
		"\u00b7\u00b2\u0001\u0000\u0000\u0000\u00b7\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b6\u0001\u0000\u0000\u0000\u00b8)\u0001\u0000\u0000\u0000\u00b9"+
		"\u00be\u0005\u001f\u0000\u0000\u00ba\u00bb\u0005\u001d\u0000\u0000\u00bb"+
		"\u00bd\u0005\u001f\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd"+
		"\u00c0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be"+
		"\u00bf\u0001\u0000\u0000\u0000\u00bf+\u0001\u0000\u0000\u0000\u00c0\u00be"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\u0002\u0000\u0000\u00c2\u00c3"+
		"\u0003.\u0017\u0000\u00c3\u00c4\u0005#\u0000\u0000\u00c4-\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c7\u00030\u0018\u0000\u00c6\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9/\u0001\u0000\u0000\u0000"+
		"\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00cc\u00032\u0019\u0000\u00cc"+
		"\u00d0\u0005$\u0000\u0000\u00cd\u00cf\u00036\u001b\u0000\u00ce\u00cd\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d2\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005"+
		"%\u0000\u0000\u00d41\u0001\u0000\u0000\u0000\u00d5\u00da\u00034\u001a"+
		"\u0000\u00d6\u00d7\u0005(\u0000\u0000\u00d7\u00d9\u00034\u001a\u0000\u00d8"+
		"\u00d6\u0001\u0000\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da"+
		"\u00d8\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db"+
		"3\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00e4"+
		"\u0005.\u0000\u0000\u00de\u00df\u0005)\u0000\u0000\u00df\u00e4\u0005."+
		"\u0000\u0000\u00e0\u00e1\u0005*\u0000\u0000\u00e1\u00e4\u0005.\u0000\u0000"+
		"\u00e2\u00e4\u0005+\u0000\u0000\u00e3\u00dd\u0001\u0000\u0000\u0000\u00e3"+
		"\u00de\u0001\u0000\u0000\u0000\u00e3\u00e0\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e2\u0001\u0000\u0000\u0000\u00e45\u0001\u0000\u0000\u0000\u00e5\u00e6"+
		"\u00038\u001c\u0000\u00e6\u00e7\u0005&\u0000\u0000\u00e7\u00e8\u0003:"+
		"\u001d\u0000\u00e8\u00e9\u0005\'\u0000\u0000\u00e97\u0001\u0000\u0000"+
		"\u0000\u00ea\u00eb\u0005.\u0000\u0000\u00eb9\u0001\u0000\u0000\u0000\u00ec"+
		"\u00ee\u0003<\u001e\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef"+
		"\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0"+
		"\u0001\u0000\u0000\u0000\u00f0;\u0001\u0000\u0000\u0000\u00f1\u00f6\u0005"+
		".\u0000\u0000\u00f2\u00f6\u0005-\u0000\u0000\u00f3\u00f6\u0005/\u0000"+
		"\u0000\u00f4\u00f6\u0005,\u0000\u0000\u00f5\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f2\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f4\u0001\u0000\u0000\u0000\u00f6=\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f8\u0005\u0005\u0000\u0000\u00f8\u00fa\u0005\b\u0000\u0000\u00f9\u00fb"+
		"\u0003\f\u0006\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001"+
		"\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005"+
		"\t\u0000\u0000\u00fd?\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005\u0001"+
		"\u0000\u0000\u00ffA\u0001\u0000\u0000\u0000\u0015EPV]bnv~\u0089\u00a1"+
		"\u00a6\u00af\u00b7\u00be\u00c8\u00d0\u00da\u00e3\u00ef\u00f5\u00fa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}