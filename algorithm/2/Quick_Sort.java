
public class Quick_Sort {

	public void sort(int[] A, int low , int high) {
		if(low< high) {     //정렬할 범위가 2개이상 데이터라면
		int q = partition(A, low, high);  //피벗을 기준으로 2개의 리스트로 분할( 함수의 반한 값은 피벗의 위치이다)
		sort(A, low , q-1);   //low에서 피벗위치 앞까지 대상으로 순환호출
		sort(A, q+1, high);   //피벗위치부터 high까지를 대상으로 순환호출
		}
	}
	
	public void random_sort(int[] A, int low , int high) {
		if(low< high) {     //정렬할 범위가 2개이상 데이터라면
		int q = randomize_partition(A, low, high);  //피벗을 기준으로 2개의 리스트로 분할( 함수의 반한 값은 피벗의 위치이다)
		sort(A, low , q-1);   //low에서 피벗위치 앞까지 대상으로 순환호출
		sort(A, q+1, high);   //피벗위치부터 high까지를 대상으로 순환호출
		}
	}
	
	
	public int partition(int []A, int low, int high ) {
		int pivot = A[high];  //피벗의 기준을 가장 마지막값으로
		int left = low -1;  //피벗보다 작은값 가리킬 포인터역할
		int right = 0;      //피벗보다 큰값을 가리킬 포인터역할 및 배열처음값 부터 차례대로 불러오는 포인터역할(for문)
		for(right = low; right <= high-1; right++) {
			if(A[right] <= pivot){  //피벗보다 값이 작으면
				left++;             //left 1증가
				swap(A, left, right);  //left와 right를 swap해줌
			}
		}
		swap(A, left+1, high); //left+1과(피벗기준보다 큰 값의 시작인덱스) high를(피벗) swap해줌
		return left + 1;
		
	}
	
	public int randomize_partition(int []A, int low, int high ) {
		int pivot = A[4];    //피벗의 기준을 4로 잡았다.
		int left = low -1;
		int right = 0;
		for(right = low; right <= high-1; right++) {
			if(A[right] <= pivot){
				left++;
				swap(A, left, right);
			}
		}
		swap(A, left+1, high);
		return left + 1;
	}
	
	private void swap(int[] elements, int left, int right) {
		
		int temp = elements[left];
		elements[left] = elements[right];
		elements[right] = temp;
	}
	
}
