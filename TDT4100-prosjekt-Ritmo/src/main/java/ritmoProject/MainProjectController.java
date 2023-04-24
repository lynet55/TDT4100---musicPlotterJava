package ritmoProject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainProjectController implements Initializable {
    
    //Balin V1

    // @FXML
    // private Label playlistName;

    @FXML
    private Label selectedTracksLbl;

    @FXML
        private ScrollPane vPlaylistScroll;
    
    @FXML
        private VBox vPlaylistBox;

    @FXML
        private ChoiceBox<String> sortList;
    
    @FXML
        private ChoiceBox<String> filterList;

    @FXML 
       public static Label trackCounterLbl;

    @FXML
        private Label OriginalLibraryCount;

    @FXML
        private Label playlistHeader;

    @FXML
    private ScrollPane track1ScrollPaneEl;

    @FXML
    private VBox track1VBoxEl;

    @FXML
    private ScrollPane track2ScrollPaneEl;

    @FXML
    private VBox track2VBoxEl;

    @FXML
    private Label playlistName1;

    @FXML
    private Label playlistName2;

    @FXML
        private ScatterChart<?, ?> scatterPlotChart;

    @FXML
        private Pane scatterPane;

    @FXML
        private Label scatterLbl;

    @FXML
        private MenuItem importCsv;
    
        @FXML
        private MenuItem likedEksportCsv;

    @FXML
        private Label distance_int;

    public void setPlaylist(CsvIO playlist) {
        
        vPlaylistBox.getChildren().clear();
        count = 0;

        for (Track track : playlist.getPlaylist()) {

            count++;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("playlistEl.fxml"));
                PlaylistController playlist_controller = new PlaylistController(this);
                loader.setController(playlist_controller);

                Node node = loader.load();
                HBox hbox = new HBox(node);

                playlist_controller.setTrack(count, track);
            
                hbox.setAlignment(Pos.CENTER);
                hbox.setPrefWidth(450);
                hbox.setPrefHeight(65);
                vPlaylistBox.getChildren().add(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        vPlaylistScroll.setContent(vPlaylistBox);
    }

    public void plot(CsvIO playlist){

        String xlabel;
        String ylabel;
        int x_distance = 0;
        int y_distance = 0;

        scatterPlotChart.getData().clear();
        XYChart.Series series = new XYChart.Series<>();
        scatterPlotChart.getData().add(series);
        
        for (int i = 0; i < playlist.getPlaylist().size(); i++) {

            EuclideanDistance distanceInstance = new EuclideanDistance();
            Track compared = playlist.getPlaylist().get(i);


            if (this.track1InDisplay == null && this.track2InDisplay == null) {
                x_distance = 0;
                y_distance = 0;

                xlabel = "None";
                ylabel = "None";
            }
            else if(this.track1InDisplay == null && this.track2InDisplay != null) {
                x_distance = 0;
                y_distance = distanceInstance.distance(this.track2InDisplay, compared);

                xlabel = "None";
                ylabel = "Distance from song 2";
            }
            else if(this.track1InDisplay != null && this.track2InDisplay == null) {
                x_distance = distanceInstance.distance(this.track1InDisplay, compared);
                y_distance = 0;

                xlabel = "Distance from song 1";
                ylabel = "None";
            }
            else {

                x_distance = distanceInstance.distance(this.track1InDisplay, compared);
                y_distance = distanceInstance.distance(this.track2InDisplay, compared);

                xlabel = "Distance from song 1";
                ylabel = "Distance from song 2";
            }

            scatterPlotChart.getXAxis().setLabel(xlabel);
            scatterPlotChart.getYAxis().setLabel(ylabel);
            
            String x_distanceString = Integer.toString(x_distance);
            XYChart.Data dataPoint = new XYChart.Data(x_distanceString, y_distance);

            series.getData().add(dataPoint);


              // Add a hover event listener to the data point
            Node point = dataPoint.getNode();
            int trackIndex = series.getData().indexOf(dataPoint);

            point.setOnMouseEntered(e -> {
                // Get the playlist value associated with the data point
                // Do something with the playlistValue on hover
                String selectedTrackName = playlist.getPlaylist().get(trackIndex).getTrackName();
                scatterLbl.setText(selectedTrackName);
                scatterLbl.setStyle("-fx-text-fill:white");

                
            });
            point.setOnMouseClicked(e -> {
                setTrackDisplay(playlist, playlist.getPlaylist().get(trackIndex));
            });
        }

    }

    public void setTrackDisplay(CsvIO playlist,Track track) {

        if(trackDisplayUpDown) {
            track1InDisplay = track;

            track1VBoxEl.getChildren().clear();
            tempVbox = track1VBoxEl;
            tempLabel = playlistName1;
            trackDisplayUpDown = false;
        }
        else{

            track2InDisplay = track;

            track2VBoxEl.getChildren().clear();
            tempVbox = track2VBoxEl;
            tempLabel = playlistName2;
            trackDisplayUpDown = true;

        }
        if(track1InDisplay != null & track2InDisplay != null) {
            EuclideanDistance eDistance = new EuclideanDistance();
            int distanceTrack12 = eDistance.distance(track1InDisplay, track2InDisplay);
            distance_int.setText(Integer.toString(distanceTrack12));
            plot(playlist);
        }
        else distance_int.setText("None()");

        int indexOfTrack = track.getIndex() - 1;

        ArrayList<String> selectedTrack = playlist.getData().get(indexOfTrack);
        tempLabel.setText(selectedTrack.get(1));


            for (int i = 1; i < selectedTrack.size(); i++) {
            
                try {
    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("songParameters.fxml"));
                    SongParameterController songParameter_controller = new SongParameterController();
                    loader.setController(songParameter_controller);
    
                    Node node = loader.load();
                    HBox hbox = new HBox(node);
    
                    
                    songParameter_controller.setParameterLabels(playlist.getHeaders().get(i), selectedTrack.get(i));
                
                    hbox.setAlignment(Pos.CENTER);
                    hbox.setPrefWidth(200);
                    hbox.setPrefHeight(35);
                    tempVbox.getChildren().add(hbox);
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
    }
    
    // Frontend variables

        private Program program;

        private VBox tempVbox;
        private Label tempLabel;

        private String currentParameter;
        private int count;

        private Track track1InDisplay;
        private Track track2InDisplay;
        boolean trackDisplayUpDown = true;

   

    public CsvIO getNew_playlist() {
        return program.getNew_playlist();
    }

    public Program getProgram() {
        return this.program;
    }

    public void setLikedTracksLbl(){
        String number = Integer.toString(Selected.getSelectedTracks().size());
        selectedTracksLbl.setText(number);
    }
    
    public void setPlaylistHeader(String header){
        playlistHeader.setText(header);
    }
    public void setOriginalLibraryCount(int count){
        String count_str = Integer.toString(count);
        OriginalLibraryCount.setText(count_str);
    }
      
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // FXML dynamic decklaration

            //Init program, gives acsess to fields, methods
            program = new Program(this);

            //creates defoult Playlist
            setPlaylist(program.getNew_playlist());
            plot(program.getNew_playlist());

            //Sets listfilter
            filterList.setValue("Filter");
            String[] filterOptions = {"Reset","Liked"};
            filterList.getItems().addAll(filterOptions);

            //Sets ParametricSort 
            sortList.setValue("Parameters");
            List<String> sortOptions = program.getNew_playlist().getHeaders();
            sortList.getItems().addAll(sortOptions);

        //Eventlistners and Model-calls

            //EVENTLISTNERS
            //Eventlistner for sort Choiceboxes genericSort, ParametricSort NB! Change RTpedia??
            sortList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    currentParameter = sortList.getSelectionModel().getSelectedItem();
        
                    // program.setSortedPlaylist(program.getNew_playlist());
                    List<Track> sorted = new ArrayList<>(program.getSortedPlaylist().getPlaylist());
        
                    program.sortBy(sorted, currentParameter);

                    //Sets and plots
                    program.getSortedPlaylist().setPlaylist(sorted);
                    setPlaylist(program.getSortedPlaylist());
                    plot(program.getSortedPlaylist());
                    
                }    
            });

            //Eventlistner filterList
            filterList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    // Method calls function for filters
                    String currentFilter = filterList.getSelectionModel().getSelectedItem();
                    program.filterBy(currentFilter);
                }});

            //Eventlistners import
                importCsv.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {program.readFileFromDisk();}
                });

            //Eventlistener for file->eksport->Csv, Instansiates Object for writing to file
                likedEksportCsv.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {program.writeLikedTracksToFile();}
                });



    }
}
    
