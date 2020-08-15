package com.example.lib_audio.mediaplayer.core;

import com.example.lib_audio.mediaplayer.exception.AudioQueueEmptyException;
import com.example.lib_audio.mediaplayer.model.AudioBean;

import java.util.ArrayList;

///
/// @name AudioController
/// @description 播放控制器类
/// @author liuca
/// @date 2020/8/15
///
public class AudioController {

    // 播放方式
    public enum PlayMode {
        // 列表循环
        LOOP,
        // 随机播放
        RANDOM,
        // 单曲循环
        REPEAT
    }

    // 单例
    private static class SingletonHolder {
        private static AudioController instance = new AudioController();
    }

    public static AudioController getInstance() {
        return SingletonHolder.instance;
    }

    private AudioPlayer mAudioPlayer;//播放器
    private ArrayList<AudioBean> mQueue;//播放列表
    private PlayMode mPlayMode;//播放模式
    private int mQueueIndex;//索引

    ///
    /// @name AudioController
    /// @description 私有构造方法
    /// @author liuca
    /// @date 2020/8/15
    ///
    private AudioController() {
        mAudioPlayer = new AudioPlayer();
        mQueue = new ArrayList<>();
        mQueueIndex = 0;
        mPlayMode = PlayMode.LOOP;
    }

    ///
    /// @name 设置播放队列
    /// @description
    /// @author liuca
    /// @date 2020/8/15
    ///
    public ArrayList<AudioBean> getQueue() {
        return mQueue == null ? new ArrayList<AudioBean>() : mQueue;
    }

    public void setQueue(ArrayList<AudioBean> queue) {
        this.setQueue(queue,0);
    }

    public void setQueue(ArrayList<AudioBean> queue, int queueIndex) {
        mQueue.addAll(queue);
        mQueueIndex = queueIndex;
    }

    ///
    /// @name queueIndex
    /// @description 获取&设置播放模式
    /// @author liuca
    /// @date 2020/8/15
    ///
    public PlayMode queueIndex() {
        return mPlayMode;
    }

    public void setPlayMode(PlayMode playMode) {
        mPlayMode = playMode;
    }

    public void setPlayIndex(int index) {
        if (mQueue == null) {
            throw new AudioQueueEmptyException("当前播放队列为空,请先设置播放队列");
        }
        mQueueIndex = index;
    }

    public int getPlayIndex() {
        return mQueueIndex;
    }

    ///
    /// @name isStartState
    /// @description 是否播放中
    /// @author liuca
    /// @date 2020/8/15
    ///
    public boolean isStartState() {
        return CustomMediaPlayer.Status.STARTED == getStatus();
    }

    private CustomMediaPlayer.Status getStatus() {
        return mAudioPlayer.getStatus();
    }
}
