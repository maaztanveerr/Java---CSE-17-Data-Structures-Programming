import java.util.ArrayList;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {

        // Test the class Rational
        System.out.println("\nTest case 1: Arithmetic on Fractions");
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(3, 2);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        System.out.println("\nTest case 2: Arithmetic on Fractions");
        r1 = new Rational(2, 3);
        r2 = new Rational(3, 5);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        System.out.println("\nTest case 3: Arithmetic on Fractions");
        r1 = new Rational(1, 4);
        r2 = new Rational(3, 4);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        System.out.println("\nTest case 4: Arithmetic on Fractions");
        r1 = new Rational(4, 8);
        r2 = new Rational(3, 9);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        // Creating a dataset of numbers
        ArrayList<Number> numbers = new ArrayList<>(25);
        for (int i = 0; i < 5; i++) {
            numbers.add(new Rational((int) (Math.random() * 10), (int) (Math.random() * 9) + 1));
        }
        for (int i = 0; i < 5; i++) {
            numbers.add((int) (Math.random() * 1000000000));
        }
        for (int i = 0; i < 5; i++) {
            numbers.add((long) (Math.random() * 10000000000L));
        }
        for (int i = 0; i < 5; i++) {
            numbers.add(Math.random() * 1e20);
        }
        for (int i = 0; i < 5; i++) {
            numbers.add(Math.random() * 1e40);
        }
        java.util.Collections.shuffle(numbers);

        // print the list of numbers
        System.out.println("\n\nTest case 5: Generic print method");
        System.out.println("List of Numbers");
        print(numbers);

        // Test Searching for a number
        System.out.println("\n\nTest case 6: Generic Search Method (fail)");
        Number n = new Rational(3, 4);
        int index = search(numbers, n);
        if (index == -1) {
            System.out.println("Number " + n + " not found.");
        } else {
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 7: Generic Search Method (success)");
        n = numbers.get(4);
        index = search(numbers, n);
        if (index == -1) {
            System.out.println("Number " + n + " not found.");
        } else {
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 8: Generic Search Method (success)");
        n = numbers.get(9);
        index = search(numbers, n);
        if (index == -1) {
            System.out.println("Number " + n + " not found.");
        } else {
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 9: Generic Search Method (success)");
        n = numbers.get(12);
        index = search(numbers, n);
        if (index == -1) {
            System.out.println("Number " + n + " not found.");
        } else {
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 10: Generic Search Method (fail)");
        n = 35.75;
        index = search(numbers, n);
        if (index == -1) {
            System.out.println("Number " + n + " not found.");
        } else {
            System.out.println("Number " + n + " found at index " + index);
        }

        // Test Sorting by double
        System.out.println("\n\nTest case 11: Generic Sort Method with a Comparator");
        sort(numbers, new ComparatorByDouble());
        System.out.println("List of Numbers sorted by their double values");
        print(numbers);
    }

    /**
     * searches for a key in the list using a helper method
     * @param list the list to search 
     * @param key the item to find
     * @return the index of the key if found, otherwise -1
     */
    public static <E> int search(ArrayList<E> list, E key) {
        return helpSearch(list, key, 0);
    }

    /**
     * prints each element in the list on a new line
     * @param list the list to print
     */
    public static <E> void print(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * sorts the list using the given comparator with a selection sort algorithm
     * @param list the list to sort
     * @param c the comparator to determine the order
     */
    public static <E> void sort(ArrayList<E> list, Comparator<E> c) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (c.compare(list.get(j), list.get(min)) < 0) {
                    min = j;
                }
            }
            // swap 
            E temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }

    /**
     * recursively searches for a key starting from a given index
     * @param list the list to search 
     * @param key the item to find
     * @param index the current index to check
     * @return the index of the key if found, otherwise -1
     */
    private static <E> int helpSearch(ArrayList<E> list, E key, int index) {
        if (index >= list.size()) {
            return -1; 
        }
        if (list.get(index).equals(key)) {
            return index; 
        }
        return helpSearch(list, key, index + 1); //recursion
    }
}
