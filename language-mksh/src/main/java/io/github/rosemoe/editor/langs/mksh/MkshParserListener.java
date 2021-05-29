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
	 * Enter a parse tree produced by {@link MkshParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(MkshParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(MkshParser.KeywordContext ctx);
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
}