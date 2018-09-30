
public class InsertionSort {
	void sort(DoubleLinked list) {
		System.out.println("Start InsertionSort");
		int i, j, key;
		for(i =1; i<list.size(); i++) {
			key = (list.getNode(i)).data;
			for(j= i-1; j>=0 && (list.getNode(j)).data > key; j--) {
				(list.getNode(j+1)).setData((list.getNode(j).data));
			}
			(list.getNode(j+1)).setData(key);
			
		}
	}
}
