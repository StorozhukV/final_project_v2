package Entity;

import Entity.entity.ResponseChannel;
import Entity.playList.ResponsePlaylist;
import Entity.video.ResponseVideo;
import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


import java.util.ArrayList;
import java.util.List;

public class YouTubeChannels {
    private static final String API_KEY = "AIzaSyCZI-WdDT4pTEU-nQ6XXdrab9f2fd4-cmg";
    private static final String LINK_PLAYLIST = "https://www.googleapis.com/youtube/v3/playlistItems";
    private static final String LINK_CHANNELS = "https://www.googleapis.com/youtube/v3/channels";
    private static final String LINK_VIDEOS = "https://www.googleapis.com/youtube/v3/videos";


    public static final ObjectMapper mapper = new ObjectMapper() {
        public <T> T readValue(String value, Class<T> valueType) {
            return JSON.parseObject(value, valueType);
        }

        public String writeValue(Object value) {
            return JSON.toJSONString(value);
        }
    };

    static {
        Unirest.setObjectMapper(mapper);
    }

    public static ResponseChannel channelMedia(String id) throws UnirestException {
        ResponseChannel responseChannel = channelSearch(id);
        if(responseChannel.getItems().length == 0){
            return responseChannel;
        }
        if (responseChannel.getItems()[0].getComment().isCacheLabel()) {
            return responseChannel;
        }

        List<String> channelsId = getChannelsId(responseChannel
                .getItems()[0]
                .getContentDetails()
                .getRelatedPlayLists()
                .getUploads());
        long commentsCount = getComments(channelsId);

        responseChannel.getItems()[0].getComment().setCountComment(commentsCount);
        responseChannel.getItems()[0].getComment().setCacheLabel(true);

        return responseChannel;
    }

    public static List<String> getChannelsId(String uploads) throws UnirestException {
        List<String> result = new ArrayList<>();
        HttpResponse<ResponsePlaylist> response = Unirest.get(LINK_PLAYLIST)
                .queryString("key", API_KEY)
                .queryString("playlistId", uploads)
                .queryString("part", "snippet")
                .queryString("maxResults", 50)
                .asObject(ResponsePlaylist.class);

        copyId(result, response.getBody());

        String page =response.getBody().getNextPageToken();
        while (page != null){
            HttpResponse<ResponsePlaylist> resp = Unirest.get(LINK_PLAYLIST)
                    .queryString("key", API_KEY)
                    .queryString("playlistId", uploads)
                    .queryString("part", "snippet")
                    .queryString("maxResults", 50)
                    .queryString("pageToken", page)
                    .asObject(ResponsePlaylist.class);
            page = resp.getBody().getNextPageToken();
            copyId(result, resp.getBody());
        }
        return result;
    }

    public static ResponseChannel channelSearch(String id) throws UnirestException {
        if (Cache.getCache() != null){
            for(ResponseChannel responseChannel : Cache.getCache().getResponseChannels()){
                if (id.equals(responseChannel.getItems()[0].getId())){
                    return responseChannel;
                }
            }}
        HttpResponse<ResponseChannel> response = Unirest.get(LINK_CHANNELS)
                .queryString("key", API_KEY)
                .queryString("id", id)
                .queryString("part", "snippet, statistics, contentDetails")
                .asObject(ResponseChannel.class);

        if (SettingsData.getSettingsData().isCache() && response.getBody().getItems().length != 0) {
            Cache.getCache().getResponseChannels().add(response.getBody());
        }
        return response.getBody();
    }

    public static long getComments(List<String> channelsId) throws UnirestException {
        long result = 0;
        for (String id : channelsId) {
            HttpResponse<ResponseVideo> response = Unirest.get(LINK_VIDEOS)
                    .queryString("key", API_KEY)
                    .queryString("id", id)
                    .queryString("part", "statistics")
                    .asObject(ResponseVideo.class);
            result += response.getBody().getItems()[0].getStatistics().getCommentCount();
        }
        return result;
    }


    public static void copyId(List<String> result, ResponsePlaylist body) {
        for (int i = 0; i < body.getItems().length; i++){
            result.add(body.getItems()[i]
                    .getSnippet()
                    .getResourceId()
                    .getVideoId());
        }
    }
}
