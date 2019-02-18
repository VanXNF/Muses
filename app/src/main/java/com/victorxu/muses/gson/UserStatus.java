package com.victorxu.muses.gson;

public class UserStatus {
    /**
     * code : OK
     * message :
     * data : {"token":"50f05c0cede24ae6a27009812149c407","userId":119,"username":"维克多","avatar":"https://s1.ax1x.com/2018/06/22/PpsPDf.jpg"}
     */

    private String code;
    private String message;
    private UserBean data;

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

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }

    public static class UserBean {
        /**
         * token : 50f05c0cede24ae6a27009812149c407
         * userId : 119
         * username : 维克多
         * avatar : https://s1.ax1x.com/2018/06/22/PpsPDf.jpg
         */

        private String token;
        private int userId;
        private String username;
        private String avatar;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
