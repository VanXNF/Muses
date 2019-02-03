package com.victorxu.muses.product.model;

import com.victorxu.muses.product.contract.ProductContract;
import com.victorxu.muses.util.HttpUtil;

import okhttp3.Callback;

public class ProductModel implements ProductContract.Model {

    private int id;
    private final String COMMODITY_API_PREFIX = "api/commodity/";
    private final String COMMENT_API_PREFIX = "api/comment/";
    private final String COMMENT_API_SUFFIX = "/1/";

    public ProductModel(int id) {
        this.id = id;
    }

    @Override
    public void getProductData(Callback callback) {
        HttpUtil.getRequest(COMMODITY_API_PREFIX + String.valueOf(id), callback);
    }

    @Override
    public void getCommentData(Callback callback) {
        HttpUtil.getRequest(COMMENT_API_PREFIX + String.valueOf(id) + COMMENT_API_SUFFIX, callback);
    }
}
