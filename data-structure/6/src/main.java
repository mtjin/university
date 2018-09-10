import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int number;
		IntNodeStack node_stack= new IntNodeStack();
		while(true) {
			System.out.println("1. 스택에 내용 추가\n2. 스택에 마지막으로 들어온 내용 삭제\n3. 스택에 처음 들어온 내용 삭제\n4. 사이즈 출력\n5. 내용 출력\n6. 종료");
			Scanner sc= new Scanner(System.in);
			number = sc.nextInt();
			//
			if(number == 1)
			{                     
				System.out.println("추가하고 싶은 숫자 입력");
				
				int n = sc.nextInt();
				node_stack.push(n);  //데이터 추가함
			}
			else if(number == 2) 
			{	
				
				System.out.println(node_stack.pop()+"삭제되었습니다");
			}
			else if(number == 3) 
			{
				System.out.println(node_stack.popBottom()+"삭제되었습니다");
			}
			else if(number == 4) 
			{
				System.out.println("사이즈는"+node_stack.size()+"입니다");
			}
			else if(number == 5) {
				System.out.println("현재노드의 데이터는"+node_stack.checkNode()+"\n총"+node_stack.size()+"개의 노드가 존재합니다");
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


