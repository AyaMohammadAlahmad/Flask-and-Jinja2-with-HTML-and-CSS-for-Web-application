parser grammar pyParser;

options { tokenVocab=pyLexer; }

@header {
package antlr;
}

/* ================= Program ================= */
program
    : element* EOF                # ProgramRule
    ;

element
    : importStmt                  # ImportElement
    | decoratedFunction           # DecoratedFunctionElement
    | functionDef                 # FunctionElement
    | statement                   # StatementElement
    ;

/* ================= Imports ================= */
importStmt
    : FROM Identifier IMPORT importList   # FromImportStmt
    | IMPORT dottedName                   # Import
    ;

dottedName
    : Identifier (DOT Identifier)*        # DottedNameRule
    ;

importList
    : Identifier (COMMA Identifier)*      # ImportListRule
    ;

/* ================= Decorators ================= */
decoratedFunction
    : decorator+ functionDef              # DecoratedFunctionRule
    ;

decorator
    : AT dottedName LPAREN argumentList? RPAREN   # DecoratorRule
    ;

/* ================= Functions ================= */
functionDef
    : DEF name=Identifier LPAREN parameterList? RPAREN COLON body=block   # FunctionDefRule
    ;

parameterList
    : Identifier (COMMA Identifier)*      # ParameterListRule
    ;

/* ================= Statements ================= */
statement
    : assignment                          # AssignmentStmt
    | returnStmt                          # Return
    | ifStmt                              # If
    | forStmt                             # For
    | BREAK                               # BreakStmt
    | expression                          # ExpressionStmt
    ;
 assignment
        : var=Identifier ASSIGN value=expression              # AssignmentRule
        | var=Identifier op=(PLUSASSIGN | MINUSASSIGN | MULASSIGN | DIVASSIGN) value=expression   # AugmentedAssignmentRule
        ;

returnStmt
    : RETURN expression (COMMA expression)*   # ReturnRule
    ;

/* ================= Control Flow ================= */
ifStmt
    : IF condition=expression COLON thenBlock=block
      (ELIF elifCondition=expression COLON elifBlock=block)*
      (ELSE COLON elseBlock=block)?       # IfStmtRule
    ;

forStmt
    : FOR var=Identifier IN iterable=expression COLON body=block   # ForStmtRule
    ;

block
    : statement*                          # BlockRule
    ;

/* ================= Expressions ================= */
expression
    : conditionalExpr
    ;

conditionalExpr
    : comparisonExpr (IF expression ELSE expression)?   # ConditionalExprRule
    ;

comparisonExpr
    : additiveExpr ((EQEQ | IS (NOT)? | GT | LT | GE | LE | NEQ) additiveExpr)?   # ComparisonExprRule
    ;

additiveExpr
    : multiplicativeExpr ((PLUS | MINUS) multiplicativeExpr)*   # AdditiveExprRule
    ;

multiplicativeExpr
    : powerExpr ((STAR | DIV | MOD) powerExpr)*   # MultiplicativeExprRule
    ;

powerExpr
    : unaryExpr (POW unaryExpr)*   # PowerExprRule
    ;


primaryExpr
    : functionCall                              # FunctionCallExpr
    | methodCall                                # MethodCallExpr
    | indexing                                  # IndexingExpr
    | list                                      # ListExpr
    | dict                                      # DictExpr
    | literal                                   # LiteralExpr
    | dottedName                                # DottedNameExpr
    | LPAREN expression RPAREN                  # ParenExpr
    ;
  unaryExpr
      : (PLUS | MINUS) unaryExpr
      | primaryExpr
      ;

generatorExpr
    : LPAREN expression FOR Identifier IN expression (IF expression)? RPAREN   # GeneratorExprRule
    ;


/* ================= Calls ================= */
functionCall
    : func=Identifier LPAREN args=argumentList? RPAREN   # FunctionCallRule
    ;

methodCall
    : method=dottedName LPAREN args=argumentList? RPAREN # MethodCallRule
    ;

/* ================= Access ================= */
indexing
    : target=dottedName (LBRACK (expression | StringLiteral) RBRACK)+   # IndexingRule
    ;

/* ================= Arguments ================= */
argumentList
    : argument (COMMA argument)*              # ArgumentListRule
    ;

argument
    : expression                              # ArgumentExpr
    | Identifier ASSIGN expression            # NamedArgumentExpr
    | generatorExpr                           # GeneratorArgumentExpr
    ;


/* ================= Data Structures ================= */
list
    : LBRACK (expression (COMMA expression)* COMMA?)? RBRACK   # ListRule
    | LBRACK comprehension RBRACK                             # ListComprehensionRule
    ;

comprehension
    : expression FOR Identifier IN expression (IF expression)? # ComprehensionRule
    ;

dict
    : LBRACE (dictItem (COMMA dictItem)*)? RBRACE             # DictRule
    ;

dictItem
    : key=StringLiteral COLON value=expression                # DictItemRule
    ;

/* ================= Literals ================= */
literal
    : StringLiteral                                           # StringLiteralRule
    | NumberLiteral                                           # NumberLiteralRule
    | NONE                                                    # NoneLiteralRule
    | TRUE                                                    # TrueLiteralRule
    | FALSE                                                   # FalseLiteralRule
    ;