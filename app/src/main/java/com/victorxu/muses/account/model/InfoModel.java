package com.victorxu.muses.account.model;

import android.content.Context;

import com.victorxu.muses.account.contract.InfoContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Callback;

public class InfoModel implements InfoContract.Model {

    private final String USER_API = "api/user/info/";

    private Context context;

    public InfoModel(Context context) {
        this.context = context;
    }

    @Override
    public void getUserInfoData(Callback callback) {
        String token = (String) SharedPreferencesUtil.get(context, "UserToken", "");
        HttpUtil.getRequest(USER_API + token, callback);
    }

    @Override
    public void removeLocalUserData() {
        SharedPreferencesUtil.clear(context);
    }
}
