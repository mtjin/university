import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		String line = ""; 
		int i = 0, j = 0;
		int size = 0;
		StringBuffer result; //LCS Print결과 저장할 변수
		String data1[] = new String[10];  //문자열 저장
		int data2[] = new int[10];		   //숫자길이 저장
		//파일 읽어와서 정수형배열에 저장
		try {
			File file1 = new File("LCS_Data.txt");
			FileReader filereader = new FileReader(file1);
			BufferedReader bufReader = new BufferedReader(filereader);
			
			//파일 읽어와 저장
			while ((line = bufReader.readLine()) != null) {
					if((size%2) == 1) {
						data1[i] = line;  //문자열 저장
						i++;
					}
					if((size%2) == 0) {
						data2[j] = Integer.parseInt(line);  //길이 저장
						j++;
					}
					size++;
				}

		} catch (IOException e) {
			System.out.println(e);
		}
		
		
		
		LCS lcs = new LCS();
		//LCS 마킹
		Arr[][] arr = lcs.LCS_length(data1[0], data1[1]);
		//LCS 순회
		result = lcs.LCS_print(arr , data1[0], data2[0], data2[1]);
		
		
		//파일 출력
		try {
			
			System.out.println("\n================================================");
			System.out.println("START Write File");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output.txt"));
		
			out1.write("Below is  Output of LCS \n");
			out1.newLine();
			out1.write(result.toString());
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}
		
	
		
		
	}
}
