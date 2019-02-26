package com.victorxu.muses.account.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.InfoContract;
import com.victorxu.muses.account.model.InfoModel;
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
                        mView.showUserInfo(userInfo.getData());
                    } else {
                        mView.showToast(userInfo.getMessage());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void quit() {
//        mModel.removeLocalUserData();
    }
}
