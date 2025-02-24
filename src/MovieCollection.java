import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movieCollection;
    private Scanner scan;

    public MovieCollection() {
        movieCollection = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    public void start() {
        readData();  // read data in from the file

        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
//                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

        System.out.println("Goodbye!");
    }


    private void readData() {
        // TODO: write this method: load the shopping list data from your shoppinglist.txt file and populate shoppingList.
        //  note that this method gets called immediately at the start of the "start" method;
        //  you only need to read the data in one time to populate the shoppingList arraylist
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runtime = Integer.parseInt(splitData[4]);
                double rating = Double.parseDouble(splitData[5]);
                Movie i = new Movie(title, cast, director, overview, runtime, rating);
                movieCollection.add(i);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void searchTitles() {
        System.out.print("Enter a title search term: ");
        String answer = scan.nextLine();
        sort(movieCollection);
        ArrayList<Movie> neededList = new ArrayList<Movie>();
        for (int i = 0; i < movieCollection.size(); i++) {
            String currentName = movieCollection.get(i).getTitle();
            if (currentName.contains(answer)) {
                neededList.add(movieCollection.get(i));
            }
        }
        if (neededList.size() > 0) {
            for (int i = 0; i < neededList.size(); i++) {
                int count = 1;
                System.out.println(count + ". " + neededList.get(i).getTitle());
            }
            System.out.println("Which movie would you like to learn more about? ");
            System.out.print("Enter number: ");
            int answer1 = scan.nextInt();
            scan.nextLine();
            System.out.println("Title: " + neededList.get(answer1 - 1).getTitle());
            System.out.println("Runtime: " + neededList.get(answer1 - 1).getRuntime());
            System.out.println("Directed by: " + neededList.get(answer1 - 1).getDirector());
            System.out.println("Cast: " + neededList.get(answer1 - 1).getCast());
            System.out.println("Overview: " + neededList.get(answer1 - 1).getOverview());
            System.out.println("User rating: " + neededList.get(answer1 - 1).getRating());
        } else {
            System.out.println("No movie titles match that search term!");
        }
        start();
    }

    private static void sort(ArrayList<Movie> words) {
        int count = 0;
        for (int i = 1; i < words.size(); i++) {
            int j = i;
            System.out.println(words.get(j - 1).getTitle());
            while (words.get(j - 1).getTitle().compareTo(words.get(j).getTitle()) > 0) {
                Movie temp = words.get(j - 1);
                words.set(j - 1, words.get(j));
                words.set(j, temp);
                j--;
                count++;
                if (j == 0) {
                    break;
                }


            }
        }

    }

}
