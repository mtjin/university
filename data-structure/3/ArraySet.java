

public class ArraySet implements Set {
	private Object[] objects = new Object[1000];
	private int size=0;
	private int i;

	@Override
	public boolean add(Object object) {        //데이터추가메소드
		// TODO Auto-generated method stub
		if(contains(object)) {                // 중복유무   
			return false;
		}else {
			objects[size++]= object;          // 데이터추가한 후 사이즈 1증가
			return true;
		}
	}

	@Override
	public boolean contains(Object object) {    //중복유무판결 메소드
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			if(objects[i].equals(object)) return true;     
		}
		return false;
	}



	@Override
	public Object getNext() {                    //다음 데이터값 반환
		// TODO Auto-generated method stub
		return objects[i++];
	}

	@Override
	public boolean remove(Object object) {             //제거메소드
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			if(objects[i].equals(object)) {
				System.arraycopy(objects, i+1, objects, i , size-i-1);       //삭제할 데이터를 없에면서 동시에 배열을 재정비해줌
				objects[size-1]= null;                            //복사한 후 마지막에 남은 데이터 null로해줌
				size--;
				System.out.println("삭제되었습니다");
				return true;
			}
		}
		System.out.println("없는 데이터입니다");
		return false;
	}

	@Override
	public int size() {                           //size값 반혼 메소드
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getFrist() {                        //objects[0] 값 반환메소드
		// TODO Auto-generated method stub
		i=0;                                          //i를 0으로초기화시켜줌
		return objects[i++];                         
	} 
}
