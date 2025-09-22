# PA5 — Rational Numbers & Generic Utilities (Java)

## Overview
This project demonstrates the implementation of a **Rational number type** and the use of **Java Generics** to design reusable utilities for printing, searching, and sorting. It highlights object-oriented programming principles, interface implementation, comparator design, and algorithm analysis.

---

## Key Features
- **Rational Class**  
  - Extends `Number` to represent fractions.  
  - Supports arithmetic operations: `add`, `sub`, `mult`, `div`.  
  - Reduces results using **Euclid’s GCD algorithm**.  
  - Provides conversions to `int`, `long`, `float`, and `double`.  
  - Overrides `equals` and `toString` for value semantics and clean output.  

- **ComparatorByDouble**  
  - Implements Comparator<Number>.  
  - Compares Number types (`Rational`, `Integer`, `Long`, `Double`) based on their `doubleValue()`.  

- **Generic Utilities (in Test.java)**  
  - print(ArrayList<E>): Prints elements of a list.  
  - search(ArrayList<E>, E): Recursive linear search; returns index or `-1`.  
  - sort(ArrayList<E>, Comparator<E>): Selection sort using a comparator.  

---

## Project Structure
PA5/
├── ComparatorByDouble.java // Comparator<Number> using doubleValue()
├── Rational.java // Rational class with arithmetic, reduction, conversions
└── Test.java // Demonstrates functionality and test cases


## Build and Run

# Compile
javac ComparatorByDouble.java Rational.java Test.java

# Run
java Test

## Complexity Analysis
Search: O(n)

Sort (Selection Sort): O(n²) time, O(1) space

Reduction (GCD): O(log(min(a, b)))

## Summary
This project demonstrates:

Use of inheritance (Rational extends Number) to integrate custom numeric types into Java’s hierarchy.

Application of interfaces and comparators to enable flexible, type-agnostic sorting.

Design of generic algorithms that are reusable across multiple data types.

Emphasis on robustness (e.g., handling division by zero) and clarity (clean string representations, reduced fractions).
