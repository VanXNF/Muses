package com.victorxu.muses.creation.model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.victorxu.muses.creation.contract.FilterCreateContract;
import com.victorxu.muses.creation.model.entity.FilterCreateEntity;
import com.victorxu.muses.util.Base64Util;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Callback;

public class FilterCreateModel implements FilterCreateContract.Model {

    private final String FILTER_CREATE_API = "api/createFilter";

    private Context context;

    public FilterCreateModel(Context context) {
        this.context = context;
    }

    @Override
    public void uploadFilter(String filterName, int brushSize, int brushIntensity, int smooth, Uri uri, Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);

        FilterCreateEntity entity = new FilterCreateEntity();
        entity.setFilterName(filterName);
        entity.setUserId(userId);
        entity.setStyleTemplate(Base64Util.imageToBase64(uri.getPath()));
        entity.setBrushSize(brushSize);
        entity.setBrushIntensity(brushIntensity);
        entity.setSmooth(smooth);

        HttpUtil.postRequest(HttpUtil.FILTER_TRAIN_SERVER, FILTER_CREATE_API, new Gson().toJson(entity), callback);
    }
}
