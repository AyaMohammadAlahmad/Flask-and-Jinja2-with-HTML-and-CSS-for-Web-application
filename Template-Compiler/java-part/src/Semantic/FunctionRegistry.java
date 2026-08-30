package Semantic;

/**
 * Central registry of all built-in and framework functions known to this
 * compiler, together with their complete FunctionSignature declarations.
 *
 * FunctionRegistry.populate(rootScope) must be called once — before the
 * symbol-table visitor walk — so that every function call encountered in
 * the template can be resolved against a pre-declared signature.
 *
 * ── Attribute-safety semantics ───────────────────────────────────────────
 * Functions that produce scalar values (URLs, escaped strings, numbers) are
 * declared attributeSafe=true and may appear inside HTML attribute expressions:
 *
 *   <a href="{{ url_for('index') }}">          ← OK
 *   <img src="{{ url_for('static', filename='img/logo.png') }}"> ← OK
 *
 * Functions that produce full HTML markup or cause side effects are declared
 * attributeSafe=false.  Embedding them inside an attribute is a semantic error
 * caught by SemanticAnalyzer:
 *
 *   <input {{ csrf_token() }}>                 ← ERROR (produces <input> tag)
 *   <div {{ render_field(form.name) }}>        ← ERROR (produces full field HTML)
 *
 * This is the foundation of the advanced semantic rule: non-attribute-safe
 * function call detection in attribute-expression context.
 */
public class FunctionRegistry {

    private FunctionRegistry() {}

    /**
     * Declares all known functions in the provided root symbol table scope.
     * Existing definitions (e.g. from a prior call) are not overwritten.
     */
    public static void populate(SymbolTable rootScope) {

        // ── url_for(endpoint, **kwargs) ──────────────────────────────
        // Produces a URL string → safe inside attribute values.
        // First positional argument is the endpoint name (string literal).
        // Additional keyword arguments are route parameters (any type).
        register(rootScope, FunctionSignature.builder("url_for")
                .doc("Generates a URL for the given endpoint.")
                .param(ParameterDescriptor.requiredPositional("_endpoint"))
                .build()
        );

        // ── static(filename) — url_for alias for static files ─────────
        // Not a real Flask function; url_for('static', filename=...) is the
        // idiomatic pattern.  Kept here for completeness.

        // ── csrf_token() ──────────────────────────────────────────────
        // Produces a complete <input type="hidden"> HTML tag.
        // NOT safe inside an attribute value.
        register(rootScope, FunctionSignature.builder("csrf_token")
                .doc("Renders a CSRF hidden input field — produces HTML markup.")
                .notAttributeSafe()
                .build()
        );

        // ── render_field(field, label=..., class_=...) ────────────────
        // Produces a complete field widget (label + input + errors).
        // NOT safe inside an attribute value.
        register(rootScope, FunctionSignature.builder("render_field")
                .doc("Renders a full form field widget — produces HTML markup.")
                .param(ParameterDescriptor.requiredPositional("field"))
                .param(ParameterDescriptor.optionalNamed("label"))
                .param(ParameterDescriptor.optionalNamed("class_"))
                .notAttributeSafe()
                .build()
        );

        // ── super() ───────────────────────────────────────────────────
        // Renders the parent template's block content — produces markup.
        // NOT safe inside an attribute value.
        register(rootScope, FunctionSignature.builder("super")
                .doc("Renders the parent block's content — produces HTML markup.")
                .notAttributeSafe()
                .build()
        );

        // ── loop.cycle(*args) ─────────────────────────────────────────
        // Returns one of its arguments cyclically — produces a scalar.
        register(rootScope, FunctionSignature.builder("cycle")
                .doc("Cycles through its arguments — produces a scalar value.")
                .param(ParameterDescriptor.requiredPositional("_first"))
                .build()
        );

        // ── range(stop) / range(start, stop) ─────────────────────────
        // Produces an iterable — acceptable in expressions but not
        // meaningful as an attribute value (not attribute-unsafe either;
        // its use inside an attribute is a logic error rather than a
        // markup-injection error, so we leave it attributeSafe=true here
        // and let the developer handle it).
        register(rootScope, FunctionSignature.builder("range")
                .doc("Returns a range iterable.")
                .param(ParameterDescriptor.requiredPositional("_stop"))
                .build()
        );
    }

    // ── Internal helper ───────────────────────────────────────────────────

    private static void register(SymbolTable scope, FunctionSignature sig) {
        if (scope.lookupLocal(sig.getFunctionName()) == null) {
            scope.define(new Symbol(
                    sig.getFunctionName(),
                    SymbolKind.FUNCTION,
                    0,   // line 0 = built-in declaration, not from source
                    sig
            ));
        }
    }
}
