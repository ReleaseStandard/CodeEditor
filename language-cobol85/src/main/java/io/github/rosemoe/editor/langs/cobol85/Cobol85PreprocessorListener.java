package io.github.rosemoe.editor.langs.cobol85;
// Generated from Cobol85Preprocessor.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Cobol85PreprocessorParser}.
 */
public interface Cobol85PreprocessorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#startRule}.
	 * @param ctx the parse tree
	 */
	void enterStartRule(Cobol85PreprocessorParser.StartRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#startRule}.
	 * @param ctx the parse tree
	 */
	void exitStartRule(Cobol85PreprocessorParser.StartRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#compilerOptions}.
	 * @param ctx the parse tree
	 */
	void enterCompilerOptions(Cobol85PreprocessorParser.CompilerOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#compilerOptions}.
	 * @param ctx the parse tree
	 */
	void exitCompilerOptions(Cobol85PreprocessorParser.CompilerOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#compilerXOpts}.
	 * @param ctx the parse tree
	 */
	void enterCompilerXOpts(Cobol85PreprocessorParser.CompilerXOptsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#compilerXOpts}.
	 * @param ctx the parse tree
	 */
	void exitCompilerXOpts(Cobol85PreprocessorParser.CompilerXOptsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#compilerOption}.
	 * @param ctx the parse tree
	 */
	void enterCompilerOption(Cobol85PreprocessorParser.CompilerOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#compilerOption}.
	 * @param ctx the parse tree
	 */
	void exitCompilerOption(Cobol85PreprocessorParser.CompilerOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#execCicsStatement}.
	 * @param ctx the parse tree
	 */
	void enterExecCicsStatement(Cobol85PreprocessorParser.ExecCicsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#execCicsStatement}.
	 * @param ctx the parse tree
	 */
	void exitExecCicsStatement(Cobol85PreprocessorParser.ExecCicsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#execSqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterExecSqlStatement(Cobol85PreprocessorParser.ExecSqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#execSqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitExecSqlStatement(Cobol85PreprocessorParser.ExecSqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#execSqlImsStatement}.
	 * @param ctx the parse tree
	 */
	void enterExecSqlImsStatement(Cobol85PreprocessorParser.ExecSqlImsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#execSqlImsStatement}.
	 * @param ctx the parse tree
	 */
	void exitExecSqlImsStatement(Cobol85PreprocessorParser.ExecSqlImsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#copyStatement}.
	 * @param ctx the parse tree
	 */
	void enterCopyStatement(Cobol85PreprocessorParser.CopyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#copyStatement}.
	 * @param ctx the parse tree
	 */
	void exitCopyStatement(Cobol85PreprocessorParser.CopyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#copySource}.
	 * @param ctx the parse tree
	 */
	void enterCopySource(Cobol85PreprocessorParser.CopySourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#copySource}.
	 * @param ctx the parse tree
	 */
	void exitCopySource(Cobol85PreprocessorParser.CopySourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#copyLibrary}.
	 * @param ctx the parse tree
	 */
	void enterCopyLibrary(Cobol85PreprocessorParser.CopyLibraryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#copyLibrary}.
	 * @param ctx the parse tree
	 */
	void exitCopyLibrary(Cobol85PreprocessorParser.CopyLibraryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#replacingPhrase}.
	 * @param ctx the parse tree
	 */
	void enterReplacingPhrase(Cobol85PreprocessorParser.ReplacingPhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#replacingPhrase}.
	 * @param ctx the parse tree
	 */
	void exitReplacingPhrase(Cobol85PreprocessorParser.ReplacingPhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#replaceArea}.
	 * @param ctx the parse tree
	 */
	void enterReplaceArea(Cobol85PreprocessorParser.ReplaceAreaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#replaceArea}.
	 * @param ctx the parse tree
	 */
	void exitReplaceArea(Cobol85PreprocessorParser.ReplaceAreaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#replaceByStatement}.
	 * @param ctx the parse tree
	 */
	void enterReplaceByStatement(Cobol85PreprocessorParser.ReplaceByStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#replaceByStatement}.
	 * @param ctx the parse tree
	 */
	void exitReplaceByStatement(Cobol85PreprocessorParser.ReplaceByStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#replaceOffStatement}.
	 * @param ctx the parse tree
	 */
	void enterReplaceOffStatement(Cobol85PreprocessorParser.ReplaceOffStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#replaceOffStatement}.
	 * @param ctx the parse tree
	 */
	void exitReplaceOffStatement(Cobol85PreprocessorParser.ReplaceOffStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#replaceClause}.
	 * @param ctx the parse tree
	 */
	void enterReplaceClause(Cobol85PreprocessorParser.ReplaceClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#replaceClause}.
	 * @param ctx the parse tree
	 */
	void exitReplaceClause(Cobol85PreprocessorParser.ReplaceClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#directoryPhrase}.
	 * @param ctx the parse tree
	 */
	void enterDirectoryPhrase(Cobol85PreprocessorParser.DirectoryPhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#directoryPhrase}.
	 * @param ctx the parse tree
	 */
	void exitDirectoryPhrase(Cobol85PreprocessorParser.DirectoryPhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#familyPhrase}.
	 * @param ctx the parse tree
	 */
	void enterFamilyPhrase(Cobol85PreprocessorParser.FamilyPhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#familyPhrase}.
	 * @param ctx the parse tree
	 */
	void exitFamilyPhrase(Cobol85PreprocessorParser.FamilyPhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#replaceable}.
	 * @param ctx the parse tree
	 */
	void enterReplaceable(Cobol85PreprocessorParser.ReplaceableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#replaceable}.
	 * @param ctx the parse tree
	 */
	void exitReplaceable(Cobol85PreprocessorParser.ReplaceableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#replacement}.
	 * @param ctx the parse tree
	 */
	void enterReplacement(Cobol85PreprocessorParser.ReplacementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#replacement}.
	 * @param ctx the parse tree
	 */
	void exitReplacement(Cobol85PreprocessorParser.ReplacementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#ejectStatement}.
	 * @param ctx the parse tree
	 */
	void enterEjectStatement(Cobol85PreprocessorParser.EjectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#ejectStatement}.
	 * @param ctx the parse tree
	 */
	void exitEjectStatement(Cobol85PreprocessorParser.EjectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#skipStatement}.
	 * @param ctx the parse tree
	 */
	void enterSkipStatement(Cobol85PreprocessorParser.SkipStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#skipStatement}.
	 * @param ctx the parse tree
	 */
	void exitSkipStatement(Cobol85PreprocessorParser.SkipStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#titleStatement}.
	 * @param ctx the parse tree
	 */
	void enterTitleStatement(Cobol85PreprocessorParser.TitleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#titleStatement}.
	 * @param ctx the parse tree
	 */
	void exitTitleStatement(Cobol85PreprocessorParser.TitleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#pseudoText}.
	 * @param ctx the parse tree
	 */
	void enterPseudoText(Cobol85PreprocessorParser.PseudoTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#pseudoText}.
	 * @param ctx the parse tree
	 */
	void exitPseudoText(Cobol85PreprocessorParser.PseudoTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#charData}.
	 * @param ctx the parse tree
	 */
	void enterCharData(Cobol85PreprocessorParser.CharDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#charData}.
	 * @param ctx the parse tree
	 */
	void exitCharData(Cobol85PreprocessorParser.CharDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#charDataSql}.
	 * @param ctx the parse tree
	 */
	void enterCharDataSql(Cobol85PreprocessorParser.CharDataSqlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#charDataSql}.
	 * @param ctx the parse tree
	 */
	void exitCharDataSql(Cobol85PreprocessorParser.CharDataSqlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#charDataLine}.
	 * @param ctx the parse tree
	 */
	void enterCharDataLine(Cobol85PreprocessorParser.CharDataLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#charDataLine}.
	 * @param ctx the parse tree
	 */
	void exitCharDataLine(Cobol85PreprocessorParser.CharDataLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#cobolWord}.
	 * @param ctx the parse tree
	 */
	void enterCobolWord(Cobol85PreprocessorParser.CobolWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#cobolWord}.
	 * @param ctx the parse tree
	 */
	void exitCobolWord(Cobol85PreprocessorParser.CobolWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(Cobol85PreprocessorParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(Cobol85PreprocessorParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(Cobol85PreprocessorParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(Cobol85PreprocessorParser.FilenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Cobol85PreprocessorParser#charDataKeyword}.
	 * @param ctx the parse tree
	 */
	void enterCharDataKeyword(Cobol85PreprocessorParser.CharDataKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link Cobol85PreprocessorParser#charDataKeyword}.
	 * @param ctx the parse tree
	 */
	void exitCharDataKeyword(Cobol85PreprocessorParser.CharDataKeywordContext ctx);
}