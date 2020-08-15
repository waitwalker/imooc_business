package com.example.lib_audio.mediaplayer.core;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;

import androidx.annotation.NonNull;

import com.example.lib_audio.app.AudioHelper;
import com.example.lib_audio.mediaplayer.model.AudioBean;

import java.util.Objects;

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

    // 音频焦点监听
    private AudioFocusManager mAudioFocusManager;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case TIME_MSG:
                    break;
            }
        }
    };

    ///
    /// @name AudioPlayer
    /// @description 构造方法 初始化一些成员变量
    /// @author liuca
    /// @date 2020/8/15
    ///
    public AudioPlayer() {
        init();
    }

    private void init() {
        mMediaPlayer = new CustomMediaPlayer();
        // 设置电量低的时候也播放
        mMediaPlayer.setWakeMode(null, PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置监听器
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnErrorListener(this);

        // 初始化WiFIlock
        mWifiLock = ((WifiManager) Objects.requireNonNull(AudioHelper.getContext().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE)))
                .createWifiLock(WifiManager.WIFI_MODE_FULL, TAG);

        mAudioFocusManager = new AudioFocusManager(AudioHelper.getContext().getApplicationContext(), this);
    }

    public void load(AudioBean audioBean) {

    }

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
