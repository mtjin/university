//201404377_진승언
package lexer;
import static lexer.TokenType.INT;
import static lexer.TransitionOutput.GOTO_ACCEPT_ID;
import static lexer.TransitionOutput.GOTO_ACCEPT_INT;
import static lexer.TransitionOutput.GOTO_EOS;
import static lexer.TransitionOutput.GOTO_FAILED;
import static lexer.TransitionOutput.GOTO_SHARP;
import static lexer.TransitionOutput.GOTO_START;
import static lexer.TransitionOutput.GOTO_SIGN;
import static lexer.TransitionOutput.GOTO_MATCHED;

enum State {
	START {   //처음받는 부분
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch ( ch.type() ) {
				case LETTER:
					context.append(v);
					return GOTO_ACCEPT_ID;
				case DIGIT:
					context.append(v);
					return GOTO_ACCEPT_INT;
				case SPECIAL_CHAR:
					 if(v == 35) {             //시작부터 #이 올경우 SHARP상태로 보내버림
						 context.append(v);
						 return GOTO_SHARP;
					 }
					 else{                    // 그 외의 특수문자들
						 context.append(v);
						 return GOTO_SIGN; 
					 }
				case WS:	
					return GOTO_START;
				case END_OF_STREAM:
					return GOTO_EOS;
				default:
					throw new AssertionError();
			}
		}
	},
	ACCEPT_ID {     //알파벳처리
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch ( ch.type() ) {
				case LETTER:
				case DIGIT:
					context.append(v);
					return GOTO_ACCEPT_ID;
				case SPECIAL_CHAR:
					if(v==63) {      // ?인 경우
						Character ch2 = new Character(v);
						String temp2 = context.getLexime();
						String b = temp2.concat(ch2.toString());
						return GOTO_MATCHED(Token.ofName(b).type(), b);  //ofName으로 의문문인지 keyword인지 알아냄
					}
					return GOTO_FAILED;
				case WS:
				case END_OF_STREAM:
					 String temp = context.getLexime();
		               return GOTO_MATCHED(Token.ofName(temp).type(), temp); //ofName으로 타입 ID냐 키워드냐 알아냄
				default:
					throw new AssertionError();
			}
		}
	},
	ACCEPT_INT {     //숫자처리
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			switch ( ch.type() ) {
				case LETTER:
					return GOTO_FAILED;
				case DIGIT:
					context.append(ch.value());
					return GOTO_ACCEPT_INT;
				case SPECIAL_CHAR:
					return GOTO_FAILED; 
				case WS:
				case END_OF_STREAM:
					return GOTO_MATCHED(INT, context.getLexime());     //타입완성본 INT형타입으로 반환
				default:
					throw new AssertionError();
			}
		}
	},
	SIGN {                     // 특수문자처리
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();  //다음 문자하나 읽어옴
			char v = ch.value();
			switch ( ch.type() ) {
				case LETTER:         //특수문자다음에 문자가 오면 fail되야함
					return GOTO_FAILED;
				case DIGIT:           //+2, -1 이런 경우
					context.append(v);
					return GOTO_ACCEPT_INT;
				case WS:  
					char ch2 = context.getLexime().charAt(0);
					Character cr = new Character(ch2);
					String str = new String(cr.toString());
					return GOTO_MATCHED(TokenType.fromSpecialCharactor(ch2), str);   //한글자짜리의 특수문자 처리
				case END_OF_STREAM:
					char ch3 = context.getLexime().charAt(0);
					Character cr2 = new Character(ch3);
					String str2 = new String(cr2.toString());
					if(ch3 == ')')
						return GOTO_MATCHED(TokenType.fromSpecialCharactor(ch3), str2);   // '(' 기호 왔을 때
					return GOTO_FAILED;
				default:
					throw new AssertionError();
			}
		}
	},
	MATCHED {
		@Override
		public TransitionOutput transit(ScanContext context) {
			throw new IllegalStateException("at final state");
		}
	},
	FAILED{

		@Override
		TransitionOutput transit(ScanContext context) {
			// TODO Auto-generated method stub
			return null;
		}
	
	},
	EOS {
		@Override
		public TransitionOutput transit(ScanContext context) {
			return GOTO_EOS;
		}
	}, 
	SHARP {     // #이 온 경우
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch ( v ) {     
				case 84:       //T가 온 경우
					Character ch2 = new Character(v);
					 String temp2 = context.getLexime();
					 String b = temp2.concat(ch2.toString());
					return GOTO_MATCHED(Token.ofName(b).type(), b);
				case 70:       //F가 온 경우
					Character ch3 = new Character(v);
					 String temp3 = context.getLexime();
					 String b2 = temp3.concat(ch3.toString());
					return GOTO_MATCHED(Token.ofName(b2).type(), b2);
				default:
					throw new AssertionError();
			}
		}
		
	};
	
	abstract TransitionOutput transit(ScanContext context);

	
}
