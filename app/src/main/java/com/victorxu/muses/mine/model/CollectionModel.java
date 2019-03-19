package com.victorxu.muses.mine.model;

import android.content.Context;

import com.victorxu.muses.gson.Collection;
import com.victorxu.muses.mine.contract.CollectionContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

public class CollectionModel implements CollectionContract.Model {

    private final String FAVORITE_API = "api/favorite/commodity/";

    private Context context;
    private List<Collection.CollectionBean> mCollectionData = new ArrayList<>();

    private Call mCallGet;
    private Call mCallDelete;

    public CollectionModel(Context context) {
        this.context = context;
    }

    @Override
    public void getCollectionData(Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        mCallGet = HttpUtil.getRequest(FAVORITE_API + String.valueOf(userId), callback);
    }

    @Override
    public void removeFromFavorite(int position, Callback callback) {
        int favId = mCollectionData.get(position).getId();
        mCallDelete = HttpUtil.deleteRequest(FAVORITE_API + String.valueOf(favId), callback);
    }

    @Override
    public List<Collection.CollectionBean> getCollectionData() {
        return mCollectionData;
    }

    @Override
    public void setCollectionData(List<Collection.CollectionBean> data) {
        mCollectionData.clear();
        mCollectionData.addAll(data);
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallGet);
        cancelCall(mCallDelete);
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }
}
