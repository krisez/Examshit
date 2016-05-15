package com.redrock.my.smusic.SearchS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/5/15.
 */
public class JSONSearch {
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
            private List<contentlist> contentlist = new ArrayList<>();

            public List<contentlist> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<contentlist> contentlist) {
                this.contentlist = contentlist;
            }

            public static class contentlist{
                private String albumpic_big;
                private String albumpic_small;
                private String downUrl;
                private String songname;
                private String singername;
                private String m4a;

                public String getSongname() {
                    return songname;
                }

                public void setSongname(String songname) {
                    this.songname = songname;
                }

                public String getM4a() {
                    return m4a;
                }

                public void setM4a(String m4a) {
                    this.m4a = m4a;
                }

                public String getAlbumpic_big() {
                    return albumpic_big;
                }

                public void setAlbumpic_big(String albumpic_big) {
                    this.albumpic_big = albumpic_big;
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

            }
        }
    }
}
