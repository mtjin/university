import java.util.Scanner;

public class TestNodeSet {
	public void run() {
		int number;
		String content;
		NodeSet Node_Set= new NodeSet();
		while(true) {
			System.out.println("1. 추가\n2. 삭제\n3. 확인\n4. 내용이있는지확인\n5. 스왑\n6. 종료");
			Scanner sc= new Scanner(System.in);
			number = sc.nextInt();
			//
			if(number == 1)
			{                     
				System.out.println("추가할 데이터");
				content = sc.next();
				Node_Set.add(content);  //데이터 추가함
			}
			else if(number == 2) 
			{
				System.out.println("삭제할 데이터");
				content = sc.next();
				Node_Set.remove(content);
			}
			else if(number == 3) 
			{
				System.out.println("현재 노드의 데이터는" + Node_Set.checkNode());
				System.out.println("총" + Node_Set.size() +"개의 노드가 존재합니다");
				Node_Set.checkNode();
			}
			else if(number == 4) 
			{
				System.out.println("확인하고 싶은 데이터 :");
				content = sc.next();
				if(Node_Set.contains(content)) {
					System.out.println("해당 내용이 존재합니다");
				}else {
					System.out.println("해당 내용이 존재하지 않습니다");
				}
			}
			else if(number == 5) {
				System.out.println("스왑함수입니다\n바꿀노드 i를 입력해주세요");
				number = sc.nextInt();  // swap 함수 매개변수 int형이니깐 int 형으로 받음
				int i= number;
				System.out.println("바꿀노드 j를 입력해주세요");
				number = sc.nextInt(); 
				int j= number;
				if(Node_Set.swap(i,j)) {
					System.out.println(i+"번쨰와"+j+"번쨰의 토큰이 swap되었습니다");
				}else {
					System.out.println("유호하지 않은 값을 입력했습니다");
				}
			}
			else if(number == 6)
			{
				System.out.println("종료되었습니다");
				break;
			}
			else {
				System.out.println("없는 옵션입니다 다시입력하십시오");
			}
		}
	}
}
