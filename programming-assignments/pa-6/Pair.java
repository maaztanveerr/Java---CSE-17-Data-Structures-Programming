public class Pair<E1, E2> {
    private E1 first;
    private E2 second;

    /**
     * creates a pair with two elements
     * 
     * time complexity: o(1)
     * 
     * @param first  the first element
     * @param second the second element
     */
    public Pair(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * gets the first element
     * 
     * time complexity: o(1)
     * 
     * @return first element
     */
    public E1 getFirst() {
        return first;
    }

    /**
     * gets the second element
     * 
     * time complexity: o(1)
     * 
     * @return second element
     */
    public E2 getSecond() {
        return second;
    }

    /**
     * sets the first element
     * 
     * time complexity: o(1)
     * 
     * @param first new first element
     */
    public void setFirst(E1 first) {
        this.first = first;
    }

    /**
     * sets the second element
     * 
     * time complexity: o(1)
     * 
     * @param second new second element
     */
    public void setSecond(E2 second) {
        this.second = second;
    }

    /**
     * returns a string showing both elements
     * 
     * time complexity: o(1)
     * 
     * @return string representation of the pair
     */
    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }

    /**
     * checks if two pairs are the same based on both elements
     * 
     * time complexity: o(1)
     * 
     * @param o other object to compare
     * @return true if both elements are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair<E1, E2> p = (Pair) o;
            return this.getFirst().equals(p.getFirst()) && this.getSecond().equals(p.getSecond());
        }
        return false;
    }
}
