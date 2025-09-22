import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Class HashMap: An abstract data type for the hash table
 */
public abstract class HashMap<K, V> {
    // data members
	protected double loadFactor;
    
    protected int trimToPowerOf2(int c) {
		int powerOf2 = 1;
		while (powerOf2 < c)
			powerOf2  = powerOf2 << 1;
		return powerOf2;
	}
    
    /**
     * Method hash
     * @param hashCode
     * @return a valid index in the hashtable
     */
    protected abstract int hash(int hashCode);

	/**
     * Method rehash
     * creates a new hashtable with the capacity doubled
     * puts all the entries from the old hashtable into the new table
     */
	protected abstract void rehash();

    /**
     * Method size
     * @return the number of pairs (key,value) stored the hashtable
     */
	public abstract int size();

    /**
     * Method isEmpty
     * @return true if the hashtable is empty, false otherwise
     */
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
     * Method contains to search for a key in the hashtable
     * @param key the value of the key being searched for
     * @return true if key was found, false otherwise
     */
	public boolean containsKey(K key) {
		if(get(key) != null)
			return true;
		return false;
	}

	/**
     * Method clear to clear the hashtable
     */
	public abstract void clear();

    /**
     * Method get to find an entry in the hashtable
     * @param key the value of the key being searched for
     * @return the value associated with the key if key is found, null otherwise
     */
	public abstract V get(K key);

    /**
     * Method remove to remote an entry from the hashtable
     * @param key the key to be removed
     * if the key is found, the pair (key and it associated value) will be removed from the hashtable
     * the hashtable is not modified if key is not found
	 * Time complexity: O(1)
     */
	public abstract void remove(K key);

    /**
     * Method put to add a new entry to the hashtable
     * @param key the value of the key of the new entry
     * @param value the value associated with the key
     * @return the old value of the entry if an entry is found for key
     *         or the new value if a new entry was added to the hashtable
	 * Time complexity: O(1) on average, but can reach O(n) if rehashing is required
     */
    public abstract V put(K key, V value);
    
    /**
     * Method toList
     * @return an arraylist with all the entries in the hashtable
	 * Time complexity: O(n)
     */
	public abstract ArrayList<MapEntry<K,V>> toList();

	public abstract void printCharacteristics();
    public abstract ArrayList<V> values();
}