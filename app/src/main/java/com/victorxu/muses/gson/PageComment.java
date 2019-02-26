package com.victorxu.muses.gson;

import java.util.List;

public class PageComment {

    /**
     * code : OK
     * message : 获取评论列表成功
     * data : {"currentPage":5,"pageSize":10,"pageCount":6,"totalNum":59,"dataList":[{"id":598,"head":"https://s1.ax1x.com/2018/06/22/PpBgx0.jpg","username":"xiulancai","date":1546531200000,"praise":null,"content":"很满意，挂起来效果不错","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":599,"head":"https://s1.ax1x.com/2018/06/22/PpsvZT.jpg","username":"wduan","date":1546358400000,"praise":null,"content":"宝贝收到了，画质不错，安装简单，美观大方，很满意的一次购物！","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":600,"head":"https://s1.ax1x.com/2018/06/22/PpyLfH.jpg","username":"dufang","date":1546185600000,"praise":null,"content":"装饰画挺漂亮，很喜欢。","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":601,"head":"https://s1.ax1x.com/2018/06/22/Pp6978.jpg","username":"wxu","date":1545753600000,"praise":null,"content":"装饰画的做工不错。颜色栩栩如生。很好看","commodityInfo":"颜色分类:A3","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":602,"head":"https://s1.ax1x.com/2018/06/22/PpsPDf.jpg","username":"xiana","date":1546185600000,"praise":null,"content":"画收到了，特别喜欢，立体效果真的是太好了，就和真的一样，太喜欢了","commodityInfo":"颜色分类:A3","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":603,"head":"https://s1.ax1x.com/2018/06/22/PpBDaQ.jpg","username":"tangxiulan","date":1546358400000,"praise":null,"content":"我觉得挺好看","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":["http://img.alicdn.com/bao/uploaded/i4/O1CN01DlXSB41wQotEGvWXB_!!0-rate.jpg"],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":604,"head":"https://s1.ax1x.com/2018/06/22/PprxCd.jpg","username":"taomao","date":1545494400000,"praise":null,"content":"快递很给力，宝贝不错","commodityInfo":"颜色分类:A3","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":605,"head":"https://s1.ax1x.com/2018/06/22/Pps5dS.jpg","username":"wei60","date":1545580800000,"praise":null,"content":"质量很好，性价比高，物流很给力，非常满意","commodityInfo":"颜色分类:A2九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":606,"head":"https://s1.ax1x.com/2018/06/22/PpBwqS.jpg","username":"pingyang","date":1546012800000,"praise":null,"content":"很大气，非常喜欢","commodityInfo":"颜色分类:Y1-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":607,"head":"https://s1.ax1x.com/2018/06/22/Ppyv6I.jpg","username":"chaowei","date":1545926400000,"praise":null,"content":"画质很清晰，品质很好，包装很结实，五分好评","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null}]}
     */

    private String code;
    private String message;
    private PageCommentData data;

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

    public PageCommentData getData() {
        return data;
    }

    public void setData(PageCommentData data) {
        this.data = data;
    }

    public static class PageCommentData {
        /**
         * currentPage : 5
         * pageSize : 10
         * pageCount : 6
         * totalNum : 59
         * dataList : [{"id":598,"head":"https://s1.ax1x.com/2018/06/22/PpBgx0.jpg","username":"xiulancai","date":1546531200000,"praise":null,"content":"很满意，挂起来效果不错","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":599,"head":"https://s1.ax1x.com/2018/06/22/PpsvZT.jpg","username":"wduan","date":1546358400000,"praise":null,"content":"宝贝收到了，画质不错，安装简单，美观大方，很满意的一次购物！","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":600,"head":"https://s1.ax1x.com/2018/06/22/PpyLfH.jpg","username":"dufang","date":1546185600000,"praise":null,"content":"装饰画挺漂亮，很喜欢。","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":601,"head":"https://s1.ax1x.com/2018/06/22/Pp6978.jpg","username":"wxu","date":1545753600000,"praise":null,"content":"装饰画的做工不错。颜色栩栩如生。很好看","commodityInfo":"颜色分类:A3","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":602,"head":"https://s1.ax1x.com/2018/06/22/PpsPDf.jpg","username":"xiana","date":1546185600000,"praise":null,"content":"画收到了，特别喜欢，立体效果真的是太好了，就和真的一样，太喜欢了","commodityInfo":"颜色分类:A3","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":603,"head":"https://s1.ax1x.com/2018/06/22/PpBDaQ.jpg","username":"tangxiulan","date":1546358400000,"praise":null,"content":"我觉得挺好看","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":["http://img.alicdn.com/bao/uploaded/i4/O1CN01DlXSB41wQotEGvWXB_!!0-rate.jpg"],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":604,"head":"https://s1.ax1x.com/2018/06/22/PprxCd.jpg","username":"taomao","date":1545494400000,"praise":null,"content":"快递很给力，宝贝不错","commodityInfo":"颜色分类:A3","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":605,"head":"https://s1.ax1x.com/2018/06/22/Pps5dS.jpg","username":"wei60","date":1545580800000,"praise":null,"content":"质量很好，性价比高，物流很给力，非常满意","commodityInfo":"颜色分类:A2九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":606,"head":"https://s1.ax1x.com/2018/06/22/PpBwqS.jpg","username":"pingyang","date":1546012800000,"praise":null,"content":"很大气，非常喜欢","commodityInfo":"颜色分类:Y1-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null},{"id":607,"head":"https://s1.ax1x.com/2018/06/22/Ppyv6I.jpg","username":"chaowei","date":1545926400000,"praise":null,"content":"画质很清晰，品质很好，包装很结实，五分好评","commodityInfo":"颜色分类:A1九鱼聚财-Z","images":[],"star":5,"orderCommodityId":null,"userId":null,"commentId":null}]
         */

        private int currentPage;
        private int pageSize;
        private int pageCount;
        private int totalNum;
        private List<CommentBean> dataList;

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

        public List<CommentBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<CommentBean> dataList) {
            this.dataList = dataList;
        }

        public static class CommentBean {
            /**
             * id : 598
             * head : https://s1.ax1x.com/2018/06/22/PpBgx0.jpg
             * username : xiulancai
             * date : 1546531200000
             * praise : null
             * content : 很满意，挂起来效果不错
             * commodityInfo : 颜色分类:A1九鱼聚财-Z
             * images : []
             * star : 5
             * orderCommodityId : null
             * userId : null
             * commentId : null
             */

            private int id;
            private String head;
            private String username;
            private long date;
            private Object praise;
            private String content;
            private String commodityInfo;
            private int star;
            private Object orderCommodityId;
            private Object userId;
            private Object commentId;
            private List<?> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public Object getPraise() {
                return praise;
            }

            public void setPraise(Object praise) {
                this.praise = praise;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCommodityInfo() {
                return commodityInfo;
            }

            public void setCommodityInfo(String commodityInfo) {
                this.commodityInfo = commodityInfo;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public Object getOrderCommodityId() {
                return orderCommodityId;
            }

            public void setOrderCommodityId(Object orderCommodityId) {
                this.orderCommodityId = orderCommodityId;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public Object getCommentId() {
                return commentId;
            }

            public void setCommentId(Object commentId) {
                this.commentId = commentId;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }
    }
}
