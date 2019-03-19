package com.victorxu.muses.account.model;

import android.content.Context;

import com.google.gson.Gson;
import com.victorxu.muses.account.contract.InfoContract;
import com.victorxu.muses.account.model.entity.UserAccountEntity;
import com.victorxu.muses.gson.UserInfo;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Call;
import okhttp3.Callback;

public class InfoModel implements InfoContract.Model {

    private final String USER_API = "api/user/info/";
    private final String USER_PWD_API = "api/user/password";

    private Context context;
    private UserInfo.UserInfoBean mUserInfoData;

    private Call mCallGet;
    private Call mCallUpdateInfo;
    private Call mCallUpdatePwd;

    public InfoModel(Context context) {
        this.context = context;
    }

    @Override
    public void getUserInfoData(Callback callback) {
        String token = (String) SharedPreferencesUtil.get(context, "UserToken", "");
        mCallGet = HttpUtil.getRequest(USER_API + token, callback);
    }

    @Override
    public void updateUserInfo(Callback callback) {
        String json = new Gson().toJson(mUserInfoData);
        mCallUpdateInfo = HttpUtil.putRequest(USER_API, json, callback);
    }

    @Override
    public void updateUserPassword(String oldPassword, String newPassword, Callback callback) {
        UserAccountEntity entity = new UserAccountEntity();
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        entity.setUserId(userId);
        entity.setOldPassword(oldPassword);
        entity.setNewPassword(newPassword);
        mCallUpdatePwd = HttpUtil.postRequest(USER_PWD_API, new Gson().toJson(entity), callback);
    }

    @Override
    public void removeLocalUserData() {
        SharedPreferencesUtil.remove(context, "UserAvatar");
        SharedPreferencesUtil.remove(context, "UserToken");
        SharedPreferencesUtil.remove(context, "UserId");
        SharedPreferencesUtil.remove(context, "UserName");
    }

    @Override
    public void updateCacheData() {
        SharedPreferencesUtil.put(context, "UserName", mUserInfoData.getNickname());
    }

    @Override
    public UserInfo.UserInfoBean getLocalUserInfoData() {
        return mUserInfoData;
    }

    @Override
    public void setLocalUserInfoData(UserInfo.UserInfoBean data) {
        mUserInfoData = new UserInfo.UserInfoBean();
        mUserInfoData.setId(data.getId());
        mUserInfoData.setUsername(data.getUsername());
        mUserInfoData.setPassword(data.getPassword());
        mUserInfoData.setAvatar(data.getAvatar());
        mUserInfoData.setNickname(data.getNickname());
        mUserInfoData.setBirthday(data.getBirthday());
        mUserInfoData.setGender(data.getGender());
        mUserInfoData.setMobile(data.getMobile());
        mUserInfoData.setEmail(data.getEmail());
        mUserInfoData.setLevel(data.getLevel());
        mUserInfoData.setToken(data.getToken());
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallGet);
        cancelCall(mCallUpdateInfo);
        cancelCall(mCallUpdatePwd);
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }
}
