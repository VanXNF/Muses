package com.victorxu.muses.mine.model;

import android.content.Context;

import com.victorxu.muses.gson.UserStatus;
import com.victorxu.muses.mine.contract.MineContract;
import com.victorxu.muses.util.SharedPreferencesUtil;

public class MineModel implements MineContract.Model {

    private Context context;

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
    public boolean checkUserStatus() {
        return (int) SharedPreferencesUtil.get(context, "UserId", 0) != 0;
    }
}
