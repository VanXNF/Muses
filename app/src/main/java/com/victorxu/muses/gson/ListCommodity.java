package com.victorxu.muses.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListCommodity {

    /**
     * code : OK
     * message :
     * data : [{"id":11,"name":"九鱼图风水招财玄关装饰画竖版过道走廊挂画新中式墙壁画客厅国画","discountPrice":268,"brief":"实木框装裱 九鱼临门 招财纳福","coverImage":"https://img.alicdn.com/imgextra/i1/TB1yv_vSpXXXXXIXXXXXXXXXXXX_!!0-item_pic.jpg"},{"id":3,"name":"北欧壁画三联组合沙发背景墙客厅装饰画现代简约餐厅简欧挂画油画","discountPrice":248,"brief":"高清画芯、色泽莹润、极简安装、精工制造","coverImage":"https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i1/2068607660/O1CN01ZOn2rj26SKLr3NSNK_!!2068607660.jpg"},{"id":6,"name":"墙蛙新中式餐厅装饰画客厅沙发背景墙壁画现代简约三联画饭厅挂画","discountPrice":478,"brief":"墙蛙签约艺术家鸟川芥作品","coverImage":"https://img.alicdn.com/imgextra/i2/752144854/O1CN01Xvj8Zu1ljAzgMYBhA_!!0-item_pic.jpg"}]
     */

    private String code;
    private String message;
    @SerializedName("data")
    private List<CommodityListModel> commodityList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CommodityListModel> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListModel> commodityList) {
        this.commodityList = commodityList;
    }

    public static class CommodityListModel {
        /**
         * id : 11
         * name : 九鱼图风水招财玄关装饰画竖版过道走廊挂画新中式墙壁画客厅国画
         * discountPrice : 268
         * brief : 实木框装裱 九鱼临门 招财纳福
         * coverImage : https://img.alicdn.com/imgextra/i1/TB1yv_vSpXXXXXIXXXXXXXXXXXX_!!0-item_pic.jpg
         */

        private int id;
        private String name;
        private int discountPrice;
        private String brief;
        private String coverImage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(int discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }
    }
}
