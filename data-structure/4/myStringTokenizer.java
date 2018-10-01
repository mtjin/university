
public class myStringTokenizer {
	
	private String[] words;       //토큰을 저장할 공간
	private String line= "";      //전체문장
	private String token ="";     //토큰을 나눌 기준
	private int index;            //words배열에 값에 접근하기위한  포인터역할
	
	public myStringTokenizer(String line, String token) {
		this.line =line;
		this.token= token;
		this.words= makeWords(line, token);     //함수를써서 토큰으로 나눠진 배열을 받아옴
		this.index= 0;
	}
	 public boolean hasMoreTokens() {
		 if(words.length == index) return false;   //words의 배열의 길이와 토큰을 가리키는 포인터의 값이 같아지면 더불러올게 없는것이므로 false를 반환
		 else return true;             
	 }
	 public String nextToken() {
		 return words[index++];                //토큰값 반환
		 }
	 public String[] makeWords(String line, String token) { 
		 String[] tmp= null;
		 int split_index= 0;  //토큰을 담은 배열(tmp)의 인덱스를 가리키는 포인터역활
		 int split_length= 1; //토큰의 총개수
		 int begin_index= 0;  //토큰찾는데 처음단어를 가리키는 인덱스
		 int last_index= 0;  //토큰찾는데 마지막 단어를 가리키는인덱스
		
		 for(int i=0; i<line.length(); i++) {
			 if(line.charAt(i)==' ')         //공백만나면 쪼개진길이 1증가(문장들  다실행)
			 {
				 split_length++;
			 }
			 }
		 tmp= new String[split_length];
		 for(int i=0; i<line.length(); i++)
		 {
			 if(split_length== (split_index+1))            //마지막배열과 인덱스마지막꼐같을떄
			 {
				 tmp[split_index]= line.substring(begin_index, line.length());        //마지막에 싹다집어넣음
				 break; 
			 }
			 else if(line.charAt(i)==' ')          //공백만난경우
			 {
				 //채워넣기
				 last_index= i;                 //공백만난순간의 i를 last_index에 넣어줌
				 tmp[split_index++]= line.substring(begin_index, last_index);  //공백(" ")찾기 이전까지의 토큰을 tmp에 넣어줌.
				 begin_index=last_index+1;      //비긴인덱스는 마지막찾은 인덱스에서 1증가시켜줌
				 
			 }
		 }
		 return tmp;
	 }

}
