package com.redrock.my.smusic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ASUS on 2016/3/31.
 */
public class MyHttp {

    public static void setHttpConnection(final String address,final MyCallback myCallback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection =null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(myCallback != null){
                        myCallback.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if(myCallback != null){
                       myCallback.onError(e);
                    }
                }
                finally {
                    if(httpURLConnection != null){
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
