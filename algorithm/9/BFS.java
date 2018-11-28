import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	final int INF = Integer.MAX_VALUE;
	
	void BFSrun(int[][] G, Vertex[] vertex, int startVertex) {
		vertex[startVertex].color = "GRAY"; //시작 vertex 값 설정
		vertex[startVertex].parent = null;
		vertex[startVertex].distance = 0;
		
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(vertex[startVertex]);  //시작인덱스 큐에 넣어줌
		while(!q.isEmpty()) {	//큐가 빌때까지 반복
			Vertex u = q.poll();	//u에 큐에서 뺴온값 저장
			for(int column = 0 ; column < vertex.length; column++) {	//큐에서 뺴온 vertex의 인접한 vertex들 값 세팅 하고 큐에 넣어줌(모든 vertex
				Vertex v = vertex[column];	
				if(isAdj(u, v, G) && v.color.equals("WHITE") && (v.distance == INF)) {	//인접하고 색이 흰색이고(아직탐색안한) 탐색하지않은(INF) vertex인 경우
					v.color = "GRAY";	//회색으로 변경
					v.distance = u.distance + 1;	//탐색시간 저장
					v.parent = u;	//인접 노드들끼리 연결해줌
					q.offer(v);	//새로 탐색한 vertex를 큐에 삽입
				}
			}
			
			u.color ="BLACK"; //완료한 vertex 색 black으로 변경
		}
	}
	
	boolean isAdj(Vertex u, Vertex v, int[][] graphMatrix) {
		int tmp = graphMatrix[u.id][v.id];	//v와 u vertex가 인접하면 1, 아니면 0
		if(tmp == 0) {  //인접 아닌 경우 false반환
			return false;
		}
		else {		//인접 vertex인 경우 true 반환
			return true;	
		}
	}
}
