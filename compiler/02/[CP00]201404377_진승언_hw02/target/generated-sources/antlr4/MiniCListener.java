// Generated from MiniC.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniCParser}.
 */
public interface MiniCListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniCParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(@NotNull MiniCParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(@NotNull MiniCParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(@NotNull MiniCParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(@NotNull MiniCParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#fun_decl}.
	 * @param ctx the parse tree
	 */
	void enterFun_decl(@NotNull MiniCParser.Fun_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#fun_decl}.
	 * @param ctx the parse tree
	 */
	void exitFun_decl(@NotNull MiniCParser.Fun_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(@NotNull MiniCParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(@NotNull MiniCParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull MiniCParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull MiniCParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(@NotNull MiniCParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(@NotNull MiniCParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpr_stmt(@NotNull MiniCParser.Expr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpr_stmt(@NotNull MiniCParser.Expr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompound_stmt(@NotNull MiniCParser.Compound_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompound_stmt(@NotNull MiniCParser.Compound_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(@NotNull MiniCParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(@NotNull MiniCParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#local_decl}.
	 * @param ctx the parse tree
	 */
	void enterLocal_decl(@NotNull MiniCParser.Local_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#local_decl}.
	 * @param ctx the parse tree
	 */
	void exitLocal_decl(@NotNull MiniCParser.Local_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#type_spec}.
	 * @param ctx the parse tree
	 */
	void enterType_spec(@NotNull MiniCParser.Type_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#type_spec}.
	 * @param ctx the parse tree
	 */
	void exitType_spec(@NotNull MiniCParser.Type_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(@NotNull MiniCParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(@NotNull MiniCParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull MiniCParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull MiniCParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(@NotNull MiniCParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(@NotNull MiniCParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull MiniCParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull MiniCParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniCParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(@NotNull MiniCParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniCParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(@NotNull MiniCParser.Return_stmtContext ctx);
}