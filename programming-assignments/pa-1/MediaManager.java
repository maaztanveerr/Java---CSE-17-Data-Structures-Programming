public class MediaManager{
    public static void main(String[] args){

        System.out.println("Test case 1: create a new media list");
        MediaList myMediaList = new MediaList();
        System.out.println("A new media list was created with " + myMediaList.size() + " media");

        System.out.println("\nTest case 2: add new media (successful)");
        myMediaList.add(new Song("Time After Time", "Pop Rock", 1983, 3500, "Cyndi Lauper"));
        myMediaList.add(new Movie("Titanic", "Drama", 1997, 30000000, "James Cameron", 2264743305L));
        myMediaList.add(new Song("Help", "Rock", 1965, 885, "The Beatles"));
        myMediaList.add(new Movie("A Beautiful Mind", "Drama", 2001, 25000000, "Ron Howard", 313000000));
        myMediaList.add(new Song("Rhythm Nation", "Pop", 1989, 775, "Janet Jackson"));
        myMediaList.add(new Movie("Avatar", "Action", 2009, 50000000, "James Cameron", 2923706026L));
        myMediaList.add(new Song("Beat It", "Pop", 1982, 1200, "Michael Jackson"));
        myMediaList.add(new Movie("Harry Potter and the Deathly Hallows", "Supernatural", 2000, 27500000, "David Yates", 1356841356));
        myMediaList.add(new Movie("Inside Out 2", "Animation", 2024, 35500000, "Kelsey Mann", 1460000000));
        myMediaList.add(new Song("Espresso", "Pop", 2024, 5000, "Sabrina Carpenter"));
        System.out.println(myMediaList.size() + " media added to the media list ");

        System.out.println("\nTest case 3: add new media (fail)");
        if(!myMediaList.add(new Song("Birds Of A Feather", "Alternative Rock", 2024, 5500, "Billie Eilish"))){
            System.out.println("The media list is full. Cannot add new media");
        }

        System.out.println("\nTest case 4: find media by title (successful)");
        String title = "Beat It";
        Media media = myMediaList.findTitle(title);
        if(media == null){
            System.out.println("No media found with title \""+ title + "\" ");
        }
        else{
            System.out.println("Media found with title \"" + title + "\": ");
            System.out.println(media);
        }

        System.out.println("\nTest case 5: find media by title (fail)");
        title = "Love";
        media = myMediaList.findTitle(title);
        if(media == null){
            System.out.println("No media found with title \"" + title + "\" ");
        }
        else{
            System.out.println("Media found with title \"" + title + "\" ");
            System.out.println(media);
        }

        System.out.println("\nTest case 6: find media by category (one media found)");
        Media[] found = myMediaList.findCategory("Action");
        if(found == null){
            System.out.println("No media found with category \"Action\" ");
        }
        else{
            System.out.println(found.length + " media found with category \"Action\" ");
            for(Media m: found){
                System.out.println(m);
            }
        }

        System.out.println("\nTest case 7: find media by category (more than one media found)");
        found = myMediaList.findCategory("Pop");
        if(found == null){
            System.out.println("No media found with category: ");
        }
        else{
            System.out.println(found.length + " media found with category \"Pop\"");
            for(Media m: found){
                System.out.println(m);
            }
        }

        System.out.println("\nTest case 8: find media by category (no media found)");
        found = myMediaList.findCategory("Fantasy");
        if(found == null){
            System.out.println("No media found with category \"Fantasy\"");
        }
        else{
            System.out.println(found.length +  " media found with category \"Fantasy\"");
            for(Media m: found){
                System.out.println(m);
            }
        }
        

        System.out.println("\nTest case 9: find media by year (one media found)");
        found = myMediaList.findYear(2000);
        if(found == null){
            System.out.println("No media found in the year 2000");
        }
        else{
            System.out.println(found.length + " media found in the year 2000");
            for(Media m: found){
                System.out.println(m);
            }
        }

        System.out.println("\nTest case 10: find media by year (more than one media found)");
        found = myMediaList.findYear(2024);
        if(found == null){
            System.out.println("No media found in the year 2024 ");
        }
        else{
            System.out.println(found.length + " media found in the year 2024");
            for(Media m: found){
                System.out.println(m);
            }
        }

        System.out.println("\nTest case 11: find media by year (no media found)");
        found = myMediaList.findYear(1980);
        if(found == null){
            System.out.println("No media found in the year 1980");
        }
        else{
            System.out.println(found.length +  " media found in the year 1980");
            for(Media m: found){
                System.out.println(m);
            }
        }

        System.out.println("\nTest case 12: remove media (successful)");
        if(myMediaList.remove("Time After Time")){
            System.out.println("Media found with title \"Time After Time\"");
            System.out.println("Media removed successfully");
        }
        else{
            System.out.println("Media with title \"Time After Time\" not found");
        }

        System.out.println("\nTest case 13: remove media (fail)");
        if(myMediaList.remove("Love")){
            System.out.println("Media with title \"Love\" found");
            System.out.println("Media removed successfully");
        }
        else{
            System.out.println("Media with title \"Love\" not found");
        }
        
        System.out.println("\nTest case 14: view the list of media");
        System.out.println(myMediaList);

        System.out.println("\nTest case 15: sort the list by title");
        myMediaList.sort(true);
        System.out.println(myMediaList.toString());

        System.out.println("\nTest case 16: sort the list by category");
        myMediaList.sort(false);
        System.out.println(myMediaList.toString());
    }
}