import java.util.Iterator;

/**
 * collection interface defining basic collection operations
 */
public interface Collection<E> {

    /**
     * adds element to collection
     */
    public boolean add(E element);

    /**
     * adds all elements from specified collection
     */
    public boolean addAll(Collection<E> c);

    /**
     * retains only elements present in specified collection
     */
    public boolean retainAll(Collection<E> c);

    /**
     * removes all elements found in specified collection
     */
    public boolean removeAll(Collection<E> c);

    /**
     * clears collection
     */
    public void clear();

    /**
     * checks if collection contains object
     */
    public boolean contains(Object o);

    /**
     * checks if collection equals object
     */
    public boolean equals(Object o);

    /**
     * checks if collection is empty
     */
    public boolean isEmpty();

    /**
     * returns iterator over collection
     */
    public Iterator<E> iterator();

    /**
     * removes single instance of object
     */
    public boolean remove(Object o);

    /**
     * returns number of elements in collection
     */
    public int size();

    /**
     * returns array containing all elements
     */
    public Object[] toArray();
}
