package Semantic;

/**
 * Enumerates the kinds of symbols stored in the symbol table.
 *
 * Only template-level constructs are tracked:
 *
 *   VARIABLE  — a Jinja2 template variable reference (e.g. product.name)
 *   FUNCTION  — a registered callable with a full FunctionSignature
 *               (e.g. url_for, csrf_token, render_field)
 *   PARAMETER — a named keyword argument name observed at a call site
 *               (e.g. "id" in url_for('view', id=product.id))
 *   BLOCK     — a {% block name %} declaration
 *
 * HTML_ELEMENT and ATTRIBUTE are intentionally absent: HTML structure
 * belongs to the AST (HtmlElementNode / AttributeNode), not the symbol table.
 */
public enum SymbolKind {
    VARIABLE,
    FUNCTION,
    PARAMETER,
    BLOCK
}
