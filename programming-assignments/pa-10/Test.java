import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test{
    public static void main(String[] args){
        ArrayList<MapEntry<Integer, Movie>> movieList = new ArrayList<>(70000);
        // Creating the hashmap
        HashMapLP<Integer, Movie> moviesHMLP = new HashMapLP<>(70000, 0.5);
        HashMapSC<Integer, Movie> moviesHMSC = new HashMapSC<>(70000, 0.9);

        // Populating the array list movieList with the data from the files
        readMovies(moviesHMLP,moviesHMSC, "movies.csv");
        System.out.println(moviesHMLP.size() + " movies read from the file");
        readRatings(moviesHMLP, "ratings.csv");
        
        // print the characetristics of the two implementations of the hashmap
        System.out.println("\nHash table characteristics (Separate Chaining)");
        moviesHMSC.printCharacteristics();

        System.out.println("\nHash table characteristics (Linear Probing)");
        moviesHMLP.printCharacteristics();

        // Test the performance of the get methods in the two implementations of the hashmap
        int ids[] = {1544, 2156, 31349, 3048, 4001, 356, 5672, 6287, 25738, 26};
        testGet(moviesHMLP, moviesHMSC, ids);

        // Test the performance of the remove methods in the two implementations of the hashmap
        testRemove(moviesHMLP, moviesHMSC, ids);


        // print the characetristics of the two implementations of the hashmap after removing movies
        System.out.println("\nHash table characteristics (Separate Chaining)");
        moviesHMSC.printCharacteristics();

        System.out.println("\nHash table characteristics (Linear Probing)");
        moviesHMLP.printCharacteristics();
       
        // sort the movies
        System.out.println("\nSorting the movies from the hashtable with separate chaining");
        mergeSortMovies(moviesHMSC);
        System.out.println("\nSorting the movies from the hashtable with linear probing");
        mergeSortMovies(moviesHMLP);
        
        
    }
    
    /**
     * Method to read the file movies.csv
     * @param hm1 the first hashmap to fill with the data read from the file
     * @param hm2 the second hashmap to fill with the data from the file
     * @param filename the name of the file to read from
     */
    public static void readMovies(HashMap<Integer, Movie> hm1, HashMap<Integer, Movie> hm2, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                String title = tokens[1];
                String genre = tokens[2];
                Movie m = new Movie(id, title, genre);         
                hm1.put(id, m);
                hm2.put(id, m);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }                                                       //I will genuinely kill myself tonight <3
    /**
     * The method to read the file ratings.csv
     * @param hm the hash to update with the ratings of the movies
     * @param filename the name of the file to read from
     */
    public static void readRatings(HashMap<Integer, Movie> hm, String filename){
        try {
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                // line format: userId,movieId,rating,timestamp
                String[] tokens = line.split(",");
                // We only need movieId and rating
                int movieId = Integer.parseInt(tokens[1]);
                double r = Double.parseDouble(tokens[2]); 
                
                Movie m = hm.get(movieId);
                if(m != null) {
                    m.addRating(r);
                }
            }
            sc.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Method to compare the performance of the get(K) methods
     * @param hm1 the first hash map
     * @param hm2 the second hash map
     * @param ids the array of movie ids used to perform the search in the two hashmaps
     */
    public static void testGet(HashMap<Integer, Movie> hm1, HashMap<Integer, Movie> hm2, int[] ids){
        System.out.println("\nResults of the search operation in the two hashmaps");
        System.out.printf("%-5s\t%-50s\t%-20s\t%-20s\n", 
                          "Id", "Title","Iterations(SC:get)", "Iterations(LP:get)");
        for(int id: ids){
            Movie m = hm1.get(id);
            hm2.get(id);
            if(m == null){
                System.out.println("Movie id not found.");
            }
            else{
                System.out.printf("%-5d\t%-50s\t%-20d\t%-20d\n", 
                                  id, m.getTitle(),HashMapSC.getIterations, HashMapLP.getIterations);
            }
        }
    }
    /**
     * Method to compare the performance of the remove(K) methods
     * @param hm1 the first hash map
     * @param hm2 the second hash map
     * @param ids the array of movie ids used to perform the deletion in the two hashmaps
     */
    public static void testRemove(HashMap<Integer, Movie> hm1, HashMap<Integer, Movie> hm2, int[] ids){
        System.out.println("\nResults of the remove operation in the two hashmaps");
        System.out.printf("%-5s\t%-50s\t%-20s\t%-20s\n", 
                          "Id", "Title","Iterations(SC:remove)", "Iterations(LP:remove)");
        for(int id: ids){
            Movie m = hm1.get(id);
            hm1.remove(id);
            hm2.remove(id);
            
                System.out.printf("%-5d\t%-50s\t%-20d\t%-20d\n", 
                                id, m.getTitle(), HashMapSC.removeIterations, HashMapLP.removeIterations);
            
        }
    }
    /**
     * Method to sort the list of movies using merge sort and two different sorting criteria
     * prints the ten lowest and highest rated movies with more than 10,000 ratings 
     * @param movies the hash map with the movies to be sorted
     */
    public static void mergeSortMovies(HashMap<Integer, Movie> movies) {
        ArrayList<MapEntry<Integer, Movie>> entries = movies.toList();
        ArrayList<Movie> movieList = new ArrayList<>();
        for (MapEntry<Integer, Movie> me : entries) {
            movieList.add(me.getValue());
        }
    
        //first sort using natural ordering (based on number of ratings)
        mergeSort(movieList, null);
    
        // extract movies with more than 10,000 ratings
        ArrayList<Movie> filtered = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getRatings() > 10000) {
                filtered.add(m);
            }
        }
    
        class comparatorByRatings implements Comparator<Movie> {
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m1.getRating(), m2.getRating());
            }
        }
    
        mergeSort(filtered, new comparatorByRatings());
    
        System.out.println("Lowest Ten movies with 10,000 ratings or more:");
        System.out.printf("%-5s\t%-50s\t%-20s\t%-20s\n", "Id", "Title", "Number of ratings", "Average rating");
        for (int i = 0; i < 10 && i < filtered.size(); i++) {
            System.out.printf("%-5d\t%-50s\t%-20d\t%-10.1f\n", 
                              filtered.get(i).getID(), 
                              filtered.get(i).getTitle(), 
                              filtered.get(i).getRatings(), 
                              filtered.get(i).getRating());
        }

        System.out.println("\nHighest Ten movies with 10,000 ratings or more:");
        System.out.printf("%-5s\t%-50s\t%-20s\t%-20s\n", "Id", "Title", "Number of ratings", "Average rating");
        for (int i = filtered.size() - 1; i >= filtered.size() - 10; i--) {
            if (i >= 0 && i < filtered.size())
                System.out.printf("%-5d\t%-50s\t%-20d\t%-10.1f\n", 
                                  filtered.get(i).getID(), 
                                  filtered.get(i).getTitle(), 
                                  filtered.get(i).getRatings(), 
                                  filtered.get(i).getRating());
        }
    
        // Discuss complexity:
        // Time complexity: O(n log n) for sorting where n is number of movies. 
        // We do two sorts: one on all movies and one on filtered. Both are O(n log n) in worst case.
        // Space complexity: O(n) for mergesort and the additional lists.
    }

    /**
     * Merge Sort Method (original)
     * @param list to be sorted
     */
    public static <E extends Comparable<E>> void mergeSort(List<E> list) {
        if (list.size() > 1) {
            List<E> firstHalf =  subList(list, 0, list.size()/2);
            List<E> secondHalf = subList(list, list.size()/2, list.size());
            mergeSort(firstHalf);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }

    /**
     * Overloaded mergeSort that accepts a comparator
     * If comp is null, use natural ordering
     * @param list the list to be sorted
     * @param comp the comparator (can be null)
     */
    public static <E> void mergeSort(List<E> list, Comparator<? super E> comp) {
        if (list.size() > 1) {
            List<E> firstHalf =  subList(list, 0, list.size()/2);
            List<E> secondHalf = subList(list, list.size()/2, list.size());
            mergeSort(firstHalf, comp);
            mergeSort(secondHalf, comp);
            merge(firstHalf, secondHalf, list, comp);
        }
    }

    /**
     * subList Method to extract a deep copy of a range of elements
     * @param list to extract from
     * @param start the index at which the extraction starts
     * @param end the index at which the extraction ends
     * throws an IndexOutOfBoundsException if start or end are out of bounds or 
     * if start > end
     * @return a sublist from the element at index start inclusive to end exclusive
     *         an empty list if start is equal to end
     */
    public static <E> List<E> subList(List<E> list, int start, int end){
        if(start < 0 || start >= list.size() || 
           end < 0 || end > list.size() || start > end)
            throw new ArrayIndexOutOfBoundsException();
        ArrayList<E> sub = new ArrayList<>();
        if(start == end) return sub;
        for(int i=start; i<end; i++)
            sub.add(list.get(i));
        return sub;
    }

    /**
     * Method merge used by the merge sort method (original)
     * @param list1 the first sorted list
     * @param list2 the second sorted list
     * @param list where list1 and list2 are merged
     */
    public static <E extends Comparable<E>> void merge(List<E> list1, List<E> list2, List<E> list) {
        int list1Index = 0, list2Index = 0, listIndex = 0;
        while(list1Index < list1.size() && list2Index < list2.size()) {
            if(list1.get(list1Index).compareTo(list2.get(list2Index)) < 0) 
                list.set(listIndex++, list1.get(list1Index++));
            else
                list.set(listIndex++, list2.get(list2Index++));
        }
        // copy the remaining elements from list1 to list if any
        while(list1Index < list1.size()){
            list.set(listIndex++, list1.get(list1Index++));
        }
        // copy the remaining elements from list2 to list if any
        while(list2Index < list2.size()){
            list.set(listIndex++, list2.get(list2Index++));
        }
    }

    /**
     * Overloaded merge method that uses a comparator if provided
     * @param list1 first half
     * @param list2 second half
     * @param list merged
     * @param comp comparator (can be null)
     */
    public static <E> void merge(List<E> list1, List<E> list2, List<E> list, Comparator<? super E> comp) {
        int list1Index = 0, list2Index = 0, listIndex = 0;
        while(list1Index < list1.size() && list2Index < list2.size()) {
            int cmp;
            if(comp == null) {
                // Use natural ordering
                Comparable<E> c1 = (Comparable<E>) list1.get(list1Index);
                cmp = c1.compareTo(list2.get(list2Index));
            } else {
                cmp = comp.compare(list1.get(list1Index), list2.get(list2Index));
            }
            if(cmp < 0)
                list.set(listIndex++, list1.get(list1Index++));
            else
                list.set(listIndex++, list2.get(list2Index++));
        }

        // copy the remaining elements from list1 to list if any
        while(list1Index < list1.size()){
            list.set(listIndex++, list1.get(list1Index++));
        }
        // copy the remaining elements from list2 to list if any
        while(list2Index < list2.size()){
            list.set(listIndex++, list2.get(list2Index++));
        }
    }
    
}
