package com.redrock.my.smusic.SomeTool;

import java.util.Calendar;

/**
 * Created by ASUS on 2016/5/14.
 */
public class Time {
    public static String  getTime() {
        String month1 = null;
        String minute = null;
        String second = null;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int t = c.get(Calendar.SECOND);
        if(month<10) {
            month1 = "0" + month;
        }
        if(m<10){
            minute  = "0" + m;
        }
        else
            minute = String.valueOf(m);
        if(t<10){
            second = "0" + t;
        }
        else
            second = String.valueOf(t);
        String time = year + ""+ month1 + "" + day + "" + h + "" + minute + "" + second;
        return time;
    }
}
