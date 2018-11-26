package logic;

public class ChannelData{
    public String id;           //id канала
    public String title;        //имя канала
    public String publishedAt;  //Дата создания канала
    public long subscriberCount;//Количество подпищиков
    public long videoCount;     //Количество видео на канале
    public long viewCount;      //Количество просмотров всех видео
    public long commentCount;   //количество комментариев под всеми видео на канале

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public long getSubscriberCount() {
        return subscriberCount;
    }

    public long getVideoCount() {
        return videoCount;
    }

    public long getViewCount() {
        return viewCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    @Override
    public String toString() {
                    return "id = " + id
                    + "\ntitle = " + title
                    + "\npublishedAt = " + publishedAt
                    + "\nsubscriberCount = " + subscriberCount
                    + "\nvideoCount = " + videoCount
                    + "\nviewCount = " + viewCount
                    + "\ncommentCount = " + commentCount
                    + "\n\n";
    }
}
