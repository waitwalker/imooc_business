package com.example.lib_audio.mediaplayer.core;

import android.media.MediaPlayer;

import java.io.IOException;

///
/// @name CustomMediaPlayer
/// @description 对外提供播放状态等服务
/// @author liuca
/// @date 2020/8/14
///
public class CustomMediaPlayer extends MediaPlayer implements MediaPlayer.OnCompletionListener {

    private OnCompletionListener mCompletionListener;

    // 状态枚举
    public enum Status {
        IDLE, INITIALIZED, STARTED, PAUSED, STOPPED, COMPLETED
    }

    private Status mStatus = Status.IDLE;

    public CustomMediaPlayer() {
        super();
        mStatus = Status.IDLE;
        // 设置监听者
        super.setOnCompletionListener(this);
    }

    // 重置播放
    @Override
    public void reset() {
        super.reset();
        mStatus = Status.IDLE;
    }

    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, IllegalStateException, SecurityException {
        super.setDataSource(path);
        mStatus = Status.INITIALIZED;
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        mStatus = Status.STARTED;
    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        mStatus = Status.PAUSED;
    }

    @Override
    public void stop() throws IllegalStateException {
        super.stop();
        mStatus = Status.STOPPED;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mStatus = Status.COMPLETED;
    }

    ///
    /// @name
    /// @description // 获取当前播放状态
    /// @author liuca
    /// @date 2020/8/14
    ///
    public Status getStatus() {
        return mStatus;
    }

    ///
    /// @name isCompleted
    /// @description 是否播放完成
    /// @author liuca
    /// @date 2020/8/14
    ///
    public boolean isCompleted() {
        return mStatus == Status.COMPLETED;
    }

    ///
    /// @name setCompleteListener
    /// @description 设置监听者
    /// @author liuca
    /// @date 2020/8/14
    ///
    public void setCompleteListener(OnCompletionListener listener) {
        mCompletionListener = listener;
    }


}
