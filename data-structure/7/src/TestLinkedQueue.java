import java.util.Scanner;

public class TestLinkedQueue {

	public void run() {
		
		int number;
		LinkedQueue queue= new LinkedQueue();
		while(true) {
			System.out.println("1.[Queue] 내용 추가\n2.[Queue] 내용 N개 삭제\n3.[Queue] 내용 하나 삭제 \n4.[Queue] 사이즈 출력\n5.[Queue] 내용 출력\n6.[Queue] 첫번쨰 값 출력\n7.종료");
			Scanner sc= new Scanner(System.in);
			number = sc.nextInt();
			//
			if(number == 1)
			{                     
				System.out.println("추가하고 싶은 내용");
				
				String n = sc.next();
				queue.add(n);  //데이터 추가함
				System.out.println("["+n+"]가 추가 되었습니다.");
			}
			else if(number == 2) 
			{	
				System.out.println("삭제하고 싶은 갯수:");
				int n = sc.nextInt();
				for(int i = 0; i < n; i++ ) {
					  System.out.println("["+queue.peek()+"]가삭제되었습니다");
					  queue.remove();//개수만큼 삭제
				}
			}
			else if(number == 3) 
			{
				System.out.println("["+queue.peek()+"]가삭제되었습니다");
				queue.remove();
			}
			else if(number == 4) 
			{
				System.out.println("사이즈는"+queue.size()+"입니다");
			}
			else if(number == 5) {
				Node tmp = queue.getHead();
			
				for(int i=0; i<queue.size(); i++) {
					System.out.print(tmp.getNext());
					tmp.setNext(tmp.getNext().getNext());
				}
				System.out.println();
			}
			else if(number == 6) {
				System.out.println(queue.peek());
			}
			else if(number == 7)
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
