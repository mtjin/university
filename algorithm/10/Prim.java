import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Prim {
	int heap_size;
	int vertexNum;
	int graphMatrix1[][];
	final int INF = Integer.MAX_VALUE;

	
	public Prim(int vertices) {
		this.vertexNum = vertices;
	}


	//가장 작은 키 가진 vertex 찾음
	public int minimunKey(int[] key, boolean[] mstSet) {

	
		int minValue = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int v = 0; v < vertexNum; v++) {
			if (!mstSet[v] && key[v] <= minValue) {
				minValue = key[v];
				minIndex = v;
			}
		}

		return minIndex;
	}

	//부모에 저장된 vertex값 출력
	public void printPrim(int parent[], int n, int[][] graph) {
		System.out.println("start  end  cost");
		try {

			System.out.println("================================================");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output(Prim).txt"));

			out1.write("start | end | cost\n");
			out1.newLine();

			for (int i = 1; i < vertexNum; i++) {
				System.out.println(parent[i] + "      " + i + "     " + graph[i][parent[i]]);
				out1.write(parent[i] + " 	   " + i + " 	   " + graph[i][parent[i]]);
				out1.newLine();
			}
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}
		
	}

	
	public void makePrim(int[][] graph) {
		int parent[] = new int[vertexNum];
		int key[] = new int[vertexNum];
		boolean mstSet[] = new boolean[vertexNum];

		for (int i = 0; i < vertexNum; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		key[0] = 0;

		parent[0] = -1;

		for (int count = 0; count < vertexNum - 1; count++) {

			int u = minimunKey(key, mstSet);

			mstSet[u] = true;

			//키값과 부모 인덱스를 인접행렬로부터 뽑은 vertex로 바꿔줌
			for (int v = 0; v < vertexNum; v++) {
				if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {

					parent[v] = u;
					key[v] = graph[u][v];
				}
			}
		}
		printPrim(parent, vertexNum, graph);
	}

	public void run(int[][] G, int num) {
		graphMatrix1 = new int[num][num];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (i == j) {
					graphMatrix1[i][j] = 0;
				} else {
					graphMatrix1[i][j] = INF;
				}
			}
		}

		for (int i = 0; i < G.length; i++) {
			for (int j = 0; j < G[i].length; j++) {
				graphMatrix1[i][j] = G[i][j];
			}
		}

		Prim pa = new Prim(num);

		pa.makePrim(graphMatrix1);
	}
	
	// MIN HEAP SORT (max heap과 반대로 heapify메소드에서 자식노드가 더 작은값이면 부모노드와 스왑해주면됨
		public void minHeapSort(int[] arr) {
			int tmp;
			buildMinHeap(arr);
			for (int i = arr.length - 1; i >= 1; i--) {
				// swap
				tmp = arr[0];
				arr[0] = arr[i];
				arr[i] = tmp;

				heap_size -= 1;
				minHeapify(arr, 0);
			}

		}

		public void buildMinHeap(int[] arr) {
			heap_size = arr.length - 1;
			for (int i = arr.length / 2 - 1; i >= 0; i--) {
				minHeapify(arr, i);
			}

		}

		public void minHeapify(int[] arr, int i) {
			int smaller;
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			if (right <= heap_size) {
				if (arr[left] < arr[right]) {
					smaller = left;
				} else {
					smaller = right;
				}
			} else if (left <= heap_size) {
				smaller = left;
			} else {
				return;
			}
			if (arr[smaller] < arr[i]) {
				swap(arr, i, smaller);
				minHeapify(arr, smaller);
			}
		}

		// swap
		public void swap(int[] arr, int i, int largest) {
			int tmp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = tmp;
		}
}