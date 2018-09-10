
public class mySection implements Section{

	String id;
	public mySection(String section) {
		this.id= section;
	}
	public String getId() {
		return id;
	}
	public String toString() {
		return ""+id;
	}
}
