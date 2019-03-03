package com.victorxu.muses.creation.presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.creation.contract.CreationContract;
import com.victorxu.muses.creation.model.CreationModel;
import com.victorxu.muses.gson.FilterClass;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreationPresenter implements CreationContract.Presenter {

    private static final String TAG = "CreationPresenter";

    private CreationContract.View mView;
    private CreationContract.Model mModel;

    public CreationPresenter(CreationContract.View mView) {
        this.mView = mView;
        mModel = new CreationModel();
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mModel.getFilterClassData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getFilterClassData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    FilterClass filterClass = new Gson().fromJson(response.body().string(), FilterClass.class);
                    if (filterClass.getCode().equals("OK")) {
                        mView.showFilterClasses(filterClass.getData());
                    } else {
                        throw new IOException();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
        mView.showPopularSearch(mModel.getPopularSearchData());
    }
}
