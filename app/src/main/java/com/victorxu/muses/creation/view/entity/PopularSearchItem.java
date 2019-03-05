package com.victorxu.muses.creation.view.entity;

public class PopularSearchItem {

    private int uploadId;
    private String name;
    private String coverImg;

    public PopularSearchItem(int uploadId, String name, String coverImg) {
        this.uploadId = uploadId;
        this.name = name;
        this.coverImg = coverImg;
    }

    public int getId() {
        return uploadId;
    }

    public void setId(int id) {
        this.uploadId = id;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
