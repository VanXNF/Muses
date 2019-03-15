package com.victorxu.muses.creation.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.creation.contract.MyFilterContract;
import com.victorxu.muses.creation.model.MyFilterModel;
import com.victorxu.muses.gson.PageFilter;
import com.victorxu.muses.gson.UnfinishedFilterStatus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyFilterPresenter implements MyFilterContract.Presenter {
    private static final String TAG = "MyFilterPresenter";

    private MyFilterContract.View mView;
    private MyFilterContract.Model mModel;

    public MyFilterPresenter(MyFilterContract.View mView, Context context, int type) {
        this.mView = mView;
        mModel = new MyFilterModel(type, context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView(int type) {
        mModel.getFilterData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getFilterData");
                mView.showToast(R.string.network_error_please_try_again);
                if (!mModel.checkDataStatus()) {
                    mView.showEmptyPage();
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String json = response.body().string();
                    switch (type) {
                        case -1:
//                            Log.d(TAG, "unfinished: " + json);
                            UnfinishedFilterStatus status = new Gson().fromJson(json, UnfinishedFilterStatus.class);
                            if (status.getCode().equals("OK")) {
                                mModel.setLocalUnfinishedData(status.getData());
                                mView.showUnfinishedFilter(status.getData());
                            } else {
                                mView.showToast(status.getMessage());
                            }
                            break;
                        case 1:
                            PageFilter pageFilter = new Gson().fromJson(json, PageFilter.class);
                            if (pageFilter.getData() != null && pageFilter.getTotalNum() != 0) {
                                mModel.setAllPages(pageFilter.getPageCount());
                                mModel.setLocalFinishedData(pageFilter.getData());
                                mView.showFinishedFilter(pageFilter.getData());
                            } else {
                                mView.showToast(R.string.data_error_please_try_again);
                            }
                            break;
                    }
                } catch (IOException e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (!mModel.checkDataStatus()) {
                        mView.showEmptyPage();
                    }
                }
            }
        });
    }

    @Override
    public void loadMoreDataToView(int type) {
        mView.showLoadMore();
        if (mModel.checkPageStatus()) {
            mModel.getMoreFilterData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getMoreFilterData");
                    mView.showToast(R.string.network_error_please_try_again);
                    mView.hideLoadMore(false, false);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        switch (type) {
//                            case -1:
//                                UnfinishedFilterStatus status = new Gson().fromJson(response.body().string(), UnfinishedFilterStatus.class);
//                                if (status.getStatus().equals("OK")) {
//                                    mView.showUnfinishedFilter(status.getData());
//                                } else {
//                                    mView.showToast(status.getMessage());
//                                }
//                                break;
                            case 1:
                                PageFilter pageFilter = new Gson().fromJson(response.body().string(), PageFilter.class);
                                if (pageFilter.getData() != null && pageFilter.getTotalNum() != 0) {
                                    mModel.setAllPages(pageFilter.getPageCount());
                                    mModel.addLocalFinishedData(pageFilter.getData());
                                    mView.showMoreFinishedFilter(pageFilter.getData());
                                    mView.hideLoadMore(true, false);
                                } else {
                                    mView.showToast(R.string.data_error_please_try_again);
                                    mView.hideLoadMore(false, false);
                                }
                                break;
                        }
                    } catch (IOException e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    } finally {
                        if (!mModel.checkDataStatus()) {
                            mView.showEmptyPage();
                        }
                    }
                }
            });
        } else {
            mView.hideLoadMore(false, true);
        }
    }
}
