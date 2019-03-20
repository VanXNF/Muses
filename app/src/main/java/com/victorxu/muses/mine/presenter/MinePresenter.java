package com.victorxu.muses.mine.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.mine.contract.MineContract;
import com.victorxu.muses.mine.model.MineModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"ConstantConditions", "NullableProblems"})
public class MinePresenter implements MineContract.Presenter {

    private static final String TAG = "MinePresenter";

    private MineContract.View mView;
    private MineContract.Model mModel;

    public MinePresenter(MineContract.View mView, Context context) {
        this.mView = mView;
        mModel = new MineModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        if (mModel.checkUserStatus()) {
            mView.showBaseUserInfo(mModel.getUserData());
            mModel.getCollectionCountData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getCollectionCountData");
                    if (!e.getMessage().equals("Socket closed")) {
                        mView.showToast(R.string.network_error_please_try_again);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        Status status = new Gson().fromJson(response.body().string(), Status.class);
                        if (status.getCode().equals("OK") && status.getData() != null) {
                            mView.showCollectionCount(((Double) status.getData()).intValue());
                        } else {
                            mView.showToast(status.getMessage());
                        }
                    } catch (Exception e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void goToAccount() {
        if (mModel.checkUserStatus()) {
            mView.goToProfilePage();
        } else {
            mView.goToLoginPage();
        }
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
