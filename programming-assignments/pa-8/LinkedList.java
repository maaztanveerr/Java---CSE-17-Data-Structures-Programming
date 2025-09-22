import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * LinkedList Generic Class
 */
public class LinkedList<E> implements List<E>, Cloneable {
    // Data members
    private Node head, tail;
    private int size;

    /**
     * Inner class Node
     */
    private class Node {
        E value;
        Node next;
        Node previous; // Added for doubly-linked list

        Node(E initialValue) {
            value = initialValue;
            next = null;
            previous = null; // Initialize previous for doubly-linked list
        }
    }

    /**
     * Default Constructor
     * creates an empty linkedlist
     * Time complexity: O(1)
     */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * size method
     * 
     * @return the number of nodes in the list
     *         Time complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * clear method
     * restes size to 0 and head and tail to null
     * Time complexity: O(1)
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * isEmpty method
     * 
     * @return true if the list is empty
     *         Time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Adding a value at the tail of the list
     * calls addLast
     * 
     * @param value to be added
     * @return true if the operation was successful
     *         Time complexity: O(1)
     */
    public boolean add(E item) {
        addLast(item);
        return true;
    }

    /**
     * Linear search method
     * 
     * @param o the object being searched
     * @return true if o was found in this list, false otherwise
     *         Time complexity: O(n)
     */
    public boolean contains(Object o) {
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            E element = iter.next();
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adding a value at the head of the list
     * 
     * @param value to be added
     * @return true if the operation was successful
     *         Time complexity: O(1)
     */
    public void addFirst(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Adding a value at the tail of the list
     * 
     * @param value to be added
     * @return true if the operation was successful
     *         Time complexity: O(1)
     */
    public void addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Get the value of the node at the head of the list
     * 
     * @return value of the node at the head
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(1)
     */
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Get the value of the node at the tail of the list
     * 
     * @return value of the node at the tail
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(1)
     */
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes the node at the head of the list
     * 
     * @return the value of the first element
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(1)
     */
    public E removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        E val = head.value;
        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null; // List is now empty
        }
        size--;
        return val;
    }

    /**
     * Removes the node at the tail of the list
     * 
     * @return the value of the last element
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(n)
     */
    public E removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        E val = tail.value;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
        }
        size--;
        return val;
    }

    /**
     * Remove an object o from the list
     * 
     * @param o the object to be removed
     * @return true if o was found and removed, false if o not found
     *         Time complexity: O(n)
     */
    public boolean remove(Object o) {
        Node current = head;
        while (current != null) {
            if ((o == null && current.value == null) || (o != null && o.equals(current.value))) {
                if (current == head) {
                    removeFirst();
                } else if (current == tail) {
                    removeLast();
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * toString method
     * 
     * @return a formatted string that contains the values of all the nodes in the
     *         list
     *         Time complexity: O(n)
     */
    public String toString() {
        String output = "[";
        Node node = head;
        while (node.next != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += node.value + "]";
        return output;
    }

    /**
     * returns the index of the first occurrence of the specified element or -1 if
     * not found
     */
    public int indexOf(Object o) {
        int index = 0;
        Node current = head;
        while (current != null) {
            if ((o == null && current.value == null) || (o != null && o.equals(current.value))) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * returns the index of the last occurrence of the specified element or -1 if
     * not found
     */
    public int lastIndexOf(Object o) {
        int index = size - 1;
        Node current = tail;
        while (current != null) {
            if ((o == null && current.value == null) || (o != null && o.equals(current.value))) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    /**
     * removes and returns the element at the specified index
     * throws arrayindexoutofboundsexception if index is invalid
     */
    public E remove(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (i == size - 1) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        Node current = head;
        for (int x = 0; x < i; x++) {
            current = current.next;
        }
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        return current.value;
    }

    /**
     * sorts the elements of the list using the specified comparator
     */
    public void sort(Comparator<E> c) {
        PriorityQueue<E> pqHeap = new PriorityQueue<>(c);
        Node current = head;
        while (current != null) {
            pqHeap.offer(current.value);
            current = current.next;
        }
        clear();
        while (!pqHeap.isEmpty()) {
            addLast(pqHeap.poll());
        }
    }

    /**
     * creates and returns a deep copy of the linkedlist
     */
    public Object clone() {
        LinkedList<E> ll = new LinkedList<E>();
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            ll.add(iter.next());
        }
        return ll;
    }

    /**
     * adds all elements from the specified collection to the list
     */
    @Override
    public boolean addAll(Collection<E> c) {
        if (c == null || c.size() == 0) {
            return false;
        }
        Iterator<E> iterator = c.iterator();
        while (iterator.hasNext()) {
            addLast(iterator.next());
        }
        return true;
    }

    /**
     * retains only the elements in the list that are also in the collection
     */
    @Override
    public boolean retainAll(Collection<E> c) {
        if (c == null) {
            System.out.println("Collection cannot be null.");
            return false;
        }
        boolean change = false;
        Node temp = head;
        while (temp != null) {
            Node nextN = temp.next;
            if (!c.contains(temp.value)) {
                remove(temp.value);
                change = true;
            }
            temp = nextN;
        }
        return change;
    }

    /**
     * converts the linkedlist into an array of objects
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) { 
            arr[i] = current.value;
            current = current.next;
        }
        return arr;
    }

    /**
     * returns the element at the specified index
     * throws indexoutofboundsexception if index is invalid
     */
    @Override
    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        Node current = head;
        for (int count = 0; count < i; count++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * removes all elements from the list that are also in the specified collection
     */
    @Override
    public boolean removeAll(Collection<E> c) {
        if (c == null) {
            System.out.println("Collection cannot be null.");
            return false;
        }
        boolean change = false;
        Node temp = head;
        while (temp != null) {
            Node nextN = temp.next;
            if (c.contains(temp.value)) {
                remove(temp.value);
                change = true;
            }
            temp = nextN;
        }
        return change;
    }

    /**
     * replaces the element at the specified index with the specified value
     * throws arrayindexoutofboundsexception if index is invalid
     */
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E last = current.value;
        current.value = e;
        return last;
    }

    /**
     * adds the specified element at the specified index
     * throws arrayindexoutofboundsexception if index is invalid
     */
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            Node current = head;
            Node previous = null;
            for (int j = 0; j < i; j++) {
                previous = current;
                current = current.next;
            }
            Node newN = new Node(e);
            previous.next = newN;
            newN.next = current;
            size++;
        }
    }

    /**
     * iterator method
     * 
     * @override iterator() from the interface Collection
     * @return an iterator object pointing to the first value in the list
     *         Time complexity: O(1)
     */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * creates a listiterator starting at the beginning of the list
     */
    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    /**
     * creates a listiterator starting at the specified index
     * throws indexoutofboundsexception if index is invalid
     */
    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index);
    }

    /**
     * Inner class that implements the interface ListIterator
     */
    private class LinkedListIterator implements ListIterator<E> {
        private Node previous, current, lastReturned;

        /**
         * default constructor for linkedlistiterator
         * initializes the iterator at the beginning of the list
         * sets current to head, previous to null, and lastreturned to null
         */
        public LinkedListIterator() {
            current = head;
            previous = null;
            lastReturned = null;
        }

        /**
         * constructor for linkedlistiterator starting at a given index
         * positions the iterator at the specified index in the list
         * throws indexoutofboundsexception if index is out of range
         * time complexity is o(n)
         */
        public LinkedListIterator(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }
            current = head;
            previous = null;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            lastReturned = null;
        }

        /**
         * hasNext method
         * 
         * @return true if the current is not null
         *         Time complexity: O(1)
         */
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * next method
         * 
         * @return the value of the node referenced by current and
         *         modifies current to hold the reference of the next node
         * @throws NoSuchElementException if current is null
         *                                Time complexity: O(1)
         */
        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            lastReturned = current; 
            E value = current.value;
            previous = current;
            current = current.next;
            return value;
        }

        /**
         * checks if there is a previous element in the list
         * returns true if previous is not null
         */
        public boolean hasPrevious() {
            return (previous != null);
        }

        /**
         * moves the iterator to the previous element in the list
         * updates current and lastreturned to previous
         * throws nosuchelementexception if there is no previous element
         */
        public E previous() {
            if (previous == null) {
                throw new NoSuchElementException();
            }
            current = previous;
            lastReturned = previous;
            E value = previous.value;
            previous = previous.previous;
            return value;
        }

        /**
         * unsupported operation for modifying the last returned element
         * throws unsupportedoperationexception
         */
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported operation for adding an element to the list
         * throws unsupportedoperationexception
         */
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported operation for getting the index of the next element
         * throws unsupportedoperationexception
         */
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported operation for getting the index of the previous element
         * throws unsupportedoperationexception
         */
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported operation for removing an element from the list
         * throws unsupportedoperationexception
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
