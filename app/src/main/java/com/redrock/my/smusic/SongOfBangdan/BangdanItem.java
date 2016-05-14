package com.redrock.my.smusic.SongOfBangdan;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

/**
 * Created by ASUS on 2016/5/14.
 */
public class BangdanItem {
    private Bitmap songAlbum;
    private String songName;
    private String songAuthor;
    private String playUrl;
    private String downUrl;

    public BangdanItem(String downUrl, String playUrl, Bitmap songAlbum, String songAuthor, String songName) {
        this.downUrl = downUrl;
        this.playUrl = playUrl;
        this.songAlbum = songAlbum;
        this.songAuthor = songAuthor;
        this.songName = songName;
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

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public Bitmap getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(Bitmap songAlbum) {
        this.songAlbum = songAlbum;
    }
}
