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
	 * Enter a parse tree produced by {@link MkshParser#encapsulated_expression}.
	 * @param ctx the parse tree
	 */
	void enterEncapsulated_expression(MkshParser.Encapsulated_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#encapsulated_expression}.
	 * @param ctx the parse tree
	 */
	void exitEncapsulated_expression(MkshParser.Encapsulated_expressionContext ctx);
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
	 * Enter a parse tree produced by {@link MkshParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(MkshParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(MkshParser.StringContext ctx);
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
	 * Enter a parse tree produced by {@link MkshParser#secondary_instruction}.
	 * @param ctx the parse tree
	 */
	void enterSecondary_instruction(MkshParser.Secondary_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#secondary_instruction}.
	 * @param ctx the parse tree
	 */
	void exitSecondary_instruction(MkshParser.Secondary_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#instruction_time}.
	 * @param ctx the parse tree
	 */
	void enterInstruction_time(MkshParser.Instruction_timeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#instruction_time}.
	 * @param ctx the parse tree
	 */
	void exitInstruction_time(MkshParser.Instruction_timeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#executable_instruction}.
	 * @param ctx the parse tree
	 */
	void enterExecutable_instruction(MkshParser.Executable_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#executable_instruction}.
	 * @param ctx the parse tree
	 */
	void exitExecutable_instruction(MkshParser.Executable_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#instruction_exec}.
	 * @param ctx the parse tree
	 */
	void enterInstruction_exec(MkshParser.Instruction_execContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#instruction_exec}.
	 * @param ctx the parse tree
	 */
	void exitInstruction_exec(MkshParser.Instruction_execContext ctx);
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
	 * Enter a parse tree produced by {@link MkshParser#execution_control_case_esac}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_case_esac(MkshParser.Execution_control_case_esacContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_case_esac}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_case_esac(MkshParser.Execution_control_case_esacContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control_for_do_done}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_for_do_done(MkshParser.Execution_control_for_do_doneContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_for_do_done}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_for_do_done(MkshParser.Execution_control_for_do_doneContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control_if_then_else}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_if_then_else(MkshParser.Execution_control_if_then_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_if_then_else}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_if_then_else(MkshParser.Execution_control_if_then_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control_select_in}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_select_in(MkshParser.Execution_control_select_inContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_select_in}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_select_in(MkshParser.Execution_control_select_inContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control_until_do}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_until_do(MkshParser.Execution_control_until_doContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_until_do}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_until_do(MkshParser.Execution_control_until_doContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control_while_do}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_while_do(MkshParser.Execution_control_while_doContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_while_do}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_while_do(MkshParser.Execution_control_while_doContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control_function}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_function(MkshParser.Execution_control_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_function}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_function(MkshParser.Execution_control_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MkshParser#execution_control_function_wo_kwrd}.
	 * @param ctx the parse tree
	 */
	void enterExecution_control_function_wo_kwrd(MkshParser.Execution_control_function_wo_kwrdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#execution_control_function_wo_kwrd}.
	 * @param ctx the parse tree
	 */
	void exitExecution_control_function_wo_kwrd(MkshParser.Execution_control_function_wo_kwrdContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link MkshParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MkshParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MkshParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MkshParser.AssignmentContext ctx);
}