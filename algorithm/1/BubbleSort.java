
public class BubbleSort {
	void sort(DoubleLinked list) {
		System.out.println("Start BubbleSort");
		int temp = 0;
		for (int i = 0; i <list.size() ; i++) {
			for (int j = 1; j < list.size() - i; j++) {
				if ((list.getNode(j)).data < (list.getNode(j-1)).data) {
					temp = (list.getNode(j-1)).getData();
					(list.getNode(j-1)).setData((list.getNode(j)).data);
					(list.getNode(j)).setData(temp);
				}
			}
		//System.out.println("bubble"+i);
		}
	}
}
