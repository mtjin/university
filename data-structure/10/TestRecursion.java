import java.util.Scanner;

public class TestRecursion {

	// TODO Auto-generated method stub
			public void run() {
				
				int number;
				Quicksort quick= new Quicksort(100);
				while(true) {
					System.out.println("1.[Recursive] add\n2.[Recursive] sort\n3.[Recursive] print \n4. 종료");
					Scanner sc= new Scanner(System.in);
					number = sc.nextInt();
					//
					if(number == 1)
					{                     
						System.out.println("추가하고 싶은 내용을 입력하세요 :");
						
						int n = sc.nextInt();
						quick.add(n);  //데이터 추가함
						System.out.println("["+n+"]가 추가 되었습니다.");
					}
					else if(number == 2) 
					{	
						quick.sorting();
					}
					else if(number == 3) 
					{
						for(int i=0; i<quick.size(); i++) {
							if(i==0) {
								System.out.print("["+quick.getFirst()+"]");
							}else {
								System.out.print("["+quick.getNext()+"]");
							}
							
						}
						System.out.println();
					}
					else if(number == 4) 
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
