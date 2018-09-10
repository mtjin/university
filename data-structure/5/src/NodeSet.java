
public class NodeSet implements Set {

	private Node head;
	private int size;

	
	@Override
	public boolean add(String data) {
		// TODO Auto-generated method stub
		
		if(!this.contains(data)) { //데이터중복이 아니라면시행
			Node newNode = new Node(data);
			newNode.setNext(this.head);
			this.head = newNode;
			this.size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(String data) {
		// TODO Auto-generated method stub
		Node previousNode = null;
		Node currentNode = this.head;
		boolean found = false;
		
		while(currentNode != null && !found) {
			if(currentNode.getData().equals(data)) {
				found = true;
			}else {
				previousNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		if(!found) {
			return false;
		}else {
			if(currentNode == this.head) {
				this.head = this.head.getNext();
			}else {
				previousNode.setNext(currentNode.getNext());
			}
		}
		this.size--;
		return true;
	}

	@Override
	public boolean contains(String data) {
		// TODO Auto-generated method stub
		Node searchNode = this.head;
		while(searchNode != null) {
			if(searchNode.getData().equals(data)) {
				return true;
			}
			searchNode = searchNode.getNext();
		}
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public String checkNode() {
		// TODO Auto-generated method stub
		String content ="";
		Node searchNode = this.head;
		while(searchNode != null) {
			content += searchNode.getData();
			content += " ";
			searchNode = searchNode.getNext();
		}
		return content;
	}

	@Override
	public boolean swap(int i, int j) {
		// TODO Auto-generated method stub
		  Node a= head;      
		  Node b= head;
	      String temp1="";
	      String temp2="";
	      if(i>0 && j>0 && size>=i && size>=j)   //(TRUE조건)
	      {
	    	  for(int n=0; n<size; n++) {
	    		  if(n== i-1) {
	    			  temp1 = a.getData(); 
	    			  a= a.getNext();
	    		  }
	    		  else if(n== j-1) {
	    			  temp2= a.getData();
	    			  a= a.getNext();
	    		  }
	    		  else {
	    			  a= a.getNext();
	    		  }
	    	  }
	    	  for(int n=0; n<size ; n++) {
	    		  if(n == i-1) {
	    			  b.setData(temp2); 
	    			  b= b.getNext();
	    		  }
	    		  else if(n== j-1) {
	    			  b.setData(temp1);
	    			  b= b.getNext();
	    		  }
	    		  else {
	    			  b= b.getNext();
	    		  }
	    	  }
	    	  return true;
	      	}
	      	else  return false;
	   	}
}

