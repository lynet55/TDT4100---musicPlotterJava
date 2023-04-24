package ritmoProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class EuclideanDistanceTest {

    @Test
    void testDistance() {

        EuclideanDistance euclideanDistance = new EuclideanDistance();

        Track track1 = new Track("1","1", "409202", "1", "1", "900", "1", "1", "1", "1", "1", "1", "1", "1");
        Track track2 = new Track("2","2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2");
        
        int actualDistance = euclideanDistance.distance(track1, track2);
        int expectedDistance = 898;
        assertEquals(expectedDistance, actualDistance);

        track1 = new Track("1","500", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5");
        track2 = new Track("2","2", "2", "2", "300", "2", "2", "2", "2", "2", "2", "2", "2", "2");

        actualDistance = euclideanDistance.distance(track1, track2);
        expectedDistance = 295;
        assertEquals(expectedDistance, actualDistance);
    }

}
