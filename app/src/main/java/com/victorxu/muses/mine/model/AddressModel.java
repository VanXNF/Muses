package com.victorxu.muses.mine.model;

import android.content.Context;

import com.google.gson.Gson;
import com.victorxu.muses.gson.Address;
import com.victorxu.muses.mine.contract.AddressContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Callback;

public class AddressModel implements AddressContract.Model {

    private final String ADDRESS_LIST_API_SUFFIX = "list/";
    private final String ADDRESS_API = "api/address/";

    private Context context;

    public AddressModel(Context context) {
        this.context = context;
    }

    @Override
    public void getAddressData(Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        HttpUtil.getRequest(ADDRESS_API + ADDRESS_LIST_API_SUFFIX + String.valueOf(userId), callback);
    }

    @Override
    public void addAddressData(Address.AddressBean data, Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        data.setUserId(userId);
        HttpUtil.postRequest(ADDRESS_API, new Gson().toJson(data), callback);
    }

    @Override
    public void deleteAddressData(int addressId, Callback callback) {
        HttpUtil.deleteRequest(ADDRESS_API + String.valueOf(addressId), callback);
    }

    @Override
    public void updateAddressData(Address.AddressBean data, Callback callback) {
        HttpUtil.postRequest(ADDRESS_API + String.valueOf(data.getId()), new Gson().toJson(data), callback);
    }
}
