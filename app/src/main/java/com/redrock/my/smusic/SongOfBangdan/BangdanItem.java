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

    public BangdanItem(Bitmap songAlbum, String songAuthor, String songName) {
        this.songAlbum = songAlbum;
        this.songAuthor = songAuthor;
        this.songName = songName;
    }

    public Bitmap getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(Bitmap songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
