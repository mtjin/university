//201404377_진승언
import ast.IntNode;
import ast.ListNode;
import ast.Node;
import compile.TreeFactory;
public class Ex04 {

	
			public static int max(Node node) {  
				//최대값을 리턴하도록 작성  
				//value와 next 값 중 큰 값을 리턴
	    
				if(node instanceof IntNode)   //IntNode일 경우   (instanceof 를 이용해서 형변환이 가능함과 동시에 IntNode인지 ListNode인지 구분 가능함)
				{               
					IntNode int_node = (IntNode)node;  //IntNode로 형변환 해줌
					
					if(int_node.getNext() == null) 
					{          //IntNode는 다음 가리키는 노드가없을떄(next) 단말노드이므로 값만 반환시키면된다.             
						return (int_node).value;
					}
					else    //다음 노드가 가리키는게 있을때 (null이 아닐떄)
					{
						if(max(int_node.getNext()) > (int_node).value) { //재귀를 이용해서 계속해서 다음노드와 현재노드값을 비교하게함, 재귀에서 더 큰 값이 있으면 그 값으로 재귀해주면됨 
							return max(int_node.getNext());
						}
						else                 //위와 반대
						{
							return (int_node).value;
						}
					}
				}
				else if(node instanceof ListNode)   //ListNode일 경우
				{   
					ListNode list_node = (ListNode)node;   //ListNode로 형변환 해줌
					
					if(list_node.getNext() == null)        //다음노드가(next) 없을 경우 value만 확인하면되니깐 밑에 처럼 max로 value를 넘겨줌 
					{
						return max((list_node).value);
					}
					else {             //다음노드(next)가 있는 경우
						if(max(list_node.getNext()) > max((list_node).value)) //ListNode는 value와 next모두 노드를 가질 수 있으므로 두개를 재귀로 돌려주고 next재귀로 돌린것에 더 큰 값이 있다면 그 재귀를 반환해준다.
						{
							return max(list_node.getNext());
						}
						else           //위의 반대
						{
							return max((list_node).value);   
						}
					}
					
				}
			   
				return 0; //에러
			}
			
			
			public static int sum(Node node) {  //피보나치수열처럼
				//노드 value의 총합을 반환
				//value와 next의 총 합을 리턴하면됨 
			
				if(node instanceof IntNode)    //IntNode 일 경우
				{
					IntNode int_node = (IntNode)node; //형변환해줌
					
					if(int_node.getNext() == null)   //다음 노드가없으면 
					{
						return int_node.value;  //ListNode에서 next가 null이면 최종 값이므로 그것의 값을 반환해주면됨.
					}
					else    //다음 가리키는 노드가 있을 경우
					{
						return int_node.value + sum(int_node.getNext());  
					}
				}
				else if(node instanceof ListNode)  //ListNode일  경우
				{  
					ListNode list_node = (ListNode)node;  //형변환해줌
					
					if(list_node.getNext() == null)    //다음 노드가 없으면
					{
						return sum(list_node.value);  //ListNode의 value를 재귀로 돌려주면됨
					}
					else    //다음 노드가 있으면
					{
						return sum(list_node.value) + sum(list_node.getNext());  //위에거에다가 다음노드를재귀로돌려서 더해주면됨
					}
					
				}
				
				return 0; 	
			}
			public static void main(String...args) {   
					Node node = TreeFactory.createtTree("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2 ( ) )"); 
					//결과 출력
					Node node2 = TreeFactory.createtTree("(3 ( 5 2 3) -378 )" );
					System.out.println("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2 ( ) ) 일 경우");
					System.out.println("최대값: " + max(node));  //최대값
					System.out.println("IntNode들의 총합: " + sum(node));  //합
					//또 다른 샘플
					System.out.println("\n다른 경우 : (3 ( 5 2 3) -378 ) 일 경우");
					System.out.println("최대값: " + max(node2));
					System.out.println("IntNode들의 총합: " + sum(node2)); 
			
			}
}