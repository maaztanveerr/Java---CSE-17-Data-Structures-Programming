import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test{
    public static void main(String[] args){
        System.out.println("TreeMap with natural ordering");
        TreeMap<String, ArrayList<String>> tree = new TreeMap<>();
        
        readFile(tree, "flowers.txt");
        System.out.print("\nTest case 1: Tree constructued correctly \n");
        tree.inorder();
        System.out.println();

        System.out.print("\nTest case 2: Method contains (successful)\n");
        System.out.println("Tree contains \"Rose\"? " + tree.contains("Rose"));
        System.out.print("\nTest case 3: Method contains (fail)\n");
        System.out.println("Tree contains \"Coquelicot\"? " + tree.contains("Coquelicot"));
        System.out.print("\nTest case 4: Method remove (successful)\n");
        System.out.println("\'Narcissus\" removed? " + tree.remove("Narcissus"));
        System.out.print("\nTest case 5: Method contains (fail)\n");
        System.out.println("\"Musk\" removed? " + tree.remove("Musk"));
        System.out.print("\nTest case 6: Method first\n");
        System.out.println("First: " + tree.first());
        System.out.print("\nTest case 7: Method last\n");
        System.out.println("Last : " + tree.last());
        System.out.print("\nTest case 8: Method ceiling (succesful)\n");
        System.out.println("Ceiling(\"Daisy\") : " + tree.ceiling("Daisy"));
        System.out.print("\nTest case 9: Method ceiling (fail)\n");
        System.out.println("Ceiling(\"Zebra\") : " + tree.ceiling("Zebra"));
        System.out.print("\nTest case 10: Method floor (successful)\n");
        System.out.println("floor(\"Iris\") : " + tree.floor("Iris"));
        System.out.print("\nTest case 11: Method floor (fail)\n");
        System.out.println("floor(\"Art\") : " + tree.floor("Art"));
        System.out.println("\nTestcase 12: Method keys");
        System.out.println("List of flowers : " + tree.keys());
        System.out.println("\nTestcase 13: Method values");
        System.out.println("List of colors : " + tree.values());


        System.out.println("\n\nTreeMap with a comparator");
        tree.clear();
        tree = new TreeMap<>(new Comparator<String>(){
            public int compare(String s1, String s2){
                return s1.hashCode() - s2.hashCode();
            }
        });
        readFile(tree, "flowers.txt");
        System.out.print("\nTest case 14: Tree constructued correctly \n");
        tree.inorder();
        System.out.println();

        System.out.print("\nTest case 15: Method contains (successful)\n");
        System.out.println("Tree contains \"Rose\"? " + tree.contains("Rose"));
        System.out.print("\nTest case 16: Method contains (fail)\n");
        System.out.println("Tree contains \"Coquelicot\"? " + tree.contains("Coquelicot"));
        System.out.print("\nTest case 17: Method remove (successful)\n");
        System.out.println("\"Narcissus\" removed? " + tree.remove("Narcissus"));
        System.out.print("\nTest case 18: Method contains (fail)\n");
        System.out.println("\"Musk\" removed? " + tree.remove("Musk"));
        System.out.print("\nTest case 19: Method first\n");
        System.out.println("First: " + tree.first());
        System.out.print("\nTest case 20: Method last\n");
        System.out.println("Last : " + tree.last());
        System.out.print("\nTest case 21: Method ceiling (succesful)\n");
        System.out.println("Ceiling(\"Daisy\") : " + tree.ceiling("Daisy"));
        System.out.print("\nTest case 22: Method ceiling (long string)\n");
        System.out.println("Ceiling(\"This string is long to get a different hash code\") : " + tree.ceiling("Zebra"));
        System.out.print("\nTest case 23: Method floor (successful)\n");
        System.out.println("floor(\"Iris\") : " + tree.floor("Iris"));
        System.out.print("\nTest case 24: Method floor (short string)\n");
        System.out.println("floor(\"0\") : " + tree.floor("0"));
        System.out.println("\nTestcase 25: Method keys");
        System.out.println("List of flowers : " + tree.keys());
        System.out.println("\nTestcase 26: Method values");
        System.out.println("List of colors : " + tree.values());
    }
    public static void readFile(TreeMap<String,ArrayList<String>> tree, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split(",");
                ArrayList<String> colors = new ArrayList<>();
                for(int i=1; i<items.length; i++){
                    colors.add(items[i]);
                }
                tree.add(items[0], colors);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
}