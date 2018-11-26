package logic;

import com.alibaba.fastjson.JSON;

import java.io.*;

public class Settings {
    final static String SAVE_FILE_PATH = "src/main/java/fileWithSettings";

    private boolean useCash;
    private boolean showTime;
    private String pathToCash;

    public Settings() {
        load();
    }

    public boolean getUseCash() {
        return useCash;
    }

    public boolean getShowTime() {
        return showTime;
    }

    public String getPathToCash() {
        return pathToCash;
    }

    public void load() {
        try {
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(SAVE_FILE_PATH));

            String json = "";
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = reader.readLine();
            }
            json = sb.toString();

            reader.close();

            SettingsForJSON loaded = parseFromJson(json);
            useCash = loaded.useCash;
            pathToCash = loaded.pathToCash;
            showTime = loaded.showTime;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static SettingsForJSON parseFromJson(String json) {
        return JSON.parseObject(json, SettingsForJSON.class);
    }

    public static void save(String json) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(SAVE_FILE_PATH);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String serializeObject(Settings settings) {
        String json = JSON.toJSONString(settings);
        return json;
    }

    public void saveSettings(boolean useCash, boolean showTime, String pathToCash) {
        this.useCash = useCash;
        this.showTime = showTime;
        this.pathToCash = pathToCash;
        save(serializeObject(this));
    }


}