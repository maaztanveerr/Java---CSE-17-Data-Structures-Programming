PA1 – Media Manager (Java)

This program (MediaManager.java) demonstrates creating and manipulating a media list with songs and movies. It runs through a series of test cases to verify functionality like adding, searching, removing, and sorting media items.

Features

Create a new media list

Add songs and movies (with capacity limit)

Find media by title

Find media by category

Find media by year

Remove media by title

View the full list of media

Sort the list by title or category

Print results for each test case to the console

How It Works

The main method runs 16 test cases in sequence:

Create a new MediaList

Add 10 media items (5 songs, 5 movies)

Attempt to add an 11th item (fails due to capacity)
4–5. Find by title (success and fail)
6–8. Find by category (1 item, many items, none)
9–11. Find by year (1 item, many items, none)
12–13. Remove by title (success and fail)

View the entire list
15–16. Sort the list by title, then by category

All results are printed to the console.

Build & Run

Compile and run with:

javac MediaManager.java
java MediaManager


No input files or command-line arguments are required — everything is hard-coded in main.
