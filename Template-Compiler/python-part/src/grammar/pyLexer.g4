lexer grammar pyLexer;


@header {
package antlr;
}
/* ================= Keywords ================= */
FROM    : 'from';
IMPORT  : 'import';
DEF     : 'def';
RETURN  : 'return';
IF      : 'if';
ELSE    : 'else';
ELIF    : 'elif';
FOR     : 'for';
IN      : 'in';
IS : 'is';
BREAK   : 'break';
NONE    : 'None';
 TRUE:'True';
 FALSE:'False';
/* ================= Decorators ================= */
AT      : '@';

/* ================= Operators ================= */
OR      : 'or' ;
AND : 'and';
NOT : 'not';
STAR    : '*' ;
DIV     : '/' ;
MOD     : '%' ;
PLUS    : '+' ;
MINUS   : '-' ;
ASSIGN  : '=' ;
EQEQ    : '==' ;
POW : '**' ;

GT  : '>';
LT  : '<';
GE  : '>=';
LE  : '<=';
NEQ : '!=';

PLUSASSIGN   : '+=' ;
MINUSASSIGN  : '-=' ;
MULASSIGN    : '*=' ;
DIVASSIGN    : '/=' ;
MODASSIGN    : '%=' ;
POWASSIGN    : '**=' ;

/* ================= Symbols ================= */
LPAREN  : '(';
RPAREN  : ')';
LBRACK  : '[';
RBRACK  : ']';
LBRACE  : '{';
RBRACE  : '}';
COMMA   : ',';
COLON   : ':';
DOT     : '.';

/* ================= Literals ================= */
StringLiteral
    : '"' (~["\\] | '\\' .)* '"'
    | '\'' (~['\\] | '\\' .)* '\''
    ;


NumberLiteral
    : [0-9]+
    ;

/* ================= Identifiers ================= */
Identifier
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

/* ================= Comments ================= */
COMMENT
    : '#' ~[\r\n]* -> channel(HIDDEN)
    ;

MULTILINE_COMMENT
    : '"""' .*? '"""' -> channel(HIDDEN)
    ;

NEWLINE
 : '\r'? '\n'-> channel(HIDDEN)
 ;



/* ================= Whitespace ================= */
WS
    : [ \t]+ -> channel(HIDDEN)
    ;
