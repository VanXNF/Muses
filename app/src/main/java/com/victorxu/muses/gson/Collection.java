package com.victorxu.muses.gson;

import java.util.List;

public class Collection {

    /**
     * code : OK
     * message :
     * data : [{"id":1,"src":"https://img.alicdn.com/imgextra/i3/1880939503/TB1aiVCrCYTBKNjSZKbXXXJ8pXa_!!0-item_pic.jpg","title":"新中式实木电表箱装饰画免打孔遮挡配电箱电闸盒弱电箱可推拉挂画","content":"实木画框","price":478,"collectPrice":338,"message":"降价通知","userId":122,"commodityId":16},{"id":2,"src":"https://img.alicdn.com/imgextra/i2/2097690972/O1CN01QdwbQY1J3DScMfmHW_!!0-item_pic.jpg","title":"新中式现代餐厅静物装饰画 卧室床头壁画沙发背景墙画 饭厅挂画","content":"中式经典平平安安尽显品味手工装裱简易安装","price":268,"collectPrice":338,"message":"降价通知","userId":null,"commodityId":9},{"id":3,"src":"https://img.alicdn.com/imgextra/i1/1665565454/O1CN01pPlqlV1q9ycMjyE6d_!!0-item_pic.jpg","title":"新中式玄关装饰画国画竖版过道走廊挂画风水工笔画 张大千金荷花","content":"富丽无匠气 寓意极美好 中西皆可搭","price":228,"collectPrice":338,"message":"降价通知","userId":null,"commodityId":10},{"id":4,"src":"https://img.alicdn.com/imgextra/i1/TB1yv_vSpXXXXXIXXXXXXXXXXXX_!!0-item_pic.jpg","title":"九鱼图风水招财玄关装饰画竖版过道走廊挂画新中式墙壁画客厅国画","content":"实木框装裱 九鱼临门 招财纳福","price":268,"collectPrice":338,"message":"降价通知","userId":null,"commodityId":11},{"id":6,"src":"https://img.alicdn.com/imgextra/i4/2114401509/TB10PFMm4HI8KJjy1zbXXaxdpXa_!!0-item_pic.jpg","title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","content":"玫瑰花开 喜气自来 情投意合 举案齐眉","price":478,"collectPrice":338,"message":null,"userId":null,"commodityId":15}]
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
         * id : 1
         * src : https://img.alicdn.com/imgextra/i3/1880939503/TB1aiVCrCYTBKNjSZKbXXXJ8pXa_!!0-item_pic.jpg
         * title : 新中式实木电表箱装饰画免打孔遮挡配电箱电闸盒弱电箱可推拉挂画
         * content : 实木画框
         * price : 478
         * collectPrice : 338
         * message : 降价通知
         * userId : 122
         * commodityId : 16
         */

        private int id;
        private String src;
        private String title;
        private String content;
        private int price;
        private int collectPrice;
        private String message;
        private int userId;
        private int commodityId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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
