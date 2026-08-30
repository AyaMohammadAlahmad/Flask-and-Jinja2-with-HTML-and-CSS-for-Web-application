package Visitor;

import AST.*;
import Semantic.Symbol;
import Semantic.SymbolKind;
import Semantic.SymbolTable;

/**
 * AST visitor that populates the symbol table with template-level symbols.
 *
 * ── What is registered ──────────────────────────────────────────────────
 *   VARIABLE  — every Jinja2 variable reference (e.g. product.name)
 *   FUNCTION  — every distinct function name used in a call (e.g. url_for)
 *               Note: the FUNCTION symbol's FunctionSignature is pre-declared
 *               by FunctionRegistry before this walk begins.  Here we register
 *               the call-site observation only if no prior declaration exists.
 *   PARAMETER — every named keyword argument name at a call site
 *   BLOCK     — every {% block name %} declaration (opens a child scope)
 *
 * ── What is NOT registered ──────────────────────────────────────────────
 *   HTML elements, HTML attributes, CSS rules/selectors — these belong to
 *   the AST only.
 *
 * ── Separation of concerns ──────────────────────────────────────────────
 *   This visitor builds the symbol table.
 *   SemanticAnalyzer performs all validation in a separate pass.
 */
public class HtmlSymbolTableVisitor {

    private final SymbolTable rootScope = new SymbolTable("template");
    private SymbolTable currentScope = rootScope;

    public SymbolTable getSymbolTable() {
        return rootScope;
    }

    // ── Entry point ───────────────────────────────────────────────────────

    public void visit(ASTNode node) {
        if (node == null) return;

        if      (node instanceof ASTTemplate template)       visitTemplate(template);
        else if (node instanceof HtmlElementNode element)    visitHtmlElement(element);
        else if (node instanceof ASTBlock block)             visitBlock(block);
        else if (node instanceof ASTJinjaExpression expr)    visitJinjaExpression(expr);
        else if (node instanceof ASTJinjaStatementNode stmt) visitJinjaStatement(stmt);
        // ASTStyle, TextNode, DoctypeNode — no symbols
    }

    // ── Template ──────────────────────────────────────────────────────────

    private void visitTemplate(ASTTemplate template) {
        for (ContentNode c : template.getContents()) {
            visit(c);
        }
    }

    // ── HTML element ──────────────────────────────────────────────────────
    // Visit attribute values and child content.
    // Do NOT register the element tag name or attribute names as symbols.

    private void visitHtmlElement(HtmlElementNode element) {
        for (AttributeNode attr : element.getAttributes()) {
            ASTExpression val = attr.getValue();
            if (val instanceof AttributeJinjaExpressionNode jinjaAttr) {
                visitJinjaExpression(jinjaAttr.getExpression());
            }
        }
        for (ContentNode child : element.getChildren()) {
            visit(child);
        }
    }

    // ── Block — registers BLOCK symbol, opens named child scope ──────────

    private void visitBlock(ASTBlock block) {
        if (currentScope.lookupLocal(block.getBlockName()) == null) {
            currentScope.define(new Symbol(
                    block.getBlockName(), SymbolKind.BLOCK, block.getLine()
            ));
        }

        SymbolTable previous = currentScope;
        currentScope = new SymbolTable(previous, "block:" + block.getBlockName());

        for (ContentNode c : block.getContents()) {
            visit(c);
        }

        currentScope = previous;
    }

    // ── Jinja expression ──────────────────────────────────────────────────

    private void visitJinjaExpression(ASTJinjaExpression expr) {
        if (expr == null) return;
        registerExpression(expr.getExpression());
    }

    private void registerExpression(ASTExpression expression) {
        if (expression == null) return;

        if      (expression instanceof ASTVariable     variable) registerVariable(variable);
        else if (expression instanceof ASTFunctionCall function) registerFunctionCall(function);
        // StringLiteralNode — no symbol to register
    }

    private void registerVariable(ASTVariable variable) {
        String name = variable.getFullName();
        if (currentScope.lookupLocal(name) == null) {
            currentScope.define(new Symbol(name, SymbolKind.VARIABLE, variable.getLine()));
        }
    }

    /**
     * Registers a function call-site observation.
     * If a FUNCTION symbol already exists (pre-declared by FunctionRegistry),
     * the existing declaration is preserved and only the arguments are processed.
     * If no declaration exists, a bare FUNCTION symbol is registered (no signature).
     */
    private void registerFunctionCall(ASTFunctionCall function) {
        String fname = function.getFunctionName();
        if (currentScope.lookup(fname) == null) {
            // No pre-declaration found — register bare observation.
            // SemanticAnalyzer will report UNDEFINED_FUNCTION for this.
            currentScope.define(new Symbol(fname, SymbolKind.FUNCTION, function.getLine()));
        }
        registerFunctionArguments(function);
    }

    private void registerFunctionArguments(ASTFunctionCall function) {
        for (ASTNode arg : function.getArguments()) {
            if (arg instanceof ASTVariable variable) {
                registerVariable(variable);
            } else if (arg instanceof NamedArgumentNode named) {
                if (currentScope.lookupLocal(named.getName()) == null) {
                    currentScope.define(new Symbol(
                            named.getName(), SymbolKind.PARAMETER, named.getLine()
                    ));
                }
                registerExpression(named.getValue());
            } else if (arg instanceof ASTFunctionCall nested) {
                registerFunctionCall(nested);
            }
        }
    }

    // ── Jinja statement (for-loop variable binding) ───────────────────────

    private void visitJinjaStatement(ASTJinjaStatementNode stmt) {
        if (!"for".equals(stmt.getStatementType())) return;

        // content format: "iterVar in iterableVar"
        String[] parts = stmt.getContent().trim().split("\\s+in\\s+", 2);
        if (parts.length != 2) return;

        String iterVar     = parts[0].trim();
        String iterableVar = parts[1].trim();

        if (currentScope.lookupLocal(iterVar) == null) {
            currentScope.define(new Symbol(iterVar, SymbolKind.VARIABLE, stmt.getLine()));
        }
        if (currentScope.lookupLocal(iterableVar) == null) {
            currentScope.define(new Symbol(iterableVar, SymbolKind.VARIABLE, stmt.getLine()));
        }
    }
}
