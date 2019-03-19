package com.victorxu.muses.search.model;

import com.google.gson.Gson;
import com.victorxu.muses.gson.PageCommodity;
import com.victorxu.muses.search.model.entity.SearchEntity;
import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

public class SearchResultModel implements SearchResultContract.Model {

    private final String API_PREFIX = "api/commodity/page/";
    private int currentPage = 1;
    private int allPages = 0;
    private int index;
    private String keyword;
    private SearchEntity searchEntity;
    private List<PageCommodity> pages = new ArrayList<>();

    private Call mCallGet;
    private Call mCallMore;

    @Override
    public void getProductData(Callback callback) {
        getProductData(1, callback);
    }

    @Override
    public void getProductData(int page, Callback callback) {
        currentPage = page;
        initSearchModel();
        mCallGet = HttpUtil.postRequest(API_PREFIX + String.valueOf(page), new Gson().toJson(searchEntity), callback);
    }

    @Override
    public void getMoreProductData(Callback callback) {
        if (searchEntity == null) {
            initSearchModel();
        }
        mCallMore = HttpUtil.postRequest(API_PREFIX + String.valueOf(++currentPage), new Gson().toJson(searchEntity), callback);
    }

    @Override
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    @Override
    public void addPage(PageCommodity page) {
        pages.add(page);
    }

    @Override
    public void setPageList(List<PageCommodity> data) {
        pages.clear();
        pages = data;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean checkDataStatus() {
        return pages.size() != 0;
    }

    @Override
    public boolean checkPageStatus() {
        return (allPages != 0 && currentPage < allPages);
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallGet);
        cancelCall(mCallMore);
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }

    private void initSearchModel() {
        searchEntity = new SearchEntity();
        switch (index) {
            case 0:
                searchEntity.setSortType(SearchEntity.SEARCH_DEFAULT);
                searchEntity.setAsc(true);
                break;
            case 1:
                searchEntity.setSortType(SearchEntity.SEARCH_TIME);
                searchEntity.setAsc(false);
                break;
            case 2:
                searchEntity.setSortType(SearchEntity.SEARCH_VOLUME);
                searchEntity.setAsc(false);
                break;
            case 3:
                searchEntity.setSortType(SearchEntity.SEARCH_PRICE);
                searchEntity.setAsc(true);
                break;
        }
        searchEntity.setSize(6);
        searchEntity.setKeyword(keyword);
    }
}
