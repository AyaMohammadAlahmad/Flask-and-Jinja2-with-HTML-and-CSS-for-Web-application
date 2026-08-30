package pyvisitor;
import antlr.pyParser.*;
import antlr.pyParserBaseVisitor;
import pyast.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.List;

/**
 * يحوّل الـ Parse Tree الناتجة من ANTLR إلى كلاسات الـ AST المخصصة.
 *
 * <p>يرث من {@code pyParserBaseVisitor<ASTNode>} ويُعيد كل زيارة
 * نود AST مناسباً بناءً على البديل المحدد (#Label) في الـ grammar.</p>
 *
 * <p>نمط الاستخدام:</p>
 * <pre>
 *   ASTBuilder builder = new ASTBuilder();
 *   Program program = (Program) builder.visit(parseTree);
 * </pre>
 */
public class ASTBuilder extends pyParserBaseVisitor<ASTNode> {

    // ═══════════════════════════════════════════════════════
    //  program  →  ProgramRule
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitProgramRule(ProgramRuleContext ctx) {
        List<TopLevel> stmts = new ArrayList<>();
        for (TopLevelContext tlCtx : ctx.topLevel()) {
            stmts.add((TopLevel) visit(tlCtx));
        }
        int line = ctx.start != null ? ctx.start.getLine() : 1;
        return new Program(line, stmts);
    }

    // ═══════════════════════════════════════════════════════
    //  topLevel  (4 بدائل)
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitTopImport(TopImportContext ctx) {
        return visit(ctx.importStmt());
    }

    @Override
    public ASTNode visitTopDecoratedFunction(TopDecoratedFunctionContext ctx) {
        int line = ctx.start.getLine();
        List<Decorator> decorators = new ArrayList<>();
        for (DecoratorContext dec : ctx.decorator()) {
            decorators.add((Decorator) visit(dec));
        }
        FunctionDef func = (FunctionDef) visit(ctx.functionDef());
        return new DecoratedFunction(line, decorators, func);
    }

    @Override
    public ASTNode visitTopFunction(TopFunctionContext ctx) {
        int line = ctx.start.getLine();
        FunctionDef func = (FunctionDef) visit(ctx.functionDef());
        return new TopFunction(line, func);
    }

    @Override
    public ASTNode visitTopStatement(TopStatementContext ctx) {
        int line = ctx.start.getLine();
        Stmt stmt = (Stmt) visit(ctx.statement());
        return new TopStatement(line, stmt);
    }

    // ═══════════════════════════════════════════════════════
    //  importStmt  (2 بدائل)
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitFromImport(FromImportContext ctx) {
        int line = ctx.start.getLine();
        String module = ctx.Identifier().getText();
        List<String> names = new ArrayList<>();
        for (TerminalNode id : ctx.importList().Identifier()) {
            names.add(id.getText());
        }
        return new FromImport(line, module, names);
    }

    @Override
    public ASTNode visitSimpleImport(SimpleImportContext ctx) {
        int line = ctx.start.getLine();
        String module = ctx.dottedName().getText().replace(" ", "");
        return new SimpleImport(line, module);
    }

    // ═══════════════════════════════════════════════════════
    //  decorator
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitDecorator(DecoratorContext ctx) {
        int line = ctx.start.getLine();
        String name = ctx.dottedName().getText().replace(" ", "");
        List<Argument> args = new ArrayList<>();
        if (ctx.argList() != null) {
            for (ArgumentContext argCtx : ctx.argList().argument()) {
                args.add((Argument) visit(argCtx));
            }
        }
        return new Decorator(line, name, args);
    }

    // ═══════════════════════════════════════════════════════
    //  functionDef
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitFunctionDef(FunctionDefContext ctx) {
        int line = ctx.start.getLine();
        String name = ctx.Identifier().getText();
        List<String> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (TerminalNode id : ctx.paramList().Identifier()) {
                params.add(id.getText());
            }
        }
        Block body = (Block) visit(ctx.block());
        return new FunctionDef(line, name, params, body);
    }

    // ═══════════════════════════════════════════════════════
    //  block  →  BlockRule
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitBlockRule(BlockRuleContext ctx) {
        int line = ctx.start.getLine();
        List<Stmt> stmts = new ArrayList<>();
        for (StatementContext stCtx : ctx.statement()) {
            stmts.add((Stmt) visit(stCtx));
        }
        return new Block(line, stmts);
    }

    // ═══════════════════════════════════════════════════════
    //  statement  (7 بدائل)
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitAssignStmt(AssignStmtContext ctx) {
        return visit(ctx.assignment());
    }

    @Override
    public ASTNode visitReturnS(ReturnSContext ctx) {
        return visit(ctx.returnStmt());
    }

    @Override
    public ASTNode visitIf(IfContext ctx) {
        return visit(ctx.ifStmt());
    }

    @Override
    public ASTNode visitFor(ForContext ctx) {
        return visit(ctx.forStmt());
    }

    @Override
    public ASTNode visitWhile(WhileContext ctx) {
        return visit(ctx.whileStmt());
    }

    @Override
    public ASTNode visitBreakStmt(BreakStmtContext ctx) {
        return new Break(ctx.start.getLine());
    }

    @Override
    public ASTNode visitExprStmt(ExprStmtContext ctx) {
        int line = ctx.start.getLine();
        Expr expr = (Expr) visit(ctx.expr());
        return new ExprStmt(line, expr);
    }

    // ═══════════════════════════════════════════════════════
    //  assignment  (2 بدائل)
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitSimpleAssign(SimpleAssignContext ctx) {
        int line = ctx.start.getLine();
        AssignTarget target = (AssignTarget) visit(ctx.assignTarget());
        Expr value = (Expr) visit(ctx.expr());
        return new Assign(line, target, value);
    }

    @Override
    public ASTNode visitAugAssign(AugAssignContext ctx) {
        int line = ctx.start.getLine();
        String varName = ctx.Identifier().getText();
        String op = ctx.op.getText();
        Expr value = (Expr) visit(ctx.expr());
        return new AugAssign(line, varName, op, value);
    }

    // ═══════════════════════════════════════════════════════
    //  assignTarget
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitAssignTarget(AssignTargetContext ctx) {
        int line = ctx.start.getLine();
        List<TerminalNode> ids = ctx.Identifier();
        String base = ids.get(0).getText();
        List<String> attrs = new ArrayList<>();
        for (int i = 1; i < ids.size(); i++) {
            attrs.add(ids.get(i).getText());
        }
        Expr subscript = null;
        if (ctx.expr() != null) {
            subscript = (Expr) visit(ctx.expr());
        }
        return new AssignTarget(line, base, attrs, subscript);
    }

    // ═══════════════════════════════════════════════════════
    //  returnStmt
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitReturnStmt(ReturnStmtContext ctx) {
        int line = ctx.start.getLine();
        List<Expr> values = new ArrayList<>();
        for (ExprContext exprCtx : ctx.expr()) {
            values.add((Expr) visit(exprCtx));
        }
        return new Return(line, values);
    }

    // ═══════════════════════════════════════════════════════
    //  ifStmt
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitIfStmt(IfStmtContext ctx) {
        int line = ctx.start.getLine();

        // الشرط الرئيسي وجسم الـ if
        Expr condition = (Expr) visit(ctx.expr(0));
        Block thenBlock = (Block) visit(ctx.block(0));

        // فقرات elif
        List<If.ElifClause> elifClauses = new ArrayList<>();
        List<ExprContext> exprs = ctx.expr();
        List<BlockContext> blocks = ctx.block();
        for (int i = 1; i < exprs.size(); i++) {
            int elifLine = exprs.get(i).start.getLine();
            Expr elifCond = (Expr) visit(exprs.get(i));
            Block elifBody = (Block) visit(blocks.get(i));
            elifClauses.add(new If.ElifClause(elifLine, elifCond, elifBody));
        }

        // فقرة else الاختيارية
        // blocks يحتوي: thenBlock [elifBlocks...] [elseBlock إن وجدت]
        int elseBlockIndex = exprs.size(); // عدد expr = عدد elif + 1 (if)
        Block elseBlock = (blocks.size() > elseBlockIndex)
                ? (Block) visit(blocks.get(elseBlockIndex))
                : null;

        return new If(line, condition, thenBlock, elifClauses, elseBlock);
    }

    // ═══════════════════════════════════════════════════════
    //  forStmt
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitForStmt(ForStmtContext ctx) {
        int line = ctx.start.getLine();
        String variable = ctx.Identifier().getText();
        Expr iterable = (Expr) visit(ctx.expr());
        Block body = (Block) visit(ctx.block());
        return new For(line, variable, iterable, body);
    }

    // ═══════════════════════════════════════════════════════
    //  whileStmt
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitWhileStmt(WhileStmtContext ctx) {
        int line = ctx.start.getLine();
        Expr condition = (Expr) visit(ctx.expr());
        Block body = (Block) visit(ctx.block());
        return new While(line, condition, body);
    }

    // ═══════════════════════════════════════════════════════
    //  expr  (برج الأولويات — 17 بديل)
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitMethodCallExpr(MethodCallExprContext ctx) {
        int line = ctx.start.getLine();
        Expr object = (Expr) visit(ctx.expr());
        String method = ctx.Identifier().getText();
        List<Argument> args = visitArgListSafe(ctx.argList());
        return new MethodCallExpr(line, object, method, args);
    }

    @Override
    public ASTNode visitAttrExpr(AttrExprContext ctx) {
        int line = ctx.start.getLine();
        Expr object = (Expr) visit(ctx.expr());
        String attr = ctx.Identifier().getText();
        return new AttrExpr(line, object, attr);
    }

    @Override
    public ASTNode visitCallExpr(CallExprContext ctx) {
        int line = ctx.start.getLine();
        Expr callee = (Expr) visit(ctx.expr());
        List<Argument> args = visitArgListSafe(ctx.argList());
        return new CallExpr(line, callee, args);
    }

    @Override
    public ASTNode visitSubscriptExpr(SubscriptExprContext ctx) {
        int line = ctx.start.getLine();
        Expr object = (Expr) visit(ctx.expr(0));
        Expr index  = (Expr) visit(ctx.expr(1));
        return new SubscriptExpr(line, object, index);
    }

    @Override
    public ASTNode visitPowExpr(PowExprContext ctx) {
        int line = ctx.start.getLine();
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, "**", left, right);
    }

    @Override
    public ASTNode visitUnaryExpr(UnaryExprContext ctx) {
        int line = ctx.start.getLine();
        String op = ctx.getChild(0).getText();   // "+" أو "-"
        Expr operand = (Expr) visit(ctx.expr());
        return new UnaryExpr(line, op, operand);
    }

    @Override
    public ASTNode visitMulExpr(MulExprContext ctx) {
        int line = ctx.start.getLine();
        String op = ctx.getChild(1).getText();   // "*" | "/" | "%"
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, op, left, right);
    }

    @Override
    public ASTNode visitAddExpr(AddExprContext ctx) {
        int line = ctx.start.getLine();
        String op = ctx.getChild(1).getText();   // "+" | "-"
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, op, left, right);
    }

    @Override
    public ASTNode visitCompareExpr(CompareExprContext ctx) {
        int line = ctx.start.getLine();
        String op = ctx.getChild(1).getText();   // "==" | "!=" | "<" | ...
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, op, left, right);
    }

    @Override
    public ASTNode visitIsExpr(IsExprContext ctx) {
        int line = ctx.start.getLine();
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, "is", left, right);
    }

    @Override
    public ASTNode visitIsNotExpr(IsNotExprContext ctx) {
        int line = ctx.start.getLine();
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, "is not", left, right);
    }

    @Override
    public ASTNode visitNotExpr(NotExprContext ctx) {
        int line = ctx.start.getLine();
        Expr operand = (Expr) visit(ctx.expr());
        return new UnaryExpr(line, "not", operand);
    }

    @Override
    public ASTNode visitAndExpr(AndExprContext ctx) {
        int line = ctx.start.getLine();
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, "and", left, right);
    }

    @Override
    public ASTNode visitOrExpr(OrExprContext ctx) {
        int line = ctx.start.getLine();
        Expr left  = (Expr) visit(ctx.expr(0));
        Expr right = (Expr) visit(ctx.expr(1));
        return new BinaryExpr(line, "or", left, right);
    }

    @Override
    public ASTNode visitTernaryExpr(TernaryExprContext ctx) {
        int line = ctx.start.getLine();
        // grammar: expr IF expr ELSE expr  →  [0] if [1] else [2]
        Expr thenExpr  = (Expr) visit(ctx.expr(0));
        Expr condition = (Expr) visit(ctx.expr(1));
        Expr elseExpr  = (Expr) visit(ctx.expr(2));
        return new TernaryExpr(line, thenExpr, condition, elseExpr);
    }

    @Override
    public ASTNode visitNameExpr(NameExprContext ctx) {
        int line = ctx.start.getLine();
        return new NameExpr(line, ctx.Identifier().getText());
    }

    @Override
    public ASTNode visitLiteralExpr(LiteralExprContext ctx) {
        return visit(ctx.literal());
    }

    @Override
    public ASTNode visitListE(ListEContext ctx) {
        return visit(ctx.listExpr());
    }

    @Override
    public ASTNode visitDictE(DictEContext ctx) {
        return visit(ctx.dictExpr());
    }

    @Override
    public ASTNode visitParenExpr(ParenExprContext ctx) {
        int line = ctx.start.getLine();
        Expr inner = (Expr) visit(ctx.expr());
        return new ParenExpr(line, inner);
    }

    // ═══════════════════════════════════════════════════════
    //  listExpr  (2 بدائل)
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitPlainList(PlainListContext ctx) {
        int line = ctx.start.getLine();
        List<Expr> elements = new ArrayList<>();
        for (ExprContext exprCtx : ctx.expr()) {
            elements.add((Expr) visit(exprCtx));
        }
        return new ListExpr(line, elements);
    }

    @Override
    public ASTNode visitListComp(ListCompContext ctx) {
        int line = ctx.start.getLine();
        Expr element  = (Expr) visit(ctx.expr(0));
        String variable = ctx.Identifier().getText();
        Expr iterable = (Expr) visit(ctx.expr(1));
        Expr filter   = (ctx.expr().size() > 2) ? (Expr) visit(ctx.expr(2)) : null;
        return new ListComp(line, element, variable, iterable, filter);
    }

    // ═══════════════════════════════════════════════════════
    //  dictExpr
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitDictExpr(DictExprContext ctx) {
        int line = ctx.start.getLine();
        List<DictExpr.DictItem> items = new ArrayList<>();
        for (DictItemContext itemCtx : ctx.dictItem()) {
            int itemLine = itemCtx.start.getLine();
            Expr key   = (Expr) visit(itemCtx.key);
            Expr value = (Expr) visit(itemCtx.value);
            items.add(new DictExpr.DictItem(itemLine, key, value));
        }
        return new DictExpr(line, items);
    }

    // ═══════════════════════════════════════════════════════
    //  literal
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitLiteral(LiteralContext ctx) {
        int line = ctx.start.getLine();
        if (ctx.StringLiteral() != null) {
            return new StringLiteral(line, ctx.StringLiteral().getText());
        }
        if (ctx.NumberLiteral() != null) {
            return new NumberLiteral(line, ctx.NumberLiteral().getText());
        }
        if (ctx.TRUE() != null)  return new BoolLiteral(line, true);
        if (ctx.FALSE() != null) return new BoolLiteral(line, false);
        if (ctx.NONE() != null)  return new NoneLiteral(line);
        throw new IllegalStateException("Unknown literal at line " + line);
    }

    // ═══════════════════════════════════════════════════════
    //  argument  (2 بدائل)
    // ═══════════════════════════════════════════════════════

    @Override
    public ASTNode visitKeywordArg(KeywordArgContext ctx) {
        int line = ctx.start.getLine();
        String keyword = ctx.Identifier().getText();
        Expr value = (Expr) visit(ctx.expr());
        return new Argument(line, keyword, value);
    }

    @Override
    public ASTNode visitPositionalArg(PositionalArgContext ctx) {
        int line = ctx.start.getLine();
        Expr value = (Expr) visit(ctx.expr());
        return new Argument(line, value);
    }

    // ═══════════════════════════════════════════════════════
    //  مساعدات خاصة
    // ═══════════════════════════════════════════════════════

    /** يزور argList بأمان ويُعيد قائمة فارغة إذا كانت null. */
    private List<Argument> visitArgListSafe(ArgListContext argList) {
        List<Argument> args = new ArrayList<>();
        if (argList != null) {
            for (ArgumentContext argCtx : argList.argument()) {
                args.add((Argument) visit(argCtx));
            }
        }
        return args;
    }
}