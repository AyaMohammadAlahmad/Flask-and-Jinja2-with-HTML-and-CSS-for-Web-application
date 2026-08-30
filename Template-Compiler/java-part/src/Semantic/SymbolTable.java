package Semantic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Scoped symbol table for the HTML/Jinja2 template compiler front-end.
 *
 * ── What is stored ──────────────────────────────────────────────────────
 * Only template-level symbols:
 *   VARIABLE  — Jinja2 variable references (product.name, loop.index, …)
 *   FUNCTION  — Registered callables with full FunctionSignature metadata
 *   PARAMETER — Keyword argument names observed at call sites
 *   BLOCK     — Jinja2 block declarations
 *
 * HTML elements and attributes are NOT stored here; they live in the AST.
 *
 * ── Scoping ─────────────────────────────────────────────────────────────
 * The table forms a tree of scopes:
 *   • Root scope   → the template's global scope
 *   • Child scopes → one per {% block %} declaration
 *
 *   lookup()      walks from the current scope up to root, returning the
 *                 first matching symbol (Jinja2 variables are lexically scoped).
 *   lookupLocal() inspects only the current scope, used for duplicate checks.
 *   lookupFunction() is a typed helper that returns only FUNCTION symbols
 *                 and is used directly by SemanticAnalyzer.
 *
 * ── Function registry ────────────────────────────────────────────────────
 * Functions pre-declared by HtmlRunner (built-ins such as url_for, csrf_token)
 * are defined at the root scope before the symbol-table walk begins.
 * The visitor then records additional call-site observations; SemanticAnalyzer
 * resolves each call against the pre-declared signature.
 */
public class SymbolTable {

    private final Map<String, Symbol>  symbols  = new LinkedHashMap<>();
    private final SymbolTable          parent;
    private final List<SymbolTable>    children = new ArrayList<>();

    /** Scope label for debugging / hierarchy print (e.g. "template", "block:content"). */
    private final String label;

    // ── Constructors ──────────────────────────────────────────────────────

    /** Creates a root (global template) scope. */
    public SymbolTable() {
        this.parent = null;
        this.label  = "template";
    }

    /** Creates a named root scope (used for testing). */
    public SymbolTable(String label) {
        this.parent = null;
        this.label  = label;
    }

    /** Creates a child scope with an auto-generated label. */
    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
        this.label  = "scope";
        if (parent != null) parent.children.add(this);
    }

    /** Creates a named child scope (e.g. "block:content"). */
    public SymbolTable(SymbolTable parent, String label) {
        this.parent = parent;
        this.label  = label;
        if (parent != null) parent.children.add(this);
    }

    // ── Symbol registration ───────────────────────────────────────────────

    /**
     * Defines a symbol in the current scope.
     * If a symbol with the same name already exists in this scope, it is
     * silently replaced (the caller is responsible for duplicate checks before
     * calling define()).
     */
    public void define(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
    }

    // ── Symbol resolution ─────────────────────────────────────────────────

    /**
     * Resolves a name starting from the current scope and walking up through
     * parent scopes. Returns null if not found in any enclosing scope.
     */
    public Symbol lookup(String name) {
        Symbol s = symbols.get(name);
        if (s != null) return s;
        if (parent != null) return parent.lookup(name);
        return null;
    }

    /**
     * Resolves a name in the current scope only (no parent traversal).
     * Used for duplicate-definition checks.
     */
    public Symbol lookupLocal(String name) {
        return symbols.get(name);
    }

    /**
     * Typed lookup that returns a Symbol only if it is of kind FUNCTION.
     * Returns null if the name is not found or resolves to a non-FUNCTION symbol.
     * Used directly by SemanticAnalyzer to avoid redundant kind checks.
     */
    public Symbol lookupFunction(String name) {
        Symbol s = lookup(name);
        return (s != null && s.getKind() == SymbolKind.FUNCTION) ? s : null;
    }

    /**
     * Typed lookup for VARIABLE symbols across all scopes.
     */
    public Symbol lookupVariable(String name) {
        Symbol s = lookup(name);
        return (s != null && s.getKind() == SymbolKind.VARIABLE) ? s : null;
    }

    // ── Structural accessors ──────────────────────────────────────────────

    public SymbolTable          getParent()   { return parent;   }
    public List<SymbolTable>    getChildren() { return Collections.unmodifiableList(children); }
    public Map<String, Symbol>  getSymbols()  { return Collections.unmodifiableMap(symbols);  }
    public String               getLabel()    { return label;    }

    // ── Diagnostics ───────────────────────────────────────────────────────

    /**
     * Recursively prints all symbols in this scope and every child scope,
     * indented to reflect nesting depth.
     * (Unchanged — kept exactly as before so nothing that already calls it breaks.)
     */
    public void printHierarchy(String indent) {
        System.out.println(indent + "[ " + label + " ]");
        for (Symbol s : symbols.values()) {
            System.out.println(indent + "  " + s);
        }
        for (SymbolTable child : children) {
            child.printHierarchy(indent + "  ");
        }
    }

    /**
     * [Phase 3 — new] Same traversal as {@link #printHierarchy}, but returns
     * the result as a String instead of writing to stdout — needed so the
     * generation pipeline (CodeGenerator) can capture it and export it into
     * {@code compiler_output/symbol_table_jinja.txt} instead of it only ever
     * appearing in the console when HtmlRunner is run standalone.
     */
    public String toHierarchyString(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent).append("[ ").append(label).append(" ]\n");
        for (Symbol s : symbols.values()) {
            sb.append(indent).append("  ").append(s).append("\n");
        }
        for (SymbolTable child : children) {
            sb.append(child.toHierarchyString(indent + "  "));
        }
        return sb.toString();
    }
}
