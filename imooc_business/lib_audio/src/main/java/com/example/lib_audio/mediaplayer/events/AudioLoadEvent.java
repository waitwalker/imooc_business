package com.example.lib_audio.mediaplayer.events;

import com.example.lib_audio.mediaplayer.model.AudioBean;

public class AudioLoadEvent {
    public AudioBean audioBean;

    public AudioLoadEvent(AudioBean audioBean) {
        this.audioBean = audioBean;
    }
}
