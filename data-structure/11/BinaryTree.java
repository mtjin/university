
public class BinaryTree {

	private Object root;
	private BinaryTree left, right;

	public BinaryTree(Object root) {
		this.root = root;
	}

	public BinaryTree(Object root, BinaryTree left, BinaryTree right) {
		this(root);
		if (left != null)
			this.left = left;
		if (right != null)
			this.right = right;
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

	public void inorderPrint() {
		if (left != null) {
			left.inorderPrint();
		}
		if (root != null) {
			System.out.print(root);
		}
		if (right != null) {
			right.inorderPrint();
		}
	}

	public void preorderPrint() {
		if (root != null) {
			System.out.print(root);
		}
		if (left != null) {
			left.preorderPrint();
		}
		if (right != null) {
			right.preorderPrint();
		}
	}

	public void postorderPrint() {
		if (left != null) {
			left.postorderPrint();
		}
		if (right != null) {
			right.postorderPrint();
		}
		if (root != null) {
			System.out.print(root);
		}
	}
}
