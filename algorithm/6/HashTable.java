
public class HashTable {
	int key;
	int h[] = new int[97];
	int size = 97;
	int size2 = 59;
	int i;
	int collision_count = 0;
	final int DELETED = -1;
	
	//선형조사 삽입
	public void linearInsert(int x) {
		key = x % size;
		while(h[key] != 0) {
			key = (key + 1) % size; //해싱
			collision_count++; //충돌횟수
		}
		h[key] = x;
	}
	
	//이차원조사 삽입
	public void quadraticInsert(int x) {
		key = x % size;
		i=0;
		while(h[key] != 0) {
			i++;
			key = ((x % size) + i*i) % size;  //해싱
			collision_count++;
		}
		h[key] = x;
	}
	
	//더블 해싱 삽입
	public void doubleInsert(int x) {
		key = x % size;
		i = 0;
		while(h[key] != 0) {
			i++;
			key = ((x % size) + i * (x % size2)+1) % size; //해싱
			collision_count++;
		}
	
		h[key] = x;
	}
	
	
	//검색한 값 찾으면 해당 값의 인덱스(키) 반환, 못찾으면 0반환
	public int linearSearch(int x) {
		key = x % size;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				return key;
			}
			else {
				key = (key + 1) % size;
				collision_count++;
			}
		}
		return 0;
	}
	
	public int quadraticSearch(int x) {
		key = x % size;
		i=0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x ) { 
				return key;
			}
			else {
				i++;
				key = ((x % size) + i*i) % size;
			}
		}
		return 0;
	}
	
	public int doubleSearch(int x) {
		key = x % size;
		i = 0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				return key;
			}
			else {
				i++;
				key = ((x % size) + i * (x % size2)+1) % size;
			}
		}
		return 0;
	}
	
	
	//삭제
	public void linearDelete(int x) {
		key = x % size;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				h[key] = DELETED;
			}
			else {
				key = ((key + 1) % size);
			}
		}
	}
	
	public void quadraticDelete(int x) {
		key = x % size;
		i =0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				h[key] = DELETED;
			}
			else {
				i++;
				key = ((x % size) + i*i) % size; 
			}
		}
	}
	
	public void  doubleDelete(int x) {
		key = x % size;
		i = 0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				h[key] = DELETED;
			}
			else {
				i++;
				key = ((x % size) + i * (x % size2)+1) % size;
			}
		}
	}
	
	public int getCollison() {
		return collision_count;
	}
}
