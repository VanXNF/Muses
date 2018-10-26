package com.victorxu.muses.search.view.entity;


public class ProductItem {

    private int productId;
    private String mImageUri;
    private String mTitle;
    private String mTag;
    private String mPrice;

    public ProductItem(int productId, String mImageUri, String mTitle, String mTag, String mPrice) {
        this.productId = productId;
        this.mImageUri = mImageUri;
        this.mTitle = mTitle;
        this.mTag = mTag;
        this.mPrice = mPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTag() {
        return mTag;
    }

    public void setmTag(String mTag) {
        this.mTag = mTag;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
