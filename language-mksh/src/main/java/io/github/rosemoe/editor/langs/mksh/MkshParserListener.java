package io.github.rosemoe.editor.langs.mksh;
// Generated from MkshParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MkshParser}.
 */
public interface MkshParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MkshParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(MkshParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(MkshParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(MkshParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(MkshParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(MkshParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(MkshParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(MkshParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(MkshParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#expression_end}.
	 * @param ctx the parse tree
	 */
	void enterExpression_end(MkshParser.Expression_endContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#expression_end}.
	 * @param ctx the parse tree
	 */
	void exitExpression_end(MkshParser.Expression_endContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(MkshParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(MkshParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MkshParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MkshParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#primary_keyword}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_keyword(MkshParser.Primary_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#primary_keyword}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_keyword(MkshParser.Primary_keywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#secondary_keyword}.
	 * @param ctx the parse tree
	 */
	void enterSecondary_keyword(MkshParser.Secondary_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#secondary_keyword}.
	 * @param ctx the parse tree
	 */
	void exitSecondary_keyword(MkshParser.Secondary_keywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control(MkshParser.Execution_controlContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control(MkshParser.Execution_controlContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#for_do_done}.
	 * @param ctx the parse tree
	 */
	void enterFor_do_done(MkshParser.For_do_doneContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#for_do_done}.
	 * @param ctx the parse tree
	 */
	void exitFor_do_done(MkshParser.For_do_doneContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void enterIf_then_else(MkshParser.If_then_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void exitIf_then_else(MkshParser.If_then_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#select_in}.
	 * @param ctx the parse tree
	 */
	void enterSelect_in(MkshParser.Select_inContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#select_in}.
	 * @param ctx the parse tree
	 */
	void exitSelect_in(MkshParser.Select_inContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#until_do}.
	 * @param ctx the parse tree
	 */
	void enterUntil_do(MkshParser.Until_doContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#until_do}.
	 * @param ctx the parse tree
	 */
	void exitUntil_do(MkshParser.Until_doContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#while_do}.
	 * @param ctx the parse tree
	 */
	void enterWhile_do(MkshParser.While_doContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#while_do}.
	 * @param ctx the parse tree
	 */
	void exitWhile_do(MkshParser.While_doContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MkshParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MkshParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#arit}.
	 * @param ctx the parse tree
	 */
	void enterArit(MkshParser.AritContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#arit}.
	 * @param ctx the parse tree
	 */
	void exitArit(MkshParser.AritContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#a_immediate}.
	 * @param ctx the parse tree
	 */
	void enterA_immediate(MkshParser.A_immediateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#a_immediate}.
	 * @param ctx the parse tree
	 */
	void exitA_immediate(MkshParser.A_immediateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#a_operand}.
	 * @param ctx the parse tree
	 */
	void enterA_operand(MkshParser.A_operandContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#a_operand}.
	 * @param ctx the parse tree
	 */
	void exitA_operand(MkshParser.A_operandContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#a_expr}.
	 * @param ctx the parse tree
	 */
	void enterA_expr(MkshParser.A_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#a_expr}.
	 * @param ctx the parse tree
	 */
	void exitA_expr(MkshParser.A_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#a_operator_binary}.
	 * @param ctx the parse tree
	 */
	void enterA_operator_binary(MkshParser.A_operator_binaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#a_operator_binary}.
	 * @param ctx the parse tree
	 */
	void exitA_operator_binary(MkshParser.A_operator_binaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#a_operator_unary}.
	 * @param ctx the parse tree
	 */
	void enterA_operator_unary(MkshParser.A_operator_unaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#a_operator_unary}.
	 * @param ctx the parse tree
	 */
	void exitA_operator_unary(MkshParser.A_operator_unaryContext ctx);
}