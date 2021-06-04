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
		ARIT_A_OR=83, P_SEMI=84, P_INTERO=85, P_COMMA=86, P_L_BRACKET=87, P_R_BRACKET=88, 
		P_L_PARENTHESIS=89, P_R_PARENTHESIS=90, BACK_TICK=91, L_SHIFT=92, R_SHIFT=93, 
		GT=94, LT=95, STRING=96, IDENTIFIER=97, WS=98, TERMINATOR=99, LINE_COMMENT=100;
	public static final int
		RULE_start = 0, RULE_file = 1, RULE_expr = 2, RULE_instruction = 3, RULE_expression_end = 4, 
		RULE_comment = 5, RULE_identifier = 6, RULE_primary_keyword = 7, RULE_secondary_keyword = 8, 
		RULE_execution_control = 9, RULE_for_do_done = 10, RULE_if_then_else = 11, 
		RULE_select_in = 12, RULE_until_do = 13, RULE_while_do = 14, RULE_function = 15, 
		RULE_string = 16, RULE_arit = 17, RULE_a_immediate = 18, RULE_a_operand = 19, 
		RULE_a_expr = 20, RULE_a_operator_binary = 21, RULE_a_operator_unary = 22, 
		RULE_assignment = 23, RULE_exec = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "file", "expr", "instruction", "expression_end", "comment", 
			"identifier", "primary_keyword", "secondary_keyword", "execution_control", 
			"for_do_done", "if_then_else", "select_in", "until_do", "while_do", "function", 
			"string", "arit", "a_immediate", "a_operand", "a_expr", "a_operator_binary", 
			"a_operator_unary", "assignment", "exec"
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
			"'&='", "'|='", "';'", "'?'", "','", "'{'", "'}'", "'('", "')'", "'`'", 
			"'<<'", "'>>'", "'>'", "'<'"
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
			"ARIT_A_R_ROTATE", "ARIT_A_XOR", "ARIT_A_AND", "ARIT_A_OR", "P_SEMI", 
			"P_INTERO", "P_COMMA", "P_L_BRACKET", "P_R_BRACKET", "P_L_PARENTHESIS", 
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
			setState(50);
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
			setState(52);
			expr();
			setState(53);
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
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expression_endContext expression_end() {
			return getRuleContext(Expression_endContext.class,0);
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
			setState(68);
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
				{
				{
				setState(58);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(55);
					arit();
					}
					break;
				case 2:
					{
					setState(56);
					execution_control();
					}
					break;
				case 3:
					{
					setState(57);
					instruction();
					}
					break;
				}
				setState(60);
				expression_end();
				}
				}
				break;
			case LINE_COMMENT:
				{
				setState(62);
				comment();
				}
				break;
			case TERMINATOR:
				{
				setState(64); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(63);
						match(TERMINATOR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(66); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(71);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(70);
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

	public static class InstructionContext extends ParserRuleContext {
		public Primary_keywordContext primary_keyword() {
			return getRuleContext(Primary_keywordContext.class,0);
		}
		public Secondary_keywordContext secondary_keyword() {
			return getRuleContext(Secondary_keywordContext.class,0);
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
		enterRule(_localctx, 6, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
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
				{
				setState(73);
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
				{
				setState(74);
				secondary_keyword();
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
			setState(77);
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
			setState(79);
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
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
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
				setState(82);
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
				setState(83);
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

	public static class Primary_keywordContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(MkshParser.CASE, 0); }
		public TerminalNode ELSE() { return getToken(MkshParser.ELSE, 0); }
		public TerminalNode FUNCTION() { return getToken(MkshParser.FUNCTION, 0); }
		public TerminalNode THEN() { return getToken(MkshParser.THEN, 0); }
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public TerminalNode ESAC() { return getToken(MkshParser.ESAC, 0); }
		public TerminalNode IF() { return getToken(MkshParser.IF, 0); }
		public TerminalNode TIME() { return getToken(MkshParser.TIME, 0); }
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
		enterRule(_localctx, 14, RULE_primary_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
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
		public TerminalNode EVAL() { return getToken(MkshParser.EVAL, 0); }
		public TerminalNode EXEC() { return getToken(MkshParser.EXEC, 0); }
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
		enterRule(_localctx, 16, RULE_secondary_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
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

	public static class Execution_controlContext extends ParserRuleContext {
		public For_do_doneContext for_do_done() {
			return getRuleContext(For_do_doneContext.class,0);
		}
		public If_then_elseContext if_then_else() {
			return getRuleContext(If_then_elseContext.class,0);
		}
		public Select_inContext select_in() {
			return getRuleContext(Select_inContext.class,0);
		}
		public Until_doContext until_do() {
			return getRuleContext(Until_doContext.class,0);
		}
		public While_doContext while_do() {
			return getRuleContext(While_doContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
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
		enterRule(_localctx, 18, RULE_execution_control);
		try {
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				for_do_done();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				if_then_else();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				select_in();
				}
				break;
			case UNTIL:
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				until_do();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(94);
				while_do();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 6);
				{
				setState(95);
				function();
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

	public static class For_do_doneContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MkshParser.FOR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Expression_endContext expression_end() {
			return getRuleContext(Expression_endContext.class,0);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public For_do_doneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_do_done; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterFor_do_done(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitFor_do_done(this);
		}
	}

	public final For_do_doneContext for_do_done() throws RecognitionException {
		For_do_doneContext _localctx = new For_do_doneContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_for_do_done);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(FOR);
			setState(99);
			identifier();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(100);
				match(IN);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STRING) {
					{
					{
					setState(101);
					string();
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(109);
			expression_end();
			setState(110);
			match(DO);
			setState(111);
			expr();
			setState(112);
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

	public static class If_then_elseContext extends ParserRuleContext {
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
		public If_then_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_then_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterIf_then_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitIf_then_else(this);
		}
	}

	public final If_then_elseContext if_then_else() throws RecognitionException {
		If_then_elseContext _localctx = new If_then_elseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_if_then_else);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(IF);
			setState(115);
			expr();
			setState(116);
			match(THEN);
			setState(117);
			expr();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(118);
				match(ELIF);
				setState(119);
				expr();
				setState(120);
				match(THEN);
				setState(121);
				expr();
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(128);
				match(ELSE);
				setState(129);
				expr();
				}
			}

			setState(132);
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

	public static class Select_inContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(MkshParser.SELECT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Expression_endContext expression_end() {
			return getRuleContext(Expression_endContext.class,0);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public Select_inContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterSelect_in(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitSelect_in(this);
		}
	}

	public final Select_inContext select_in() throws RecognitionException {
		Select_inContext _localctx = new Select_inContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_select_in);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(SELECT);
			setState(135);
			identifier();
			{
			setState(136);
			match(IN);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(137);
				string();
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(143);
			expression_end();
			setState(144);
			match(DO);
			setState(145);
			expr();
			setState(146);
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

	public static class Until_doContext extends ParserRuleContext {
		public TerminalNode UNTIL() { return getToken(MkshParser.UNTIL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public Until_doContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_until_do; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterUntil_do(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitUntil_do(this);
		}
	}

	public final Until_doContext until_do() throws RecognitionException {
		Until_doContext _localctx = new Until_doContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_until_do);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(UNTIL);
			setState(149);
			expr();
			setState(150);
			match(DO);
			setState(151);
			expr();
			setState(152);
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

	public static class While_doContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MkshParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public While_doContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_do; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterWhile_do(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitWhile_do(this);
		}
	}

	public final While_doContext while_do() throws RecognitionException {
		While_doContext _localctx = new While_doContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_while_do);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(WHILE);
			setState(155);
			expr();
			setState(156);
			match(DO);
			setState(157);
			expr();
			setState(158);
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

	public static class FunctionContext extends ParserRuleContext {
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
		public List<TerminalNode> P_COMMA() { return getTokens(MkshParser.P_COMMA); }
		public TerminalNode P_COMMA(int i) {
			return getToken(MkshParser.P_COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(FUNCTION);
			setState(161);
			identifier();
			setState(162);
			match(P_L_PARENTHESIS);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CASE) | (1L << ELSE) | (1L << FUNCTION) | (1L << THEN) | (1L << DO) | (1L << ESAC) | (1L << IF) | (1L << TIME) | (1L << DONE) | (1L << FI) | (1L << IN) | (1L << UNTIL) | (1L << ELIF) | (1L << FOR) | (1L << SELECT) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << EVAL) | (1L << EXEC) | (1L << EXIT) | (1L << EXPORT) | (1L << READONLY) | (1L << RETURN) | (1L << SET) | (1L << SHIFT) | (1L << TIMES) | (1L << TRAP) | (1L << UNSET) | (1L << BUILTIN) | (1L << GLOBAL) | (1L << TYPESET) | (1L << WAIT) | (1L << ALIAS) | (1L << BG) | (1L << BIND) | (1L << CAT) | (1L << CD) | (1L << COMMAND) | (1L << ECHO) | (1L << FALSE) | (1L << TRUE) | (1L << FC) | (1L << FG) | (1L << GETOPTS) | (1L << JOBS) | (1L << KILL) | (1L << LET) | (1L << MKNOD) | (1L << PRINT) | (1L << PWD) | (1L << READ) | (1L << REALPATH) | (1L << RENAME) | (1L << SLEEP) | (1L << SUSPEND) | (1L << TEST) | (1L << ULIMIT) | (1L << UMASK) | (1L << UNALIAS) | (1L << WHENCE))) != 0) || _la==IDENTIFIER) {
				{
				setState(163);
				identifier();
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==P_COMMA) {
					{
					{
					setState(164);
					match(P_COMMA);
					setState(165);
					identifier();
					}
					}
					setState(170);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(173);
			match(P_R_PARENTHESIS);
			setState(174);
			match(P_L_BRACKET);
			setState(175);
			expr();
			setState(176);
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
		enterRule(_localctx, 32, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
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
		enterRule(_localctx, 34, RULE_arit);
		try {
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(LET);
				setState(181);
				a_expr(0);
				}
				break;
			case ARIT_OPERATOR_L:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(ARIT_OPERATOR_L);
				setState(183);
				a_expr(0);
				setState(184);
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
		enterRule(_localctx, 36, RULE_a_immediate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
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
		enterRule(_localctx, 38, RULE_a_operand);
		try {
			setState(192);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARIT_ONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
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
				setState(191);
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_a_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(195);
			a_operand();
			}
			_ctx.stop = _input.LT(-1);
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(203);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new A_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_a_expr);
						setState(197);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(198);
						a_operator_binary();
						setState(199);
						a_expr(3);
						}
						break;
					case 2:
						{
						_localctx = new A_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_a_expr);
						setState(201);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(202);
						a_operator_binary();
						}
						break;
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
		enterRule(_localctx, 42, RULE_a_operator_binary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
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
		enterRule(_localctx, 44, RULE_a_operator_unary);
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
		enterRule(_localctx, 46, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			identifier();
			setState(213);
			match(ARIT_A);
			setState(214);
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

	public static class ExecContext extends ParserRuleContext {
		public TerminalNode EXEC() { return getToken(MkshParser.EXEC, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> BACK_TICK() { return getTokens(MkshParser.BACK_TICK); }
		public TerminalNode BACK_TICK(int i) {
			return getToken(MkshParser.BACK_TICK, i);
		}
		public ExecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterExec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitExec(this);
		}
	}

	public final ExecContext exec() throws RecognitionException {
		ExecContext _localctx = new ExecContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_exec);
		try {
			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				match(EXEC);
				setState(217);
				expr();
				}
				break;
			case BACK_TICK:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(BACK_TICK);
				setState(219);
				expr();
				setState(220);
				match(BACK_TICK);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3f\u00e3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\5\4=\n\4\3\4\3\4\3\4\3\4\6"+
		"\4C\n\4\r\4\16\4D\5\4G\n\4\3\4\5\4J\n\4\3\5\3\5\5\5N\n\5\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\b\5\bW\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13c\n\13\3\f\3\f\3\f\3\f\7\fi\n\f\f\f\16\fl\13\f\5\fn\n\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r~\n\r\f\r\16\r\u0081"+
		"\13\r\3\r\3\r\5\r\u0085\n\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u008d\n\16"+
		"\f\16\16\16\u0090\13\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\7"+
		"\21\u00a9\n\21\f\21\16\21\u00ac\13\21\5\21\u00ae\n\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00bd\n\23\3\24"+
		"\3\24\3\25\3\25\5\25\u00c3\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\7\26\u00ce\n\26\f\26\16\26\u00d1\13\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00e1\n\32\3\32\2\3"+
		"*\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\6\4\2VVe"+
		"e\3\2\3\22\3\2\23?\4\2IUXX\2\u00e3\2\64\3\2\2\2\4\66\3\2\2\2\6F\3\2\2"+
		"\2\bM\3\2\2\2\nO\3\2\2\2\fQ\3\2\2\2\16V\3\2\2\2\20X\3\2\2\2\22Z\3\2\2"+
		"\2\24b\3\2\2\2\26d\3\2\2\2\30t\3\2\2\2\32\u0088\3\2\2\2\34\u0096\3\2\2"+
		"\2\36\u009c\3\2\2\2 \u00a2\3\2\2\2\"\u00b4\3\2\2\2$\u00bc\3\2\2\2&\u00be"+
		"\3\2\2\2(\u00c2\3\2\2\2*\u00c4\3\2\2\2,\u00d2\3\2\2\2.\u00d4\3\2\2\2\60"+
		"\u00d6\3\2\2\2\62\u00e0\3\2\2\2\64\65\5\4\3\2\65\3\3\2\2\2\66\67\5\6\4"+
		"\2\678\7\2\2\38\5\3\2\2\29=\5$\23\2:=\5\24\13\2;=\5\b\5\2<9\3\2\2\2<:"+
		"\3\2\2\2<;\3\2\2\2=>\3\2\2\2>?\5\n\6\2?G\3\2\2\2@G\5\f\7\2AC\7e\2\2BA"+
		"\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2F<\3\2\2\2F@\3\2\2\2F"+
		"B\3\2\2\2GI\3\2\2\2HJ\5\6\4\2IH\3\2\2\2IJ\3\2\2\2J\7\3\2\2\2KN\5\20\t"+
		"\2LN\5\22\n\2MK\3\2\2\2ML\3\2\2\2N\t\3\2\2\2OP\t\2\2\2P\13\3\2\2\2QR\7"+
		"f\2\2R\r\3\2\2\2SW\7c\2\2TW\5\20\t\2UW\5\22\n\2VS\3\2\2\2VT\3\2\2\2VU"+
		"\3\2\2\2W\17\3\2\2\2XY\t\3\2\2Y\21\3\2\2\2Z[\t\4\2\2[\23\3\2\2\2\\c\5"+
		"\26\f\2]c\5\30\r\2^c\5\32\16\2_c\5\34\17\2`c\5\36\20\2ac\5 \21\2b\\\3"+
		"\2\2\2b]\3\2\2\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2\2ba\3\2\2\2c\25\3\2\2\2d"+
		"e\7\20\2\2em\5\16\b\2fj\7\r\2\2gi\5\"\22\2hg\3\2\2\2il\3\2\2\2jh\3\2\2"+
		"\2jk\3\2\2\2kn\3\2\2\2lj\3\2\2\2mf\3\2\2\2mn\3\2\2\2no\3\2\2\2op\5\n\6"+
		"\2pq\7\7\2\2qr\5\6\4\2rs\7\13\2\2s\27\3\2\2\2tu\7\t\2\2uv\5\6\4\2vw\7"+
		"\6\2\2w\177\5\6\4\2xy\7\17\2\2yz\5\6\4\2z{\7\6\2\2{|\5\6\4\2|~\3\2\2\2"+
		"}x\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0084\3"+
		"\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7\4\2\2\u0083\u0085\5\6\4\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\f"+
		"\2\2\u0087\31\3\2\2\2\u0088\u0089\7\21\2\2\u0089\u008a\5\16\b\2\u008a"+
		"\u008e\7\r\2\2\u008b\u008d\5\"\22\2\u008c\u008b\3\2\2\2\u008d\u0090\3"+
		"\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0091\u0092\5\n\6\2\u0092\u0093\7\7\2\2\u0093\u0094\5\6"+
		"\4\2\u0094\u0095\7\13\2\2\u0095\33\3\2\2\2\u0096\u0097\7\16\2\2\u0097"+
		"\u0098\5\6\4\2\u0098\u0099\7\7\2\2\u0099\u009a\5\6\4\2\u009a\u009b\7\13"+
		"\2\2\u009b\35\3\2\2\2\u009c\u009d\7\22\2\2\u009d\u009e\5\6\4\2\u009e\u009f"+
		"\7\7\2\2\u009f\u00a0\5\6\4\2\u00a0\u00a1\7\13\2\2\u00a1\37\3\2\2\2\u00a2"+
		"\u00a3\7\5\2\2\u00a3\u00a4\5\16\b\2\u00a4\u00ad\7[\2\2\u00a5\u00aa\5\16"+
		"\b\2\u00a6\u00a7\7X\2\2\u00a7\u00a9\5\16\b\2\u00a8\u00a6\3\2\2\2\u00a9"+
		"\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ae\3\2"+
		"\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00a5\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\7\\\2\2\u00b0\u00b1\7Y\2\2\u00b1\u00b2\5\6"+
		"\4\2\u00b2\u00b3\7Z\2\2\u00b3!\3\2\2\2\u00b4\u00b5\7b\2\2\u00b5#\3\2\2"+
		"\2\u00b6\u00b7\7\62\2\2\u00b7\u00bd\5*\26\2\u00b8\u00b9\7D\2\2\u00b9\u00ba"+
		"\5*\26\2\u00ba\u00bb\7E\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bc"+
		"\u00b8\3\2\2\2\u00bd%\3\2\2\2\u00be\u00bf\7H\2\2\u00bf\'\3\2\2\2\u00c0"+
		"\u00c3\5&\24\2\u00c1\u00c3\5\16\b\2\u00c2\u00c0\3\2\2\2\u00c2\u00c1\3"+
		"\2\2\2\u00c3)\3\2\2\2\u00c4\u00c5\b\26\1\2\u00c5\u00c6\5(\25\2\u00c6\u00cf"+
		"\3\2\2\2\u00c7\u00c8\f\4\2\2\u00c8\u00c9\5,\27\2\u00c9\u00ca\5*\26\5\u00ca"+
		"\u00ce\3\2\2\2\u00cb\u00cc\f\5\2\2\u00cc\u00ce\5,\27\2\u00cd\u00c7\3\2"+
		"\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0+\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d3\t\5\2\2"+
		"\u00d3-\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5/\3\2\2\2\u00d6\u00d7\5\16\b"+
		"\2\u00d7\u00d8\7I\2\2\u00d8\u00d9\5\"\22\2\u00d9\61\3\2\2\2\u00da\u00db"+
		"\7\26\2\2\u00db\u00e1\5\6\4\2\u00dc\u00dd\7]\2\2\u00dd\u00de\5\6\4\2\u00de"+
		"\u00df\7]\2\2\u00df\u00e1\3\2\2\2\u00e0\u00da\3\2\2\2\u00e0\u00dc\3\2"+
		"\2\2\u00e1\63\3\2\2\2\25<DFIMVbjm\177\u0084\u008e\u00aa\u00ad\u00bc\u00c2"+
		"\u00cd\u00cf\u00e0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}