package ritmoProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RitmoApp extends Application {

    static Stage primaryStage;
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        RitmoApp.primaryStage = primaryStage;

        primaryStage.setTitle("Ritmo App");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("r_main.fxml"))));
        primaryStage.show();
    }

}
