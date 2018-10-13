package com.victorxu.muses.gallery.view.entity;

public class ImageItem {

    private int mImageId;
    private Integer mImageResId;
    private String mImageUri;

    public ImageItem(int mImageId, String mImageUri) {
        this.mImageId = mImageId;
        this.mImageUri = mImageUri;
    }

    public ImageItem(int mImageId, Integer mImageResId) {
        this.mImageId = mImageId;
        this.mImageResId = mImageResId;
    }

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }

    public Integer getmImageResId() {
        return mImageResId;
    }

    public void setmImageResId(Integer mImageResId) {
        this.mImageResId = mImageResId;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }
}
