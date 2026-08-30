lexer grammar pyLexer;

@header {
package antlr;
}
options {
    superClass = PyLexerBase;
}

tokens { INDENT, DEDENT }
@ lexer::members {
    public static final int TAB_SIZE = 4;
}
/*
 * Lexer for a Flask-oriented Python subset.
 * Design notes:
 *   - Keywords before Identifier so they are not swallowed as names.
 *   - Longer operator tokens before shorter ones (e.g. '**=' before '**').
 *   - OR / AND / NOT are keywords, so they must appear before Identifier.
 *   - Whitespace, newlines and comments are all hidden; no INDENT/DEDENT.
 */

/* ── Keywords ──────────────────────────────────────────────────────────── */
FROM    : 'from';
IMPORT  : 'import';
DEF     : 'def';
RETURN  : 'return';
IF      : 'if';
ELIF    : 'elif';
ELSE    : 'else';
FOR     : 'for';
WHILE   : 'while';
IN      : 'in';
IS      : 'is';
NOT     : 'not';
AND     : 'and';
OR      : 'or';
BREAK   : 'break';
NONE    : 'None';
TRUE    : 'True';
FALSE   : 'False';

/* ── Compound / augmented operators  (longer strings first) ────────────── */
POWASSIGN   : '**=';
POW         : '**';
MULASSIGN   : '*=';
DIVASSIGN   : '/=';
MODASSIGN   : '%=';
PLUSASSIGN  : '+=';
MINUSASSIGN : '-=';

/* ── Comparison operators ───────────────────────────────────────────────── */
EQEQ : '==';
NEQ  : '!=';
GE   : '>=';
LE   : '<=';
GT   : '>';
LT   : '<';

/* ── Arithmetic operators ───────────────────────────────────────────────── */
STAR  : '*';
DIV   : '/';
MOD   : '%';
PLUS  : '+';
MINUS : '-';

/* ── Assignment / misc ──────────────────────────────────────────────────── */
ASSIGN : '=';
AT     : '@';
COMMA  : ',';
COLON  : ':';
DOT    : '.';

/* ── Brackets ───────────────────────────────────────────────────────────── */
LPAREN  : '(' { openBrace(); };
RPAREN  : ')' { closeBrace(); };
LBRACK  : '[' { openBrace(); };
RBRACK  : ']' { closeBrace(); };
LBRACE  : '{' { openBrace(); };
RBRACE  : '}' { closeBrace(); };

/* ── Literals ───────────────────────────────────────────────────────────── */

// Double- or single-quoted strings; basic escape sequences supported.
StringLiteral
    : '"'  (~["\\\r\n]  | '\\' .)* '"'
    | '\'' (~['\\\r\n]  | '\\' .)* '\''
    ;

// Integer, decimal, and scientific-notation numbers.
NumberLiteral
    : [0-9]+ '.' [0-9]* ([eE] [+-]? [0-9]+)?   // 1.  /  1.5  /  1.5e3
    | '.'     [0-9]+    ([eE] [+-]? [0-9]+)?   // .5  /  .5e3
    | [0-9]+            ([eE] [+-]? [0-9]+)?   // 1   /  1e3
    ;

/* ── Identifiers  (must come after all keyword tokens) ──────────────────── */
Identifier
    : [a-zA-Z_] [a-zA-Z0-9_]*
    ;

/* ── Skipped / hidden ───────────────────────────────────────────────────── */
CCOMMENT
     : '#' ~[\r\n]* -> channel(HIDDEN)
     ;
 MULTILINE_COMMENT
     : '"""' .*? '"""' -> channel(HIDDEN)
     ;

NEWLINE
    : ( {atStartOfInput()}?   SPACES
      | ( '\r'? '\n' | '\r' ) SPACES?
      )
      { onNewLine(); }
    ;
// fragment — يُستخدم داخل NEWLINE فقط
fragment SPACES
    : [ \t]+
    ;
// مسافات داخل السطر (بين التوكنز) — تُخفى
WS
    : [ \t]+ -> channel(HIDDEN)
    ;