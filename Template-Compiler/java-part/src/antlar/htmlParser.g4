parser grammar htmlParser;

options { tokenVocab=htmlLexer; }


template
    : content* EOF
    ;

content
    : doctype
    | html_element
    | style_element
    | jinja_statement
    | jinja_expression
    | TEXT
    ;


html_element
    : html_open_tag content* html_close_tag   # NormalElement
    | html_self_closing_tag                   # SelfClosingElement
    | html_void_element                       # VoidElement
    ;

html_open_tag
    : LT IDENTIFIER attribute_list? GT
    ;

html_close_tag
    : LT SLASH IDENTIFIER GT
    ;

html_self_closing_tag
    : LT IDENTIFIER attribute_list? SLASH GT
    ;


attribute_list
    : attribute+
    ;

attribute
    : IDENTIFIER EQ attribute_value
    ;

attribute_value
    : STRING              # StringAttribute
    | jinja_expression    # JinjaAttribute
    ;



jinja_statement
    : JINJA_STMT_OPEN jinja_stmt_body JINJA_STMT_CLOSE
    ;

jinja_stmt_body
    : extends_stmt      # ExtendsStatement
    | block_stmt        # BlockStatement
    | endblock_stmt     # EndblockStatement
    | for_stmt          # ForStatement
    | endfor_stmt       # EndforStatement
    ;

extends_stmt
    : EXTENDS STRING_J
    ;

block_stmt
    : BLOCK IDENTIFIER_J
    ;

endblock_stmt
    : ENDBLOCK
    ;

for_stmt
    : FOR IDENTIFIER_J IN IDENTIFIER_J
    ;

endfor_stmt
    : ENDFOR
    ;



jinja_expression
    : JINJA_EXPR_OPEN expression JINJA_EXPR_CLOSE
    ;

expression
    : function_call    # FunctionExpression
    | variable         # VariableExpression
    | STRING_E         # StringExpression
    ;

function_call
    : IDENTIFIER_E LPAREN argument_list? RPAREN
    ;

argument_list
    : argument (COMMA argument)*
    ;

argument
    : STRING_E                       # StringArgument
    | IDENTIFIER_E EQ_J expression   # NamedArgument
    | expression                     # ExpressionArgument
    ;

variable
    : IDENTIFIER_E (DOT_J IDENTIFIER_E)*
    ;



style_element
    : STYLE_OPEN css_stylesheet STYLE_CLOSE
    ;

css_stylesheet
    : css_rule*
    ;

css_rule
    : css_selector_list
      LBRACE
      css_declaration*
      RBRACE
    ;

css_selector_list
    : css_selector (COMMA_CSS css_selector)*
    ;

css_selector
    : IDENTIFIER_CSS                 # ElementSelector
    | DOT_CSS IDENTIFIER_CSS         # ClassSelector
    | HASH IDENTIFIER_CSS            # IdSelector
    | STAR                           # UniversalSelector
    ;

css_declaration
    : css_property COLON css_value SEMI
    ;

css_property
    : IDENTIFIER_CSS
    ;

css_value
    : css_value_item+
    ;

css_value_item
    : IDENTIFIER_CSS     # IdentifierValue
    | NUMBER_CSS         # NumberValue
    | STRING_CSS         # StringValue
    | COLOR              # ColorValue
    ;



html_void_element
    : LT VOID_TAG attribute_list? GT
    ;

doctype
    : DOCTYPE
    ;