package settings;

import com.alibaba.fastjson.JSON;

public class Settings {
    public static boolean saveCache;
    public static String pathToCache;
    public static boolean time;

    // Serialization from object to JSON
    public static String serializeObject(Settings set) {
        return JSON.toJSONString(set);
    }

    // Deserialization from JSON to object
    public static Settings parseFromJson(String json) {
        return JSON.parseObject(json, Settings.class);
    }

    public Settings() {
    }

    public Settings(boolean saveCache, String pathToCache, boolean time) {
        Settings.saveCache = saveCache;
        Settings.pathToCache = pathToCache;
        Settings.time = time;
    }

    public boolean isSaveCache() {
        return saveCache;
    }

    public void setSaveCache(boolean saveCache) {
        Settings.saveCache = saveCache;
    }

    public String getPathToCache() {
        return pathToCache;
    }

    public void setPathToCache(String pathToCache) {
        Settings.pathToCache = pathToCache;
    }

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        Settings.time = time;
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
