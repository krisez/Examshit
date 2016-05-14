package com.redrock.my.smusic.SongOfBangdan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.redrock.my.smusic.MyCallback;
import com.redrock.my.smusic.MyHttp;
import com.redrock.my.smusic.PlaySong.PlayActivity;
import com.redrock.my.smusic.R;
import com.redrock.my.smusic.SomeTool.DividerItemDecoration;
import com.redrock.my.smusic.SomeTool.Time;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/5/14.
 */
@SuppressLint("ValidFragment")
public class BangHkMain extends Fragment {

    private RecyclerView recyclerView;
    private BangdanAdapterofR adapter;
    private List<BangdanItem> bangdanItemList = new ArrayList<>();
    private ProgressBar progressBar;
    private String time = Time.getTime();

    private String URLhttp="https://route.showapi.com/213-4?showapi_appid=19010&showapi_timestamp="+time+"&topid=6&showapi_sign=1e7df399f90547119cadb0eacdf07a03";

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                onStop();
            return true;
        }
    });

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bangdan);
        adapter = new BangdanAdapterofR(view.getContext(),bangdanItemList);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_bangdan);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new BangdanAdapterofR.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(),PlayActivity.class);
                intent.putExtra("songName",bangdanItemList.get(position).getSongName());
                intent.putExtra("songAuthor",bangdanItemList.get(position).getSongAuthor());
                intent.putExtra("albumBig",bangdanItemList.get(position).getBigAlbum());
                intent.putExtra("playUrl",bangdanItemList.get(position).getPlayUrl());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(view.getContext(),"经过自我鉴定，决定下载还是不弹出来了",Toast.LENGTH_LONG);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String address = URLhttp;
                MyHttp.setHttpConnection(address,new MyCallback() {
                    @Override
                    public void onFinish(String response) {
                        Log.d("NetMusic",response);
                        Gson gson = new Gson();
                        JsonBangdan jsonBangdan = gson.fromJson(response, JsonBangdan.class);
                        for(int i = 0;i<15;i++){
                            String songName = jsonBangdan.getShowapi_res_body().getPagebean().getSonglist().get(i).getSongname();
                            String singerName = jsonBangdan.getShowapi_res_body().getPagebean().getSonglist().get(i).getSingername();
                            String albumSmall = jsonBangdan.getShowapi_res_body().getPagebean().getSonglist().get(i).getAlbumpic_small();
                            String playUrl = jsonBangdan.getShowapi_res_body().getPagebean().getSonglist().get(i).getUrl();
                            String downUrl = jsonBangdan.getShowapi_res_body().getPagebean().getSonglist().get(i).getDownUrl();
                            String bigImg = jsonBangdan.getShowapi_res_body().getPagebean().getSonglist().get(i).getAlbumpic_big();
                            Bitmap bitmap1 = null;
                            Bitmap bitmap2 = null;
                            Log.d("NetMusic",songName);
                            try {
                                URL u = new URL(albumSmall);
                                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                                BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
                                bitmap1 = BitmapFactory.decodeStream(is);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                URL u = new URL(albumSmall);
                                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                                BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
                                bitmap2 = BitmapFactory.decodeStream(is);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            BangdanItem item = new BangdanItem(downUrl,playUrl,bitmap1,singerName,songName,bitmap2);
                            bangdanItemList.add(item);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bangdan_main,container,false);
    }
}
