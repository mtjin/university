import java.util.LinkedList;
import java.util.Queue;

public class DFS {
	final int INF = Integer.MAX_VALUE;
	int time;
	
	public void DFSrun(int[][] graphMatrix, Vertex[] vertex) {
		time = 0;	
		for(int i = 0; i <vertex.length; i++) {
			if(vertex[i].color.equals("WHITE")) {
				visit(graphMatrix, vertex[i], vertex);
			}
		}
	}
	
	public void visit(int[][] graphMatrix, Vertex u, Vertex[] vertex) {
		time = time + 1; //처음 도착시간은 1부터시작하고 1씩더해짐
		u.distance = time;
		u.color = "GRAY";
		Queue<Integer> q = new LinkedList<Integer>();
		int row = u.id;
		for(int column = 0 ; column < vertex.length; column++) { //모든 vertex와 비교(인접한지)
			
			if(graphMatrix[row][column] != INF && graphMatrix[row][column] != 0) {	//아직 탐색하지 않고 인접한 경우 큐에 인접한 vertex 삽입
				q.offer(column);	
			}
		}
		
		for(int i : q) {	//큐에 넣어진 vertex들  visit 재귀
			Vertex v = vertex[i];
			if(v.color.equals("WHITE")) {
				v.parent = u;
				visit(graphMatrix , v , vertex);
			}
		}
		
		//탐색완료한 vertex색을 black으로 변경 및 빠져나온 시간(완료시간) 저장
		u.color = "BLACK";	
		time = time + 1;
		u.f = time;
	}
	
	boolean isAdj(Vertex u, Vertex v, int[][] graphMatrix) {
		int tmp = graphMatrix[u.id][v.id];
		if(tmp == 0) {  // 0이면(인접아닌경우) false반환
			return false;
		}
		else {
			return true;
		}
	}
}
