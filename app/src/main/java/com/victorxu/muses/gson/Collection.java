package com.victorxu.muses.gson;

import java.util.List;

public class Collection {

    /**
     * code : OK
     * message :
     * data : [{"src":"https://img.alicdn.com/imgextra/i2/2097690972/O1CN01QdwbQY1J3DScMfmHW_!!0-item_pic.jpg","title":"新中式现代餐厅静物装饰画 卧室床头壁画沙发背景墙画 饭厅挂画","content":"中式经典平平安安尽显品味手工装裱简易安装","price":268,"collectPrice":338,"message":"降价通知","userId":122,"commodityId":9},{"src":"https://img.alicdn.com/imgextra/i3/1880939503/TB1aiVCrCYTBKNjSZKbXXXJ8pXa_!!0-item_pic.jpg","title":"新中式实木电表箱装饰画免打孔遮挡配电箱电闸盒弱电箱可推拉挂画","content":"实木画框","price":478,"collectPrice":338,"message":null,"userId":122,"commodityId":16}]
     */

    private String code;
    private String message;
    private List<CollectionBean> data;

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

    public List<CollectionBean> getData() {
        return data;
    }

    public void setData(List<CollectionBean> data) {
        this.data = data;
    }

    public static class CollectionBean {
        /**
         * src : https://img.alicdn.com/imgextra/i2/2097690972/O1CN01QdwbQY1J3DScMfmHW_!!0-item_pic.jpg
         * title : 新中式现代餐厅静物装饰画 卧室床头壁画沙发背景墙画 饭厅挂画
         * content : 中式经典平平安安尽显品味手工装裱简易安装
         * price : 268
         * collectPrice : 338
         * message : 降价通知
         * userId : 122
         * commodityId : 9
         */

        private String src;
        private String title;
        private String content;
        private int price;
        private int collectPrice;
        private String message;
        private int userId;
        private int commodityId;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCollectPrice() {
            return collectPrice;
        }

        public void setCollectPrice(int collectPrice) {
            this.collectPrice = collectPrice;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }
    }
}
