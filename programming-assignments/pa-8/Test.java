import java.util.Comparator;
import java.util.ListIterator;
import java.util.Iterator;

public class Test{
    public static void main(String[] args){
        System.out.println("\nTesting the ArrayList\n==================================================");
        List<Integer> alist = new ArrayList<>();
        testList(alist);

        System.out.println("\n\nTesting the LinkedList\n==================================================");
        List<Integer> llist = new LinkedList<>();
        testList(llist);
    }
    public static void testList(List<Integer> list){
        System.out.println("\nTest case 1: The add methods: add(E), add(int,E), addFirst(E), addLast(E)");
        list.add(11);
        list.add(22);
        list.add(1, 33);
        list.addFirst(44);
        list.add(55);
        list.add(3, 66);
        list.addLast(77);
        list.add(5, 88);
        System.out.println(list);

        System.out.println("\nTest case 2: The clone method");
        List<Integer> copylist = clone(list);
        System.out.println(list + ".clone() = " + copylist);

        System.out.println("\nTest case 3: The collection methods: addAll, retainAll, removeAll");
        List<Integer> nlist = null;
        if(list instanceof ArrayList)
            nlist = new ArrayList<>();
        else if(list instanceof LinkedList)
            nlist = new LinkedList<>();
        nlist.add(111);
        nlist.add(77);
        nlist.add(222);
        nlist.add(55);
        copylist = clone(nlist);
        System.out.print(copylist + ".addAll(" + list + ") = ");
        copylist.addAll(list);
        System.out.println(copylist);

        copylist = clone(list);
        System.out.print(copylist + ".retainAll(" + nlist + ") = ");
        copylist.retainAll(nlist);
        System.out.println(copylist);

        copylist = clone(list);
        System.out.print(copylist + ".removeAll(" + nlist + ") = ");
        copylist.removeAll(nlist);
        System.out.println(copylist);

        System.out.println("\nTest case 4: The getters: get, getFirst, getLast");
        System.out.println(list + ".get(3) = " + list.get(3));
        System.out.println(list + ".getFirst() = " + list.getFirst());
        System.out.println(list + ".getLast() = " + list.getLast());

        System.out.println("\nTest case 5: The set method");
        System.out.println(list + ".set(3, 44) = " + list.set(3, 44));
        System.out.println("Modified list : " + list);

        System.out.println("\nTest case 6: The remove methods: remove(Object), remove(int), removeFirst, removeLast");
        Integer i = 55;
        System.out.println(copylist);
        System.out.println("remove(55) = " + copylist.remove(i));
        System.out.println("removeFirst() = " + copylist.removeFirst());
        System.out.println("removeLast() = " + copylist.removeLast());
        System.out.println("remove(2) = " + copylist.remove(2));
        System.out.println("Modified list: " + copylist);

        System.out.println("\nTest case 7: The search methods: contains, indexOf, lastIndexOf");
        System.out.println(list + ".contains(88) = " + list.contains(88));
        System.out.println(list + ".contains(111) = " + list.contains(111));
        System.out.println(list + ".indexOf(55) = " + list.indexOf(55));
        System.out.println(list + ".indexOf(222) = " + list.indexOf(222));
        System.out.println(list + ".lastIndexOf(77) = " + list.lastIndexOf(77));
        System.out.println(list + ".lastIndexOf(222) = " + list.lastIndexOf(222));
        System.out.println("Unmodified list: " + list);

        System.out.println("\nTest case 8: The iterator methods: iterator, listIterator, listIterator(int)");
        Iterator<Integer> iter = list.iterator();
        System.out.print("Iterator from 0 to " + (list.size()-1) + ": ");
        while(iter.hasNext()){
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        ListIterator<Integer> liter = list.listIterator();
        System.out.print("ListIterator from 0 to " + (list.size()-1) + ", then from " + (list.size()-1) + " to 0: ");
        while(liter.hasNext()){
            System.out.print(liter.next() + " ");
        }
        while(liter.hasPrevious()){
            System.out.print(liter.previous() + " ");
        }
        System.out.println();
        System.out.print("ListIterator from 3 to 0" + ", then from 0 to " + (list.size()-1) + ": ");
        liter = list.listIterator(4);
        while(liter.hasPrevious()){
            System.out.print(liter.previous() + " ");
        }
        while(liter.hasNext()){
            System.out.print(liter.next() + " ");
        }
        System.out.println();


        System.out.println("\nTest case 9: The equals method");
        System.out.println(list + ".equals(" + nlist + ") ? " + list.equals(nlist));
        System.out.println(list + ".equals(" + list + ") ? " + list.equals(list));


        System.out.println("\nTest case 10: The sort method");
        System.out.println("list1: " + list);
        nlist = clone(list);
        list.sort(null);
        System.out.println("list1 sorted using the natural ordering: " + list);

        nlist.clear();
        nlist.add(1000); nlist.add(900); nlist.add(1500); nlist.add(33); nlist.add(555); nlist.add(88);
        Comparator<Integer> c = new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                String s1 = i1.toString();
                String s2 = i2.toString();
                return s1.length() - s2.length();
            }
        };
        System.out.println("list2: " + nlist);
        nlist.sort(c);
        System.out.println("list2 sorted using the number of digits: " + nlist);

        System.out.println("\nTest case 11: The toArray method");
        Object[] array = list.toArray();
        System.out.print(list + ".toArray() = [");
        for(Object o: array){
            System.out.print(o + " ");
        }
        System.out.println("]");
    }
    public static List<Integer> clone(List<Integer> list){
        if(list instanceof ArrayList)
            return (List<Integer>) ((ArrayList<Integer>)list).clone();
        else if (list instanceof LinkedList)
            return (List<Integer>) ((LinkedList<Integer>)list).clone();
        else
            return null;
    }
}