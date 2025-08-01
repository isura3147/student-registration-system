package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UsernamePasswordAlertBoxController {
    @FXML
    public Button btnOk;

    @FXML
    public void btnOkOnAction(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close(); // Close the stage
    }
}
