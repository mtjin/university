package AVL;

public class AVLTree {

	private int key, height;
	private AVLTree left, right;
	
	public static final AVLTree NIL = new AVLTree();
	
	public AVLTree(int key) {
		this.key = key;
		left = right = NIL;
	}
	
	private AVLTree() {
		left = right =this;
		height = -1;
	}
	
	private AVLTree(int key, AVLTree left, AVLTree right) {
		this.key = key;
		this.left= left;
		this.right = right;
		height = 1 + Math.max(left.height, right.height);
		
	}
	
	public boolean add(int key) {
		int oldSize = size();
		grow(key);
		return size() > oldSize;
	}
	
	public int getHeight() {
		return height;
	}
	
	public AVLTree getLeft() {
		if(this == NIL) throw new IllegalStateException();
		return left;
	}
	
	public AVLTree getRight() {
		if(this == NIL) throw new IllegalStateException();
		return right;
	}
	
	public int getRoot() {
		if(this == NIL) return -1;
		return key;
	}
	
	public AVLTree grow(int key) {
		if(this == NIL) return new AVLTree(key);
		
		if(key==this.key) return this;
		if(key < this.key) left = left.grow(key);
		else right = right.grow(key);
		rebalance();
		height = 1 + Math.max(left.height,right.height);
		return this;
	}
	
	public int size() {
		if(this == NIL) return 0;
		return 1 + left.size() + right.size();
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		if(left != NIL) buf.append(left +",");
		buf.append(key);
		if(right != NIL) buf.append(","+right);
		return buf + "]";
	}
	
	private void rebalance() {
		if(right.height > left.height+1) {
			if(right.left.height > right.right.height) right.rotateRight();
			rotateLeft();
		}
		else if(left.height > right.height+1) {
			if(left.right.height > left.left.height) left.rotateLeft();
			rotateRight();
		}
	}
	private void rotateLeft() {
		left = new AVLTree(key,left, right.left);
		key = right.key;
		right = right.right;
	}
	
	private void rotateRight() {
		right = new AVLTree(key, left.right, right);
		key = left.key;
		left = left.left;
	}
}
