
public class SelectionSort {
	void sort(DoubleLinked list) {
		System.out.println("Start selectionsort");
		int i, j , min, temp;
		for(i = 0; i < list.size()-1; i++ ) {
			min = i;
			for(j = i+1; j<list.size(); j++) {
				if((list.getNode(j)).data < (list.getNode(min)).data)
						min = j;
			}
			//swap
			temp = (list.getNode(i)).data;
			(list.getNode(i)).setData((list.getNode(min)).data);
			(list.getNode(min)).setData(temp);
		}
	}
}
