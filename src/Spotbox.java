import java.util.ArrayList;
import java.util.Scanner;

/*
 * Processes and analyzes data about music
 */
public class Spotbox {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[40m";
    public static final String ANSI_BOLD = "\033[0;1m";
    String[] artists;
    String[] genres;
    String[] albums;

    // TO DO: Declare instance variables for the arrays you need.
    public Spotbox(String [] artists, String[] genres, String[] albums){
        this.artists = artists;
        this.genres = genres;
        this.albums = albums;
    }



    // TO DO: Write your methods to find the information you need about your data.
    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_GREEN+"Welcome to Spotbox! What would you like to do? " + ANSI_YELLOW +"Press 0 to search all" + ANSI_RED + ", " + ANSI_PURPLE+ "Press 1 to search artists" + ANSI_RED + ", " + ANSI_CYAN+ "Press 2 to search genres"  + ANSI_RED + ", " + ANSI_RED+ "Press 3 to search albums" + ANSI_RED + ", " + ANSI_GREEN + "Press 4 to exit" + ANSI_RESET);
        int response = scanner.nextInt();
        if(response == 1){

        } else if (response == 2) {
            String genreR ="";
            while(genreR.length() < 3){
                System.out.println(ANSI_GREEN+"What genre would you like to search for?" + ANSI_RESET);
                genreR = scanner.next();
                if(genreR.length() < 3){
                    System.out.println(ANSI_GREEN+"Please provide a more specific search" + ANSI_RESET);
                }
                else {
                    genreR = genreR.substring(0,1).toUpperCase() + genreR.substring(1);
                    genrePull(genreR);
                }
            }
        } else if (response == 3) {

        } else {
            System.out.println(ANSI_RED+"Invalid response" + ANSI_RESET);
            start();
        }
    }

    public void invalidResponse(){

    }

    public void genrePull(String inputGenre){
        ArrayList<String> albumStorage = new ArrayList<String>();
        ArrayList<String> artistStorage = new ArrayList<String>();
        for(int i = 0; i < genres.length; i++){
            if(genres[i].toLowerCase().contains(inputGenre.toLowerCase())){
                albumStorage.add(albums[i]);
                artistStorage.add(artists[i]);
            }
        }
        if (albumStorage.size() == 0){
            System.out.println(ANSI_GREEN_BACKGROUND+ ANSI_RED+"There are no albums that fall under the " + inputGenre + " genre." + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_BOLD+ ANSI_GREEN+"Here are all of the albums that fall under the " + inputGenre +" genre." + ANSI_RESET);
            for (int i = 0; i < albumStorage.size(); i++) {
                System.out.println(ANSI_CYAN + albumStorage.get(i) + ANSI_PURPLE + " by " + ANSI_YELLOW + artistStorage.get(i) + ANSI_RESET);
            }
        }
        System.out.println(ANSI_BOLD + ANSI_GREEN+ "---------------------------" + ANSI_RESET);
        start();
    }
//////////
}