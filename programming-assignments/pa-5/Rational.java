/**
 * the rational class represents a rational number (fraction) 
 * with a numerator and a denominator.
 */
public class Rational extends Number {
    private int numerator;
    private int denominator;

    /**
     * default constructor that sets both numerator and denominator to 0.
     */
    public Rational() {
        numerator = 0;
        denominator = 0;
    }

    /**
     * constructor that sets the numerator and denominator to given values.
     * @param numerator the numerator of the rational number.
     * @param denominator the denominator of the rational number.
     */
    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * gets the numerator of this rational number
     * @return the numerator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * gets the denominator of this rational number
     * @return the denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * sets the numerator of this rational number
     * @param numerator the new numerator
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * sets the denominator of this rational number
     * @param denominator the new denominator
     */
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /**
     * adds this rational number to another and returns the result
     * @param r the other rational number to add
     * @return a new rational that is the sum of the two numbers
     */
    public Rational add(Rational r) {
        int den = denominator * r.denominator;
        int num = numerator * r.denominator + denominator * r.numerator;
        Rational result = new Rational(num, den);
        result.reduce();
        return result;
    }

    /**
     * subtracts another rational number from this one
     * @param r the rational number to subtract
     * @return a new rational that is the difference of the two numbers
     */
    public Rational sub(Rational r) {
        int den = denominator * r.denominator;
        int num = numerator * r.denominator - denominator * r.numerator;
        Rational result = new Rational(num, den);
        result.reduce();
        return result;
    }

    /**
     * multiplies this rational number with another
     * @param r the rational number to multiply with
     * @return a new rational that is the product of the two numbers
     */
    public Rational mult(Rational r) {
        int den = denominator * r.denominator;
        int num = numerator * r.numerator;
        Rational result = new Rational(num, den);
        result.reduce();
        return result;
    }

    /**
     * divides this rational number by another
     * @param r the rational number to divide by
     * @return a new rational that is the quotient of the division
     * @throws ArithmeticException if division by zero occurs
     */
    public Rational div(Rational r) {
        int num = numerator * r.denominator; // a * d
        int den = denominator * r.numerator; // b * c

        if (den == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }

        Rational result = new Rational(num, den);
        result.reduce();
        return result;
    }

    /**
     * finds the greatest common divisor (gcd) of the numerator and denominator
     * @return the gcd value
     */
    private int gcd(){
        return helpGCD(this.numerator, this.denominator);
    }

    /**
     * helper method to find gcd using recursion
     * @param a the first number
     * @param b the second number
     * @return the gcd of a and b
     */
    private int helpGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return helpGCD(b, a % b);
    }
    
    /**
     * reduces the fraction by dividing both numerator and denominator by their gcd
     */
    private void reduce(){
        int gcdValue = gcd();
        this.numerator = this.numerator / gcdValue;
        this.denominator = this.denominator / gcdValue;
    }

    /**
     * converts the rational number to an integer 
     * @return the integer value of the fraction
     */
    public int intValue(){
        return (int) getNumerator() / (int) getDenominator();
    }

    /**
     * converts the rational number to a long
     * @return the long value of the fraction
     */
    public long longValue(){
        return (long) getNumerator() / (long) getDenominator();
    }

    /**
     * converts the rational number to a float 
     * @return the float value of the fraction
     */
    public float floatValue(){
        return (float) getNumerator() / (float) getDenominator();
    }

    /**
     * converts the rational number to a double value
     * @return the double value of the fraction
     */
    public double doubleValue(){
        return (double) getNumerator() / (double) getDenominator();
    }

    /**
     * checks if this rational number is equal to another object
     * @param obj the object to compare with
     * @return true if the object is a rational with the same numerator and denominator
     */
    @Override
    public boolean equals(Object obj) {
        boolean status = false;
        if(obj instanceof Rational){
            Rational r = (Rational) obj;
            if (this.numerator == r.numerator && this.denominator == r.denominator) {
                status = true;
            }
        }
        return status;
    }

    /**
     * returns the string form of the rational number
     * handles cases where the denominator is 1, the numerator is 0, or negative denominators
     * @return the rational number as a string
     */
    @Override
    public String toString() {
        if (denominator == 1) {
            return numerator + " ";
        } 
        if (numerator == 0) {
            return "0";
        }
        if (denominator < 0) {
            return String.format("-%d/%d", numerator, denominator * -1);
        }
        if (numerator == denominator) {
            return "1";
        }
        return String.format("%d/%d", numerator, denominator);
    }
}