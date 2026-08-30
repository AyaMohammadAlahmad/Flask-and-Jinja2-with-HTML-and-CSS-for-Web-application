package Semantic;

/**
 * Represents a single diagnostic produced by SemanticAnalyzer.
 *
 * Each error has:
 *   type    — machine-readable category used for testing and filtering
 *   message — human-readable description shown to the developer
 *   line    — source line where the offending construct appears
 */
public class SemanticError {

    // ── Error taxonomy ────────────────────────────────────────────────────

    public enum ErrorType {

        /** A Jinja2 variable is referenced but not registered in any enclosing scope. */
        UNDEFINED_VARIABLE,

        /** A function is called but no matching FUNCTION symbol exists in scope. */
        UNDEFINED_FUNCTION,

        /**
         * A required positional argument is absent from a function call.
         * Example: url_for() with no endpoint argument.
         */
        MISSING_REQUIRED_POSITIONAL_ARGUMENT,

        /**
         * A required named (keyword) argument is absent from a function call.
         * Example: render_field() without the required 'field' positional argument.
         */
        MISSING_REQUIRED_NAMED_ARGUMENT,

        /**
         * A named argument is supplied whose name does not appear in the
         * declared signature's named-parameter list.
         * Example: url_for('index', typo_param=1) where 'typo_param' is unknown.
         * Note: url_for accepts arbitrary **kwargs so this only fires for
         *       functions with closed (fixed) named-parameter lists.
         */
        UNEXPECTED_NAMED_ARGUMENT,

        /**
         * ── ADVANCED SEMANTIC RULE ──────────────────────────────────────
         *
         * A function call appears as the value of an HTML attribute expression
         * (inside {{ }} within "…") but the function's signature declares it
         * NOT attribute-safe — i.e. the function produces full HTML markup or
         * causes side effects rather than returning a scalar value.
         *
         * This is invalid because an HTML attribute value must be a scalar
         * (string, URL, number, boolean-like).  Embedding a function that
         * produces raw HTML tags (such as csrf_token() or render_field())
         * inside an attribute value results in broken markup that browsers
         * will escape or misinterpret.
         *
         * Example (INVALID):
         *   <input class="{{ csrf_token() }}">
         *     → csrf_token() produces <input type="hidden" …>, not a class string
         *
         *   <div title="{{ render_field(form.name) }}">
         *     → render_field() produces an entire <label>+<input>+<span> block
         *
         * Detection requires BOTH:
         *   • AST context  — recognising that the call is nested inside an
         *                    AttributeJinjaExpressionNode (not a body JinjaExpression)
         *   • Symbol table — looking up the function's FunctionSignature and
         *                    reading the attributeSafe flag
         *
         * This rule is non-trivial and does not duplicate any HTML structural
         * validation (unknown tags, duplicate IDs, invalid nesting).
         */
        NON_ATTRIBUTE_SAFE_FUNCTION_IN_ATTRIBUTE_CONTEXT,

        /** Two {% block %} declarations share the same name in the same scope. */
        DUPLICATE_BLOCK,

        /**
         * ── NEW SEMANTIC RULE ────────────────────────────────────────────
         *
         * A {% for x in collection %} loop binds an iteration variable that
         * is never referenced anywhere in the loop's body (neither bare
         * ({{ x }}) nor as part of a dotted access such as {{ x.name }}).
         *
         * Example (INVALID — 'item' bound but never used):
         *   {% for item in products %}
         *     <li>Product</li>
         *   {% endfor %}
         *
         * Example (VALID — 'product' is used inside the loop body):
         *   {% for product in products %}
         *     <li>{{ product.name }}</li>
         *   {% endfor %}
         *
         * This is a true semantic error (not a syntax error) because
         * detecting it requires understanding which AST subtree constitutes
         * the loop body, resolving every variable reference found in that
         * subtree back to its dotted root identifier, and comparing that set
         * against the loop-bound name — none of which the grammar/parser
         * can determine on its own. A binding with no reference anywhere in
         * its lexical extent is dead, which is exactly the class of defect
         * semantic analysis exists to catch (the same category as "unused
         * variable" warnings in conventional compilers).
         *
         * This rule is unrelated to, and does not duplicate, HTML-structural
         * checks such as duplicate ID detection, invalid tag nesting, or
         * unknown tag detection — it concerns Jinja variable bindings, not
         * HTML element/attribute structure.
         */
        UNUSED_LOOP_VARIABLE,

        /**
         * The same HTML {@code id} attribute value appears on more than one
         * element anywhere in the document.  An {@code id} must be globally
         * unique within a single HTML document.
         *
         * Example (INVALID):
         *   <div id="header"></div>
         *   <span id="header"></span>
         *
         * Detection requires collecting every {@code id} value seen while
         * walking the entire AST and checking each new value against the
         * accumulated set — a document-level data-flow check that the
         * parser cannot perform.
         */
        DUPLICATE_HTML_ID,

        /**
         * An HTML element is placed in a position that violates well-known
         * HTML nesting rules:
         *   • An {@code <a>} element is nested directly or indirectly inside
         *     another {@code <a>} element.
         *   • An {@code <li>} element appears outside any {@code <ul>} or
         *     {@code <ol>} ancestor.
         *
         * Detection requires maintaining an ancestor-tag stack while
         * traversing the AST, which is beyond the scope of the grammar.
         */
        INVALID_TAG_NESTING
    }

    // ── Fields ────────────────────────────────────────────────────────────

    private final ErrorType type;
    private final String    message;
    private final int       line;

    public SemanticError(ErrorType type, String message, int line) {
        this.type    = type;
        this.message = message;
        this.line    = line;
    }

    // ── Accessors ─────────────────────────────────────────────────────────

    public ErrorType getType()    { return type;    }
    public String    getMessage() { return message; }
    public int       getLine()    { return line;    }

    // ── Display ───────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "[SEMANTIC ERROR:" + type.name() + "] line " + line + ": " + message;
    }
}
