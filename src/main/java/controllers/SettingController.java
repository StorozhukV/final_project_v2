package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import settings.MyFileReader;
import settings.MyFileWriter;
import settings.Settings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static settings.Timer.finishTimer;
import static settings.Timer.startTimer;

public class SettingController implements Initializable {
    private final String PATH_SETTINGS = "src/main/java/fileWithSettings";


    @FXML
    public Button btnHome;
    public TextField txtCache;
    public TextField txtRequestTime;
    public TextField txtPathToCache;
    public Button btnSave;

    public void moveToHome(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/home.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSettingsFromFile();
    }

    public void save(ActionEvent event) {
        startTimer(); // start timer

        MyFileWriter writer = new MyFileWriter(PATH_SETTINGS);
        try {
            writer.write(Settings.serializeObject(new Settings(Boolean.parseBoolean(txtCache.getText()),
                    txtPathToCache.getText(),
                    Boolean.parseBoolean(txtRequestTime.getText()))), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // finish timer check condition and show result if need
        if (Boolean.parseBoolean(txtRequestTime.getText())) {
            System.out.println(finishTimer());
        }
    }

    // Method for set settings from file
    public void setSettingsFromFile() {
        String jsonFromFile = null;
        MyFileReader reader = new MyFileReader(PATH_SETTINGS);
        try {
            jsonFromFile = reader.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Settings settings = Settings.parseFromJson(jsonFromFile);

        txtCache.setText(Boolean.toString(settings.isSaveCache()));
        txtPathToCache.setText(settings.getPathToCache());
        txtRequestTime.setText(Boolean.toString(settings.isTime()));
    }
}
