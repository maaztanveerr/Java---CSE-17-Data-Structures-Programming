import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;

public class Test{
    public static void main(String[] args){

        System.out.println("\nTest case 1: Reading the csv and txt files");
        Olympics olympics = new Olympics("athletes.csv", "countries.txt");
        System.out.println("Number of athletes read from the file: " + olympics.getAthleteCount());
        System.out.println("Number of countries read from the file: " + olympics.getCountryCount());
        
        // print the list of the top 10 medaled athletes in the year 2000
        System.out.println("\nTest case 2: Testing viewTopTenAthletes for the year 2000");
        olympics.viewTopTenAthletes(2000);
        
        // print the list of the top 10 medaled athletes in the year 2016
        System.out.println("\nTest case 3: Testing viewTopTenAthletes for the year 2016");
        olympics.viewTopTenAthletes(2016);
       
        // print the list of the top 10 medaled athletes throughout the history of the Olympics
        System.out.println("\nTest case 4: Testing viewTopTenAthletes for all the years");
        olympics.viewTopTenAthletes();

        // print the list of the top 10 countries (descending number of medals) in the year 2000
        System.out.println("\nTest case 5: Testing viewTopTenCountries for the year 2000");
        olympics.viewTopTenCountries(2000);
        
        // print the list of the top 10 countries (descending number of medals) in the year 2016
        System.out.println("\nTest case 6: Testing viewTopTenCountries for the year 2016");
        olympics.viewTopTenCountries(2016);

        // print the list of the top 10 countries (descending number of medals) throughout the history of the Olympics
        System.out.println("\nTest case 7: Testing viewTopTenCountries for all the years");
        olympics.viewTopTenCountries();

    }
}