import java.util.ArrayList;

/**
 * this class makes a hashmap using linear probing
 * k is the key type
 * v is the value type
 */
public class HashMapLP<K, V> extends HashMap<K, V> {
    private MapEntry<K,V>[] hashTable; // array to store the hashmap data
    private int size;
    private double loadFactor; // load factor to decide when to resize
    public static int collisions; // counts the number of collisions
    public static int getIterations;
    public static int removeIterations;

    /**
     * creates a hashmap with default size 100 and load factor 0.9
     */
    public HashMapLP() {
        this(100, 0.9);
    }

    /**
     * creates a hashmap with given size and default load factor 0.9
     * @param c the size of the hashmap
     */
    public HashMapLP(int c) {
        this(c, 0.9);
    }

    /**
     * creates a hashmap with given size and load factor
     * @param c the size of the hashmap
     * @param lf the load factor
     */
    public HashMapLP(int c, double lf) {
        loadFactor = lf;
        hashTable = new MapEntry[trimToPowerOf2(c)];
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
     * makes the hashmap bigger and moves all items to the new table
     */
    protected void rehash() {
        ArrayList<MapEntry<K,V>> list = toList();
        hashTable = new MapEntry[hashTable.length << 1];
        size = 0;
        for(MapEntry<K,V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * gets the number of items in the hashmap
     * @return the size of the hashmap
     */
    public int size() {
        return size;
    }

    /**
     * removes all items from the hashmap
     */
    public void clear() {
        for(int i=0; i<hashTable.length; i++) {
            hashTable[i] = null;
        }
        size = 0;
    }

    /**
     * finds the value for a given key
     * @param key the key to look for
     * @return the value if found, or null if not found
     */
    public V get(K key) {
        getIterations = 0;
        int index = hash(key.hashCode());
        while (hashTable[index] != null) {
            getIterations++;
            if(hashTable[index].getKey().equals(key)) {
                return hashTable[index].getValue();
            }
            index = (index+1) % hashTable.length;
        }
        return null;
    }

    /**
     * removes the item with the given key
     * @param key the key to remove
     */
    public void remove(K key) {
        removeIterations = 0; 
        int index = hash(key.hashCode()); 
        ArrayList<MapEntry<K, V>> cluster = new ArrayList<>(); //temp
    
        while (hashTable[index] != null) {
            removeIterations++;
            if (hashTable[index].getKey().equals(key)) {
                hashTable[index] = null;
                size--;
    
                index = (index + 1) % hashTable.length;
                while (hashTable[index] != null) {
                    removeIterations++;
                    cluster.add(hashTable[index]); 
                    hashTable[index] = null; 
                    size--;
                    index = (index + 1) % hashTable.length;
                }
    
                // reinsert
                for (MapEntry<K, V> entry : cluster) {
                    put(entry.getKey(), entry.getValue());
                    removeIterations++;
                }
    
                return; 
            }
            index = (index + 1) % hashTable.length;
        }
    }
    
    /**
     * adds a new key-value pair or updates an existing one
     * @param key the key to add or update
     * @param value the value to add or update
     * @return the old value if the key existed, or the new value
     */
    public V put(K key, V value) {
        V existingValue = get(key);
        
        if (existingValue != null) {
            int index = hash(key.hashCode());
            while (hashTable[index] != null) {
                if (hashTable[index].getKey().equals(key)) {
                    V oldValue = hashTable[index].getValue();
                    hashTable[index].setValue(value);
                    return oldValue;
                }
                index = (index + 1) % hashTable.length; 
                // linear probing
            }
        }
    
        if (size >= hashTable.length * loadFactor) {
            rehash();
        }
    
        int index = hash(key.hashCode());
        boolean collisionOccurred = false;
    
        while (hashTable[index] != null) {
            collisionOccurred = true; 
            index = (index + 1) % hashTable.length; 
        }
    
        if (collisionOccurred) {
            collisions++;
        }
    
        hashTable[index] = new MapEntry<>(key, value);
        size++;
        return value;
    }
    
     /**
     * gets all items in the hashmap as a list
     * @return a list of all key-value pairs
     */
    public ArrayList<MapEntry<K,V>> toList() {
        ArrayList<MapEntry<K,V>> list = new ArrayList<>();
        for (int i=0; i< hashTable.length; i++) {
            if(hashTable[i]!= null) {
                list.add(hashTable[i]);
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
        for (int i=0; i<hashTable.length; i++) {
            if(hashTable[i]!=null) {
                vals.add(hashTable[i].getValue());
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
        int clusterCount = 0;
        int largestCluster = 0;
        int smallestCluster = Integer.MAX_VALUE;

        int i=0;
        while(i<hashTable.length) {
            if(hashTable[i]!=null) {
                clusterCount++;
                int clusterSize = 0;
                while(i<hashTable.length && hashTable[i]!=null) {
                    clusterSize++;
                    i++;
                }
                if(clusterSize>largestCluster)
                    largestCluster=clusterSize;
                if(clusterSize<smallestCluster)
                    smallestCluster=clusterSize;
            } else {
                i++;
            }
        }

        if(clusterCount == 0) {
            smallestCluster = 0;
            largestCluster = 0;
        }

        System.out.println("HashTable capacity: " + capacity);
        System.out.println("HashTable size: " + currSize);
        System.out.println("Total number of collisions: " + totalCollisions);
        System.out.println("Number of clusters: " + clusterCount);
        System.out.println("Size of the largest cluster: " + largestCluster);
        System.out.println("Size of the smallest cluster: " + smallestCluster);
    }
    
    /**
     * shows the hashmap as a string
     * @return the string version of the hashmap
     */
    public String toString() {
        String out = "[";
        for(int i=0; i<hashTable.length; i++) {
            if(hashTable[i]!=null) {
                out += hashTable[i].toString();
            }
        }
        out += "]";
        return out;
    }
}
