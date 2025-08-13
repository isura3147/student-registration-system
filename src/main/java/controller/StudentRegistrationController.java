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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private String selectedGender;

    Stage stage = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "-", "ICM", "ICD", "ICP"
        );
        cmbCourse.setItems(items);
        cmbCourse.getSelectionModel().selectFirst();

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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinformation", "root", "isura1234");
            String SQL = "INSERT INTO studentinfo VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtStudentId.getText());
            preparedStatement.setObject(2, txtFullName.getText());
            preparedStatement.setObject(3, txtEmail.getText());
            preparedStatement.setObject(4, gender(selectedGender));
            preparedStatement.setObject(5, cmbCourse.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public boolean gender(String gender) {
        return gender.equals("Male") ? true : false;
    }
}
