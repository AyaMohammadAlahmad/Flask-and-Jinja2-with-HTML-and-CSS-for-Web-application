lexer grammar htmlLexer;


DOCTYPE
    : '<!doctype' [ \t]+ 'html' '>'
    ;


STYLE_OPEN
    : '<style' .*? '>' -> pushMode(CSS)
    ;


JINJA_STMT_OPEN
    : '{%' -> pushMode(JINJA_STMT)
    ;

JINJA_EXPR_OPEN
    : '{{' -> pushMode(JINJA_EXPR)
    ;


LT
    : '<' -> pushMode(TAG)
    ;


TEXT
    : ~[<{]+
    ;


WS
    : [ \t\r\n]+ -> skip
    ;

mode TAG;

VOID_TAG
    : 'img'
    | 'br'
    | 'hr'
    | 'meta'
    | 'link'
    | 'input'
    ;

GT
    : '>' -> popMode
    ;

SLASH
    : '/'
    ;

EQ
    : '='
    ;

IDENTIFIER
    : [a-zA-Z][a-zA-Z0-9]*
    ;

STRING
    : '"'  (~["\r\n])* '"'
    | '\'' (~['\r\n])* '\''
    ;

TAG_WS
    : [ \t\r\n]+ -> skip
    ;

mode JINJA_STMT;

JINJA_STMT_CLOSE
    : '%}' -> popMode
    ;

EXTENDS
    : 'extends'
    ;

BLOCK
    : 'block'
    ;

ENDBLOCK
    : 'endblock'
    ;

FOR
    : 'for'
    ;

ENDFOR
    : 'endfor'
    ;

IN
    : 'in'
    ;

IDENTIFIER_J
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

STRING_J
    : '"' (~["\r\n])* '"'
    | '\'' (~['\r\n])* '\''
    ;

JINJA_WS
    : [ \t\r\n]+ -> skip
    ;

mode JINJA_EXPR;

JINJA_EXPR_CLOSE
    : '}}' -> popMode
    ;

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

COMMA
    : ','
    ;

DOT_J
    : '.'
    ;

EQ_J
    : '='
    ;

IDENTIFIER_E
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

STRING_E
    : '"' (~["\r\n])* '"'
    | '\'' (~['\r\n])* '\''
    ;

NUMBER
    : [0-9]+
    ;

JINJA_EXPR_WS
    : [ \t\r\n]+ -> skip
    ;

mode CSS;

STYLE_CLOSE
    : '</style>' -> popMode
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

COLON
    : ':'
    ;

SEMI
    : ';'
    ;

COMMA_CSS
    : ','
    ;

DOT_CSS
    : '.'
    ;

HASH
    : '#'
    ;

STAR
    : '*'
    ;

COLOR
    : '#' [a-fA-F0-9]+
    ;

NUMBER_CSS
    : [0-9]+ ('.' [0-9]+)?
      ('px' | '%' | 'em' | 'rem' | 'vh' | 'vw')?
    ;

IDENTIFIER_CSS
    : [a-zA-Z_][a-zA-Z0-9_-]*
    ;

STRING_CSS
    : '"' (~["\r\n])* '"'
    | '\'' (~['\r\n])* '\''
    ;

CSS_COMMENT
    : '/*' .*? '*/' -> skip
    ;

CSS_WS
    : [ \t\r\n]+ -> skip
    ;