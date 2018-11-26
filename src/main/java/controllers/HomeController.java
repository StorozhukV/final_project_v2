package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Button btnYoutubeAnalytics;
    public Button btnSettings;

    @FXML
    public void moveToAnalytics(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analytics.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToSettings(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/settings.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
