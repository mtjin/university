import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = ""; // 0은 100개txt 1은 1000개txt 2는 10000개txt
		String line2 = "";
		int i = 0;
		
		int Merge_input100[] = new int[100];
		int Merge_input1000[] = new int[1000];
		
		int Merge_input100_divided_4[] = new int[100];
		int Merge_input1000_divided_4[] = new int[1000];
		
		int Merge_input100_divided_16[] = new int[100];
		int Merge_input1000_divided_16[] = new int[1000];
		
		int Quick_input100[] = new int[100];
		int Quick_input1000[] = new int[1000];
		
		int RandomQucik_input100[] = new int[100];
		int RandomQucik_input1000[] = new int[1000];

		try {
			File file_100 = new File("test_100.txt");
			File file_1000 = new File("test_1000.txt");
			FileReader filereader = new FileReader(file_100);
			BufferedReader bufReader = new BufferedReader(filereader);
			FileReader filereader2 = new FileReader(file_1000);
			BufferedReader bufReader2 = new BufferedReader(filereader2);

			while ((line = bufReader.readLine()) != null) {
				// System.out.println(line);

				Merge_input100[i] = Integer.parseInt(line);
				Merge_input100_divided_4[i] = Integer.parseInt(line);
				Merge_input100_divided_16[i] = Integer.parseInt(line);
				Quick_input100[i] = Integer.parseInt(line);
				RandomQucik_input100[i++] = Integer.parseInt(line);
				// size++; // 읽어온 개수
			}
			i = 0;
			while ((line2 = bufReader2.readLine()) != null) {
				// System.out.println(line);
				Merge_input1000[i] = Integer.parseInt(line2);
				Merge_input1000_divided_4[i] = Integer.parseInt(line2);
				Merge_input1000_divided_16[i] = Integer.parseInt(line2);
				Quick_input1000[i] = Integer.parseInt(line2);
				RandomQucik_input1000[i++] = Integer.parseInt(line2);
				// size2++; // 읽어온 개수
			}

		} catch (IOException e) {
			System.out.println(e);
		}

		Quick_Sort quick = new Quick_Sort();
		Merge_Sort merge = new Merge_Sort();
		
		// 일반적인 merge sort(과제조건: 병합을 삽입정렬도 대체함)
		long start_time = System.nanoTime();
		System.out.println("start 100 Merge sort");
		merge.sort(Merge_input100, 0, Merge_input100.length - 1);
		System.out.println("start 1000 Merge sort");
		merge.sort(Merge_input1000, 0, Merge_input1000.length - 1);
		
		long end_time = System.nanoTime(); // 퀵소트(일반) 종료시간
		long time_difference = end_time - start_time; // 퀵소트(일반) 걸린시간
		System.out.println("일반적인 merge sort 걸린 시간:" + time_difference);
		
		// 쪼개는 최대 개수:4개  merge sort
		start_time = System.nanoTime();
		System.out.println("start 100 Merge sort(최대 쪼개는 개수: 4개)");
		merge.divided_sort(Merge_input100_divided_4, 0, Merge_input100_divided_4.length - 1, 4);
		System.out.println("start 1000 Merge sort(최대 쪼개는 개수: 4개)");
		merge.divided_sort(Merge_input1000_divided_4, 0, Merge_input1000_divided_4.length - 1, 4);
		
		end_time = System.nanoTime(); // 퀵소트(일반) 종료시간
		time_difference = end_time - start_time; // 퀵소트(일반) 걸린시간
		System.out.println("쪼개는 최대 개수:4개  merge sort 걸린 시간:" + time_difference);
		
		// 쪼개는 최대 개수 :16개  merge sort 
		start_time = System.nanoTime();
		System.out.println("start 100 Merge sort(최대 쪼개는 개수 : 16개)");
		merge.divided_sort(Merge_input100_divided_16, 0, Merge_input100_divided_16.length - 1, 16);
		System.out.println("start 1000 Merge sort(최대 쪼개는 개수: 16 개)");
		merge.divided_sort(Merge_input1000_divided_16, 0, Merge_input1000_divided_16.length - 1, 16);
		
		end_time = System.nanoTime(); // 퀵소트(일반) 종료시간
		time_difference = end_time - start_time; // 퀵소트(일반) 걸린시간
		System.out.println("쪼개는 최대 개수 :16개  merge sort  걸린 시간:" + time_difference);
		
		// 일반적인 quick sort
		start_time = System.nanoTime(); // 소팅 시작시간
		System.out.println("start 100 Quick sort");
		quick.sort(Quick_input100, 0, Quick_input100.length - 1);
		System.out.println("start 1000 Quick sort");
		quick.sort(Quick_input1000, 0, Quick_input1000.length - 1);

		end_time = System.nanoTime(); // 소팅 종료시간
		time_difference = end_time - start_time; // 소팅 걸린시간
		System.out.println("퀵소트 pivot을 맨 마지막값으로 한 경우 정렬하는데 걸린 시간:" + time_difference);

		// Random pivot quick sort
		start_time = System.nanoTime(); // 소팅 시작시간
		System.out.println("start 100 Random pivot Quick sort");
		quick.sort(RandomQucik_input100, 0, RandomQucik_input100.length - 1);
		System.out.println("start 1000 Random pivot Quick sort");
		quick.sort(RandomQucik_input1000, 0, RandomQucik_input1000.length - 1);
		end_time = System.nanoTime(); // 소팅 종료시간
		time_difference = end_time - start_time; // 소팅 걸린시간
		System.out.println("퀵소트 pivot을 4번째 값으로 한 경우 정렬하는데 걸린시간:" + time_difference);
		
		//파일 출력
		try {
			BufferedWriter out_100 = new BufferedWriter(new FileWriter("result100.txt"));
			BufferedWriter out_1000 = new BufferedWriter(new FileWriter("result1000.txt"));

			// merge sort 출력
			out_100.write("Below is Merge_Sort 100 result\n");
			for (i = 0; i < Merge_input100.length; i++) {
				out_100.write(Merge_input100[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Merge_Sort 1000 result\n");
			for (i = 0; i < Merge_input1000.length; i++) {
				out_1000.write(Merge_input1000[i] + "");
				out_1000.newLine();
			}
			
			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// merge sort(최대 쪼개는 개수: 4개) 출력
			out_100.write("Below is Merge_Sort 100_4 result(최대 쪼개는 개수: 4개)\n");
			for (i = 0; i < Merge_input100_divided_4.length; i++) {
				out_100.write(Merge_input100_divided_4[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Merge_Sort 1000_4 result(최대 쪼개는 개수: 4개)\n");
			for (i = 0; i < Merge_input1000_divided_4.length; i++) {
				out_1000.write(Merge_input1000_divided_4[i] + "");
				out_1000.newLine();
			}

			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// merge sort(최대 쪼개는 개수: 16개) 출력
			out_100.write("Below is Merge_Sort 100_16 result(최대 쪼개는 개수: 16개)\n");
			for (i = 0; i < Merge_input100_divided_16.length; i++) {
				out_100.write(Merge_input100_divided_16[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Merge_Sort 1000_16 result(최대 쪼개는 개수: 16개)\n");
			for (i = 0; i < Merge_input1000_divided_16.length; i++) {
				out_1000.write(Merge_input1000_divided_16[i] + "");
				out_1000.newLine();
			}
			
			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// quick sort 출력
			out_100.write("Below is Qucik_Sort 100result\n");
			for (i = 0; i < Quick_input100.length; i++) {
				out_100.write(Quick_input100[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Qucik_Sort 1000 result\n");
			for (i = 0; i < Quick_input1000.length; i++) {
				out_1000.write(Quick_input1000[i] + "");
				out_1000.newLine();
			}

			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// random quick sort 출력
			out_100.write("Below is Random pivot Qucik_Sort 100 result\n");
			for (i = 0; i < RandomQucik_input100.length; i++) {
				out_100.write(RandomQucik_input100[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Random pivot Qucik_Sort 1000 result\n");
			for (i = 0; i < RandomQucik_input1000.length; i++) {
				out_1000.write(RandomQucik_input1000[i] + "");
				out_1000.newLine();
			}

			out_100.close();
			out_1000.close();

		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}

	}

}
