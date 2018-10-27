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
		
		int data10[] = new int[10];
		int data20[] = new int[20];
		int data10_median[] = new int[10];
		int data20_median[] = new int[20];
		//파일 읽어와서 정수형배열에 저장
		try {
			File file_10 = new File("Data1.txt");
			File file_20 = new File("Data2.txt");
			FileReader filereader = new FileReader(file_10);
			BufferedReader bufReader = new BufferedReader(filereader);
			FileReader filereader2 = new FileReader(file_20);
			BufferedReader bufReader2 = new BufferedReader(filereader2);

			while ((line = bufReader.readLine()) != null) {
			
				data10[i] = Integer.parseInt(line);
				data10_median[i] = Integer.parseInt(line);
				i++;
			}
			i = 0;
			while ((line2 = bufReader2.readLine()) != null) {
			
				data20[i] = Integer.parseInt(line2);
				data20_median[i] = Integer.parseInt(line2);
				i++;
			}

		} catch (IOException e) {
			System.out.println(e);
		}
		
	
		BinarySearchTree tree10 = new BinarySearchTree();
		BinarySearchTree tree20 = new BinarySearchTree();
		BinarySearchTree tree10_median = new BinarySearchTree();
		BinarySearchTree tree20_median = new BinarySearchTree();
		
		//일반 삽입
		System.out.println("================================================");
		System.out.println("start data10 insert");
		for(i=0; i<data10.length; i++) { 
			tree10.insert(data10[i]);
		}
		
		int result1[] = tree10.inorder(); //배열로 중위순회한 결과값 받음
		
		
		System.out.println("start data20 insert");
		for(i=0; i<data20.length; i++) {
			tree20.insert(data20[i]);
		}
		
		int result2[] = tree20.inorder();
		
		//median 삽입
		System.out.println("================================================");
		System.out.println("start data10_median insert");
		tree10_median.median_insert(data10_median);
		int result3[] = tree10_median.inorder();
		
		System.out.println("start data20_median insert");
		tree20_median.median_insert(data20_median);
		int result4[] = tree20_median.inorder();
		
		
		//recursive_search
		System.out.println("================================================");
		System.out.println("this is recursive_search result EX)26(10개짜리텍스트파일),30(20개짜리텍스트파일)");
		TreeNode searchNode1 = tree10.recursive_search(26);
		TreeNode searchNode2 = tree20.recursive_search(30);
		System.out.println(searchNode1.data); 
		System.out.println(searchNode2.data);
		
		//iterative_search
		System.out.println("================================================");
		System.out.println("this is iterative_search result EX)26(10개짜리텍스트파일),30(20개짜리텍스트파일)" );
		TreeNode searchNode3 = tree10.iterative_search(26);
		TreeNode searchNode4 = tree20.iterative_search(30);
		System.out.println(searchNode3.data); 
		System.out.println(searchNode4.data);
		
		
		
		//sucessor
		System.out.println("================================================");
		System.out.println("this is sucessor result");
		TreeNode sucessor1 = tree10.sucessor(tree10.getRoot());
		TreeNode sucessor2 = tree20.sucessor(tree20.getRoot());
		System.out.println("sucessor of Data1.txt tree: "+ sucessor1.data); 
		System.out.println("sucessor of Data2.txt tree: "+sucessor2.data);
		
		//prodecessor
		System.out.println("================================================");
		System.out.println("this is prodecessor result");
		TreeNode prodecessor1 = tree10.prodecessor(tree10.getRoot());
		TreeNode prodecessor2 = tree20.prodecessor(tree20.getRoot());
		System.out.println("prodecessor of Data1.txt tree: "+prodecessor1.data); 
		System.out.println("prodecessor of Data2.txt tree: "+prodecessor2.data);
		
	
		//파일 출력
		try {
			System.out.println("================================================");
			System.out.println("START Write File");
			BufferedWriter out_10 = new BufferedWriter(new FileWriter("BST_result10.txt"));
			BufferedWriter out_20 = new BufferedWriter(new FileWriter("BST_result20.txt"));

			out_10.write("Below is BST insert 10 result\n");
			out_10.newLine();
			for (i = 0; i < 10; i++) {
				out_10.write(result1[i] + "");
				out_10.newLine();
			}
			out_20.write("Below is BST intsert 20 result\n");
			out_20.newLine();
			for (i = 0; i < 20; i++) {
				out_20.write(result2[i] + "");
				out_20.newLine();
			}
			
			out_10.write("------------------------------------------------");
			out_20.write("------------------------------------------------");
			
			out_10.write("Below is BST median_insert 10 result\n");
			out_10.newLine();
			for (i = 0; i < 10; i++) {
				out_10.write(result3[i] + "");
				out_10.newLine();
			}
			out_20.write("Below is BST median_insert  20 result\n");
			out_20.newLine();
			for (i = 0; i < 20; i++) {
				out_20.write(result4[i] + "");
				out_20.newLine();
			}
		
			out_10.write("------------------------------------------------");
			out_20.write("------------------------------------------------");

			out_10.close();
			out_20.close();

		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}

	}

	
}
