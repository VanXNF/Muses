package com.victorxu.muses.gson;

public class CommentCountStatus {
    /**
     * code : OK
     * message : 获取评论信息成功
     * data : {"withImageCount":26,"goodCount":58,"middleCount":1,"badCount":1}
     */

    private String code;
    private String message;
    private CommentCountBean data;

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

    public CommentCountBean getData() {
        return data;
    }

    public void setData(CommentCountBean data) {
        this.data = data;
    }

    public static class CommentCountBean {
        /**
         * withImageCount : 26
         * goodCount : 58
         * middleCount : 1
         * badCount : 1
         */

        private int withImageCount;
        private int goodCount;
        private int middleCount;
        private int badCount;

        public int getWithImageCount() {
            return withImageCount;
        }

        public void setWithImageCount(int withImageCount) {
            this.withImageCount = withImageCount;
        }

        public int getGoodCount() {
            return goodCount;
        }

        public void setGoodCount(int goodCount) {
            this.goodCount = goodCount;
        }

        public int getMiddleCount() {
            return middleCount;
        }

        public void setMiddleCount(int middleCount) {
            this.middleCount = middleCount;
        }

        public int getBadCount() {
            return badCount;
        }

        public void setBadCount(int badCount) {
            this.badCount = badCount;
        }
    }
}
