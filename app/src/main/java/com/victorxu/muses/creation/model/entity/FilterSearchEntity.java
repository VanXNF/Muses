package com.victorxu.muses.creation.model.entity;

public class FilterSearchEntity {
    int size;     // 每页数量
    int sortType; // 排序方法（暂时没用）
    boolean asc;      // 正序倒序（暂时没用）
    String keyword;   // 搜索关键词
    int page;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
