package com.example.lib_network;

import com.example.lib_network.listener.DisposeDataHandle;
import com.example.lib_network.response.CommonFileCallBack;
import com.example.lib_network.response.CommonJsonCallBack;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

///
/// @name CommonOkHttpClient
/// @description 请求工具
/// @author liuca
/// @date 2020/8/13
///
public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            // 验证域名
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        // 添加拦截器
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                // 为所有请求添加请求头
                Request request = chain.request().newBuilder().addHeader("User-Agent", "Imooc-Mobile").build();
                return chain.proceed(request);
            };
        });

        /// 设置超时时间
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        /// 允许重定向
        okHttpClientBuilder.followRedirects(true);

        mOkHttpClient = okHttpClientBuilder.build();
    }

    ///
    /// @name get
    /// @description get请求
    /// @author liuca
    /// @date 2020/8/13
    ///
    public static Call get(Request request, DisposeDataHandle disposeDataHandle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallBack(disposeDataHandle));
        return call;
    }

    ///
    /// @name post
    /// @description post请求
    /// @author liuca
    /// @date 2020/8/13
    ///
    public static Call post(Request request, DisposeDataHandle disposeDataHandle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallBack(disposeDataHandle));
        return call;
    }

    ///
    /// @name downloadFile
    /// @description 文件下载请求
    /// @author liuca
    /// @date 2020/8/13
    ///
    public static Call downloadFile(Request request, DisposeDataHandle disposeDataHandle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallBack(disposeDataHandle));
        return call;
    }
}
