package com.redrock.my.smusic;

/**
 * Created by ASUS on 2016/3/31.
 */
public interface MyCallback {
    void onFinish(String response);
    void onError(Exception e);
}
