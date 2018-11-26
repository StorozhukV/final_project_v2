package logic;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.jnlp.ApiDialog;


public class YoutubeAPIController {
    private static final String API_KEY = "AIzaSyCZI-WdDT4pTEU-nQ6XXdrab9f2fd4-cmg";

    public static Channel getChannel(String channelID){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.get("https://www.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id={CHANNEL_ID}&fields=items(contentDetails%2FrelatedPlaylists%2Fuploads%2Cid%2Csnippet(publishedAt%2Ctitle)%2Cstatistics(subscriberCount%2CvideoCount%2CviewCount))&key={API_KEY}\n")
            //jsonResponse = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                    .routeParam("CHANNEL_ID", channelID)
                    .routeParam("API_KEY", API_KEY)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        Channel channel = JSON.parseObject(jsonResponse.getBody(), Channel.class);
        if (channel.items.size() == 0) return null;
        return channel;
    }

    public static Playlist getPlaylist(String playlistID){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.get("https://www.googleapis.com/youtube/v3/playlistItems?part=contentDetails&maxResults=50&playlistId={PLAYLIST_ID}&fields=items%2FcontentDetails%2FvideoId&key={API_KEY}")
                    .routeParam("PLAYLIST_ID", playlistID)
                    .routeParam("API_KEY", API_KEY)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        Playlist playlist = JSON.parseObject(jsonResponse.getBody(), Playlist.class);
        if (playlist.items.size() == 0)return null;
        return playlist;
    }

    public static long getCommentsCount(String videoID){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.get("https://www.googleapis.com/youtube/v3/videos?part=statistics&id={VIDEO_ID}&fields=items%2Fstatistics%2FcommentCount&key={API_KEY}")
                    .routeParam("VIDEO_ID", videoID)
                    .routeParam("API_KEY", API_KEY)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        Video video = JSON.parseObject(jsonResponse.getBody(), Video.class);
        if (video.items.size() == 0) return 0;
        return video.items.get(0).statistics.commentCount;
    }

    public static ChannelData getChannelData(String channelID){
        Channel channel = getChannel(channelID);
        ChannelData channelData = convertChannelToChannelData(channel);
        if (channel != null) {
            CacheController cacheController = new CacheController(new Settings());
            cacheController.saveToCache(channelData);
        }
        return channelData;
    }

    public static ChannelData getFullChannelData(String channelID){
        Channel channel = getChannel(channelID);
        if (channel == null) return null;
        ChannelData result = getChannelData(channelID);

        result.commentCount = calculateMediaResonance(channel);
        CacheController cacheController = new CacheController(new Settings());
        cacheController.saveToCache(result);
        return result;
    }

    private static long calculateMediaResonance(Channel channel){
        String publishedPlaylist = channel.items.get(0).contentDetails.relatedPlaylists.uploads;
        Playlist playlist = getPlaylist(publishedPlaylist);

        long commentsSum = 0;
        for(Playlist.Item video : playlist.items){
            commentsSum += getCommentsCount(video.contentDetails.videoId);
        }
        return commentsSum;
    }

    public static ChannelData convertChannelToChannelData(Channel channel){
        if (channel == null) return null;

        ChannelData result = new ChannelData();
        result.id = channel.items.get(0).id;
        result.title = channel.items.get(0).snippet.title;
        result.publishedAt = channel.items.get(0).snippet.publishedAt;
        result.subscriberCount = channel.items.get(0).statistics.subscriberCount;
        result.videoCount = channel.items.get(0).statistics.videoCount;
        result.viewCount = channel.items.get(0).statistics.viewCount;
        result.commentCount = -1;
        return result;
    }
}