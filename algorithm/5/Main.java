import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		String line = "";
		String line2 = "";
		int i = 0;
		int size = 0;// 불러오는 데이터개수

		int RBT_data[] = new int[100];
		int BST_data[] = new int[100];

		// 파일 읽어와서 정수형배열에 저장
		try {
			File file_10 = new File("Data1.txt");
			FileReader filereader = new FileReader(file_10);
			BufferedReader bufReader = new BufferedReader(filereader);

			while ((line = bufReader.readLine()) != null) {

				RBT_data[i] = Integer.parseInt(line);
				BST_data[i] = Integer.parseInt(line);
				i++;
			}
			size = i; // 불러온 사이즈 크기(즉 읽는 데이터 개수)
			i = 0;
		} catch (IOException e) {
			System.out.println(e);
		}

		RedBlackTree RBTtree = new RedBlackTree();
		BinarySearchTree BSTtree = new BinarySearchTree();

		// 레드블랙트리 삽입
		System.out.println("================================================");
		System.out.println("start Red Black Tree insert");
		for (i = 0; i < size; i++) {
			RBTtree.insert(RBT_data[i]);
			// System.out.println(RBT_data[i]+" AAAA");
		}

		int RBT_result[] = RBTtree.inorder(); // 배열로 중위순회한 결과값 받음

		// 이진탐색트리 삽입
		System.out.println("================================================");
		System.out.println("start Binary Search Tree insert");
		for (i = 0; i < size; i++) {
			BSTtree.insert(BST_data[i]);
		}

		int BST_result[] = BSTtree.inorder(); // 배열로 중위순회한 결과값 받음

		//search 시간측정
		System.out.println("================================================");
		System.out.println("This is find time of RED BLACK TREE");
		long currentTime = System.nanoTime();
		RBTtree.search(47);
		long endTime = System.nanoTime();
		System.out.println("RBT : "+ (endTime - currentTime));
		
		System.out.println("This is fine time of BINARY SEARCH TREE");
		currentTime = System.nanoTime();
		TreeNode searchNode = BSTtree.recursive_search(47);
		System.out.println("Find Node(BST) : "+ searchNode.data);
		endTime = System.nanoTime();
		System.out.println("BST : "+ (endTime - currentTime));
		
		// 파일 출력
		try {
			System.out.println("================================================");
			System.out.println("START Write File");
			BufferedWriter out_RBT = new BufferedWriter(new FileWriter("RedBlackTree.txt"));
			BufferedWriter out_BST = new BufferedWriter(new FileWriter("BinarySearchTree.txt"));

			out_RBT.write("Below is RBT insert result\n");
			out_RBT.newLine();
			for (i = 0; i < RBT_data.length; i++) {
				if (RBT_result[i] != 0) {
					out_RBT.write(RBT_result[i] + "");
					out_RBT.newLine();
				}
			}

			out_RBT.write("================================================");
			out_BST.write("Below is BST intsert result\n");
			out_BST.newLine();
			for (i = 0; i < BST_data.length; i++) {
				if (BST_result[i] != 0) {
					out_BST.write(BST_result[i] + "");
					out_BST.newLine();
				}
			}

			out_RBT.close();
			out_BST.close();

		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}

	}

}
