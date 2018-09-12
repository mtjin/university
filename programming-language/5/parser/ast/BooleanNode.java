//201404377_Áø½Â¾ð
package parser.ast;

public class BooleanNode extends Node{

	public boolean value;
	@Override 
	public String toString(){ 
		return value ? "#T" : "#F";  
	} 
}
