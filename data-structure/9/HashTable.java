
public class HashTable {

	private Entry[] entries;
	private int size, used, collisionCount;
	private float loadFactor;
	private Entry NIL = new Entry(null, null);
	
	public HashTable(int capacity, float loadFactor) {
		entries = new Entry[capacity];
		this.loadFactor = loadFactor;
	}
	
	private class Entry{
		Object key, value;
		Entry(Object k, Object v){ key = k; value = v;}
	}
	public Object get(Object key) {
		int h = hash(key);
		
		if(h==-1)
			return null;
		
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,i);
			Entry entry = entries[j];
			if(entry  == null) break;
			if(entry.key.equals(key)) {
				System.out.println("[DEBUG] get, "+ j);
				return entry.value;
			}
			System.out.println("[DEBUG] Hash Collision, get, "+ j);
		}
		return null;
	}
	public Object put(Object key, Object value) {
		if(used>loadFactor*entries.length) rehash();
		int h = hash(key);
		
		if(h==1)
			return null;
		
		for(int i=0; i<entries.length; i++) {
			int j = nextProbe(h,i);
			Entry entry = entries[j];
			if(entry == null) {
				System.out.println("[DEBUG] put, " + j);
				entries[j] = new Entry(key, value);
				++size;
				++used;
				return null;
			}
			if(entry.key.equals(key)) {
				Object oldValue = entry.value;
				entries[j].value = value;
				return oldValue;
				
			}
			System.out.println("[DEBUG] Hash Collision, put, " + j);
		}
		return null;
	}
	private int hash(Object key) {
		if(key == null) throw new IllegalArgumentException();
		
		if(key.equals("KR"))
		{
			return 0;
		}
		else if(key.equals("FI"))
		{
			return 0;
		}
		else if(key.equals("IQ"))
		{
			return 1;
		}
		else if(key.equals("IR"))
		{
			return 2;
		}
		else if(key.equals("SK"))
		{
			return 3;
		}
		else if(key.equals("CA"))
		{
			return 3;
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
	}
	private int nextProbe(int h, int i) {
		return (h + i)%entries.length;
	}
	private void rehash() {
		Entry[] oldEntries = entries;
		entries = new Entry[2*oldEntries.length+1];
		for(int k=0; k<oldEntries.length; k++) {
			Entry entry = oldEntries[k];
			if(entry == null)continue;
			int h = hash(entry.key);
			for(int i=0; i<entries.length; i++) {
				int j = nextProbe(h,i);
				if(entries[j] == null) {
					entries[j] = entry;
					break;
				}
			}
		}
		used = size;
	}
}
