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
import java.sql.*;
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
    public TableColumn<?, ?> colStudentId;

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

    ObservableList<RegistrationInfo> registrationInfos = FXCollections.observableArrayList();

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        setItems();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems();
    }

    public void setItems() {
        tblSummary.getItems().clear();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinformation", "root", "isura1234");
            String SQL = "SELECT * FROM studentinfo;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RegistrationInfo registrationInfo = new RegistrationInfo(
                        resultSet.getString("studentId"),
                        resultSet.getString("fullName"),
                        resultSet.getString("email"),
                        String.valueOf(resultSet.getBoolean("gender")),
                        resultSet.getString("course")
                );
                registrationInfos.add(registrationInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));

        tblSummary.setItems(registrationInfos);
    }
}