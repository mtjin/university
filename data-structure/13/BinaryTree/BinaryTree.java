package BinaryTree;

public class BinaryTree {

	private Object root;
	private BinaryTree left, right;

	public BinaryTree(Object root) {
		this.root = root;
		this.left = null;
		this.right = null;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		if (left != null)
			buf.append(left + ",");
		buf.append(root);
		if (right != null)
			buf.append("," + right);
		return buf + "]";
	}

	public Object getRoot() {
		return root;
	}

	public void setRoot(Object root) {
		this.root = root;
	}

	public BinaryTree getLeft() {
		return left;
	}

	public void setLeft(BinaryTree left) {
		this.left = left;
	}

	public BinaryTree getRight() {
		return right;
	}

	public void setRight(BinaryTree right) {
		this.right = right;
	}

	public Boolean isBST() {
		Comparable key = (Comparable) root;
		if (left != null && key.compareTo(this.left.getRoot()) < 0)
			return false;
		if (right != null && key.compareTo(this.right.getRoot()) > 0)
			return false;
		if (left != null) {
			left.isBST();
		}
		if (right != null) {
			right.isBST();
		}
		return true;

	}

	public void insertKey(Object data) {
		if (data.hashCode() > root.hashCode()) {
			if (right != null) {
				right.insertKey(data);
			} else if (right == null) {
				BinaryTree tmp = new BinaryTree(data);
				right = tmp;
			}
		} else if (data.hashCode() < root.hashCode()) {
			if (left != null) {
				left.insertKey(data);
			} else if (left == null) {
				BinaryTree tmp = new BinaryTree(data);
				left = tmp;
			}
		}
	}

}
