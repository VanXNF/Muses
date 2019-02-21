package com.victorxu.muses.mine.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Collection;
import com.victorxu.muses.mine.contract.CollectionContract;
import com.victorxu.muses.mine.model.CollectionModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CollectionPresenter implements CollectionContract.Presenter {

    private static final String TAG = "CollectionPresenter";

    private CollectionContract.View mView;
    private CollectionContract.Model mModel;

    public CollectionPresenter(CollectionContract.View mView, Context context) {
        this.mView = mView;
        mModel = new CollectionModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mView.showLoading();
        mModel.getCollectionData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getCollectionData");
                mView.showToast(R.string.network_error_please_try_again);
                mView.hideLoading();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mView.hideLoading();
                Collection collection = new Gson().fromJson(response.body().string(), Collection.class);
                if (collection != null) {
                    if (collection.getCode().equals("OK")) {
                        mView.showCollection(collection.getData());
                    } else {
                        mView.showToast(collection.getMessage());
                    }
                } else {
                    mView.showToast(R.string.data_error_please_try_again);
                    Log.w(TAG, "onResponse: DATA ERROR");
                }
            }
        });
    }

    @Override
    public void reloadDataToView() {
        loadDataToView();
    }
}
