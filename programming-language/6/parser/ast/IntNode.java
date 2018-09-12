//201404377_Áø½Â¾ð
package parser.ast;

public class IntNode implements ValueNode{

	private Integer value; 
	
	@Override 
	public String toString(){ 
		return "INT: " +  value;
	} 
	
	public IntNode(String text) { 
		  this.value = new Integer(text); 
	} 
		  
}
