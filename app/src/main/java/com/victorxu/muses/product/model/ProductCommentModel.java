package com.victorxu.muses.product.model;

import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.contract.ProductCommentContract;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class ProductCommentModel implements ProductCommentContract.Model {

    private final String COMMENT_API_PREFIX = "api/comment/";
    private final String COMMENT_API_SUFFIX = "/";
    private final String NONE = "none";
    private final String GOOD = "good";
    private final String MIDDLE = "middle";
    private final String BAD = "bad";
    private final String WITH_IMAGE = "withImage";

    private int id;
    private String filter = NONE;
    private int currentPage = 1;
    private int allPages = 0;
    private List<PageComment> pages = new ArrayList<>();

    public ProductCommentModel(int id) {
        this.id = id;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getAllPages() {
        return allPages;
    }

    @Override
    public List<PageComment> getPageList() {
        return pages;
    }

    @Override
    public List<String> getTagData() {
        List<String> data = new ArrayList<>();
        data.add("全部");
        data.add("带图");
        data.add("好评");
        data.add("中评");
        data.add("差评");
        return data;
    }

    @Override
    public void getCommentData(Callback callback) {
        getCommentData(1, callback);
    }

    @Override
    public void getCommentData(int page, Callback callback) {
        currentPage = page;
        HttpUtil.getRequest(COMMENT_API_PREFIX + String.valueOf(id)
                + COMMENT_API_SUFFIX + String.valueOf(currentPage) + COMMENT_API_SUFFIX + filter, callback);
    }

    @Override
    public void getMoreCommentData(Callback callback) {
        getCommentData(currentPage + 1, callback);
    }

    @Override
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    @Override
    public void addPage(PageComment page) {
        pages.add(page);
    }

    @Override
    public void setPageList(List<PageComment> data) {
        this.pages = data;
    }

    @Override
    public void setFilter(int filterId) {
        switch (filterId) {
            case 1:
                filter = WITH_IMAGE;
                break;
            case 2:
                filter = GOOD;
                break;
            case 3:
                filter = MIDDLE;
                break;
            case 4:
                filter = BAD;
                break;
            default:
                filter = NONE;
        }
    }
}
