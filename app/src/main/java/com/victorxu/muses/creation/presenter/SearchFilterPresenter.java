package com.victorxu.muses.creation.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.victorxu.muses.R;
import com.victorxu.muses.creation.contract.SearchFilterContract;
import com.victorxu.muses.creation.model.SearchFilterModel;
import com.victorxu.muses.gson.PageFilter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class SearchFilterPresenter implements SearchFilterContract.Presenter {

    private static final String TAG = "SearchFilterPresenter";

    private SearchFilterContract.View mView;
    private SearchFilterContract.Model mModel;

    public SearchFilterPresenter(SearchFilterContract.View mView) {
        this.mView = mView;
        mModel = new SearchFilterModel();
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            mModel.setKeyword(keyword);
            mModel.getFilterData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getFilterData");
                    if (!e.getMessage().equals("Socket closed")) {
                        mView.showToast(R.string.network_error_please_try_again);
                        if (!mModel.checkDataStatus()) {
                            mView.showEmptyPage();
                        }
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        PageFilter pageFilter = new Gson().fromJson(response.body().string(), PageFilter.class);
                        if (pageFilter.getData() != null && pageFilter.getTotalNum() != 0) {
                            mModel.setAllPages(pageFilter.getPageCount());
                            mModel.setLocalFilterData(pageFilter.getData());
                            mView.hideEmptyPage();
                            mView.showFilterData(pageFilter.getData());
                        } else {
                            mModel.setAllPages(0);
                            mModel.setLocalFilterData(new ArrayList<>());
                            mView.showFilterData(new ArrayList<>());
                            mView.showEmptyPage();
                        }
                    } catch (IOException e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    } catch (JsonSyntaxException jse) {
                        mView.showToast(R.string.data_error_please_try_again);
                        jse.printStackTrace();
                    }
                }
            });
        } else {
            mView.showToast(R.string.please_input_filter_name_first);
        }
    }

    @Override
    public void loadMoreDataToView() {
        if (mModel.checkPageStatus()) {
            mModel.getMoreFilterData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getFilterData");
                    if (!e.getMessage().equals("Socket closed")) {
                        mView.showToast(R.string.network_error_please_try_again);
                        mView.hideLoadMore(false, false);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        PageFilter pageFilter = new Gson().fromJson(response.body().string(), PageFilter.class);
                        if (pageFilter.getData() != null && pageFilter.getTotalNum() != 0) {
                            mModel.setAllPages(pageFilter.getPageCount());
                            mModel.addLocalFilterData(pageFilter.getData());
                            mView.showMoreFilterData(pageFilter.getData());
                            mView.hideLoadMore(true, false);
                        } else {
                            mView.hideLoadMore(false, false);
                            throw new IOException();
                        }
                    } catch (IOException e) {
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
