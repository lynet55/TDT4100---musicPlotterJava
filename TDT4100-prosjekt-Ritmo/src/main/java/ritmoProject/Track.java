package ritmoProject;

import java.util.Arrays;
import java.util.List;

public class Track {

    private List<Integer> vector;

    private Integer index;

    private String trackName;
    private String artistName;
    private String genre;

    private Integer Bpm;
    private Integer Energy;
    private Integer Danceability;
    private Integer LoudnessdB;
    private Integer Liveness;
    private Integer Valence;
    private Integer Length;
    private Integer Acousticness;
    private Integer Speechiness;
    private Integer Popularity;

    public Track(String index, String trackName, String artistName, String genre, String bpm, String energy, String danceability, String loudnessdB,
    String liveness, String valence, String length, String acousticness, String speechiness, String popularity) {
        
        this.index = Integer.parseInt(index);
        
        this.trackName = trackName;
        this.artistName = artistName;
        this.genre = genre;

        Bpm = Integer.parseInt(bpm);
        Energy = Integer.parseInt(energy);
        Danceability = Integer.parseInt(danceability);
        LoudnessdB = Integer.parseInt(loudnessdB);
        Liveness = Integer.parseInt(liveness);
        Valence = Integer.parseInt(valence);
        Length = Integer.parseInt(length);
        Acousticness = Integer.parseInt(acousticness);
        Speechiness = Integer.parseInt(speechiness);
        Popularity = Integer.parseInt(popularity);

        this.vector = Arrays.asList(Bpm, Energy, Danceability, LoudnessdB, Liveness, Valence, Length, Acousticness, Speechiness, Popularity);
    }

    public Integer getIndex() {
        return index;
    }


    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getBpm() {
        return Bpm;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getEnergy() {
        return Energy;
    }

    public void setEnergyEnergy(Integer energy) {
        Energy = energy;
    }

    public Integer getDanceability() {
        return Danceability;
    }

    public void setDanceability(Integer danceability) {
        Danceability = danceability;
    }

    public Integer getLoudnessdB() {
        return LoudnessdB;
    }

    public void setLoudnessdB(Integer loudnessdB) {
        LoudnessdB = loudnessdB;
    }

    public Integer getLiveness() {
        return Liveness;
    }

    public void setLiveness(Integer liveness) {
        Liveness = liveness;
    }

    public Integer getValence() {
        return Valence;
    }

    public void setValence(Integer valence) {
        Valence = valence;
    }

    public double getLength() {
        return Length;
    }

    public void setLength(Integer length) {
        Length = length;
    }

    public Integer getAcousticness() {
        return Acousticness;
    }

    public void setAcousticness(Integer acousticness) {
        Acousticness = acousticness;
    }

    public Integer getSpeechiness() {
        return Speechiness;
    }

    public void setSpeechiness(Integer speechiness) {
        Speechiness = speechiness;
    }

    public Integer getPopularity() {
        return Popularity;
    }

    public void setPopularity(Integer popularity) {
        Popularity = popularity;
    }

    public List<Integer> getVector() {
        return vector;
    }
    
}
