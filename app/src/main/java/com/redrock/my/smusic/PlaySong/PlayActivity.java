package com.redrock.my.smusic.PlaySong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.redrock.my.smusic.MyCallback;
import com.redrock.my.smusic.MyHttp;
import com.redrock.my.smusic.R;
import com.redrock.my.smusic.SongOfBangdan.BangdanItem;
import com.redrock.my.smusic.SongOfBangdan.JsonBangdan;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ASUS on 2016/5/15.
 */
public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private int clickPause = 0;

    private TextView songName;
    private TextView songAuthor;
    private ImageView songAlbum;
    private ImageButton preSong;
    private ImageButton toSong;
    private ImageButton nextSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_song_main);

        initViews();
        initEvents();

    }

    private void initEvents() {
        preSong.setOnClickListener(this);
        toSong.setOnClickListener(this);
        nextSong.setOnClickListener(this);
    }

    private void initViews() {
        preSong = (ImageButton) findViewById(R.id.song_last);
        toSong = (ImageButton) findViewById(R.id.song_pause);
        nextSong = (ImageButton) findViewById(R.id.song_next);
        songAlbum = (ImageView) findViewById(R.id.song_albumPic);
        songName = (TextView) findViewById(R.id.song_name);
        songAuthor = (TextView) findViewById(R.id.song_author);
        songName.setText(getIntent().getStringExtra("songName"));
        songAuthor.setText(getIntent().getStringExtra("songAuthor"));
        songAlbum.setImageBitmap((Bitmap) getIntent().getParcelableExtra("albumBig"));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.song_last:
                break;
            case R.id.song_pause:
                if(clickPause == 0) {
                    toSong.setImageResource(android.R.drawable.ic_media_play);
                    clickPause = 1;
                }
                else {
                    toSong.setImageResource(android.R.drawable.ic_media_pause);
                    clickPause = 0;
                }
                break;
            case R.id.song_next:
                break;
        }
    }
}
