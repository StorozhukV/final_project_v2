package logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChannelsListController {
    public static void sortChannels(List<ChannelData> channels, String parameter){
        switch (parameter){
            case "Имя канала" : {
                Collections.sort(channels, Comparator.comparing(ChannelData::getTitle));
                break;
            }
            case "Дата создания канала" : {
                Collections.sort(channels, Comparator.comparing(ChannelData::getPublishedAt).reversed());
                break;
            }
            case "Кол-во подписчиков" : {
                Collections.sort(channels, Comparator.comparing(ChannelData::getSubscriberCount).reversed());
                break;
            }
            case "Кол-во видео на канале" : {
                Collections.sort(channels, Comparator.comparing(ChannelData::getVideoCount).reversed());
                break;
            }
            case "Кол-во просмотров всех видео" : {
                Collections.sort(channels, Comparator.comparing(ChannelData::getViewCount).reversed());
                break;
            }
            case "Количество комментариев" : {
                Collections.sort(channels, Comparator.comparing(ChannelData::getCommentCount).reversed());
                break;
            }
        }

    }

    public static String channelsListToString(List<ChannelData> channels){
        String result = "";
        for(ChannelData channel : channels){
            result += channel.toString();
        }
        return result;
    }
}
