package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import settings.Timer;

import java.io.IOException;

public class SortInfoController {
    public Button btnBack;
    public TextField txtChannelId;
    public Button btnSearch;
    public TableColumn columnName;
    public TableColumn columnId;
    public TableColumn columnDate;
    public TableColumn columnSubscribers;
    public TableColumn columnVideos;
    public TableColumn columnViews;
    public Label lblRequestTime;

    public void moveToBack(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analytics.fxml"))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    public void search(ActionEvent event) {
        Timer.startTimer();

        lblRequestTime.setText(Timer.finishTimer());
    }
}
