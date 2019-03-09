package com.victorxu.muses.trade.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.DefaultAddress;
import com.victorxu.muses.gson.ShoppingCart;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.trade.contract.SettleContract;
import com.victorxu.muses.trade.model.SettleModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SettlePresenter implements SettleContract.Presenter {

    private static final String TAG = "SettlePresenter";

    private SettleContract.View mView;
    private SettleContract.Model mModel;

    public SettlePresenter(SettleContract.View mView, Context context) {
        this.mView = mView;
        mModel = new SettleModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDefaultAddress() {
        mModel.getDefaultAddressData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getDefaultAddress");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    DefaultAddress address = new Gson().fromJson(response.body().string(), DefaultAddress.class);
                    if (address.getCode().equals("OK")) {
                        mView.showAddress(address.getData());
                    }
                } catch (IOException e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadCartItemOfCart(List<ShoppingCart.CartItemBean> data) {
        mView.showTotalPrice(mModel.getOrderPrice(data));
        mView.showCartItemOfOrder(data);
    }

    @Override
    public void updateAddressId(int addressId) {
        mModel.updateAddressId(addressId);
    }

    @Override
    public void updateCartIds(List<Integer> cartIds) {
        mModel.updateCartIds(cartIds);
    }

    @Override
    public void submitOrder() {
        mModel.updateOrderData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updateOrderData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("OK")) {
                        mView.showPayPage();
                    } else {
                        mView.showToast(status.getMessage());
                    }
                } catch (IOException e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
    }
}
