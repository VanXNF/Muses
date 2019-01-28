package com.victorxu.muses.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotKey {

    /**
     * code : OK
     * message :
     * data : [{"id":4,"keyword":"印象派"},{"id":9,"keyword":"抽象"},{"id":5,"keyword":"梵高"},{"id":1,"keyword":"欧式"},{"id":2,"keyword":"水墨画"},{"id":7,"keyword":"现代"},{"id":8,"keyword":"简约"}]
     */

    private String code;
    private String message;
    @SerializedName("data")
    private List<Key> hotKeyData;

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

    public List<Key> getHotKeyData() {
        return hotKeyData;
    }

    public void setHotKeyData(List<Key> hotKeyData) {
        this.hotKeyData = hotKeyData;
    }

    public static class Key {
        /**
         * id : 4
         * keyword : 印象派
         */

        private int id;
        private String keyword;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
