package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import settings.Settings;
import settings.Timer;

import java.io.IOException;

import static settings.Timer.finishTimer;
import static settings.Timer.startTimer;

public class GlobalInfoController {
    public TextField txtChannelId;
    public Button btnSearch;
    public TableColumn columnTypeOfInfo;
    public TableColumn columnData;
    public Label lblRequestTime;
    public Button btnBack;

    public void search(ActionEvent event) {
        Timer.startTimer();

        lblRequestTime.setText(Timer.finishTimer());

    }

    public void moveToBack(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analytics.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
