package com.victorxu.muses.trade.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Address;
import com.victorxu.muses.gson.DefaultAddress;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.trade.contract.SettleContract;
import com.victorxu.muses.trade.model.SettleModel;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
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
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    DefaultAddress address = new Gson().fromJson(response.body().string(), DefaultAddress.class);
                    if (address.getCode().equals("OK")) {
                        mModel.updateAddressId(address.getData().getId());
                        mView.showAddress(address.getData());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadAddress(Address.AddressBean data) {
        mView.showAddress(data);
    }

    @Override
    public void loadCartItemOfCart(List<ProductSettleOrderBean> data) {
        mView.showTotalPrice(mModel.getOrderPrice(data));
        mView.showCartItemOfOrder(data);
    }

    @Override
    public void loadCartItemOfCart(ProductSettleOrderBean data) {
        mModel.updateProductInfo(data);
        mView.showTotalPrice(mModel.getOrderPrice(data));
        mView.showProductItemOfOrder(data);
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
    public void submitCartOrder() {
        if (mModel.checkAddressStatus()) {
            mModel.updateCartOrderData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: updateCartOrderData");
                    if (!e.getMessage().equals("Socket closed")) {
                        mView.showToast(R.string.network_error_please_try_again);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        Status status = new Gson().fromJson(response.body().string(), Status.class);
                        if (status.getCode().equals("OK")) {
                            JSONObject object = new JSONObject(status.getData().toString());
                            int orderId = object.getInt("id");
                            mModel.updateOrderId(orderId);
                            String orderSN = object.getString("orderSN");
                            mView.showPayPage(orderSN);
                        } else {
                            mView.showToast(status.getMessage());
                        }
                    } catch (Exception e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }
                }
            });
        } else {
            mView.showToast(R.string.please_choose_address_first);
        }

    }

    @Override
    public void submitProductOrder() {
        if (mModel.checkAddressStatus()) {
            mModel.updateProductOrderData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: updateProductOrderData");
                    if (!e.getMessage().equals("Socket closed")) {
                        mView.showToast(R.string.network_error_please_try_again);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        Status status = new Gson().fromJson(response.body().string(), Status.class);
                        if (status.getCode().equals("OK")) {
                            JSONObject object = new JSONObject(status.getData().toString());
                            int orderId = object.getInt("id");
                            mModel.updateOrderId(orderId);
                            String orderSN = object.getString("orderSN");
                            mView.showPayPage(orderSN);
                        } else {
                            mView.showToast(status.getMessage());
                        }
                    } catch (Exception e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }
                }
            });
        } else {
            mView.showToast(R.string.please_choose_address_first);
        }
    }

    @Override
    public void payOrder() {
        mModel.updateOrderStatus(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: updateOrderStatus");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("OK")) {
                        mView.showToast(R.string.order_pay_success);
                    } else {
                        mView.showToast(R.string.order_do_not_finish_please_check_in_my_order_page);
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    mView.hidePayPage();
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
