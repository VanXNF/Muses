package com.victorxu.muses.creation.model;

import com.google.gson.Gson;
import com.victorxu.muses.creation.contract.SearchFilterContract;
import com.victorxu.muses.creation.model.entity.FilterSearchEntity;
import com.victorxu.muses.gson.PageFilter;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class SearchFilterModel implements SearchFilterContract.Model {

    private final String FILTER_SEARCH_API = "api/filter/search";

    private String keyword;
    private int currentPage = 1;
    private int allPages = 0;
    private List<PageFilter.FilterBean> data = new ArrayList<>();

    @Override
    public void getFilterData(Callback callback) {
        getFilterData(1, callback);
    }

    @Override
    public void getFilterData(int page, Callback callback) {
        currentPage = page;
        FilterSearchEntity entity = new FilterSearchEntity();
        entity.setKeyword(keyword);
        entity.setPage(currentPage);
        entity.setSize(12);
        HttpUtil.postRequest(FILTER_SEARCH_API, new Gson().toJson(entity), callback);
    }

    @Override
    public void getMoreFilterData(Callback callback) {
        getFilterData(currentPage + 1, callback);
    }

    @Override
    public boolean checkDataStatus() {
        return data.size() != 0;
    }

    @Override
    public boolean checkPageStatus() {
        return (allPages != 0 && currentPage < allPages);
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    @Override
    public void setLocalFilterData(List<PageFilter.FilterBean> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public void addLocalFilterData(List<PageFilter.FilterBean> data) {
        this.data.addAll(data);
    }
}
