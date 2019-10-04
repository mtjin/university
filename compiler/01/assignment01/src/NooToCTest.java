import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by eschough on 2019-09-02.
 */
public class NooToCTest {
    public static void main(String[] args) {
    	String s = "";
		NooToC ntc = null;
    	
		String filePath = "test1.noo";
    	try (FileInputStream fstream= new FileInputStream(filePath);){		
    		byte[] rb = new byte[fstream.available()];
    		while(fstream.read(rb) != -1) {}
    		fstream.close();
    		s = new String(rb);
    		
    		System.out.println("input : " + s);
    	}catch(Exception e) {
    		e.getStackTrace();
    	}
    	
    	try (FileWriter fw = new FileWriter("test.c");){
    		ntc = new NooToC(fw,s);
			fw.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
}
