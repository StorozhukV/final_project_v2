package logic;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CacheController {
    Settings settings;


    public CacheController(Settings settings) {
        this.settings = settings;
    }

    public void saveToCache(ChannelData channelData){
        String cacheFilePath = "src/main/java/cache/"+ channelData.id + ".json";
        String jsonString = JSON.toJSONString(channelData);
        try {
            FileWriter fileWriter = new FileWriter(cacheFilePath);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChannelData loadFromCache(String channelId){
        String cacheFilePath = "src/main/java/cache/"+ channelId + ".json";
        File file = new File(cacheFilePath);
        try {
            Scanner scanner = new Scanner(file);
            String json = "";
            while (scanner.hasNext()){
                json += scanner.nextLine();
            }
            return JSON.parseObject(json, ChannelData.class);
        } catch (FileNotFoundException e) {
            System.out.println("Cache file for this id was not found.");
            return null;
        }
    }
}
