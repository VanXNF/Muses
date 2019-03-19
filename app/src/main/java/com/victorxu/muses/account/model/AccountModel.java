package com.victorxu.muses.account.model;

import android.content.Context;

import com.google.gson.Gson;
import com.victorxu.muses.account.contract.AccountContract;
import com.victorxu.muses.account.model.entity.UserAccountEntity;
import com.victorxu.muses.gson.UserStatus;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Call;
import okhttp3.Callback;

public class AccountModel implements AccountContract.Model {

    private final String REGISTER_API = "api/user/register";
    private final String LOGIN_USERNAME_API = "api/user/login/username";
    private final String LOGIN_MOBILE_API = "api/user/login/mobile";
    private Context context;

    private Call mCallLoginPWD;
    private Call mCallLoginCode;
    private Call mCallRegister;

    public AccountModel(Context context) {
        this.context = context;
    }

    @Override
    public void doLoginByPWD(String username, String password, Callback callback) {
        UserAccountEntity entity = new UserAccountEntity();
        entity.setUsername(username);
        entity.setPassword(password);
        mCallLoginPWD = HttpUtil.postRequest(LOGIN_USERNAME_API, new Gson().toJson(entity), callback);
    }

    @Override
    public void doLoginByCode(String mobile, String code, Callback callback) {
        UserAccountEntity entity = new UserAccountEntity();
        entity.setMobile(mobile);
        entity.setPassword(code);
        mCallLoginCode = HttpUtil.postRequest(LOGIN_MOBILE_API, new Gson().toJson(entity), callback);
    }

    @Override
    public void doRegister(String mobile, String password, String code, Callback callback) {
        UserAccountEntity entity = new UserAccountEntity();
        entity.setMobile(mobile);
        entity.setPassword(password);
        entity.setCode(code);
        mCallRegister = HttpUtil.postRequest(REGISTER_API, new Gson().toJson(entity), callback);
    }

    @Override
    public boolean saveUserInfo(UserStatus.UserBean userBean) {
        try {
            SharedPreferencesUtil.put(context, "UserAvatar", userBean.getAvatar());
            SharedPreferencesUtil.put(context, "UserToken", userBean.getToken());
            SharedPreferencesUtil.put(context, "UserId", userBean.getUserId());
            SharedPreferencesUtil.put(context, "UserName", userBean.getUsername());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallLoginPWD);
        cancelCall(mCallLoginCode);
        cancelCall(mCallRegister);
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }
}
