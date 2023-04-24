package ritmoProject;

import java.util.ArrayList;
import java.util.List;

public class Selected {

    private static List<Track> selectedTracks = new ArrayList<>();
    
    public static void reset(){
        List<Track> empty = new ArrayList<>();
        selectedTracks = empty;
    }

    public static List<Track> getSelectedTracks() {
        return selectedTracks;
    }
    public static void setSelectedTracks(Track track) {
        selectedTracks.add(track);
    }
    public static void remove_selected(Track track) {
        selectedTracks.remove(track);
    }
    public static int numberOfSelected() {
        return selectedTracks.size();
    }
}
