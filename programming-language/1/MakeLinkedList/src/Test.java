//201404377Áø½Â¾ð
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Test {
	public static void main(String[] args) throws FileNotFoundException {   
		// TODO Auto-generated method stub  
		RecursionLinkedList list = new RecursionLinkedList();   
		FileReader fr;  
		try {   
		    fr = new FileReader("C:\\javalec\\workspace\\MakeLinkedList\\src\\hw01.txt");   
			BufferedReader br = new BufferedReader(fr);   
			String inputString = br.readLine();   
			for(int i = 0; i < inputString.length(); i++)   
				list.add(inputString.charAt(i));   } catch (IOException e) {  
					// TODO Auto-generated catch block   
					e.printStackTrace(); 
					}   
		System. out .println(list.toString());   
		list.add(3, 'b'); System. out .println(list.toString()); 
        list.reverse();  System. out .println(list.toString());  
	}
}

