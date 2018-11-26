package Entity;

import Entity.entity.ResponseChannel;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private static Cache cache;
    private List<ResponseChannel> responseChannels = new ArrayList<>();

    public List<ResponseChannel> getResponseChannels() {
        return responseChannels;
    }

    public void setResponseChannels(List<ResponseChannel> responseChannels) {
        this.responseChannels = responseChannels;
    }

    public static Cache getCache() {
        return cache;
    }

    // потрыбно прописати збереження кешу
}
