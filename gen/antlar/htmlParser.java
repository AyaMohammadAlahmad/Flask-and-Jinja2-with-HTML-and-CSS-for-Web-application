// Generated from E:/aya/study/مترجمات/‏‏‏‏untitled1 - نسخة - نسخة/src/antlar/htmlParser.g4 by ANTLR 4.13.2
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
		LPAREN=26, RPAREN=27, COMMA=28, DOT=29, EQ_J=30, IDENTIFIER_E=31, STRING_E=32, 
		NUMBER=33, JINJA_EXPR_WS=34, STYLE_CLOSE=35, CSS_TEXT=36, CSS_WS=37;
	public static final int
		RULE_template = 0, RULE_content = 1, RULE_html_element = 2, RULE_html_open_tag = 3, 
		RULE_html_close_tag = 4, RULE_html_self_closing_tag = 5, RULE_attribute_list = 6, 
		RULE_attribute = 7, RULE_attribute_value = 8, RULE_jinja_statement = 9, 
		RULE_jinja_stmt_body = 10, RULE_extends_stmt = 11, RULE_block_stmt = 12, 
		RULE_endblock_stmt = 13, RULE_for_stmt = 14, RULE_endfor_stmt = 15, RULE_jinja_expression = 16, 
		RULE_expression = 17, RULE_function_call = 18, RULE_argument_list = 19, 
		RULE_argument = 20, RULE_variable = 21, RULE_style_element = 22, RULE_html_void_element = 23, 
		RULE_doctype = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"template", "content", "html_element", "html_open_tag", "html_close_tag", 
			"html_self_closing_tag", "attribute_list", "attribute", "attribute_value", 
			"jinja_statement", "jinja_stmt_body", "extends_stmt", "block_stmt", "endblock_stmt", 
			"for_stmt", "endfor_stmt", "jinja_expression", "expression", "function_call", 
			"argument_list", "argument", "variable", "style_element", "html_void_element", 
			"doctype"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'{%'", "'{{'", "'<'", null, null, null, "'>'", "'/'", 
			null, null, null, null, "'%}'", "'extends'", "'block'", "'endblock'", 
			"'for'", "'endfor'", "'in'", null, null, null, "'}}'", "'('", "')'", 
			"','", "'.'", null, null, null, null, null, "'</style>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DOCTYPE", "STYLE_OPEN", "JINJA_STMT_OPEN", "JINJA_EXPR_OPEN", 
			"LT", "TEXT", "WS", "VOID_TAG", "GT", "SLASH", "EQ", "IDENTIFIER", "STRING", 
			"TAG_WS", "JINJA_STMT_CLOSE", "EXTENDS", "BLOCK", "ENDBLOCK", "FOR", 
			"ENDFOR", "IN", "IDENTIFIER_J", "STRING_J", "JINJA_WS", "JINJA_EXPR_CLOSE", 
			"LPAREN", "RPAREN", "COMMA", "DOT", "EQ_J", "IDENTIFIER_E", "STRING_E", 
			"NUMBER", "JINJA_EXPR_WS", "STYLE_CLOSE", "CSS_TEXT", "CSS_WS"
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
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126L) != 0)) {
				{
				{
				setState(50);
				content();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
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
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOCTYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				doctype();
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				html_element();
				}
				break;
			case STYLE_OPEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				style_element();
				}
				break;
			case JINJA_STMT_OPEN:
				enterOuterAlt(_localctx, 4);
				{
				setState(61);
				jinja_statement();
				}
				break;
			case JINJA_EXPR_OPEN:
				enterOuterAlt(_localctx, 5);
				{
				setState(62);
				jinja_expression();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 6);
				{
				setState(63);
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
		public Html_self_closing_tagContext html_self_closing_tag() {
			return getRuleContext(Html_self_closing_tagContext.class,0);
		}
		public Html_void_elementContext html_void_element() {
			return getRuleContext(Html_void_elementContext.class,0);
		}
		public Html_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterHtml_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitHtml_element(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitHtml_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Html_elementContext html_element() throws RecognitionException {
		Html_elementContext _localctx = new Html_elementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_html_element);
		try {
			int _alt;
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				html_open_tag();
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(67);
						content();
						}
						} 
					}
					setState(72);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				setState(73);
				html_close_tag();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				html_self_closing_tag();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
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
			setState(79);
			match(LT);
			setState(80);
			match(IDENTIFIER);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(81);
				attribute_list();
				}
			}

			setState(84);
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
			setState(86);
			match(LT);
			setState(87);
			match(SLASH);
			setState(88);
			match(IDENTIFIER);
			setState(89);
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
			setState(91);
			match(LT);
			setState(92);
			match(IDENTIFIER);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(93);
				attribute_list();
				}
			}

			setState(96);
			match(SLASH);
			setState(97);
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
			setState(100); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(99);
				attribute();
				}
				}
				setState(102); 
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
			setState(104);
			match(IDENTIFIER);
			setState(105);
			match(EQ);
			setState(106);
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
		public TerminalNode STRING() { return getToken(htmlParser.STRING, 0); }
		public Jinja_expressionContext jinja_expression() {
			return getRuleContext(Jinja_expressionContext.class,0);
		}
		public Attribute_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterAttribute_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitAttribute_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitAttribute_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attribute_valueContext attribute_value() throws RecognitionException {
		Attribute_valueContext _localctx = new Attribute_valueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_attribute_value);
		try {
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(STRING);
				}
				break;
			case JINJA_EXPR_OPEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
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
			setState(112);
			match(JINJA_STMT_OPEN);
			setState(113);
			jinja_stmt_body();
			setState(114);
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
		public Extends_stmtContext extends_stmt() {
			return getRuleContext(Extends_stmtContext.class,0);
		}
		public Block_stmtContext block_stmt() {
			return getRuleContext(Block_stmtContext.class,0);
		}
		public Endblock_stmtContext endblock_stmt() {
			return getRuleContext(Endblock_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public Endfor_stmtContext endfor_stmt() {
			return getRuleContext(Endfor_stmtContext.class,0);
		}
		public Jinja_stmt_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jinja_stmt_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterJinja_stmt_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitJinja_stmt_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitJinja_stmt_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Jinja_stmt_bodyContext jinja_stmt_body() throws RecognitionException {
		Jinja_stmt_bodyContext _localctx = new Jinja_stmt_bodyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_jinja_stmt_body);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXTENDS:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				extends_stmt();
				}
				break;
			case BLOCK:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				block_stmt();
				}
				break;
			case ENDBLOCK:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				endblock_stmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				for_stmt();
				}
				break;
			case ENDFOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
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
			setState(123);
			match(EXTENDS);
			setState(124);
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
			setState(126);
			match(BLOCK);
			setState(127);
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
			setState(129);
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
			setState(131);
			match(FOR);
			setState(132);
			match(IDENTIFIER_J);
			setState(133);
			match(IN);
			setState(134);
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
			setState(136);
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
			setState(138);
			match(JINJA_EXPR_OPEN);
			setState(139);
			expression();
			setState(140);
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
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expression);
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				function_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				variable();
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
			setState(146);
			match(IDENTIFIER_E);
			setState(147);
			match(LPAREN);
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER_E || _la==STRING_E) {
				{
				setState(148);
				argument_list();
				}
			}

			setState(151);
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
			setState(153);
			argument();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(154);
				match(COMMA);
				setState(155);
				argument();
				}
				}
				setState(160);
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
		public TerminalNode STRING_E() { return getToken(htmlParser.STRING_E, 0); }
		public TerminalNode IDENTIFIER_E() { return getToken(htmlParser.IDENTIFIER_E, 0); }
		public TerminalNode EQ_J() { return getToken(htmlParser.EQ_J, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof htmlParserListener ) ((htmlParserListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof htmlParserVisitor ) return ((htmlParserVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_argument);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_E:
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				match(STRING_E);
				}
				break;
			case IDENTIFIER_E:
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				match(IDENTIFIER_E);
				setState(163);
				match(EQ_J);
				setState(164);
				expression();
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
	public static class VariableContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER_E() { return getTokens(htmlParser.IDENTIFIER_E); }
		public TerminalNode IDENTIFIER_E(int i) {
			return getToken(htmlParser.IDENTIFIER_E, i);
		}
		public List<TerminalNode> DOT() { return getTokens(htmlParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(htmlParser.DOT, i);
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
			setState(167);
			match(IDENTIFIER_E);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(168);
				match(DOT);
				setState(169);
				match(IDENTIFIER_E);
				}
				}
				setState(174);
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
		public TerminalNode STYLE_CLOSE() { return getToken(htmlParser.STYLE_CLOSE, 0); }
		public List<TerminalNode> CSS_TEXT() { return getTokens(htmlParser.CSS_TEXT); }
		public TerminalNode CSS_TEXT(int i) {
			return getToken(htmlParser.CSS_TEXT, i);
		}
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(STYLE_OPEN);
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CSS_TEXT) {
				{
				{
				setState(176);
				match(CSS_TEXT);
				}
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(182);
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
		enterRule(_localctx, 46, RULE_html_void_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(LT);
			setState(185);
			match(VOID_TAG);
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(186);
				attribute_list();
				}
			}

			setState(189);
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
		enterRule(_localctx, 48, RULE_doctype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
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
		"\u0004\u0001%\u00c2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0001\u0000\u0005\u00004\b\u0000\n\u0000\f\u00007\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001A\b\u0001\u0001\u0002\u0001\u0002\u0005\u0002"+
		"E\b\u0002\n\u0002\f\u0002H\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002N\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003S\b\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005_\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0004\u0006e\b\u0006\u000b\u0006\f\u0006f\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0003\bo\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\nz\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u0091\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u0096\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0005\u0013\u009d\b\u0013\n\u0013\f\u0013\u00a0\t\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00a6\b\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00ab\b\u0015\n\u0015\f\u0015"+
		"\u00ae\t\u0015\u0001\u0016\u0001\u0016\u0005\u0016\u00b2\b\u0016\n\u0016"+
		"\f\u0016\u00b5\t\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u00bc\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0000\u0000\u0019\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0\u0000"+
		"\u0000\u00c0\u00005\u0001\u0000\u0000\u0000\u0002@\u0001\u0000\u0000\u0000"+
		"\u0004M\u0001\u0000\u0000\u0000\u0006O\u0001\u0000\u0000\u0000\bV\u0001"+
		"\u0000\u0000\u0000\n[\u0001\u0000\u0000\u0000\fd\u0001\u0000\u0000\u0000"+
		"\u000eh\u0001\u0000\u0000\u0000\u0010n\u0001\u0000\u0000\u0000\u0012p"+
		"\u0001\u0000\u0000\u0000\u0014y\u0001\u0000\u0000\u0000\u0016{\u0001\u0000"+
		"\u0000\u0000\u0018~\u0001\u0000\u0000\u0000\u001a\u0081\u0001\u0000\u0000"+
		"\u0000\u001c\u0083\u0001\u0000\u0000\u0000\u001e\u0088\u0001\u0000\u0000"+
		"\u0000 \u008a\u0001\u0000\u0000\u0000\"\u0090\u0001\u0000\u0000\u0000"+
		"$\u0092\u0001\u0000\u0000\u0000&\u0099\u0001\u0000\u0000\u0000(\u00a5"+
		"\u0001\u0000\u0000\u0000*\u00a7\u0001\u0000\u0000\u0000,\u00af\u0001\u0000"+
		"\u0000\u0000.\u00b8\u0001\u0000\u0000\u00000\u00bf\u0001\u0000\u0000\u0000"+
		"24\u0003\u0002\u0001\u000032\u0001\u0000\u0000\u000047\u0001\u0000\u0000"+
		"\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000068\u0001\u0000"+
		"\u0000\u000075\u0001\u0000\u0000\u000089\u0005\u0000\u0000\u00019\u0001"+
		"\u0001\u0000\u0000\u0000:A\u00030\u0018\u0000;A\u0003\u0004\u0002\u0000"+
		"<A\u0003,\u0016\u0000=A\u0003\u0012\t\u0000>A\u0003 \u0010\u0000?A\u0005"+
		"\u0006\u0000\u0000@:\u0001\u0000\u0000\u0000@;\u0001\u0000\u0000\u0000"+
		"@<\u0001\u0000\u0000\u0000@=\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000"+
		"\u0000@?\u0001\u0000\u0000\u0000A\u0003\u0001\u0000\u0000\u0000BF\u0003"+
		"\u0006\u0003\u0000CE\u0003\u0002\u0001\u0000DC\u0001\u0000\u0000\u0000"+
		"EH\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000"+
		"\u0000GI\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000IJ\u0003\b\u0004"+
		"\u0000JN\u0001\u0000\u0000\u0000KN\u0003\n\u0005\u0000LN\u0003.\u0017"+
		"\u0000MB\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000ML\u0001\u0000"+
		"\u0000\u0000N\u0005\u0001\u0000\u0000\u0000OP\u0005\u0005\u0000\u0000"+
		"PR\u0005\f\u0000\u0000QS\u0003\f\u0006\u0000RQ\u0001\u0000\u0000\u0000"+
		"RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0005\t\u0000\u0000"+
		"U\u0007\u0001\u0000\u0000\u0000VW\u0005\u0005\u0000\u0000WX\u0005\n\u0000"+
		"\u0000XY\u0005\f\u0000\u0000YZ\u0005\t\u0000\u0000Z\t\u0001\u0000\u0000"+
		"\u0000[\\\u0005\u0005\u0000\u0000\\^\u0005\f\u0000\u0000]_\u0003\f\u0006"+
		"\u0000^]\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0001\u0000"+
		"\u0000\u0000`a\u0005\n\u0000\u0000ab\u0005\t\u0000\u0000b\u000b\u0001"+
		"\u0000\u0000\u0000ce\u0003\u000e\u0007\u0000dc\u0001\u0000\u0000\u0000"+
		"ef\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000g\r\u0001\u0000\u0000\u0000hi\u0005\f\u0000\u0000ij\u0005\u000b"+
		"\u0000\u0000jk\u0003\u0010\b\u0000k\u000f\u0001\u0000\u0000\u0000lo\u0005"+
		"\r\u0000\u0000mo\u0003 \u0010\u0000nl\u0001\u0000\u0000\u0000nm\u0001"+
		"\u0000\u0000\u0000o\u0011\u0001\u0000\u0000\u0000pq\u0005\u0003\u0000"+
		"\u0000qr\u0003\u0014\n\u0000rs\u0005\u000f\u0000\u0000s\u0013\u0001\u0000"+
		"\u0000\u0000tz\u0003\u0016\u000b\u0000uz\u0003\u0018\f\u0000vz\u0003\u001a"+
		"\r\u0000wz\u0003\u001c\u000e\u0000xz\u0003\u001e\u000f\u0000yt\u0001\u0000"+
		"\u0000\u0000yu\u0001\u0000\u0000\u0000yv\u0001\u0000\u0000\u0000yw\u0001"+
		"\u0000\u0000\u0000yx\u0001\u0000\u0000\u0000z\u0015\u0001\u0000\u0000"+
		"\u0000{|\u0005\u0010\u0000\u0000|}\u0005\u0017\u0000\u0000}\u0017\u0001"+
		"\u0000\u0000\u0000~\u007f\u0005\u0011\u0000\u0000\u007f\u0080\u0005\u0016"+
		"\u0000\u0000\u0080\u0019\u0001\u0000\u0000\u0000\u0081\u0082\u0005\u0012"+
		"\u0000\u0000\u0082\u001b\u0001\u0000\u0000\u0000\u0083\u0084\u0005\u0013"+
		"\u0000\u0000\u0084\u0085\u0005\u0016\u0000\u0000\u0085\u0086\u0005\u0015"+
		"\u0000\u0000\u0086\u0087\u0005\u0016\u0000\u0000\u0087\u001d\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0005\u0014\u0000\u0000\u0089\u001f\u0001\u0000"+
		"\u0000\u0000\u008a\u008b\u0005\u0004\u0000\u0000\u008b\u008c\u0003\"\u0011"+
		"\u0000\u008c\u008d\u0005\u0019\u0000\u0000\u008d!\u0001\u0000\u0000\u0000"+
		"\u008e\u0091\u0003$\u0012\u0000\u008f\u0091\u0003*\u0015\u0000\u0090\u008e"+
		"\u0001\u0000\u0000\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091#\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0005\u001f\u0000\u0000\u0093\u0095\u0005"+
		"\u001a\u0000\u0000\u0094\u0096\u0003&\u0013\u0000\u0095\u0094\u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0005\u001b\u0000\u0000\u0098%\u0001\u0000\u0000"+
		"\u0000\u0099\u009e\u0003(\u0014\u0000\u009a\u009b\u0005\u001c\u0000\u0000"+
		"\u009b\u009d\u0003(\u0014\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d"+
		"\u00a0\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0001\u0000\u0000\u0000\u009f\'\u0001\u0000\u0000\u0000\u00a0\u009e"+
		"\u0001\u0000\u0000\u0000\u00a1\u00a6\u0005 \u0000\u0000\u00a2\u00a3\u0005"+
		"\u001f\u0000\u0000\u00a3\u00a4\u0005\u001e\u0000\u0000\u00a4\u00a6\u0003"+
		"\"\u0011\u0000\u00a5\u00a1\u0001\u0000\u0000\u0000\u00a5\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a6)\u0001\u0000\u0000\u0000\u00a7\u00ac\u0005\u001f\u0000"+
		"\u0000\u00a8\u00a9\u0005\u001d\u0000\u0000\u00a9\u00ab\u0005\u001f\u0000"+
		"\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u00ae\u0001\u0000\u0000"+
		"\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ad+\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000"+
		"\u00af\u00b3\u0005\u0002\u0000\u0000\u00b0\u00b2\u0005$\u0000\u0000\u00b1"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b6\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b7\u0005#\u0000\u0000\u00b7-\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005"+
		"\u0005\u0000\u0000\u00b9\u00bb\u0005\b\u0000\u0000\u00ba\u00bc\u0003\f"+
		"\u0006\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00be\u0005\t\u0000"+
		"\u0000\u00be/\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005\u0001\u0000\u0000"+
		"\u00c01\u0001\u0000\u0000\u0000\u00105@FMR^fny\u0090\u0095\u009e\u00a5"+
		"\u00ac\u00b3\u00bb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}