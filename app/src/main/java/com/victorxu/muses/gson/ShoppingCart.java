package com.victorxu.muses.gson;

import java.util.List;

public class ShoppingCart {
    /**
     * code : OK
     * message :
     * data : [{"id":155,"number":10,"userId":56,"commodity":{"id":15,"name":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","discountPrice":478,"brief":"玫瑰花开 喜气自来 情投意合 举案齐眉","coverImage":"https://img.alicdn.com/imgextra/i4/2114401509/TB10PFMm4HI8KJjy1zbXXaxdpXa_!!0-item_pic.jpg"},"commodityId":15,"detail":"尺寸:90*150;颜色分类:B款白色PU;","parameterId":1},{"id":252,"number":5,"userId":56,"commodity":{"id":9,"name":"新中式现代餐厅静物装饰画 卧室床头壁画沙发背景墙画 饭厅挂画","discountPrice":268,"brief":"中式经典平平安安尽显品味手工装裱简易安装","coverImage":"https://img.alicdn.com/imgextra/i2/2097690972/O1CN01QdwbQY1J3DScMfmHW_!!0-item_pic.jpg"},"commodityId":9,"detail":"尺寸:50*50;颜色分类:C款 HPF14029-3;","parameterId":1}]
     */

    private String code;
    private String message;
    private List<CartItemBean> data;

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

    public List<CartItemBean> getData() {
        return data;
    }

    public void setData(List<CartItemBean> data) {
        this.data = data;
    }

    public static class CartItemBean {
        /**
         * id : 155
         * number : 10
         * userId : 56
         * commodity : {"id":15,"name":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","discountPrice":478,"brief":"玫瑰花开 喜气自来 情投意合 举案齐眉","coverImage":"https://img.alicdn.com/imgextra/i4/2114401509/TB10PFMm4HI8KJjy1zbXXaxdpXa_!!0-item_pic.jpg"}
         * commodityId : 15
         * detail : 尺寸:90*150;颜色分类:B款白色PU;
         * parameterId : 1
         */

        private int id;
        private int number;
        private int userId;
        private CommodityBean commodity;
        private int commodityId;
        private String detail;
        private int parameterId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public CommodityBean getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityBean commodity) {
            this.commodity = commodity;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getParameterId() {
            return parameterId;
        }

        public void setParameterId(int parameterId) {
            this.parameterId = parameterId;
        }

        public static class CommodityBean {
            /**
             * id : 15
             * name : 纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸
             * discountPrice : 478
             * brief : 玫瑰花开 喜气自来 情投意合 举案齐眉
             * coverImage : https://img.alicdn.com/imgextra/i4/2114401509/TB10PFMm4HI8KJjy1zbXXaxdpXa_!!0-item_pic.jpg
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
}
