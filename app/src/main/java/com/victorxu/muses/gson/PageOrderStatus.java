package com.victorxu.muses.gson;

import java.util.List;

public class PageOrderStatus {
    /**
     * code : OK
     * message :
     * data : {"currentPage":1,"pageSize":5,"pageCount":3,"totalNum":11,"dataList":[{"id":1173,"orderSN":"dd0c85c4ad424467bb42fbe77fdf1572","tradeNo":null,"status":0,"postScript":null,"orderAmount":726,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;达尔文;12345678910","orderCommodityModels":[{"id":1177,"number":1,"orderId":1173,"commodityId":3,"price":248,"title":"北欧壁画三联组合沙发背景墙客厅装饰画现代简约餐厅简欧挂画油画","brief":null,"image":"https://img.alicdn.com/imgextra/i2/2068607660/TB2lQbnbcUrBKNjSZPxXXX00pXa_!!2068607660.jpg"},{"id":1178,"number":1,"orderId":1173,"commodityId":15,"price":478,"title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","brief":null,"image":"https://img.alicdn.com/imgextra/i3/2114401509/TB2r2RedQfb_uJkSmRyXXbWxVXa_!!2114401509.jpg"}]},{"id":1176,"orderSN":"5e2ebf704f6a4a738af77efc5a1854f8","tradeNo":null,"status":0,"postScript":null,"orderAmount":448,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区东和公寓;维克多;12345678910","orderCommodityModels":[{"id":1180,"number":1,"orderId":1176,"commodityId":2,"price":448,"title":"客厅装饰画北欧风格餐厅墙面装饰壁画现代简约大气沙发背景墙挂画","brief":null,"image":"https://img.alicdn.com/imgextra/TB1wSFazeGSBuNjSspbL6UiipXa"}]},{"id":1182,"orderSN":"2e42a26e60b24a989a61a9e352951683","tradeNo":null,"status":0,"postScript":null,"orderAmount":268,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;西西里的吉皮;12345678910","orderCommodityModels":[{"id":1185,"number":1,"orderId":1182,"commodityId":11,"price":268,"title":"九鱼图风水招财玄关装饰画竖版过道走廊挂画新中式墙壁画客厅国画","brief":null,"image":"https://img.alicdn.com/imgextra/i4/1880939503/TB2LnkojfNNTKJjSspcXXb4KVXa_!!1880939503.jpg"}]},{"id":1189,"orderSN":"209496c62bc147dea8df910d421e6149","tradeNo":null,"status":0,"postScript":null,"orderAmount":478,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;西西里的吉皮;12345678910","orderCommodityModels":[{"id":1193,"number":1,"orderId":1189,"commodityId":15,"price":478,"title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","brief":null,"image":"https://img.alicdn.com/imgextra/i2/2114401509/TB2JLOtepTM8KJjSZFlXXaO8FXa_!!2114401509.jpg"}]},{"id":1190,"orderSN":"e8d8385cc0e34f1790cef21a9f64a14a","tradeNo":null,"status":0,"postScript":null,"orderAmount":478,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;西西里的吉皮;12345678910","orderCommodityModels":[{"id":1194,"number":1,"orderId":1190,"commodityId":15,"price":478,"title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","brief":null,"image":"https://img.alicdn.com/imgextra/i2/2114401509/TB2JLOtepTM8KJjSZFlXXaO8FXa_!!2114401509.jpg"}]}]}
     */

    private String code;
    private String message;
    private PageOrder data;

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

    public PageOrder getData() {
        return data;
    }

    public void setData(PageOrder data) {
        this.data = data;
    }

    public static class PageOrder {
        /**
         * currentPage : 1
         * pageSize : 5
         * pageCount : 3
         * totalNum : 11
         * dataList : [{"id":1173,"orderSN":"dd0c85c4ad424467bb42fbe77fdf1572","tradeNo":null,"status":0,"postScript":null,"orderAmount":726,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;达尔文;12345678910","orderCommodityModels":[{"id":1177,"number":1,"orderId":1173,"commodityId":3,"price":248,"title":"北欧壁画三联组合沙发背景墙客厅装饰画现代简约餐厅简欧挂画油画","brief":null,"image":"https://img.alicdn.com/imgextra/i2/2068607660/TB2lQbnbcUrBKNjSZPxXXX00pXa_!!2068607660.jpg"},{"id":1178,"number":1,"orderId":1173,"commodityId":15,"price":478,"title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","brief":null,"image":"https://img.alicdn.com/imgextra/i3/2114401509/TB2r2RedQfb_uJkSmRyXXbWxVXa_!!2114401509.jpg"}]},{"id":1176,"orderSN":"5e2ebf704f6a4a738af77efc5a1854f8","tradeNo":null,"status":0,"postScript":null,"orderAmount":448,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区东和公寓;维克多;12345678910","orderCommodityModels":[{"id":1180,"number":1,"orderId":1176,"commodityId":2,"price":448,"title":"客厅装饰画北欧风格餐厅墙面装饰壁画现代简约大气沙发背景墙挂画","brief":null,"image":"https://img.alicdn.com/imgextra/TB1wSFazeGSBuNjSspbL6UiipXa"}]},{"id":1182,"orderSN":"2e42a26e60b24a989a61a9e352951683","tradeNo":null,"status":0,"postScript":null,"orderAmount":268,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;西西里的吉皮;12345678910","orderCommodityModels":[{"id":1185,"number":1,"orderId":1182,"commodityId":11,"price":268,"title":"九鱼图风水招财玄关装饰画竖版过道走廊挂画新中式墙壁画客厅国画","brief":null,"image":"https://img.alicdn.com/imgextra/i4/1880939503/TB2LnkojfNNTKJjSspcXXb4KVXa_!!1880939503.jpg"}]},{"id":1189,"orderSN":"209496c62bc147dea8df910d421e6149","tradeNo":null,"status":0,"postScript":null,"orderAmount":478,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;西西里的吉皮;12345678910","orderCommodityModels":[{"id":1193,"number":1,"orderId":1189,"commodityId":15,"price":478,"title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","brief":null,"image":"https://img.alicdn.com/imgextra/i2/2114401509/TB2JLOtepTM8KJjSZFlXXaO8FXa_!!2114401509.jpg"}]},{"id":1190,"orderSN":"e8d8385cc0e34f1790cef21a9f64a14a","tradeNo":null,"status":0,"postScript":null,"orderAmount":478,"payTime":null,"userId":122,"address":"浙江省杭州市西湖区浙江科技学院西和公寓小破地方;西西里的吉皮;12345678910","orderCommodityModels":[{"id":1194,"number":1,"orderId":1190,"commodityId":15,"price":478,"title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","brief":null,"image":"https://img.alicdn.com/imgextra/i2/2114401509/TB2JLOtepTM8KJjSZFlXXaO8FXa_!!2114401509.jpg"}]}]
         */

        private int currentPage;
        private int pageSize;
        private int pageCount;
        private int totalNum;
        private List<OrderBean> dataList;

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

        public List<OrderBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<OrderBean> dataList) {
            this.dataList = dataList;
        }

        public static class OrderBean {
            /**
             * id : 1173
             * orderSN : dd0c85c4ad424467bb42fbe77fdf1572
             * tradeNo : null
             * status : 0
             * postScript : null
             * orderAmount : 726
             * payTime : null
             * userId : 122
             * address : 浙江省杭州市西湖区浙江科技学院西和公寓小破地方;达尔文;12345678910
             * orderCommodityModels : [{"id":1177,"number":1,"orderId":1173,"commodityId":3,"price":248,"title":"北欧壁画三联组合沙发背景墙客厅装饰画现代简约餐厅简欧挂画油画","brief":null,"image":"https://img.alicdn.com/imgextra/i2/2068607660/TB2lQbnbcUrBKNjSZPxXXX00pXa_!!2068607660.jpg"},{"id":1178,"number":1,"orderId":1173,"commodityId":15,"price":478,"title":"纯手绘油画玫瑰花餐厅玄关装饰画书房客厅壁画手工定制挂画大尺寸","brief":null,"image":"https://img.alicdn.com/imgextra/i3/2114401509/TB2r2RedQfb_uJkSmRyXXbWxVXa_!!2114401509.jpg"}]
             */

            private int id;
            private String orderSN;
            private Object tradeNo;
            private int status;
            private Object postScript;
            private int orderAmount;
            private Object payTime;
            private int userId;
            private String address;
            private List<OrderCommodityModelsBean> orderCommodityModels;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderSN() {
                return orderSN;
            }

            public void setOrderSN(String orderSN) {
                this.orderSN = orderSN;
            }

            public Object getTradeNo() {
                return tradeNo;
            }

            public void setTradeNo(Object tradeNo) {
                this.tradeNo = tradeNo;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getPostScript() {
                return postScript;
            }

            public void setPostScript(Object postScript) {
                this.postScript = postScript;
            }

            public int getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(int orderAmount) {
                this.orderAmount = orderAmount;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public List<OrderCommodityModelsBean> getOrderCommodityModels() {
                return orderCommodityModels;
            }

            public void setOrderCommodityModels(List<OrderCommodityModelsBean> orderCommodityModels) {
                this.orderCommodityModels = orderCommodityModels;
            }

            public static class OrderCommodityModelsBean {
                /**
                 * id : 1177
                 * number : 1
                 * orderId : 1173
                 * commodityId : 3
                 * price : 248
                 * title : 北欧壁画三联组合沙发背景墙客厅装饰画现代简约餐厅简欧挂画油画
                 * brief : null
                 * image : https://img.alicdn.com/imgextra/i2/2068607660/TB2lQbnbcUrBKNjSZPxXXX00pXa_!!2068607660.jpg
                 */

                private int id;
                private int number;
                private int orderId;
                private int commodityId;
                private int price;
                private String title;
                private String detail;
                private String image;

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

                public int getOrderId() {
                    return orderId;
                }

                public void setOrderId(int orderId) {
                    this.orderId = orderId;
                }

                public int getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(int commodityId) {
                    this.commodityId = commodityId;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
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

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }
            }
        }
    }
}
