parser grammar pyParser;

options { tokenVocab = pyLexer; }

@header {
package antlr;
}

/*
 * Parser for a Flask-oriented Python subset.
 *
 * Design goals
 * ────────────
 * 1. Flat, readable rule hierarchy — no unnecessary wrapper rules.
 * 2. Labelled alternatives (#Foo) only where the AST visitor needs to
 *    distinguish siblings of the same rule.  Thin "pass-through" rules
 *    (e.g. expression → conditionalExpr) carry no label.
 * 3. Standard expression precedence tower (low → high):
 *      or  →  and  →  not  →  comparison  →  additive  →
 *      multiplicative  →  power  →  unary  →  postfix  →  primary
 * 4. postfix covers both call and subscript so method chaining is natural.
 * 5. No INDENT/DEDENT — indentation is ignored entirely.
 */

/* ═══════════════════════════════════════════════════════════════════════════
   Program
   ═══════════════════════════════════════════════════════════════════════════ */
program
    : (NEWLINE | topLevel)* EOF                # ProgramRule
    ;
/*
 * A top-level element is an import, a (possibly decorated) function, or
 * any statement (assignment, if-block used as __name__ guard, etc.).
 * All alternatives must be labelled because one of them already is.
 */
topLevel
    : importStmt                # TopImport
    |decorator+ NEWLINE* functionDef   # TopDecoratedFunction
    | functionDef               # TopFunction
    | statement                 # TopStatement
    ;

/* ═══════════════════════════════════════════════════════════════════════════
   Imports
   ═══════════════════════════════════════════════════════════════════════════ */

importStmt
    : FROM Identifier IMPORT importList     # FromImport
    | IMPORT dottedName                     # SimpleImport
    ;

// e.g. flask  /  os.path  /  app
dottedName
    : Identifier (DOT Identifier)*
    ;

// e.g. Flask, render_template, request
importList
    : Identifier (COMMA Identifier)*
    ;

/* ═══════════════════════════════════════════════════════════════════════════
   Decorators & Functions
   ═══════════════════════════════════════════════════════════════════════════ */

/*
 * @app.route("/")
 * @app.route("/products/add", methods=["GET","POST"])
 */
decorator
    : AT dottedName (LPAREN argList? RPAREN)?
    ;

/*
 * def index():
 * def add_product():
 * def product_detail(product_id):
 */
functionDef
    : DEF Identifier LPAREN paramList? RPAREN COLON block
    ;

paramList
    : Identifier (COMMA Identifier)*
    ;
argList
    : argument (COMMA argument)* COMMA?
    ;

argument
    : Identifier ASSIGN expr    # KeywordArg
    | expr                      # PositionalArg
    ;

/* ═══════════════════════════════════════════════════════════════════════════
   Block  (indentation is ignored — just a sequence of statements)
   ═══════════════════════════════════════════════════════════════════════════ */
block
    : NEWLINE INDENT (statement | NEWLINE)+ DEDENT        # BlockRule
    ;

/* ═══════════════════════════════════════════════════════════════════════════
   Statements
   ═══════════════════════════════════════════════════════════════════════════ */

statement
    : assignment        # AssignStmt
    | returnStmt        # ReturnS       // "ReturnStmt" clashes with rule name returnStmt
    | ifStmt            # If           // "IfStmt"     clashes with rule name ifStmt
    | forStmt           # For          // "ForStmt"    clashes with rule name forStmt
   | whileStmt           #While
    | BREAK             # BreakStmt
    | expr              # ExprStmt      // bare call / method call as a statement
    ;

/*
 * Assignment target (lvalue) — the three forms valid in Flask apps:
 *   name              simple variable          x = 1
 *   name.attr         attribute write          app.config = …
 *   name[key]         subscript write          app.config['KEY'] = …
 *   name.attr[key]    chained form             app.config['KEY'] = …
 *
 * Kept as a dedicated rule so the visitor can pattern-match lvalues
 * without guarding against arbitrary expressions on the left of '='.
 * Augmented assignment stays restricted to simple Identifier targets.
 */
assignTarget
    : Identifier (DOT Identifier)* (LBRACK expr RBRACK)?
    ;

assignment
    : assignTarget ASSIGN expr                                                  # SimpleAssign
    | Identifier op=(PLUSASSIGN|MINUSASSIGN|MULASSIGN|DIVASSIGN|MODASSIGN|POWASSIGN)
      expr                                                                       # AugAssign
    ;

/*
 * return                     ← bare return (not needed often but harmless)
 * return expr
 * return expr, expr          ← tuple return (e.g. "Product not found", 404)
 */
returnStmt
    : RETURN (expr (COMMA expr)*)?
    ;

/* ═══════════════════════════════════════════════════════════════════════════
   Control flow
   ═══════════════════════════════════════════════════════════════════════════ */

ifStmt
    : IF expr COLON block
      (ELIF expr COLON block)*
      (ELSE COLON block)?
    ;

forStmt
    : FOR Identifier IN expr COLON block
    ;

 whileStmt
        : 'while' expr ':' block
        ;

/* ═══════════════════════════════════════════════════════════════════════════
   Expression tower  (lowest precedence first)
   ═══════════════════════════════════════════════════════════════════════════ */

/*
 * expr is the single public entry point for all expressions.
 * Using a single recursive rule with labelled alternatives gives ANTLR
 * enough information to build the correct precedence-climbing parser and
 * keeps the visitor extremely lean — one enterExpr / exitExpr with a
 * switch on the alternative label.
 *
 * Precedence (low → high, top → bottom of the alternatives list):
 *   ternary  (x if c else y)
 *   or
 *   and
 *   not
 *   comparison  (==  !=  <  >  <=  >=  is  is not)
 *   additive    (+  -)
 *   multiplicative  (*  /  %)
 *   power       (**)   — right-associative
 *   unary       (+ - not)
 *   postfix     (attr  call  subscript)  — left-associative chain
 *   primary     (atom)
 */
expr
    : expr DOT Identifier LPAREN argList? RPAREN                    # MethodCallExpr
    | expr DOT Identifier                                           # AttrExpr
    | expr LPAREN argList? RPAREN                                   # CallExpr
    | expr LBRACK expr RBRACK                                       # SubscriptExpr
    | expr POW expr                                                 # PowExpr
    | (PLUS | MINUS) expr                                           # UnaryExpr
    | expr (STAR | DIV | MOD) expr                                  # MulExpr
    | expr (PLUS | MINUS) expr                                      # AddExpr
    | expr (EQEQ | NEQ | LT | GT | LE | GE) expr                   # CompareExpr
    | expr IS NOT expr                                              # IsNotExpr
    | expr IS     expr                                              # IsExpr
    | NOT expr                                                      # NotExpr
    | expr AND expr                                                 # AndExpr
    | expr OR  expr                                                 # OrExpr
    | expr IF expr ELSE expr                                        # TernaryExpr
    | Identifier                                                    # NameExpr
    | literal                                                       # LiteralExpr
    | listExpr                                                      # ListE
    | dictExpr                                                      # DictE
    | LPAREN expr RPAREN                                            # ParenExpr
    ;
/* ═══════════════════════════════════════════════════════════════════════════
   Data structures
   ═══════════════════════════════════════════════════════════════════════════ */

listExpr
    : LBRACK (expr (COMMA expr)* COMMA?)? RBRACK    # PlainList
    | LBRACK expr FOR Identifier IN expr
              (IF expr)? RBRACK                      # ListComp
    ;

dictExpr
    : LBRACE (dictItem (COMMA dictItem)* COMMA?)? RBRACE
    ;

/*
 * Both string and expression keys are allowed so integer keys (e.g. "id")
 * and identifier keys work without special-casing.
 */
dictItem
    : key=expr COLON value=expr
    ;

/* ═══════════════════════════════════════════════════════════════════════════
   Literals
   ═══════════════════════════════════════════════════════════════════════════ */

literal
    : StringLiteral
    | NumberLiteral
    | NONE
    | TRUE
    | FALSE
    ;
