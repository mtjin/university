//201404377_Áø½Â¾ð
package parser.ast;

public class IdNode implements ValueNode{

	String idString;
	
	public IdNode(String text) { 
		  idString = text; 
	}  
		 
	@Override  
	public String toString(){ 
		return "ID: " +  idString;
	}
}
