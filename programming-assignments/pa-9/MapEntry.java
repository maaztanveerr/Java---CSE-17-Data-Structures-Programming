public class MapEntry<K, V> {
    private K key;
    private V value;

    /**
     * constructs a map entry with the given key and value
     * @param key the key 
     * @param value the value associated with the key
     */
    public MapEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    /**
     * returns key
     * @return key
     */
    public K getKey(){ 
        return key;
    }

    /**
     * sets key 
     * @param key the new key
     */
    public void setKey(K key){ 
        this.key = key;
    }

    /**
     * returns the value 
     * @return value
     */
    public V getValue(){ 
        return value;
    }    

    /**
     * sets value 
     * @param value the new value
     */
    public void setValue(V value){ 
        this.value = value;
    }

    /**
     * returns a string representation 
     * @return string in the format "(key, value)"
     */
    @Override
    public String toString(){
        return "(" + key + ", " + value + ")";
    }
}