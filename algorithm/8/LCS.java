
public class LCS {
	
	//Direction
	final int LEFT = 0;
	final int UP = 1;
	final int DIAGONAL = 2;
	
	StringBuffer result = new StringBuffer();	//LCS 결과값 저장
	
	//Longest Common Subsequence
	Arr[][] LCS_length(String x, String y) {
		int m = x.length()+1;  //x의 문자열 길이 (문자열의 길이보다 1더 필요하므로 1더함)
		int n = y.length()+1;  // ''''
		Arr[][] arr = new Arr[m][n];
		
		//배열 초기화 생성해줌
		for(int i = 0; i<m; i++) { 	
			for(int j =0; j<n; j++) {
				arr[i][j] = new Arr();
			}
		}
		
		//배열 행, 열 첫줄들을 0으로 초기화 (=행 0번 줄 0으로 초기화, 열 0번 줄 0으로 초기화)  
		for(int i =1; i<m; i++) {  
			arr[i][0].num = 0;
		}
		for(int j =0; j < n; j++) {
			arr[0][j].num = 0;
		}
		
		//LCS 마킹 
		for(int i =1; i<m; i++) {  // 위에서 0으로 초기화 한 위치를 제외한 칸들을 for문 돌려 마킹한다
			for(int j=1; j<n; j++) { // ''
				if(x.charAt(i-1) == y.charAt(j-1)) {	//x문자열의 i-1번쨰 문자와 y문자열의 j-1번째 문자가 같은 경우(문자열의 시작은 0부터 시작하므로 i,j보다 1작아야 매칭되므로 charAt에 -1해줌)
					arr[i][j].num = arr[i-1][j-1].num + 1;	//현재 i,j칸의 왼쪽대각선위칸([i-1,j-1])에 1을 더한 값을 저장
					arr[i][j].direction = DIAGONAL;		//방향에 대각선 마킹
				}
				else if(arr[i-1][j].num >= arr[i][j-1].num) {	//문자가 같지않고 현재위치의 위쪽값이 왼쪽칸 값보다 큰 경우
					arr[i][j].num = arr[i-1][j].num;	//현재위치의 위칸값 저장
					arr[i][j].direction =  UP;			//방향에 UP 마킹
				}
				else {		//문자가 같지않고 현재위치의 왼쪽칸 값이 위쪽칸 값보다 큰 경우
					arr[i][j].num = arr[i][j-1].num;	//현재위치의 왼쪽칸값 저장
					arr[i][j].direction = LEFT;		//방향에 LEFT 마킹
				}
			}
		}
		return arr;
	}
	
	StringBuffer LCS_print(Arr[][] b, String x, int i, int j ) {
		if(i==0 || j == 0) { 	//i,j가 0인 경우 탐색을 다한것이므로 지금까지 저장한 결과를 리턴
			return result;
		}
		if(b[i][j].direction == DIAGONAL) {		//DIAGONAL 마킹인 경우
			LCS_print(b,x, i-1, j-1);		//왼쪽대각선 위 칸으로 재귀
			System.out.print(x.charAt(i-1));
			result.append(x.charAt(i-1));	//해당 문자 결과값에 저장
		}
		else if(b[i][j].direction == UP) {	//UP 마킹인 경우
			LCS_print(b,x, i-1, j);		//	//왼쪽 칸으로 재귀
		}
		else {	//LEFT 마킹인 경우
			LCS_print(b,x, i, j-1); //오른쪽 칸으로 재귀
		}
		
		return result;
		
	}
	
	
	 

}
