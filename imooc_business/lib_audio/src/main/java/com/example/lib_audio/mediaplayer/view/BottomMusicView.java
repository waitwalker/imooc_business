package com.example.lib_audio.mediaplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_audio.R;
import com.example.lib_audio.mediaplayer.events.AudioLoadEvent;
import com.example.lib_audio.mediaplayer.events.AudioPauseEvent;
import com.example.lib_audio.mediaplayer.events.AudioProgressEvent;
import com.example.lib_audio.mediaplayer.events.AudioStartEvent;
import com.example.lib_audio.mediaplayer.model.AudioBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BottomMusicView extends RelativeLayout {
    private Context mContext;

    /*
     * View
     */
    private ImageView mLeftView;
    private TextView mTitleView;
    private TextView mAlbumView;
    private ImageView mPlayView;
    private ImageView mRightView;
    /*
     * data
     */
    private AudioBean mAudioBean;

    public BottomMusicView(Context context) {
        this(context, null);
    }

    public BottomMusicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.bottom_view, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) public void onAudioLoadEvent(AudioLoadEvent event) {
        //更新当前view为load状态
        //mAudioBean = event.mAudioBean;
        //showLoadView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) public void onAudioPauseEvent(AudioPauseEvent event) {
        //更新当前view为暂停状态
        //showPauseView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) public void onAudioStartEvent(AudioStartEvent event) {
        //更新当前view为播放状态
        //showPlayView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioProgrssEvent(AudioProgressEvent event) {
        //更新当前view的播放进度
    }
}
