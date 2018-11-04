
public class BinarySearchTree {
	int inordered_data[] = new int[100]; // 중위순회한 결과 담을 배열
	int num = 0;
	TreeNode root;

	public TreeNode getRoot() {
		return root;
	}

	public void insert(int data) {
		if (root == null) { // 루트노드가 null이면 루트노드에 해당값으로 생성해준다.
			root = new TreeNode(data);
		} else { // 기존 루트노드가 비어있지 않으면 즉 처음넣는값이 아닌 경우
			TreeNode newNode = new TreeNode(data);
			insertKey(this.root, newNode);
		}
	}


	// 트리에 데이터 삽입
	public void insertKey(TreeNode root, TreeNode newNode) {
		TreeNode parent = null; // x의 부모노드 역활함
		TreeNode x = root; // root노드로 받아옴
		while (x != null) {
			parent = x;
			if (newNode.data < x.data) { // 새로삽입할려는 노드가 현재 비교노드 보다 작은 경우(처음 비교노드는 루트노드)
				x = x.left; // 비교노드를 왼쪽자식노드로바꿈
			} else {
				x = x.right; // 비교노드를 오른쪽 자식노드로 바꿈
			}
		}
		newNode.parent = parent; // 삽입하는 노드의 부모를 parent노드로 해줌

		// 마지막 비교까지 온 노드와(즉 리프노드까지 비교) 비교해서 해당비교노드의 왼쪽오른쪽 자식노드에 newNode 삽입
		if (newNode.data < parent.data) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

	}

	// 재귀 탐색
	public TreeNode recursive_search(int k) {
		return recursive_search2(root, k);
	}

	public TreeNode recursive_search2(TreeNode x, int k) {
		if (x == null || k == x.data) { //해당 값을 가진 노드를 찾았을 때 해당노드 반환
			return x;
		}
		if (k < x.data) { //x가 비교하는 노드보다 작거나 클 때 알맞게 재귀를 돌린다.
			return recursive_search2(x.left, k);
		} else {
			return recursive_search2(x.right, k);
		}
	}

	// 반복문 탐색
	public TreeNode iterative_search(int k) {
		return iterative_search2(root, k);
	}

	public TreeNode iterative_search2(TreeNode x, int k) { 
		//해당 값을 가진 노드가 나올 때 까지 반복문을 돌린다
		while (x != null && k != x.data) {
			if (k < x.data) { //기준노드 보다 찾으려는 값이 작으면 기준노드를 왼쪽자식으로 체인지
				x = x.left;
			} else {    //기준노드 보다 크면 기준노드를 왼쪽자식으로 체인지
				x = x.right;
			}
		}
		return x;
	}

	// main에서 getRoot해서 루트받고 원하는 노드 골라서 넣어주면됨
	// 후임자(오른쪽 서브트리의 최솟값)
	public TreeNode sucessor(TreeNode x) {
		
		if (x.right != null) {  //먼저 해당노드의 오른쪽 자식이 있어야한다(루트의 오른쪽서브트리 중 가장 작은 값을 찾는것이기 떄문)
			return treeMinimum(x.right);  //가장 작은 값을 반환해준다.
		}
		//오른쪽 자식이 null인 경우
		TreeNode y = x.parent;
		while (y != null && x == y.right) {  
			x = y;
			y = y.parent;
		}
		return y;
	}

	// 자식 서브트리중 가장 작은 노드 반환
	public TreeNode treeMinimum(TreeNode x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	// 선임자(왼쪽 서브트리의 최댓값)
	public TreeNode prodecessor(TreeNode x) {
		if (x.left != null) { //왼쪽자식노드가 있는지 확인
			return treeMaximum(x.left);  //최댓값을 가진 노드를 반환함
		}
		//왼쪽 자식이 null인 경우
		TreeNode y = x.parent;
		while (y != null && x == y.left) { 
			x = y;
			y = y.parent;
		}
		return y;
	}

	// 자식서브트리중 가장 큰 값 반환
	public TreeNode treeMaximum(TreeNode x) {
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}

	public void delete(TreeNode root, TreeNode removed) {
		if (removed.left == null ) { //지우고자 하는 노드가 자식이 없을 때
			transplant(root, removed, removed.right); // 지우고자 하는 노드를 삭제한다
		} else if (removed.right == null) { // 지우고자 하는 노드가 하나의 자식을 가질 때
			transplant(root, removed, removed.left); // 지우고자 하는 노드의 자식을 지우고자 하는 노드의 위치로 이동한다.
		} else { // 지우고자 하는 노드가 둘의 자식을 가질 때
			TreeNode y = treeMinimum(removed.right);
			if (y.parent != removed) { //지우고자 하는 노드의 후임과 교체한다.
				transplant(root, y, y.right);
				y.right = removed.right;
				y.right.parent = y;
			}
			transplant(root, removed, y); //위의 다른 유형을 적용하여 지우고자 하는 노드를 지운다.
			y.left = removed.left;
			y.left.parent = y;
		}

	}

	//노드를 삭제하고 자리이동시켜준다.
	public void transplant(TreeNode root, TreeNode u, TreeNode v) {
		if (u.parent == null) { 
			root = v;  
		} else if (u == u.parent.left) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
		if (v != null) {
			v.parent = u.parent;
		}
	}

	public int[] inorder() {
		inorder(this.root);
		return inordered_data;
	}

	public void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.left);
			System.out.println(root.data);
			inordered_data[num++] = root.data;
			inorder(root.right);
		}
	}

}