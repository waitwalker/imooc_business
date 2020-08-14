package com.example.lib_audio.mediaplayer.core;

import android.media.MediaPlayer;

///
/// @name AudioPlayer
/// @description 1.音频播放 2.对外提供何种类型事件
/// @author liuca
/// @date 2020/8/14
///
public class AudioPlayer implements
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        AudioFocusManager.AudioFocusListener {
    private static final String TAG = "AudioPlayer";
    private static final int TIME_MSG = 0x01;
    private static final int TIME_INVAL = 100;
}
