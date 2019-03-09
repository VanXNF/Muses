package com.victorxu.muses.gson;

public class DefaultAddress {
    /**
     * code : OK
     * message : 获取默认地址成功
     * data : {"id":2,"province":"福建省","city":"深圳市","district":"山亭区","address":"谈路E座","signerName":"咎斌","signerMobile":"15948477495","userId":3,"defaultAddress":true}
     */

    private String code;
    private String message;
    private DefaultAddressBean data;

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

    public DefaultAddressBean getData() {
        return data;
    }

    public void setData(DefaultAddressBean data) {
        this.data = data;
    }

    public static class DefaultAddressBean {
        /**
         * id : 2
         * province : 福建省
         * city : 深圳市
         * district : 山亭区
         * address : 谈路E座
         * signerName : 咎斌
         * signerMobile : 15948477495
         * userId : 3
         * defaultAddress : true
         */

        private int id;
        private String province;
        private String city;
        private String district;
        private String address;
        private String signerName;
        private String signerMobile;
        private int userId;
        private boolean defaultAddress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSignerName() {
            return signerName;
        }

        public void setSignerName(String signerName) {
            this.signerName = signerName;
        }

        public String getSignerMobile() {
            return signerMobile;
        }

        public void setSignerMobile(String signerMobile) {
            this.signerMobile = signerMobile;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public boolean isDefaultAddress() {
            return defaultAddress;
        }

        public void setDefaultAddress(boolean defaultAddress) {
            this.defaultAddress = defaultAddress;
        }
    }
}
