
public class DoubleLinked {
	private Node head;
	private Node tail;
	private int size;
	
	public DoubleLinked() {
		size =0;
	}
	
	public class Node{
		public Node next;
		public Node prev;
		public int data;
		Node(int data){
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}
		
		public String toString() {
			return ""+ data;
		}
		
		
	}
	
	public void addFirst(int line) {
		Node node = new Node(line);
		
		if(head != null) {
			node.next = head;
			head.prev = node;
		}
		
		head = node;
		if(head.next == null) {
			tail =head;
		}
		size++;
	}
	
	public void addLast(int line) {
		if(size == 0) {
			addFirst(line);
		}
		else {
			Node node =new Node(line);
			tail.next = node;
			node.prev = tail;
			tail = node;
			size++;
		}
		////
	}
	
	public Object removeFirst() {
		if(size == 0) {
			return null;
		}
		Node removeNode = head;
		head = null;
		head = removeNode.next;
		head.prev = null;
		size--;
		return removeNode.data;
	}
	
	public Object removeLast() {
		if(size == 0) {
			return null;
		}
		Node removeNode = tail;
		tail = null;
		tail = removeNode.prev;
		tail.next = null;
		size--;
		return removeNode.data;
	}
	
	public Object remove(int index) {
		if(size == 0) {
			return null;
		}
		if(index == 0) {
			return removeFirst();
		}else if(index == size-1) {
			return removeLast();
		}else {
			Node removeNode = getNode(index);
			Node prevNode = removeNode.prev;
			Node nextNode = removeNode.next;
			
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			size--;
			
			return removeNode.data;
		}
	}
	
	public Node getNode(int index) {
		if(index < size/2) {
			Node node =head;
			for(int i=0; i<index; i++) {
				node = node.next;
			}
			return node;
		}
		else {
			Node node = tail;
			for(int i = size -1; i>index; i--){
					node = node.prev;
			}
			return node;
		}
	}
	
	public int size() {
		return size;
	}
	
	public String printAll() {
		StringBuffer result = new StringBuffer();
		if(head == null) {
			System.out.println("Data is not exitst");
			
		}
		if(head != null) {
		/*	System.out.println(head.data);
			while(head.next != null) {
			System.out.println(head.next.data);
			head= head.next;
			
			}*/
			result.append(head.data);
			result.append(",\n");
			while(head.next != null) {
				result.append(head.next.data);
				result.append(",\n");
				head= head.next;
				}
		}
		return result.toString();

	}
	

	
}
