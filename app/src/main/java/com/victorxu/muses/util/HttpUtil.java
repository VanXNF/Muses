package com.victorxu.muses.util;

import android.graphics.BitmapFactory;
import android.media.ImageReader;
import android.util.Log;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    //    "http://192.168.2.225:8080/"
//    "http://120.79.162.134:7010/"
    public static final String WEB_SERVER = "http://muses.deepicecream.com:7010/";
    public static final String FILTER_TRANSFER_SERVER = "http://art.deepicecream.com:7004/";
    public static final String FILTER_TRAIN_SERVER = "http://art.deepicecream.com:7003/";

    /**
     * Get 方法
     *
     * @param address  Api 地址
     * @param callback 回调方法
     */
    public static Call getRequest(String address, okhttp3.Callback callback) {
        return getRequest(WEB_SERVER, address, callback);
    }

    public static Call getRequest(String server, String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(server + address).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }


    /**
     * Post 传参
     *
     * @param address  api 地址
     * @param map      键值对
     * @param callback 回调方法
     */
    @SuppressWarnings("ConstantConditions")
    public static Call postRequest(String address, Map<String, String> map, okhttp3.Callback callback) {
        return postRequest(WEB_SERVER, address, map, callback);
    }

    public static Call postRequest(String server, String address, Map<String, String> map, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key : map.keySet()) {
            bodyBuilder.add(key, map.get(key));
        }
        Request request = new Request.Builder()
                .post(bodyBuilder.build())
                .url(server + address)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static Call postRequest(String server, String address, File file, Map<String, String> map, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        String type = options.outMimeType;
        Log.d("image type:", type);

        RequestBody requestBody = RequestBody.create(MediaType.parse(type), file);

        MultipartBody.Builder multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), requestBody);
        for (String key : map.keySet()) {
            multipartBody.addFormDataPart(key, map.get(key));
        }
        Request request = new Request.Builder()
                .post(multipartBody.build())
                .url(server + address)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * Post 传 json
     *
     * @param address  api 地址
     * @param json     json 数据
     * @param callback 回调方法
     */
    public static Call postRequest(String address, String json, okhttp3.Callback callback) {
        return postRequest(WEB_SERVER, address, json, callback);
    }

    public static Call postRequest(String server, String address, String json, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(server + address)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }


    /**
     * Delete 方法
     *
     * @param address  api地址
     * @param callback 回调方法
     */
    public static Call deleteRequest(String address, okhttp3.Callback callback) {
        return deleteRequest(WEB_SERVER, address, callback);
    }

    public static Call deleteRequest(String server, String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .delete()
                .url(server + address)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * Put 传 json
     *
     * @param address  api地址
     * @param json     json 数据
     * @param callback 回调方法
     */
    public static Call putRequest(String address, String json, okhttp3.Callback callback) {
        return putRequest(WEB_SERVER, address, json, callback);
    }

    public static Call putRequest(String server, String address, String json, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(server + address)
                .put(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
