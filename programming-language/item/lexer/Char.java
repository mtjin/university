//201404377_진승언
package lexer;


class Char {
	private final char value;
	private final CharacterType type;

	enum CharacterType {
		LETTER, DIGIT, SPECIAL_CHAR, WS, END_OF_STREAM,  //문자의 타입들
	}          
	
	static Char of(char ch) {
		return new Char(ch, getType(ch));
	}
	
	static Char end() {
		return new Char(Character.MIN_VALUE, CharacterType.END_OF_STREAM);
	}
	
	private Char(char ch, CharacterType type) {
		this.value = ch;
		this.type = type;
	}
	
	char value() {
		return this.value;
	}
	
	CharacterType type() {
		return this.type;
	}
	
	private static CharacterType getType(char ch) {    //알파벳인경우 letter
		int code = (int)ch;
		if ( (code >= (int)'A' && code <= (int)'Z')
			|| (code >= (int)'a' && code <= (int)'z')) {
			return CharacterType.LETTER;
		}
		
		if ( Character.isDigit(ch) ) {    //숫자면 DIGIT
			return CharacterType.DIGIT;
		}
		
		switch ( ch ) {                             
			case '-': //추가 밑
			case '?': case '(': case '+': case '*': case '/': case '<': case '=':
			case '>': case '\'': case '#': case ')': case '`':
				return CharacterType.SPECIAL_CHAR;
			
		}
		
		if ( Character.isWhitespace(ch) ) {   //공백인 경우 WS
			return CharacterType.WS;
		}
		
		throw new IllegalArgumentException("input=" + ch);
	}
}
