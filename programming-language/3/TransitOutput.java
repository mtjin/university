//201404377_진승언
package lexer;

import java.util.Optional;

class TransitionOutput {
	private final State nextState;
	private final Optional<Token> token;

	static TransitionOutput GOTO_START = new TransitionOutput(State.START);         //내부에 이런 상태들이 생성되있음
	static TransitionOutput GOTO_ACCEPT_ID = new TransitionOutput(State.ACCEPT_ID);
	static TransitionOutput GOTO_ACCEPT_INT = new TransitionOutput(State.ACCEPT_INT);
	static TransitionOutput GOTO_SIGN = new TransitionOutput(State.SIGN);
	static TransitionOutput GOTO_FAILED = new TransitionOutput(State.FAILED);
	static TransitionOutput GOTO_EOS = new TransitionOutput(State.EOS);
	
	//추가  #T #F일 경우를 처리할 상태
	static TransitionOutput GOTO_SHARP = new TransitionOutput(State.SHARP);
	
	
	static TransitionOutput GOTO_MATCHED(TokenType type, String lexime) {           
		return new TransitionOutput(State.MATCHED, new Token(type, lexime));      //MATCHED가 되면 토큰타입이 확정된거인듯
	}
	static TransitionOutput GOTO_MATCHED(Token token) {
		return new TransitionOutput(State.MATCHED, token);
	}
	
	TransitionOutput(State nextState, Token token) {
		this.nextState = nextState;
		this.token = Optional.of(token);
	}
	
	TransitionOutput(State nextState) {
		this.nextState = nextState;
		this.token = Optional.empty();
	}
	
	State nextState() {
		return this.nextState;
	}
	
	Optional<Token> token() {
		return this.token;
	}
}