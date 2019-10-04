// Generated from MiniC.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__18=1, T__17=2, T__16=3, T__15=4, T__14=5, T__13=6, T__12=7, T__11=8, 
		T__10=9, T__9=10, T__8=11, T__7=12, T__6=13, T__5=14, T__4=15, T__3=16, 
		T__2=17, T__1=18, T__0=19, VOID=20, INT=21, WHILE=22, IF=23, ELSE=24, 
		RETURN=25, OR=26, AND=27, LE=28, GE=29, EQ=30, NE=31, IDENT=32, LITERAL=33, 
		DecimalConstant=34, OctalConstant=35, HexadecimalConstant=36, WS=37;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'{'", "'['", "';'", "'<'", "'++'", "'--'", "'}'", 
		"']'", "'='", "'>'", "'!'", "'%'", "'('", "')'", "'*'", "'+'", "','", 
		"'-'", "'void'", "'int'", "'while'", "'if'", "'else'", "'return'", "'or'", 
		"'and'", "'<='", "'>='", "'=='", "'!='", "IDENT", "LITERAL", "DecimalConstant", 
		"OctalConstant", "HexadecimalConstant", "WS"
	};
	public static final int
		RULE_program = 0, RULE_decl = 1, RULE_var_decl = 2, RULE_type_spec = 3, 
		RULE_fun_decl = 4, RULE_params = 5, RULE_param = 6, RULE_stmt = 7, RULE_expr_stmt = 8, 
		RULE_while_stmt = 9, RULE_compound_stmt = 10, RULE_local_decl = 11, RULE_if_stmt = 12, 
		RULE_return_stmt = 13, RULE_expr = 14, RULE_args = 15;
	public static final String[] ruleNames = {
		"program", "decl", "var_decl", "type_spec", "fun_decl", "params", "param", 
		"stmt", "expr_stmt", "while_stmt", "compound_stmt", "local_decl", "if_stmt", 
		"return_stmt", "expr", "args"
	};

	@Override
	public String getGrammarFileName() { return "MiniC.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32); decl();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VOID || _la==INT );
			System.out.println("201404377 Rule 0");
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

	public static class DeclContext extends ParserRuleContext {
		public Fun_declContext fun_decl() {
			return getRuleContext(Fun_declContext.class,0);
		}
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(45);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39); var_decl();
				System.out.println("201404377 Rule 1-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42); fun_decl();
				System.out.println("201404377 Rule 1-2");
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

	public static class Var_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitVar_decl(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_var_decl);
		try {
			setState(67);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47); type_spec();
				setState(48); match(IDENT);
				setState(49); match(T__15);
				System.out.println("201404377 Rule 2-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52); type_spec();
				setState(53); match(IDENT);
				setState(54); match(T__9);
				setState(55); match(LITERAL);
				setState(56); match(T__15);
				System.out.println("201404377 Rule 2-2");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(59); type_spec();
				setState(60); match(IDENT);
				setState(61); match(T__16);
				setState(62); match(LITERAL);
				setState(63); match(T__10);
				setState(64); match(T__15);
				System.out.println("201404377 Rule 2-3");
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

	public static class Type_specContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public TerminalNode INT() { return getToken(MiniCParser.INT, 0); }
		public Type_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterType_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitType_spec(this);
		}
	}

	public final Type_specContext type_spec() throws RecognitionException {
		Type_specContext _localctx = new Type_specContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_spec);
		try {
			setState(73);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(69); match(VOID);
				System.out.println("201404377 Rule 3-1");
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(71); match(INT);
				System.out.println("201404377 Rule 3-2");
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

	public static class Fun_declContext extends ParserRuleContext {
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public Fun_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterFun_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitFun_decl(this);
		}
	}

	public final Fun_declContext fun_decl() throws RecognitionException {
		Fun_declContext _localctx = new Fun_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); type_spec();
			setState(76); match(IDENT);
			setState(77); match(T__5);
			setState(78); params();
			setState(79); match(T__4);
			setState(80); compound_stmt();
			System.out.println("201404377 Rule 4");
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

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			setState(96);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83); param();
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(84); match(T__1);
					setState(85); param();
					}
					}
					setState(90);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				System.out.println("201404377 Rule 5-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93); match(VOID);
				System.out.println("201404377 Rule 5-2");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				System.out.println("201404377 Rule 5-3");
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

	public static class ParamContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			setState(108);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(98); type_spec();
				setState(99); match(IDENT);
				System.out.println("201404377 Rule 6-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102); type_spec();
				setState(103); match(IDENT);
				setState(104); match(T__16);
				setState(105); match(T__10);
				System.out.println("201404377 Rule 6-2");
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

	public static class StmtContext extends ParserRuleContext {
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stmt);
		try {
			setState(125);
			switch (_input.LA(1)) {
			case T__13:
			case T__12:
			case T__7:
			case T__5:
			case T__2:
			case T__0:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(110); expr_stmt();
				System.out.println("201404377 Rule 7-1");
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(113); compound_stmt();
				System.out.println("201404377 Rule 7-2");
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(116); if_stmt();
				System.out.println("201404377 Rule 7-3");
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(119); while_stmt();
				System.out.println("201404377 Rule 7-4");
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 5);
				{
				setState(122); return_stmt();
				System.out.println("201404377 Rule 7-5");
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

	public static class Expr_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr_stmt(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); expr(0);
			setState(128); match(T__15);
			System.out.println("201404377 Rule 8");
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

	public static class While_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(MiniCParser.WHILE, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterWhile_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitWhile_stmt(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); match(WHILE);
			setState(132); match(T__5);
			setState(133); expr(0);
			setState(134); match(T__4);
			setState(135); stmt();
			System.out.println("201404377 Rule 9");
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

	public static class Compound_stmtContext extends ParserRuleContext {
		public List<Local_declContext> local_decl() {
			return getRuleContexts(Local_declContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public Local_declContext local_decl(int i) {
			return getRuleContext(Local_declContext.class,i);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterCompound_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitCompound_stmt(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_compound_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); match(T__17);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VOID || _la==INT) {
				{
				{
				setState(139); local_decl();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__13) | (1L << T__12) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << T__0) | (1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << IDENT) | (1L << LITERAL))) != 0)) {
				{
				{
				setState(145); stmt();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(151); match(T__11);
			System.out.println("201404377 Rule 10");
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

	public static class Local_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public Local_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterLocal_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitLocal_decl(this);
		}
	}

	public final Local_declContext local_decl() throws RecognitionException {
		Local_declContext _localctx = new Local_declContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_local_decl);
		try {
			setState(174);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(154); type_spec();
				setState(155); match(IDENT);
				setState(156); match(T__15);
				System.out.println("201404377 Rule 11-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159); type_spec();
				setState(160); match(IDENT);
				setState(161); match(T__9);
				setState(162); match(LITERAL);
				setState(163); match(T__15);
				System.out.println("201404377 Rule 11-2");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(166); type_spec();
				setState(167); match(IDENT);
				setState(168); match(T__16);
				setState(169); match(LITERAL);
				setState(170); match(T__10);
				setState(171); match(T__15);
				System.out.println("201404377 Rule 11-3");
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

	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(MiniCParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(MiniCParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitIf_stmt(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_if_stmt);
		try {
			setState(192);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(176); match(IF);
				setState(177); match(T__5);
				setState(178); expr(0);
				setState(179); match(T__4);
				setState(180); stmt();
				System.out.println("201404377 Rule 12-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183); match(IF);
				setState(184); match(T__5);
				setState(185); expr(0);
				setState(186); match(T__4);
				setState(187); stmt();
				setState(188); match(ELSE);
				setState(189); stmt();
				System.out.println("201404377 Rule 12-2");
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

	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MiniCParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitReturn_stmt(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_return_stmt);
		try {
			setState(202);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194); match(RETURN);
				setState(195); match(T__15);
				System.out.println("201404377 Rule 13-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197); match(RETURN);
				setState(198); expr(0);
				setState(199); match(T__15);
				System.out.println("201404377 Rule 13-2");
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

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode GE() { return getToken(MiniCParser.GE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LE() { return getToken(MiniCParser.LE, 0); }
		public TerminalNode AND() { return getToken(MiniCParser.AND, 0); }
		public TerminalNode EQ() { return getToken(MiniCParser.EQ, 0); }
		public TerminalNode NE() { return getToken(MiniCParser.NE, 0); }
		public TerminalNode OR() { return getToken(MiniCParser.OR, 0); }
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(205); match(T__0);
				setState(206); expr(20);
				System.out.println("201404377 Rule 14-6");
				}
				break;
			case 2:
				{
				setState(209); match(T__2);
				setState(210); expr(19);
				System.out.println("201404377 Rule 14-7");
				}
				break;
			case 3:
				{
				setState(213); match(T__12);
				setState(214); expr(18);
				System.out.println("201404377 Rule 14-8");
				}
				break;
			case 4:
				{
				setState(217); match(T__13);
				setState(218); expr(17);
				System.out.println("201404377 Rule 14-9");
				}
				break;
			case 5:
				{
				setState(221); match(T__7);
				setState(222); expr(5);
				System.out.println("201404377 Rule 14-21");
				}
				break;
			case 6:
				{
				setState(225); match(IDENT);
				setState(226); match(T__9);
				setState(227); expr(2);
				System.out.println("201404377 Rule 14-24");
				}
				break;
			case 7:
				{
				setState(230); match(LITERAL);
				System.out.println("201404377 Rule 14-1");
				}
				break;
			case 8:
				{
				setState(232); match(T__5);
				setState(233); expr(0);
				setState(234); match(T__4);
				System.out.println("201404377 Rule 14-2");
				}
				break;
			case 9:
				{
				setState(237); match(IDENT);
				System.out.println("201404377 Rule 14-3");
				}
				break;
			case 10:
				{
				setState(239); match(IDENT);
				setState(240); match(T__16);
				setState(241); expr(0);
				setState(242); match(T__10);
				System.out.println("201404377 Rule 14-4");
				}
				break;
			case 11:
				{
				setState(245); match(IDENT);
				setState(246); match(T__5);
				setState(247); args();
				setState(248); match(T__4);
				System.out.println("201404377 Rule 14-5");
				}
				break;
			case 12:
				{
				setState(251); match(IDENT);
				setState(252); match(T__16);
				setState(253); expr(0);
				setState(254); match(T__10);
				setState(255); match(T__9);
				setState(256); expr(0);
				System.out.println("201404377 Rule 14-25");
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(328);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(326);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(261);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(262); match(T__3);
						setState(263); expr(17);
						System.out.println("201404377 Rule 14-10");
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(266);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(267); match(T__18);
						setState(268); expr(16);
						System.out.println("201404377 Rule 14-11");
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(271);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(272); match(T__6);
						setState(273); expr(15);
						System.out.println("201404377 Rule 14-12");
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(276);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(277); match(T__2);
						setState(278); expr(14);
						System.out.println("201404377 Rule 14-13");
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(281);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(282); match(T__0);
						setState(283); expr(13);
						System.out.println("201404377 Rule 14-14");
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(286);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(287); match(EQ);
						setState(288); expr(12);
						System.out.println("201404377 Rule 14-15");
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(291);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(292); match(NE);
						setState(293); expr(11);
						System.out.println("201404377 Rule 14-16");
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(296);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(297); match(LE);
						setState(298); expr(10);
						System.out.println("201404377 Rule 14-17");
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(301);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(302); match(T__14);
						setState(303); expr(9);
						System.out.println("201404377 Rule 14-18");
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(306);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(307); match(GE);
						setState(308); expr(8);
						System.out.println("201404377 Rule 14-19");
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(311);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(312); match(T__8);
						setState(313); expr(7);
						System.out.println("201404377 Rule 14-20");
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(316);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(317); match(AND);
						setState(318); expr(5);
						System.out.println("201404377 Rule 14-22");
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(321);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(322); match(OR);
						setState(323); expr(4);
						System.out.println("201404377 Rule 14-23");
						}
						break;
					}
					} 
				}
				setState(330);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_args);
		int _la;
		try {
			setState(342);
			switch (_input.LA(1)) {
			case T__13:
			case T__12:
			case T__7:
			case T__5:
			case T__2:
			case T__0:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(331); expr(0);
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(332); match(T__1);
					setState(333); expr(0);
					}
					}
					setState(338);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				System.out.println("201404377 Rule 15-1");
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				System.out.println("201404377 Rule 15-2");
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
		case 14: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 16);
		case 1: return precpred(_ctx, 15);
		case 2: return precpred(_ctx, 14);
		case 3: return precpred(_ctx, 13);
		case 4: return precpred(_ctx, 12);
		case 5: return precpred(_ctx, 11);
		case 6: return precpred(_ctx, 10);
		case 7: return precpred(_ctx, 9);
		case 8: return precpred(_ctx, 8);
		case 9: return precpred(_ctx, 7);
		case 10: return precpred(_ctx, 6);
		case 11: return precpred(_ctx, 4);
		case 12: return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u015b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\6\2$\n"+
		"\2\r\2\16\2%\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\60\n\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"F\n\4\3\5\3\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\7\3\7\3\7\3\7\3\7\5\7c\n\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bo\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\7\f\u008f\n\f\f\f\16\f\u0092\13\f\3\f"+
		"\7\f\u0095\n\f\f\f\16\f\u0098\13\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b1\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\5\16\u00c3\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u00cd\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u0106\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u0149\n\20\f\20\16\20\u014c\13\20\3\21\3\21\3\21\7\21\u0151\n\21\f\21"+
		"\16\21\u0154\13\21\3\21\3\21\3\21\5\21\u0159\n\21\3\21\2\3\36\22\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \2\2\u0177\2#\3\2\2\2\4/\3\2\2\2\6E"+
		"\3\2\2\2\bK\3\2\2\2\nM\3\2\2\2\fb\3\2\2\2\16n\3\2\2\2\20\177\3\2\2\2\22"+
		"\u0081\3\2\2\2\24\u0085\3\2\2\2\26\u008c\3\2\2\2\30\u00b0\3\2\2\2\32\u00c2"+
		"\3\2\2\2\34\u00cc\3\2\2\2\36\u0105\3\2\2\2 \u0158\3\2\2\2\"$\5\4\3\2#"+
		"\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\b\2\1\2(\3\3\2"+
		"\2\2)*\5\6\4\2*+\b\3\1\2+\60\3\2\2\2,-\5\n\6\2-.\b\3\1\2.\60\3\2\2\2/"+
		")\3\2\2\2/,\3\2\2\2\60\5\3\2\2\2\61\62\5\b\5\2\62\63\7\"\2\2\63\64\7\6"+
		"\2\2\64\65\b\4\1\2\65F\3\2\2\2\66\67\5\b\5\2\678\7\"\2\289\7\f\2\29:\7"+
		"#\2\2:;\7\6\2\2;<\b\4\1\2<F\3\2\2\2=>\5\b\5\2>?\7\"\2\2?@\7\5\2\2@A\7"+
		"#\2\2AB\7\13\2\2BC\7\6\2\2CD\b\4\1\2DF\3\2\2\2E\61\3\2\2\2E\66\3\2\2\2"+
		"E=\3\2\2\2F\7\3\2\2\2GH\7\26\2\2HL\b\5\1\2IJ\7\27\2\2JL\b\5\1\2KG\3\2"+
		"\2\2KI\3\2\2\2L\t\3\2\2\2MN\5\b\5\2NO\7\"\2\2OP\7\20\2\2PQ\5\f\7\2QR\7"+
		"\21\2\2RS\5\26\f\2ST\b\6\1\2T\13\3\2\2\2UZ\5\16\b\2VW\7\24\2\2WY\5\16"+
		"\b\2XV\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\b"+
		"\7\1\2^c\3\2\2\2_`\7\26\2\2`c\b\7\1\2ac\b\7\1\2bU\3\2\2\2b_\3\2\2\2ba"+
		"\3\2\2\2c\r\3\2\2\2de\5\b\5\2ef\7\"\2\2fg\b\b\1\2go\3\2\2\2hi\5\b\5\2"+
		"ij\7\"\2\2jk\7\5\2\2kl\7\13\2\2lm\b\b\1\2mo\3\2\2\2nd\3\2\2\2nh\3\2\2"+
		"\2o\17\3\2\2\2pq\5\22\n\2qr\b\t\1\2r\u0080\3\2\2\2st\5\26\f\2tu\b\t\1"+
		"\2u\u0080\3\2\2\2vw\5\32\16\2wx\b\t\1\2x\u0080\3\2\2\2yz\5\24\13\2z{\b"+
		"\t\1\2{\u0080\3\2\2\2|}\5\34\17\2}~\b\t\1\2~\u0080\3\2\2\2\177p\3\2\2"+
		"\2\177s\3\2\2\2\177v\3\2\2\2\177y\3\2\2\2\177|\3\2\2\2\u0080\21\3\2\2"+
		"\2\u0081\u0082\5\36\20\2\u0082\u0083\7\6\2\2\u0083\u0084\b\n\1\2\u0084"+
		"\23\3\2\2\2\u0085\u0086\7\30\2\2\u0086\u0087\7\20\2\2\u0087\u0088\5\36"+
		"\20\2\u0088\u0089\7\21\2\2\u0089\u008a\5\20\t\2\u008a\u008b\b\13\1\2\u008b"+
		"\25\3\2\2\2\u008c\u0090\7\4\2\2\u008d\u008f\5\30\r\2\u008e\u008d\3\2\2"+
		"\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0096"+
		"\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0095\5\20\t\2\u0094\u0093\3\2\2\2"+
		"\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099"+
		"\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\7\n\2\2\u009a\u009b\b\f\1\2\u009b"+
		"\27\3\2\2\2\u009c\u009d\5\b\5\2\u009d\u009e\7\"\2\2\u009e\u009f\7\6\2"+
		"\2\u009f\u00a0\b\r\1\2\u00a0\u00b1\3\2\2\2\u00a1\u00a2\5\b\5\2\u00a2\u00a3"+
		"\7\"\2\2\u00a3\u00a4\7\f\2\2\u00a4\u00a5\7#\2\2\u00a5\u00a6\7\6\2\2\u00a6"+
		"\u00a7\b\r\1\2\u00a7\u00b1\3\2\2\2\u00a8\u00a9\5\b\5\2\u00a9\u00aa\7\""+
		"\2\2\u00aa\u00ab\7\5\2\2\u00ab\u00ac\7#\2\2\u00ac\u00ad\7\13\2\2\u00ad"+
		"\u00ae\7\6\2\2\u00ae\u00af\b\r\1\2\u00af\u00b1\3\2\2\2\u00b0\u009c\3\2"+
		"\2\2\u00b0\u00a1\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b1\31\3\2\2\2\u00b2\u00b3"+
		"\7\31\2\2\u00b3\u00b4\7\20\2\2\u00b4\u00b5\5\36\20\2\u00b5\u00b6\7\21"+
		"\2\2\u00b6\u00b7\5\20\t\2\u00b7\u00b8\b\16\1\2\u00b8\u00c3\3\2\2\2\u00b9"+
		"\u00ba\7\31\2\2\u00ba\u00bb\7\20\2\2\u00bb\u00bc\5\36\20\2\u00bc\u00bd"+
		"\7\21\2\2\u00bd\u00be\5\20\t\2\u00be\u00bf\7\32\2\2\u00bf\u00c0\5\20\t"+
		"\2\u00c0\u00c1\b\16\1\2\u00c1\u00c3\3\2\2\2\u00c2\u00b2\3\2\2\2\u00c2"+
		"\u00b9\3\2\2\2\u00c3\33\3\2\2\2\u00c4\u00c5\7\33\2\2\u00c5\u00c6\7\6\2"+
		"\2\u00c6\u00cd\b\17\1\2\u00c7\u00c8\7\33\2\2\u00c8\u00c9\5\36\20\2\u00c9"+
		"\u00ca\7\6\2\2\u00ca\u00cb\b\17\1\2\u00cb\u00cd\3\2\2\2\u00cc\u00c4\3"+
		"\2\2\2\u00cc\u00c7\3\2\2\2\u00cd\35\3\2\2\2\u00ce\u00cf\b\20\1\2\u00cf"+
		"\u00d0\7\25\2\2\u00d0\u00d1\5\36\20\26\u00d1\u00d2\b\20\1\2\u00d2\u0106"+
		"\3\2\2\2\u00d3\u00d4\7\23\2\2\u00d4\u00d5\5\36\20\25\u00d5\u00d6\b\20"+
		"\1\2\u00d6\u0106\3\2\2\2\u00d7\u00d8\7\t\2\2\u00d8\u00d9\5\36\20\24\u00d9"+
		"\u00da\b\20\1\2\u00da\u0106\3\2\2\2\u00db\u00dc\7\b\2\2\u00dc\u00dd\5"+
		"\36\20\23\u00dd\u00de\b\20\1\2\u00de\u0106\3\2\2\2\u00df\u00e0\7\16\2"+
		"\2\u00e0\u00e1\5\36\20\7\u00e1\u00e2\b\20\1\2\u00e2\u0106\3\2\2\2\u00e3"+
		"\u00e4\7\"\2\2\u00e4\u00e5\7\f\2\2\u00e5\u00e6\5\36\20\4\u00e6\u00e7\b"+
		"\20\1\2\u00e7\u0106\3\2\2\2\u00e8\u00e9\7#\2\2\u00e9\u0106\b\20\1\2\u00ea"+
		"\u00eb\7\20\2\2\u00eb\u00ec\5\36\20\2\u00ec\u00ed\7\21\2\2\u00ed\u00ee"+
		"\b\20\1\2\u00ee\u0106\3\2\2\2\u00ef\u00f0\7\"\2\2\u00f0\u0106\b\20\1\2"+
		"\u00f1\u00f2\7\"\2\2\u00f2\u00f3\7\5\2\2\u00f3\u00f4\5\36\20\2\u00f4\u00f5"+
		"\7\13\2\2\u00f5\u00f6\b\20\1\2\u00f6\u0106\3\2\2\2\u00f7\u00f8\7\"\2\2"+
		"\u00f8\u00f9\7\20\2\2\u00f9\u00fa\5 \21\2\u00fa\u00fb\7\21\2\2\u00fb\u00fc"+
		"\b\20\1\2\u00fc\u0106\3\2\2\2\u00fd\u00fe\7\"\2\2\u00fe\u00ff\7\5\2\2"+
		"\u00ff\u0100\5\36\20\2\u0100\u0101\7\13\2\2\u0101\u0102\7\f\2\2\u0102"+
		"\u0103\5\36\20\2\u0103\u0104\b\20\1\2\u0104\u0106\3\2\2\2\u0105\u00ce"+
		"\3\2\2\2\u0105\u00d3\3\2\2\2\u0105\u00d7\3\2\2\2\u0105\u00db\3\2\2\2\u0105"+
		"\u00df\3\2\2\2\u0105\u00e3\3\2\2\2\u0105\u00e8\3\2\2\2\u0105\u00ea\3\2"+
		"\2\2\u0105\u00ef\3\2\2\2\u0105\u00f1\3\2\2\2\u0105\u00f7\3\2\2\2\u0105"+
		"\u00fd\3\2\2\2\u0106\u014a\3\2\2\2\u0107\u0108\f\22\2\2\u0108\u0109\7"+
		"\22\2\2\u0109\u010a\5\36\20\23\u010a\u010b\b\20\1\2\u010b\u0149\3\2\2"+
		"\2\u010c\u010d\f\21\2\2\u010d\u010e\7\3\2\2\u010e\u010f\5\36\20\22\u010f"+
		"\u0110\b\20\1\2\u0110\u0149\3\2\2\2\u0111\u0112\f\20\2\2\u0112\u0113\7"+
		"\17\2\2\u0113\u0114\5\36\20\21\u0114\u0115\b\20\1\2\u0115\u0149\3\2\2"+
		"\2\u0116\u0117\f\17\2\2\u0117\u0118\7\23\2\2\u0118\u0119\5\36\20\20\u0119"+
		"\u011a\b\20\1\2\u011a\u0149\3\2\2\2\u011b\u011c\f\16\2\2\u011c\u011d\7"+
		"\25\2\2\u011d\u011e\5\36\20\17\u011e\u011f\b\20\1\2\u011f\u0149\3\2\2"+
		"\2\u0120\u0121\f\r\2\2\u0121\u0122\7 \2\2\u0122\u0123\5\36\20\16\u0123"+
		"\u0124\b\20\1\2\u0124\u0149\3\2\2\2\u0125\u0126\f\f\2\2\u0126\u0127\7"+
		"!\2\2\u0127\u0128\5\36\20\r\u0128\u0129\b\20\1\2\u0129\u0149\3\2\2\2\u012a"+
		"\u012b\f\13\2\2\u012b\u012c\7\36\2\2\u012c\u012d\5\36\20\f\u012d\u012e"+
		"\b\20\1\2\u012e\u0149\3\2\2\2\u012f\u0130\f\n\2\2\u0130\u0131\7\7\2\2"+
		"\u0131\u0132\5\36\20\13\u0132\u0133\b\20\1\2\u0133\u0149\3\2\2\2\u0134"+
		"\u0135\f\t\2\2\u0135\u0136\7\37\2\2\u0136\u0137\5\36\20\n\u0137\u0138"+
		"\b\20\1\2\u0138\u0149\3\2\2\2\u0139\u013a\f\b\2\2\u013a\u013b\7\r\2\2"+
		"\u013b\u013c\5\36\20\t\u013c\u013d\b\20\1\2\u013d\u0149\3\2\2\2\u013e"+
		"\u013f\f\6\2\2\u013f\u0140\7\35\2\2\u0140\u0141\5\36\20\7\u0141\u0142"+
		"\b\20\1\2\u0142\u0149\3\2\2\2\u0143\u0144\f\5\2\2\u0144\u0145\7\34\2\2"+
		"\u0145\u0146\5\36\20\6\u0146\u0147\b\20\1\2\u0147\u0149\3\2\2\2\u0148"+
		"\u0107\3\2\2\2\u0148\u010c\3\2\2\2\u0148\u0111\3\2\2\2\u0148\u0116\3\2"+
		"\2\2\u0148\u011b\3\2\2\2\u0148\u0120\3\2\2\2\u0148\u0125\3\2\2\2\u0148"+
		"\u012a\3\2\2\2\u0148\u012f\3\2\2\2\u0148\u0134\3\2\2\2\u0148\u0139\3\2"+
		"\2\2\u0148\u013e\3\2\2\2\u0148\u0143\3\2\2\2\u0149\u014c\3\2\2\2\u014a"+
		"\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\37\3\2\2\2\u014c\u014a\3\2\2"+
		"\2\u014d\u0152\5\36\20\2\u014e\u014f\7\24\2\2\u014f\u0151\5\36\20\2\u0150"+
		"\u014e\3\2\2\2\u0151\u0154\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2"+
		"\2\2\u0153\u0155\3\2\2\2\u0154\u0152\3\2\2\2\u0155\u0156\b\21\1\2\u0156"+
		"\u0159\3\2\2\2\u0157\u0159\b\21\1\2\u0158\u014d\3\2\2\2\u0158\u0157\3"+
		"\2\2\2\u0159!\3\2\2\2\24%/EKZbn\177\u0090\u0096\u00b0\u00c2\u00cc\u0105"+
		"\u0148\u014a\u0152\u0158";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}