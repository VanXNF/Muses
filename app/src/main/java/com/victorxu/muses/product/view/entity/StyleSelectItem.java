package com.victorxu.muses.product.view.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.victorxu.muses.gson.Commodity;

public class StyleSelectItem implements MultiItemEntity {

    public static final int STYLE = 1;
    public static final int NUM = 2;
    private Commodity.CommodityDetail.AttributesBean attribute;
    private int selectNum;
    private int itemType;

    public StyleSelectItem(Commodity.CommodityDetail.AttributesBean attribute) {
        this.attribute = attribute;
        this.itemType = STYLE;
    }

    public StyleSelectItem(int selectNum) {
        this.selectNum = selectNum;
        this.itemType = NUM;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public Commodity.CommodityDetail.AttributesBean getAttribute() {
        return attribute;
    }

    public void setAttribute(Commodity.CommodityDetail.AttributesBean attribute) {
        this.attribute = attribute;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }
}
