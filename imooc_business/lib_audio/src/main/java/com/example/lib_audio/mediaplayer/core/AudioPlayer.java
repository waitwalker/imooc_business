package com.example.lib_audio.mediaplayer.core;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;

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

    ///
    /// @name load
    /// @description 对外提供的加载
    /// @author liuca
    /// @date 2020/8/15
    ///
    public void load(AudioBean audioBean) {
        try {
            // 正常加载逻辑
            // 清空
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(audioBean.mUrl);
            mMediaPlayer.prepareAsync();
            // 对外发送load事件
        } catch (Exception e) {
            // 对外发送error事件
        }
    }

    ///
    /// @name start
    /// @description 内部开始播放
    /// @author liuca
    /// @date 2020/8/15
    ///
    private void start(){
        if (!mAudioFocusManager.requestAudioFocus()) {
            Log.d(TAG,"获取音频焦点失败,其他音频播放器占用了");
        }
        mMediaPlayer.start();
        mWifiLock.acquire();
        // 对外发送start事件
    }

    ///
    /// @name pause
    /// @description 对外提供暂停播放
    /// @author liuca
    /// @date 2020/8/15
    ///
    public void pause() {
        if (getStatus() == CustomMediaPlayer.Status.STARTED) {
            mMediaPlayer.pause();
            // 如果占用
            if (mWifiLock.isHeld()) {
                mWifiLock.release();
            }

            // 释放音频焦点
            if (mAudioFocusManager != null) {
                mAudioFocusManager.abandonAudioFocus();
            }
        }
    }

    ///
    /// @name resume
    /// @description 对外提供恢复播放
    /// @author liuca
    /// @date 2020/8/15
    ///
    public void resume() {

    }

    ///
    /// @name getStatus
    /// @description 获取播放状态
    /// @author liuca
    /// @date 2020/8/15
    ///
    public CustomMediaPlayer.Status getStatus() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getStatus();
        }
        return CustomMediaPlayer.Status.STOPPED;
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
