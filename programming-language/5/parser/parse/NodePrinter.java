//201404377_Áø½Â¾ð
package parser.parse;

import java.io.PrintStream;

import parser.ast.ListNode;
import parser.ast.Node;

public class NodePrinter {

	PrintStream ps;    
	public static NodePrinter getPrinter(PrintStream ps) { 
		return new NodePrinter(ps); 
	} 
	private NodePrinter(PrintStream ps) { 
		this.ps = ps;
	} 
	private void printList(Node head) { 
		if (head == null) {  
			ps.print("( ) ");    
			return; 
		}   
		ps.print("( ");   
		printNode(head);  
		ps.print(")  "); 
	} 
	private void printNode(Node head) { 
		if (head == null) return;   
		if (head instanceof ListNode) { 
			ListNode ln = (ListNode) head;   
			printList(ln.value);  
		} else {   
			ps.print("[" + head + "] ");  
		}  
		printNode(head.getNext()); 
	} 
	public void prettyPrint(Node root){   
		printNode(root); 
	} 
}
