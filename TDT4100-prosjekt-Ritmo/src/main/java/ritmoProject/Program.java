package ritmoProject;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


public class Program {

// TODO
// Kan ikke like også sortere
// Kan ikke trykke reset etter det
// Fiks Display etter importert fil, Track Displays, trot kankanskje det ordnet seg skjønner ikke hvorfot tho
    

// Declaration & Initalization

    private Stage primaryStage = RitmoApp.getPrimaryStage();
    private MainProjectController mainProjectController;

    private CsvIO new_playlist;   
    private CsvIO sortedPlaylist;

    private LikedToText writeFile = new LikedToText(null);
    private String nameOfLoadedFileCleaned = "Load Data";

    private boolean liked;

    public Program(MainProjectController mainProjectController) {

        this.mainProjectController = mainProjectController;

       try {
        File dummy = Paths.get(getClass().getResource("dummy.csv").toURI()).toFile();
        new_playlist = new CsvIO(dummy);
        
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean getLiked() {return liked;}
 
    public CsvIO getSortedPlaylist() {
        return sortedPlaylist;
    }

    public String getnameOfLoadedFileCleaned(){return nameOfLoadedFileCleaned;}

    public void setSortedPlaylist(CsvIO sortedPlaylist) {
        this.sortedPlaylist = sortedPlaylist;
    }

    public CsvIO getNew_playlist() {
        return new_playlist;
    }

    public void setNew_playlist(CsvIO new_playlist) {
        this.new_playlist = new_playlist;
    }



//Method for sorting the playlist
    public void sortBy(List<Track> list, String parameter){

        if (parameter.equals("Track.Name") || parameter.equals("Artist.Name") || parameter.equals("Genre")) {
            if (parameter.equals("Track.Name")) {
                list.sort(Comparator.comparing(Track::getTrackName));
            }
            if (parameter.equals("Artist.Name")) {
                list.sort(Comparator.comparing(Track::getArtistName));
            }
            if (parameter.equals("Genre")) {
                list.sort(Comparator.comparing(Track::getGenre));
            }

        }
        else {
    
            if (parameter.equals("Beats.Per.Minute")) {
                list.sort(Comparator.comparing(Track::getBpm));
            }
            else if (parameter.equals("Energy")) {
                list.sort(Comparator.comparing(Track::getEnergy));
            }
            else if (parameter.equals("Danceability")) {
                list.sort(Comparator.comparing(Track::getDanceability));
            }
            else if (parameter.equals("Loudness..dB..")) {
                list.sort(Comparator.comparing(Track::getLoudnessdB));
            }
            else if (parameter.equals("Liveness")) {
                list.sort(Comparator.comparing(Track::getLiveness));
            }
            else if (parameter.equals("Valence.")) {
                list.sort(Comparator.comparing(Track::getValence));
            }
            else if (parameter.equals("Lenght.")) {
                list.sort(Comparator.comparing(Track::getLength));
            }
            else if (parameter.equals("Acousticness..")) {
                list.sort(Comparator.comparing(Track::getAcousticness));
            }
            else if (parameter.equals("Speechiness.")) {
                list.sort(Comparator.comparing(Track::getSpeechiness));
            }
            else if (parameter.equals("Popularity")) {
                list.sort(Comparator.comparing(Track::getPopularity));
            }

            Collections.reverse(list);
        }

        
    }

//Method for filtering the playlist
    public void filterBy(String currentFilter) {

        List<Track> toBeFiltered = new ArrayList<>(new_playlist.getPlaylist());
        sortedPlaylist = new CsvIO();
        // System.out.println(new_playlist.getPlaylist() + "Før IF");

        if (currentFilter.equals("Liked")) {
            
            liked = true;

            List<Track> filtered = toBeFiltered.stream()
            .filter(track -> Selected.getSelectedTracks().contains(track))
            .toList();
            
            sortedPlaylist.setPlaylist(filtered);


            mainProjectController.setPlaylist(sortedPlaylist);
            mainProjectController.plot(sortedPlaylist);

        }
        if (currentFilter.equals("Reset")) {
            liked = false;
            Selected.reset();
            sortedPlaylist.setPlaylist(new_playlist.getPlaylist());
            mainProjectController.setPlaylist(new_playlist);
            mainProjectController.plot(new_playlist);
        }
    }

//Eventlistner for reading files from disk
 
   public void readFileFromDisk() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a CSV File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            new_playlist = new CsvIO(file);
            this.sortedPlaylist = new_playlist;

            nameOfLoadedFileCleaned = file.getName().substring(0, file.getName().length() - 4);

            mainProjectController.setPlaylistHeader(nameOfLoadedFileCleaned);
            mainProjectController.setOriginalLibraryCount(new_playlist.getPlaylist().size());
            mainProjectController.setPlaylist(new_playlist);
            mainProjectController.plot(new_playlist);
            mainProjectController.setTrackDisplay(new_playlist, new_playlist.getPlaylist().get(0));
        }
    }
    
//Eventlistener for file->eksport->Csv, Instansiates Object for writing to file
    public void writeLikedTracksToFile() {
        FileChooser fileChooser = new FileChooser();

                    fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Csv", "*.csv"),
                    new ExtensionFilter("All", "*.*") 
                    );

                    fileChooser.setInitialFileName("your_curatedList.csv");
                    
                    File selectedFile = fileChooser.showSaveDialog(primaryStage);

                    if (selectedFile != null) {
                        writeFile.setLikedTracks(Selected.getSelectedTracks());
                        writeFile.setPath(selectedFile);
                        writeFile.createNewFile(fileChooser.getInitialFileName());
                        writeFile.writeToFile();
                    }
    }
}
