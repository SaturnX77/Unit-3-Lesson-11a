import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<String> albumStorage = new ArrayList<String>();
    private ArrayList<String> artistStorage = new ArrayList<String>();
    String[] artists;
    String[] genres;
    String[] albums;
    private ArrayList<String> genreConv = new ArrayList<String>();


    // TO DO: Declare instance variables for the arrays you need.
    public Spotbox(String [] artists, String[] genres, String[] albums){
        this.artists = artists;
        this.genres = genres;
        this.albums = albums;
        genreConv.addAll(Arrays.asList(genres));
        genreConv = filterDup(splitArray(genreConv));
    }



    // TO DO: Write your methods to find the information you need about your data.
    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_GREEN+"Welcome to Spotbox! What would you like to do? " + ANSI_YELLOW +"Press 0 to search all" + ANSI_RED + ", " + ANSI_PURPLE+ "Press 1 to search artists" + ANSI_RED + ", " + ANSI_CYAN+ "Press 2 to search genres"  + ANSI_RED + ", " + ANSI_RED+ "Press 3 to search albums" + ANSI_RED + ", " + ANSI_GREEN + "Press 4 to exit" + ANSI_RESET);
        int response = scanner.nextInt();
        if(response == 1){
            String artistR = "";
            while(artistR.length() < 1){
                System.out.println(ANSI_GREEN+"What artist would you like to search for?" + ANSI_RESET);
                while(artistR.length() == 0){
                    artistR = scanner.nextLine();
                }
                if(artistR.length() < 1){
                    System.out.println(ANSI_RED + "Please provide a more specific search" + ANSI_RESET);
                } else {
                    artistPull(artistR, false);
                }
            }
        } else if (response == 2) {
            String genreR ="";
            while(genreR.length() < 3){
                System.out.println(ANSI_GREEN+"What genre would you like to search for? (Press 1 to see all available genres)" + ANSI_RESET);
                while(genreR.length() == 0){
                    genreR = scanner.nextLine();
                }
                if (genreR.equals("1")){
                    genreList();
                    genreR = "";
                }
//                if(genreR.length() < 1){
//                    System.out.println(ANSI_RED+"Please provide a more specific search" + ANSI_RESET);
//                }
                else {
                    genreR = genreR.substring(0,1).toUpperCase() + genreR.substring(1);
                    genrePull(genreR, false);
                }
            }
        } else if (response == 3) {

        } else if (response == 4) {

        } else {
            System.out.println(ANSI_RED+"Invalid response" + ANSI_RESET);
            start();
        }
    }

    public void invalidResponse(){

    }

    public void genrePull(String inputGenre, boolean searchAll){
        for(int i = 0; i < genres.length; i++){
            if(genres[i].toLowerCase().contains(inputGenre.toLowerCase())){
                albumStorage.add(albums[i]);
                artistStorage.add(artists[i]);
            }
        }
        if (!searchAll){
            genrePrint(inputGenre);
        }
    }
    public void genreList(){
        System.out.println(ANSI_GREEN + "Here are all of the available genres:" + ANSI_RESET);

        for (int i = 0; i < genreConv.size(); i++) {
            if(genreConv.get(i).equals("HipHop")){
                genreConv.set(i, "Hip Hop");
            }
            System.out.print(ANSI_CYAN+ genreConv.get(i) + "  " + ANSI_RESET);
        }
        System.out.println();
    }
    public void genrePrint(String inputGenre){
        if (albumStorage.size() == 0){
            System.out.println(ANSI_BOLD+ANSI_RED+"There are no albums that fall under the " + inputGenre + " genre." + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_BOLD+ ANSI_GREEN+"Here are all of the albums that fall under the " + inputGenre +" genre." + ANSI_RESET);
            for (int i = 0; i < albumStorage.size(); i++) {
                System.out.println(ANSI_CYAN + albumStorage.get(i) + ANSI_PURPLE + " by " + ANSI_YELLOW + artistStorage.get(i) + ANSI_RESET);
            }
        }
        returnToStart();
    }


    public void artistPull(String inputArtist, boolean searchAll){
        for(int i = 0; i < artists.length; i++){
            if(artists[i].toLowerCase().contains(inputArtist.toLowerCase())){
                albumStorage.add(albums[i]);
                artistStorage.add(artists[i]);
            }
        }
        if (!searchAll){
            artistPrint(inputArtist);
        }
    }
    public void artistPrint(String inputArtist){
        if(artistStorage.size() == 0){
            System.out.println(ANSI_BOLD+ANSI_RED+"There are no artists that fall under the " + inputArtist + " name." + ANSI_RESET);
        } else {
            System.out.println(ANSI_BOLD+ ANSI_GREEN+"Here are all of the artists that contain the phrase " + inputArtist +"." + ANSI_RESET);
            ArrayList<String> temp = new ArrayList<String>();
            boolean canAdd = true;
            temp = filterDup(artistStorage);
            for (String artist : temp) {
                System.out.println(ANSI_YELLOW + artist);
            }
            System.out.println(ANSI_BOLD+ ANSI_GREEN+"Here are all of the albums that are produced by " + inputArtist +"." + ANSI_RESET);
            for (int i = 0; i < albumStorage.size(); i++) {
                System.out.println(ANSI_CYAN + albumStorage.get(i) + ANSI_PURPLE + " by " + ANSI_YELLOW + artistStorage.get(i) + ANSI_RESET);
            }
        }
        returnToStart();
    }
    public ArrayList<String> filterDup(ArrayList<String> list){
        ArrayList<String> filter = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++){
            if(!filter.contains(list.get(i))){
                filter.add(list.get(i));
            }
        }
        return filter;
    }

    public ArrayList<String> splitArray(ArrayList<String> list) {
        ArrayList<String> output = new ArrayList<>();
        String chara = "I_tried_to_make_this_the_whole_script_of_shrek_but_it_didnt_like_that";
        for (String str : list) {
            output.addAll(Arrays.asList(str.replace(", &", chara).replace(",", chara).replace("&", chara).replace("/", chara).replace(" ", "").split(chara)));
        }
        return output;
    }

    public void returnToStart(){
        System.out.println(ANSI_BOLD + ANSI_GREEN+ "---------------------------" + ANSI_RESET);
        albumStorage.clear();
        artistStorage.clear();
        start();
    }
//////////
}