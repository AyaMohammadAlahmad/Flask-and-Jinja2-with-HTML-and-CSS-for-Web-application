package antlr;

import org.antlr.v4.runtime.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public abstract class PyLexerBase extends Lexer {
    // تعيين حجم التاب هنا مباشرة يمنع مشاكل التجميع العكسي
    public static final int TAB_SIZE = 4;

    private final LinkedList<Token> tokens = new LinkedList<>();
    private final Deque<Integer> indents = new ArrayDeque<>();
    private int opened = 0;
    private Token lastToken = null;

    public PyLexerBase(CharStream input) {
        super(input);
    }

    @Override
    public void emit(Token token) {
        super.setToken(token);
        tokens.offer(token);
    }

    @Override
    public Token nextToken() {
        if (_input.LA(1) == Token.EOF && !indents.isEmpty()) {
            for (int i = tokens.size() - 1; i >= 0; i--) {
                if (tokens.get(i).getType() == Token.EOF) {
                    tokens.remove(i);
                }
            }
            emit(commonToken(pyLexer.NEWLINE, "\n"));
            while (!indents.isEmpty()) {
                emit(createDedent());
                indents.pop();
            }
            emit(commonToken(Token.EOF, ""));
        }
        Token next = super.nextToken();
        if (next.getChannel() == Token.DEFAULT_CHANNEL) {
            lastToken = next;
        }
        return tokens.isEmpty() ? next : tokens.poll();
    }

    protected void openBrace() { opened++; }
    protected void closeBrace() { if (opened > 0) opened--; }
    protected boolean atStartOfInput() { return getCharPositionInLine() == 0 && getLine() == 1; }

    protected void onNewLine() {
        String newLine = getText().replaceAll("[^\r\n\f]+", "");
        String spaces = getText().replaceAll("[\r\n\f]+", "");
        int next = _input.LA(1);
        int nextNext = _input.LA(2);

        if (opened > 0 || (nextNext != Token.EOF && (next == '\r' || next == '\n' || next == '\f' || next == '#'))) {
            skip();
            return;
        }

        emit(commonToken(pyLexer.NEWLINE, newLine));
        int indent = getIndentationCount(spaces);
        int previous = indents.isEmpty() ? 0 : indents.peek();

        if (indent == previous) {
            skip();
        } else if (indent > previous) {
            indents.push(indent);
            emit(commonToken(pyLexer.INDENT, spaces)); // تم تبسيطها لتعتمد على التوليد الديناميكي
        } else {
            while (!indents.isEmpty() && indents.peek() > indent) {
                emit(createDedent());
                indents.pop();
            }
        }
    }

    private Token createDedent() {
        CommonToken dedent = commonToken(pyLexer.DEDENT, "");
        if (lastToken != null) {
            dedent.setLine(lastToken.getLine());
        }
        return dedent;
    }

    private CommonToken commonToken(int type, String text) {
        int stop = getCharIndex() - 1;
        int start = text.isEmpty() ? stop : stop - text.length() + 1;
        return new CommonToken(_tokenFactorySourcePair, type, Lexer.DEFAULT_TOKEN_CHANNEL, start, stop);
    }

    private static int getIndentationCount(String spaces) {
        int count = 0;
        for (char ch : spaces.toCharArray()) {
            if (ch == '\t') {
                count += TAB_SIZE - (count % TAB_SIZE); // تستخدم المتغير المحلي المحمي الآن
            } else {
                count++;
            }
        }
        return count;
    }

    @Override
    public void reset() {
        tokens.clear();
        indents.clear();
        opened = 0;
        lastToken = null;
        super.reset();
    }
}