import java.util.*;

public class myStudent implements Student{

	private String name;
	private boolean male;
	private int yob;
	Transcript transcript= new Transcript();     //transcript를 이 클래스에서 쓰니깐 생성해줌
	public myStudent() {
		
	}
	public myStudent(String name, boolean male, int yob) {
		this.name= name;
		this.male= male;
		this.yob= yob;
		
	}
	 public String getName() {
		 return name;
	 }

	 public void printTranscript() {        //학번,등급출력
		 System.out.println(transcript);
	 }
	
		@Override
		public void updateTranscript(mySection section, myGrade grade) {  //학번,등급 추가
			// TODO Auto-generated method stub
			transcript.add(section, grade);             
			}
		private class Transcript {
			Map map= new HashMap();  //학번, 등급
			void add(mySection mySection, myGrade myGrade) {
				map.put(mySection, myGrade);
				}
			public  String toString() {
				return map.toString();
				}
			 
		}
	 
	 

}
