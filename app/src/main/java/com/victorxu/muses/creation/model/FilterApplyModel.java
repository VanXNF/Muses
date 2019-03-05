package com.victorxu.muses.creation.model;

import android.net.Uri;

import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.util.HttpUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;

public class FilterApplyModel implements FilterApplyContract.Model {

    private final String TRANSFER_API = "api/transfer";

    private int id;

    public FilterApplyModel(int id) {
        this.id = id;
    }

    @Override
    public void uploadImageData(Uri uri, Callback callback) {
        File file = new File(uri.getPath());
        Map<String, String> map = new HashMap<>();
        map.put("upload_id", String.valueOf(id));
        HttpUtil.postRequest(HttpUtil.FILTER_TRANSFER_SERVER, TRANSFER_API, file, map, callback);
    }
}
