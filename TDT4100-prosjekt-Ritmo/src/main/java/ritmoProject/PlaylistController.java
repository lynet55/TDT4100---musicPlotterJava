package ritmoProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PlaylistController implements Initializable {

    private Track modelTrack;

    @FXML
    private AnchorPane trackContainer;

    @FXML
    private Pane trackColorEl;

    @FXML
    private Label trackNameEl;

    @FXML
    private Label artistNameEl;

    @FXML
    private Label numberEl;

    @FXML
    private Pane loadButton;

    @FXML
    private Label loadLabel;

    @FXML
    private ImageView addLikedBtn;

    private MainProjectController mainProjectController;

    public PlaylistController(MainProjectController mainProjectController){
        this.mainProjectController = mainProjectController;
    }
    
    private boolean clicked = false;
    private boolean addButtonClicked = true;
    String imageStringPathforLiked = getClass().getResource("icon/filledHart.png").toString();
    Image filledHartImage = new Image(imageStringPathforLiked);

    String imageStringPathforUnliked = getClass().getResource("icon/unfilledHart.png").toString();
    Image unfilledHartImage = new Image(imageStringPathforUnliked);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Hover effect
        trackContainer.setOnMouseEntered(
            event -> {trackContainer.setOpacity(.6);}
        );
        trackContainer.setOnMouseExited(
            event -> {trackContainer.setOpacity(1);}
        );
        
        //Sett reset trackContainer fra ExamplProject classen
        trackContainer.setOnMouseClicked(e -> {
            if(clicked == false) {
                trackContainer.setOpacity(.6);
                mainProjectController.setTrackDisplay(mainProjectController.getNew_playlist(), modelTrack); //Indexen kan er dratt en framover pga det er tatt fra playlist nummeret
                System.out.println(modelTrack.getArtistName());
                clicked = true;
            }
            else {
                clicked = false;
            }
        });

        if (!mainProjectController.getProgram().getLiked()) {
            
            //add/removebtn
            addLikedBtn.setOnMouseClicked(
                event -> {

                    if (addButtonClicked) { //Hvis den er pluss gjør den minus
                    //Legger til i selected klassen
                    //    File unfilledHartFile = new File(imageStringPathforUnliked);

                    addLikedBtn.setImage(filledHartImage);
                    addButtonClicked = false;
                    
                    Selected.setSelectedTracks(modelTrack);
                    mainProjectController.setLikedTracksLbl();

                    }
                    else {
                        addLikedBtn.setImage(unfilledHartImage);
                        addButtonClicked = true;

                        Selected.remove_selected(modelTrack);
                    }
                }
            );
        }
    }
    public void setTrack(Integer nr, Track model) {

        this.modelTrack = model;
        trackNameEl.setText(model.getTrackName());
        artistNameEl.setText(model.getArtistName());
        numberEl.setText(Integer.toString(nr));

        if (mainProjectController.getProgram().getLiked()) {
            addLikedBtn.setImage(filledHartImage);
            addButtonClicked = false;
        }
    }
    public void resetColorOfPlaylistContainer(){trackContainer.setStyle("-fx-background-color: #000000;");}
}
