package com.redrock.my.smusic.SearchS;

import android.graphics.Bitmap;

/**
 * Created by ASUS on 2016/5/15.
 */
public class Search {
    private Bitmap smallAlbum;
    private Bitmap bigAlbum;
    private String songAuthor;
    private String playUrl;
    private String downUrl;
    private String  songName;

    public Search(Bitmap bigAlbum, String downUrl, String playUrl, Bitmap smallAlbum, String songAuthor,String songName) {
        this.bigAlbum = bigAlbum;
        this.songName = songName;
        this.downUrl = downUrl;
        this.playUrl = playUrl;
        this.smallAlbum = smallAlbum;
        this.songAuthor = songAuthor;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Bitmap getBigAlbum() {
        return bigAlbum;
    }

    public void setBigAlbum(Bitmap bigAlbum) {
        this.bigAlbum = bigAlbum;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Bitmap getSmallAlbum() {
        return smallAlbum;
    }

    public void setSmallAlbum(Bitmap smallAlbum) {
        this.smallAlbum = smallAlbum;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }
}
