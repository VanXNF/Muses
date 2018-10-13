package com.victorxu.muses.gallery.view.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ProductItem implements MultiItemEntity {

    public static final int TITLE = 2;
    public static final int CONTENT = 1;
    private int mItemType;
    private String mImageUri;
    private Integer mImageResId;
    private String mTitle;
    private String mTag;
    private String mPrice;

    public ProductItem(int mItemType, String mImageUri, String mTitle, String mTag, String mPrice) {
        this.mItemType = mItemType;
        this.mImageUri = mImageUri;
        this.mTitle = mTitle;
        this.mTag = mTag;
        this.mPrice = mPrice;
    }

    public ProductItem(int mItemType, Integer mImageResId, String mTitle, String mTag, String mPrice) {
        this.mItemType = mItemType;
        this.mImageResId = mImageResId;
        this.mTitle = mTitle;
        this.mTag = mTag;
        this.mPrice = mPrice;
    }

    @Override
    public int getItemType() {
        return mItemType;
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

    public String getmPrice() {        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public Integer getmImageResId() {
        return mImageResId;
    }

    public void setmImageResId(Integer mImageResId) {
        this.mImageResId = mImageResId;
    }
}
