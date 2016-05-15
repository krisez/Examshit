package com.redrock.my.smusic.SearchS;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.redrock.my.smusic.MyCallback;
import com.redrock.my.smusic.MyHttp;
import com.redrock.my.smusic.PlaySong.PlayActivity;
import com.redrock.my.smusic.R;
import com.redrock.my.smusic.SomeTool.DividerItemDecoration;
import com.redrock.my.smusic.SomeTool.Time;
import com.redrock.my.smusic.SongOfBangdan.BangdanAdapterofR;
import com.redrock.my.smusic.SongOfBangdan.BangdanItem;
import com.redrock.my.smusic.SongOfBangdan.JsonBangdan;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/5/15.
 */
public class SearchSong extends AppCompatActivity{

    private int isSearch = 0;

    private EditText songKey;
    private Button searchButton;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<Search> searchList = new ArrayList<>();
    private ProgressBar progressBar;
    private String time = Time.getTime();

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            isSearch = 1;
            onStop();
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiety_search);

        init();
    }

    private void init() {
        adapter = new SearchAdapter(SearchSong.this,searchList);
        progressBar = (ProgressBar) findViewById(R.id.search_progress);
        searchButton = (Button) findViewById(R.id.search_btn);
        songKey = (EditText) findViewById(R.id.search_edit);
        recyclerView = (RecyclerView) findViewById(R.id.search_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchSong.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(SearchSong.this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SearchSong.this,PlayActivity.class);
                intent.putExtra("songName", searchList.get(position).getSongName());
                intent.putExtra("songAuthor", searchList.get(position).getSongAuthor());
                intent.putExtra("albumBig", searchList.get(position).getBigAlbum());
                intent.putExtra("playUrl", searchList.get(position).getPlayUrl());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(view.getContext(),"经过自我鉴定，决定下载还是不弹出来了",Toast.LENGTH_LONG);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearch != 0) {
                    searchList.removeAll(searchList);
                    adapter.notifyDataSetChanged();
                }
                    progressBar.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String address = "https://route.showapi.com/213-1?keyword=" + songKey.getText().toString() + "&page=1&showapi_appid=19010&showapi_timestamp=" + time + "&showapi_sign=1e7df399f90547119cadb0eacdf07a03";
                            MyHttp.setHttpConnection(address, new MyCallback() {
                                @Override
                                public void onFinish(String response) {
                                    Gson gson = new Gson();
                                    JSONSearch jsonSearch = gson.fromJson(response, JSONSearch.class);
                                    for (int i = 0; i < 10; i++) {
                                        String songName = jsonSearch.getShowapi_res_body().getPagebean().getContentlist().get(i).getSongname();
                                        String singerName = jsonSearch.getShowapi_res_body().getPagebean().getContentlist().get(i).getSingername();
                                        String albumSmall = jsonSearch.getShowapi_res_body().getPagebean().getContentlist().get(i).getAlbumpic_small();
                                        String playUrl = jsonSearch.getShowapi_res_body().getPagebean().getContentlist().get(i).getM4a();
                                        String downUrl = jsonSearch.getShowapi_res_body().getPagebean().getContentlist().get(i).getDownUrl();
                                        String bigImg = jsonSearch.getShowapi_res_body().getPagebean().getContentlist().get(i).getAlbumpic_big();
                                        Bitmap bitmap1 = null;
                                        Bitmap bitmap2 = null;
                                        try {
                                            URL u = new URL(albumSmall);
                                            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                                            BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
                                            bitmap1 = BitmapFactory.decodeStream(is);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            URL u = new URL(bigImg);
                                            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                                            BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
                                            bitmap2 = BitmapFactory.decodeStream(is);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Search search = new Search(bitmap2, downUrl, playUrl, bitmap1, singerName, songName);
                                        searchList.add(search);
                                    }
                                    Message message = new Message();
                                    handler.sendMessage(message);
                                }

                                @Override
                                public void onError(Exception e) {
                                }
                            });
                        }
                    }).start();

            }
        });


    }


}
