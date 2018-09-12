//201404377_Áø½Â¾ð
package parser.ast;

public interface ListNode extends Node {

	Node car(); 
	ListNode cdr(); 
	 
	static ListNode cons(Node head, ListNode tail){ 
		  return new ListNode(){  
		   
		
		   @Override 
		   public Node car() { 
		    return head; 
		   }  
		   @Override 
		   public ListNode cdr() { 
		    return tail; 
		   } 
		    
		  }; 
	}  
	
	static ListNode EMPTYLIST = new ListNode(){ 
		@Override 
		public Node car() { 
			return null; 
		}  
		@Override 
		  public ListNode cdr() { 
			return null; 
		} 
		   
	}; 
		  	
	static ListNode ENDLIST = new ListNode(){   
		  @Override 
		  public Node car() { 
			  return null; 
		  }  
		  @Override 
		  public ListNode cdr() { 
			  return null; 
		  } 
		   
	}; 
}
