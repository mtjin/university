//201404377_진승언
package lexer;

//특수문자 나오게할려면 ofName 부등호나오게할려면 formSpecial 써야함
public enum TokenType {
	INT,
	ID, QUESTION,
	TRUE, FALSE, NOT,
	PLUS, MINUS, TIMES, DIV,
	LT, GT, EQ, APOSTROPHE,
	L_PAREN, R_PAREN,
	DEFINE, LAMBDA, COND, QUOTE,
	CAR, CDR, CONS,
	ATOM_Q, NULL_Q, EQ_Q;
	
	static TokenType fromSpecialCharactor(char ch) {   
		switch ( ch ) { 
		case '(':
			return L_PAREN;
		case ')':
			return R_PAREN;
		case '+':
			return PLUS;
		case '-':
			return MINUS;
		case '*':
			return TIMES;
		case '/':
			return DIV;
		case '<':
			return LT;
		case '=':
			return EQ;
		case '>':
			return GT;
		case '\'':
			return APOSTROPHE;
		case '`':
			return QUOTE;
	
		default:    
			throw new IllegalArgumentException("unregistered char: " + ch);  
		}
	}
}
