package com.redrock.my.smusic.SongOfBangdan;

import android.annotation.SuppressLint;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.redrock.my.smusic.ItemDivider.DividerItemDecoration;
import com.redrock.my.smusic.MyCallback;
import com.redrock.my.smusic.MyHttp;
import com.redrock.my.smusic.R;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/5/14.
 */
@SuppressLint("ValidFragment")
public class BandMain extends Fragment {
    private RecyclerView recyclerView;
    private BangdanAdapterofR adapter;
    private List<BangdanItem> bangdanItemList = new ArrayList<>();
    private ProgressBar progressBar;
    private int listId;

    public BandMain(int pagerId) {
        this.listId = pagerId;
    }

    private String URLhttp="https://route.showapi.com/213-4?" +
            "showapi_appid=19010&showapi_timestamp=20160514152532&topid="+ listId +
            "&showapi_sign=1e7df399f90547119cadb0eacdf07a03";

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.obj.toString() == "a"){
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                onStop();
            }
            return true;
        }
    });

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bangdan);

        progressBar.setVisibility(View.VISIBLE);
        adapter = new BangdanAdapterofR(view.getContext(),bangdanItemList);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_bangdan);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String address = URLhttp;
                MyHttp.setHttpConnection(address, new MyCallback() {
                    @Override
                    public void onFinish(String response) {
                        Gson gson = new Gson();
                        JsonBangdan jsonBangdan = gson.fromJson(response,JsonBangdan.class);
                        for(int i = 0;i<jsonBangdan.getSonglist().size();i++){
                            String songName = jsonBangdan.getSonglist().get(i).getSongname();
                            String singerName = jsonBangdan.getSonglist().get(i).getSingername();
                            String albumSmall = jsonBangdan.getSonglist().get(i).getAlbumpic_small();
                            String playUrl = jsonBangdan.getSonglist().get(i).getUrl();
                            String downUrl = jsonBangdan.getSonglist().get(i).getDownUrl();
                        }
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
