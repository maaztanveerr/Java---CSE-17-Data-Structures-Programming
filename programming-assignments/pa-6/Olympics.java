import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;

/**
 * the olympics class manages athletes and countries in the olympics, tracking
 * medals and showing top performers
 */
public class Olympics {
    private ArrayList<String> countries;
    private ArrayList<Athlete> athletes;

    /**
     * sets up an olympics instance with lists for countries and athletes and
     * loads data from files
     * 
     * time complexity: o(n + m)
     * 
     * @param athleteFile      the file path for athlete data
     * @param countryFileName  the file path for country data
     */
    public Olympics(String athleteFile, String countryFileName) {
        countries = new ArrayList<>(100000);
        athletes = new ArrayList<>(100000);
        read(athletes, athleteFile);

        File countryFile = new File(countryFileName);
        try {
            Scanner scan = new Scanner(countryFile);
            while (scan.hasNextLine()) {
                countries.add(scan.nextLine().trim());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    /**
     * loads athletes from a file and adds them to the athlete list
     * 
     * time complexity: o(n)
     * 
     * @param athleteList the list to add athletes to
     * @param fileName    the file path for athlete data
     */
    public void read(ArrayList<Athlete> athletes, String filename){
        File file = new File(filename);
        try {
            Scanner read = new Scanner(file);
            if (read.hasNextLine()){
                read.nextLine();
            } 

            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] input = line.split(",");

                int id = Integer.parseInt(input[0]);
                String name = input[1];
                String country = input[2];
                int year = Integer.parseInt(input[3].trim());
                String medalType = (input[4]);
                int type = -1;
                if(input[4].equals("Gold")){
                    type = 0;
                }else if(input[4].equals("Silver")){
                    type = 1;
                }else if (input[4].equals("Bronze")){
                    type = 2;
                }
                
                Athlete a = find(new Athlete(id, name, country));
                if (a == null){
                    a = new Athlete(id, name, country);
                    add(a);
                }
                if (type != -1){
                    a.addMedal(year, type);
                }
                
            }
            read.close();
        } catch (Exception e){
            System.out.println("Cannnot find Athletes File ");
        }

    }

    /**
     * adds an athlete to the list
     * 
     * time complexity: o(1)
     * 
     * @param athlete the athlete to add
     */
    public void add(Athlete athlete) {
        athletes.add(athlete);
    }

    /**
     * finds and returns an athlete in the list
     * 
     * time complexity: o(n)
     * 
     * @param athlete the athlete to find
     * @return the athlete if found, otherwise null
     */
    public Athlete find(Athlete athlete) {
        for (Athlete a : athletes) {
            if (a.equals(athlete)) {
                return a;
            }
        }
        return null;
    }

    /**
     * removes an athlete from the list if present
     * 
     * time complexity: o(n)
     * 
     * @param athlete the athlete to remove
     * @return the removed athlete, or null if not found
     */
    public Athlete remove(Athlete athlete) {
        for (Athlete a : athletes) {
            if (a.equals(athlete)) {
                athletes.remove(a);
                return a;
            }
        }
        return null;
    }

    /**
     * returns the total number of athletes
     * 
     * time complexity: o(1)
     * 
     * @return the size of the athlete list
     */
    public int getAthleteCount() {
        return athletes.size();
    }

    /**
     * returns the total number of countries
     * 
     * time complexity: o(1)
     * 
     * @return the size of the country list
     */
    public int getCountryCount() {
        return countries.size();
    }

    /**
     * displays the top ten athletes for a specified year based on total medals
     *
     * time complexity: o(n log n)
     * 
     * @param year the year to filter the athletes by
     */
    public void viewTopTenAthletes(int year) {
        ArrayList<Athlete> athletesWithMedals = new ArrayList<>();
        for (Athlete athlete : athletes) {
            if (athlete.getTotalMedals(year) > 0) {
                athletesWithMedals.add(athlete);
            }
        }
    
        athletesWithMedals.sort(new Comparator<Athlete>() {
            @Override
            public int compare(Athlete athlete1, Athlete athlete2) {
                return athlete2.getTotalMedals(year) - athlete1.getTotalMedals(year);
            }
        });
    
        System.out.println("Top Ten Medaled Athletes in the Olympics " + year);
        for (int i = 0; i < Math.min(10, athletesWithMedals.size()); i++) {
            Athlete topAthlete = athletesWithMedals.get(i);
            System.out.printf("%-10d\t%-50s\t%-5s\t%-10d\n",
                    topAthlete.getID(), topAthlete.getName(),
                    topAthlete.getCountry(), topAthlete.getTotalMedals(year));
        }
    }

    /**
     * shows the top ten athletes by total medals across all years
     * 
     * time complexity: o(n log n)
     */
    public void viewTopTenAthletes() {
        athletes.sort(null); // Uses compareTo defined in Athlete
        for (int i = athletes.size() - 1; i >= athletes.size() - 10; i--) {
            Athlete athlete = athletes.get(i);
            System.out.printf("%-10d\t%-50s\t%-5s\t%-10d\n", athlete.getID(), athlete.getName(), athlete.getCountry(), athlete.getTotalMedals());
        }
    }

    /**
     * shows the top ten countries by medals for a specific year
     * 
     * time complexity: o(n * m + m log m)
     * 
     * @param year the year to filter by
     */
    public void viewTopTenCountries(int year) {
        ArrayList<Pair<String, Integer>> countryMedals = new ArrayList<>();
        for (int i = 0; i < athletes.size(); i++) {
            Athlete athlete = athletes.get(i);
            String country = athlete.getCountry();
            int medals = athlete.getTotalMedals(year);
            boolean found = false;
            for (int j = 0; j < countryMedals.size(); j++) {
                Pair<String, Integer> countryEntry = countryMedals.get(j);
                if (countryEntry.getFirst().equals(country)) {
                    countryEntry.setSecond(countryEntry.getSecond() + medals);
                    found = true;
                    break;
                }
            }
            if (!found) {
                countryMedals.add(new Pair<>(country, medals));
            }
        }
        countryMedals.sort(new Comparator<Pair<String, Integer>>() {
            public int compare(Pair<String, Integer> c1, Pair<String, Integer> c2) {
                return Integer.compare(c2.getSecond(), c1.getSecond());
            }
        });
        System.out.println("Top Ten Medaled Countries in the Olympics " + year);
        for (int i = 0; i < Math.min(10, countryMedals.size()); i++) {
            Pair<String, Integer> countryEntry = countryMedals.get(i);
            System.out.println(countryEntry.getFirst() + " " + countryEntry.getSecond());
        }
    }

    /**
     * shows the top ten countries by total medals across all years
     * 
     * time complexity: o(n * m + m log m)
     */
    public void viewTopTenCountries() {
        ArrayList<Pair<String, Integer>> countryMedals = new ArrayList<>();
        for (int i = 0; i < athletes.size(); i++) {
            Athlete athlete = athletes.get(i);
            String country = athlete.getCountry();
            int medals = athlete.getTotalMedals();
            boolean found = false;
            for (int j = 0; j < countryMedals.size(); j++) {
                Pair<String, Integer> countryEntry = countryMedals.get(j);
                if (countryEntry.getFirst().equals(country)) {
                    countryEntry.setSecond(countryEntry.getSecond() + medals);
                    found = true;
                    break;
                }
            }
            if (!found) {
                countryMedals.add(new Pair<>(country, medals));
            }
        }
        countryMedals.sort(new Comparator<Pair<String, Integer>>() {
            public int compare(Pair<String, Integer> c1, Pair<String, Integer> c2) {
                return Integer.compare(c2.getSecond(), c1.getSecond());
            }
        });
        System.out.println("Top Ten Medaled Countries in Olympic history");
        for (int i = 0; i < Math.min(10, countryMedals.size()); i++) {
            Pair<String, Integer> countryEntry = countryMedals.get(i);
            System.out.println(countryEntry.getFirst() + " " + countryEntry.getSecond());
        }
    }
}
