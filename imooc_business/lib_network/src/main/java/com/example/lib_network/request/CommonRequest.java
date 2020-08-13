package com.example.lib_network.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;

///
/// @name CommonRequest
/// @description 请求,对外提供get/post等方法
/// @author liuca
/// @date 2020/8/13
///
public class CommonRequest {

    ///
    /// @name createPostRequest
    /// @description 构建post 请求对象
    /// @author liuca
    /// @date 2020/8/13
    ///
    public static Request createPostRequest(String url, RequestParams params) {
        return createPostRequest(url, params, null);
    }

    ///
    /// @name createPostRequest
    /// @description 构建post 请求对象
    /// @author liuca
    /// @date 2020/8/13
    ///
    public static Request createPostRequest(String url, RequestParams params, RequestParams headers) {
        // 构建请求体 构建者模式
        FormBody.Builder mFormBodyBuilder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry: params.urlParams.entrySet()) {
                // 参数遍历
                mFormBodyBuilder.add(entry.getKey(),entry.getValue());
            }
        }

        // 构建请求头
        Headers.Builder mHeadersBuilder = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String,String> entry: headers.urlParams.entrySet()) {
                //请求头遍历
                mHeadersBuilder.add(entry.getKey(),entry.getValue());
            }
        }

        // 构建Request对象 构建者模式,将请求url,请求头,请全体传进去
        Request request = new Request.Builder()
                .url(url)
                .headers(mHeadersBuilder.build())
                .post(mFormBodyBuilder.build())
                .build();
        return request;
    }
}