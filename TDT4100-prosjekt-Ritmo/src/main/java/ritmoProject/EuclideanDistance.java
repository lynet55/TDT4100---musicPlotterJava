package ritmoProject;

public class EuclideanDistance implements Distance {

    @Override
    public int distance(Track selected, Track compared) {
        
        int sum = 0;
        
       for (int i = 0; i < selected.getVector().size(); i++) {
            int diff = selected.getVector().get(i) - compared.getVector().get(i);
            sum += Math.pow(diff,2);
        }
        int distance = (int) Math.round(Math.sqrt(sum));
        return distance;
    }

    public static void main(String[] args) {
        EuclideanDistance euclideanDistance = new EuclideanDistance();

        Track track1 = new Track("1","1", "409202", "1", "1", "900", "1", "1", "1", "1", "1", "1", "1", "1");
        Track track2 = new Track("2","2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2");
        
        System.out.println(euclideanDistance.distance(track1, track2));

        track1 = new Track("1","500", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5");
        track2 = new Track("2","2", "2", "2", "300", "2", "2", "2", "2", "2", "2", "2", "2", "2");

        System.out.println(euclideanDistance.distance(track1, track2));
    }
    
}
