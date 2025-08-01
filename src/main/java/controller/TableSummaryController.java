package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class TableSummaryController {

    @FXML
    private Button btnBackToRegistration;

    @FXML
    private Button btnReload;

    @FXML
    private TreeTableColumn<?, ?> colCourse;

    @FXML
    private TreeTableColumn<?, ?> colEmail;

    @FXML
    private TreeTableColumn<?, ?> colFullName;

    @FXML
    private TreeTableColumn<?, ?> colGender;

    Stage stage;

    @FXML
    void btnBackToRegistrationOnAction(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/student_registration_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        //
    }

}
