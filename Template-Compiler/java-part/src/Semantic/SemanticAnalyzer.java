package Semantic;

import AST.*;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Two-pass semantic analyser for HTML/Jinja2 templates.
 *
 * ════════════════════════════════════════════════════════════════════════════
 * SEMANTIC RULES IMPLEMENTED
 * ════════════════════════════════════════════════════════════════════════════
 *
 * ① UNDEFINED VARIABLE DETECTION
 *   Every ASTVariable found in a {{ expr }} or attribute {{ expr }} context
 *   is resolved against the symbol table.  A variable that cannot be found
 *   in any enclosing scope generates an UNDEFINED_VARIABLE error.
 *
 * ② FUNCTION SIGNATURE VALIDATION
 *   Every ASTFunctionCall is validated against the FunctionSignature stored
 *   for the called function in the symbol table:
 *     a. UNDEFINED_FUNCTION          — no FUNCTION symbol found for the name.
 *     b. MISSING_REQUIRED_POSITIONAL_ARGUMENT — fewer positional args supplied
 *        than the signature's required positional parameter count.
 *     c. MISSING_REQUIRED_NAMED_ARGUMENT — a required named param is absent.
 *     d. UNEXPECTED_NAMED_ARGUMENT   — a named arg whose name is not in the
 *        declared closed parameter list (only for non-variadic functions).
 *
 * ③ NON-ATTRIBUTE-SAFE FUNCTION CALL IN ATTRIBUTE CONTEXT  [ADVANCED RULE]
 *   ─────────────────────────────────────────────────────────────────────────
 *   Detects calls to functions that produce full HTML markup (or cause side
 *   effects) when those calls appear as the value expression of an HTML
 *   attribute.
 *
 *   Context recognition uses the AST:
 *     • A body Jinja expression  → JinjaExpression (ContentNode child)
 *     • An attribute Jinja value → AttributeJinjaExpressionNode inside
 *       an AttributeNode, which is itself a child of HtmlElementNode
 *
 *   The analyser uses a boolean flag (inAttributeContext) toggled when
 *   descending into attribute value expressions, and then checked for each
 *   ASTFunctionCall.  The function's FunctionSignature.isAttributeSafe()
 *   flag (from the symbol table) determines whether the call is legal there.
 *
 *   REQUIRES BOTH AST AND SYMBOL TABLE:
 *     AST          → determines the call's syntactic position (attribute vs body)
 *     Symbol table → determines whether the called function is attribute-safe
 *
 *   Example triggers:
 *     <input class="{{ csrf_token() }}">          → ERROR (csrf_token not attr-safe)
 *     <a href="{{ url_for('index') }}">           → OK    (url_for is attr-safe)
 *     <div title="{{ render_field(form.f) }}">    → ERROR (render_field not attr-safe)
 *   ─────────────────────────────────────────────────────────────────────────
 *
 * ④ DUPLICATE BLOCK DETECTION
 *   Two {% block name %} declarations with the same name at the same scope
 *   level generate a DUPLICATE_BLOCK error.
 *
 * ⑤ UNUSED LOOP VARIABLE DETECTION  [NEW RULE]
 *   ─────────────────────────────────────────────────────────────────────────
 *   For every {% for x in collection %} statement, the loop-bound name `x`
 *   must be referenced at least once somewhere in the loop's body (either
 *   bare, {{ x }}, or as the root of a dotted access, {{ x.field }}). If `x`
 *   is never referenced anywhere in the body, an UNUSED_LOOP_VARIABLE error
 *   is reported at the {% for %} statement's line.
 *
 *   WHY THIS IS A TRUE SEMANTIC ERROR (not syntactic):
 *     The grammar only knows that "for x in y" is syntactically well-formed;
 *     it has no notion of "binding" or "reference" at all. Determining
 *     whether a bound name is ever read requires walking the AST subtree
 *     that constitutes the loop body and resolving every ASTVariable found
 *     there back to its root identifier — a data-flow question, which is
 *     exactly the class of analysis semantic passes (not parsers) perform.
 *
 *   WHY IT DIFFERS FROM THE TEAMMATE-OWNED RULES (left untouched):
 *     Duplicate HTML id checking, invalid tag nesting (<a> inside <a>, <li>
 *     outside <ul>/<ol>), and unknown-tag detection are all purely about
 *     HTML element/attribute structure. This rule never inspects tag names,
 *     attributes, or nesting — it only inspects Jinja variable bindings and
 *     references. The two problem domains do not overlap.
 *
 *   HOW THE LOOP BODY IS IDENTIFIED (AST-only, no grammar change):
 *     {% for %} and {% endfor %} are both emitted by ASTVisitor as
 *     ASTJinjaStatementNode siblings within the SAME parent's child list
 *     (ASTBlock.getContents() or HtmlElementNode.getChildren()) — the
 *     grammar's content* production guarantees a for/endfor pair can never
 *     end up split across two different containers. So for each "for"
 *     statement found at any depth in the tree, the algorithm scans forward
 *     through ITS OWN parent's child list for the next "endfor" sibling;
 *     every list entry strictly between them (recursing into any nested
 *     HtmlElementNode subtrees) is the loop body.
 *
 *   SYMBOL TABLE ROLE:
 *     The Symbol Table is consulted as a consistency check — the loop
 *     variable must already be registered as a VARIABLE in the relevant
 *     scope (it always will be, since HtmlSymbolTableVisitor registers it),
 *     confirming the AST and Symbol Table agree on the binding before the
 *     usage scan runs.
 *   ─────────────────────────────────────────────────────────────────────────
 *
 * ════════════════════════════════════════════════════════════════════════════
 */
public class SemanticAnalyzer {

    private final SymbolTable        rootScope;
    private       SymbolTable        currentScope;
    private final List<SemanticError> errors       = new ArrayList<>();
    private final Set<String>         seenBlocks   = new HashSet<>();

    // ── HTML structural check state ────────────────────────────────────────
    /** Accumulates every {@code id} attribute value seen across the entire document. */
    private final Set<String>         globalHtmlIds = new HashSet<>();
    /** Ancestor-tag stack maintained while descending through HtmlElementNode tree. */
    private final Deque<String>       tagStack      = new ArrayDeque<>();

    /**
     * When true, the analyser is currently descending through the value
     * expression of an HTML attribute (AttributeJinjaExpressionNode).
     * Any function call encountered while this flag is set is subject to
     * the attribute-safety check (Rule ③).
     */
    private boolean inAttributeContext = false;

    public SemanticAnalyzer(SymbolTable rootScope) {
        this.rootScope    = rootScope;
        this.currentScope = rootScope;
    }

    public List<SemanticError> getErrors() {
        return errors;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Entry point
    // ══════════════════════════════════════════════════════════════════════

    public void analyze(ASTTemplate template) {
        for (ContentNode node : template.getContents()) {
            analyzeContent(node);
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Content-level dispatch
    // ══════════════════════════════════════════════════════════════════════

    private void analyzeContent(ContentNode node) {
        if (node == null) return;

        if      (node instanceof HtmlElementNode    elem)  analyzeElement(elem);
        else if (node instanceof ASTBlock           block) analyzeBlock(block);
        else if (node instanceof ASTJinjaExpression expr)  analyzeBodyExpression(expr);
        else if (node instanceof ASTJinjaStatementNode)    { /* no checks */ }
        else if (node instanceof ASTStyle)                 { /* CSS: no Jinja */ }
        // TextNode, DoctypeNode — nothing to check
    }

    // ══════════════════════════════════════════════════════════════════════
    // HTML element
    // ══════════════════════════════════════════════════════════════════════

    private void analyzeElement(HtmlElementNode elem) {
        String tag = elem.getTagName().toLowerCase();

        // ── Rule: Invalid tag nesting — <a> inside <a> ────────────────────
        if (tag.equals("a") && tagStack.contains("a")) {
            errors.add(new SemanticError(
                    SemanticError.ErrorType.INVALID_TAG_NESTING,
                    "Nested anchor '<a>' elements are not allowed",
                    elem.getLine()
            ));
        }

        // ── Rule: Invalid tag nesting — <li> outside <ul>/<ol> ───────────
        if (tag.equals("li") && !tagStack.contains("ul") && !tagStack.contains("ol")) {
            errors.add(new SemanticError(
                    SemanticError.ErrorType.INVALID_TAG_NESTING,
                    "List item '<li>' must be nested inside a '<ul>' or '<ol>' element",
                    elem.getLine()
            ));
        }

        // ── Rule: Global duplicate HTML id ────────────────────────────────
        for (AttributeNode attr : elem.getAttributes()) {
            if (attr.getName().equalsIgnoreCase("id") && attr.getValue() instanceof StringLiteralNode lit) {
                String idValue = lit.getValue().trim();
                if (!idValue.isEmpty()) {
                    if (globalHtmlIds.contains(idValue)) {
                        errors.add(new SemanticError(
                                SemanticError.ErrorType.DUPLICATE_HTML_ID,
                                "Duplicate global HTML id '" + idValue + "'",
                                attr.getLine()
                        ));
                    } else {
                        globalHtmlIds.add(idValue);
                    }
                }
            }
        }

        tagStack.push(tag);

        for (AttributeNode attr : elem.getAttributes()) {
            analyzeAttribute(attr);
        }
        for (ContentNode child : elem.getChildren()) {
            analyzeContent(child);
        }
        // ── Rule ⑤: unused loop variable ──────────────────────────────
        // for/endfor pairs that live inside this element's own child list
        // (e.g. a {% for %} placed between <table> and its matching <tr>)
        // are checked against that same child list.
        checkUnusedLoopVariablesIn(elem.getChildren());

        tagStack.pop();
    }

    /**
     * Analyses a single attribute's value expression.
     * Enables the attribute-context flag for the duration of the descent so
     * that any function call inside the attribute value is subject to Rule ③.
     */
    private void analyzeAttribute(AttributeNode attr) {
        ASTExpression val = attr.getValue();
        if (!(val instanceof AttributeJinjaExpressionNode jinjaAttr)) {
            return; // plain string literal — no semantic checks
        }

        ASTJinjaExpression inner = jinjaAttr.getExpression();
        if (inner == null) return;

        boolean wasInAttr = inAttributeContext;
        inAttributeContext = true;

        analyzeExpression(inner.getExpression(), inner.getLine());

        inAttributeContext = wasInAttr;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Jinja expressions in body content (NOT inside attribute values)
    // ══════════════════════════════════════════════════════════════════════

    private void analyzeBodyExpression(ASTJinjaExpression expr) {
        boolean wasInAttr = inAttributeContext;
        inAttributeContext = false;   // body context — attribute flag must be off

        analyzeExpression(expr.getExpression(), expr.getLine());

        inAttributeContext = wasInAttr;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Block  (opens child scope)
    // ══════════════════════════════════════════════════════════════════════

    private void analyzeBlock(ASTBlock block) {
        // ── Rule ④: duplicate block ────────────────────────────────────
        if (seenBlocks.contains(block.getBlockName())) {
            errors.add(new SemanticError(
                    SemanticError.ErrorType.DUPLICATE_BLOCK,
                    "Duplicate block declaration '" + block.getBlockName() + "'",
                    block.getLine()
            ));
        } else {
            seenBlocks.add(block.getBlockName());
        }

        SymbolTable blockScope = resolveBlockScope(block.getBlockName());
        SymbolTable previous   = currentScope;
        if (blockScope != null) currentScope = blockScope;

        for (ContentNode c : block.getContents()) {
            analyzeContent(c);
        }

        // ── Rule ⑤: unused loop variable ──────────────────────────────
        // for/endfor pairs that live directly inside this block's own
        // content list (e.g. {% for product in products %} placed right
        // under {% block content %}) are checked against that list.
        checkUnusedLoopVariablesIn(block.getContents());

        currentScope = previous;
    }

    /**
     * Locates the child SymbolTable created by HtmlSymbolTableVisitor for
     * the given block name.
     *
     * ── How the symbol table is structured ───────────────────────────────
     *
     * HtmlSymbolTableVisitor.visitBlock() does:
     *   1. Defines a BLOCK symbol for the block name in the PARENT (current) scope.
     *   2. Creates a CHILD scope labelled "block:<name>".
     *   3. Populates the child scope with all symbols from the block's body.
     *
     * Therefore the child scope is identified by its label "block:<name>",
     * NOT by containing a BLOCK symbol with the block's name (which lives in
     * the parent scope, not the child).
     *
     * The previous implementation searched child scopes for a BLOCK symbol
     * matching the name — which always returned null because the BLOCK symbol
     * is in the parent.  This caused the analyser to stay in the parent scope
     * while checking block-body variables, producing false UNDEFINED_VARIABLE
     * errors for every variable that was only registered inside the block scope.
     *
     * FIX: match child scopes by their label ("block:<name>") instead.
     */
    private SymbolTable resolveBlockScope(String blockName) {
        String expectedLabel = "block:" + blockName;
        for (SymbolTable child : currentScope.getChildren()) {
            if (expectedLabel.equals(child.getLabel())) {
                return child;
            }
        }
        return null;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Rule ⑤: Unused loop variable
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Scans one sibling list (a block's contents, or an HTML element's
     * children) for {% for %} ... {% endfor %} pairs and reports an
     * UNUSED_LOOP_VARIABLE error for any loop whose bound variable is never
     * referenced anywhere in the body between them.
     *
     * Algorithm:
     *   1. Walk the list with an index i.
     *   2. When siblings.get(i) is a "for" ASTJinjaStatementNode, parse its
     *      iteration variable name from "iterVar in iterableVar".
     *   3. Scan forward from i+1 for the next "endfor" ASTJinjaStatementNode
     *      in the SAME list — the grammar's content* production guarantees
     *      a for/endfor pair is always emitted as siblings of one another,
     *      so the matching endfor is always found in this same list (never
     *      in a different container).
     *   4. The loop body is every sibling strictly between the for and the
     *      matching endfor. Recursively collect every ASTVariable reference
     *      found anywhere in that body's subtree (including nested
     *      HtmlElementNode descendants, attribute values, and nested for
     *      loops) and check whether any of them is the loop variable itself
     *      or a dotted access rooted at the loop variable.
     *   5. If none are found, report UNUSED_LOOP_VARIABLE at the for
     *      statement's line.
     *   6. Continue scanning after the matching endfor (loops cannot be
     *      double-counted; nested for-loops inside the body are handled by
     *      the recursive descent in step 4's variable collection AND by the
     *      normal recursive analyzeElement/analyzeBlock calls that already
     *      visit nested containers independently).
     */
    private void checkUnusedLoopVariablesIn(List<ContentNode> siblings) {
        for (int i = 0; i < siblings.size(); i++) {
            ContentNode node = siblings.get(i);

            if (!(node instanceof ASTJinjaStatementNode stmt)) continue;
            if (!"for".equals(stmt.getStatementType())) continue;

            String[] parts = stmt.getContent().trim().split("\\s+in\\s+", 2);
            if (parts.length != 2) continue;
            String iterVar = parts[0].trim();

            // Sanity-check against the Symbol Table: the loop variable must
            // have been registered as a VARIABLE in the current scope by
            // HtmlSymbolTableVisitor. If it wasn't (which should not happen
            // given the existing infrastructure), skip the usage check
            // rather than risk a false positive from an inconsistent state.
            if (currentScope.lookupVariable(iterVar) == null) {
                continue;
            }

            // Find the matching endfor among the remaining siblings.
            int endIndex = -1;
            for (int j = i + 1; j < siblings.size(); j++) {
                if (siblings.get(j) instanceof ASTJinjaStatementNode s2
                        && "endfor".equals(s2.getStatementType())) {
                    endIndex = j;
                    break;
                }
            }
            if (endIndex == -1) {
                // No matching endfor found in this list — nothing to scan;
                // leave silently (should not happen given the grammar).
                continue;
            }

            boolean used = false;
            for (int k = i + 1; k < endIndex && !used; k++) {
                if (subtreeReferencesVariable(siblings.get(k), iterVar)) {
                    used = true;
                }
            }

            if (!used) {
                errors.add(new SemanticError(
                        SemanticError.ErrorType.UNUSED_LOOP_VARIABLE,
                        "Loop variable '" + iterVar + "' is never used in the body of "
                                + "'{% for " + stmt.getContent().trim() + " %}'",
                        stmt.getLine()
                ));
            }

            // Resume scanning after the matching endfor — already-covered
            // siblings between i and endIndex are skipped naturally since
            // the outer loop only inspects "for" nodes, and nested for/endfor
            // pairs inside the body are handled by the normal recursive
            // analyzeElement/analyzeBlock traversal of that nested container.
        }
    }

    /**
     * Returns true if the given AST subtree contains any ASTVariable
     * reference whose root identifier is exactly targetName — i.e. either
     * the bare variable ({{ targetName }}) or a dotted access rooted at it
     * ({{ targetName.field }}, {{ targetName.field.sub }}, ...).
     *
     * Recurses through every node kind that can contain content or
     * expressions: HtmlElementNode (attributes + children), ASTBlock
     * (contents), ASTJinjaExpression (body {{ }} expressions),
     * AttributeJinjaExpressionNode (attribute {{ }} expressions),
     * ASTFunctionCall (arguments), NamedArgumentNode (value).
     */
    private boolean subtreeReferencesVariable(ASTNode node, String targetName) {
        if (node == null) return false;

        if (node instanceof ASTVariable variable) {
            List<String> ids = variable.getIdentifiers();
            return !ids.isEmpty() && ids.get(0).equals(targetName);
        }

        if (node instanceof HtmlElementNode elem) {
            for (AttributeNode attr : elem.getAttributes()) {
                if (subtreeReferencesVariable(attr.getValue(), targetName)) return true;
            }
            for (ContentNode child : elem.getChildren()) {
                if (subtreeReferencesVariable(child, targetName)) return true;
            }
            return false;
        }

        if (node instanceof ASTBlock block) {
            for (ContentNode c : block.getContents()) {
                if (subtreeReferencesVariable(c, targetName)) return true;
            }
            return false;
        }

        if (node instanceof ASTJinjaExpression expr) {
            return subtreeReferencesVariable(expr.getExpression(), targetName);
        }

        if (node instanceof AttributeJinjaExpressionNode attrExpr) {
            return subtreeReferencesVariable(attrExpr.getExpression(), targetName);
        }

        if (node instanceof ASTFunctionCall call) {
            for (ASTNode arg : call.getArguments()) {
                if (subtreeReferencesVariable(arg, targetName)) return true;
            }
            return false;
        }

        if (node instanceof NamedArgumentNode named) {
            return subtreeReferencesVariable(named.getValue(), targetName);
        }

        // TextNode, StringLiteralNode, DoctypeNode, ASTStyle,
        // ASTJinjaStatementNode (for/endfor/extends) — no variable references
        return false;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Expression dispatch
    // ══════════════════════════════════════════════════════════════════════

    private void analyzeExpression(ASTExpression expression, int contextLine) {
        if (expression == null) return;

        if      (expression instanceof ASTVariable     var)  checkVariable(var);
        else if (expression instanceof ASTFunctionCall call) checkFunctionCall(call);
        // StringLiteralNode — always valid, no checks
    }

    // ══════════════════════════════════════════════════════════════════════
    // Rule ①: Undefined variable
    // ══════════════════════════════════════════════════════════════════════

    private void checkVariable(ASTVariable variable) {
        String name = variable.getFullName();
        Symbol s = currentScope.lookupVariable(name);
        if (s == null) {
            errors.add(new SemanticError(
                    SemanticError.ErrorType.UNDEFINED_VARIABLE,
                    "Undefined variable '" + name + "'",
                    variable.getLine()
            ));
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Rules ② + ③: Function call validation + attribute-context safety
    // ══════════════════════════════════════════════════════════════════════

    private void checkFunctionCall(ASTFunctionCall call) {
        String fname      = call.getFunctionName();
        Symbol funcSymbol = currentScope.lookupFunction(fname);

        // ── Rule ②a: Undefined function ───────────────────────────────
        if (funcSymbol == null) {
            errors.add(new SemanticError(
                    SemanticError.ErrorType.UNDEFINED_FUNCTION,
                    "Call to undeclared function '" + fname + "'",
                    call.getLine()
            ));
            // Still recurse into arguments to catch nested undefined variables
            analyzeArguments(call);
            return;
        }

        FunctionSignature sig = funcSymbol.getSignature();

        if (sig != null) {

            // ── Rule ③: Non-attribute-safe function in attribute context ─
            if (inAttributeContext && !sig.isAttributeSafe()) {
                errors.add(new SemanticError(
                        SemanticError.ErrorType.NON_ATTRIBUTE_SAFE_FUNCTION_IN_ATTRIBUTE_CONTEXT,
                        "Function '" + fname + "' produces HTML markup and cannot "
                                + "be used as an HTML attribute value. "
                                + "(" + sig.getDoc() + ")",
                        call.getLine()
                ));
                // Continue checking — do not short-circuit, additional errors may apply.
            }

            // ── Rule ②b: Missing required positional arguments ────────
            long suppliedPositional = call.getArguments().stream()
                    .filter(a -> !(a instanceof NamedArgumentNode))
                    .count();

            long requiredPositional = sig.getRequiredPositionalParams().size();

            if (suppliedPositional < requiredPositional) {
                errors.add(new SemanticError(
                        SemanticError.ErrorType.MISSING_REQUIRED_POSITIONAL_ARGUMENT,
                        "Function '" + fname + "' requires "
                                + requiredPositional + " positional argument(s), "
                                + "but " + suppliedPositional + " were supplied",
                        call.getLine()
                ));
            }

            // ── Rule ②c + ②d: Named argument validation ───────────────
            // Only validate named args when the signature declares a closed
            // named-param list (i.e. at least one non-positional parameter).
            if (sig.hasNamedParams()) {

                List<String> suppliedNamed = new ArrayList<>();
                for (ASTNode arg : call.getArguments()) {
                    if (arg instanceof NamedArgumentNode named) {
                        suppliedNamed.add(named.getName());
                    }
                }

                // ②c: Missing required named args
                for (ParameterDescriptor req : sig.getRequiredNamedParams()) {
                    if (!suppliedNamed.contains(req.getName())) {
                        errors.add(new SemanticError(
                                SemanticError.ErrorType.MISSING_REQUIRED_NAMED_ARGUMENT,
                                "Function '" + fname
                                        + "' is missing required named argument '"
                                        + req.getName() + "' (expected type: "
                                        + req.getTypeHint() + ")",
                                call.getLine()
                        ));
                    }
                }

                // ②d: Unexpected named args (only for closed signatures)
                for (String supplied : suppliedNamed) {
                    if (sig.findParam(supplied).isEmpty()) {
                        errors.add(new SemanticError(
                                SemanticError.ErrorType.UNEXPECTED_NAMED_ARGUMENT,
                                "Function '" + fname
                                        + "' received unknown named argument '"
                                        + supplied + "'",
                                call.getLine()
                        ));
                    }
                }
            }
        }

        // Recurse into argument expressions
        analyzeArguments(call);
    }

    // ── Argument sub-expression analysis ──────────────────────────────────

    private void analyzeArguments(ASTFunctionCall call) {
        for (ASTNode arg : call.getArguments()) {
            if      (arg instanceof ASTVariable     v) checkVariable(v);
            else if (arg instanceof ASTFunctionCall c) checkFunctionCall(c);
            else if (arg instanceof NamedArgumentNode named) {
                ASTExpression val = named.getValue();
                if      (val instanceof ASTVariable     v) checkVariable(v);
                else if (val instanceof ASTFunctionCall c) checkFunctionCall(c);
            }
            // StringLiteralNode — no checks
        }
    }
}
