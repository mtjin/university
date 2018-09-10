public class LinkedQueue {
	
	private int size;
	private Node head = new Node();
	
	public LinkedQueue() {
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public void add(Object item) {
		
		Node new_node = new Node(item);
		new_node.setNext(null);
		
		if(isEmpty()) {
			head.setNext(new_node);
			head.setPrev(new_node);
		}
		else {
			if(size == 1) {
				Node tmp1 = head.getNext();
				tmp1.setNext(new_node);   
				tmp1.setPrev(new_node);
				new_node.setNext(tmp1);
				new_node.setPrev(tmp1);
			}
			else {
			Node tmp1= head.getNext(); //첫노드
			Node tmp2= tmp1.getPrev(); //마지막노드
			tmp1.setPrev(new_node); //첫노드와 prev를 새노드에 연결
			tmp2.setNext(new_node);    //마지막노드의 next를 새노드에 연결
			new_node.setPrev(tmp2); // 새노드의 prev를 마지막노드와연결
			new_node.setNext(tmp1); //새노드의 next를 첫노드와 연결
			}
			
		}
		
		size++;
	}
	
	public Object peek() { //맨앞에있는 노드fornt를 꺼내주는 자겅ㅂ
		if(isEmpty()) {
			throw new IllegalStateException("the queue is empty");
		}
		else {
			return head.getNext();
		}
	}
	
	//front를 큐에서 제거한다
	public Object remove() {
		
		Object item = peek();
		if(size == 1) {
			head.setNext(null);
			head.setPrev(null);
			size--;
			return item;
		}
		else {
		Node tmp = head.getNext(); 
		Node tmp2 = tmp.getNext(); 	
		Node tmp3 = tmp.getPrev(); 
		tmp3.setNext(tmp2); 
		head.setNext(tmp2); 
		tmp2.setPrev(tmp3);  
		size--;
		return item;
		}
		
	}
	
	public int size() {
		return size;
	}
	
	public Node getHead() {
		return this.head;
	}
	
	
}
