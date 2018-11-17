
public class UnionFind {
	
	public void makeSet(Node x) {	//노드의 부모를 자신으로 함
		x.parent = x;
	}

	
	public Node findSet(Node x) {	//해당 노드의 최상위 부모를 찾음
		if(x.parent == x) {
			return x;
		}
		else {
			return findSet(x.parent);
		}
	}
	
	public void unions(Node x, Node y) {  //한노드의 최상위노드의 부모를 다른 노드의 최상위노드로 이어줌
		findSet(y).parent = findSet(x);
	}
}
