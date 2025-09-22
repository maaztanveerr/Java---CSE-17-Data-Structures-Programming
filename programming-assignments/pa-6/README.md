# PA6 — Olympics Medal Tracker (Java)

## Overview
This project implements an **Olympics Medal Tracking System** using **Object-Oriented Design** and **Generics** in Java.  
It models athletes, countries, and medal records across multiple years, supporting analytics like **top athletes** and **top countries** by medals.

---

## Key Features
- **Athlete Class**  
  - Stores `id`, `name`, `country`, and medal history.  
  - Tracks medals by year using a `LinkedList<Pair<Integer, Triple<Integer,Integer,Integer>>>`.  
  - Supports queries for medals by year and across all years.  
  - Implements `Comparable<Athlete>` for sorting by total medals.  

- **Olympics Class**  
  - Loads athletes and countries from CSV/TXT files.  
  - Provides methods to:  
    - View **Top 10 Athletes** in a specific year.  
    - View **Top 10 Athletes** across Olympic history.  
    - View **Top 10 Countries** by medals in a year.  
    - View **Top 10 Countries** across all years.  
  - Uses `Comparator` for flexible sorting.  

- **Generic Utilities**  
  - **Pair** and **Triple** classes for structured data storage.  
  - Supports immutability with getters and setters.  
  - Includes robust `equals`, `toString`, and comparison methods.  

---

## Project Structure
PA6/
├── Athlete.java // Represents an athlete, medals, and comparisons
├── Olympics.java // Manages athletes/countries and provides analytics
├── Pair.java // Generic pair data structure
├── Triple.java // Generic triple data structure
└── Test.java // Demonstrates functionality with test cases


---

## Build and Run
```bash
# Compile
javac Athlete.java Olympics.java Pair.java Triple.java Test.java

# Run
java Test

Complexity Analysis

Adding medals: O(n)

Searching athletes: O(n)

Top 10 lists (athletes/countries): O(n log n)

Overall Olympics data load: O(n + m) for athletes + countries

Summary

This project demonstrates:

Application of inheritance, interfaces, and generics in Java.

Use of custom data structures (Pair, Triple) for clean record keeping.

Realistic data processing from CSV/TXT files.

Analytics on athletes and countries with efficient sorting and aggregation.
