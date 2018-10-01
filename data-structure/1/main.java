public class main {
	public static void main(String []args) {
	myStudent student= new myStudent("김근영",true,24);
	student.updateTranscript(new mySection("논리회로"), new myGrade("B+"));
	student.updateTranscript(new mySection("자료구조"), new myGrade("A"));
	System.out.println(student.getName());
	student.printTranscript();
	}
}
