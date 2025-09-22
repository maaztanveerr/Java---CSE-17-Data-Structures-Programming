import java.util.ArrayList;
import java.util.LinkedList;

/**
 * class that implements a hashmap using separate chaining
 * K for key
 * V for value
 */
public class HashMapSC<K, V> extends HashMap<K, V> {
    // data member: array of linked lists used for separate chaining
    private LinkedList<MapEntry<K,V>>[] hashTable;
    // data member: number of elements in the hashmap
    private int size;
    // data member: number of collisions that occurred during insertions
    private int collisions;
    // data member: load factor at which rehashing is triggered
    private double loadFactor;
    public static int getIterations; //tracking iterations in get method
    public static int removeIterations; //tracking iterations in remove method

    /**
     * default constructor with initial capacity 100 and load factor 0.9
     */
    public HashMapSC() {
        this(100, 0.9);
    }

    /**
     * creates a hashmap with given size and default load factor 0.9
     * @param c the size of the hashmap
     */
    public HashMapSC(int c) {
        this(c, 0.9);
    }


    /**
     * creates a hashmap with given size and load factor
     * @param c the size of the hashmap
     * @param lf the load factor
     */
    public HashMapSC(int c, double lf) {
        hashTable = new LinkedList[trimToPowerOf2(c)];
        loadFactor = lf;
        size = 0;
        collisions = 0;
        getIterations = 0;
        removeIterations = 0;
    }

    /**
     * gets the index in the hash table for a key
     * @param hashCode the hash code of the key
     * @return the index in the hash table
     */
    protected int hash(int hashCode) {
        return hashCode & (hashTable.length-1);
    }

    /**
     * gets how many items are in the hashmap
     * @return the size of the hashmap
     */
    public int size() { 
        return size;
    }

    /**
     * removes all items from the hashmap
     */
    public void clear() { 
        size = 0;
        for(int i=0; i<hashTable.length; i++)
            if(hashTable[i] != null)
                hashTable[i].clear();
    }

    /**
     * finds the value for a given key
     * @param key the key to look for
     * @return the value if found, or null if not found
     */
    public V get(K key) {
        getIterations = 0;
        int HTIndex = hash(key.hashCode());
        if(hashTable[HTIndex] != null) {
            LinkedList<MapEntry<K,V>> ll = hashTable[HTIndex];
            for(MapEntry<K,V> entry: ll) {
                getIterations++;
                if(entry.getKey().equals(key))
                    return entry.getValue();
            }
        }
        return null;
    }

    /**
     * removes the item with the given key
     * @param key the key to remove
     */
    public void remove(K key) {
        removeIterations = 0;
        int HTIndex = hash(key.hashCode());
        if (hashTable[HTIndex]!=null) { 
            LinkedList<MapEntry<K,V>> ll = hashTable[HTIndex];
            for(int i=0; i<ll.size(); i++) {
                removeIterations++;
                if(ll.get(i).getKey().equals(key)) {
                    ll.remove(i);
                    size--;
                    break;
                }
            }
        }       
    }

    /**
     * adds a new key-value pair or updates the value if the key already exists
     * @param key the key to add or update
     * @param value the value to add or update
     * @return the old value if the key was already in the hashmap, or the new value
     */
    public V put(K key, V value) {
        V val = get(key);
        if (val != null) {
            int HTIndex = hash(key.hashCode());
            LinkedList<MapEntry<K,V>> ll = hashTable[HTIndex];
            for(MapEntry<K,V> entry: ll) {
                if(entry.getKey().equals(key)) {
                    V old = entry.getValue();
                    entry.setValue(value);
                    return old;
                }
            }
            return val; 
        }
        if (size >= hashTable.length * loadFactor) {
            rehash() ; }
        int HTIndex = hash(key.hashCode());
        if(hashTable[HTIndex] == null){
            hashTable[HTIndex] = new LinkedList<>();
        } else {
            if(!hashTable[HTIndex].isEmpty()) {
                collisions++ ;
            }
        }
        hashTable[HTIndex].add(new MapEntry<>(key, value));
        size++; 
        return value;
    }

    /**
     * makes the hashmap bigger and re-adds all items
     */
    protected void rehash() {
        ArrayList<MapEntry<K,V>> list = toList();
        hashTable = new LinkedList[hashTable.length << 1];
        size = 0;
        for(MapEntry<K,V> entry: list) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * gets all items in the hashmap as a list
     * @return a list of all key-value pairs
     */
    public ArrayList<MapEntry<K,V>> toList(){
        ArrayList<MapEntry<K,V>> list = new ArrayList<>();
        for(int i=0; i< hashTable.length; i++) {
            if(hashTable[i]!= null) {
                LinkedList<MapEntry<K,V>> ll = hashTable[i];
                for(MapEntry<K,V> entry: ll)
                    list.add(entry);
            }
        } 
        return list;
    }

    /**
     * gets all values in the hashmap
     * @return a list of all values
     */
    public ArrayList<V> values() {
        ArrayList<V> vals = new ArrayList<>();
        for(int i=0; i<hashTable.length; i++) {
            if(hashTable[i]!=null) {
                for(MapEntry<K,V> entry : hashTable[i]) {
                    vals.add(entry.getValue());
                }
            }
        }
        return vals;
    }

    /**
     * prints information about the hashmap like size and collisions
     */
    public void printCharacteristics() {
        int capacity = hashTable.length;
        int currSize = size;
        int totalCollisions = collisions;
        int nonEmptyBuckets = 0;
        int largestBucket = 0;
        int smallestBucket = Integer.MAX_VALUE;

        for (int i = 0; i<hashTable.length; i++){
            if (hashTable[i] != null && !hashTable[i].isEmpty()){
                nonEmptyBuckets++;
                int bucketSize = hashTable[i].size();
                if (bucketSize > largestBucket){
                    largestBucket = bucketSize;
                }
                if (bucketSize < smallestBucket){
                    smallestBucket = bucketSize;
                }
            }
        }

        if (nonEmptyBuckets == 0) {
            smallestBucket = 0;
            largestBucket = 0;
        }

        System.out.println("HashTable capacity: " + capacity);
        System.out.println("HashTable size: " + currSize);
        System.out.println("Total number of collisions: " + totalCollisions);
        System.out.println("Number of buckets: " + nonEmptyBuckets);
        System.out.println("Size of the largest bucket: " + largestBucket);
        System.out.println("Size of the smallest bucket: " + smallestBucket);
    }

    /**
     * shows the hashmap as a string
     * @return the string version of the hashmap
     */
    public String toString() {
        String out = "[";
        for(int i=0; i<hashTable.length; i++) {
            if(hashTable[i]!=null && !hashTable[i].isEmpty()) {
                for(MapEntry<K,V> entry: hashTable[i])
                    out += entry.toString();
                out += "\n";
            }
        }
        out += "]"; 
        return out;
    }
}
