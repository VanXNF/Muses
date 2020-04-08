package com.victorxu.muses.mine.model;

import android.content.Context;

import com.google.gson.Gson;
import com.victorxu.muses.gson.Address;
import com.victorxu.muses.mine.contract.AddressContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import okhttp3.Call;
import okhttp3.Callback;

public class AddressModel implements AddressContract.Model {

    private final String ADDRESS_LIST_API_SUFFIX = "list/";
    private final String ADDRESS_API = "api/address/";

    private Context context;

    private Call mCallGet;
    private Call mCallAdd;
    private Call mCallDelete;
    private Call mCallUpdate;

    public AddressModel(Context context) {
        this.context = context;
    }

    @Override
    public void getAddressData(Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        mCallGet = HttpUtil.getRequest(context, ADDRESS_API + ADDRESS_LIST_API_SUFFIX + String.valueOf(userId), callback);
    }

    @Override
    public void addAddressData(Address.AddressBean data, Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        data.setUserId(userId);
        mCallAdd = HttpUtil.postRequest(context, ADDRESS_API, new Gson().toJson(data), callback);
    }

    @Override
    public void deleteAddressData(int addressId, Callback callback) {
        mCallDelete = HttpUtil.deleteRequest(context, ADDRESS_API + String.valueOf(addressId), callback);
    }

    @Override
    public void updateAddressData(Address.AddressBean data, Callback callback) {
        mCallUpdate = HttpUtil.postRequest(context, ADDRESS_API + String.valueOf(data.getId()), new Gson().toJson(data), callback);
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallGet);
        cancelCall(mCallAdd);
        cancelCall(mCallDelete);
        cancelCall(mCallUpdate);
    }

    private void cancelCall(Call call) {
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }
}
