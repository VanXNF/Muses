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
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
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
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                }
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
                            mView.showAddress(new ArrayList<>());
                            mView.showToast(R.string.do_not_have_address);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void addAddress(Address.AddressBean data) {
        mModel.addAddressData(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: addAddress");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    mView.showToast(status.getMessage());
                    if (status.getCode().equals("OK")) {
                        loadDataToView();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.w(TAG, "onResponse: addAddress");
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void deleteAddress(int id) {
        mModel.deleteAddressData(id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: deleteAddress");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    mView.showToast(status.getMessage());
                    if (status.getCode().equals("OK")) {
                        loadDataToView();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.w(TAG, "onResponse: deleteAddress");
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updateAddress(Address.AddressBean data) {
        mModel.updateAddressData(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updateAddress");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    mView.showToast(status.getMessage());
                    if (status.getCode().equals("OK")) {
                        loadDataToView();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.w(TAG, "onResponse: updateAddress");
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void destroy() {
        mView = null;
        if (mModel != null) {
            mModel.cancelTask();
            mModel = null;
        }
    }
}
