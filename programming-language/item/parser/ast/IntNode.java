//201404377_진승언
package parser.ast;

public class IntNode implements ValueNode{

	public Integer value;  //private에서 public으로 변경
	
	@Override 
	public String toString(){ 
		return "INT: " +  value;
	} 
	
	public IntNode(String text) { 
		  this.value = new Integer(text); 
	} 
		  
}
