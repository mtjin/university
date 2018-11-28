import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		String line = "";
		int i = 0, j = 0;
		int size = 0;
		int vertexNum; // 문자열 저장
		int startVertex; // 시작 vertex
		Vertex[] vertex = null; // vertex배열
		int[][] graphMatrix = null; // 인접행렬저장 배열

		// 파일 읽어와서 정수형배열에 저장
		try {
			File file1 = new File("graph.txt");
			FileReader filereader = new FileReader(file1);
			BufferedReader bufReader = new BufferedReader(filereader);

			// 입력 텍스트 파일에서 개수 읽어와 저장
			line = bufReader.readLine(); // 개수 읽어옴
			vertexNum = Integer.parseInt(line); // vertex 개수저장
			graphMatrix = new int[vertexNum][vertexNum]; // 인접행렬 생성
			vertex = new Vertex[vertexNum]; // vertex 생성

			// 입력 텍스트 파일에서 인접행렬 값 읽어와 저장
			while ((line = bufReader.readLine()) != null) {

				vertex[i] = new Vertex(i); // 읽어온 순서대로의 값을 vertex의 id값을 설정 생성함

				StringTokenizer str = new StringTokenizer(line, " ");
				while (str.hasMoreTokens()) {
					graphMatrix[i][j] = Integer.parseInt(str.nextToken());
					j++;
				}
				size++;
				i++;
				j = 0;
			}

		} catch (IOException e) {
			System.out.println(e);
		}

		i = 0;

		// BFS 수행
		BFS bfs = new BFS();
		System.out.println("BFS의 시작 vertex를 입력하시오 (정해진  vertex크기보다 작게입력하시오)");
		Scanner sc = new Scanner(System.in);
		startVertex = sc.nextInt();

		bfs.BFSrun(graphMatrix, vertex, startVertex); // 맨 마지막 매개변수에 탐색을 시작할 vertex를 설정가능, 0으로하였다.
		for (i = 0; i < vertex.length; i++) {
			System.out.println("id값:" + vertex[i].id + "  탐색횟수: " + vertex[i].distance);
		}

		// BFS 결과 파일 출력
		try {

			System.out.println("================================================");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output(BFS).txt"));

			out1.write("Vertex_ID | Searching_Time\n");
			out1.newLine();
			for (i = 0; i < vertex.length; i++) {
				out1.write("" + vertex[i].id + "         |     " + vertex[i].distance);
				out1.newLine();
			}
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}

		// BFS에서 사용한 vertex 기본값으로 초기화
		for (i = 0; i < vertex.length; i++) {
			vertex[i].init();
		}

		// DFS 수행
		DFS dfs = new DFS();
		dfs.DFSrun(graphMatrix, vertex);
		System.out.println("DFS 결과( DFS는 0부터 탐색 고정)");
		for (i = 0; i < vertex.length; i++) {
			System.out.println("id값:" + vertex[i].id + " 도착시간: " + vertex[i].distance + " 빠져나온 시간: " + vertex[i].f);
		}

		// BFS 결과 파일 출력
		try {

			System.out.println("================================================");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output(DFS).txt"));

			out1.write("Vertex_ID | 도착시간 | 빠져나온시간\n");
			out1.newLine();
			for (i = 0; i < vertex.length; i++) {
				out1.write(" " + vertex[i].id + "        |     " + vertex[i].distance + "  |   " + vertex[i].f);
				out1.newLine();
			}
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}

	}
}
