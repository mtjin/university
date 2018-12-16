
import java.beans.beancontext.BeanContextChildSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.tools.DiagnosticCollector;



public class Main {
	public static void main(String[] args) {
		String line1 = "";
		String line2 = "";
		int i = 0, j = 0;
		int size = 0;
		int size2 = 0;
		int vertexNum1 = 0; // 문자열 저장
		int vertexNum2 = 0;
		
		
		int weight = 0;
		final int INF = Integer.MAX_VALUE; // 무한대

		int[][] graphMatrix1 = null; // 인접행렬저장 배열
		int[][] graphMatrix2 = null;

		// 파일 읽어와서 정수형배열에 저장
		try {
			File file1 = new File("graph_sample_prim_kruskal.txt");
			File file2 = new File("graph_sample_dijkstra.txt");
			FileReader filereader1 = new FileReader(file1);
			FileReader filereader2 = new FileReader(file2);
			BufferedReader bufReader1 = new BufferedReader(filereader1);
			BufferedReader bufReader2 = new BufferedReader(filereader2);
			// 입력 텍스트 파일에서 개수 읽어와 저장
			line1 = bufReader1.readLine(); // 개수 읽어옴
			line2 = bufReader2.readLine(); // 개수 읽어옴

			vertexNum1 = Integer.parseInt(line1); // vertex 개수저장
			vertexNum2 = Integer.parseInt(line2); // vertex 개수저장

			graphMatrix1 = new int[vertexNum1][vertexNum1]; // 인접행렬 생성
			graphMatrix2 = new int[vertexNum2][vertexNum2];

			// 초기화(자기자신은 0, 나머지는 무한대로 초기화함, 무한대는 가중치가 없다는걸 뜻함 즉 연결이 안되있다.
			for (i = 0; i < vertexNum1; i++) {
				for (j = 0; j < vertexNum1; j++) {
					if (i == j) {
						graphMatrix1[i][j] = 0;
					} else {
						graphMatrix1[i][j] = INF;
					}
				}
			}
			i = 0;
			j = 0;
			for (i = 0; i < vertexNum2; i++) {
				for (j = 0; j < vertexNum2; j++) {
					if (i == j) {
						graphMatrix2[i][j] = 0;
					} else {
						graphMatrix2[i][j] = 0;
					}
				}
			}
			i = 0;
			j = 0;

			// 입력 텍스트 파일에서 값 읽어와 저장
			while ((line1 = bufReader1.readLine()) != null) {

				StringTokenizer str = new StringTokenizer(line1, " ");
				i = Integer.parseInt(str.nextToken());
				j = Integer.parseInt(str.nextToken());
				weight = Integer.parseInt(str.nextToken());
				graphMatrix1[i][j] = weight;
				graphMatrix1[j][i] = weight;

				size++;
				i = 0;
				j = 0;
				weight = 0;
			}

			// 입력 텍스트 파일에서 값 읽어와 저장
			while ((line2 = bufReader2.readLine()) != null) {

				StringTokenizer str = new StringTokenizer(line2, " ");
				i = Integer.parseInt(str.nextToken());
				j = Integer.parseInt(str.nextToken());
				weight = Integer.parseInt(str.nextToken());
				graphMatrix2[i][j] = weight;
				graphMatrix2[j][i] = weight;

				size2++;
				i = 0;
				j = 0;
				weight = 0;
			}

		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("===========PRIM==============\n");
		Prim prim = new Prim(vertexNum1);
		prim.run(graphMatrix1, vertexNum1);
		
		System.out.println("===========DIJKSTRA===========\n");
		
		Dijkstra d = new Dijkstra();
		d.run(graphMatrix2, vertexNum2);
		
	}
}
