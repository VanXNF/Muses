package com.victorxu.muses.mine.model;

import android.content.Context;

import com.victorxu.muses.mine.contract.CollectionContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Callback;

public class CollectionModel implements CollectionContract.Model {

    private final String COLLECTION_API = "api/favorite/commodity/";

    private Context context;

    public CollectionModel(Context context) {
        this.context = context;
    }

    @Override
    public void getCollectionData(Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        HttpUtil.getRequest(COLLECTION_API + String.valueOf(userId), callback);
    }
}
