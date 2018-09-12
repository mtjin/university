//201404377_진승언
package parser.parse;

import java.io.FileNotFoundException;
import java.util.Iterator;

import lexer.Scanner;
import lexer.Token;
import lexer.TokenType;
import parser.ast.BinaryOpNode;
import parser.ast.BooleanNode;
import parser.ast.FunctionNode;
import parser.ast.IdNode;
import parser.ast.IntNode;
import parser.ast.ListNode;
import parser.ast.Node;
import parser.ast.QuoteNode;

public class CuteParser {

	private Iterator<Token> tokens; 
	private static Node END_OF_LIST = new Node(){};   //새로 추가된 부분
	
	public CuteParser(String str) {    //수정해줌(8주차)
		try {   
			tokens = Scanner.scan(str);  
		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block  
			e.printStackTrace();   
			} 
		}  
	private Token getNextToken() {   
		if (!tokens.hasNext())   
			return null;  
		return tokens.next(); 
		} 
	public Node parseExpr() { 
		Token t = getNextToken();  
		if (t == null) {   
			System.out.println("No more token");   
			return null;  
		}  
		TokenType tType = t.type(); 
		String tLexeme = t.lexme();  
		switch (tType) { 
		case ID:   
			IdNode idNode = new IdNode(tLexeme);  //수정했음
			return idNode;  
		case INT:  
			IntNode intNode = new IntNode(tLexeme);     //수정햇음  
			if (tLexeme == null)   
				System.out.println("???"); 
			return intNode;  
		
		// BinaryOpNode  +, -, /, *가 해당  
		case DIV:  
		case EQ: 
		case MINUS:  
		case GT:  
		case PLUS:  
		case TIMES: 
		case LT:    
			BinaryOpNode binaryNode = new BinaryOpNode();  
			binaryNode.setValue(tType);   
			return binaryNode;  
			  
		// FunctionNode 키워드가 FunctionNode에 해당   
		case ATOM_Q: 
		case CAR:  
		case CDR: 
		case COND: 
		case CONS:
		case DEFINE:  
		case EQ_Q:
		case LAMBDA:  
		case NOT: 
		case NULL_Q: 
			FunctionNode functionNode = new FunctionNode(); // 내용 채우기 (BinaryOp참고)
			functionNode.setValue(tType);   
			return functionNode; 
			 
			
		// 새로 구현된 BooleanNode  
		case FALSE:    
			return BooleanNode.FALSE_NODE; 
			
		case TRUE:   
			return BooleanNode.TRUE_NODE;
		
		// case L_PAREN일 경우와 case R_PAREN일 경우
		// L_PAREN일 경우 parseExprList()를 호출하여 처리   
		
		//새로 구현된 L_PAREN, R_PAREN  Case 
		case L_PAREN:    
			return parseExprList();  
			  
		case R_PAREN: 
			return END_OF_LIST ; 
			  
		//새로 추가된 APOSTROPHE, QUOTE 
		case APOSTROPHE: 
			   return new QuoteNode(parseExpr()); 
		case QUOTE: 
			   return new QuoteNode(parseExpr()); 
		default:    
			// head의 next를 만들고 head를 반환하도록 작성
			System.out.println("Parsing Error!");    
			return null;  
		}  
	}  
	// List의 value를 생성하는 메소드
	private Node parseExprList() {  
		Node head = parseExpr(); 
		// head의 next 노드를 set하시오.
		if (head == null) { // if next token is RPAREN  
			return null;  
		}
		if (head == END_OF_LIST) // if next token is RPAREN          
			return ListNode.ENDLIST;   
		
		ListNode tail = (ListNode) parseExprList(); 
		if (tail == null)     
		      return null; 
		  
		return ListNode.cons(head, tail);   
	} 
	
}
	
