package com.redrock.my.smusic.SongOfBangdan;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/5/14.
 */
public class JsonBangdan {

    private List<songList> songlist = new ArrayList<>();

    public List<songList> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<songList> songlist) {
        this.songlist = songlist;
    }

    public static class songList{
        private String albumpic_big;
        private String albumpic_small;
        private String downUrl;
        private int seconds;
        private String singername;
        private String songname;
        private String url;

        public String getAlbumpic_big() {
            return albumpic_big;
        }

        public void setAlbumpic_big(String albumpic_big) {
            this.albumpic_big = albumpic_big;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSingername() {
            return singername;
        }

        public void setSingername(String singername) {
            this.singername = singername;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public String getAlbumpic_small() {
            return albumpic_small;
        }

        public void setAlbumpic_small(String albumpic_small) {
            this.albumpic_small = albumpic_small;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }
    }
}
