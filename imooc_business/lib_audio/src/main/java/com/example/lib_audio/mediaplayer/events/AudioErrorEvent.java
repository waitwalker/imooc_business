package com.example.lib_audio.mediaplayer.events;

public class AudioErrorEvent {
    public enum ErrorCode {
        LoadError,
        PlayError
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public AudioErrorEvent(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


}
