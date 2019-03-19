package com.victorxu.muses.mine.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Collection;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.mine.contract.CollectionContract;
import com.victorxu.muses.mine.model.CollectionModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            public void onResponse(Call call, Response response) {
                try {
                    Collection collection = new Gson().fromJson(response.body().string(), Collection.class);
                    if (collection.getCode().equals("OK")) {
                        mModel.setCollectionData(collection.getData());
                        mView.showCollection(collection.getData());
                    } else {
                        mView.showToast(collection.getMessage());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    mView.hideLoading();
                }


            }
        });
    }

    @Override
    public void reloadDataToView() {
        loadDataToView();
    }

    @Override
    public void removeFavorite(int position) {
        mModel.removeFromFavorite(position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: removeFromFavorite");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("ERROR")) {
                        mView.showToast(status.getMessage());
                    } else {
                        List<Collection.CollectionBean> data = new ArrayList<>();
                        data.addAll(mModel.getCollectionData());
                        data.remove(position);
                        mModel.setCollectionData(data);
                        mView.showCollection(data);
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
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
