package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.RegistrationInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableSummaryController implements Initializable {

    @FXML
    private TableView<RegistrationInfo> tblSummary;

    @FXML
    private Button btnBackToRegistration;

    @FXML
    private Button btnReload;

    @FXML
    private TableColumn<?, ?> colCourse;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFullName;

    @FXML
    private TableColumn<?, ?> colGender;

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

    ObservableList<RegistrationInfo> registrationInfos = FXCollections.observableArrayList(
            new RegistrationInfo("Isura", "isura@gmail.com", "Male", "ICD"),
            new RegistrationInfo("Nirmala", "nirmala@yahoo.com", "Male", "ICM"),
            new RegistrationInfo("Anuki", "anuki@hotmail.com", "Female", "ICP")
    );

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        setItems();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems();
    }

    public void setItems() {
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));

        tblSummary.setItems(registrationInfos);
    }
}