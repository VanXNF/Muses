package com.victorxu.muses.creation.model;

import android.content.Context;

import com.victorxu.muses.creation.contract.MyFilterContract;
import com.victorxu.muses.gson.PageFilter;
import com.victorxu.muses.gson.UnfinishedFilterStatus;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class MyFilterModel implements MyFilterContract.Model {

    private final String UNFINISHED_FILTER_API = "api/filter/list?user_id=";
    private final String FINISHED_FILTER_API = "api/filter/user/";
    private int type;
    private Context context;
    private int currentPage = 1;
    private int allPages = 0;

    private List<UnfinishedFilterStatus.UnfinishedFilterBean> mUnfinishedData;
    private List<PageFilter.FilterBean> mFinishedData;

    public MyFilterModel(int type, Context context) {
        this.type = type;
        this.context = context;
        if (type == -1) {
            mUnfinishedData = new ArrayList<>();
        } else if (type == 1) {
            mFinishedData = new ArrayList<>();
        }
    }

    @Override
    public void getFilterData(Callback callback) {
        getFilterData(1, callback);
    }

    @Override
    public void getFilterData(int page, Callback callback) {
        currentPage = page;
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        switch (type) {
            case -1:
                HttpUtil.getRequest(HttpUtil.FILTER_TRAIN_SERVER, UNFINISHED_FILTER_API + String.valueOf(userId), callback);
                break;
            case 1:
                HttpUtil.getRequest(FINISHED_FILTER_API + String.valueOf(userId) + "/" + String.valueOf(currentPage), callback);
                break;
        }
    }

    @Override
    public void getMoreFilterData(Callback callback) {
        if (type == 1) {
            getFilterData(currentPage + 1, callback);
        }
    }

    @Override
    public void setLocalUnfinishedData(List<UnfinishedFilterStatus.UnfinishedFilterBean> data) {
        mUnfinishedData.clear();
        mUnfinishedData.addAll(data);
    }

    @Override
    public void setLocalFinishedData(List<PageFilter.FilterBean> data) {
        mFinishedData.clear();
        mFinishedData.addAll(data);
    }

    @Override
    public void addLocalUnfinishedData(List<UnfinishedFilterStatus.UnfinishedFilterBean> data) {
        mUnfinishedData.addAll(data);
    }

    @Override
    public void addLocalFinishedData(List<PageFilter.FilterBean> data) {
        mFinishedData.addAll(data);
    }

    @Override
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    @Override
    public boolean checkDataStatus() {
        switch (type) {
            case -1:
                return mUnfinishedData.size() != 0;
            case 1:
                return mFinishedData.size() != 0;
        }
        return false;
    }

    @Override
    public boolean checkPageStatus() {
        return (allPages != 0 && currentPage < allPages);
    }
}
