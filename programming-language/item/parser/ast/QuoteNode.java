//201404377_Áø½Â¾ð
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
