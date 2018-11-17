
public class Node {

	Node parent;
	Node next;
	String id;
	int data;
	Node(){
		
	}
	Node(int data){
		this.data= data;
	}

	Node(String id) {
		this.id = id;
	}
	Node(String id, int data){
		this.id = id;
		this.data = data;
	}
	
	public void setData(int data) {
		this.data = data;
	}

}
