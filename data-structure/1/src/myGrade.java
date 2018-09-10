
public class myGrade implements Grade {

	String value;
	public myGrade(String grade) {
		this.value= grade;
	}
	 public String getValue() {
		 return value;
	 }
	 public String toString() {
		 return ""+value;
	 }
}
