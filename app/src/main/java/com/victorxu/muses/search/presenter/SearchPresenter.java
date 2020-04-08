package com.victorxu.muses.search.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.gson.HotKey;
import com.victorxu.muses.search.contract.SearchContract;
import com.victorxu.muses.search.model.SearchModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class SearchPresenter implements SearchContract.Presenter {

    private static final String TAG = "SearchPresenter";

    private SearchContract.Model mModel;
    private SearchContract.View mView;

    public SearchPresenter(SearchContract.View mView, Context context) {
        this.mView = mView;
        mModel = new SearchModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initView(view);
    }

    @Override
    public void loadDataToView() {
        mView.hideHistoryKey();
        List<HistoryKey> historyKeys = mModel.getHistoryKeyData();
        if (historyKeys != null) {
            mView.showHistoryKey(mModel.getHistoryKeyData());
        }
        mView.hideHotKey();
        mView.showLoading();
        mModel.getHotKeyData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG, "onFailure: getHotKeyData");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.hideLoading();
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    HotKey hotKey = new Gson().fromJson(response.body().string(), HotKey.class);
                    if (hotKey.getCode().equals("OK") && hotKey.getHotKeyData().size() != 0) {
                        mView.showHotKey(hotKey.getHotKeyData());
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
        List<HistoryKey> historyKeys = mModel.getHistoryKeyData();
        if (historyKeys != null) {
            mView.showHistoryKey(mModel.getHistoryKeyData());
        }
    }

    @Override
    public void goToSearch(String key) {
        if (!TextUtils.isEmpty(key)) {
            mModel.addHistoryKeyData(key);
            mView.goToSearch(key);
        } else {
            mView.showToast(R.string.please_enter_keywords);
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
