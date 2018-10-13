
public class Heap_Sort {

	static int heap_size;
	public void maxHeapSort(int[] arr) {
		int tmp;
		buildMaxHeap(arr);
		for(int i =arr.length-1; i>=1; i--) {
			//swap
			tmp = arr[0];
			arr[0] = arr[i];
			arr[i] = tmp;
			
			heap_size -= 1;
			maxHeapify(arr, 0);
		}
		System.out.println("asdasd:  "+ arr[0]);
	}

	public void buildMaxHeap(int[] arr) {
		heap_size = arr.length-1;
		for(int i=arr.length/2-1; i>=0; i--) {
			maxHeapify(arr, i);
		}
		
	}
	public void maxHeapify(int[] arr, int i) {
		int largest;
		int left = 2*i+1;
		int right = 2*i+2;
			
		
		if(left <= heap_size && arr[left]>arr[i]) {
			largest = left;
		}
		else {
			largest = i; 
		}
		
		
		if(right <= heap_size && arr[right] > arr[largest]) {
			largest =right;
		}
		if(largest != i){
			swap(arr, i, largest);
			maxHeapify(arr, largest);
		}
	
	}
	
	public void swap(int[] arr, int i , int largest) {
		int tmp = arr[i];
		arr[i] = arr[largest];
		arr[largest] = tmp;
		
	}

}
