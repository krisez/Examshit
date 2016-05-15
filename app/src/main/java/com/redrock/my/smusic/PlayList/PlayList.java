package com.redrock.my.smusic.PlayList;

/**
 * Created by ASUS on 2016/5/15.
 */
public class PlayList {
    private String name;
    private String author;
    private String url;

    public PlayList(String author, String name, String url) {
        this.author = author;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
