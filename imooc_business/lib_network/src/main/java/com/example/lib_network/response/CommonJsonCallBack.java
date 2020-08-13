package com.example.lib_network.response;

import android.os.Handler;
import android.os.Looper;

import com.example.lib_network.exception.OkHttpException;
import com.example.lib_network.listener.DisposeDataHandle;
import com.example.lib_network.listener.DisposeDataListener;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

///
/// @name CommonJsonCallBack
/// @description 处理Json类型响应
/// @author liuca
/// @date 2020/8/13
///
public class CommonJsonCallBack implements Callback {

    protected final String Empty_Message = "";

    /// 网络异常
    protected final int Network_Error = -1;
    /// json异常
    protected final int Json_Error = -2;
    /// 未知异常
    protected final int Other_Error = -3;

    /// 处理回调
    private DisposeDataListener mListener;

    /// json要转换成的类
    private Class<?> mClass;

    private Handler mDeliverHandler;

    public CommonJsonCallBack(DisposeDataHandle handle){
        mListener = handle.mListener;
        mClass = handle.mClass;
        mDeliverHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull final IOException e) {
        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(Network_Error,e));
            }
        });
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        final String result = response.body().string();
        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(Object responseObj) {
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(Network_Error, Empty_Message));
            return;
        }

        try {
            JSONObject result = new JSONObject(responseObj.toString());
            if (mClass == null) {
                mListener.onSuccess(result);
            } else {
                Object obj = new Gson().fromJson(responseObj.toString(), mClass);
                if (obj != null) {
                    mListener.onSuccess(obj);
                } else {
                    mListener.onFailure(new OkHttpException(Json_Error, Empty_Message));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(Other_Error, e.getMessage()));
            e.printStackTrace();
        }
    }
}
