package Entity;

import Entity.entity.ResponseChannel;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class YouTubeAnalytics {

    //Отобразить глобальную информацию о канале / Медиа резонанс
    public static void globalChannelInfo(String id, long startTime, boolean mediaLabel){
        new Thread(() -> {
            List<ResponseChannel> channels = new ArrayList<>() ;
            try {
                if (mediaLabel){
                    channels.add(YouTubeChannels.channelMedia(id));
                } else {
                    channels.add(YouTubeChannels.channelSearch(id));
                }
            } catch (UnirestException e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ERROR ");
                    }
                });
            }
//            //выполняет код в UI потоке
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    checkList(channels, new String[] {id});
//                }
//            });
        }).start();
    }

    //Сравнить глобальную информацию о каналах  / Медиа резонанс
    public static void compareChannel(String id1, String id2, long startTime, boolean mediaLabel){
        new Thread(() -> {
            List<ResponseChannel> channels = new ArrayList<>() ;
            try {
                if(mediaLabel){
                    channels.add(YouTubeChannels.channelMedia(id1));
                    channels.add(YouTubeChannels.channelMedia(id2));
                } else {
                    channels.add(YouTubeChannels.channelSearch(id1));
                    channels.add(YouTubeChannels.channelSearch(id2));
                }

            } catch (UnirestException e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ERROR ");
                    }
                });
                }
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        checkList(channels, new String[] {id1, id2});
//                    }
//                });
        }).start();
    }

    //Сортировать каналы по их данным / Медиа резонанс
    public static void sortChannels(String channelsId, int sortType, long startTime, boolean mediaLabel){
        String[] id = channelsId.split(" ");
        new Thread(() -> {

            List<ResponseChannel> channels = new ArrayList<>() ;
            try {
                for (int i = 0; i < id.length; i++) {
                    if (mediaLabel){
                        channels.add(YouTubeChannels.channelMedia(id[i]));
                    } else {
                        channels.add(YouTubeChannels.channelSearch(id[i]));
                    }
                }
            } catch (UnirestException e) {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        checkList(channels, id);
//                    }
//                });
            }
        }).start();
    }

    private static List<ResponseChannel> sort(List<ResponseChannel> channels, int sortType) {
        switch (sortType) {
            case 0 : return channels.stream()
                    .sorted(Comparator.comparing(s -> s.getItems()[0].getSnippet().getTitle()))
                    .collect(Collectors.toList());
            case 1 : return channels.stream()
                    .sorted(Comparator.comparing(s -> s.getItems()[0].getSnippet().getPublishedAt()))
                    .collect(Collectors.toList());
            case 2:  return channels.stream()
                    .sorted(Comparator.comparing(s -> s.getItems()[0].getStatistics().getSubscriberCount()))
                    .collect(Collectors.toList());
            case 3:  return channels.stream()
                    .sorted(Comparator.comparing(s -> s.getItems()[0].getStatistics().getVideoCount()))
                    .collect(Collectors.toList());
            case 4: return channels.stream()
                    .sorted(Comparator.comparing(s -> s.getItems()[0].getStatistics().getViewCount()))
                    .collect(Collectors.toList());
            case 5: return channels.stream()
                    .sorted(Comparator.comparing(s -> s.getItems()[0].getComment().getCountComment()))
                    .collect(Collectors.toList());
            default:return channels.stream()
                    .sorted(Comparator.comparing(s -> s.getItems()[0].getSnippet().getTitle()))
                    .collect(Collectors.toList());
        }

    }

    private static void checkList(List<ResponseChannel> channels, String[]id){
        List<String> listId = new ArrayList<>(Arrays.asList(id));
        for (int i = 0; i < channels.size(); i++){
            if (channels.get(i).getItems().length == 0){
                channels.remove(i);
                System.out.println(("Канал с id - " + listId.get(i) + " не найден!!!"));
                listId.remove(i);
                i--;
            }
        }

    }

}
