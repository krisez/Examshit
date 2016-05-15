package com.redrock.my.smusic.PlayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.redrock.my.smusic.MainActivity;
import com.redrock.my.smusic.PlaySong.PlayActivity;
import com.redrock.my.smusic.PlaySong.Player;
import com.redrock.my.smusic.R;
import com.redrock.my.smusic.SomeTool.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {

    private MusicHelper helper;
    private SQLiteDatabase db;
    private List<PlayList> mDatas = new ArrayList<>();
    private PAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        helper = new MusicHelper(this,"LIST.db",null,1);
        initdata();
        initView();

        adapter.setOnItemClickListener(new PAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String sth = mDatas.get(position).getName();
                Cursor cursor = db.query("list", null, "SONGNAME = ?",
                        new String[]{sth}, null, null, null);
                String dataname = null;
                String dataauthor = null;
                String url = null;
                if(cursor.moveToFirst()) {
                    do {
                        dataname = cursor.getString(cursor.getColumnIndex("SONGNAME"));
                        dataauthor = cursor.getString(cursor.getColumnIndex("SINGERNAME"));
                        url = cursor.getString(cursor.getColumnIndex("PLAYURL"));
                    }while ((cursor.moveToNext()));
                }cursor.close();
                Intent intent = new Intent(PlayListActivity.this, PlayActivity.class);
                intent.putExtra("songName",dataname);
                intent.putExtra("songAuthor", dataauthor);
                intent.putExtra("playUrl", url);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                String sth = mDatas.get(position).getName();
                db.delete("list", "SONGNAME = ?", new String[]{sth});
                adapter.del(position);
                Toast.makeText(PlayListActivity.this,"已删该条记录",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        helper.getWritableDatabase();
        adapter = new PAdapter(this,mDatas);
        recyclerView = (RecyclerView) findViewById(R.id.play_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initdata() {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("list", null, null, null, null, null, null);
        if (cursor.moveToLast()) {
            do{
                String name = cursor.getString(cursor.getColumnIndex("SONGNAME"));
                String author = cursor.getString(cursor.getColumnIndex("SINGERNAME"));
                String url = cursor.getString(cursor.getColumnIndex("PLAYURL"));
                mDatas.add(new PlayList(author,name,url));
            }while (cursor.moveToPrevious());
        }
        cursor.close();
    }
}
