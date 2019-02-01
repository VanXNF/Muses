package com.victorxu.muses.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageCommodity {

    /**
     * code : OK
     * message :
     * data : {"currentPage":1,"pageSize":6,"pageCount":4,"totalNum":20,"dataList":[{"id":18,"name":"慕斯维 纯手绘新中式客厅油画抽象山水风景油画装饰画 春华泛舟图","discountPrice":218,"brief":"抽象山水风景油画装饰画","coverImage":"https://img.alicdn.com/imgextra/i3/2452564014/TB1iGVASVXXXXXXXFXXXXXXXXXX_!!0-item_pic.jpg"},{"id":10,"name":"新中式玄关装饰画国画竖版过道走廊挂画风水工笔画 张大千金荷花","discountPrice":228,"brief":"富丽无匠气 寓意极美好 中西皆可搭","coverImage":"https://img.alicdn.com/imgextra/i1/1665565454/O1CN01pPlqlV1q9ycMjyE6d_!!0-item_pic.jpg"},{"id":13,"name":"梵高世界名画欧式装饰画油画客厅美式卧室丰收餐厅现代简约壁挂画","discountPrice":238,"brief":"梵高名画 午睡 丰收 收割者 高清复刻","coverImage":"https://img.alicdn.com/imgextra/i4/2097690972/O1CN01XXzhqp1J3DSc9Bc8u_!!0-item_pic.jpg"},{"id":3,"name":"北欧壁画三联组合沙发背景墙客厅装饰画现代简约餐厅简欧挂画油画","discountPrice":248,"brief":"高清画芯、色泽莹润、极简安装、精工制造","coverImage":"https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i1/2068607660/O1CN01ZOn2rj26SKLr3NSNK_!!2068607660.jpg"},{"id":19,"name":"新中式客厅装饰画风水招财田园风沙发背景餐厅玄关中国风三联墙画","discountPrice":248,"brief":"水晶透亮画面 易打理 易安装","coverImage":"https://img.alicdn.com/imgextra/i2/1051497370/O1CN01LicaIi24JVTuX9INZ_!!0-item_pic.jpg"},{"id":4,"name":"新中式玄关装饰画国画竖版过道走廊挂画风水工笔画 张大千金荷花","discountPrice":258,"brief":"富丽无匠气 寓意极美好 中西皆可搭","coverImage":"https://img.alicdn.com/imgextra/i1/1665565454/O1CN01pPlqlV1q9ycMjyE6d_!!0-item_pic.jpg"}]}
     */

    private String code;
    private String message;
    @SerializedName("data")
    private PageBean pageData;

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

    public PageBean getPageData() {
        return pageData;
    }

    public void setPageData(PageBean pageData) {
        this.pageData = pageData;
    }

    public static class PageBean {
        /**
         * currentPage : 1
         * pageSize : 6
         * pageCount : 4
         * totalNum : 20
         * dataList : [{"id":18,"name":"慕斯维 纯手绘新中式客厅油画抽象山水风景油画装饰画 春华泛舟图","discountPrice":218,"brief":"抽象山水风景油画装饰画","coverImage":"https://img.alicdn.com/imgextra/i3/2452564014/TB1iGVASVXXXXXXXFXXXXXXXXXX_!!0-item_pic.jpg"},{"id":10,"name":"新中式玄关装饰画国画竖版过道走廊挂画风水工笔画 张大千金荷花","discountPrice":228,"brief":"富丽无匠气 寓意极美好 中西皆可搭","coverImage":"https://img.alicdn.com/imgextra/i1/1665565454/O1CN01pPlqlV1q9ycMjyE6d_!!0-item_pic.jpg"},{"id":13,"name":"梵高世界名画欧式装饰画油画客厅美式卧室丰收餐厅现代简约壁挂画","discountPrice":238,"brief":"梵高名画 午睡 丰收 收割者 高清复刻","coverImage":"https://img.alicdn.com/imgextra/i4/2097690972/O1CN01XXzhqp1J3DSc9Bc8u_!!0-item_pic.jpg"},{"id":3,"name":"北欧壁画三联组合沙发背景墙客厅装饰画现代简约餐厅简欧挂画油画","discountPrice":248,"brief":"高清画芯、色泽莹润、极简安装、精工制造","coverImage":"https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i1/2068607660/O1CN01ZOn2rj26SKLr3NSNK_!!2068607660.jpg"},{"id":19,"name":"新中式客厅装饰画风水招财田园风沙发背景餐厅玄关中国风三联墙画","discountPrice":248,"brief":"水晶透亮画面 易打理 易安装","coverImage":"https://img.alicdn.com/imgextra/i2/1051497370/O1CN01LicaIi24JVTuX9INZ_!!0-item_pic.jpg"},{"id":4,"name":"新中式玄关装饰画国画竖版过道走廊挂画风水工笔画 张大千金荷花","discountPrice":258,"brief":"富丽无匠气 寓意极美好 中西皆可搭","coverImage":"https://img.alicdn.com/imgextra/i1/1665565454/O1CN01pPlqlV1q9ycMjyE6d_!!0-item_pic.jpg"}]
         */

        private int currentPage;
        private int pageSize;
        private int pageCount;
        private int totalNum;
        @SerializedName("dataList")
        private List<CommodityListModel> commodityList;

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

        public List<CommodityListModel> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListModel> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListModel {
            /**
             * id : 18
             * name : 慕斯维 纯手绘新中式客厅油画抽象山水风景油画装饰画 春华泛舟图
             * discountPrice : 218
             * brief : 抽象山水风景油画装饰画
             * coverImage : https://img.alicdn.com/imgextra/i3/2452564014/TB1iGVASVXXXXXXXFXXXXXXXXXX_!!0-item_pic.jpg
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
