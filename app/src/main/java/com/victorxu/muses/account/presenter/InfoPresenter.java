package com.victorxu.muses.account.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.InfoContract;
import com.victorxu.muses.account.model.InfoModel;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.gson.UserInfo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class InfoPresenter implements InfoContract.Presenter {

    private static final String TAG = "InfoPresenter";

    private InfoContract.Model mModel;
    private InfoContract.View mView;

    public InfoPresenter(InfoContract.View mView, Context context) {
        this.mView = mView;
        mModel = new InfoModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mModel.getUserInfoData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getUserInfoData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    UserInfo userInfo = new Gson().fromJson(response.body().string(), UserInfo.class);
                    if (userInfo.getCode().equals("OK")) {
                        mModel.setLocalUserInfoData(userInfo.getData());
                        mView.showUserInfo(userInfo.getData());
                    } else {
                        mView.showToast(userInfo.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        mModel.updateUserPassword(oldPassword, newPassword, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updatePassword");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    mView.showToast(status.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updateNickname(String name) {
        UserInfo.UserInfoBean user = mModel.getLocalUserInfoData();
        user.setNickname(name);
        mModel.setLocalUserInfoData(user);
        mModel.updateUserInfo(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updateNickname");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("ERROR")) {
                        mView.showToast(status.getMessage());
                        Log.w(TAG, "onResponse: updateNickname");
                    } else {
                        mModel.updateCacheData();
                        mView.showNickname(name);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updateBirthday(long timestamp) {
        UserInfo.UserInfoBean user = mModel.getLocalUserInfoData();
        user.setBirthday(timestamp);
        mModel.setLocalUserInfoData(user);
        mModel.updateUserInfo(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updateBirthday");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("ERROR")) {
                        mView.showToast(status.getMessage());
                        Log.w(TAG, "onResponse: updateBirthday");
                    } else {
                        mView.showBirthday(timestamp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updateGender(int gender) {
        UserInfo.UserInfoBean user = mModel.getLocalUserInfoData();
        user.setGender(gender);
        mModel.setLocalUserInfoData(user);
        mModel.updateUserInfo(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updateGender");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("ERROR")) {
                        mView.showToast(status.getMessage());
                        Log.w(TAG, "onResponse: updateGender");
                    } else {
                        mView.showGender(gender);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updateEmail(String email) {
        UserInfo.UserInfoBean user = mModel.getLocalUserInfoData();
        user.setEmail(email);
        mModel.setLocalUserInfoData(user);
        mModel.updateUserInfo(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updateEmail");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("ERROR")) {
                        mView.showToast(status.getMessage());
                        Log.w(TAG, "onResponse: updateEmail");
                    } else {
                        mView.showEmail(email);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void quit() {
        mModel.removeLocalUserData();
    }

    @Override
    public void destroy() {
        mView = null;
        if (mModel != null) {
            mModel.cancelTask();
            mModel = null;
        }
    }

}
