package com.victorxu.muses.trade.view.entity;

import java.io.Serializable;

public class ProductSettleOrderBean implements Serializable {

    private int commodityId;
    private String image;
    private String title;
    private String detail;
    private String parameter;
    private String price;
    private String number;

    public ProductSettleOrderBean() {}

    public ProductSettleOrderBean(int commodityId, String image, String title, String detail, String parameter, String price, String number) {
        this.commodityId = commodityId;
        this.image = image;
        this.title = title;
        this.detail = detail;
        this.parameter = parameter;
        this.price = price;
        this.number = number;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
