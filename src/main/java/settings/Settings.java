package settings;

import com.alibaba.fastjson.JSON;

public class Settings {
    private boolean saveCache;
    private String pathToCache;
    private boolean time;

    // Serialization from object to JSON
    public static String serializeObject(Settings set) {
        String json = JSON.toJSONString(set);
        return json;
    }

    // Deserialization from JSON to object
    public static Settings parseFromJson(String json) {
        return JSON.parseObject(json, Settings.class);
    }

    public Settings() {
    }

    public Settings(boolean saveCash, String pathToCache, boolean time) {
        this.saveCache = saveCash;
        this.pathToCache = pathToCache;
        this.time = time;
    }

    public boolean isSaveCache() {
        return saveCache;
    }

    public void setSaveCache(boolean saveCache) {
        this.saveCache = saveCache;
    }

    public String getPathToCache() {
        return pathToCache;
    }

    public void setPathToCache(String pathToCache) {
        this.pathToCache = pathToCache;
    }

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "saveCache = " + saveCache +
                ", pathToCache = '" + pathToCache + '\'' +
                ", time = " + time +
                '}';
    }
}
