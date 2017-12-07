// Generated from Smile.g4 by ANTLR 4.7

    import wci.intermediate.*;
    import wci.intermediate.symtabimpl.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SmileParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SmileVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SmileParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SmileParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(SmileParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#mainBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainBlock(SmileParser.MainBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SmileParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(SmileParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#declList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclList(SmileParser.DeclListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(SmileParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#varList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarList(SmileParser.VarListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#varId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarId(SmileParser.VarIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#typeId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeId(SmileParser.TypeIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#compoundStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStmt(SmileParser.CompoundStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(SmileParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#stmtList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtList(SmileParser.StmtListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#assignmentStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStmt(SmileParser.AssignmentStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(SmileParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#printStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(SmileParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#parenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(SmileParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(SmileParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(SmileParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#exprLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteral(SmileParser.ExprLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(SmileParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link SmileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(SmileParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link SmileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(SmileParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relOpExpr}
	 * labeled alternative in {@link SmileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelOpExpr(SmileParser.RelOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unsignedNumberExpr}
	 * labeled alternative in {@link SmileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedNumberExpr(SmileParser.UnsignedNumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link SmileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(SmileParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link SmileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(SmileParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code signedNumberExpr}
	 * labeled alternative in {@link SmileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignedNumberExpr(SmileParser.SignedNumberExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#mulDivOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivOp(SmileParser.MulDivOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#addSubOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubOp(SmileParser.AddSubOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(SmileParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#signedNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignedNumber(SmileParser.SignedNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(SmileParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerConst}
	 * labeled alternative in {@link SmileParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerConst(SmileParser.IntegerConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatConst}
	 * labeled alternative in {@link SmileParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatConst(SmileParser.FloatConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#nEQUALS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNEQUALS(SmileParser.NEQUALSContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#lTHAN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLTHAN(SmileParser.LTHANContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmileParser#gThan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGThan(SmileParser.GThanContext ctx);
}