package Semantic;

/**
 * Describes a single formal parameter in a function signature.
 *
 * A parameter has:
 *   - name         : the keyword name (e.g. "id", "filename")
 *   - type hint    : an optional declared type string used for display and
 *                    future type-checking (e.g. "string", "int", "any")
 *   - required     : whether the caller must supply this argument
 *   - positional   : whether this parameter may be passed positionally
 *                    (i.e. without the name=value syntax)
 *
 * Example — url_for('endpoint', id=product.id):
 *   ParameterDescriptor("_endpoint", "string", required=true,  positional=true)
 *   ParameterDescriptor("id",        "any",    required=false, positional=false)
 */
public class ParameterDescriptor {

    private final String  name;
    private final String  typeHint;   // "string" | "int" | "any" | etc.
    private final boolean required;
    private final boolean positional;

    public ParameterDescriptor(String name,
                               String typeHint,
                               boolean required,
                               boolean positional) {
        this.name       = name;
        this.typeHint   = typeHint;
        this.required   = required;
        this.positional = positional;
    }

    /** Convenience constructor: required positional parameter of type "any". */
    public static ParameterDescriptor requiredPositional(String name) {
        return new ParameterDescriptor(name, "any", true, true);
    }

    /** Convenience constructor: optional named parameter of type "any". */
    public static ParameterDescriptor optionalNamed(String name) {
        return new ParameterDescriptor(name, "any", false, false);
    }

    /** Convenience constructor: required named parameter with explicit type. */
    public static ParameterDescriptor requiredNamed(String name, String typeHint) {
        return new ParameterDescriptor(name, typeHint, true, false);
    }

    public String getName()      { return name;       }
    public String getTypeHint()  { return typeHint;   }
    public boolean isRequired()  { return required;   }
    public boolean isPositional(){ return positional;  }

    @Override
    public String toString() {
        String marker = required ? "" : "?";
        String pos    = positional ? "pos" : "kw";
        return name + marker + ":" + typeHint + "[" + pos + "]";
    }
}
