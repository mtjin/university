//201404377_진승언
package parser.parse;

import java.io.PrintStream;

import parser.ast.IdNode;
import parser.ast.ListNode;
import parser.ast.Node;
import parser.ast.QuoteNode;

public class NodePrinter {

	PrintStream ps;    
	
	public static NodePrinter getPrinter(PrintStream ps) { 
		return new NodePrinter(ps); 
	} 
	private NodePrinter(PrintStream ps) { 
		this.ps = ps;
	} 
	
	private void printNode(ListNode listNode) { 
		if (listNode == ListNode.EMPTYLIST) { 
			   ps.print("( ) "); 
			   return; 
		} 
		if (listNode == ListNode.ENDLIST) {  
			   return; 
		}
		// 이후 부분을 주어진 출력 형식에 맞게 코드를 작성하시오. 
		
		ps.print("( "); 
		printNode(listNode.car()); //처음원소 (Node)
		printNode(listNode.cdr()); //처음원소 뒤 리스트(ListNode)
		ps.print(") ");
		
	} 

	private void printNode(QuoteNode quoteNode) {       
		  if (quoteNode.nodeInside() == null) 
			  return;  
		// 이후 부분을 주어진 출력 형식에 맞게 코드를 작성하시오. 
		
		  ps.print('\'');
		  if(quoteNode.nodeInside() instanceof IdNode) {
			  ps.print(quoteNode);
		  }
		  else {
		  ListNode listNode = (ListNode)quoteNode.nodeInside();
		  ps.print("( ");
		  printNode(listNode.car());
		  printNode(listNode.cdr());
		  ps.print(") ");
		  }
		  
		 
	}
	
	private void printNode(Node node) {       
		if (node == null)  
			return; 
		// 이후 부분을 주어진 출력 형식에 맞게 코드를 작성하시오.
		if (node instanceof ListNode) { 
			ListNode listNode = (ListNode)node;
			printNode(listNode); 
			//printNode(listNode.cdr());
		}
		else if(node instanceof QuoteNode) {
			QuoteNode quoteNode = (QuoteNode)node;  
			printNode(quoteNode);  
		}
		else {   
			ps.print("[" + node + "] "); 
		}  
		
		
	}
		  
	public void prettyPrint(Node node){   
		printNode(node); 
	} 
}
