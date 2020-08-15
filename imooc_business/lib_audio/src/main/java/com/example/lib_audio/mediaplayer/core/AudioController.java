package com.example.lib_audio.mediaplayer.core;

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

}
