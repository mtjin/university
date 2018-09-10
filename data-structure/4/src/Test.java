import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Test {
	
	public void run(String file) {
		
		int words=0, chars =0;
		try {
			BufferedReader in= new BufferedReader(new FileReader("test.txt"));
			String line =in.readLine();
			while(line != null) {
				myStringTokenizer parser= new myStringTokenizer(line," ");
				while(parser.hasMoreTokens()) {     //불러올 토큰이 더이상 없을때까지 반복
					++words;
					String word = parser.nextToken();
					chars += word.length();
				}
				line = in.readLine();
			}
			in.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("words:"+ words);
		System.out.println("characters :"+ chars);
	}

}
