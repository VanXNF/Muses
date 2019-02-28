package com.victorxu.muses.mine.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Address;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.mine.contract.AddressContract;
import com.victorxu.muses.mine.model.AddressModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddressPresenter implements AddressContract.Presenter {

    private static final String TAG = "AddressPresenter";

    private AddressContract.View mView;
    private AddressContract.Model mModel;

    public AddressPresenter(AddressContract.View mView, Context context) {
        this.mView = mView;
        mModel = new AddressModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mModel.getAddressData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getAddressData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Address address = new Gson().fromJson(response.body().string(), Address.class);
                    if (address.getCode().equals("ERROR")) {
                        mView.showToast(address.getMessage());
                    } else {
                        if (address.getData() != null) {
                            mView.showAddress(address.getData());
                        } else {
                            throw new IOException();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void addAddress() {

    }

    @Override
    public void deleteAddress(int position) {

    }

    @Override
    public void updateAddress(int position) {

    }
}
