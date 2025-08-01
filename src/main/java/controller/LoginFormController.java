package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginFormController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    Stage stage = new Stage();

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        if (txtUserName.getText().equals("admin") && txtPassword.getText().equals("1234")) {
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/student_registration_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
        }
        else {
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/username_password_alert_box.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
