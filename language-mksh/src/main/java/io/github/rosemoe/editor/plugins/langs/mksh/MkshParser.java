package io.github.rosemoe.editor.langs.mksh;
// Generated from MkshParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MkshParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CASE=1, ELSE=2, FUNCTION=3, THEN=4, DO=5, ESAC=6, IF=7, TIME=8, DONE=9, 
		FI=10, IN=11, UNTIL=12, ELIF=13, FOR=14, SELECT=15, WHILE=16, BREAK=17, 
		CONTINUE=18, EVAL=19, EXEC=20, EXIT=21, EXPORT=22, READONLY=23, RETURN=24, 
		SET=25, SHIFT=26, TIMES=27, TRAP=28, UNSET=29, BUILTIN=30, GLOBAL=31, 
		TYPESET=32, WAIT=33, ALIAS=34, BG=35, BIND=36, CAT=37, CD=38, COMMAND=39, 
		ECHO=40, FALSE=41, TRUE=42, FC=43, FG=44, GETOPTS=45, JOBS=46, KILL=47, 
		LET=48, MKNOD=49, PRINT=50, PWD=51, READ=52, REALPATH=53, RENAME=54, SLEEP=55, 
		SUSPEND=56, TEST=57, ULIMIT=58, UMASK=59, UNALIAS=60, WHENCE=61, SQ_BRACKET_OPEN=62, 
		SQ_BRACKET_CLOSE=63, PERIOD=64, COLON=65, ARIT_OPERATOR_L=66, ARIT_OPERATOR_R=67, 
		ARIT_PLUS=68, ARIT_MINUS=69, ARIT_ONE=70, ARIT_A=71, ARIT_A_PLUS=72, ARIT_A_MINUS=73, 
		ARIT_A_STAR=74, ARIT_A_DIV=75, ARIT_A_MOD=76, ARIT_A_L_SHIFT=77, ARIT_A_R_SHIFT=78, 
		ARIT_A_L_ROTATE=79, ARIT_A_R_ROTATE=80, ARIT_A_XOR=81, ARIT_A_AND=82, 
		ARIT_A_OR=83, EXECUTION_CONTROL_CASE_ESAC_TERMINATOR=84, P_SEMI=85, P_INTERO=86, 
		P_COMMA=87, P_L_BRACKET=88, P_R_BRACKET=89, P_L_PARENTHESIS=90, P_R_PARENTHESIS=91, 
		BACK_TICK=92, L_SHIFT=93, R_SHIFT=94, GT=95, LT=96, STRING=97, IDENTIFIER=98, 
		WS=99, TERMINATOR=100, LINE_COMMENT=101;
	public static final int
		RULE_start = 0, RULE_file = 1, RULE_expr = 2, RULE_encapsulated_expression = 3, 
		RULE_expression_end = 4, RULE_comment = 5, RULE_identifier = 6, RULE_string = 7, 
		RULE_instruction = 8, RULE_primary_keyword = 9, RULE_secondary_keyword = 10, 
		RULE_secondary_instruction = 11, RULE_instruction_time = 12, RULE_executable_instruction = 13, 
		RULE_instruction_exec = 14, RULE_execution_control = 15, RULE_execution_control_case_esac = 16, 
		RULE_execution_control_for_do_done = 17, RULE_execution_control_if_then_else = 18, 
		RULE_execution_control_select_in = 19, RULE_execution_control_until_do = 20, 
		RULE_execution_control_while_do = 21, RULE_execution_control_function = 22, 
		RULE_execution_control_function_wo_kwrd = 23, RULE_arit = 24, RULE_a_immediate = 25, 
		RULE_a_operand = 26, RULE_a_expr = 27, RULE_a_operator_binary = 28, RULE_a_operator_unary = 29, 
		RULE_assignment = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "file", "expr", "encapsulated_expression", "expression_end", 
			"comment", "identifier", "string", "instruction", "primary_keyword", 
			"secondary_keyword", "secondary_instruction", "instruction_time", "executable_instruction", 
			"instruction_exec", "execution_control", "execution_control_case_esac", 
			"execution_control_for_do_done", "execution_control_if_then_else", "execution_control_select_in", 
			"execution_control_until_do", "execution_control_while_do", "execution_control_function", 
			"execution_control_function_wo_kwrd", "arit", "a_immediate", "a_operand", 
			"a_expr", "a_operator_binary", "a_operator_unary", "assignment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'case'", "'else'", "'function'", "'then'", "'do'", "'esac'", "'if'", 
			"'time'", "'done'", "'fi'", "'in'", "'until'", "'elif'", "'for'", "'select'", 
			"'while'", "'break'", "'continue'", "'eval'", "'exec'", "'exit'", "'export'", 
			"'readonly'", "'return'", "'set'", "'shift'", "'times'", "'trap'", "'unset'", 
			"'builtint'", "'global'", "'typeset'", "'wait'", "'alias'", "'bg'", "'bind'", 
			"'cat'", "'cd'", "'command'", "'echo'", "'false'", "'true'", "'fc'", 
			"'fg'", "'getopts'", "'jobs'", "'kill'", "'let'", "'mknod'", "'print'", 
			"'pwd'", "'read'", "'realpath'", "'rename'", "'sleep'", "'suspend'", 
			"'test'", "'ulimit'", "'umask'", "'unalias'", "'whence'", "'['", "']'", 
			"'.'", "':'", "'(('", "'))'", "'+'", "'-'", "'1'", "'='", "'+='", "'-='", 
			"'*='", "'/='", "'%='", "'<<='", "'>>='", "'<<<='", "'>>>='", "'^='", 
			"'&='", "'|='", null, "';'", "'?'", "','", "'{'", "'}'", "'('", "')'", 
			"'`'", "'<<'", "'>>'", "'>'", "'<'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CASE", "ELSE", "FUNCTION", "THEN", "DO", "ESAC", "IF", "TIME", 
			"DONE", "FI", "IN", "UNTIL", "ELIF", "FOR", "SELECT", "WHILE", "BREAK", 
			"CONTINUE", "EVAL", "EXEC", "EXIT", "EXPORT", "READONLY", "RETURN", "SET", 
			"SHIFT", "TIMES", "TRAP", "UNSET", "BUILTIN", "GLOBAL", "TYPESET", "WAIT", 
			"ALIAS", "BG", "BIND", "CAT", "CD", "COMMAND", "ECHO", "FALSE", "TRUE", 
			"FC", "FG", "GETOPTS", "JOBS", "KILL", "LET", "MKNOD", "PRINT", "PWD", 
			"READ", "REALPATH", "RENAME", "SLEEP", "SUSPEND", "TEST", "ULIMIT", "UMASK", 
			"UNALIAS", "WHENCE", "SQ_BRACKET_OPEN", "SQ_BRACKET_CLOSE", "PERIOD", 
			"COLON", "ARIT_OPERATOR_L", "ARIT_OPERATOR_R", "ARIT_PLUS", "ARIT_MINUS", 
			"ARIT_ONE", "ARIT_A", "ARIT_A_PLUS", "ARIT_A_MINUS", "ARIT_A_STAR", "ARIT_A_DIV", 
			"ARIT_A_MOD", "ARIT_A_L_SHIFT", "ARIT_A_R_SHIFT", "ARIT_A_L_ROTATE", 
			"ARIT_A_R_ROTATE", "ARIT_A_XOR", "ARIT_A_AND", "ARIT_A_OR", "EXECUTION_CONTROL_CASE_ESAC_TERMINATOR", 
			"P_SEMI", "P_INTERO", "P_COMMA", "P_L_BRACKET", "P_R_BRACKET", "P_L_PARENTHESIS", 
			"P_R_PARENTHESIS", "BACK_TICK", "L_SHIFT", "R_SHIFT", "GT", "LT", "STRING", 
			"IDENTIFIER", "WS", "TERMINATOR", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MkshParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MkshParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public FileContext file() {
			return getRuleContext(FileContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			file();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FileContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MkshParser.EOF, 0); }
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			expr();
			setState(65);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Encapsulated_expressionContext encapsulated_expression() {
			return getRuleContext(Encapsulated_expressionContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> TERMINATOR() { return getTokens(MkshParser.TERMINATOR); }
		public TerminalNode TERMINATOR(int i) {
			return getToken(MkshParser.TERMINATOR, i);
		}
		public AritContext arit() {
			return getRuleContext(AritContext.class,0);
		}
		public Execution_controlContext execution_control() {
			return getRuleContext(Execution_controlContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Expression_endContext expression_end() {
			return getRuleContext(Expression_endContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
			case ELSE:
			case FUNCTION:
			case THEN:
			case DO:
			case ESAC:
			case IF:
			case TIME:
			case DONE:
			case FI:
			case IN:
			case UNTIL:
			case ELIF:
			case FOR:
			case SELECT:
			case WHILE:
			case BREAK:
			case CONTINUE:
			case EVAL:
			case EXEC:
			case EXIT:
			case EXPORT:
			case READONLY:
			case RETURN:
			case SET:
			case SHIFT:
			case TIMES:
			case TRAP:
			case UNSET:
			case BUILTIN:
			case GLOBAL:
			case TYPESET:
			case WAIT:
			case ALIAS:
			case BG:
			case BIND:
			case CAT:
			case CD:
			case COMMAND:
			case ECHO:
			case FALSE:
			case TRUE:
			case FC:
			case FG:
			case GETOPTS:
			case JOBS:
			case KILL:
			case LET:
			case MKNOD:
			case PRINT:
			case PWD:
			case READ:
			case REALPATH:
			case RENAME:
			case SLEEP:
			case SUSPEND:
			case TEST:
			case ULIMIT:
			case UMASK:
			case UNALIAS:
			case WHENCE:
			case ARIT_OPERATOR_L:
			case IDENTIFIER:
				{
				{
				setState(71);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(67);
					arit();
					}
					break;
				case 2:
					{
					setState(68);
					execution_control();
					}
					break;
				case 3:
					{
					setState(69);
					instruction();
					}
					break;
				case 4:
					{
					setState(70);
					assignment();
					}
					break;
				}
				setState(74);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(73);
					expression_end();
					}
					break;
				}
				}
				}
				break;
			case P_L_BRACKET:
			case P_L_PARENTHESIS:
				{
				setState(76);
				encapsulated_expression();
				}
				break;
			case LINE_COMMENT:
				{
				setState(77);
				comment();
				}
				break;
			case TERMINATOR:
				{
				setState(79); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(78);
						match(TERMINATOR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(81); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(85);
				expr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Encapsulated_expressionContext extends ParserRuleContext {
		public TerminalNode P_L_PARENTHESIS() { return getToken(MkshParser.P_L_PARENTHESIS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode P_R_PARENTHESIS() { return getToken(MkshParser.P_R_PARENTHESIS, 0); }
		public Expression_endContext expression_end() {
			return getRuleContext(Expression_endContext.class,0);
		}
		public TerminalNode P_L_BRACKET() { return getToken(MkshParser.P_L_BRACKET, 0); }
		public TerminalNode P_R_BRACKET() { return getToken(MkshParser.P_R_BRACKET, 0); }
		public Encapsulated_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_encapsulated_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterEncapsulated_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitEncapsulated_expression(this);
		}
	}

	public final Encapsulated_expressionContext encapsulated_expression() throws RecognitionException {
		Encapsulated_expressionContext _localctx = new Encapsulated_expressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_encapsulated_expression);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case P_L_PARENTHESIS:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				match(P_L_PARENTHESIS);
				setState(89);
				expr();
				setState(90);
				match(P_R_PARENTHESIS);
				setState(92);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(91);
					expression_end();
					}
					break;
				}
				}
				break;
			case P_L_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(P_L_BRACKET);
				setState(95);
				expr();
				setState(96);
				match(P_R_BRACKET);
				setState(98);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(97);
					expression_end();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_endContext extends ParserRuleContext {
		public TerminalNode P_SEMI() { return getToken(MkshParser.P_SEMI, 0); }
		public TerminalNode TERMINATOR() { return getToken(MkshParser.TERMINATOR, 0); }
		public Expression_endContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_end; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExpression_end(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExpression_end(this);
		}
	}

	public final Expression_endContext expression_end() throws RecognitionException {
		Expression_endContext _localctx = new Expression_endContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expression_end);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !(_la==P_SEMI || _la==TERMINATOR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode LINE_COMMENT() { return getToken(MkshParser.LINE_COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(LINE_COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MkshParser.IDENTIFIER, 0); }
		public Primary_keywordContext primary_keyword() {
			return getRuleContext(Primary_keywordContext.class,0);
		}
		public Secondary_keywordContext secondary_keyword() {
			return getRuleContext(Secondary_keywordContext.class,0);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_identifier);
		try {
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				match(IDENTIFIER);
				}
				break;
			case CASE:
			case ELSE:
			case FUNCTION:
			case THEN:
			case DO:
			case ESAC:
			case IF:
			case TIME:
			case DONE:
			case FI:
			case IN:
			case UNTIL:
			case ELIF:
			case FOR:
			case SELECT:
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				primary_keyword();
				}
				break;
			case BREAK:
			case CONTINUE:
			case EVAL:
			case EXEC:
			case EXIT:
			case EXPORT:
			case READONLY:
			case RETURN:
			case SET:
			case SHIFT:
			case TIMES:
			case TRAP:
			case UNSET:
			case BUILTIN:
			case GLOBAL:
			case TYPESET:
			case WAIT:
			case ALIAS:
			case BG:
			case BIND:
			case CAT:
			case CD:
			case COMMAND:
			case ECHO:
			case FALSE:
			case TRUE:
			case FC:
			case FG:
			case GETOPTS:
			case JOBS:
			case KILL:
			case LET:
			case MKNOD:
			case PRINT:
			case PWD:
			case READ:
			case REALPATH:
			case RENAME:
			case SLEEP:
			case SUSPEND:
			case TEST:
			case ULIMIT:
			case UMASK:
			case UNALIAS:
			case WHENCE:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				secondary_keyword();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(MkshParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public Instruction_timeContext instruction_time() {
			return getRuleContext(Instruction_timeContext.class,0);
		}
		public Secondary_instructionContext secondary_instruction() {
			return getRuleContext(Secondary_instructionContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TIME:
				{
				setState(113);
				instruction_time();
				}
				break;
			case BREAK:
			case CONTINUE:
			case EVAL:
			case EXEC:
			case EXIT:
			case EXPORT:
			case READONLY:
			case RETURN:
			case SET:
			case SHIFT:
			case TIMES:
			case TRAP:
			case UNSET:
			case BUILTIN:
			case GLOBAL:
			case TYPESET:
			case WAIT:
			case ALIAS:
			case BG:
			case BIND:
			case CAT:
			case CD:
			case COMMAND:
			case ECHO:
			case FALSE:
			case TRUE:
			case FC:
			case FG:
			case GETOPTS:
			case JOBS:
			case KILL:
			case LET:
			case MKNOD:
			case PRINT:
			case PWD:
			case READ:
			case REALPATH:
			case RENAME:
			case SLEEP:
			case SUSPEND:
			case TEST:
			case ULIMIT:
			case UMASK:
			case UNALIAS:
			case WHENCE:
				{
				setState(114);
				secondary_instruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primary_keywordContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(MkshParser.CASE, 0); }
		public TerminalNode ELSE() { return getToken(MkshParser.ELSE, 0); }
		public TerminalNode FUNCTION() { return getToken(MkshParser.FUNCTION, 0); }
		public TerminalNode TIME() { return getToken(MkshParser.TIME, 0); }
		public TerminalNode THEN() { return getToken(MkshParser.THEN, 0); }
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public TerminalNode ESAC() { return getToken(MkshParser.ESAC, 0); }
		public TerminalNode IF() { return getToken(MkshParser.IF, 0); }
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public TerminalNode FI() { return getToken(MkshParser.FI, 0); }
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public TerminalNode UNTIL() { return getToken(MkshParser.UNTIL, 0); }
		public TerminalNode ELIF() { return getToken(MkshParser.ELIF, 0); }
		public TerminalNode FOR() { return getToken(MkshParser.FOR, 0); }
		public TerminalNode SELECT() { return getToken(MkshParser.SELECT, 0); }
		public TerminalNode WHILE() { return getToken(MkshParser.WHILE, 0); }
		public Primary_keywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterPrimary_keyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitPrimary_keyword(this);
		}
	}

	public final Primary_keywordContext primary_keyword() throws RecognitionException {
		Primary_keywordContext _localctx = new Primary_keywordContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_primary_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CASE) | (1L << ELSE) | (1L << FUNCTION) | (1L << THEN) | (1L << DO) | (1L << ESAC) | (1L << IF) | (1L << TIME) | (1L << DONE) | (1L << FI) | (1L << IN) | (1L << UNTIL) | (1L << ELIF) | (1L << FOR) | (1L << SELECT) | (1L << WHILE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Secondary_keywordContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(MkshParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(MkshParser.CONTINUE, 0); }
		public TerminalNode EXEC() { return getToken(MkshParser.EXEC, 0); }
		public TerminalNode EVAL() { return getToken(MkshParser.EVAL, 0); }
		public TerminalNode EXIT() { return getToken(MkshParser.EXIT, 0); }
		public TerminalNode EXPORT() { return getToken(MkshParser.EXPORT, 0); }
		public TerminalNode READONLY() { return getToken(MkshParser.READONLY, 0); }
		public TerminalNode RETURN() { return getToken(MkshParser.RETURN, 0); }
		public TerminalNode SET() { return getToken(MkshParser.SET, 0); }
		public TerminalNode SHIFT() { return getToken(MkshParser.SHIFT, 0); }
		public TerminalNode TIMES() { return getToken(MkshParser.TIMES, 0); }
		public TerminalNode TRAP() { return getToken(MkshParser.TRAP, 0); }
		public TerminalNode UNSET() { return getToken(MkshParser.UNSET, 0); }
		public TerminalNode BUILTIN() { return getToken(MkshParser.BUILTIN, 0); }
		public TerminalNode GLOBAL() { return getToken(MkshParser.GLOBAL, 0); }
		public TerminalNode TYPESET() { return getToken(MkshParser.TYPESET, 0); }
		public TerminalNode WAIT() { return getToken(MkshParser.WAIT, 0); }
		public TerminalNode ALIAS() { return getToken(MkshParser.ALIAS, 0); }
		public TerminalNode BG() { return getToken(MkshParser.BG, 0); }
		public TerminalNode BIND() { return getToken(MkshParser.BIND, 0); }
		public TerminalNode CAT() { return getToken(MkshParser.CAT, 0); }
		public TerminalNode CD() { return getToken(MkshParser.CD, 0); }
		public TerminalNode COMMAND() { return getToken(MkshParser.COMMAND, 0); }
		public TerminalNode ECHO() { return getToken(MkshParser.ECHO, 0); }
		public TerminalNode FALSE() { return getToken(MkshParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(MkshParser.TRUE, 0); }
		public TerminalNode FC() { return getToken(MkshParser.FC, 0); }
		public TerminalNode FG() { return getToken(MkshParser.FG, 0); }
		public TerminalNode GETOPTS() { return getToken(MkshParser.GETOPTS, 0); }
		public TerminalNode JOBS() { return getToken(MkshParser.JOBS, 0); }
		public TerminalNode KILL() { return getToken(MkshParser.KILL, 0); }
		public TerminalNode LET() { return getToken(MkshParser.LET, 0); }
		public TerminalNode MKNOD() { return getToken(MkshParser.MKNOD, 0); }
		public TerminalNode PRINT() { return getToken(MkshParser.PRINT, 0); }
		public TerminalNode PWD() { return getToken(MkshParser.PWD, 0); }
		public TerminalNode READ() { return getToken(MkshParser.READ, 0); }
		public TerminalNode REALPATH() { return getToken(MkshParser.REALPATH, 0); }
		public TerminalNode RENAME() { return getToken(MkshParser.RENAME, 0); }
		public TerminalNode SLEEP() { return getToken(MkshParser.SLEEP, 0); }
		public TerminalNode SUSPEND() { return getToken(MkshParser.SUSPEND, 0); }
		public TerminalNode TEST() { return getToken(MkshParser.TEST, 0); }
		public TerminalNode ULIMIT() { return getToken(MkshParser.ULIMIT, 0); }
		public TerminalNode UMASK() { return getToken(MkshParser.UMASK, 0); }
		public TerminalNode UNALIAS() { return getToken(MkshParser.UNALIAS, 0); }
		public TerminalNode WHENCE() { return getToken(MkshParser.WHENCE, 0); }
		public Secondary_keywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_secondary_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterSecondary_keyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitSecondary_keyword(this);
		}
	}

	public final Secondary_keywordContext secondary_keyword() throws RecognitionException {
		Secondary_keywordContext _localctx = new Secondary_keywordContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_secondary_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BREAK) | (1L << CONTINUE) | (1L << EVAL) | (1L << EXEC) | (1L << EXIT) | (1L << EXPORT) | (1L << READONLY) | (1L << RETURN) | (1L << SET) | (1L << SHIFT) | (1L << TIMES) | (1L << TRAP) | (1L << UNSET) | (1L << BUILTIN) | (1L << GLOBAL) | (1L << TYPESET) | (1L << WAIT) | (1L << ALIAS) | (1L << BG) | (1L << BIND) | (1L << CAT) | (1L << CD) | (1L << COMMAND) | (1L << ECHO) | (1L << FALSE) | (1L << TRUE) | (1L << FC) | (1L << FG) | (1L << GETOPTS) | (1L << JOBS) | (1L << KILL) | (1L << LET) | (1L << MKNOD) | (1L << PRINT) | (1L << PWD) | (1L << READ) | (1L << REALPATH) | (1L << RENAME) | (1L << SLEEP) | (1L << SUSPEND) | (1L << TEST) | (1L << ULIMIT) | (1L << UMASK) | (1L << UNALIAS) | (1L << WHENCE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Secondary_instructionContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(MkshParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(MkshParser.CONTINUE, 0); }
		public Instruction_execContext instruction_exec() {
			return getRuleContext(Instruction_execContext.class,0);
		}
		public TerminalNode EXIT() { return getToken(MkshParser.EXIT, 0); }
		public TerminalNode EXPORT() { return getToken(MkshParser.EXPORT, 0); }
		public TerminalNode READONLY() { return getToken(MkshParser.READONLY, 0); }
		public TerminalNode RETURN() { return getToken(MkshParser.RETURN, 0); }
		public TerminalNode SET() { return getToken(MkshParser.SET, 0); }
		public TerminalNode SHIFT() { return getToken(MkshParser.SHIFT, 0); }
		public TerminalNode TIMES() { return getToken(MkshParser.TIMES, 0); }
		public TerminalNode TRAP() { return getToken(MkshParser.TRAP, 0); }
		public TerminalNode UNSET() { return getToken(MkshParser.UNSET, 0); }
		public TerminalNode BUILTIN() { return getToken(MkshParser.BUILTIN, 0); }
		public TerminalNode GLOBAL() { return getToken(MkshParser.GLOBAL, 0); }
		public TerminalNode TYPESET() { return getToken(MkshParser.TYPESET, 0); }
		public TerminalNode WAIT() { return getToken(MkshParser.WAIT, 0); }
		public TerminalNode ALIAS() { return getToken(MkshParser.ALIAS, 0); }
		public TerminalNode BG() { return getToken(MkshParser.BG, 0); }
		public TerminalNode BIND() { return getToken(MkshParser.BIND, 0); }
		public TerminalNode CAT() { return getToken(MkshParser.CAT, 0); }
		public TerminalNode CD() { return getToken(MkshParser.CD, 0); }
		public TerminalNode COMMAND() { return getToken(MkshParser.COMMAND, 0); }
		public TerminalNode ECHO() { return getToken(MkshParser.ECHO, 0); }
		public TerminalNode FALSE() { return getToken(MkshParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(MkshParser.TRUE, 0); }
		public TerminalNode FC() { return getToken(MkshParser.FC, 0); }
		public TerminalNode FG() { return getToken(MkshParser.FG, 0); }
		public TerminalNode GETOPTS() { return getToken(MkshParser.GETOPTS, 0); }
		public TerminalNode JOBS() { return getToken(MkshParser.JOBS, 0); }
		public TerminalNode KILL() { return getToken(MkshParser.KILL, 0); }
		public TerminalNode LET() { return getToken(MkshParser.LET, 0); }
		public TerminalNode MKNOD() { return getToken(MkshParser.MKNOD, 0); }
		public TerminalNode PRINT() { return getToken(MkshParser.PRINT, 0); }
		public TerminalNode PWD() { return getToken(MkshParser.PWD, 0); }
		public TerminalNode READ() { return getToken(MkshParser.READ, 0); }
		public TerminalNode REALPATH() { return getToken(MkshParser.REALPATH, 0); }
		public TerminalNode RENAME() { return getToken(MkshParser.RENAME, 0); }
		public TerminalNode SLEEP() { return getToken(MkshParser.SLEEP, 0); }
		public TerminalNode SUSPEND() { return getToken(MkshParser.SUSPEND, 0); }
		public TerminalNode TEST() { return getToken(MkshParser.TEST, 0); }
		public TerminalNode ULIMIT() { return getToken(MkshParser.ULIMIT, 0); }
		public TerminalNode UMASK() { return getToken(MkshParser.UMASK, 0); }
		public TerminalNode UNALIAS() { return getToken(MkshParser.UNALIAS, 0); }
		public TerminalNode WHENCE() { return getToken(MkshParser.WHENCE, 0); }
		public Secondary_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_secondary_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterSecondary_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitSecondary_instruction(this);
		}
	}

	public final Secondary_instructionContext secondary_instruction() throws RecognitionException {
		Secondary_instructionContext _localctx = new Secondary_instructionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_secondary_instruction);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BREAK:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(BREAK);
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				match(CONTINUE);
				}
				break;
			case EVAL:
			case EXEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				instruction_exec();
				}
				break;
			case EXIT:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				match(EXIT);
				}
				break;
			case EXPORT:
				enterOuterAlt(_localctx, 5);
				{
				setState(125);
				match(EXPORT);
				}
				break;
			case READONLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(126);
				match(READONLY);
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 7);
				{
				setState(127);
				match(RETURN);
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 8);
				{
				setState(128);
				match(SET);
				}
				break;
			case SHIFT:
				enterOuterAlt(_localctx, 9);
				{
				setState(129);
				match(SHIFT);
				}
				break;
			case TIMES:
				enterOuterAlt(_localctx, 10);
				{
				setState(130);
				match(TIMES);
				}
				break;
			case TRAP:
				enterOuterAlt(_localctx, 11);
				{
				setState(131);
				match(TRAP);
				}
				break;
			case UNSET:
				enterOuterAlt(_localctx, 12);
				{
				setState(132);
				match(UNSET);
				}
				break;
			case BUILTIN:
				enterOuterAlt(_localctx, 13);
				{
				setState(133);
				match(BUILTIN);
				}
				break;
			case GLOBAL:
				enterOuterAlt(_localctx, 14);
				{
				setState(134);
				match(GLOBAL);
				}
				break;
			case TYPESET:
				enterOuterAlt(_localctx, 15);
				{
				setState(135);
				match(TYPESET);
				}
				break;
			case WAIT:
				enterOuterAlt(_localctx, 16);
				{
				setState(136);
				match(WAIT);
				}
				break;
			case ALIAS:
				enterOuterAlt(_localctx, 17);
				{
				setState(137);
				match(ALIAS);
				}
				break;
			case BG:
				enterOuterAlt(_localctx, 18);
				{
				setState(138);
				match(BG);
				}
				break;
			case BIND:
				enterOuterAlt(_localctx, 19);
				{
				setState(139);
				match(BIND);
				}
				break;
			case CAT:
				enterOuterAlt(_localctx, 20);
				{
				setState(140);
				match(CAT);
				}
				break;
			case CD:
				enterOuterAlt(_localctx, 21);
				{
				setState(141);
				match(CD);
				}
				break;
			case COMMAND:
				enterOuterAlt(_localctx, 22);
				{
				setState(142);
				match(COMMAND);
				}
				break;
			case ECHO:
				enterOuterAlt(_localctx, 23);
				{
				setState(143);
				match(ECHO);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 24);
				{
				setState(144);
				match(FALSE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 25);
				{
				setState(145);
				match(TRUE);
				}
				break;
			case FC:
				enterOuterAlt(_localctx, 26);
				{
				setState(146);
				match(FC);
				}
				break;
			case FG:
				enterOuterAlt(_localctx, 27);
				{
				setState(147);
				match(FG);
				}
				break;
			case GETOPTS:
				enterOuterAlt(_localctx, 28);
				{
				setState(148);
				match(GETOPTS);
				}
				break;
			case JOBS:
				enterOuterAlt(_localctx, 29);
				{
				setState(149);
				match(JOBS);
				}
				break;
			case KILL:
				enterOuterAlt(_localctx, 30);
				{
				setState(150);
				match(KILL);
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 31);
				{
				setState(151);
				match(LET);
				}
				break;
			case MKNOD:
				enterOuterAlt(_localctx, 32);
				{
				setState(152);
				match(MKNOD);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 33);
				{
				setState(153);
				match(PRINT);
				}
				break;
			case PWD:
				enterOuterAlt(_localctx, 34);
				{
				setState(154);
				match(PWD);
				}
				break;
			case READ:
				enterOuterAlt(_localctx, 35);
				{
				setState(155);
				match(READ);
				}
				break;
			case REALPATH:
				enterOuterAlt(_localctx, 36);
				{
				setState(156);
				match(REALPATH);
				}
				break;
			case RENAME:
				enterOuterAlt(_localctx, 37);
				{
				setState(157);
				match(RENAME);
				}
				break;
			case SLEEP:
				enterOuterAlt(_localctx, 38);
				{
				setState(158);
				match(SLEEP);
				}
				break;
			case SUSPEND:
				enterOuterAlt(_localctx, 39);
				{
				setState(159);
				match(SUSPEND);
				}
				break;
			case TEST:
				enterOuterAlt(_localctx, 40);
				{
				setState(160);
				match(TEST);
				}
				break;
			case ULIMIT:
				enterOuterAlt(_localctx, 41);
				{
				setState(161);
				match(ULIMIT);
				}
				break;
			case UMASK:
				enterOuterAlt(_localctx, 42);
				{
				setState(162);
				match(UMASK);
				}
				break;
			case UNALIAS:
				enterOuterAlt(_localctx, 43);
				{
				setState(163);
				match(UNALIAS);
				}
				break;
			case WHENCE:
				enterOuterAlt(_localctx, 44);
				{
				setState(164);
				match(WHENCE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Instruction_timeContext extends ParserRuleContext {
		public TerminalNode TIME() { return getToken(MkshParser.TIME, 0); }
		public Instruction_timeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction_time; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterInstruction_time(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitInstruction_time(this);
		}
	}

	public final Instruction_timeContext instruction_time() throws RecognitionException {
		Instruction_timeContext _localctx = new Instruction_timeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_instruction_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(TIME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Executable_instructionContext extends ParserRuleContext {
		public Secondary_instructionContext secondary_instruction() {
			return getRuleContext(Secondary_instructionContext.class,0);
		}
		public Instruction_timeContext instruction_time() {
			return getRuleContext(Instruction_timeContext.class,0);
		}
		public Executable_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_executable_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecutable_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecutable_instruction(this);
		}
	}

	public final Executable_instructionContext executable_instruction() throws RecognitionException {
		Executable_instructionContext _localctx = new Executable_instructionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_executable_instruction);
		try {
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BREAK:
			case CONTINUE:
			case EVAL:
			case EXEC:
			case EXIT:
			case EXPORT:
			case READONLY:
			case RETURN:
			case SET:
			case SHIFT:
			case TIMES:
			case TRAP:
			case UNSET:
			case BUILTIN:
			case GLOBAL:
			case TYPESET:
			case WAIT:
			case ALIAS:
			case BG:
			case BIND:
			case CAT:
			case CD:
			case COMMAND:
			case ECHO:
			case FALSE:
			case TRUE:
			case FC:
			case FG:
			case GETOPTS:
			case JOBS:
			case KILL:
			case LET:
			case MKNOD:
			case PRINT:
			case PWD:
			case READ:
			case REALPATH:
			case RENAME:
			case SLEEP:
			case SUSPEND:
			case TEST:
			case ULIMIT:
			case UMASK:
			case UNALIAS:
			case WHENCE:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				secondary_instruction();
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				instruction_time();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Instruction_execContext extends ParserRuleContext {
		public TerminalNode EXEC() { return getToken(MkshParser.EXEC, 0); }
		public Executable_instructionContext executable_instruction() {
			return getRuleContext(Executable_instructionContext.class,0);
		}
		public TerminalNode EVAL() { return getToken(MkshParser.EVAL, 0); }
		public Instruction_execContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction_exec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterInstruction_exec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitInstruction_exec(this);
		}
	}

	public final Instruction_execContext instruction_exec() throws RecognitionException {
		Instruction_execContext _localctx = new Instruction_execContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_instruction_exec);
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				match(EXEC);
				setState(174);
				executable_instruction();
				}
				break;
			case EVAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(EVAL);
				setState(176);
				executable_instruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_controlContext extends ParserRuleContext {
		public Execution_control_case_esacContext execution_control_case_esac() {
			return getRuleContext(Execution_control_case_esacContext.class,0);
		}
		public Execution_control_for_do_doneContext execution_control_for_do_done() {
			return getRuleContext(Execution_control_for_do_doneContext.class,0);
		}
		public Execution_control_if_then_elseContext execution_control_if_then_else() {
			return getRuleContext(Execution_control_if_then_elseContext.class,0);
		}
		public Execution_control_select_inContext execution_control_select_in() {
			return getRuleContext(Execution_control_select_inContext.class,0);
		}
		public Execution_control_until_doContext execution_control_until_do() {
			return getRuleContext(Execution_control_until_doContext.class,0);
		}
		public Execution_control_while_doContext execution_control_while_do() {
			return getRuleContext(Execution_control_while_doContext.class,0);
		}
		public Execution_control_functionContext execution_control_function() {
			return getRuleContext(Execution_control_functionContext.class,0);
		}
		public Execution_control_function_wo_kwrdContext execution_control_function_wo_kwrd() {
			return getRuleContext(Execution_control_function_wo_kwrdContext.class,0);
		}
		public Execution_controlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control(this);
		}
	}

	public final Execution_controlContext execution_control() throws RecognitionException {
		Execution_controlContext _localctx = new Execution_controlContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_execution_control);
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				execution_control_case_esac();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				execution_control_for_do_done();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(181);
				execution_control_if_then_else();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(182);
				execution_control_select_in();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(183);
				execution_control_until_do();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(184);
				execution_control_while_do();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(185);
				execution_control_function();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(186);
				execution_control_function_wo_kwrd();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_case_esacContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(MkshParser.CASE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public TerminalNode ESAC() { return getToken(MkshParser.ESAC, 0); }
		public List<TerminalNode> TERMINATOR() { return getTokens(MkshParser.TERMINATOR); }
		public TerminalNode TERMINATOR(int i) {
			return getToken(MkshParser.TERMINATOR, i);
		}
		public List<Expression_endContext> expression_end() {
			return getRuleContexts(Expression_endContext.class);
		}
		public Expression_endContext expression_end(int i) {
			return getRuleContext(Expression_endContext.class,i);
		}
		public List<TerminalNode> STRING() { return getTokens(MkshParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MkshParser.STRING, i);
		}
		public List<TerminalNode> P_R_PARENTHESIS() { return getTokens(MkshParser.P_R_PARENTHESIS); }
		public TerminalNode P_R_PARENTHESIS(int i) {
			return getToken(MkshParser.P_R_PARENTHESIS, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> EXECUTION_CONTROL_CASE_ESAC_TERMINATOR() { return getTokens(MkshParser.EXECUTION_CONTROL_CASE_ESAC_TERMINATOR); }
		public TerminalNode EXECUTION_CONTROL_CASE_ESAC_TERMINATOR(int i) {
			return getToken(MkshParser.EXECUTION_CONTROL_CASE_ESAC_TERMINATOR, i);
		}
		public List<TerminalNode> P_L_PARENTHESIS() { return getTokens(MkshParser.P_L_PARENTHESIS); }
		public TerminalNode P_L_PARENTHESIS(int i) {
			return getToken(MkshParser.P_L_PARENTHESIS, i);
		}
		public Execution_control_case_esacContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_case_esac; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_case_esac(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_case_esac(this);
		}
	}

	public final Execution_control_case_esacContext execution_control_case_esac() throws RecognitionException {
		Execution_control_case_esacContext _localctx = new Execution_control_case_esacContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_execution_control_case_esac);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(CASE);
			setState(190);
			identifier();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TERMINATOR) {
				{
				{
				setState(191);
				match(TERMINATOR);
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
			match(IN);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==P_SEMI || _la==TERMINATOR) {
				{
				{
				setState(198);
				expression_end();
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==P_L_PARENTHESIS || _la==STRING) {
				{
				{
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==P_L_PARENTHESIS) {
					{
					setState(204);
					match(P_L_PARENTHESIS);
					}
				}

				setState(207);
				match(STRING);
				setState(208);
				match(P_R_PARENTHESIS);
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(209);
						expression_end();
						}
						} 
					}
					setState(214);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				}
				setState(215);
				expr();
				setState(216);
				match(EXECUTION_CONTROL_CASE_ESAC_TERMINATOR);
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==P_SEMI || _la==TERMINATOR) {
					{
					{
					setState(217);
					expression_end();
					}
					}
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(228);
			match(ESAC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_for_do_doneContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MkshParser.FOR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public List<Expression_endContext> expression_end() {
			return getRuleContexts(Expression_endContext.class);
		}
		public Expression_endContext expression_end(int i) {
			return getRuleContext(Expression_endContext.class,i);
		}
		public List<TerminalNode> TERMINATOR() { return getTokens(MkshParser.TERMINATOR); }
		public TerminalNode TERMINATOR(int i) {
			return getToken(MkshParser.TERMINATOR, i);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public Execution_control_for_do_doneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_for_do_done; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_for_do_done(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_for_do_done(this);
		}
	}

	public final Execution_control_for_do_doneContext execution_control_for_do_done() throws RecognitionException {
		Execution_control_for_do_doneContext _localctx = new Execution_control_for_do_doneContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_execution_control_for_do_done);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(FOR);
			setState(231);
			identifier();
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==TERMINATOR) {
					{
					{
					setState(232);
					match(TERMINATOR);
					}
					}
					setState(237);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(238);
				match(IN);
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(242);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==TERMINATOR) {
							{
							{
							setState(239);
							match(TERMINATOR);
							}
							}
							setState(244);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(245);
						string();
						setState(249);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(246);
								match(TERMINATOR);
								}
								} 
							}
							setState(251);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
						}
						}
						} 
					}
					setState(256);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
				}
				break;
			}
			setState(260); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(259);
				expression_end();
				}
				}
				setState(262); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==P_SEMI || _la==TERMINATOR );
			setState(264);
			match(DO);
			setState(265);
			expr();
			setState(266);
			match(DONE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_if_then_elseContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MkshParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> THEN() { return getTokens(MkshParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(MkshParser.THEN, i);
		}
		public TerminalNode FI() { return getToken(MkshParser.FI, 0); }
		public List<TerminalNode> ELIF() { return getTokens(MkshParser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(MkshParser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(MkshParser.ELSE, 0); }
		public Execution_control_if_then_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_if_then_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_if_then_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_if_then_else(this);
		}
	}

	public final Execution_control_if_then_elseContext execution_control_if_then_else() throws RecognitionException {
		Execution_control_if_then_elseContext _localctx = new Execution_control_if_then_elseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_execution_control_if_then_else);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(IF);
			setState(269);
			expr();
			setState(270);
			match(THEN);
			setState(271);
			expr();
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(272);
				match(ELIF);
				setState(273);
				expr();
				setState(274);
				match(THEN);
				setState(275);
				expr();
				}
				}
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(282);
				match(ELSE);
				setState(283);
				expr();
				}
			}

			setState(286);
			match(FI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_select_inContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(MkshParser.SELECT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public List<TerminalNode> TERMINATOR() { return getTokens(MkshParser.TERMINATOR); }
		public TerminalNode TERMINATOR(int i) {
			return getToken(MkshParser.TERMINATOR, i);
		}
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public List<Expression_endContext> expression_end() {
			return getRuleContexts(Expression_endContext.class);
		}
		public Expression_endContext expression_end(int i) {
			return getRuleContext(Expression_endContext.class,i);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public Execution_control_select_inContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_select_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_select_in(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_select_in(this);
		}
	}

	public final Execution_control_select_inContext execution_control_select_in() throws RecognitionException {
		Execution_control_select_inContext _localctx = new Execution_control_select_inContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_execution_control_select_in);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(SELECT);
			setState(289);
			identifier();
			setState(293);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(290);
					match(TERMINATOR);
					}
					} 
				}
				setState(295);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(296);
				match(IN);
				setState(312);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(300);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==TERMINATOR) {
							{
							{
							setState(297);
							match(TERMINATOR);
							}
							}
							setState(302);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(303);
						string();
						setState(307);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(304);
								match(TERMINATOR);
								}
								} 
							}
							setState(309);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
						}
						}
						} 
					}
					setState(314);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				}
				}
			}

			setState(318); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(317);
				expression_end();
				}
				}
				setState(320); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==P_SEMI || _la==TERMINATOR );
			setState(322);
			match(DO);
			setState(323);
			expr();
			setState(324);
			match(DONE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_until_doContext extends ParserRuleContext {
		public TerminalNode UNTIL() { return getToken(MkshParser.UNTIL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public Execution_control_until_doContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_until_do; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_until_do(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_until_do(this);
		}
	}

	public final Execution_control_until_doContext execution_control_until_do() throws RecognitionException {
		Execution_control_until_doContext _localctx = new Execution_control_until_doContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_execution_control_until_do);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(UNTIL);
			setState(327);
			expr();
			setState(328);
			match(DO);
			setState(329);
			expr();
			setState(330);
			match(DONE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_while_doContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MkshParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public Execution_control_while_doContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_while_do; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_while_do(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_while_do(this);
		}
	}

	public final Execution_control_while_doContext execution_control_while_do() throws RecognitionException {
		Execution_control_while_doContext _localctx = new Execution_control_while_doContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_execution_control_while_do);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			match(WHILE);
			setState(333);
			expr();
			setState(334);
			match(DO);
			setState(335);
			expr();
			setState(336);
			match(DONE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_functionContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(MkshParser.FUNCTION, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode P_L_PARENTHESIS() { return getToken(MkshParser.P_L_PARENTHESIS, 0); }
		public TerminalNode P_R_PARENTHESIS() { return getToken(MkshParser.P_R_PARENTHESIS, 0); }
		public TerminalNode P_L_BRACKET() { return getToken(MkshParser.P_L_BRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode P_R_BRACKET() { return getToken(MkshParser.P_R_BRACKET, 0); }
		public List<TerminalNode> TERMINATOR() { return getTokens(MkshParser.TERMINATOR); }
		public TerminalNode TERMINATOR(int i) {
			return getToken(MkshParser.TERMINATOR, i);
		}
		public List<TerminalNode> P_COMMA() { return getTokens(MkshParser.P_COMMA); }
		public TerminalNode P_COMMA(int i) {
			return getToken(MkshParser.P_COMMA, i);
		}
		public Execution_control_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_function(this);
		}
	}

	public final Execution_control_functionContext execution_control_function() throws RecognitionException {
		Execution_control_functionContext _localctx = new Execution_control_functionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_execution_control_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(FUNCTION);
			setState(339);
			identifier();
			setState(343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TERMINATOR) {
				{
				{
				setState(340);
				match(TERMINATOR);
				}
				}
				setState(345);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(346);
			match(P_L_PARENTHESIS);
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CASE) | (1L << ELSE) | (1L << FUNCTION) | (1L << THEN) | (1L << DO) | (1L << ESAC) | (1L << IF) | (1L << TIME) | (1L << DONE) | (1L << FI) | (1L << IN) | (1L << UNTIL) | (1L << ELIF) | (1L << FOR) | (1L << SELECT) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << EVAL) | (1L << EXEC) | (1L << EXIT) | (1L << EXPORT) | (1L << READONLY) | (1L << RETURN) | (1L << SET) | (1L << SHIFT) | (1L << TIMES) | (1L << TRAP) | (1L << UNSET) | (1L << BUILTIN) | (1L << GLOBAL) | (1L << TYPESET) | (1L << WAIT) | (1L << ALIAS) | (1L << BG) | (1L << BIND) | (1L << CAT) | (1L << CD) | (1L << COMMAND) | (1L << ECHO) | (1L << FALSE) | (1L << TRUE) | (1L << FC) | (1L << FG) | (1L << GETOPTS) | (1L << JOBS) | (1L << KILL) | (1L << LET) | (1L << MKNOD) | (1L << PRINT) | (1L << PWD) | (1L << READ) | (1L << REALPATH) | (1L << RENAME) | (1L << SLEEP) | (1L << SUSPEND) | (1L << TEST) | (1L << ULIMIT) | (1L << UMASK) | (1L << UNALIAS) | (1L << WHENCE))) != 0) || _la==IDENTIFIER) {
				{
				setState(347);
				identifier();
				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==P_COMMA) {
					{
					{
					setState(348);
					match(P_COMMA);
					setState(349);
					identifier();
					}
					}
					setState(354);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(357);
			match(P_R_PARENTHESIS);
			setState(361);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TERMINATOR) {
				{
				{
				setState(358);
				match(TERMINATOR);
				}
				}
				setState(363);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(364);
			match(P_L_BRACKET);
			setState(365);
			expr();
			setState(366);
			match(P_R_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Execution_control_function_wo_kwrdContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode P_L_PARENTHESIS() { return getToken(MkshParser.P_L_PARENTHESIS, 0); }
		public TerminalNode P_R_PARENTHESIS() { return getToken(MkshParser.P_R_PARENTHESIS, 0); }
		public Encapsulated_expressionContext encapsulated_expression() {
			return getRuleContext(Encapsulated_expressionContext.class,0);
		}
		public Executable_instructionContext executable_instruction() {
			return getRuleContext(Executable_instructionContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public AritContext arit() {
			return getRuleContext(AritContext.class,0);
		}
		public List<TerminalNode> TERMINATOR() { return getTokens(MkshParser.TERMINATOR); }
		public TerminalNode TERMINATOR(int i) {
			return getToken(MkshParser.TERMINATOR, i);
		}
		public List<TerminalNode> P_COMMA() { return getTokens(MkshParser.P_COMMA); }
		public TerminalNode P_COMMA(int i) {
			return getToken(MkshParser.P_COMMA, i);
		}
		public Execution_control_function_wo_kwrdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution_control_function_wo_kwrd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExecution_control_function_wo_kwrd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExecution_control_function_wo_kwrd(this);
		}
	}

	public final Execution_control_function_wo_kwrdContext execution_control_function_wo_kwrd() throws RecognitionException {
		Execution_control_function_wo_kwrdContext _localctx = new Execution_control_function_wo_kwrdContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_execution_control_function_wo_kwrd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			identifier();
			setState(369);
			match(P_L_PARENTHESIS);
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CASE) | (1L << ELSE) | (1L << FUNCTION) | (1L << THEN) | (1L << DO) | (1L << ESAC) | (1L << IF) | (1L << TIME) | (1L << DONE) | (1L << FI) | (1L << IN) | (1L << UNTIL) | (1L << ELIF) | (1L << FOR) | (1L << SELECT) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << EVAL) | (1L << EXEC) | (1L << EXIT) | (1L << EXPORT) | (1L << READONLY) | (1L << RETURN) | (1L << SET) | (1L << SHIFT) | (1L << TIMES) | (1L << TRAP) | (1L << UNSET) | (1L << BUILTIN) | (1L << GLOBAL) | (1L << TYPESET) | (1L << WAIT) | (1L << ALIAS) | (1L << BG) | (1L << BIND) | (1L << CAT) | (1L << CD) | (1L << COMMAND) | (1L << ECHO) | (1L << FALSE) | (1L << TRUE) | (1L << FC) | (1L << FG) | (1L << GETOPTS) | (1L << JOBS) | (1L << KILL) | (1L << LET) | (1L << MKNOD) | (1L << PRINT) | (1L << PWD) | (1L << READ) | (1L << REALPATH) | (1L << RENAME) | (1L << SLEEP) | (1L << SUSPEND) | (1L << TEST) | (1L << ULIMIT) | (1L << UMASK) | (1L << UNALIAS) | (1L << WHENCE))) != 0) || _la==IDENTIFIER) {
				{
				setState(370);
				identifier();
				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==P_COMMA) {
					{
					{
					setState(371);
					match(P_COMMA);
					setState(372);
					identifier();
					}
					}
					setState(377);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(380);
			match(P_R_PARENTHESIS);
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TERMINATOR) {
				{
				{
				setState(381);
				match(TERMINATOR);
				}
				}
				setState(386);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(391);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(387);
				encapsulated_expression();
				}
				break;
			case 2:
				{
				setState(388);
				executable_instruction();
				}
				break;
			case 3:
				{
				setState(389);
				assignment();
				}
				break;
			case 4:
				{
				setState(390);
				arit();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AritContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(MkshParser.LET, 0); }
		public A_exprContext a_expr() {
			return getRuleContext(A_exprContext.class,0);
		}
		public TerminalNode ARIT_OPERATOR_L() { return getToken(MkshParser.ARIT_OPERATOR_L, 0); }
		public TerminalNode ARIT_OPERATOR_R() { return getToken(MkshParser.ARIT_OPERATOR_R, 0); }
		public AritContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterArit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitArit(this);
		}
	}

	public final AritContext arit() throws RecognitionException {
		AritContext _localctx = new AritContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_arit);
		try {
			setState(399);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				enterOuterAlt(_localctx, 1);
				{
				setState(393);
				match(LET);
				setState(394);
				a_expr(0);
				}
				break;
			case ARIT_OPERATOR_L:
				enterOuterAlt(_localctx, 2);
				{
				setState(395);
				match(ARIT_OPERATOR_L);
				setState(396);
				a_expr(0);
				setState(397);
				match(ARIT_OPERATOR_R);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class A_immediateContext extends ParserRuleContext {
		public TerminalNode ARIT_ONE() { return getToken(MkshParser.ARIT_ONE, 0); }
		public A_immediateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a_immediate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterA_immediate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitA_immediate(this);
		}
	}

	public final A_immediateContext a_immediate() throws RecognitionException {
		A_immediateContext _localctx = new A_immediateContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_a_immediate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			match(ARIT_ONE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class A_operandContext extends ParserRuleContext {
		public A_immediateContext a_immediate() {
			return getRuleContext(A_immediateContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public A_operandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterA_operand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitA_operand(this);
		}
	}

	public final A_operandContext a_operand() throws RecognitionException {
		A_operandContext _localctx = new A_operandContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_a_operand);
		try {
			setState(405);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARIT_ONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				a_immediate();
				}
				break;
			case CASE:
			case ELSE:
			case FUNCTION:
			case THEN:
			case DO:
			case ESAC:
			case IF:
			case TIME:
			case DONE:
			case FI:
			case IN:
			case UNTIL:
			case ELIF:
			case FOR:
			case SELECT:
			case WHILE:
			case BREAK:
			case CONTINUE:
			case EVAL:
			case EXEC:
			case EXIT:
			case EXPORT:
			case READONLY:
			case RETURN:
			case SET:
			case SHIFT:
			case TIMES:
			case TRAP:
			case UNSET:
			case BUILTIN:
			case GLOBAL:
			case TYPESET:
			case WAIT:
			case ALIAS:
			case BG:
			case BIND:
			case CAT:
			case CD:
			case COMMAND:
			case ECHO:
			case FALSE:
			case TRUE:
			case FC:
			case FG:
			case GETOPTS:
			case JOBS:
			case KILL:
			case LET:
			case MKNOD:
			case PRINT:
			case PWD:
			case READ:
			case REALPATH:
			case RENAME:
			case SLEEP:
			case SUSPEND:
			case TEST:
			case ULIMIT:
			case UMASK:
			case UNALIAS:
			case WHENCE:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				identifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class A_exprContext extends ParserRuleContext {
		public A_operandContext a_operand() {
			return getRuleContext(A_operandContext.class,0);
		}
		public List<A_exprContext> a_expr() {
			return getRuleContexts(A_exprContext.class);
		}
		public A_exprContext a_expr(int i) {
			return getRuleContext(A_exprContext.class,i);
		}
		public A_operator_binaryContext a_operator_binary() {
			return getRuleContext(A_operator_binaryContext.class,0);
		}
		public A_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterA_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitA_expr(this);
		}
	}

	public final A_exprContext a_expr() throws RecognitionException {
		return a_expr(0);
	}

	private A_exprContext a_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		A_exprContext _localctx = new A_exprContext(_ctx, _parentState);
		A_exprContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_a_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(408);
			a_operand();
			}
			_ctx.stop = _input.LT(-1);
			setState(418);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(416);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
					case 1:
						{
						_localctx = new A_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_a_expr);
						setState(410);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(411);
						a_operator_binary();
						setState(412);
						a_expr(3);
						}
						break;
					case 2:
						{
						_localctx = new A_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_a_expr);
						setState(414);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(415);
						a_operator_binary();
						}
						break;
					}
					} 
				}
				setState(420);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class A_operator_binaryContext extends ParserRuleContext {
		public TerminalNode ARIT_A() { return getToken(MkshParser.ARIT_A, 0); }
		public TerminalNode ARIT_A_PLUS() { return getToken(MkshParser.ARIT_A_PLUS, 0); }
		public TerminalNode ARIT_A_MINUS() { return getToken(MkshParser.ARIT_A_MINUS, 0); }
		public TerminalNode ARIT_A_DIV() { return getToken(MkshParser.ARIT_A_DIV, 0); }
		public TerminalNode ARIT_A_MOD() { return getToken(MkshParser.ARIT_A_MOD, 0); }
		public TerminalNode ARIT_A_L_SHIFT() { return getToken(MkshParser.ARIT_A_L_SHIFT, 0); }
		public TerminalNode ARIT_A_R_SHIFT() { return getToken(MkshParser.ARIT_A_R_SHIFT, 0); }
		public TerminalNode ARIT_A_L_ROTATE() { return getToken(MkshParser.ARIT_A_L_ROTATE, 0); }
		public TerminalNode ARIT_A_R_ROTATE() { return getToken(MkshParser.ARIT_A_R_ROTATE, 0); }
		public TerminalNode ARIT_A_XOR() { return getToken(MkshParser.ARIT_A_XOR, 0); }
		public TerminalNode ARIT_A_AND() { return getToken(MkshParser.ARIT_A_AND, 0); }
		public TerminalNode ARIT_A_OR() { return getToken(MkshParser.ARIT_A_OR, 0); }
		public TerminalNode ARIT_A_STAR() { return getToken(MkshParser.ARIT_A_STAR, 0); }
		public TerminalNode P_COMMA() { return getToken(MkshParser.P_COMMA, 0); }
		public A_operator_binaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a_operator_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterA_operator_binary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitA_operator_binary(this);
		}
	}

	public final A_operator_binaryContext a_operator_binary() throws RecognitionException {
		A_operator_binaryContext _localctx = new A_operator_binaryContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_a_operator_binary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (ARIT_A - 71)) | (1L << (ARIT_A_PLUS - 71)) | (1L << (ARIT_A_MINUS - 71)) | (1L << (ARIT_A_STAR - 71)) | (1L << (ARIT_A_DIV - 71)) | (1L << (ARIT_A_MOD - 71)) | (1L << (ARIT_A_L_SHIFT - 71)) | (1L << (ARIT_A_R_SHIFT - 71)) | (1L << (ARIT_A_L_ROTATE - 71)) | (1L << (ARIT_A_R_ROTATE - 71)) | (1L << (ARIT_A_XOR - 71)) | (1L << (ARIT_A_AND - 71)) | (1L << (ARIT_A_OR - 71)) | (1L << (P_COMMA - 71)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class A_operator_unaryContext extends ParserRuleContext {
		public A_operator_unaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a_operator_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterA_operator_unary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitA_operator_unary(this);
		}
	}

	public final A_operator_unaryContext a_operator_unary() throws RecognitionException {
		A_operator_unaryContext _localctx = new A_operator_unaryContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_a_operator_unary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ARIT_A() { return getToken(MkshParser.ARIT_A, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			identifier();
			setState(426);
			match(ARIT_A);
			setState(427);
			string();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 27:
			return a_expr_sempred((A_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean a_expr_sempred(A_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3g\u01b0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4J\n\4\3\4\5\4M\n\4\3\4\3\4\3\4\6\4"+
		"R\n\4\r\4\16\4S\5\4V\n\4\3\4\5\4Y\n\4\3\5\3\5\3\5\3\5\5\5_\n\5\3\5\3\5"+
		"\3\5\3\5\5\5e\n\5\5\5g\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\5\bp\n\b\3\t\3"+
		"\t\3\n\3\n\5\nv\n\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u00a8\n\r\3\16\3\16\3\17\3\17\5\17\u00ae\n\17\3\20\3\20\3\20"+
		"\3\20\5\20\u00b4\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00be"+
		"\n\21\3\22\3\22\3\22\7\22\u00c3\n\22\f\22\16\22\u00c6\13\22\3\22\3\22"+
		"\7\22\u00ca\n\22\f\22\16\22\u00cd\13\22\3\22\5\22\u00d0\n\22\3\22\3\22"+
		"\3\22\7\22\u00d5\n\22\f\22\16\22\u00d8\13\22\3\22\3\22\3\22\7\22\u00dd"+
		"\n\22\f\22\16\22\u00e0\13\22\7\22\u00e2\n\22\f\22\16\22\u00e5\13\22\3"+
		"\22\3\22\3\23\3\23\3\23\7\23\u00ec\n\23\f\23\16\23\u00ef\13\23\3\23\3"+
		"\23\7\23\u00f3\n\23\f\23\16\23\u00f6\13\23\3\23\3\23\7\23\u00fa\n\23\f"+
		"\23\16\23\u00fd\13\23\7\23\u00ff\n\23\f\23\16\23\u0102\13\23\5\23\u0104"+
		"\n\23\3\23\6\23\u0107\n\23\r\23\16\23\u0108\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0118\n\24\f\24\16\24\u011b"+
		"\13\24\3\24\3\24\5\24\u011f\n\24\3\24\3\24\3\25\3\25\3\25\7\25\u0126\n"+
		"\25\f\25\16\25\u0129\13\25\3\25\3\25\7\25\u012d\n\25\f\25\16\25\u0130"+
		"\13\25\3\25\3\25\7\25\u0134\n\25\f\25\16\25\u0137\13\25\7\25\u0139\n\25"+
		"\f\25\16\25\u013c\13\25\5\25\u013e\n\25\3\25\6\25\u0141\n\25\r\25\16\25"+
		"\u0142\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\7\30\u0158\n\30\f\30\16\30\u015b\13\30"+
		"\3\30\3\30\3\30\3\30\7\30\u0161\n\30\f\30\16\30\u0164\13\30\5\30\u0166"+
		"\n\30\3\30\3\30\7\30\u016a\n\30\f\30\16\30\u016d\13\30\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\7\31\u0178\n\31\f\31\16\31\u017b\13\31"+
		"\5\31\u017d\n\31\3\31\3\31\7\31\u0181\n\31\f\31\16\31\u0184\13\31\3\31"+
		"\3\31\3\31\3\31\5\31\u018a\n\31\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0192"+
		"\n\32\3\33\3\33\3\34\3\34\5\34\u0198\n\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\7\35\u01a3\n\35\f\35\16\35\u01a6\13\35\3\36\3\36\3\37"+
		"\3\37\3 \3 \3 \3 \3 \2\38!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$"+
		"&(*,.\60\62\64\668:<>\2\6\4\2WWff\3\2\3\22\3\2\23?\4\2IUYY\2\u01f5\2@"+
		"\3\2\2\2\4B\3\2\2\2\6U\3\2\2\2\bf\3\2\2\2\nh\3\2\2\2\fj\3\2\2\2\16o\3"+
		"\2\2\2\20q\3\2\2\2\22u\3\2\2\2\24w\3\2\2\2\26y\3\2\2\2\30\u00a7\3\2\2"+
		"\2\32\u00a9\3\2\2\2\34\u00ad\3\2\2\2\36\u00b3\3\2\2\2 \u00bd\3\2\2\2\""+
		"\u00bf\3\2\2\2$\u00e8\3\2\2\2&\u010e\3\2\2\2(\u0122\3\2\2\2*\u0148\3\2"+
		"\2\2,\u014e\3\2\2\2.\u0154\3\2\2\2\60\u0172\3\2\2\2\62\u0191\3\2\2\2\64"+
		"\u0193\3\2\2\2\66\u0197\3\2\2\28\u0199\3\2\2\2:\u01a7\3\2\2\2<\u01a9\3"+
		"\2\2\2>\u01ab\3\2\2\2@A\5\4\3\2A\3\3\2\2\2BC\5\6\4\2CD\7\2\2\3D\5\3\2"+
		"\2\2EJ\5\62\32\2FJ\5 \21\2GJ\5\22\n\2HJ\5> \2IE\3\2\2\2IF\3\2\2\2IG\3"+
		"\2\2\2IH\3\2\2\2JL\3\2\2\2KM\5\n\6\2LK\3\2\2\2LM\3\2\2\2MV\3\2\2\2NV\5"+
		"\b\5\2OV\5\f\7\2PR\7f\2\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3"+
		"\2\2\2UI\3\2\2\2UN\3\2\2\2UO\3\2\2\2UQ\3\2\2\2VX\3\2\2\2WY\5\6\4\2XW\3"+
		"\2\2\2XY\3\2\2\2Y\7\3\2\2\2Z[\7\\\2\2[\\\5\6\4\2\\^\7]\2\2]_\5\n\6\2^"+
		"]\3\2\2\2^_\3\2\2\2_g\3\2\2\2`a\7Z\2\2ab\5\6\4\2bd\7[\2\2ce\5\n\6\2dc"+
		"\3\2\2\2de\3\2\2\2eg\3\2\2\2fZ\3\2\2\2f`\3\2\2\2g\t\3\2\2\2hi\t\2\2\2"+
		"i\13\3\2\2\2jk\7g\2\2k\r\3\2\2\2lp\7d\2\2mp\5\24\13\2np\5\26\f\2ol\3\2"+
		"\2\2om\3\2\2\2on\3\2\2\2p\17\3\2\2\2qr\7c\2\2r\21\3\2\2\2sv\5\32\16\2"+
		"tv\5\30\r\2us\3\2\2\2ut\3\2\2\2v\23\3\2\2\2wx\t\3\2\2x\25\3\2\2\2yz\t"+
		"\4\2\2z\27\3\2\2\2{\u00a8\7\23\2\2|\u00a8\7\24\2\2}\u00a8\5\36\20\2~\u00a8"+
		"\7\27\2\2\177\u00a8\7\30\2\2\u0080\u00a8\7\31\2\2\u0081\u00a8\7\32\2\2"+
		"\u0082\u00a8\7\33\2\2\u0083\u00a8\7\34\2\2\u0084\u00a8\7\35\2\2\u0085"+
		"\u00a8\7\36\2\2\u0086\u00a8\7\37\2\2\u0087\u00a8\7 \2\2\u0088\u00a8\7"+
		"!\2\2\u0089\u00a8\7\"\2\2\u008a\u00a8\7#\2\2\u008b\u00a8\7$\2\2\u008c"+
		"\u00a8\7%\2\2\u008d\u00a8\7&\2\2\u008e\u00a8\7\'\2\2\u008f\u00a8\7(\2"+
		"\2\u0090\u00a8\7)\2\2\u0091\u00a8\7*\2\2\u0092\u00a8\7+\2\2\u0093\u00a8"+
		"\7,\2\2\u0094\u00a8\7-\2\2\u0095\u00a8\7.\2\2\u0096\u00a8\7/\2\2\u0097"+
		"\u00a8\7\60\2\2\u0098\u00a8\7\61\2\2\u0099\u00a8\7\62\2\2\u009a\u00a8"+
		"\7\63\2\2\u009b\u00a8\7\64\2\2\u009c\u00a8\7\65\2\2\u009d\u00a8\7\66\2"+
		"\2\u009e\u00a8\7\67\2\2\u009f\u00a8\78\2\2\u00a0\u00a8\79\2\2\u00a1\u00a8"+
		"\7:\2\2\u00a2\u00a8\7;\2\2\u00a3\u00a8\7<\2\2\u00a4\u00a8\7=\2\2\u00a5"+
		"\u00a8\7>\2\2\u00a6\u00a8\7?\2\2\u00a7{\3\2\2\2\u00a7|\3\2\2\2\u00a7}"+
		"\3\2\2\2\u00a7~\3\2\2\2\u00a7\177\3\2\2\2\u00a7\u0080\3\2\2\2\u00a7\u0081"+
		"\3\2\2\2\u00a7\u0082\3\2\2\2\u00a7\u0083\3\2\2\2\u00a7\u0084\3\2\2\2\u00a7"+
		"\u0085\3\2\2\2\u00a7\u0086\3\2\2\2\u00a7\u0087\3\2\2\2\u00a7\u0088\3\2"+
		"\2\2\u00a7\u0089\3\2\2\2\u00a7\u008a\3\2\2\2\u00a7\u008b\3\2\2\2\u00a7"+
		"\u008c\3\2\2\2\u00a7\u008d\3\2\2\2\u00a7\u008e\3\2\2\2\u00a7\u008f\3\2"+
		"\2\2\u00a7\u0090\3\2\2\2\u00a7\u0091\3\2\2\2\u00a7\u0092\3\2\2\2\u00a7"+
		"\u0093\3\2\2\2\u00a7\u0094\3\2\2\2\u00a7\u0095\3\2\2\2\u00a7\u0096\3\2"+
		"\2\2\u00a7\u0097\3\2\2\2\u00a7\u0098\3\2\2\2\u00a7\u0099\3\2\2\2\u00a7"+
		"\u009a\3\2\2\2\u00a7\u009b\3\2\2\2\u00a7\u009c\3\2\2\2\u00a7\u009d\3\2"+
		"\2\2\u00a7\u009e\3\2\2\2\u00a7\u009f\3\2\2\2\u00a7\u00a0\3\2\2\2\u00a7"+
		"\u00a1\3\2\2\2\u00a7\u00a2\3\2\2\2\u00a7\u00a3\3\2\2\2\u00a7\u00a4\3\2"+
		"\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\31\3\2\2\2\u00a9\u00aa"+
		"\7\n\2\2\u00aa\33\3\2\2\2\u00ab\u00ae\5\30\r\2\u00ac\u00ae\5\32\16\2\u00ad"+
		"\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\35\3\2\2\2\u00af\u00b0\7\26\2"+
		"\2\u00b0\u00b4\5\34\17\2\u00b1\u00b2\7\25\2\2\u00b2\u00b4\5\34\17\2\u00b3"+
		"\u00af\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\37\3\2\2\2\u00b5\u00be\5\"\22"+
		"\2\u00b6\u00be\5$\23\2\u00b7\u00be\5&\24\2\u00b8\u00be\5(\25\2\u00b9\u00be"+
		"\5*\26\2\u00ba\u00be\5,\27\2\u00bb\u00be\5.\30\2\u00bc\u00be\5\60\31\2"+
		"\u00bd\u00b5\3\2\2\2\u00bd\u00b6\3\2\2\2\u00bd\u00b7\3\2\2\2\u00bd\u00b8"+
		"\3\2\2\2\u00bd\u00b9\3\2\2\2\u00bd\u00ba\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd"+
		"\u00bc\3\2\2\2\u00be!\3\2\2\2\u00bf\u00c0\7\3\2\2\u00c0\u00c4\5\16\b\2"+
		"\u00c1\u00c3\7f\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2"+
		"\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7"+
		"\u00cb\7\r\2\2\u00c8\u00ca\5\n\6\2\u00c9\u00c8\3\2\2\2\u00ca\u00cd\3\2"+
		"\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00e3\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00ce\u00d0\7\\\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d6\7]\2\2\u00d3\u00d5"+
		"\5\n\6\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6"+
		"\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\5\6"+
		"\4\2\u00da\u00de\7V\2\2\u00db\u00dd\5\n\6\2\u00dc\u00db\3\2\2\2\u00dd"+
		"\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e2\3\2"+
		"\2\2\u00e0\u00de\3\2\2\2\u00e1\u00cf\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e6\u00e7\7\b\2\2\u00e7#\3\2\2\2\u00e8\u00e9\7\20\2\2\u00e9\u0103"+
		"\5\16\b\2\u00ea\u00ec\7f\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed"+
		"\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ed\3\2"+
		"\2\2\u00f0\u0100\7\r\2\2\u00f1\u00f3\7f\2\2\u00f2\u00f1\3\2\2\2\u00f3"+
		"\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f7\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00fb\5\20\t\2\u00f8\u00fa\7f\2\2\u00f9"+
		"\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2"+
		"\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00f4\3\2\2\2\u00ff"+
		"\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0104\3\2"+
		"\2\2\u0102\u0100\3\2\2\2\u0103\u00ed\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"\u0106\3\2\2\2\u0105\u0107\5\n\6\2\u0106\u0105\3\2\2\2\u0107\u0108\3\2"+
		"\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a"+
		"\u010b\7\7\2\2\u010b\u010c\5\6\4\2\u010c\u010d\7\13\2\2\u010d%\3\2\2\2"+
		"\u010e\u010f\7\t\2\2\u010f\u0110\5\6\4\2\u0110\u0111\7\6\2\2\u0111\u0119"+
		"\5\6\4\2\u0112\u0113\7\17\2\2\u0113\u0114\5\6\4\2\u0114\u0115\7\6\2\2"+
		"\u0115\u0116\5\6\4\2\u0116\u0118\3\2\2\2\u0117\u0112\3\2\2\2\u0118\u011b"+
		"\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011e\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011c\u011d\7\4\2\2\u011d\u011f\5\6\4\2\u011e\u011c\3\2"+
		"\2\2\u011e\u011f\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\7\f\2\2\u0121"+
		"\'\3\2\2\2\u0122\u0123\7\21\2\2\u0123\u0127\5\16\b\2\u0124\u0126\7f\2"+
		"\2\u0125\u0124\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128"+
		"\3\2\2\2\u0128\u013d\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u013a\7\r\2\2\u012b"+
		"\u012d\7f\2\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2"+
		"\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130\u012e\3\2\2\2\u0131"+
		"\u0135\5\20\t\2\u0132\u0134\7f\2\2\u0133\u0132\3\2\2\2\u0134\u0137\3\2"+
		"\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0139\3\2\2\2\u0137"+
		"\u0135\3\2\2\2\u0138\u012e\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2"+
		"\2\2\u013a\u013b\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013d"+
		"\u012a\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u0141\5\n"+
		"\6\2\u0140\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0143\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0145\7\7\2\2\u0145\u0146\5\6"+
		"\4\2\u0146\u0147\7\13\2\2\u0147)\3\2\2\2\u0148\u0149\7\16\2\2\u0149\u014a"+
		"\5\6\4\2\u014a\u014b\7\7\2\2\u014b\u014c\5\6\4\2\u014c\u014d\7\13\2\2"+
		"\u014d+\3\2\2\2\u014e\u014f\7\22\2\2\u014f\u0150\5\6\4\2\u0150\u0151\7"+
		"\7\2\2\u0151\u0152\5\6\4\2\u0152\u0153\7\13\2\2\u0153-\3\2\2\2\u0154\u0155"+
		"\7\5\2\2\u0155\u0159\5\16\b\2\u0156\u0158\7f\2\2\u0157\u0156\3\2\2\2\u0158"+
		"\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015c\3\2"+
		"\2\2\u015b\u0159\3\2\2\2\u015c\u0165\7\\\2\2\u015d\u0162\5\16\b\2\u015e"+
		"\u015f\7Y\2\2\u015f\u0161\5\16\b\2\u0160\u015e\3\2\2\2\u0161\u0164\3\2"+
		"\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0166\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0165\u015d\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0167\3\2"+
		"\2\2\u0167\u016b\7]\2\2\u0168\u016a\7f\2\2\u0169\u0168\3\2\2\2\u016a\u016d"+
		"\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016e\3\2\2\2\u016d"+
		"\u016b\3\2\2\2\u016e\u016f\7Z\2\2\u016f\u0170\5\6\4\2\u0170\u0171\7[\2"+
		"\2\u0171/\3\2\2\2\u0172\u0173\5\16\b\2\u0173\u017c\7\\\2\2\u0174\u0179"+
		"\5\16\b\2\u0175\u0176\7Y\2\2\u0176\u0178\5\16\b\2\u0177\u0175\3\2\2\2"+
		"\u0178\u017b\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017d"+
		"\3\2\2\2\u017b\u0179\3\2\2\2\u017c\u0174\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"\u017e\3\2\2\2\u017e\u0182\7]\2\2\u017f\u0181\7f\2\2\u0180\u017f\3\2\2"+
		"\2\u0181\u0184\3\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0189"+
		"\3\2\2\2\u0184\u0182\3\2\2\2\u0185\u018a\5\b\5\2\u0186\u018a\5\34\17\2"+
		"\u0187\u018a\5> \2\u0188\u018a\5\62\32\2\u0189\u0185\3\2\2\2\u0189\u0186"+
		"\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u0188\3\2\2\2\u018a\61\3\2\2\2\u018b"+
		"\u018c\7\62\2\2\u018c\u0192\58\35\2\u018d\u018e\7D\2\2\u018e\u018f\58"+
		"\35\2\u018f\u0190\7E\2\2\u0190\u0192\3\2\2\2\u0191\u018b\3\2\2\2\u0191"+
		"\u018d\3\2\2\2\u0192\63\3\2\2\2\u0193\u0194\7H\2\2\u0194\65\3\2\2\2\u0195"+
		"\u0198\5\64\33\2\u0196\u0198\5\16\b\2\u0197\u0195\3\2\2\2\u0197\u0196"+
		"\3\2\2\2\u0198\67\3\2\2\2\u0199\u019a\b\35\1\2\u019a\u019b\5\66\34\2\u019b"+
		"\u01a4\3\2\2\2\u019c\u019d\f\4\2\2\u019d\u019e\5:\36\2\u019e\u019f\58"+
		"\35\5\u019f\u01a3\3\2\2\2\u01a0\u01a1\f\5\2\2\u01a1\u01a3\5:\36\2\u01a2"+
		"\u019c\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3\2"+
		"\2\2\u01a4\u01a5\3\2\2\2\u01a59\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7\u01a8"+
		"\t\5\2\2\u01a8;\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa=\3\2\2\2\u01ab\u01ac"+
		"\5\16\b\2\u01ac\u01ad\7I\2\2\u01ad\u01ae\5\20\t\2\u01ae?\3\2\2\2\60IL"+
		"SUX^dfou\u00a7\u00ad\u00b3\u00bd\u00c4\u00cb\u00cf\u00d6\u00de\u00e3\u00ed"+
		"\u00f4\u00fb\u0100\u0103\u0108\u0119\u011e\u0127\u012e\u0135\u013a\u013d"+
		"\u0142\u0159\u0162\u0165\u016b\u0179\u017c\u0182\u0189\u0191\u0197\u01a2"+
		"\u01a4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}