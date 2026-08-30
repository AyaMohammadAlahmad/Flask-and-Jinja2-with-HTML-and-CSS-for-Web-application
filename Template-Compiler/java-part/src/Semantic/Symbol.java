package Semantic;

/**
 * A single entry in the symbol table.
 *
 * Supports three distinct categories of symbols:
 *
 *   VARIABLE  — a Jinja2 template variable (e.g. product.name).
 *               No additional metadata beyond name/kind/line.
 *
 *   FUNCTION  — a callable registered in the symbol table together with its
 *               full FunctionSignature. The signature encodes the parameter
 *               list (name, type hint, required/optional, positional/keyword)
 *               and the attributeSafe flag used by the advanced semantic rule.
 *
 *   PARAMETER — a named keyword argument name observed at a call site
 *               (e.g. "id" in url_for('view', id=product.id)).
 *
 *   BLOCK     — a {% block name %} declaration.
 *
 * Using FunctionSignature as a first-class field (rather than a flat list of
 * strings) allows the semantic analyser to perform complete signature matching:
 * required-positional count check, required-named-param check, unknown-named-
 * param check, and attribute-context safety check.
 */
public class Symbol {

    private final String          name;
    private final SymbolKind      kind;
    private final int             line;
    private final FunctionSignature signature; // non-null only for FUNCTION symbols

    // ── Constructor for non-FUNCTION symbols ─────────────────────────────

    public Symbol(String name, SymbolKind kind, int line) {
        this.name      = name;
        this.kind      = kind;
        this.line      = line;
        this.signature = null;
    }

    // ── Constructor for FUNCTION symbols ─────────────────────────────────

    public Symbol(String name, SymbolKind kind, int line, FunctionSignature signature) {
        this.name      = name;
        this.kind      = kind;
        this.line      = line;
        this.signature = signature;
    }

    // ── Accessors ─────────────────────────────────────────────────────────

    public String          getName()      { return name;      }
    public SymbolKind      getKind()      { return kind;      }
    public int             getLine()      { return line;      }

    /**
     * Returns the FunctionSignature for FUNCTION symbols, or null for all
     * other symbol kinds.
     */
    public FunctionSignature getSignature() { return signature; }

    /** Convenience: true when this is a FUNCTION symbol with a signature. */
    public boolean hasSignature() {
        return kind == SymbolKind.FUNCTION && signature != null;
    }

    // ── Display ───────────────────────────────────────────────────────────

    @Override
    public String toString() {
        if (hasSignature()) {
            return kind + " " + signature + " (line " + line + ")";
        }
        return kind + " " + name + " (line " + line + ")";
    }
}
