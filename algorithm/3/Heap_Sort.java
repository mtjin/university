
public class Heap_Sort {

	int heap_size;
	
	//MAX HEAP SORT(인덱스 0부터 시작)
	public void maxHeapSort(int[] arr) {
		int tmp;
		buildMaxHeap(arr);  //max heap 구조로 만듬
		for(int i =arr.length-1; i>=1; i--) { //최대 힙으로 되있는 것을 마저 완벽하게 정렬해줌, 노드가 n개라면 n-1번 반보해야함
			//swap
			tmp = arr[0];  //힙에서 최댓값을 가장 마지막값과 바꿈 (최댒값 최솟값 스왑)
			arr[0] = arr[i];
			arr[i] = tmp;
			
			heap_size -= 1;  //하나씩 정렬하는 것이므로 하나의 노드가 정렬이 완료되면 힙사이즈를 1줄여준다.(즉 스왑 후 마지막노드값은 고정이됨)(마지막값이 가장 큰 노드값으로 고정) 
			maxHeapify(arr, 0);  //첫노드엔 가장 큰 값이 마지막노드엔 가장 작은값이 오도록 heapify재귀를 돌려줌
		}
		
	}

	public void buildMaxHeap(int[] arr) {
		heap_size = arr.length-1; //힙사이즈 최대크기 설정 
		for(int i=arr.length/2-1; i>=0; i--) {  //자식노드들가 있는노드에서부터 상위노드까지 차례대로 힙구조로 만듬
			maxHeapify(arr, i);
		}
		
	}
	public void maxHeapify(int[] arr, int i) { //부모노드와 부모노드의 서브트리를 비교(재귀), 스왑하면서 maxheap구조로 만들어줌
		int largest;  //최대값 갖고있는 인덱스
		int left = 2*i+1;  //왼쪽 자식노드 인덱스
		int right = 2*i+2; //오른쪽 자식노드 인덱스
			
		
		if(left <= heap_size && arr[left]>arr[i]) { //왼쪽 자식노드 힙사이즈보다 작거나같고 부모노드(기준) 값보다 크면 largest에 왼쪽자식노드 인덱스 저장
			largest = left;
		}
		else { //아니면 부모노드 인덱스를 largest에 저장
			largest = i; 
		}
		
		//largest에 부모인덱스 or 왼쪽자식인덱스 저장되있는 상태
		if(right <= heap_size && arr[right] > arr[largest]) { //오른쪽자식 인덱스가 힙사이즈보다 작거나같고 largest인덱스 값보다 크면 largest에 오른쪽자식노드 인덱스저장
			largest =right;
		}
		if(largest != i){ //가장 큰 값의 인덱스가 부모노드가 아니라면 자식노드와 부모노드를 swap 해줌
			swap(arr, i, largest);
			maxHeapify(arr, largest); //가장 컸던 자식노드를(largest) 기준으로 재귀로 heapify반복
		}
	
	}
	
	
	//MIN HEAP SORT (max heap과 반대로 heapify메소드에서 자식노드가 더 작은값이면 부모노드와 스왑해주면됨
	public void minHeapSort(int[] arr) {
		int tmp;
		buildMinHeap(arr);
		for(int i =arr.length-1; i>=1; i--) {
			//swap
			tmp = arr[0];
			arr[0] = arr[i];
			arr[i] = tmp;
			
			heap_size -= 1;
			minHeapify(arr, 0);
		}
		
	}
	
	public void buildMinHeap(int[] arr) {
		heap_size = arr.length-1;
		for(int i=arr.length/2-1; i>=0; i--) {
			minHeapify(arr, i);
		}
		
	}
	
	public void minHeapify(int[] arr, int i) {
		int smaller;
		int left = 2*i+1;
		int right = 2*i+2;
		if(right<= heap_size) {
			if(arr[left] < arr[right]) {
				smaller = left;
			}
			else {
				smaller = right;
			}
		}
		else if(left <= heap_size) {
			smaller = left;
		}
		else {
			return;
		}
		if(arr[smaller] < arr[i]) {
			swap(arr, i, smaller);
			minHeapify(arr, smaller);
		}
	}
	
	//swap
	public void swap(int[] arr, int i , int largest) {
		int tmp = arr[i];
		arr[i] = arr[largest];
		arr[largest] = tmp;
		
	}

}
