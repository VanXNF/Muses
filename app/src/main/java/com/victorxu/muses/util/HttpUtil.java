package com.victorxu.muses.util;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {


    /**
     * get
     * */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * post
     * */
    @SuppressWarnings("ConstantConditions")
    public static void sendOkHttpRequest(String address, Map<String,String> map, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key : map.keySet()) {
            bodyBuilder.add(key, map.get(key));
        }
        Request request = new Request.Builder()
                .post(bodyBuilder.build())
                .url(address)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
