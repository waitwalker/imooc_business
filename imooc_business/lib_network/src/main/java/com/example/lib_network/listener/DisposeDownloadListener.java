package com.example.lib_network.listener;

///
/// @name DisposeDownloadListener
/// @description 处理进度
/// @author liuca
/// @date 2020/8/13
///
public interface DisposeDownloadListener extends DisposeDataListener{
    void onProgress(int progress);
}
