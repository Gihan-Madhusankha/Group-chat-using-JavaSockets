package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author : Gihan Madhusankha
 * 2022-08-13 3:17 PM
 **/

public class LoginFormController {
    public static String username;
    public JFXTextField txtUsername;
    public AnchorPane loginContext;

    public void loginBtnOnAction(ActionEvent actionEvent) {

        try {

            username = txtUsername.getText();
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ClientForm.fxml"))));
            stage.centerOnScreen();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
