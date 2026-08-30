package html;

import AST.ASTNode;
import AST.ASTTemplate;
import Semantic.FunctionRegistry;
import Semantic.SemanticAnalyzer;
import Semantic.SemanticError;
import Visitor.ASTVisitor;
import Visitor.HtmlSymbolTableVisitor;
import antlar.htmlLexer;
import antlar.htmlParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

/**
 * Compiler front-end pipeline for an HTML/Jinja2 template file:
 *
 *   Step 1 — Lex + Parse        (ANTLR-generated htmlLexer / htmlParser)
 *   Step 2 — AST Construction   (ASTVisitor)
 *   Step 3 — Function Registry  (FunctionRegistry.populate)
 *   Step 4 — Symbol Table Build (HtmlSymbolTableVisitor)
 *   Step 5 — Semantic Analysis  (SemanticAnalyzer)
 */
public class HtmlRunner {

    public static void run(String path) throws Exception {

        // ── Step 1: Lex and parse ──────────────────────────────────────
        CharStream       input  = CharStreams.fromFileName(path);
        htmlLexer        lexer  = new htmlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        htmlParser        parser = new htmlParser(tokens);
        ParseTree         tree   = parser.template();

        // ── Step 2: Build AST ─────────────────────────────────────────
        ASTVisitor astVisitor = new ASTVisitor();
        ASTNode    astRoot    = astVisitor.visit(tree);

        System.out.println("==== HTML AST ====");
        System.out.println(astRoot.print(""));

        // ── Step 3: Pre-declare built-in functions ────────────────────
        // Must happen BEFORE the symbol-table walk so that every call site
        // encountered by HtmlSymbolTableVisitor can resolve against a full
        // FunctionSignature rather than a bare observation symbol.
        HtmlSymbolTableVisitor stVisitor = new HtmlSymbolTableVisitor();
        FunctionRegistry.populate(stVisitor.getSymbolTable());

        // ── Step 4: Symbol table walk ─────────────────────────────────
        stVisitor.visit(astRoot);

        System.out.println("==== HTML Symbol Table ====");
        stVisitor.getSymbolTable().printHierarchy("");

        // ── Step 5: Semantic analysis ─────────────────────────────────
        if (astRoot instanceof ASTTemplate template) {
            SemanticAnalyzer analyzer = new SemanticAnalyzer(stVisitor.getSymbolTable());
            analyzer.analyze(template);

            List<SemanticError> errors = analyzer.getErrors();

            System.out.println("\n==== Semantic Analysis ====");
            if (errors.isEmpty()) {
                System.out.println("No semantic errors found.");
            } else {
                for (SemanticError err : errors) {
                    System.out.println(err);
                }
                System.out.println("\nTotal: " + errors.size() + " semantic error(s).");
            }
        }
    }
}
