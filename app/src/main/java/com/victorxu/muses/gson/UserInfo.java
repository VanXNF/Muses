package com.victorxu.muses.gson;

public class UserInfo {

    /**
     * code : OK
     * message : 请求成功
     * data : {"id":116,"username":"13326707192","password":"pbkdf2_sha256$20000$gcIJmE3uEhjo$N+KnrSXZByhF961PVuDtl3vPqehUrbb0FO4jaJqAtmw=","avatar":"https://s1.ax1x.com/2018/06/22/PpBWrT.jpg","nickname":"贡丹","birthday":997200000000,"gender":1,"mobile":"13326707192","email":"yanshao@yahoo.com","level":2,"token":"e51a2b6b-97cf-7628-1331-50cee40b9390"}
     */

    private String code;
    private String message;
    private UserInfoBean data;

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

    public UserInfoBean getData() {
        return data;
    }

    public void setData(UserInfoBean data) {
        this.data = data;
    }

    public static class UserInfoBean {
        /**
         * id : 116
         * username : 13326707192
         * password : pbkdf2_sha256$20000$gcIJmE3uEhjo$N+KnrSXZByhF961PVuDtl3vPqehUrbb0FO4jaJqAtmw=
         * avatar : https://s1.ax1x.com/2018/06/22/PpBWrT.jpg
         * nickname : 贡丹
         * birthday : 997200000000
         * gender : 1
         * mobile : 13326707192
         * email : yanshao@yahoo.com
         * level : 2
         * token : e51a2b6b-97cf-7628-1331-50cee40b9390
         */

        private int id;
        private String username;
        private String password;
        private String avatar;
        private String nickname;
        private long birthday;
        private int gender;
        private String mobile;
        private String email;
        private int level;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
