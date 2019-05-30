package com.curtcaldwell.nikecodechallenge.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Version;

import java.io.Serializable;
import java.nio.channels.Channel;
import java.util.List;


@Root(strict = false)
public class Rss {

    @Version(revision = 2.0)
    private double version;

    @Element

    private Channel channel;

    public Channel getChannel(){
        return channel;
    }

@Root(strict = false)

    public static class Channel {


    @ElementList(inline = true, entry = "item")
    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }
}

    @Root(strict = false)
    public static class Item {

        @Element(name = "title")
        private String title;

//        @Element(name = "category")
        private String category;

        @Element(name = "link")
        private String link;

        @Element(name = "guid")
        private String guid;

        @Element(name = "description")
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
