package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.CacheController;
import logic.ChannelData;
import logic.DataController;
import logic.Settings;
import service.entity.CollectionGlobalInfoComparisonImplAdditional;
import service.entity.DoubleData;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MediaCompareInfoController {
    private static final String DELIMITER = ",";
    public Button btnBack;
    public TextField txtChannelId;
    public Button btnSearch;
    public TableColumn<DoubleData, String> columnTypeOfInfo;
    public TableColumn<DoubleData, String> columnFirstData;
    public TableColumn<DoubleData, String> columnSecondData;
    public Label lblRequestTime;
    public TableView table;

    private logic.Settings settings;
    private CacheController cacheController;
    private DataController dataController;
    private CollectionGlobalInfoComparisonImplAdditional channelGlobalInfo = new CollectionGlobalInfoComparisonImplAdditional();
    public void moveToBack(ActionEvent event) {
        try {
            AppRunner.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analytics.fxml"))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void search(ActionEvent event) {
        settings = new Settings();
        cacheController = new CacheController(settings);
        dataController = new DataController(settings, cacheController);
        long startTime = System.nanoTime();
        String channels = txtChannelId.getText();
        channels = channels.replaceAll(" ", "");
        ChannelData channel1 = dataController.getFullChannelData
                (channels.substring(0, channels.indexOf(DELIMITER)));
        ChannelData channel2 = dataController.getFullChannelData
                (channels.substring(channels.indexOf(DELIMITER) + 1,channels.length()));
        long runTime = (System.nanoTime() - startTime);
        double runTimeInSeconds = TimeUnit.MILLISECONDS.convert(runTime, TimeUnit.NANOSECONDS) / 1000.0;
        lblRequestTime.setText(String.valueOf(runTimeInSeconds));
        for (int i = 0; i < table.getItems().size(); i++) {
            table.getItems().clear();
        }
        columnTypeOfInfo.setCellValueFactory(new PropertyValueFactory<>("typeOfInfo"));
        columnFirstData.setCellValueFactory(new PropertyValueFactory<>("value1"));
        columnSecondData.setCellValueFactory(new PropertyValueFactory<>("value2"));
        channelGlobalInfo.add(channel1, channel2);
        table.setItems(channelGlobalInfo.getGlobalInfoList());

    }
}
