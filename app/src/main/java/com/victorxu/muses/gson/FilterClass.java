package com.victorxu.muses.gson;

import java.util.List;

public class FilterClass {
    /**
     * code : OK
     * message : 请求成功
     * data : [{"id":1,"categoryName":"印象派","imageUrl":""},{"id":2,"categoryName":"现代","imageUrl":""}]
     */

    private String code;
    private String message;
    private List<FilterClassBean> data;

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

    public List<FilterClassBean> getData() {
        return data;
    }

    public void setData(List<FilterClassBean> data) {
        this.data = data;
    }

    public static class FilterClassBean {
        /**
         * id : 1
         * categoryName : 印象派
         * imageUrl :
         */

        private int id;
        private String categoryName;
        private String imageUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
