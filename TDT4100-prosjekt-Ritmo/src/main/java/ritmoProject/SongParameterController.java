package ritmoProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SongParameterController implements Initializable {

    @FXML
    private Label parameterEl;

    @FXML
    private Label parameterValueEl;

    public void setParameterLabels(String parameterName, String parameterValue) {
        parameterEl.setText(parameterName);
        parameterValueEl.setText(parameterValue);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
    
    
}
