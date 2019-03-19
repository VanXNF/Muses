package com.victorxu.muses.creation.model;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;

public class FilterApplyModel implements FilterApplyContract.Model {

    private final String COMMODITY_API = "api/commodity/";
    private final String TRANSFER_API = "api/transfer";

    private int id;
    private Context context;

    private String filterUrl = "";

    private Call mCallImage;
    private Call mCallCustomize;

    public FilterApplyModel(int id, Context context) {
        this.id = id;
        this.context = context;
    }

    @Override
    public void uploadImageData(Uri uri, Callback callback) {
        File file = new File(uri.getPath());
        Map<String, String> map = new HashMap<>();
        map.put("upload_id", String.valueOf(id));
        mCallImage = HttpUtil.postRequest(HttpUtil.FILTER_TRANSFER_SERVER, TRANSFER_API, file, map, callback);
    }

    @Override
    public void uploadCustomizeImageData(Uri uri, Callback callback) {
        File file = new File(uri.getPath());
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        Map<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(userId));
        mCallCustomize = HttpUtil.postRequest(HttpUtil.WEB_SERVER, COMMODITY_API, file, map, callback);
    }

    @Override
    public String getFilterUrl() {
        if (!TextUtils.isEmpty(filterUrl)) {
            return filterUrl;
        } else {
            return "";
        }
    }

    @Override
    public void setFilterUrl(String url) {
        filterUrl = url;
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallImage);
        cancelCall(mCallCustomize);
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }
}
