package logic;

import java.util.List;

class SettingsForJSON{
    public boolean useCash;
    public boolean showTime;
    public String pathToCash;
}

class Channel {
    public List<Items> items;

    class Items {
        public ContentDetails contentDetails;

        class ContentDetails {
            public RelatedPlaylists relatedPlaylists;

            class RelatedPlaylists {
                public String uploads;
            }
        }

        public Snippet snippet;

        class Snippet {
            public String publishedAt;
            public String title;
        }

        public String id;
        public Statistics statistics;

        class Statistics {
            public long videoCount;
            public long subscriberCount;
            public long viewCount;
        }
    }
}

class Playlist {
    public List<Item> items;

    class Item {
        public contentDetails contentDetails;

        class contentDetails {
            public String videoId;
        }
    }
}

class Video {
    public List<Item> items;

    class Item {
        public Statistics statistics;

        class Statistics {
            public long commentCount;
        }
    }
}
