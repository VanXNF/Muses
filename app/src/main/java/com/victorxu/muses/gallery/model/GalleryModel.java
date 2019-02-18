package com.victorxu.muses.gallery.model;

import com.google.gson.Gson;
import com.victorxu.muses.gallery.contract.GalleryContract;

import com.victorxu.muses.search.model.entity.SearchEntity;
import com.victorxu.muses.util.HttpUtil;


import okhttp3.Callback;

public class GalleryModel implements GalleryContract.Model {

    private static final String TAG = "GalleryModel";

    private static final int REQUEST_NUM = 4; //四个模块四次请求
    private boolean[] flags = new boolean[REQUEST_NUM];
    private static final int BANNER_INDEX = 0;
    private static final int RECOMMEND_INDEX = 1;
    private static final int NEW_INDEX = 2;
    private static final int HOT_INDEX = 3;

    public GalleryModel() {
        resetFlags();
    }

    @Override
    public void getBannerData(Callback callback) {
        setFlag(BANNER_INDEX);
        HttpUtil.getRequest("api/banner/", callback);
    }

    @Override
    public void getRecommendData(int count, Callback callback) {
        setFlag(RECOMMEND_INDEX);
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setSize(count);
        searchEntity.setAsc(false);
        searchEntity.setSortType(SearchEntity.SEARCH_DEFAULT);
        HttpUtil.postRequest("api/commodity/list/1", new Gson().toJson(searchEntity), callback);
    }

    @Override
    public void getNewProductData(int count, Callback callback) {
        setFlag(NEW_INDEX);
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setSize(count);
        searchEntity.setAsc(false);
        searchEntity.setSortType(SearchEntity.SEARCH_TIME);
        HttpUtil.postRequest("api/commodity/list/1", new Gson().toJson(searchEntity), callback);
    }

    @Override
    public void getHotProductData(int count, Callback callback) {
        setFlag(HOT_INDEX);
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setSize(count);
        searchEntity.setAsc(false);
        searchEntity.setSortType(SearchEntity.SEARCH_VOLUME);
        HttpUtil.postRequest("api/commodity/list/1", new Gson().toJson(searchEntity), callback);
    }


    private void setFlag(int index) {
        flags[index] = true;
    }

    @Override
    public void resetFlags() {
        for (int i = 0; i < REQUEST_NUM; i++) {
            flags[i] = false;
        }
    }

    @Override
    public boolean checkFlags() {
        int count = 0;
        int i = 0;
        while (i < REQUEST_NUM) {
            if (flags[i]) count++;
            i++;
        }
        return count == REQUEST_NUM;
    }
}
