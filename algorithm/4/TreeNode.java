

public class TreeNode {

	//더 간단하게 하기위해서 setter , getter없이 그냥 변수 바로 직접 접근하게 public변수로 선언하였다.
	int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	

	public TreeNode() {
		this.left = null;
		this.right = null;
	}
	public TreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	
	

}	
