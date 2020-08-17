package com.example.imooc_business.app;

import android.app.Application;

import com.example.lib_audio.app.AudioHelper;

public class App extends Application {

    private static App mApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        AudioHelper.init(this);
    }

    public static App getInstance() {
        return mApp;
    }
}
