package com.victorxu.muses.mine.model;

import android.content.Context;

import com.victorxu.muses.gson.UserStatus;
import com.victorxu.muses.mine.contract.MineContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Call;
import okhttp3.Callback;

public class MineModel implements MineContract.Model {

    private final String COLLECTION_COUNT_API = "api/favorite/commodity/count/";

    private Context context;
    private Call mCallGet;

    public MineModel(Context context) {
        this.context = context;
    }

    @Override
    public UserStatus.UserBean getUserData() {
        UserStatus.UserBean user = new UserStatus.UserBean();
        user.setUserId((int) SharedPreferencesUtil.get(context, "UserId", 0));
        user.setAvatar((String) SharedPreferencesUtil.get(context, "UserAvatar", ""));
        user.setUsername((String) SharedPreferencesUtil.get(context, "UserName", ""));
        user.setToken((String) SharedPreferencesUtil.get(context, "UserToken", ""));
        return user;
    }

    @Override
    public void getCollectionCountData(Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        mCallGet = HttpUtil.getRequest(COLLECTION_COUNT_API + String.valueOf(userId), callback);
    }

    @Override
    public boolean checkUserStatus() {
        return (int) SharedPreferencesUtil.get(context, "UserId", 0) != 0;
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallGet);
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }
}
