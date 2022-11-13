public class MyConsole {
    public static void main(String[] args) {

        // TO DO: Read the files you need into 1D arrays and create a Spotbox object with the arrays.
        FileReader reader = new FileReader("src/artists");
        String[] artists = reader.getStringData(498);
        reader.setFile("src/albums");
        String[] albums = reader.getStringData(498);
        reader.setFile("src/genres");
        String[] genres = reader.getStringData(498);

        Spotbox spotify = new Spotbox(artists,genres,albums);
        spotify.start();



        // TO DO: Call the methods you write to provide the information to the user.
        //I did it all in the other class, no need :)



    }
}