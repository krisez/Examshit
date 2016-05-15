package com.redrock.my.smusic.SomeTool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.redrock.my.smusic.MainActivity;
import com.redrock.my.smusic.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ASUS on 2016/3/31.
 */
public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Timer timer = new Timer();
        TimerTask timertask = new TimerTask(){
            @Override
            public void run() {
                //得到数据，判断是否存在
                Intent i = new Intent(StartActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }};
        timer.schedule(timertask, 2000);


    }
}
