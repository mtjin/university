
public class QuickSort {

	public void sort(int[] elements , int size) {
		
			
			if(size > 1 ) {
				int maxLoc = 0; 
				for(int i = 1; i<size; i++) {
					if(elements[i] > elements[maxLoc]) {
						maxLoc = i;
					}
				}
				swap(elements, maxLoc, size-1);
				quickSortRecursively(elements, 0, size -2);
			}
	
	}
	
		void quickSortRecursively(int[] elements, int left, int right) {
			int pivot, up, down;
			if(left < right) {
				pivot = left;
				up = left;
				down = right + 1;
				do {
					do {
						up++;
					}while( elements[pivot] > elements[up]);
					do {
						down--;
					}while(elements[pivot] < elements[down]);
					if(up <down) {
						swap(elements, up, down);
					}
				}while(up < down);
				swap(elements, pivot, down);
				int mid = down;
				quickSortRecursively(elements, left, mid -1);
				quickSortRecursively(elements, mid + 1 , right);
			}
		}
		
		private void swap(int[] elements, int left, int right) {
			
			int temp = elements[left];
			elements[left] = elements[right];
			elements[right] = temp;
		}
}
