package Semantic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A complete formal signature for a Jinja2 / framework function.
 *
 * Stores the ordered parameter list and encodes the call-context
 * restrictions that drive the new semantic rule:
 *
 *   attributeSafe — whether this function may be called inside an HTML
 *                   attribute value expression ({{ expr }} inside "…").
 *                   Functions that produce full HTML markup (e.g. render_field,
 *                   csrf_token producing a full <input> tag) are NOT attribute-
 *                   safe and must only appear in body content position.
 *
 * This flag is the foundation of the advanced semantic rule implemented in
 * SemanticAnalyzer: detecting calls to non-attribute-safe functions when they
 * appear as the value of an HTML attribute.
 */
public class FunctionSignature {

    private final String               functionName;
    private final List<ParameterDescriptor> parameters;

    /**
     * true  → safe to embed in an HTML attribute value (produces a scalar).
     * false → must only appear in body content (produces HTML markup / side effects).
     */
    private final boolean attributeSafe;

    /** Optional documentation string shown in error messages. */
    private final String doc;

    private FunctionSignature(Builder b) {
        this.functionName  = b.name;
        this.parameters    = Collections.unmodifiableList(new ArrayList<>(b.params));
        this.attributeSafe = b.attributeSafe;
        this.doc           = b.doc;
    }

    // ── Accessors ─────────────────────────────────────────────────────────

    public String getFunctionName() { return functionName; }

    public List<ParameterDescriptor> getParameters() { return parameters; }

    public boolean isAttributeSafe() { return attributeSafe; }

    public String getDoc() { return doc; }

    // ── Helpers ───────────────────────────────────────────────────────────

    /** Returns all required named parameters (non-positional, required). */
    public List<ParameterDescriptor> getRequiredNamedParams() {
        List<ParameterDescriptor> out = new ArrayList<>();
        for (ParameterDescriptor p : parameters) {
            if (p.isRequired() && !p.isPositional()) {
                out.add(p);
            }
        }
        return out;
    }

    /** Returns all required positional parameters. */
    public List<ParameterDescriptor> getRequiredPositionalParams() {
        List<ParameterDescriptor> out = new ArrayList<>();
        for (ParameterDescriptor p : parameters) {
            if (p.isRequired() && p.isPositional()) {
                out.add(p);
            }
        }
        return out;
    }

    /** Finds a parameter descriptor by name. Returns empty if not declared. */
    public Optional<ParameterDescriptor> findParam(String name) {
        return parameters.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

    /** Whether the signature declares any named parameters at all. */
    public boolean hasNamedParams() {
        return parameters.stream().anyMatch(p -> !p.isPositional());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(functionName).append("(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(parameters.get(i));
        }
        sb.append(")");
        if (!attributeSafe) sb.append(" [NOT attr-safe]");
        return sb.toString();
    }

    // ── Builder ───────────────────────────────────────────────────────────

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public static class Builder {
        private final String name;
        private final List<ParameterDescriptor> params = new ArrayList<>();
        private boolean attributeSafe = true;
        private String  doc           = "";

        private Builder(String name) { this.name = name; }

        public Builder param(ParameterDescriptor p) {
            params.add(p);
            return this;
        }

        /** Mark the function as unsafe to embed inside HTML attribute values. */
        public Builder notAttributeSafe() {
            this.attributeSafe = false;
            return this;
        }

        public Builder doc(String doc) {
            this.doc = doc;
            return this;
        }

        public FunctionSignature build() {
            return new FunctionSignature(this);
        }
    }
}
