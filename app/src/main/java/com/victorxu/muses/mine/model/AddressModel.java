package com.victorxu.muses.mine.model;

import android.content.Context;

import com.victorxu.muses.mine.contract.AddressContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Callback;

public class AddressModel implements AddressContract.Model {

    private final String ADDRESS_API = "api/address/list/";

    private Context context;

    public AddressModel(Context context) {
        this.context = context;
    }

    @Override
    public void getAddressData(Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        HttpUtil.getRequest(ADDRESS_API + String.valueOf(userId), callback);
    }

    @Override
    public void addAddressData(Callback callback) {

    }

    @Override
    public void deleteAddressData(Callback callback) {

    }

    @Override
    public void updateAddressData(Callback callback) {

    }
}
