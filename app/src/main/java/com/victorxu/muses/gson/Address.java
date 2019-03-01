package com.victorxu.muses.gson;

import java.io.Serializable;
import java.util.List;

public class Address {

    /**
     * code : OK
     * message : 获取地址列表成功
     * data : [{"id":391,"province":"浙江省","city":"杭州市","district":"西湖区","address":"浙江科技学院","signerName":"维克多","signerMobile":"12345678910","userId":122,"defaultAddress":true},{"id":392,"province":"浙江省","city":"绍兴市","district":"上虞区","address":"小区","signerName":"维克多","signerMobile":"12345678910","userId":122,"defaultAddress":false}]
     */

    private String code;
    private String message;
    private List<AddressBean> data;

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

    public List<AddressBean> getData() {
        return data;
    }

    public void setData(List<AddressBean> data) {
        this.data = data;
    }

    public static class AddressBean implements Serializable {
        /**
         * id : 391
         * province : 浙江省
         * city : 杭州市
         * district : 西湖区
         * address : 浙江科技学院
         * signerName : 维克多
         * signerMobile : 12345678910
         * userId : 122
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
