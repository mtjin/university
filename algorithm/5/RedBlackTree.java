
public class RedBlackTree {
	int inordered_data[] = new int[100]; // 중위순회한 결과 담을 배열
	int num = 0;

	private final int RED = 0;
	private final int BLACK = 1;

	// 자식이 없는 노드는 자식을 nil노드로 한다
	private class Node {

		int data = -1;
		int color = BLACK;
		Node left;
		Node right;
		Node parent;

		Node(int data) {
			this.data = data;
			left = nil;
			right = nil;
			parent = nil;
		}
	}

	private final Node nil = new Node(-1);
	private Node root = nil;

	// 삽입
	public void insert(int data) {
		Node newNode = new Node(data); // 삽입할 노드 생성
		insert(newNode);
	}

	private void insert(Node newNode) {
		Node parent = root; // 루트노드값을 복사해논다(삽입할려는 노드의 부모노드 가리키는 용도로 쓴다)
		if (root == nil) { // 루트노드가 nil(리프노드)인 경우
			root = newNode; // 삽입한 노드가 루트노드가됨
			newNode.color = BLACK; // 루트노드의 색은 BLACK이여야함
			newNode.parent = nil; // 루트노드의 부모는 nil 이여야한다
		} else { // 처음 삽입하는 노드가 아닐 경우
			newNode.color = RED; // 일단 삽입할 때의 색은 RED이여야함
			while (true) { // 노드 삽입완료 될 때까지 반복
				if (newNode.data < parent.data) { // 삽입할려는 노드 값이 parent(항상 newNode의 부모노드) 값보다 작을 경우
					if (parent.left == nil) { // 부모노드의 왼쪽 자식이 nil인 경우 그 자리에 newNode삽입
						parent.left = newNode;
						newNode.parent = parent;
						break;
					} else { // nil이 아니라면 부모노드를 왼쪽 자식노드로 하고 다시 반복문을 돌린다
						parent = parent.left;
					}
				} else if (newNode.data >= parent.data) { // 삽입할려는 노드 값이 parent(항상 newNode의 부모노드) 값보다 클 경우
					if (parent.right == nil) { // 부모노드의 왼쪽 자식이 nil인 경우 그 자리에 newNode삽입
						parent.right = newNode;
						newNode.parent = parent;
						break;
					} else { // nil이 아니라면 부모노드를 오른쪽 자식노드로 하고 다시 반복문을 돌린다
						parent = parent.right;
					}
				}
			}
			// 삽입을 한 후 레드블랙트리 특성에 맞게 고쳐준다
			fixTree(newNode);
		}
	}

	// 레드블랙트리 특성맞게 fix
	private void fixTree(Node node) {
		while (node.parent.color == RED) { // 삽입한노드 부모의 색이 RED일 때까지 반복
			Node parentSilbing = nil; // 부모의 형제노드
			if (node.parent == node.parent.parent.left) { // 삽입한노드의 부모가 부모의부모노드의(조상님노드) 왼쪽 자식노드인 경우
				parentSilbing = node.parent.parent.right; // parentSilbing을 삽입한노드의 형제노드로함

				if (parentSilbing != nil && parentSilbing.color == RED) { // parentSilbing이 nil이 아니고 RED인 경우(ppt설명으로치면
																			// case1인 경우)
					node.parent.color = BLACK; // 삽입한노드(이하생략) 부모의 색을 Black으로
					parentSilbing.color = BLACK; // 부모형제노드 색도 Black으로
					node.parent.parent.color = RED; // 조상님노드 색을 RED로
					node = node.parent.parent; // 삽입한 노드를 조상님 노드로 함
					continue;
				}
				if (node == node.parent.right) { // 삽입한노드가 부모노드의 오른쪽 자식인 경우(case2)-> case3으로 가서 한번더 색 조정하고 회전해야함
					node = node.parent; // 삽입한노드를 부모노드로 설정하고
					rotateLeft(node); // 왼쪽회전
				}
				// case3
				node.parent.color = BLACK; // 삽입한노드 부모 Black으로
				node.parent.parent.color = RED; // 삽입한노드 조상 Red로
				rotateRight(node.parent.parent);// 삽입하노드 조상 오른쪽회전
			} else { // 삽입한노드의 부모노드가 조상노드의 오른쪽 자식일때 (위와 left right 반대로해주면됨)
				parentSilbing = node.parent.parent.left;
				if (parentSilbing != nil && parentSilbing.color == RED) {
					node.parent.color = BLACK;
					parentSilbing.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) {
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateLeft(node.parent.parent);
			}
		}
		root.color = BLACK; // 루트노드색을 Black으로
	}

	// 왼쪽회전
	void rotateLeft(Node node) {
		if (node.parent != nil) { // 노드의 부모가 nil노드일 때(즉 node.parent가 루트노드일 경우)
			if (node == node.parent.left) { // 노드가 부모의 왼쪽 자식일 경우
				node.parent.left = node.right; // 부모노드의 왼쪽자식에 현재노드의 오른쪽 자식을 이어준다.
			} else { // 오른쪽 자식인 경우는 부모노드의 오른쪽 자식노드에 현재노드의 오른쪽자식을 이어준다.
				node.parent.right = node.right;
			}
			node.right.parent = node.parent; // 부모노드의 오른쪽자식의 부모노드에 부모노드를 넣는다.
			node.parent = node.right; // 부모노드를 현재노드의 오른쪽자식노드로함
			if (node.right.left != nil) { // 오른쪽자식의 왼쪽자식 노드가 nil인 경우
				node.right.left.parent = node; // 오른쪽 자식의 왼쪽자식의 부모노드에 현재노드를 넣는다.
			}
			node.right = node.right.left; // 노드의 오른쪽 자식노드에 오른쪽자식의 왼쪽자식 노드를 넣는다.
			node.parent.left = node; // 부모노드의 왼쪽 자식에 현재노드를 넣는다.
		} else {
			Node right = root.right; // 임시 오른쪽 자식노드 right 저장
			root.right = right.left; // 루트의 오른쪽 자식노드에 임시로 저장한 right노드의 왼쪽 자식노드를 넣는다
			right.left.parent = root; // 오른쪽 자식의 왼쪽 자식의 부모노드에 루트노드를 넣는다
			root.parent = right; // 루트노드의 부모노드에 임시저장했던 right 노드를 넣는다.
			right.left = root; // rigth노드의 왼족 자식에 루트노드를 넣는다
			right.parent = nil; // 루트노드의 부모를 nil로 해준다.
			root = right; // 루트 노드흫 right로 해준다.
		}
	}

	void rotateRight(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != nil) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else {// Need to rotate root
			Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}
	
	public Node search(int data) {
		Node searchNode = new Node(data);
		Node result = search2(searchNode, root);
		System.out.println("Find Node(RBT) :  " + result.data);
		return result; 
	}
	public Node search2(Node searchNode, Node root) {
        if (root == nil) { //루트노드가 nil이면 null반환
            return null;
        }

        if (searchNode.data < root.data) {  
            if (root.left != nil) {
                return search2(searchNode, root.left);
            }
        } else if (searchNode.data > root.data) {
            if (root.right != nil) {
                return search2(searchNode, root.right);
            }
        } else if (searchNode.data == root.data) {
            return root;
        }
        return null;
    }
	
	public int[] inorder() {
		inorder(root);
		return inordered_data;
	}

	// 중위순회 (오름차순 노드 키값 출력에 사용한다)
	public void inorder(Node root) {
		if (root != nil) { // nil도 노드로 설정하고 -1이란 값으로 설정하였는데 nil노드는 출력안되게 하기위해
							// 다음 if문 조건을 넣어 nil노드는 순회하지 않게하였다.(nil노드값이 떠도 상관은 없겠지만)
			inorder(root.left);
			System.out.println(root.data);
			inordered_data[num++] = root.data;
			inorder(root.right);
		}
	}
}
