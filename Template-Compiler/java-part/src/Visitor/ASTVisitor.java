package Visitor;

import AST.*;
import antlar.htmlParser;
import antlar.htmlParserBaseVisitor;
import antlar.htmlLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ANTLR visitor that builds the AST from the parse tree.
 *
 * Responsibilities (pure AST construction — no semantic logic):
 *   - Maps parse tree nodes to AST nodes
 *   - Maintains the block stack so Jinja blocks correctly collect their
 *     inner content without mixing with the surrounding HTML element tree
 *   - Delegates attribute value parsing for embedded Jinja expressions
 *
 * The visitor does NOT register symbols or perform any semantic checks;
 * those are the responsibility of HtmlSymbolTableVisitor and SemanticAnalyzer.
 *
 * ── Block stack protocol ─────────────────────────────────────────────────
 *
 * When visitBlockStatement() fires it:
 *   1. Creates the ASTBlock node.
 *   2. Immediately attaches it to the correct parent (template root or current
 *      block) BEFORE pushing it onto blockStack.
 *   3. Pushes it onto blockStack so that subsequent TOP-LEVEL sibling content
 *      nodes (i.e. parse-tree siblings of the {% block %} statement inside
 *      template's or an enclosing block's content* list) are routed into the
 *      block rather than into the template root.
 *
 * This ordering is critical: pushing FIRST and then routing would cause the
 * block node itself to be routed into its own content list (self-parenting bug).
 *
 * visitEndblockStatement() pops the stack so content after the endblock
 * resumes being routed to whatever is now on top (or to the template root).
 *
 * IMPORTANT — blockStack scope: blockStack is consulted ONLY by visitTemplate()
 * (and by visitBlockStatement()/visitEndblockStatement() for stack management).
 * It is deliberately NOT consulted inside visitNormalElement(). An HTML
 * element's own content* children (text, nested elements, Jinja expressions
 * found directly inside <tag>...</tag>) always belong to that element,
 * regardless of whether a Jinja block happens to be open — the parse tree's
 * actual nesting (html_element : html_open_tag content* html_close_tag)
 * already tells us the true parent. Routing element-internal content through
 * blockStack was the cause of TextNode / nested HtmlElementNode appearing as
 * siblings of their containing element instead of as its children.
 */
public class ASTVisitor extends htmlParserBaseVisitor<ASTNode> {

    /**
     * Stack of open ASTBlock nodes.  While a block is on the stack, content
     * nodes produced by visitContent() are routed into the block rather than
     * into the current HTML element or template root.
     *
     * INVARIANT: the block at the top of this stack has ALREADY been added to
     * its parent (template root or enclosing block) before being pushed here.
     * New content is therefore appended to the block's child list — never to
     * the block's parent again.
     */
    private final Deque<ASTBlock> blockStack = new ArrayDeque<>();

    /**
     * The ASTTemplate being built.  Kept as a field so that
     * visitBlockStatement() can attach a new ASTBlock directly to the template
     * root when no other block is open, without needing the template to be
     * passed as a parameter through every visit method.
     */
    private ASTTemplate currentTemplate = null;

    // ─────────────────────────────────────────────────────────────────────
    // Template root
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitTemplate(htmlParser.TemplateContext ctx) {
        ASTTemplate root = new ASTTemplate(ctx.getStart().getLine());
        currentTemplate = root;

        for (htmlParser.ContentContext c : ctx.content()) {
            ASTNode node = visit(c);
            // ASTBlock nodes are attached to their parent directly inside
            // visitBlockStatement() — do NOT re-add them here.
            if (node instanceof ASTBlock) {
                // Already attached; skip.
                continue;
            }
            if (node instanceof ContentNode content) {
                if (!blockStack.isEmpty()) {
                    blockStack.peek().addContent(content);
                } else {
                    root.addContent(content);
                }
            }
        }

        currentTemplate = null;
        return root;
    }

    // ─────────────────────────────────────────────────────────────────────
    // Content dispatch
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitContent(htmlParser.ContentContext ctx) {

        if (ctx.doctype() != null)
            return visit(ctx.doctype());

        if (ctx.html_element() != null)
            return visit(ctx.html_element());

        if (ctx.style_element() != null)
            return visit(ctx.style_element());

        if (ctx.jinja_statement() != null)
            return visit(ctx.jinja_statement());

        if (ctx.jinja_expression() != null)
            return visit(ctx.jinja_expression());

        if (ctx.TEXT() != null) {
            String text = ctx.TEXT().getText().trim();
            if (!text.isEmpty()) {
                return new TextNode(text, ctx.getStart().getLine());
            }
        }

        return null;
    }

    // ─────────────────────────────────────────────────────────────────────
    // HTML elements
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitDoctype(htmlParser.DoctypeContext ctx) {
        return new DoctypeNode(ctx.getText(), ctx.getStart().getLine());
    }

    @Override
    public ASTNode visitNormalElement(htmlParser.NormalElementContext ctx) {
        String openTag  = ctx.html_open_tag().IDENTIFIER().getText();
        String closeTag = ctx.html_close_tag().IDENTIFIER().getText();

        if (!openTag.equals(closeTag)) {
            throw new RuntimeException(
                    "HTML tag mismatch at line "
                            + ctx.getStart().getLine()
                            + ": <" + openTag + "> closed by </" + closeTag + ">"
            );
        }

        HtmlElementNode node = new HtmlElementNode(
                openTag, false, ctx.getStart().getLine());

        if (ctx.html_open_tag().attribute_list() != null) {
            for (var a : ctx.html_open_tag().attribute_list().attribute()) {
                ASTNode attr = visit(a);
                if (attr instanceof AttributeNode attributeNode) {
                    node.addAttribute(attributeNode);
                }
            }
        }

        for (var c : ctx.content()) {
            ASTNode child = visit(c);
            // ASTBlock nodes are attached to their parent directly inside
            // visitBlockStatement() — do NOT re-add them here.
            if (child instanceof ASTBlock) {
                // Already attached; skip.
                continue;
            }
            if (child instanceof ContentNode content) {
                // IMPORTANT: content produced while parsing the inside of an
                // HTML element ALWAYS belongs to that element, regardless of
                // whether a Jinja block happens to be open at the template
                // level. blockStack only governs how TOP-LEVEL template
                // content (siblings of this element in the parse tree) is
                // routed — it must never reach inside an element's own
                // child list. Routing here via blockStack was the bug that
                // caused TextNode/HtmlElementNode to appear as siblings
                // (e.g. "TextNode: All products" next to "<h2>") instead of
                // the text being a child of the element that contains it.
                node.addChild(content);
            }
        }

        return node;
    }

    @Override
    public ASTNode visitSelfClosingElement(htmlParser.SelfClosingElementContext ctx) {
        String tag = ctx.html_self_closing_tag().IDENTIFIER().getText();
        HtmlElementNode node = new HtmlElementNode(tag, true, ctx.getStart().getLine());

        if (ctx.html_self_closing_tag().attribute_list() != null) {
            for (var a : ctx.html_self_closing_tag().attribute_list().attribute()) {
                ASTNode attr = visit(a);
                if (attr instanceof AttributeNode attributeNode) {
                    node.addAttribute(attributeNode);
                }
            }
        }

        return node;
    }

    @Override
    public ASTNode visitVoidElement(htmlParser.VoidElementContext ctx) {
        String tag = ctx.html_void_element().VOID_TAG().getText();
        HtmlElementNode node = new HtmlElementNode(tag, true, ctx.getStart().getLine());

        if (ctx.html_void_element().attribute_list() != null) {
            for (var a : ctx.html_void_element().attribute_list().attribute()) {
                ASTNode attr = visit(a);
                if (attr instanceof AttributeNode attributeNode) {
                    node.addAttribute(attributeNode);
                }
            }
        }

        return node;
    }

    // ─────────────────────────────────────────────────────────────────────
    // Attributes
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitAttribute(htmlParser.AttributeContext ctx) {
        ASTNode valueNode = visit(ctx.attribute_value());

        if (!(valueNode instanceof ASTExpression expression)) {
            return null;
        }

        return new AttributeNode(
                ctx.IDENTIFIER().getText(),
                expression,
                ctx.getStart().getLine()
        );
    }

    /**
     * Handles attribute values that are plain strings ("…") or strings that
     * embed a Jinja expression ("{{ expr }}").
     */
    @Override
    public ASTNode visitStringAttribute(htmlParser.StringAttributeContext ctx) {
        String raw   = ctx.STRING().getText();
        String value = raw.substring(1, raw.length() - 1); // strip quotes

        // Detect embedded Jinja expression {{ … }}
        if (value.startsWith("{{") && value.endsWith("}}")) {
            String inner = value.substring(2, value.length() - 2).trim();

            CharStream cs = CharStreams.fromString("{{ " + inner + " }}");
            htmlLexer lexer = new htmlLexer(cs);
            htmlParser parser = new htmlParser(new CommonTokenStream(lexer));

            htmlParser.Jinja_expressionContext exprCtx = parser.jinja_expression();
            ASTNode result = visit(exprCtx);

            if (result instanceof ASTJinjaExpression jinjaExpr) {
                int originalLine = ctx.getStart().getLine();

                ASTJinjaExpression fixedExpr = new ASTJinjaExpression(
                        jinjaExpr.getExpression(), originalLine);
                propagateLine(fixedExpr, originalLine);

                return new AttributeJinjaExpressionNode(fixedExpr, originalLine);
            }
        }

        return new StringLiteralNode(value, ctx.getStart().getLine());
    }

    @Override
    public ASTNode visitJinjaAttribute(htmlParser.JinjaAttributeContext ctx) {
        // The attribute value is a bare {{ expr }} — delegate to jinja_expression
        ASTNode result = visit(ctx.jinja_expression());
        if (result instanceof ASTJinjaExpression jinjaExpr) {
            return new AttributeJinjaExpressionNode(jinjaExpr, jinjaExpr.getLine());
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────────────
    // Jinja expressions
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitJinja_expression(htmlParser.Jinja_expressionContext ctx) {
        ASTNode exprNode = visit(ctx.expression());

        if (!(exprNode instanceof ASTExpression expr)) {
            return null;
        }

        return new ASTJinjaExpression(expr, ctx.getStart().getLine());
    }

    // ─────────────────────────────────────────────────────────────────────
    // Jinja statements
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitJinja_statement(htmlParser.Jinja_statementContext ctx) {
        return visit(ctx.jinja_stmt_body());
    }

    /**
     * Creates an ASTBlock, immediately attaches it to the correct parent
     * (the enclosing open block, or the template root), THEN pushes it onto
     * blockStack so that subsequent sibling content is routed into it.
     *
     * Attaching BEFORE pushing is essential: if we pushed first, the routing
     * logic in visitTemplate / visitNormalElement would see a non-empty stack
     * and incorrectly add the block node to its own content list (self-parenting).
     */
    @Override
    public ASTNode visitBlockStatement(htmlParser.BlockStatementContext ctx) {
        String blockName = ctx.block_stmt().IDENTIFIER_J().getText();
        ASTBlock block = new ASTBlock(blockName, ctx.getStart().getLine());

        // Attach the block to the correct parent BEFORE pushing onto the stack.
        if (!blockStack.isEmpty()) {
            // We are inside another open block — add as child of that block.
            blockStack.peek().addContent(block);
        } else if (currentTemplate != null) {
            // Top-level block — add to the template root.
            currentTemplate.addContent(block);
        }
        // else: visitBlockStatement called outside a template context (should not happen).

        // Now push: subsequent sibling content nodes will be routed into this block.
        blockStack.push(block);

        // Return the block so callers can recognise it as an ASTBlock and skip
        // re-adding it (see the 'instanceof ASTBlock' guard in visitTemplate
        // and visitNormalElement).
        return block;
    }

    @Override
    public ASTNode visitEndblockStatement(htmlParser.EndblockStatementContext ctx) {
        if (!blockStack.isEmpty()) {
            blockStack.pop();
        }
        // endblock itself produces no AST node — the block was already
        // attached to its parent when the opening {% block %} was encountered.
        return null;
    }

    @Override
    public ASTNode visitExtendsStatement(htmlParser.ExtendsStatementContext ctx) {
        String templatePath = ctx.extends_stmt().STRING_J().getText();
        return new ASTJinjaStatementNode(
                "extends",
                templatePath,
                ctx.getStart().getLine()
        );
    }

    @Override
    public ASTNode visitForStatement(htmlParser.ForStatementContext ctx) {
        String iterVar      = ctx.for_stmt().IDENTIFIER_J(0).getText();
        String iterableVar  = ctx.for_stmt().IDENTIFIER_J(1).getText();
        return new ASTJinjaStatementNode(
                "for",
                iterVar + " in " + iterableVar,
                ctx.getStart().getLine()
        );
    }

    @Override
    public ASTNode visitEndforStatement(htmlParser.EndforStatementContext ctx) {
        return new ASTJinjaStatementNode("endfor", "", ctx.getStart().getLine());
    }

    // ─────────────────────────────────────────────────────────────────────
    // Expressions
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitVariable(htmlParser.VariableContext ctx) {
        ASTVariable variable = new ASTVariable(ctx.getStart().getLine());
        for (var id : ctx.IDENTIFIER_E()) {
            variable.addIdentifier(id.getText());
        }
        return variable;
    }

    @Override
    public ASTNode visitFunction_call(htmlParser.Function_callContext ctx) {
        ASTFunctionCall function = new ASTFunctionCall(
                ctx.IDENTIFIER_E().getText(),
                ctx.getStart().getLine()
        );

        if (ctx.argument_list() != null) {
            for (var arg : ctx.argument_list().argument()) {
                ASTNode argNode = visit(arg);
                if (argNode != null) {
                    function.addArgument(argNode);
                }
            }
        }

        return function;
    }

    @Override
    public ASTNode visitStringArgument(htmlParser.StringArgumentContext ctx) {
        return new StringLiteralNode(
                ctx.STRING_E().getText(),
                ctx.getStart().getLine()
        );
    }

    @Override
    public ASTNode visitStringExpression(htmlParser.StringExpressionContext ctx) {
        return new StringLiteralNode(
                ctx.STRING_E().getText(),
                ctx.getStart().getLine()
        );
    }

    @Override
    public ASTNode visitExpressionArgument(htmlParser.ExpressionArgumentContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitNamedArgument(htmlParser.NamedArgumentContext ctx) {
        ASTNode expr = visit(ctx.expression());

        if (expr instanceof ASTExpression expression) {
            return new NamedArgumentNode(
                    ctx.IDENTIFIER_E().getText(),
                    expression,
                    ctx.getStart().getLine()
            );
        }

        return null;
    }

    // ─────────────────────────────────────────────────────────────────────
    // CSS / Style element
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitStyle_element(htmlParser.Style_elementContext ctx) {
        ASTStyle style = new ASTStyle(ctx.getStart().getLine());

        if (ctx.css_stylesheet() != null) {
            for (var ruleCtx : ctx.css_stylesheet().css_rule()) {
                CSSRuleNode rule = new CSSRuleNode(ruleCtx.getStart().getLine());

                for (var selCtx : ruleCtx.css_selector_list().css_selector()) {
                    rule.addSelector(new CSSSelectorNode(
                            selCtx.getText(),
                            selCtx.getStart().getLine()
                    ));
                }

                for (var declCtx : ruleCtx.css_declaration()) {
                    rule.addDeclaration(new CSSDeclarationNode(
                            declCtx.css_property().getText(),
                            declCtx.css_value().getText(),
                            declCtx.getStart().getLine()
                    ));
                }

                style.addRule(rule);
            }
        }

        return style;
    }

    // ─────────────────────────────────────────────────────────────────────
    // Helper: propagate line numbers into re-parsed sub-trees
    // ─────────────────────────────────────────────────────────────────────

    /**
     * Recursively overwrites the line number stored in node and all
     * descendant nodes with the given line.  Used to correct line numbers
     * after an attribute value containing {{ }} is re-parsed from a fresh
     * CharStream (which would otherwise report line 1).
     */
    private void propagateLine(ASTNode node, int line) {
        if (node == null) return;
        node.setLine(line);

        if (node instanceof ASTFunctionCall function) {
            for (ASTNode arg : function.getArguments()) {
                propagateLine(arg, line);
            }
        } else if (node instanceof NamedArgumentNode named) {
            propagateLine(named.getValue(), line);
        } else if (node instanceof ASTJinjaExpression expr) {
            propagateLine(expr.getExpression(), line);
        }
    }
}
