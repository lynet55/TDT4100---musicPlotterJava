package ritmoProject;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Paths;

public class CsvIO {

    private List<String> headers;
    private String filenameString;
    private List<Track> playlist = new ArrayList<>();
    List<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    public CsvIO() {
        
    }

    public CsvIO(File file) {
        this.filenameString = file.getName();
        readData(file.toURI());
    }

    public List<ArrayList<String>> getData() {
        return data;
    }

    public String getFilenameString() {
        return filenameString;
    }

    public List<String> getHeaders() {
        return headers;
    } 
    
    public List<Track> getPlaylist() {
        return playlist;
    }
    //NB finn ut av dårlig innkapsling etc
    public void setPlaylist(List<Track> new_playlist) {
        this.playlist = new_playlist;
    }

    public void readData(URI uri) {

        try {

            // lager todimensjonal array av filen

            // Files.lines(Paths.get(getClass().getResource(filename).toURI()))  !!!!!
            Files.lines(Paths.get(uri))



            // Henter filen som en string, bruker denne getClass.getResource som henter på en måte en custom relative path
            // .skip(1) 
            // Hopper over headerne
            .map(line -> new ArrayList<>(Arrays.asList(line.replaceAll("\"", "").split(","))))
            .forEach(array -> data.add(array));

            headers = data.get(0);
            data.remove(0);

            // Lager track objekter
            data.stream()
            .forEach(songData -> playlist.add(new Track(songData.get(0),songData.get(1),songData.get(2),songData.get(3),songData.get(4),songData.get(5),songData.get(6),songData.get(7),songData.get(8),songData.get(9),songData.get(10),songData.get(11),songData.get(12),songData.get(13))));
        
        } catch (Exception e) {
            System.out.println("Feil " + e);
        }
    }

    
    public static void main(String[] args) {
        
    }
}
