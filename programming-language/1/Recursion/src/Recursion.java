//201404377진승언
public class Recursion {
	public static void main(String[] args) {   // TODO Auto-generated method stub 
		System. out .println( fibonacci (10));   
		System. out .println( recursiveAnt (10));  
	} 
	//public static int factorial(int n){  
	// if(n==1) return 1;  
	// return n* factorial (n-1); 
	//}  
	// recursion example     
	public static int fibonacci(int n){   
		if(n==0) return 0;
		if(n<3) return 1;
		else return ((fibonacci(n-2)+ fibonacci(n-1)));     // 채워서 사용, recursion 사용  }  
		}
	
	public static String recursiveAnt(int n){
		if(n==1) {
			return "1";
		} 
		else return makeResult(recursiveAnt(n-1));         // 채워서 사용, recursion 사용  }  
	}
	
	public static String makeResult(String previous){ 
		StringBuffer result= new StringBuffer(1000);  //
		char temp = 0;
		int count=0;
		for(int i=0; i<previous.length(); i++) {           // 채워서 사용, 반복문 최대 1회 사용 가능  }  
			if(previous.charAt(i)==temp || i==0) {         //처음오는 숫자이거나 같은 숫자가 온 경우
				count++;
				temp= previous.charAt(i);
				if(i==previous.length()-1)                 //같은숫자가 왔으며 마지막숫자인경우
					result.append(""+temp+count);
			}
			else{                                          //같지 않은 숫자가 온 경우
				result.append(""+temp+count);
				temp= previous.charAt(i);
				count=1;
				if(i==previous.length()-1) {               //같지 않은 숫자가왔으며 마지막숫자인경우
					temp= previous.charAt(i);
					count=1;
					result.append(""+temp+count);
				}
			}
		} //for문
		
		String result2= new String(result);
		return result2;
	}
}
