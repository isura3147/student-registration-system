package controller;

import com.jfoenix.controls.JFXCheckBox;
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
import model.dto.RegistrationInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentRegistrationController implements Initializable {
    @FXML
    public ToggleGroup genderSelect = new ToggleGroup();

    @FXML
    public Button btnSubmit;

    @FXML
    public JFXCheckBox btnMaleSelect;

    @FXML
    public JFXCheckBox btnFemaleSelect;

    @FXML
    public Button btnViewDetails;

    @FXML
    public Button btnReset;

    @FXML
    public TextField txtStudentId;

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

        btnMaleSelect.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnFemaleSelect.setSelected(false);
                selectedGender = btnMaleSelect.getText();
            } else if (!btnFemaleSelect.isSelected()) {
                selectedGender = null;
            }
        });

        btnFemaleSelect.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnMaleSelect.setSelected(false);
                selectedGender = btnFemaleSelect.getText();
            } else if (!btnMaleSelect.isSelected()) {
                selectedGender = null;
            }
        });
    }

    @FXML
    public void btnSubmitOnAction(ActionEvent event) {
        if (btnMaleSelect.isSelected()) {
            selectedGender = "Male";
        } else if (btnFemaleSelect.isSelected()) {
            selectedGender = "Female";
        } else {
            selectedGender = null; // or some default value
        }
        RegistrationInfo registrationInfo = new RegistrationInfo(txtStudentId.getText(), txtFullName.getText(), txtEmail.getText(), selectedGender, selectedCourse);
        System.out.println("Student ID: " + registrationInfo.getId());
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
        txtStudentId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        cmbCourse.getSelectionModel().selectFirst();
        btnMaleSelect.setSelected(false);
        btnFemaleSelect.setSelected(false);
        selectedGender = null;
    }
}
