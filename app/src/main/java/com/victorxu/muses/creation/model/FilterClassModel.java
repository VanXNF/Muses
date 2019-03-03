package com.victorxu.muses.creation.model;

import com.victorxu.muses.creation.contract.FilterClassContract;
import com.victorxu.muses.util.HttpUtil;

import okhttp3.Callback;

public class FilterClassModel implements FilterClassContract.Model {

    private final String FILTER_API = "api/filter/";

    private String key;
    private int id;

    private int currentPage = 1;
    private int allPages = 0;

    public FilterClassModel(String key, int id) {
        this.key = key;
        this.id = id;
    }

    @Override
    public void getFilterData(Callback callback) {
        allPages = 0;
        getFilterData(1, callback);
    }

    @Override
    public void getFilterData(int page, Callback callback) {
        currentPage = page;
        HttpUtil.getRequest(FILTER_API + key + "/" + String.valueOf(id) + "/"
                + String.valueOf(currentPage), callback);
    }

    @Override
    public void getMoreFilterData(Callback callback) {
        getFilterData(currentPage + 1, callback);
    }

    @Override
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getAllPages() {
        return allPages;
    }
}
