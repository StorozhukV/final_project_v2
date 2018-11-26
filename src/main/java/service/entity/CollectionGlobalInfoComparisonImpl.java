package service.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.ChannelData;



public class CollectionGlobalInfoComparisonImpl implements CollectionChannelsDoubleInfo {
    public ObservableList<DoubleData> globalInfoList = FXCollections.observableArrayList();

    public ObservableList<DoubleData> getGlobalInfoList() {
        return globalInfoList;
    }

    public void setGlobalInfoList(ObservableList<DoubleData> globalInfoList) {
        this.globalInfoList = globalInfoList;
    }

    @Override
    public void add(ChannelData channel1, ChannelData channel2) {
        String date1 = channel1.getPublishedAt();
        String date2 = channel2.getPublishedAt();
        globalInfoList.add(new DoubleData("Channel title",
                channel1.getTitle(),
                channel2.getTitle()));
        globalInfoList.add(new DoubleData("Channel Id", channel1.getId(), channel2.getId()));
        globalInfoList.add(new DoubleData("Creation date",
                date1.substring(0, 4) + "." + date1.substring(5, 7) + "." + date1.substring(8, 10),
                date2.substring(0, 4) + "." + date2.substring(5, 7) + "." + date2.substring(8, 10)));
        globalInfoList.add(new DoubleData("Subscriber count",
                Long.toString(channel1.getSubscriberCount()),
                Long.toString(channel2.getSubscriberCount())));
        globalInfoList.add(new DoubleData("Video count",
                Long.toString(channel1.getVideoCount()),
                Long.toString(channel2.getVideoCount())));
        globalInfoList.add(new DoubleData("View count",
                Long.toString(channel1.getViewCount()),
                Long.toString(channel2.getViewCount())));
    }

    @Override
    public void removeAll() {
        globalInfoList.removeAll();
    }
}
