import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.ArrayList;

public class PriorityQueue<E> {
    // ArrayList where the nodes of the heap are stored
    private ArrayList<E> list;
    private Comparator<E> comp;
    /**
     * Default Constructor
     */
    public PriorityQueue(){
        list = new ArrayList<>();
        comp = null;
    }
    /**
     * Constructor with one arg
     * @param c the comparator object used to order the nodes of the heap
     */
    public PriorityQueue(Comparator<E> c){
        list = new ArrayList<>();
        comp = c;
    }
    /**
     * Method size
     * @return the number of nodes in the heap
     */
    public int size(){
        return list.size(); 
    }
    /**
     * Method isEmpty
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
     * Method to empty the heap
     */
    public void clear(){
        list.clear(); 
    }
    /**
     * Method toString
     * @return a formatted string containing the values of the nodes of the heap
     */
    public String toString(){
        return list.toString();
    }
    /**
     * Method getRoot
     * @return the value of the root
     */
    public E peek(){
        if (list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.get(0);
    }
    /**
     * Method offer
     * @param value to be added to the heap
     * reconstructs the heap to keep the MinHeap properties
     */
    public void offer(E value) {
        list.add(value);
        int currentIndex = list.size()-1; 
        while(currentIndex > 0) {
            int parentIndex = (currentIndex-1)/2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            int comparison = (comp == null)? ((Comparable)(current)).compareTo(parent): comp.compare(current, parent);
            if(comparison < 0) {
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            }
            else
                break;
            currentIndex = parentIndex;
        }
    }
    /**
     * Method pop
     * @return the value of the root, null if the heap is empty
     * reconstructs the heap to keep the MinHeap properties
     */
    public E poll() {
        if(list.isEmpty()) 
            return null;
        E removedItem = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left >= list.size()) 
                break;
            int minIndex = left;
            E min = list.get(minIndex);
            if (right < list.size()){
                int comparison = (comp == null)? ((Comparable)(min)).compareTo(list.get(right)): comp.compare(min, list.get(right));
                if(comparison > 0)
                    minIndex = right;
            }
            E current = list.get(currentIndex);
            min = list.get(minIndex); 
            int comparison = (comp == null)? ((Comparable)(current)).compareTo(min): comp.compare(current, min);           
            if(comparison > 0){
                list.set(minIndex, current);
                list.set(currentIndex, min);
                currentIndex = minIndex;
            }
            else
                break;
        }
        return removedItem;
    }
}