
public class HeapSort {

	int[] newData;

	public void sort(int[] a, int size) {
		this.newData = new int[size + 1];
		this.newData[0] = 0;
		for (int i = 0; i < size; i++) {
			this.newData[i + 1] = a[i]; // 배열 인덱스 1부터 차례대로 값 넣어줌
		}

		int i;

		for (int j = size; j > 0; j--) { // 사이즈만큼 반복
			for (i = size / 2; i > 0; i--) //
				adjust(this.newData, i, j);
			swap(1, j);
		}

		for (int j = 0; j < size; j++)
			a[j] = this.newData[j + 1];

	}

	private void swap(int x, int y) {
		int temp = newData[x];
		newData[x] = newData[y];
		newData[y] = temp;

	}

	public void adjust(int[] newData, int root, int n) {
		int child, rootkey;
		int temp = newData[root]; // 임시 루트값
		rootkey = newData[root]; // 루트키값
		child = 2 * root; // 처음에 8
		while (child <= n) {

			if (child < n && newData[child] < newData[child + 1]) { //오른쪽 자식이있고 왼쪽자식이 오른쪾 자식보다 작을때 
			temp = newData[++child];
			} else { // 왼쪽 자식밖에 없거나(차수가1) 왼쪽자식이 오른쪽 자식보다 클 때
				temp = newData[child];
			}

			if (rootkey < temp) {  //rootkey값과 child값을 비교해서 child값이 더 큰 경우(교환)
				int tmp2 = newData[child / 2];  //tmp2에 자식의 부모값을 저장
				newData[child / 2] = newData[child];  // 자식의 부모값에 자식의 값을 저장
				newData[child] = tmp2; //자식 값은  자식의 부모값을 저장 
			}
			child = child * 2;  //child를 부모의 값으로 바꿔줌
		}

	}
}
