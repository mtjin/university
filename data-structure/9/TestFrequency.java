import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestFrequency {

	String[] countries = {"KO", "DE", "ES", "FR", "DE", "ES", "DE"};
	List list = Arrays.asList(countries);
	public void run() {
		// TODO Auto-generated method stub
	
		frequency(list, "DE");
		frequency(list, "KO");
		frequency(list, "ES");
		frequency(list, "FR");
		
		
	}
	public int frequency(List list,Object object) {
		Iterator it = list.iterator();
		int count = 0;
		while(it.hasNext()) {
			
			if(it.next().equals(object)) {
				count++;
			}
		}
		System.out.println("frequency(list,"+ object+ "):"+count);
		return count;
	}
	
	

}
