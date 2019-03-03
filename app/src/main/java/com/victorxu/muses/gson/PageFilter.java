package com.victorxu.muses.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageFilter {

    /**
     * currentPage : 1
     * pageSize : 10
     * pageCount : 1
     * totalNum : 1
     * dataList : [{"ownerId":1,"uploadId":1,"filterName":"1","coverImage":"1","brushSize":1,"brushIntensity":1,"smooth":1,"publishData":1551281040000,"vipOnly":false}]
     */

    private int currentPage;
    private int pageSize;
    private int pageCount;
    private int totalNum;
    @SerializedName("dataList")
    private List<FilterBean> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<FilterBean> getData() {
        return data;
    }

    public void setData(List<FilterBean> data) {
        this.data = data;
    }

    public static class FilterBean {
        /**
         * ownerId : 1
         * uploadId : 1
         * filterName : 1
         * coverImage : 1
         * brushSize : 1
         * brushIntensity : 1
         * smooth : 1
         * publishData : 1551281040000
         * vipOnly : false
         */

        private int ownerId;
        private int uploadId;
        private String filterName;
        private String coverImage;
        private int brushSize;
        private int brushIntensity;
        private int smooth;
        private long publishData;
        private boolean vipOnly;

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public int getUploadId() {
            return uploadId;
        }

        public void setUploadId(int uploadId) {
            this.uploadId = uploadId;
        }

        public String getFilterName() {
            return filterName;
        }

        public void setFilterName(String filterName) {
            this.filterName = filterName;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public int getBrushSize() {
            return brushSize;
        }

        public void setBrushSize(int brushSize) {
            this.brushSize = brushSize;
        }

        public int getBrushIntensity() {
            return brushIntensity;
        }

        public void setBrushIntensity(int brushIntensity) {
            this.brushIntensity = brushIntensity;
        }

        public int getSmooth() {
            return smooth;
        }

        public void setSmooth(int smooth) {
            this.smooth = smooth;
        }

        public long getPublishData() {
            return publishData;
        }

        public void setPublishData(long publishData) {
            this.publishData = publishData;
        }

        public boolean isVipOnly() {
            return vipOnly;
        }

        public void setVipOnly(boolean vipOnly) {
            this.vipOnly = vipOnly;
        }
    }
}
