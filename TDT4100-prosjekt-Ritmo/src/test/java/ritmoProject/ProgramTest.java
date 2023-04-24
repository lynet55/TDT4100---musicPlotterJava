package ritmoProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProgramTest {

    Program program = new Program(null);
    List<Track> playList;
    
    @BeforeEach
    public void initEach() {
        playList = new ArrayList<>();

        Track track1 = new Track("1", "Balin Balinov", "FilthyRich", "jazz", "230", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        Track track2 = new Track("3", "Calin Balinov", "FilthyRich", "jazz", "80", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        Track track3 = new Track("2","Aalin Balinov", "FilthyRich", "jazz", "130", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        
        playList.add(track1);
        playList.add(track2);
        playList.add(track3);
    }

    @Test
    public void sortBy() {
        program.sortBy(playList, "Track.Name");
        checkSortedByName();

        program.sortBy(playList, "Beats.Per.Minute");
        checkSortedByBpm();
    }

    private void checkSortedByName() {
        List<Track> expected = new ArrayList<>();

        Track track1 = new Track("2","Aalin Balinov", "FilthyRich", "jazz", "130", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        Track track2 = new Track("1", "Balin Balinov", "FilthyRich", "jazz", "230", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        Track track3 = new Track("3", "Calin Balinov", "FilthyRich", "jazz", "80", "0", "0", "0", "0", "0", "0", "0", "0", "0");

        expected.add(track1);
        expected.add(track2);
        expected.add(track3);

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getTrackName(), playList.get(i).getTrackName());
        }
    }

    private void checkSortedByBpm() {
        List<Track> expected = new ArrayList<>();

        Track track1 = new Track("1", "Balin Balinov", "FilthyRich", "jazz", "230", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        Track track2 = new Track("2","Aalin Balinov", "FilthyRich", "jazz", "130", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        Track track3 = new Track("3", "Calin Balinov", "FilthyRich", "jazz", "80", "0", "0", "0", "0", "0", "0", "0", "0", "0");

        expected.add(track1);
        expected.add(track2);
        expected.add(track3);

        for (int i = 0; i < expected.size(); i++) {
            System.out.println(expected.get(i).getBpm());
            // System.out.println(playList.get(i).getBpm());
             assertEquals(expected.get(i).getBpm(), playList.get(i).getBpm());
        }
    }

   





}
