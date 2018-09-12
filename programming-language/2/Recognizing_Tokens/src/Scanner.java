//201404377진승언 
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Scanner {
	public enum TokenType {
		ID(3), INT(2);     
		private final int finalState;   
		TokenType(int finalState) {    
			this.finalState = finalState;  
			}  
		} 
	
	
	public static class Token {
		public final TokenType type;  
		public final String lexme;  
		  Token(TokenType type, String lexme) { 
			  this.type = type;    this.lexme = lexme;  
			  }  
		  @Override  
		  public String toString() {   
			  return String.format("[%s: %s]", type.toString(), lexme);    // 출력값
		  }
	}
	private int transM[][]; 
	private String source;   
	private StringTokenizer st;

	
	public Scanner(String source) { 
		this.transM = new int[4][128];  
		this.source = source == null ? "" : source;  
		initTM(); 
		}   
	
	private void initTM() {
		transM[0][45] = 1;                          // '-'기호가 온 경우
        transM[1][45] = -1;  
        transM[2][45] = -1;   
        transM[3][45] = -1;
		for(int i=0;i<4;i++) {
			for(int j=48;j<58;j++) {                //Digit이 온 경우
				if(i==0) transM[i][j] = 2;      
	            if(i==1) transM[i][j] = 2;  
	            if(i==2) transM[i][j] = 2;   
	            if(i==3) transM[i][j] = 3;  
	            }
			
	        for(int j=97;j<123;j++) {                //a~z가 온 경우(Alpha)
	        	if(i==0) transM[i][j] = 3;     
	            if(i==1) transM[i][j] = -1;    
	            if(i==2) transM[i][j] = 3;
	            if(i==3) transM[i][j] = 3; 
	          }
	        for(int j=65;j<90;j++) {                 //A~Z가 온 경우(Alpha)
	            if(i==0) transM[i][j] = 3;
	            if(i==1) transM[i][j] = -1;
	            if(i==2) transM[i][j] = 3;
	            if(i==3) transM[i][j] = 3;   
	         }
	      }
	   } 
	
	
	private Token nextToken(){    
				int stateOld = 0, stateNew;  
				if(!st.hasMoreTokens()) return null;   //토큰이 더 있는지 검사   
				String temp = st.nextToken();          //그 다음 토큰을 받음   
		
				Token result = null;   
				for(int i = 0; i<temp.length();i++){    //문자열의 문자를 하나씩 가져와 상태 판별    
				stateNew = transM[stateOld][temp.charAt(i)];       
			
				if(stateNew == -1){                     //입력된 문자의 상태가 reject 이므로 에러메세지 출력후 return함     
					System. out .println(String. format ("acceptState error %s\n", temp));
					return null;    
				}   
				stateOld = stateNew;   
			}
			for (TokenType t : TokenType. values ()){   
				if(t.finalState == stateOld){     
					result = new Token(t, temp);    // 최종 토큰
					break;  
				}   
			}  
			return result; 
		}
	public List<Token> tokenize() {
		//Token 리스트반환, nextToken()이용..
		List<Token> result= new LinkedList<Token>();
		st= new StringTokenizer(source," ");        
		Token next_token = nextToken();
		while(next_token != null) {
			result.add(next_token);
			next_token = nextToken();
		}
		return result;
	}
	
	public static void main(String[] args){   
		//txt file to String   
		FileReader fr;
		try {   
		    fr = new FileReader("./as02.txt");   
			BufferedReader br = new BufferedReader(fr);  
			String source=br.readLine();
			Scanner s = new Scanner(source);    
			List<Token> tokens = (s.tokenize());
			for(int i=0; i<tokens.size(); i++) {
			System.out.println(tokens.get(i));
			}
			} catch (IOException e) {  
					// TODO Auto-generated catch block   
					e.printStackTrace(); 
					}   
		}
}