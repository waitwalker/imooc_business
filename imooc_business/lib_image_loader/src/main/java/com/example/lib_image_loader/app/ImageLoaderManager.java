package com.example.lib_image_loader.app;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.lib_image_loader.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

///
/// @name ImageLoaderManager
/// @description 图片加载类,与外界通信类,支持各种View,notification,appwidget,viewgroup加载图片
/// @author liuca
/// @date 2020/8/13
///
public class ImageLoaderManager {

    private ImageLoaderManager() {}

    /// 单例的构建方式一: 通过内部类
    private static class SingletonHolder {
        private static ImageLoaderManager instance = new ImageLoaderManager();
    }

    public static ImageLoaderManager getInstance() {
        return SingletonHolder.instance;
    }

    ///
    /// @name displayImageForView
    /// @description 为ImageView加载图片
    /// @author liuca
    /// @date 2020/8/13
    ///
    public void displayImageForView(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(BitmapTransitionOptions.withCrossFade())
                .into(imageView);
    }

    ///
    /// @name displayImageForCircle
    /// @description 为ImageView加载圆形图形
    /// @author liuca
    /// @date 2020/8/13
    ///
    public void displayImageForCircle(final ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new BitmapImageViewTarget(imageView) {
                    // 将ImageView包装成target
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory
                                .create(imageView.getResources(), resource);
                        drawable.setCircular(true);
                        imageView.setImageDrawable(drawable);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private RequestOptions initCommonRequestOption() {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.b4y)//占位图片
                .error(R.mipmap.b4y)//失败占位
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)//缓存策略
                .skipMemoryCache(false)//是否错过内存缓存
                .priority(Priority.NORMAL);//优先级
        return options;
    }
}
