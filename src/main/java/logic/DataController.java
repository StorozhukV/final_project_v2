package logic;

public class DataController {
    private Settings settings;
    private CacheController cacheController;

    public DataController(Settings settings, CacheController cacheController) {
        this.settings = settings;
        this.cacheController = cacheController;
    }

    public ChannelData getChannelData(String channelId){
        ChannelData channelData = null;
        if (settings.getUseCash()){
            channelData = cacheController.loadFromCache(channelId);
            if (channelData == null) channelData = logic.YoutubeAPIController.getChannelData(channelId);
        } else {
            channelData = logic.YoutubeAPIController.getChannelData(channelId);
        }
        return channelData;
    }

    public ChannelData getFullChannelData(String channelId){
        ChannelData channelData = null;
        if (settings.getUseCash()){
            channelData = cacheController.loadFromCache(channelId);
            if (channelData == null || channelData.commentCount == -1) {
                channelData = logic.YoutubeAPIController.getFullChannelData(channelId);
            }
        } else {
            channelData = logic.YoutubeAPIController.getFullChannelData(channelId);
        }
        return channelData;
    }
}
