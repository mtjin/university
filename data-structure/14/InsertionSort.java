
public class InsertionSort {

	public void sort(int[] data , int size) {
		for(int index =1; index < data.length; index++) {
			int temp =data[index];
			int aux = index - 1;
			
			while((aux >= 0) && (data[aux] > temp)) {
				
				data[aux+1] = data[aux];
				aux--;
			}
			data[aux + 1]= temp; 
		}
	}
}
