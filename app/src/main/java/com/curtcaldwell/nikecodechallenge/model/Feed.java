package com.curtcaldwell.nikecodechallenge.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;


@Root(name = "rss", strict = false)
public class Feed implements Serializable {


    @Element(name = "channel")


    @ElementList(inline = true, name = "item", required = false)
    private List<Rss.Item> items;

    public List<Rss.Item> getItems() {
        return items;
    }

    public void setItems(List<Rss.Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Feed: \n [Items: " + items +"]";
    }
}
