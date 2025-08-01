package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.RegistrationInfo;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentRegistrationController implements Initializable {
    @FXML
    public ToggleGroup genderSelect = new ToggleGroup();



    @FXML
    public Button btnSubmit;

    @FXML
    public RadioButton btnMaleSelect;

    @FXML
    public RadioButton btnFemaleSelect;

    @FXML
    private ComboBox<String> combobxCourse;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtEmail;

    private String selectedCourse;
    private String selectedGender;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "ICM", "ICD", "ICP"
        );
        combobxCourse.setItems(items);
        combobxCourse.getSelectionModel().selectFirst();
        combobxCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCourse = newValue;
            }
        });

        genderSelect.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selectedButton = (RadioButton) newToggle;
                this.selectedGender = selectedButton.getText();
            }
        });
    }

    @FXML
    public void btnSubmitOnAction(ActionEvent event) {
        RegistrationInfo registrationInfo = new RegistrationInfo(txtFullName.getText(), txtEmail.getText(), selectedGender, selectedCourse);
    }
}
