package com.example.lib_audio.mediaplayer.core;

import android.media.MediaPlayer;
import android.net.wifi.WifiManager;

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
    // 日志用的
    private static final String TAG = "AudioPlayer";
    private static final int TIME_MSG = 0x01;
    private static final int TIME_INVAL = 100;

    // 真正音频播放器
    private CustomMediaPlayer mMediaPlayer;

    // 用于后台保活
    private WifiManager.WifiLock mWifiLock;


    private AudioFocusManager mAudioFocusManager;

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void audioFocusGrant() {

    }

    @Override
    public void audioFocusLoss() {

    }

    @Override
    public void audioFocusLossTransient() {

    }

    @Override
    public void audioFocusLossDuck() {

    }
}
