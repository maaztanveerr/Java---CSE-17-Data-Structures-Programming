import java.util.Iterator;
import java.util.ListIterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Generic class to impelment an array based list
 */
public class ArrayList<E> implements List<E>, Cloneable {
    // data member: array for the list elements
    private E[] elements;
    // data member: size of the list
    private int size;

    /**
     * Default constructor creates the array with a default length of 10 and sets
     * size to 0
     * Time complexity: O(1)
     */
    public ArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    /**
     * Constructor with one parameter creates the array with length equal to
     * capacity and sets size to 0
     * 
     * @param capacity length of the arrat elements
     *                 Time complexity: O(1)
     */
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Get the size of the list
     * 
     * @return the number of elements in the list
     *         Time complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Clear the list by setting size to 0
     * Time complexity: O(1)
     */
    public void clear() {
        size = 0;
    }

    /**
     * Predicate to check if the list is empty
     * 
     * @return true if the list is empty, false otherwise
     *         Time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Method to add a new item at the end of the list
     * 
     * @param item the value of the item to be added
     * @return true if item was added successfully, false otherwise
     *         Time complexity: O(1) or O(n) if the array capacity needs to grow
     */
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    /**
     * Method to add a new item at a position index
     * 
     * @param index the position where item should be added
     * @param item  the value of the element to be added
     * @return true if item was added successfully, false otherwise
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index > size
     *                                        Time complexity: O(n)
     */
    public void add(int index, E item) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = item;
        size++;
    }

    /**
     * Linear search method
     * 
     * @param o the object being searched
     * @return true if o was found in this list, false otherwise
     *         Time complexity: O(n)
     */
    public boolean contains(Object o) {
        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            E element = iter.next();
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the value of the element at index
     * 
     * @param index of the element being accessed
     * @return the value of the element at index
     * @throws ArrayIndexOutofBounds if index < 0 or index >= size
     *                               Time complexity: O(1)
     */
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Set the value of the element at index
     * 
     * @param index of the element being modified
     * @param value new value of the element at index
     * @return the old value of the element at index
     * @throws ArrayIndexOutofBounds if index < 0 or index >= size
     *                               Time complexity: O(1)
     */
    public E set(int index, E newValue) {
        checkIndex(index);
        E oldValue = elements[index];
        elements[index] = newValue;
        return oldValue;
    }

    /**
     * Remove an object from the list
     * 
     * @param o the object to remove from the list
     * @return true if o was found and removed or false if o was not found
     *         Time complexity: O(n)
     */
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the element at a index
     * 
     * @param index the position of the element to be removed
     * @return true if the elements was removed successfully, false otherwise
     * @throws ArrayIndexOutofBoundsException if index < 0 or index >= size
     *                                        Time complexity: O(n)
     */
    public E remove(int index) {
        checkIndex(index);
        E val = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return val;
    }

    /**
     * Resize the length of the array 'elements' to the size of the list
     * Time complexity: O(n) if trimming needed
     */
    public void trimToSize() {
        if (size != elements.length) {
            E[] newElements = (E[]) new Object[size];
            for (int i = 0; i < size; i++)
                newElements[i] = elements[i];
            elements = newElements;
        }
    }

    /**
     * Grow the length of the array 'elements' by 1.5 if it is full
     * Time complexity: O(n) if the size reaches the capacity
     */
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCap = (int) (elements.length * 1.5);
            E[] newElements = (E[]) new Object[newCap];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    /**
     * Check if the index is valid
     * 
     * @param index to be checked
     * @throws ArrayIndexOutOFBoundsException is index is out of bounds
     *                                        Time complexity: O(1)
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(
                    "Index out of bounds. Must be between 0 and " + (size - 1));
    }

    /**
     * @override iterator() from the interface Collection
     * @return iterator object pointing to the first element the list
     *         Time complexity: O(1)
     */
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    /**
     * Inner class to implement the interface Iterator<E>
     */
    private class ArrayIterator implements Iterator<E> {
        // data member current: the index of the element at which the iterator is
        // pointing
        private int current = 0;

        /**
         * @return true if current did not reach the end of the list, false otherwise
         *         Time complexity: O(1)
         */
        public boolean hasNext() {
            return current < size;
        }

        /**
         * @return the value of the current element and moves the index current to the
         *         next element
         * @throws ArrayIndexOutOfBoundsException if current is out of bounds
         *                                        Time complexity: O(1)
         */
        public E next() {
            if (current < 0 || current >= size)
                throw new NoSuchElementException();
            return elements[current++];
        }
    }

    /**
     * @override toString() from class Object
     * @return a formatted string containing the elements of the list
     *         Time complexity: O(n)
     */
    public String toString() {
        String output = "[";
        for (int i = 0; i < size - 1; i++)
            output += elements[i] + " ";
        if (size > 0)
            output += elements[size - 1];
        output += "]";
        return output;
    }

    /**
     * creates a list iterator for this list
     * 
     * @return a ListIterator object pointing to the first element
     *         Time complexity: O(1)
     */
    public ListIterator<E> listIterator() {
        return new ArrayListIterator();
    }

    /**
     * creates a list iterator for this list
     * 
     * @param index the position where the iterator starts
     * @return a ListIterator object pointing to the element at position index
     *         Time complexity: O(1)
     */
    public ListIterator<E> listIterator(int index) {
        return new ArrayListIterator(index);
    }

    /**
     * Inner class to implement the interface ListIterator<E>
     */
    private class ArrayListIterator implements ListIterator<E> {
        private int current;

        /**
         * Default constructor to start the iterator at the beginning of the list
         * Time complexity: O(1)
         */
        public ArrayListIterator() {
            current = -1;
        }

        /**
         * Default constructor to start the iterator at the position
         * 
         * @param index the position where the iterator starts
         *              Time complexity: O(1)
         */
        public ArrayListIterator(int index) {
            current = index;
        }

        /**
         * @return true if current did not reach the last element
         *         Time complexity: O(1)
         */
        public boolean hasNext() {
            return current < size - 1;
        }

        /**
         * @return the element at position current and moves forward one position
         *         Time complexity: O(1)
         */
        public E next() {
            if (current < -1 || current > size - 1)
                throw new NoSuchElementException();
            current++;
            E val = elements[current];
            if (current == size - 1)
                current = size;
            return val;
        }

        /**
         * @return true if current did not reach the first element
         *         Time complexity: O(1)
         */
        public boolean hasPrevious() {
            return current > 0;
        }

        /**
         * @return the element at position current and moves backward one position
         *         Time complexity: O(1)
         */
        public E previous() {
            if (current <= 0 || current > size)
                throw new NoSuchElementException();
            current--;
            E val = elements[current];
            if (current == 0)
                current = -1;
            return val;
        }

        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns a deep copy of this ArrayList
     * Time complexity: O(n)
     */
    public Object clone() {
        ArrayList<E> clonedList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            clonedList.add(elements[i]);
        }
        return clonedList;
    }

    /**
     * Returns the elements of this list in an array of type Object
     * 
     * @return an array containing all elements in this list
     *         Time complexity: O(n)
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = elements[i];
        }
        return array;
    }

    /**
     * Checks if the object is equal to this list.
     * 
     * @param o the object to be compared
     * @return true if o is an ArrayList with the same size and elements in the same
     *         order
     *         Time complexity: O(n)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArrayList)) {
            return false;
        }
        ArrayList<?> other = (ArrayList<?>) o;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(other.elements[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first element in the list.
     * 
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[0];
    }

    /**
     * Returns the last element in the list.
     * 
     * @return the last element
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[size - 1];
    }

    /**
     * Removes and returns the first element in the list.
     * 
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    /**
     * Removes and returns the last element in the list.
     * 
     * @return the last element
     * @throws NoSuchElementException if the list is empty
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(size - 1);
    }


    /**
     * Sorts the elements of this list using selection sort.
     * 
     * @param c the comparator used for sorting
     *          Time complexity: O(n^2)
     */
    public void sort(Comparator<E> c) {
        PriorityQueue<E> pqHeap = new PriorityQueue<>(c); 
        for (int i = 0; i < size; i++) {        
            pqHeap.offer(elements[i]);
        }
        size = 0;
        while (!pqHeap.isEmpty()) {
            add(pqHeap.poll());         //removing things from heap as i add them to the list. poll removes and returns!!
        }
    }

    /**
     * adds element to list at start
     */
    public void addFirst(E e) {
        ensureCapacity(); 
        for (int i = size - 1; i >= 0; i--) {
        elements[i + 1] = elements[i];
        }
        elements[0] = e; 
        size++;
    }

    /**
     * adds element to list at end
     */
    public void addLast(E e) {
        ensureCapacity(); 
        elements[size] = e; 
        size++;
    }

    /**
     * adds all elements from the collection to the list
     */
    @Override
    public boolean addAll(Collection<E> c) {
        Iterator<E> iterator = c.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();
            add(e); 
        }
        return true;
    }

    /**
     * retains only the elements in the list that are also in the collection
     */
    @Override
    public boolean retainAll(Collection<E> c) {
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(i); 
                i--; 
            }
        }
        return true;
    }

    /**
     * removes all elements in the list that are also in collection
     */
    @Override
    public boolean removeAll(Collection<E> c) {
        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                remove(i); 
                i--; 
            }
        }
        return true;
    }


    /**
     * Returns the index of the first occurrence or -1 if this list does not contain the element.
     * Time complexity: O(n)
     */
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && o == null) || (elements[i] != null && elements[i].equals(o))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence or -1 if this list does not contain the element.
     * Time complexity: O(n)
     */
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if ((elements[i] == null && o == null) || (elements[i] != null && elements[i].equals(o))) {
                return i;
            }
        }
        return -1;
    }

}
