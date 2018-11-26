package service.entity;

import logic.ChannelData;

public interface CollectionChannelsDoubleInfo {
    void add(ChannelData channel1, ChannelData channel2);
    void removeAll();
}
