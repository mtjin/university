import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
	String line = ""; 
	String line2 = "";
	int i = 0;
	
	int MaxHeap_input100[] = new int[100];
	int MaxHeap_input1000[] = new int[1000];
	
	int MinHeap_input100[] = new int[100];
	int MinHeap_input1000[] = new int[1000];
	
	int Counting_input100[] = new int[100];
	int Counting_input1000[] = new int[1000];

	//파일 읽어와서 정수형배열에 저장
	try {
		File file_100 = new File("test_100.txt");
		File file_1000 = new File("test_1000.txt");
		FileReader filereader = new FileReader(file_100);
		BufferedReader bufReader = new BufferedReader(filereader);
		FileReader filereader2 = new FileReader(file_1000);
		BufferedReader bufReader2 = new BufferedReader(filereader2);

		while ((line = bufReader.readLine()) != null) {
			

			MaxHeap_input100[i] = Integer.parseInt(line);
			MinHeap_input100[i] = Integer.parseInt(line);
			Counting_input100[i++] = Integer.parseInt(line);
			
		
		}
		i = 0;
		while ((line2 = bufReader2.readLine()) != null) {
		
			MaxHeap_input1000[i] = Integer.parseInt(line2);
			MinHeap_input1000[i] = Integer.parseInt(line2);
			Counting_input1000[i++] = Integer.parseInt(line2);
			
			
		}

	} catch (IOException e) {
		System.out.println(e);
	}
	
	
	Heap_Sort heap = new Heap_Sort();
	Counting_Sort counting = new Counting_Sort();

	//소팅시작
	System.out.println("start 100 MAX_HEAP sort");
	heap.maxHeapSort(MaxHeap_input100);
	System.out.println("start 1000 MAX_HEAP sort");
	heap.maxHeapSort(MaxHeap_input1000);
	System.out.println("start 100 MIN_HEAP sort");
	heap.minHeapSort(MinHeap_input100);
	System.out.println("start 1000 MIN_HEAP sort");
	heap.minHeapSort(MinHeap_input1000);
	System.out.println("start 100 COUNTING sort");
	counting.countingSort(Counting_input100);
	System.out.println("start 1000 COUNTING sort");
	counting.countingSort(Counting_input1000);
	
	
	//파일 출력
	try {
		BufferedWriter out_100 = new BufferedWriter(new FileWriter("result100.txt"));
		BufferedWriter out_1000 = new BufferedWriter(new FileWriter("result1000.txt"));

		// MAX heap sort 출력
		out_100.write("Below is MAX Heap_Sort 100 result\n");
		for (i = 0; i < MaxHeap_input100.length; i++) {
			out_100.write(MaxHeap_input100[i] + "");
			out_100.newLine();
		}
		out_1000.write("Below is MAX Sort 1000 result\n");
		for (i = 0; i < MaxHeap_input1000.length; i++) {
			out_1000.write(MaxHeap_input1000[i] + "");
			out_1000.newLine();
		}
		
		out_100.write("--------------------------------------------------\n");
		out_1000.write("--------------------------------------------------\n");
		
		//MIN heap sort 출력
		out_100.write("Below is MIN Heap_Sort 100 result\n");
		for (i = 0; i < MinHeap_input100.length; i++) {
			out_100.write(MinHeap_input100[i] + "");
			out_100.newLine();
		}
		out_1000.write("Below is MIN Heap_Sort 1000 result\n");
		for (i = 0; i < MinHeap_input1000.length; i++) {
			out_1000.write(MinHeap_input1000[i] + "");
			out_1000.newLine();
		}
		
		out_100.write("--------------------------------------------------\n");
		out_1000.write("--------------------------------------------------\n");
		
		//Coungting sort 출력
		out_100.write("Below is Counting_Sort 100 result\n");
		for (i = 0; i < Counting_input100.length; i++) {
			out_100.write(Counting_input100[i] + "");
			out_100.newLine();
		}
		out_1000.write("Below is Counting_Sort 1000 result\n");
		for (i = 0; i < Counting_input1000.length; i++) {
			out_1000.write(Counting_input1000[i] + "");
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
