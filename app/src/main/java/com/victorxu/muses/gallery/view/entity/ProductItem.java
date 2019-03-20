package com.victorxu.muses.gallery.view.entity;

import com.victorxu.muses.custom.AdvancedImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

public class ProductItem {

    private AppCompatImageView mImageView;
    private AppCompatTextView mTitleText;
    private AppCompatTextView mTagText;
    private AppCompatTextView mPriceText;

    public ProductItem(AppCompatImageView mImageView) {
        this.mImageView = mImageView;
        this.mTitleText = null;
        this.mTagText = null;
        this.mPriceText = null;
    }

    public ProductItem(AppCompatImageView mImageView, AppCompatTextView mTitleText, AppCompatTextView mTagText, AppCompatTextView mPriceText) {
        this.mImageView = mImageView;
        this.mTitleText = mTitleText;
        this.mTagText = mTagText;
        this.mPriceText = mPriceText;
    }

    public AppCompatImageView getImageView() {
        return mImageView;
    }

    public void setImageView(AppCompatImageView mImageView) {
        this.mImageView = mImageView;
    }

    public AppCompatTextView getTitleText() {
        return mTitleText;
    }

    public void setTitleText(AppCompatTextView mTitleText) {
        this.mTitleText = mTitleText;
    }

    public AppCompatTextView getTagText() {
        return mTagText;
    }

    public void setTagText(AppCompatTextView mTagText) {
        this.mTagText = mTagText;
    }

    public AppCompatTextView getPriceText() {
        return mPriceText;
    }

    public void setPriceText(AppCompatTextView mPriceText) {
        this.mPriceText = mPriceText;
    }
}
