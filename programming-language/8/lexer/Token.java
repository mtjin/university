//201404377_진승언
package lexer;
import java.util.HashMap;
import java.util.Map;

public class Token {                            //토큰의 타입과 문자를 저장, 키워드인지 퀘스천인지 등을 알아낼 수 있음
	private final TokenType type;
	private final String lexme;
	
	static Token ofName(String lexme) {   
		TokenType type = KEYWORDS.get(lexme);                 //키워드를 받음   
		if ( type != null ){                                  //키워드가 있으면 그 키워드타입의 토큰반환
			return new Token(type, lexme);  
		} 
		else if ( lexme.endsWith("?") ){                          //정규식에서의  ID'?' =>QUESTION
			if ( lexme.substring(0, lexme.length()-1).contains("?") ) { 
				throw new ScannerException("invalid ID=" + lexme);   //문장 끝에?가 있지만 중간에 ?가 또있는경우 invaild
			}       
			return new Token(TokenType.QUESTION, lexme);       //?가 끝에있으면 QUESTION타입으로 토큰반환
			}   
		
		else if ( lexme.contains("?") ) {                      //끝에 ?가 없고 다른부분에 ?가 있는경우 invalid
			throw new ScannerException("invalid ID=" + lexme);  
		}


		else if(lexme.startsWith("#")) {              //내가추가한거  lexme가 #T ,#F인 경우
			if(lexme.endsWith("T") && lexme.length()==2) {
				return new Token(TokenType.TRUE, lexme);
			}
			else if(lexme.endsWith("F") && lexme.length()==2) {
				return new Token(TokenType.FALSE, lexme);
			}
			else  throw new ScannerException("invalid ID=" + lexme); 
		} 
		
		else {
				return new Token(TokenType.ID, lexme);            //?와  관련없는 문장이며 키워드문장도 아닌경우 ID타입으로 토큰 반환
			}
		}
	Token(TokenType type, String lexme) { 
		this.type = type;  
		this.lexme = lexme; 
	}   
	public TokenType type() { 
		return this.type; 
	}   
	public String lexme() { 
		return lexme; 
	}    
	
	@Override 
		public String toString() { 
		return String.format("%s(%s)", type, lexme);
		}
	
		private static final Map<String,TokenType> KEYWORDS = new HashMap<>(); 
		static { 
			KEYWORDS.put("define", TokenType.DEFINE);
			KEYWORDS.put("lambda", TokenType.LAMBDA); 
			KEYWORDS.put("cond", TokenType.COND);
			KEYWORDS.put("quote", TokenType.QUOTE);  
			KEYWORDS.put("not", TokenType.NOT); 
			KEYWORDS.put("cdr", TokenType.CDR);  
			KEYWORDS.put("car", TokenType.CAR);  
			KEYWORDS.put("cons", TokenType.CONS); 
			KEYWORDS.put("eq?", TokenType.EQ_Q); 
			KEYWORDS.put("null?", TokenType.NULL_Q);  
			KEYWORDS.put("atom?", TokenType.ATOM_Q); 
		}
}