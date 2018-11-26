package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class AnalyticsController{

    public Button btnHome;
    public Button btnGlobalInfo;
    public Button btnCompareInfo;
    public Button btnSortingChannel;

    public void moveToHome(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/home.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToGlobalInfo(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/globalInfo.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToCompareInfo(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/compareInfo.fxml"))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void media(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/media.fxml"))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void compareMedia(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/mediaCompare.fxml"))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public void sortingChannel(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/sortInfo.fxml"))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
