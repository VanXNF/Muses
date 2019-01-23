package com.victorxu.muses.gson;

public class SearchModel {

    private int size;
    private int sortType;
    private boolean asc;
    private String keyword;

    public static final int SEARCH_DEFAULT = 3;
    public static final int SEARCH_TIME = 0;
    public static final int SEARCH_PRICE = 1;
    public static final int SEARCH_VOLUME = 2;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
