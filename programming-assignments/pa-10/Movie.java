/**
 * represents a movie with attributes like id, title, genre, number of ratings, and average rating
 */
public class Movie implements Comparable<Movie> {
    private int id;
    private String title;
    private String genre;
    private int ratings; // Number of ratings
    private double rating; // Average rating

    /**
     * constructs a movie with given id, title, and genre
     * initializes ratings and average rating to zero
     * @param id the unique id of the movie
     * @param title the title of the movie
     * @param genre the genre of the movie
     */
    public Movie(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.ratings = 0;
        this.rating = 0.0;
    }

    /**
     * gets the id of the movie
     * @return the movie id
     */
    public int getID() { return id; }

    /**
     * gets the title of the movie
     * @return the movie title
     */
    public String getTitle() { return title; }

    /**
     * gets the genre of the movie
     * @return the movie genre
     */
    public String getGenre() { return genre; }
    /**
     * gets the number of ratings the movie has received
     * @return the number of ratings
     */
    public int getRatings() { return ratings; }

    /**
     * gets the average rating of the movie
     * @return the average rating
     */
    public double getRating() { return rating; }

    /**
     * sets the id of the movie
     * @param id the new id for the movie
     */
    public void setID(int id) { this.id = id; }

    /**
     * sets the title of the movie
     * @param title the new title for the movie
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * sets the genre of the movie
     * @param genre the new genre for the movie
     */
    public void setGenre(String genre) { this.genre = genre; }

    /**
     * sets the number of ratings for the movie
     * @param rs the new number of ratings
     */
    public void setRatings(int rs) { this.ratings = rs; }

    /**
     * adds a new rating to the movie and updates the average rating
     * @param r the new rating to add
     */
    public void addRating(double r) {
        double total = rating * ratings;
        total = total + r;
        ratings++;
        rating = total / ratings;
    }

    /**
     * formats the movie attributes into a readable string
     * @return the formatted string of movie details
     */
    @Override
    public String toString() {
        return String.format("%-5d\t%-50s\t%-15d\t%-10.1f\n", id, title, ratings, rating);
    }

    /**
     * compares two movies based on the number of ratings
     * @param m the other movie to compare
     * @return a negative, zero, or positive value based on the comparison
     */
    public int compareTo(Movie m) {
        return Integer.compare(this.ratings, m.ratings);
    }
}