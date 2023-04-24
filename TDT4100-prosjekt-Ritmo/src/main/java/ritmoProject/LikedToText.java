package ritmoProject;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class LikedToText {

    private List<Track> likedTracks;
    private File new_file;
    private File folder;
    private String truePath;
    private String output;


    //Legg til validering
    public LikedToText(List<Track> likedTracks) {
        this.likedTracks = likedTracks;
        // this.pathFolder = pathFolder;
    }

    public void setLikedTracks(List<Track> likedTracks) {
        this.likedTracks = likedTracks;
    }

    public void setPath(File pathFolder) {
        this.truePath = pathFolder.getAbsolutePath();
    }

    public void createNewFolder() {
        // System.out.println("Enter the name of the desired a directory: ");  
        // Scanner sc = new Scanner(System.in);
        // String path = sc.next();
       
        if(folder == null){
            folder = new File(truePath + "/curated_tracks");
            boolean bool = folder.mkdir();
            if (bool) {
                System.out.println("Folder is created succsessfully");
            } else {
                System.out.println("Error Found!");
            }
        }
    }

    public void createNewFile(String name) {
        //Validering av navn må legges til!!!!

        try {
            // new_file = new File(truePath + "/curated_tracks/" + fileName);
            new_file = new File(truePath);

            if (new_file.createNewFile()) {
                System.out.println("File created" + new_file.getName());
            } else {
                System.out.println("File alredy excists.");
            }
        } catch (Exception e) {
            System.out.println("error has occured.");
            e.printStackTrace();
        }
    }

    public void writeToFile() {

        for (Track track : likedTracks) {
            output += track.getTrackName() + "," + track.getArtistName() + "\n";
        }

        try {
            FileWriter myWriter = new FileWriter(new_file);
            myWriter.write(output);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
