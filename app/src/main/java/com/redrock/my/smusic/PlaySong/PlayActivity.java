package com.redrock.my.smusic.PlaySong;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.redrock.my.smusic.Download.DownloadM;
import com.redrock.my.smusic.PlayList.MusicHelper;
import com.redrock.my.smusic.R;

/**
 * Created by ASUS on 2016/5/15.
 */
public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private int clickPause = 0;

    private MusicHelper helper;
    private SQLiteDatabase db;
    private SeekBar musicProgress;
    private Player player;
    private TextView songName;
    private TextView songAuthor;
    private ImageView songAlbum;
    private ImageButton preSong;
    private ImageButton toSong;
    private ImageButton nextSong;
    private DownloadM downMusic = new DownloadM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_song_main);

        initViews();
        initEvents();
        if(getIntent().getIntExtra("refuse",0) != 1230) {
            SavaData();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                player.playUrl(getIntent().getStringExtra("playUrl"));
            }
        }).start();
    }

    private void initEvents() {
        preSong.setOnClickListener(this);
        toSong.setOnClickListener(this);
        nextSong.setOnClickListener(this);
    }

    private void initViews() {
        musicProgress = (SeekBar) findViewById(R.id.song_rate);
        player = new Player(musicProgress);
        preSong = (ImageButton) findViewById(R.id.song_last);
        toSong = (ImageButton) findViewById(R.id.song_pause);
        nextSong = (ImageButton) findViewById(R.id.song_next);
        songAlbum = (ImageView) findViewById(R.id.song_albumPic);
        songName = (TextView) findViewById(R.id.song_name);
        songAuthor = (TextView) findViewById(R.id.song_author);
        songName.setText(getIntent().getStringExtra("songName"));
        songAuthor.setText(getIntent().getStringExtra("songAuthor"));
        songAlbum.setImageBitmap((Bitmap) getIntent().getParcelableExtra("albumBig"));

        //进度条的监听
        musicProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progress = progress * player.mediaPlayer.getDuration()
                        /seekBar.getMax();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // player.mediaPlayer.seekTo(progress);
                player.mediaPlayer.seekTo(progress);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.song_last:
                //原谅我利用数据库的id来查找歌曲，但是蛋疼的不知道发生了什么，哎
                //Toast.makeText(PlayActivity.this, "WATI...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.song_pause:
                if(clickPause == 0) {
                    toSong.setImageResource(android.R.drawable.ic_media_play);
                    player.pause();
                    clickPause = 1;
                }
                else {
                    toSong.setImageResource(android.R.drawable.ic_media_pause);
                    player.play();
                    clickPause = 0;
                }
                break;
            case R.id.song_next:
                /**
                 *
                 * 下一个id所携带的url
                 */
                //原谅我利用数据库的id来查找歌曲，但是蛋疼的不知道发生了什么，哎
                Toast.makeText(PlayActivity.this, "WATI...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //给activity销毁就停歌了。。不然蛋疼
    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    void SavaData(){
        helper = new MusicHelper(this,"LIST.db",null,1);
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SONGNAME",getIntent().getStringExtra("songName"));
        values.put("SINGERNAME",getIntent().getStringExtra("songAuthor"));
        values.put("PLAYURL",getIntent().getStringExtra("playUrl"));
        db.insert("list", null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_song,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.song_xq:
                Toast.makeText(PlayActivity.this, "傲娇：没得！哼╭(╯^╰)╮..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.song_down:
                Handler handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        int pg = 0;
                        Notification notification = new Notification.Builder(PlayActivity.this)
                                .setTicker("开始下载")
                                .setSmallIcon(R.drawable.kr)
                                .setContentTitle("开始下载...")
                                .setProgress(100,pg,false)
                                .build();
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(1,notification);
                        return false;
                    }
                });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downMusic.downFile(getIntent().getStringExtra("playDown"),getIntent().getStringExtra("songName"),".mp3");

                    }
                }).start();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
