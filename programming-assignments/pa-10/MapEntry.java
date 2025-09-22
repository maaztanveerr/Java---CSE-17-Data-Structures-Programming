/**
 * represents a key-value pair for use in hashmaps
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class MapEntry<K, V> {
    private K key; 
    private V value; 

    /**
     * makes a new map entry with a key and value
     * @param k the key
     * @param v the value
     */
    public MapEntry(K k, V v) {
        key = k;
        value = v;
    }

    /**
     * gets the key of the entry
     * @return the key
     */
    public K getKey() { 
        return key; 
    }

    /**
     * gets the value of the entry
     * @return the value
     */
    public V getValue() { 
        return value; 
    }

    /**
     * changes the key of the entry
     * @param k the new key
     */
    public void setKey(K k) {
        key = k;
    }

    /**
     * changes the value of the entry
     * @param v the new value
     */
    public void setValue(V v) {
        value = v;
    }

    /**
     * shows the map entry as a string
     * @return a string in the format (key, value)
     */
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}
