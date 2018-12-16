import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
	
	
	static String result = "";
	public static final int NO_PARENT = -1;
	int flag = 0;
	int heap_size = 0;
	
	public  void dijkstra(int[][] adjacentMatrix, int startVertex) {
		int nVertices = adjacentMatrix[0].length;

		//가장 짧은 distance 담을 배열 생성
		int[] shortestDistances = new int[nVertices];
		//해당 vertex가 추가됬는지
		boolean[] added = new boolean[nVertices]; 

		//초기화
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}

		shortestDistances[startVertex] = 0;
		int[] parents = new int[nVertices];
		
		//시작 vertex , 부모없는
		parents[startVertex] = NO_PARENT;

		//가장 짧은 경로 find
		for (int i = 1; i < nVertices; i++) {

			// Pick the minimum distance vertex
			// from the set of vertices not yet
			// processed. nearestVertex is
			// always equal to startNode in
			// first iteration.
			int nearVertex = -1;
			int shortestDistance = Integer.MAX_VALUE;
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
					nearVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}

			//한 vertex담음
			added[nearVertex] = true;

			//인접 vertex로 dist값 갱신
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				int edgeDistance = adjacentMatrix[nearVertex][vertexIndex];

				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
					parents[vertexIndex] = nearVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}

		printSolution(startVertex, shortestDistances, parents);
	}

	//print
	public  void printSolution(int startVertex, int[] distances, int[] parents) {
		int nVertices = distances.length;
		

		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			if (vertexIndex != startVertex) {
				
				
				if(flag == 0) {
					result += "0\t" + "distatnce : 0";
					System.out.print("0\t");
					System.out.println("distatnce : 0");
					result += "\n";
					flag = 1; 
				}
				//System.out.print("\n" + startVertex + " -> ");
				//System.out.print(vertexIndex + " \t ");
				printPath(vertexIndex, parents);
				System.out.print("\t");
				result += "\t";
				System.out.println("distatnce :" + distances[vertexIndex] );
				result += "distatnce :" + distances[vertexIndex] + "\n";
				
				//printPath(vertexIndex, parents);
			}
		}
		try {

			System.out.println("================================================");
			BufferedWriter out2 = new BufferedWriter(new FileWriter("Output(Dijkstra).txt"));

			out2.write(result);
			out2.newLine();

			out2.close();
		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}
	}

	//경로출력
	public  void printPath(int currentVertex, int[] parents) {

		//아직 실행 안한 vertex경우
		if (currentVertex == NO_PARENT) {
			return;
		}
		result += currentVertex + "";
		System.out.print(currentVertex );
		if(parents[currentVertex] != NO_PARENT) {
			System.out.print("<-");
			result += "<-";
		}
		printPath(parents[currentVertex], parents);
		
	}
	
	//내가추가
	public  void run(int[][] graphMatrix, int num) {
		int[][] g = new int[num][num];
		for(int i = 0; i< num; i++) {
			for(int j = 0; j <num; j++) {
				g[i][j] = graphMatrix[i][j]; 
			}
		}
		dijkstra(g, 0);
	}
	
	//MIN HEAP SORT (max heap과 반대로 heapify메소드에서 자식노드가 더 작은값이면 부모노드와 스왑해주면됨
		public void minHeapSort(int[] arr) {
			int tmp;
			buildMinHeap(arr);
			for(int i =arr.length-1; i>=1; i--) {
				//swap
				tmp = arr[0];
				arr[0] = arr[i];
				arr[i] = tmp;
				
				heap_size -= 1;
				minHeapify(arr, 0);
			}
			
		}
		
		public void buildMinHeap(int[] arr) {
			heap_size = arr.length-1;
			for(int i=arr.length/2-1; i>=0; i--) {
				minHeapify(arr, i);
			}
			
		}
		
		public void minHeapify(int[] arr, int i) {
			int smaller;
			int left = 2*i+1;
			int right = 2*i+2;
			if(right<= heap_size) {
				if(arr[left] < arr[right]) {
					smaller = left;
				}
				else {
					smaller = right;
				}
			}
			else if(left <= heap_size) {
				smaller = left;
			}
			else {
				return;
			}
			if(arr[smaller] < arr[i]) {
				swap(arr, i, smaller);
				minHeapify(arr, smaller);
			}
		}
		
		//swap
		public void swap(int[] arr, int i , int largest) {
			int tmp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = tmp;
			
		}
}