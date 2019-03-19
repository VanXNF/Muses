package com.victorxu.muses.account.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.AccountContract;
import com.victorxu.muses.account.model.AccountModel;
import com.victorxu.muses.gson.UserStatus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AccountPresenter implements AccountContract.Presenter {

    private static final String TAG = "AccountPresenter";

    private AccountContract.View mView;
    private AccountContract.Model mModel;

    public AccountPresenter(AccountContract.View mView, Context context) {
        this.mView = mView;
        mModel = new AccountModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void doLoginByPWD(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            mView.showToast(R.string.please_input_phone);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mView.showToast(R.string.please_input_password);
            return;
        }
        mModel.doLoginByPWD(username, password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mView.showToast(R.string.account_failure_network_error);
                Log.e(TAG, "onFailure: doLoginByPWD");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    UserStatus userStatus = new Gson().fromJson(response.body().string(), UserStatus.class);
                    if (userStatus.getCode().equals("OK") && userStatus.getData() != null) {
                        if (mModel.saveUserInfo(userStatus.getData())) {
                            mView.notifyLoginSuccess();
                        } else {
                            mView.showToast(R.string.data_store_error_please_login_again);
                        }
                    } else {
                        mView.showToast(userStatus.getMessage());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.account_failure_data_error);
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void doLoginByCode(String mobile, String code) {
        if (TextUtils.isEmpty(mobile)) {
            mView.showToast(R.string.please_input_phone);
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mView.showToast(R.string.please_input_code);
            return;
        }
        mModel.doLoginByCode(mobile, code, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mView.showToast(R.string.account_failure_network_error);
                Log.e(TAG, "onFailure: doLoginByCode");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    UserStatus userStatus = new Gson().fromJson(response.body().string(), UserStatus.class);
                    if (userStatus.getCode().equals("OK") && userStatus.getData() != null) {
                        if (mModel.saveUserInfo(userStatus.getData())) {
                            mView.notifyLoginSuccess();
                        } else {
                            mView.showToast(R.string.data_store_error_please_login_again);
                        }
                    } else {
                        mView.showToast(userStatus.getMessage());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.account_failure_data_error);
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void doRegister(String mobile, String password, String code) {
        if (TextUtils.isEmpty(mobile)) {
            mView.showToast(R.string.please_input_phone);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mView.showToast(R.string.please_input_password);
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mView.showToast(R.string.please_input_code);
            return;
        }
        mModel.doRegister(mobile, password, code, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mView.showToast(R.string.account_failure_network_error);
                Log.e(TAG, "onFailure: doRegister");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    UserStatus userStatus = new Gson().fromJson(response.body().string(), UserStatus.class);
                    if (userStatus.getCode().equals("OK") && userStatus.getData() != null) {
                        if (mModel.saveUserInfo(userStatus.getData())) {
                            mView.notifyLoginSuccess();
                        } else {
                            mView.showToast(R.string.data_store_error_please_login_again);
                        }
                    } else {
                        mView.showToast(userStatus.getMessage());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.account_failure_data_error);
                    e.printStackTrace();
                }

            }
        });
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
