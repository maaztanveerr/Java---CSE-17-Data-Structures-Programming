PA2 – Media Manager (Java)

This program (MediaManager.java) loads a media library from a text file, supports searching by title, category, and year, allows removal by title, prints the list, sorts by title/category, and can save the library back to a text file. It runs a scripted set of test cases and prints results to the console.

Features

Load media from a CSV-like text file (e.g., media.txt)

Find media by title (exact match)

Find media by category (case-insensitive list match)

Find media by year

Handle invalid inputs (e.g., invalid category or year)

Remove media by title

Print the full library in a tabular format

Sort the library by title or category

Save the library back to a text file and reload it

File Format (media.txt)

Each line represents one media item. Two types are supported — Song and Movie.

Song,<Title>,<Category>,<Year>,<SizeKB>,<Artist>
Movie,<Title>,<Category>,<Year>,<SizeKB>,<Director>,<Revenue>

# Compile 
javac MediaManager.java

# Run (expects media.txt present; MediaManager constructs MediaList("media.txt"))
java MediaManager
