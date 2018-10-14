
public class Counting_Sort {
	
	public void countingSort(int[] arr) {
		int i, j, max=0;
		int count[];
		int tmp[] = new int[arr.length];
		
		
		//arr배열 최댓값 max에 저장
		for(i=0; i<arr.length; i++ ) {
			if(arr[i] > max) {
				max = arr[i]; 
			}
		}
		
		//cout배열 생성
		count = new int[max+1];
		
		//카운팅할 배열 초기화
		for(i=0; i<=max; i++) {
			count[i] = 0;
		}
		
		//카운팅 저장(ex)arr배열에 1이 3개면 count[1]에 3저장 
		for(i=0; i<arr.length; i++) {
			count[arr[i]]++;
		}
		
		//count에 누적된 합저장
		//count[j]에 j보다 작거나 같은 원소의 총 개수 저장 (ex) count[1]은 count[0]과 count[1]의 합, count[2]는 누적합이저장된 count[1]과 count[2]의 합 
		for(j=1 ; j < count.length; j++) {
			count[j] = count[j] + count[j-1];
		}
		
		
		//위에서한 누적된 합을(count) 이용해 정렬
		for(j=arr.length-1; j >= 0; j--) {
			tmp[count[arr[j]]-1] = arr[j]; // arr값이 들어있는 count 누적된값에서 -1한 인덱스 위치를 사용하여 tmp에 해당 arr의 값을 저장 
			count[arr[j]]--; //count에 누적된합에서 1감소시켜줌
		}
		
		//tmp배열을 arr배열에 복사
		System.arraycopy(tmp, 0, arr, 0, tmp.length);
	}
}
