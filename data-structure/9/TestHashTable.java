import java.util.Scanner;

public class TestHashTable {

	HashTable hashtable= new HashTable(100, 0.75f);
	public void run() {
		while(true) {
			System.out.println("1. [Hash] put\n2. [Hash] get\n3. 종료");
			Scanner scan = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);
			Scanner scan3 = new Scanner(System.in);
			int choice = scan.nextInt();
			if(choice == 1) {
				System.out.println("key를 입력하세요:");
				Object key = scan.next();
				System.out.println("국가를 입력하세요:");
				String name = scan2.nextLine();
				System.out.println("언어를 입력하세요:");
				String language = scan3.nextLine();
				Country country= new Country( name , language);
				hashtable.put(key, country);
			}
			else if(choice == 2) {
				System.out.println("key를 입력하세요:");
				scan.nextLine();
				String key = scan.nextLine();
				Country tmp = (Country) hashtable.get(key);
				System.out.println("("+tmp.name+", "+tmp.language+")" );
			}
			else if(choice == 3) {
				System.out.println("종료되었습니다");
				break;
			}
			else {
				System.out.println("없는 옵션입니다 다시입력하세요");
			}
			
		}
	}
}
