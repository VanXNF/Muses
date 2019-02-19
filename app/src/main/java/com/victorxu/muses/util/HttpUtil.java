package com.victorxu.muses.util;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
//    "http://192.168.2.225:8080/"
//    "http://120.79.162.134:7010/"
    private static final String BASE_API = "http://120.79.162.134:7010/";

    /**
     * Get 方法
     * @param address Api 地址
     * @param callback 回调方法
     */
    public static void getRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BASE_API + address).build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * Post 传参
     * @param address api 地址
     * @param map 键值对
     * @param callback 回调方法
     */
    @SuppressWarnings("ConstantConditions")
    public static void postRequest(String address, Map<String, String> map, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key : map.keySet()) {
            bodyBuilder.add(key, map.get(key));
        }
        Request request = new Request.Builder()
                .post(bodyBuilder.build())
                .url(BASE_API + address)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * Post 传 json
     * @param address api 地址
     * @param json json 数据
     * @param callback 回调方法
     */
    public static void postRequest(String address, String json, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(BASE_API + address)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * Delete 方法
     * @param address api地址
     * @param callback 回调方法
     */
    public static void deleteRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .delete()
                .url(BASE_API + address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
