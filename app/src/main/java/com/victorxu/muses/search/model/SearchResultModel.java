package com.victorxu.muses.search.model;

import com.google.gson.Gson;
import com.victorxu.muses.gson.PageCommodity;
import com.victorxu.muses.gson.SearchModel;
import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.search.view.entity.ProductItem;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Callback;

public class SearchResultModel implements SearchResultContract.Model {

    private final String API_PREFIX = "api/commodity/page/";
    private int currentPage = 1;
    private int allPages = 0;
    private int index;
    private String keyword;
    private SearchModel searchModel;
    private List<PageCommodity> pages = new ArrayList<>();

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getAllPages() {
        try {
            return pages.get(currentPage).getPageData().getPageCount();
        } catch (IndexOutOfBoundsException e) {
            return allPages;
        }
    }

    @Override
    public List<PageCommodity> getPageList() {
        return pages;
    }

    @Override
    public void getProductData(Callback callback) {
        getProductData(1, callback);
    }

    @Override
    public void getProductData(int page, Callback callback) {
        currentPage = page;
        initSearchModel();
        HttpUtil.postRequest(API_PREFIX + String.valueOf(page), new Gson().toJson(searchModel), callback);
    }

    @Override
    public void getMoreProductData(Callback callback) {
        if (searchModel == null) {
            initSearchModel();
        }
        HttpUtil.postRequest(API_PREFIX + String.valueOf(++currentPage), new Gson().toJson(searchModel), callback);
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

    private void initSearchModel() {
        searchModel = new SearchModel();
        switch (index) {
            case 0:
                searchModel.setSortType(SearchModel.SEARCH_DEFAULT);
                searchModel.setAsc(true);
                break;
            case 1:
                searchModel.setSortType(SearchModel.SEARCH_TIME);
                searchModel.setAsc(false);
                break;
            case 2:
                searchModel.setSortType(SearchModel.SEARCH_VOLUME);
                searchModel.setAsc(false);
                break;
            case 3:
                searchModel.setSortType(SearchModel.SEARCH_PRICE);
                searchModel.setAsc(true);
                break;
        }
        searchModel.setSize(6);
        searchModel.setKeyword(keyword);
    }
}
