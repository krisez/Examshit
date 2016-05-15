package com.redrock.my.smusic.PlayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 2016/5/15.
 */
public class MusicHelper extends SQLiteOpenHelper {

    public static final String CREATE_LIST = "create table list("
            +  "id integer primary key autoincrement,"
            +  "SONGNAME text,"
            +  "SINGERNAME text,"
            +  "PLAYURL text)";

    private Context mContext;
    public MusicHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
