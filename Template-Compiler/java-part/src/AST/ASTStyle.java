package AST;

import java.util.ArrayList;
import java.util.List;

/**
 * HTML DOM layer — represents an inline <style>…</style> block.
 * Contains zero or more CSS rules parsed from the stylesheet.
 */
public class ASTStyle extends ContentNode {

    private final List<CSSRuleNode> rules;

    public ASTStyle(int line) {
        super(line);
        this.rules = new ArrayList<>();
    }

    public void addRule(CSSRuleNode rule) {
        rules.add(rule);
    }

    public List<CSSRuleNode> getRules() {
        return rules;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent).append("ASTStyle (line ").append(line).append(")\n");
        for (CSSRuleNode rule : rules) {
            sb.append(rule.print(indent + "  "));
        }
        return sb.toString();
    }
}
