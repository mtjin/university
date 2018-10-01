
public class IntNodeStack implements IntStack {

	private Node head;
	private int size;
	
	 public IntNodeStack() {

	 }
	 public boolean isEmpty() {
		 return (size == 0);
	 }
	 public int pop() {
		 if(size == 0) {
			 return -1;
		 }
		 else
		 {
		 Node tmp = head;
		 this.head = tmp.getNext();
		 size--;
		 return tmp.getData();
		 }
		
	 }
	 
	 public int popBottom() {
		 if(size == 0)
		 {
			 return -1;
		 }
		 else 
		 {
			 Node tmp = head;
			 Node tmp2 = head;
			 for(int i=0; i<size-2; i++) 
			 {	 
				 tmp = tmp.getNext();
				 tmp2 = tmp2.getNext();
				 //처음노드 바로 전 노드까지감
			 }
			 
			 int result= (tmp.getNext()).getData();
			 tmp2.setNext(null);
			 size--;
			 return result;
			 
		 }
		
	 }
	 public void push(int i) {
		 Node newNode = new Node(i);
		 newNode.setNext(this.head);
		 this.head = newNode;
		 size++;
	 }
	 public int size() {
		 return size;
	 }
	 public String checkNode() {
		  	String content ="";
			Node searchNode = this.head;
			while(searchNode != null) {
				content += searchNode.getData();  //string형변환해줘야할 것같은데
				content += " ";
				searchNode = searchNode.getNext();
			}
			return content;
	 }
}
