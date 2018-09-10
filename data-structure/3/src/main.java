import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArraySet set= new ArraySet();
		int number;
		String content;
		while(true) {
		System.out.println("1. 추가\n2. 삭제\n3. 내용 출력\n4. 사이즈 출력\n5. 해당 내용이 있는지 확인\n6. 종료");
		Scanner sc= new Scanner(System.in);
		number = sc.nextInt();
		sc.nextLine();
		if(number==1) {                     
			System.out.println("추가하고 싶은 내용 :");
			content = sc.next();
			if(set.add(content)) {
				System.out.println("정상적으로 추가 되었습니다");
			}else {
				System.out.println("내용이 중복 되었습니다");
			}
		}else if(number== 2) {
			System.out.println("삭제하고 싶은 내용");
			content = sc.next();
			set.remove(content);
		}else if(number==3) {
			print(set);
		}else if(number ==4) {
			System.out.println(set.size());
		}else if(number==5) {
			System.out.println("확인하고 싶은 내용 :");
			content = sc.next();
			if(set.contains(content)) {
				System.out.println("해당 내용이 존재합니다");
			}else {
				System.out.println("해당 내용이 존재하지 않습니다");
			}
		}else if(number == 6) {
			System.out.println("종료되었습니다");
			break;
		}else {
			System.out.println("없는 옵션입니다 다시입력하십시오");
		}
		}
	}

	public static void print(ArraySet set) {                 //데이터 내용반환함수
		System.out.println("\n 가방 안에 들어있는 내용입니다");
		System.out.print(set.getFrist());
		for(int i=1; i<set.size(); i++) {
			System.out.print("," + set.getNext());
		}
		System.out.println();
		
	}
}
