package com.victorxu.muses.creation.view.entity;

public class PopularSearchItem {

    private int id;
    private String name;
    private String coverImg;

    public PopularSearchItem(int id, String name, String coverImg) {
        this.id = id;
        this.name = name;
        this.coverImg = coverImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
