//201404377_���¾�
package parser.ast;

public class QuoteNode implements Node{

	Node quoted; 
	
	public QuoteNode(Node quoted) { 
		  this.quoted = quoted; 
	} 
	
	@Override 
	 public String toString(){ 
	  return quoted.toString(); 
	}   
		  
	public Node nodeInside() { 
		  // TODO Auto-generated method stub 
		  return quoted;  
	} 
}
