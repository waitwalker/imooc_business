package com.example.lib_network.listener;

///
/// @name DisposeDataHandle
/// @description 包装类
/// @author liuca
/// @date 2020/8/13
///
public class DisposeDataHandle {
    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;
    public  String mSource = null; //文件保存路径
    public  DisposeDataHandle(DisposeDataListener listener){
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }

    public DisposeDataHandle(DisposeDataListener listener, String source) {
        this.mListener = listener;
        this.mSource = source;
    }
}
