package com.example.lib_audio.app;

import android.annotation.SuppressLint;
import android.content.Context;

public final class AudioHelper {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
