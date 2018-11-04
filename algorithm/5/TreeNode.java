
public class TreeNode {

	// 더 간단하게 하기위해서 setter , getter없이 그냥 변수 바로 직접 접근하게 public변수로 선언하였다.
	int data;
	int color; // 0은 레드 1은 블랙으로 하기로한다.(기본은 레드)
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public TreeNode nil = null;

	public TreeNode() {
		this.left = nil;
		this.right = nil;
		this.color = 0;
		this.parent = nil;
	}

	public TreeNode(int data) {
		this.data = data;
		this.left = nil;
		this.right = nil;
		this.color = 0;
		this.parent = nil;
	}

}
