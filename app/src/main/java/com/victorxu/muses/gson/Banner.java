package com.victorxu.muses.gson;

import java.util.List;

public class Banner {

    /**
     * code : OK
     * message : 加载成功
     * data : [{"id":1,"imageUrl":"https://s2.ax1x.com/2019/01/22/kFs73F.png"},{"id":2,"imageUrl":"https://s2.ax1x.com/2019/01/22/kFsIhT.png"},{"id":3,"imageUrl":"https://s2.ax1x.com/2019/01/22/kFsT9U.png"},{"id":4,"imageUrl":"https://s2.ax1x.com/2019/01/22/kFs5NV.png"}]
     */

    private String code;
    private String message;
    private List<BannerData> data;

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

    public List<BannerData> getData() {
        return data;
    }

    public void setData(List<BannerData> data) {
        this.data = data;
    }

    public static class BannerData {
        /**
         * id : 1
         * imageUrl : https://s2.ax1x.com/2019/01/22/kFs73F.png
         */

        private int id;
        private String imageUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
