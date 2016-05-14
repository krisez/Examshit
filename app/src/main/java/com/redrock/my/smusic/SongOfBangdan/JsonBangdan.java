package com.redrock.my.smusic.SongOfBangdan;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/5/14.
 */
public class JsonBangdan {

    private showapi_res_body showapi_res_body;

    public showapi_res_body getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(showapi_res_body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class showapi_res_body{
        private pagebean pagebean;

        public pagebean getPagebean() {
            return pagebean;
        }

        public void setPagebean(pagebean pagebean) {
            this.pagebean = pagebean;
        }

        public static class pagebean{
            private List<songlist> songlist = new ArrayList<>();

            public List<songlist> getSonglist() {
                return songlist;
            }

            public void setSonglist(List<songlist> songlist) {
                this.songlist = songlist;
            }

            public static class songlist{
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
    }
}
