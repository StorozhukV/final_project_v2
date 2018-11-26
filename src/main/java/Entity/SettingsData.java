package Entity;

public class SettingsData {
    private boolean cache;
    private String path;
    private boolean spendTime;
    private static SettingsData settingsData;

    public SettingsData(boolean cache, String path, boolean spentTime) {
        this.cache = cache;
        this.path = path;
        this.spendTime = spentTime;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSpendTime() {
        return spendTime;
    }

    public void setSpendTime(boolean spendTime) {
        this.spendTime = spendTime;
    }

    public static SettingsData getSettingsData() {
        return settingsData;
    }
}
