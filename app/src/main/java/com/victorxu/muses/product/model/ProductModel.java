package com.victorxu.muses.product.model;

import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.product.contract.ProductContract;
import com.victorxu.muses.product.view.entity.StyleSelectItem;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<StyleSelectItem> getStyleSelectData(List<Commodity.CommodityDetail.AttributesBean> attributesBeans) {
        List<StyleSelectItem> styleSelectItems = new ArrayList<>();
        for (int i = 0; i < attributesBeans.size(); i++) {
            styleSelectItems.add(new StyleSelectItem(attributesBeans.get(i)));
        }
        styleSelectItems.add(new StyleSelectItem(0));
        return styleSelectItems;
    }
}
