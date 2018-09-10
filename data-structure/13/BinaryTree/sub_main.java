package BinaryTree;

public class sub_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree treeA = 	new BinaryTree("C");
		treeA.insertKey("D");
		treeA.insertKey("B");
		treeA.insertKey("G");
		treeA.insertKey("A");
		treeA.insertKey("E");
		treeA.insertKey("F");
		
		System.out.println("treeA: " + treeA);
		System.out.println("treeA.isBSt(): " + treeA.isBST());

	}

}
