package com.victorxu.muses.creation.model.entity;

import com.google.gson.annotations.SerializedName;

public class FilterCreateEntity {
    /**
     * filter_name : filter_name
     * user_id : 1
     * style_template : base64_data
     * brush_size : 768
     * brush_intensity : 1000
     * smooth : 1000
     */

    @SerializedName("filter_name")
    private String filterName;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("style_template")
    private String styleTemplate;
    @SerializedName("brush_size")
    private int brushSize;
    @SerializedName("brush_intensity")
    private int brushIntensity;
    private int smooth;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStyleTemplate() {
        return styleTemplate;
    }

    public void setStyleTemplate(String styleTemplate) {
        this.styleTemplate = styleTemplate;
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
}
