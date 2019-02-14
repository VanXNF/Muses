package com.victorxu.muses.product.model;

import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.contract.ProductCommentContract;

import java.util.List;

import okhttp3.Callback;

public class ProductCommentModel implements ProductCommentContract.Model {

    private int id;

    public ProductCommentModel(int id) {
        this.id = id;
    }

    @Override
    public int getCurrentPage() {
        return 0;
    }

    @Override
    public int getAllPages() {
        return 0;
    }

    @Override
    public List<PageComment> getPageList() {
        return null;
    }

    @Override
    public void getCommentData(Callback callback) {

    }

    @Override
    public void getCommentData(int page, Callback callback) {

    }

    @Override
    public void getMoreCommentData(Callback callback) {

    }

    @Override
    public void setAllPages(int allPages) {

    }

    @Override
    public void addPage(PageComment page) {

    }

    @Override
    public void setPageList(List<PageComment> data) {

    }
}
