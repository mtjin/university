
public class Merge_Sort {

	//끝까지 쪼개질떄까지 sort
	public void sort(int[] A, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2; // 절반기준
			sort(A, left, mid); // 기준 왼쪽 또 쪼갬
			sort(A, mid + 1, right); // 기준 오른쪽 또 쪼갬
			merge(A, left, mid, right); // 쪼갠배열 합침
		}
	}

	//해당개수될떄까지 sort
	public void divided_sort(int[] A, int left, int right, int divided_num) { //뒤에 최대 쪼개는 개수 매개변수 추가
		if (left < right && right-left >= divided_num) {
			int mid = (left + right) / 2; // 절반기준
			divided_sort(A, left, mid, divided_num); // 기준 왼쪽 또 쪼갬
			divided_sort(A, mid + 1, right, divided_num); // 기준 오른쪽 또 쪼갬
			merge(A, left, mid, right); // 쪼갠배열 합침
			
			//System.out.println(right-left);   // 이 출력문으로 정해진 개수까지만 쪼개지는지 확인가능
		}
	}
	
	//merge
	public void merge(int[] arr, int left, int mid, int right) {
		int k = left; // 배열 시작위치
		int merged[] = new int[right + 1]; // left에서 right인덱스 까지 저장할 임시배열을 알맞은 크기로 생성 (right인덱스까지 넣어야하므로 right+1)

		for (k = left; k <= right; k++) { // 임시배열에 쪼개진 두 배열을 그대로 합쳐서 넣어줌(정렬X)
			merged[k] = arr[k];
		}

		insertion_sort(merged); // 두개의 배열을 합친 배열을 삽입정렬해줌(이번과제에서 합병정렬을 삽입정렬을 이용하라 했으므로)

		for (k = left; k <= right; k++) { // 삽입 정렬한 임시배열을 원래 배열에 알맞은 위치에 값을 넣어줌 (정렬한값들을 복사).
			arr[k] = merged[k];
		}
	}

	// 삽입정렬
	void insertion_sort(int[] list) {
		int i, j, key;
		for (i = 1; i < list.length; i++) {
			key = list[i];
			for (j = i - 1; j >= 0 && list[j] > key; j--) {
				list[j + 1] = list[j];
			}
			list[j + 1] = key;
		}
	}
}
