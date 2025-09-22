import java.util.Comparator;
import java.util.ListIterator;

/**
 * list interface defining list structure
 */
public interface List<E> extends Collection<E> {

    /**
     * adds element at specified index
     */
    public void add(int index, E element);

    /**
     * returns element at index
     */
    public E get(int index);

    /**
     * returns index of first occurrence of object
     */
    public int indexOf(Object o);

    /**
     * returns index of last occurrence of object
     */
    public int lastIndexOf(Object o);

    /**
     * sets element at index and returns old element
     */
    public E set(int index, E element);

    /**
     * sorts list using comparator or natural order
     */
    public void sort(Comparator<E> c);

    /**
     * adds element at start of list
     */
    public void addFirst(E e);

    /**
     * adds element at end of list
     */
    public void addLast(E e);

    /**
     * returns first element
     */
    public E getFirst();

    /**
     * returns last element
     */
    public E getLast();

    /**
     * returns list iterator at start
     */
    public ListIterator<E> listIterator();

    /**
     * returns list iterator starting at index
     */
    public ListIterator<E> listIterator(int index);

    /**
     * removes element at index and returns it
     */
    public E remove(int index);

    /**
     * removes and returns first element
     */
    public E removeFirst();

    /**
     * removes and returns last element
     */
    public E removeLast();
}
