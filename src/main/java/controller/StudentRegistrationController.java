package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentRegistrationController implements Initializable {

    @FXML
    private ComboBox<String> combobxCourse;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "ICM", "ICD", "ICP"
        );
        combobxCourse.setItems(items);
        // Optionally, set a default selected item
        combobxCourse.getSelectionModel().selectFirst();
    }
}
