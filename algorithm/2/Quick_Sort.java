
public class Quick_Sort {

	public void sort(int[] A, int low , int high) {
		if(low< high) {
		int q = partition(A, low, high);
		sort(A, low , q-1);
		sort(A, q+1, high);
		}
	}
	
	public void random_sort(int[] A, int low , int high) {
		if(low< high) {
		int q = randomize_partition(A, low, high);
		sort(A, low , q-1);
		sort(A, q+1, high);
		}
	}
	
	
	public int partition(int []A, int low, int high ) {
		int pivot = A[high];
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
	
	public int randomize_partition(int []A, int low, int high ) {
		int pivot = A[4];
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
