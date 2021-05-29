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
		SQ_BRACKET_CLOSE=63, PERIOD=64, COLON=65, P_SEMI=66, P_INTERO=67, P_COMMA=68, 
		P_L_BRACKET=69, P_R_BRACKET=70, P_L_PARENTHESIS=71, P_R_PARENTHESIS=72, 
		IDENTIFIER=73, WS=74, TERMINATOR=75, LINE_COMMENT=76, STRING=77, L_SHIFT=78, 
		R_SHIFT=79, GT=80, LT=81;
	public static final int
		RULE_start = 0, RULE_keyword = 1, RULE_expr = 2, RULE_instruction = 3, 
		RULE_execution_control = 4, RULE_for_do_done = 5, RULE_if_then_else = 6, 
		RULE_select_in = 7, RULE_until_do = 8, RULE_while_do = 9, RULE_function = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "keyword", "expr", "instruction", "execution_control", "for_do_done", 
			"if_then_else", "select_in", "until_do", "while_do", "function"
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
			"'.'", "':'", "';'", "'?'", "','", "'{'", "'}'", "'('", "')'", null, 
			null, null, null, null, "'<<'", "'>>'", "'>'", "'<'"
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
			"COLON", "P_SEMI", "P_INTERO", "P_COMMA", "P_L_BRACKET", "P_R_BRACKET", 
			"P_L_PARENTHESIS", "P_R_PARENTHESIS", "IDENTIFIER", "WS", "TERMINATOR", 
			"LINE_COMMENT", "STRING", "L_SHIFT", "R_SHIFT", "GT", "LT"
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MkshParser.EOF, 0); }
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
			setState(22);
			expr();
			setState(23);
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

	public static class KeywordContext extends ParserRuleContext {
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
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MkshParserListener ) ((MkshParserListener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
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

	public static class ExprContext extends ParserRuleContext {
		public Execution_controlContext execution_control() {
			return getRuleContext(Execution_controlContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
			case IF:
			case UNTIL:
			case FOR:
			case SELECT:
			case WHILE:
				{
				setState(27);
				execution_control();
				}
				break;
			case TRUE:
				{
				setState(28);
				instruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNCTION) | (1L << IF) | (1L << UNTIL) | (1L << FOR) | (1L << SELECT) | (1L << WHILE) | (1L << TRUE))) != 0)) {
				{
				setState(31);
				expr();
				}
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
		public TerminalNode TRUE() { return getToken(MkshParser.TRUE, 0); }
		public TerminalNode P_SEMI() { return getToken(MkshParser.P_SEMI, 0); }
		public TerminalNode TERMINATOR() { return getToken(MkshParser.TERMINATOR, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(TRUE);
			setState(36);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(35);
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
		enterRule(_localctx, 8, RULE_execution_control);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				for_do_done();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				if_then_else();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				select_in();
				}
				break;
			case UNTIL:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				until_do();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				while_do();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 6);
				{
				setState(43);
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
		public TerminalNode IDENTIFIER() { return getToken(MkshParser.IDENTIFIER, 0); }
		public TerminalNode P_SEMI() { return getToken(MkshParser.P_SEMI, 0); }
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public List<TerminalNode> STRING() { return getTokens(MkshParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MkshParser.STRING, i);
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
		enterRule(_localctx, 10, RULE_for_do_done);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(FOR);
			setState(47);
			match(IDENTIFIER);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(48);
				match(IN);
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STRING) {
					{
					{
					setState(49);
					match(STRING);
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(57);
			match(P_SEMI);
			setState(58);
			match(DO);
			setState(59);
			expr();
			setState(60);
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
		public List<TerminalNode> P_SEMI() { return getTokens(MkshParser.P_SEMI); }
		public TerminalNode P_SEMI(int i) {
			return getToken(MkshParser.P_SEMI, i);
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
		enterRule(_localctx, 12, RULE_if_then_else);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(IF);
			setState(63);
			expr();
			setState(64);
			match(P_SEMI);
			setState(65);
			match(THEN);
			setState(66);
			expr();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(67);
				match(ELIF);
				setState(68);
				expr();
				setState(69);
				match(P_SEMI);
				setState(70);
				match(THEN);
				setState(71);
				expr();
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(78);
				match(ELSE);
				setState(79);
				expr();
				}
			}

			setState(82);
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
		public TerminalNode IDENTIFIER() { return getToken(MkshParser.IDENTIFIER, 0); }
		public TerminalNode P_SEMI() { return getToken(MkshParser.P_SEMI, 0); }
		public TerminalNode DO() { return getToken(MkshParser.DO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DONE() { return getToken(MkshParser.DONE, 0); }
		public TerminalNode IN() { return getToken(MkshParser.IN, 0); }
		public List<TerminalNode> STRING() { return getTokens(MkshParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MkshParser.STRING, i);
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
		enterRule(_localctx, 14, RULE_select_in);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(SELECT);
			setState(85);
			match(IDENTIFIER);
			{
			setState(86);
			match(IN);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(87);
				match(STRING);
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(93);
			match(P_SEMI);
			setState(94);
			match(DO);
			setState(95);
			expr();
			setState(96);
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
		public TerminalNode P_SEMI() { return getToken(MkshParser.P_SEMI, 0); }
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
		enterRule(_localctx, 16, RULE_until_do);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(UNTIL);
			setState(99);
			expr();
			setState(100);
			match(P_SEMI);
			setState(101);
			match(DO);
			setState(102);
			expr();
			setState(103);
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
		public TerminalNode P_SEMI() { return getToken(MkshParser.P_SEMI, 0); }
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
		enterRule(_localctx, 18, RULE_while_do);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(WHILE);
			setState(106);
			expr();
			setState(107);
			match(P_SEMI);
			setState(108);
			match(DO);
			setState(109);
			expr();
			setState(110);
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
		public List<TerminalNode> IDENTIFIER() { return getTokens(MkshParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MkshParser.IDENTIFIER, i);
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
		enterRule(_localctx, 20, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(FUNCTION);
			setState(113);
			match(IDENTIFIER);
			setState(114);
			match(P_L_PARENTHESIS);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(115);
				match(IDENTIFIER);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==P_COMMA) {
					{
					{
					setState(116);
					match(P_COMMA);
					setState(117);
					match(IDENTIFIER);
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(125);
			match(P_R_PARENTHESIS);
			setState(126);
			match(P_L_BRACKET);
			setState(127);
			expr();
			setState(128);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3S\u0085\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\2\3\3\3\3\3\4\3\4\5\4 \n\4\3\4\5\4#\n\4\3\5\3\5"+
		"\5\5\'\n\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6/\n\6\3\7\3\7\3\7\3\7\7\7\65\n\7"+
		"\f\7\16\78\13\7\5\7:\n\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\bL\n\b\f\b\16\bO\13\b\3\b\3\b\5\bS\n\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\7\t[\n\t\f\t\16\t^\13\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\7\fy\n\f\f\f\16\f|\13\f\5\f~\n\f\3\f\3\f\3\f\3\f\3\f\3\f\2\2\r"+
		"\2\4\6\b\n\f\16\20\22\24\26\2\4\3\2\3\22\4\2DDMM\2\u0088\2\30\3\2\2\2"+
		"\4\33\3\2\2\2\6\37\3\2\2\2\b$\3\2\2\2\n.\3\2\2\2\f\60\3\2\2\2\16@\3\2"+
		"\2\2\20V\3\2\2\2\22d\3\2\2\2\24k\3\2\2\2\26r\3\2\2\2\30\31\5\6\4\2\31"+
		"\32\7\2\2\3\32\3\3\2\2\2\33\34\t\2\2\2\34\5\3\2\2\2\35 \5\n\6\2\36 \5"+
		"\b\5\2\37\35\3\2\2\2\37\36\3\2\2\2 \"\3\2\2\2!#\5\6\4\2\"!\3\2\2\2\"#"+
		"\3\2\2\2#\7\3\2\2\2$&\7,\2\2%\'\t\3\2\2&%\3\2\2\2&\'\3\2\2\2\'\t\3\2\2"+
		"\2(/\5\f\7\2)/\5\16\b\2*/\5\20\t\2+/\5\22\n\2,/\5\24\13\2-/\5\26\f\2."+
		"(\3\2\2\2.)\3\2\2\2.*\3\2\2\2.+\3\2\2\2.,\3\2\2\2.-\3\2\2\2/\13\3\2\2"+
		"\2\60\61\7\20\2\2\619\7K\2\2\62\66\7\r\2\2\63\65\7O\2\2\64\63\3\2\2\2"+
		"\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3\2\2\28\66\3\2\2\29\62\3"+
		"\2\2\29:\3\2\2\2:;\3\2\2\2;<\7D\2\2<=\7\7\2\2=>\5\6\4\2>?\7\13\2\2?\r"+
		"\3\2\2\2@A\7\t\2\2AB\5\6\4\2BC\7D\2\2CD\7\6\2\2DM\5\6\4\2EF\7\17\2\2F"+
		"G\5\6\4\2GH\7D\2\2HI\7\6\2\2IJ\5\6\4\2JL\3\2\2\2KE\3\2\2\2LO\3\2\2\2M"+
		"K\3\2\2\2MN\3\2\2\2NR\3\2\2\2OM\3\2\2\2PQ\7\4\2\2QS\5\6\4\2RP\3\2\2\2"+
		"RS\3\2\2\2ST\3\2\2\2TU\7\f\2\2U\17\3\2\2\2VW\7\21\2\2WX\7K\2\2X\\\7\r"+
		"\2\2Y[\7O\2\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3"+
		"\2\2\2_`\7D\2\2`a\7\7\2\2ab\5\6\4\2bc\7\13\2\2c\21\3\2\2\2de\7\16\2\2"+
		"ef\5\6\4\2fg\7D\2\2gh\7\7\2\2hi\5\6\4\2ij\7\13\2\2j\23\3\2\2\2kl\7\22"+
		"\2\2lm\5\6\4\2mn\7D\2\2no\7\7\2\2op\5\6\4\2pq\7\13\2\2q\25\3\2\2\2rs\7"+
		"\5\2\2st\7K\2\2t}\7I\2\2uz\7K\2\2vw\7F\2\2wy\7K\2\2xv\3\2\2\2y|\3\2\2"+
		"\2zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|z\3\2\2\2}u\3\2\2\2}~\3\2\2\2~\177\3"+
		"\2\2\2\177\u0080\7J\2\2\u0080\u0081\7G\2\2\u0081\u0082\5\6\4\2\u0082\u0083"+
		"\7H\2\2\u0083\27\3\2\2\2\r\37\"&.\669MR\\z}";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}