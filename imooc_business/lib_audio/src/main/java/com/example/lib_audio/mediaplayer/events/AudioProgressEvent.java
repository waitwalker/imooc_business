package com.example.lib_audio.mediaplayer.events;

import com.example.lib_audio.mediaplayer.core.CustomMediaPlayer;

public class AudioProgressEvent {
    public CustomMediaPlayer.Status mStatus;
    public int progress;
    public int maxLength;

    public AudioProgressEvent(CustomMediaPlayer.Status mStatus, int progress, int maxLength) {
        this.mStatus = mStatus;
        this.progress = progress;
        this.maxLength = maxLength;
    }
}
