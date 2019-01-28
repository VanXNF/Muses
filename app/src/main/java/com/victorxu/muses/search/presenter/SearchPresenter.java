package com.victorxu.muses.search.presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.HotKey;
import com.victorxu.muses.search.contract.SearchContract;
import com.victorxu.muses.search.model.SearchModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchPresenter implements SearchContract.Presenter {

    private static final String TAG = "SearchPresenter";

    private SearchContract.Model mModel;
    private SearchContract.View mView;

    public SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
        mModel = new SearchModel();
    }

    @Override
    public void loadRootView(View view) {
        mView.initView(view);
    }

    @Override
    public void loadDataToView() {
        mView.hideHistoryKey();
        mView.showHistoryKey(mModel.getHistoryKeyData());
        mView.hideHotKey();
        mView.showLoading();
        mModel.getHotKeyData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG, "onFailure: getHotKeyData");
                mView.hideLoading();
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HotKey hotKey = new Gson().fromJson(response.body().string(), HotKey.class);
                if (hotKey != null && hotKey.getCode().equals("OK") && hotKey.getHotKeyData().size() != 0) {
                    mView.hideLoading();
                    mView.showHotKey(hotKey.getHotKeyData());
                    Log.d(TAG, "onResponse: getHotKeyData");
                } else {
                    mView.hideLoading();
                    mView.showToast(R.string.data_error_please_try_again);
                    Log.w(TAG, "onResponse: getHotKeyData DATA ERROR");
                }
            }
        });
    }

    @Override
    public void deleteHistoryData(String keyword) {
        mModel.removeHistoryKeyData(keyword);
        reloadHistoryDataToView();
    }

    @Override
    public void deleteAllHistoryData() {
        mModel.clearAllHistoryKeyData();
        mView.hideHistoryKey();
    }

    @Override
    public void reloadDataToView() {
        loadDataToView();
    }

    @Override
    public void reloadHistoryDataToView() {
        mView.hideHistoryKey();
        mView.showHistoryKey(mModel.getHistoryKeyData());
    }

    @Override
    public void goToSearch(String key) {
        mModel.addHistoryKeyData(key);
        mView.goToSearch(key);
    }
}
