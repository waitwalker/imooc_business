package com.example.lib_audio.mediaplayer.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.example.lib_audio.R;
import com.example.lib_audio.app.AudioHelper;
import com.example.lib_audio.mediaplayer.core.AudioController;
import com.example.lib_audio.mediaplayer.model.AudioBean;

///
/// @name NotificationHelper
/// @description 音乐播放通知栏
/// @author liuca
/// @date 2020/8/17
///
public class NotificationHelper {
    public static final String CHANNEL_ID = "channel_id_audio_";
    public static final String CHANNEL_NAME = "channel_name_audio_";
    public static final int NOTIFICATION_ID = 0x1111;

    // UI
    private Notification mNotification;
    private RemoteViews mRemoteViews; //大布局
    private RemoteViews mSmallRemoteViews;//小布局
    private NotificationManager mNotificationManager;

    // data
    private NotificationHelperListener mListener;//与Service回调通信
    private String packageName;

    // 当前播放音乐Bean
    private AudioBean mAudioBean;


    // 单例
    public static NotificationHelper getInstance() {
        return SingletonHolder.instance;
    }
    public static class SingletonHolder {
        private static NotificationHelper instance = new NotificationHelper();
    }

    private void init(NotificationHelperListener listener) {
        mNotificationManager = (NotificationManager) AudioHelper.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        packageName = AudioHelper.getContext().getPackageName();
        mAudioBean = AudioController.getInstance().getNowPlaying();
        initNotification();
        mListener = listener;
        if (mListener != null) mListener.onNotificationInit();
    }

    private void initNotification() {
        if (mNotification == null) {
            initRemoteViews();
            //Intent intent = new Intent(AudioHelper.getContext(), )
            Intent intent = new Intent();
            PendingIntent pendingIntent = PendingIntent.getActivity(AudioHelper.getContext(),0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH);
                channel.enableLights(false);
                channel.enableVibration(false);
                mNotificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(AudioHelper.getContext(), CHANNEL_ID).setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setCustomContentView(mRemoteViews)
                    .setContent(mSmallRemoteViews);
            mNotification = builder.build();

            showLoadStatus(mAudioBean);
        }
    }

    private void initRemoteViews() {

    }

    public Notification getNotification() {
        return mNotification;
    }

    private void showLoadStatus(AudioBean bean) {

    }

    // 接口
    public interface NotificationHelperListener{
        void onNotificationInit();
    }
}
