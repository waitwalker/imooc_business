package com.example.lib_audio.mediaplayer.core;

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

    private static class SingletonHolder {
        private static AudioController instance = new AudioController();
    }

    public static AudioController getInstance() {
        return SingletonHolder.instance;
    }

}
