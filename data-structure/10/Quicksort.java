
public class Quicksort {

	private int[] array;
	private int size, i;
	private int maxSize;
	
	public Quicksort(int maxSize) {
		this.maxSize = maxSize;
		size = 0;
		array = new int[maxSize];
		this.i =0;
	}
	
	public void quickSortRecursively(int left, int right) { //void로 바궈줌
		if(left < right) {
		int mid = partition(left, right);     
		quickSortRecursively(left, mid-1);
		quickSortRecursively(mid+1, right);   
		}
	}
	public void sorting() {
		this.quickSortRecursively(0, size-1);
	}
	public int partition(int left, int right) {
		int pivot = left;
		int data = this.array[pivot];
		right++;  //그래야 밑에 do-while문에서 감소시켰을때 데이터있는값부터 시작할 수 있음.
		do {
			//채워넣기
			do {
				left++;
			}while((array[left] < data) && left <= right);  //피벗값보다 left데이터값이 작고, left는 right보다 작거나 같아야함
			
			do {
				right--;
			}while(array[right] > data && right >= left); //위도 마찬가지지만 (right >= left) =을 붙여야 left하고 right가 딱 엇갈린다.
			if(left<right) {
				this.swap(left, right);
			}
		}while(left < right);
			
		this.swap(pivot , right);
		return right;
	}
	public void swap(int a , int b) {
		int tmp;
		tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
		
	}
	public boolean add(int data) {
		if(size< maxSize) {
			array[size++]= data;
			return true;
		}else {
			return false;
		}
		
	}
	public int getFirst() {
		i =0; //i초기화해줌
		return array[i++]; //getNext()할때 1번인덱스부터 불러올수있게함
	}
	public int getNext() {
		return array[i++];
	}
	public int size(){
		return size;
	}
}
