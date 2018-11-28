
public class Vertex {
	
	final int INF = Integer.MAX_VALUE;
	int id;
    String color;
    int distance;	//도착한시간(탐색하는데 걸린시간)
    int f; //빠져나온 시간(DFS에서 쓰임)
    Vertex parent;
    
	Vertex(){
		color = "WHITE";
		distance = INF;
		parent = null;
	}
	
	Vertex(int id){
		this.id = id;
		color = "WHITE";
		distance = INF;
		parent = null;
	}
	
	public void init() {	//vertex 초기화
		color = "WHITE";
		distance = INF;
		parent = null;
	}
	
    
    
}
