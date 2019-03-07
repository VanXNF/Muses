package com.victorxu.muses.shopping_cart.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.ShoppingCart;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.model.ShoppingCartModel;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShoppingCartPresenter implements ShoppingCartContract.Presenter {

    private static final String TAG = "ShoppingCartPresenter";

    private ShoppingCartContract.Model mModel;
    private ShoppingCartContract.View mView;

    public ShoppingCartPresenter(ShoppingCartContract.View mView, Context context) {
        this.mView = mView;
        mModel = new ShoppingCartModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView(boolean isEditMode) {
        if (mModel.getUserId() == 0) {
            mView.hideShoppingCart();
            mView.showEmptyView();
        } else {
            mView.showLoading();
            mModel.getCartData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getCartData");
                    mView.hideLoading();
                    if (!mModel.checkDataStatus()) {
                        mView.hideShoppingCart();
                        mView.showEmptyView();
                    }
                    mView.showToast(R.string.network_error_please_try_again);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        ShoppingCart cart = new Gson().fromJson(response.body().string(), ShoppingCart.class);
                        if (cart.getCode().equals("OK")) {
                            mModel.setShoppingCartData(cart.getData());
                        } else {
                            mView.showToast(cart.getMessage());
                        }
                        if (!mModel.checkDataStatus()) {
                            mView.hideShoppingCart();
                            mView.showEmptyView();
                        } else {
                            mView.hideEmptyView();
                            mView.showShoppingCart();
                            changeCartMode(isEditMode);
                        }
                    } catch (IOException e) {
                        Log.w(TAG, "onResponse: getCartData DATA ERROR");
                        mView.showToast(R.string.data_store_error_please_login_again);
                        e.printStackTrace();
                    } finally {
                        mView.hideLoading();
                    }
                }
            });
        }
    }

    @Override
    public void reloadDataToView(boolean isEditMode) {
        loadDataToView(isEditMode);
    }

    @Override
    public void loadStyleSelectData(int position) {
        mModel.getProductData(position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getProductData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Commodity commodity = new Gson().fromJson(response.body().string(), Commodity.class);
                if (commodity != null && commodity.getCode().equals("OK") && commodity.getData() != null) {
                    mView.showBottomSheet(mModel.getStyleSelectData(commodity.getData().getAttributes()));
                } else {
                    Log.w(TAG, "onResponse: getProductData DATA ERROR");
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updateData(int position, boolean isChecked) {
        mModel.updateData(position, isChecked);
        mView.showCartItem(mModel.getShoppingCartData());
        mView.showPrice(String.valueOf(mModel.getTotalPrice()));
    }

    @Override
    public void updateData(int position, int number) {
        mModel.updateData(position, number);
        mView.showCartItem(mModel.getShoppingCartData());
        mView.showPrice(String.valueOf(mModel.getTotalPrice()));
        mModel.updateCartData(position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: update number");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Status status = new Gson().fromJson(response.body().string(), Status.class);
                if (status != null) {
                    if (!status.getCode().equals("OK")) {
                        mView.showToast(status.getMessage());
                    }
                } else {
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void updateData(int position, String detail) {
        mModel.updateData(position, detail);
        mView.showCartItem(mModel.getShoppingCartData());
        mView.showPrice(String.valueOf(mModel.getTotalPrice()));
        mModel.updateCartData(position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: detail");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Status status = new Gson().fromJson(response.body().string(), Status.class);
                if (status != null) {
                    if (!status.getCode().equals("OK")) {
                        mView.showToast(status.getMessage());
                    }
                } else {
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void removeDataFromView() {
        mModel.deleteCartData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: delete all checked");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Status status = new Gson().fromJson(response.body().string(), Status.class);
                if (status != null) {
                    if (!status.getCode().equals("OK")) {
                        mView.showToast(status.getMessage());
                    }
                    if (!mModel.checkDataStatus()) {
                        mView.hideShoppingCart();
                        mView.showEmptyView();
                    }
                } else {
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
        mView.showCartItem(mModel.getShoppingCartData());
        mView.showPrice(String.valueOf(mModel.getTotalPrice()));
    }

    @Override
    public void removeDataFromView(int position) {
        mModel.deleteCartData(position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: delete");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Status status = new Gson().fromJson(response.body().string(), Status.class);
                if (status != null) {
                    if (!status.getCode().equals("OK")) {
                        mView.showToast(status.getMessage());
                    }
                    if (!mModel.checkDataStatus()) {
                        mView.hideShoppingCart();
                        mView.showEmptyView();
                    }
                } else {
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
        mView.showCartItem(mModel.getShoppingCartData());
        mView.showPrice(String.valueOf(mModel.getTotalPrice()));
    }

    @Override
    public void collectDataFromView() {
        mModel.addCartDataToFavorite(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: addCartDataToFavorite");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Status status = new Gson().fromJson(response.body().string(), Status.class);
                if (status != null) {
                    if (status.getCode().equals("OK") && status.getData() != null) {
                        mView.showToast(status.getMessage());
                    } else {
                        mView.showToast(status.getMessage());
                    }
                } else {
                    Log.w(TAG, "onResponse: addCartDataToFavorite DATA ERROR");
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void collectDataFromView(int position) {
        mModel.addCartDataToFavorite(position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: addCartDataToFavorite");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Status status = new Gson().fromJson(response.body().string(), Status.class);
                if (status != null) {
                    if (status.getCode().equals("OK") && status.getData() != null) {
                        mView.showToast(status.getMessage());
                    } else {
                        mView.showToast(status.getMessage());
                    }
                } else {
                    Log.w(TAG, "onResponse: addCartDataToFavorite DATA ERROR");
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
    }

    @Override
    public void changeCartMode(boolean isEditMode) {
        mModel.changeCartMode(isEditMode);
        mView.switchCartMode(isEditMode);
        mView.showPrice(String.valueOf(mModel.getTotalPrice()));
        mView.showCartItem(mModel.getShoppingCartData());
    }

    @Override
    public void checkAllData(boolean isCheckedAll) {
        mModel.checkAllData(isCheckedAll);
        mView.showCartItem(mModel.getShoppingCartData());
        mView.showPrice(String.valueOf(mModel.getTotalPrice()));
    }
}
