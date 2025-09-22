import java.util.LinkedList;

public class Athlete implements Comparable<Athlete> {
    private int id;
    private String name;
    private String country;
    private LinkedList<Pair<Integer, Triple<Integer, Integer, Integer>>> medals;

    /**
     * creates an athlete with a unique id, name, country, and an empty list of medals
     * 
     * time complexity: o(1)
     * 
     * @param id the athlete's id
     * @param name the athlete's name
     * @param country the athlete's country
     */
    public Athlete(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.medals = new LinkedList<>();
    }

    /**
     * gets the athlete's id
     * 
     * time complexity: o(1)
     * 
     * @return athlete's id
     */
    public int getID() {
        return id;
    }

    /**
     * sets the athlete's id
     * 
     * time complexity: o(1)
     * 
     * @param id new id for the athlete
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * gets the athlete's name
     * 
     * time complexity: o(1)
     * 
     * @return athlete's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * sets the athlete's name
     * 
     * time complexity: o(1)
     * 
     * @param name new name for the athlete
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the athlete's country
     * 
     * time complexity: o(1)
     * 
     * @return athlete's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets the athlete's country
     * 
     * time complexity: o(1)
     * 
     * @param country new country for the athlete
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * gets the total medals won by the athlete in a specified year
     * 
     * time complexity: o(n) where n is the number of years with medals
     * 
     * @param year the specified year
     * @return total medals won by the athlete in that year
     */
    public int getTotalMedals(int year) {
        for (Pair<Integer, Triple<Integer, Integer, Integer>> pair : medals) {
            if (pair.getFirst() == year) {
                Triple<Integer, Integer, Integer> counts = pair.getSecond();
                return counts.getFirst() + counts.getSecond() + counts.getThird();
            }
        }
        return 0;
    }

    /**
     * gets the total medals won by the athlete across all years
     * 
     * time complexity: o(n) where n is the number of years with medals
     * 
     * @return total medals won by the athlete across all years
     */
    public int getTotalMedals() {
        int totalMedal = 0;
        for (Pair<Integer, Triple<Integer, Integer, Integer>> pair : medals) {
            Triple<Integer, Integer, Integer> counts = pair.getSecond();
            totalMedal = totalMedal + counts.getFirst() + counts.getSecond() + counts.getThird();
        }
        return totalMedal;
    }

    /**
     * adds a medal of a specific type to the athlete's record for a specified year
     * if the year does not exist, a new entry is created
     * 
     * time complexity: o(n) where n is the number of years with medals
     * 
     * @param year the specified year
     * @param type the type of medal (0 for gold, 1 for silver, 2 for bronze)
     */
    public void addMedal(int year, int type) {
        boolean exists = false;
        for (Pair<Integer, Triple<Integer, Integer, Integer>> record : medals) {
            if (record.getFirst().equals(year)) {
                Triple<Integer, Integer, Integer> counts = record.getSecond();
                if (type == 0) {
                    counts.setFirst(counts.getFirst() + 1);
                } else if (type == 1) {
                    counts.setSecond(counts.getSecond() + 1);
                } else if (type == 2) {
                    counts.setThird(counts.getThird() + 1);
                }
                exists = true;
                break;
            }
        }
        if (!exists) {
            Triple<Integer, Integer, Integer> newCounts = new Triple<>(0, 0, 0);
            if (type == 0) {
                newCounts.setFirst(1);
            } else if (type == 1) {
                newCounts.setSecond(1);
            } else if (type == 2) {
                newCounts.setThird(1);
            }
            medals.add(new Pair<>(year, newCounts));
        }
    }   

    /**
     * compares two athletes based on the total number of medals they have won
     * 
     * time complexity: o(n) where n is the number of years with medals
     * 
     * @param a the other athlete to compare to
     * @return difference in total medals between this athlete and the other
     */
    @Override
    public int compareTo(Athlete a) {
        return getTotalMedals() - a.getTotalMedals();
    }

    /**
     * checks if two athlete objects are equal based on their ids
     * 
     * time complexity: o(1)
     * 
     * @param o the object to compare
     * @return true if the ids match, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Athlete) {
            Athlete a = (Athlete) o;
            return a.id == this.id;
        }
        return false;
    }

    /**
     * returns a string representation of the athlete with id, name, country, and total medals
     * 
     * time complexity: o(n) where n is the number of years with medals
     * 
     * @return formatted string with athlete details
     */
    @Override
    public String toString() {
        return String.format("%-10d\t%-10s\t\t%-10s\t%-10d", id, name, country, getTotalMedals());
    }
}

