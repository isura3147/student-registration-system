package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.RegistrationInfo;

import java.io.IOException;
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
    public Button btnViewDetails;

    @FXML
    public Button btnReset;

    @FXML
    private ComboBox<String> cmbCourse;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtEmail;

    private String selectedCourse;
    private String selectedGender;

    Stage stage = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "-", "ICM", "ICD", "ICP"
        );
        cmbCourse.setItems(items);
        cmbCourse.getSelectionModel().selectFirst();
        cmbCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
        // when submit button pressed new object needs to be stored and displayed in the summary table.
        RegistrationInfo registrationInfo = new RegistrationInfo(txtFullName.getText(), txtEmail.getText(), selectedGender, selectedCourse);
        System.out.println("Full Name: " + registrationInfo.getFullName());
        System.out.println("Email: " + registrationInfo.getEmail());
        System.out.println("Gender: " + registrationInfo.getGender());
        System.out.println("Course: " + registrationInfo.getCourse());
    }

    @FXML
    public void btnViewDetailsOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/table_summary.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnResetOnAction(ActionEvent event) {
        txtFullName.setText("");
        txtEmail.setText("");
        cmbCourse.getSelectionModel().selectFirst();
        btnMaleSelect.setSelected(false);
        btnFemaleSelect.setSelected(false);
    }
}
