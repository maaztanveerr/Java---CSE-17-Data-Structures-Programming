public class Triple<E1, E2, E3> {
    private E1 first;
    private E2 second;
    private E3 third;

    /**
     * creates a triple with three elements
     * 
     * time complexity: o(1)
     * 
     * @param f the first element
     * @param s the second element
     * @param t the third element
     */
    public Triple(E1 f, E2 s, E3 t) {
        first = f;
        second = s;
        third = t;
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
     * @param f new first element
     */
    public void setFirst(E1 f) {
        first = f;
    }

    /**
     * sets the second element
     * 
     * time complexity: o(1)
     * 
     * @param s new second element
     */
    public void setSecond(E2 s) {
        second = s;
    }

    /**
     * gets the third element
     * 
     * time complexity: o(1)
     * 
     * @return third element
     */
    public E3 getThird() { 
        return third;
    }
    
    /**
     * sets the third element
     * 
     * time complexity: o(1)
     * 
     * @param t new third element
     */
    public void setThird(E3 t) {
        third = t;
    }
    
    /**
     * returns a string showing all three elements
     * 
     * time complexity: o(1)
     * 
     * @return string representation of the triple
     */
    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ", " + third.toString() + ")";
    }
}
