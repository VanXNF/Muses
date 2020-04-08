package com.victorxu.muses.creation.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.creation.contract.FilterClassContract;
import com.victorxu.muses.creation.model.FilterClassModel;
import com.victorxu.muses.gson.PageFilter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class FilterClassPresenter implements FilterClassContract.Presenter {

    private static final String TAG = "FilterClassPresenter";

    private FilterClassContract.View mView;
    private FilterClassContract.Model mModel;

    public FilterClassPresenter(FilterClassContract.View mView, String key, int id, Context context) {
        this.mView = mView;
        mModel = new FilterClassModel(key, id, context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mModel.getFilterData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getFilterData");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showEmptyPage();
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    PageFilter pageFilter = new Gson().fromJson(response.body().string(), PageFilter.class);
                    if (pageFilter.getData() != null && pageFilter.getTotalNum() != 0) {
                        mModel.setAllPages(pageFilter.getPageCount());
                        mView.hideEmptyPage();
                        mView.showFilter(pageFilter.getData());
                    } else {
                        mView.showEmptyPage();
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadMoreDataToView() {
        mView.showLoadMore();
        if (mModel.getAllPages() != 0 && mModel.getCurrentPage() < mModel.getAllPages()) {
            mModel.getMoreFilterData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getMoreFilterData");
                    if (!e.getMessage().equals("Socket closed")) {
                        mView.hideLoadMore(false, false);
                        mView.showToast(R.string.network_error_please_try_again);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        PageFilter pageFilter = new Gson().fromJson(response.body().string(), PageFilter.class);
                        if (pageFilter.getData() != null && pageFilter.getTotalNum() != 0) {
                            mModel.setAllPages(pageFilter.getPageCount());
                            mView.hideLoadMore(true, false);
                            mView.showMoreFilter(pageFilter.getData());
                        }
                    } catch (Exception e) {
                        mView.hideLoadMore(false, false);
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }
                }
            });
        } else {
            mView.hideLoadMore(false, true);
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
