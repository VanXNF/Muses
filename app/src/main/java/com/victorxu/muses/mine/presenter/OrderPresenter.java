package com.victorxu.muses.mine.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.PageOrderStatus;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.mine.contract.OrderContract;
import com.victorxu.muses.mine.model.OrderModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OrderPresenter implements OrderContract.Presenter {

    private static final String TAG = "OrderPresenter";

    private OrderContract.View mView;
    private OrderContract.Model mModel;

    public OrderPresenter(OrderContract.View mView, int type, Context context) {
        this.mView = mView;
        mModel = new OrderModel(type, context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mView.showLoading();
        mModel.getOrderData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getOrderData");
                mView.showToast(R.string.network_error_please_try_again);
                if (!mModel.checkOrderDataStatus()) {
                    mView.showEmptyPage();
                }
                mView.hideLoading();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    PageOrderStatus orderStatus = new Gson().fromJson(response.body().string(), PageOrderStatus.class);
                    if (orderStatus.getCode().equals("OK") && orderStatus.getData() != null) {
                        mModel.setAllPages(orderStatus.getData().getPageCount());
                        mModel.setLocalOrderData(orderStatus.getData().getDataList());
                        mView.hideEmptyPage();
                        mView.showOrder(orderStatus.getData().getDataList());
                    } else {
                        mView.showEmptyPage();
                    }
                } catch (IOException e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (!mModel.checkOrderDataStatus()) {
                        mView.showEmptyPage();
                    }
                    mView.hideLoading();
                }
            }
        });

    }

    @Override
    public void reloadDataToView() {
        loadDataToView();
    }

    @Override
    public void loadMoreDataToView() {
        mView.showLoadMore();
        if (mModel.checkPageStatus()) {
            mModel.getMoreOrderData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getMoreOrderData");
                    mView.showToast(R.string.network_error_please_try_again);
                    mView.hideLoadMore(false, false);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        PageOrderStatus orderStatus = new Gson().fromJson(response.body().string(), PageOrderStatus.class);
                        if (orderStatus.getCode().equals("OK")) {
                            mModel.setAllPages(orderStatus.getData().getPageCount());
                            mModel.addLocalOrderData(orderStatus.getData().getDataList());
                            mView.showMoreOrder(orderStatus.getData().getDataList());
                            mView.hideLoadMore(true, false);
                        } else {
//                            mView.showToast(orderStatus.getMessage());
                            mView.hideLoadMore(false, false);
                        }
                    } catch (IOException e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        mView.hideLoadMore(false, false);
                        e.printStackTrace();
                    } finally {
                        if (!mModel.checkOrderDataStatus()) {
                            mView.showEmptyPage();
                        }
                    }
                }
            });
        } else {
            mView.hideLoadMore(false, true);
        }
    }

    @Override
    public void cancelOrder(int position) {
        mModel.deleteOrderData(position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: deleteOrderData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("OK")) {
                        mView.showOrder(mModel.getLocalOrderData());
                    }
                    mView.showToast(status.getMessage());
                } catch (IOException e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (!mModel.checkOrderDataStatus()) {
                        mView.showEmptyPage();
                    }
                }
            }
        });
    }

    @Override
    public void payOrder(int position) {
        mModel.updateOrderData(position, new Callback() {
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
                        mView.showToast(R.string.order_pay_success);
                        reloadDataToView();
                    } else {
                        mView.showToast(R.string.order_do_not_finish);
                    }
                } catch (IOException e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    mView.hidePaySheet();
                }
            }
        });
    }
}
