
public class Country {

	public String name, language;
	
	public Country(String n, String l) {
		name= n; language = l;
	}
	
	public String toString() {
		return "("+name+","+language+")";
		
	}
}
