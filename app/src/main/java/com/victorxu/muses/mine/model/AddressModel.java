package com.victorxu.muses.mine.model;

import android.content.Context;

import com.victorxu.muses.mine.contract.AddressContract;

import okhttp3.Callback;

public class AddressModel implements AddressContract.Model {

    private Context context;

    public AddressModel(Context context) {
        this.context = context;
    }

    @Override
    public void getAddressData(Callback callback) {

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
