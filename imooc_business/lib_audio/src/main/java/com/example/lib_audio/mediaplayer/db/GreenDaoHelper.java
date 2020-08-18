package com.example.lib_audio.mediaplayer.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.lib_audio.app.AudioHelper;
import com.example.lib_audio.mediaplayer.model.AudioBean;

public class GreenDaoHelper {

    private static final String DB_NAME = "music_db";

    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase mDb;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public static void initDataBase() {
        mHelper = new DaoMaster.DevOpenHelper(AudioHelper.getContext(),DB_NAME,null);
        mDb = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();
    }

    public static void addFavourite(AudioBean audioBean) {

    }
}
