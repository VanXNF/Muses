package com.victorxu.muses.trade.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.trade.contract.CustomizeContract;
import com.victorxu.muses.trade.model.CustomizeModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CustomizePresenter implements CustomizeContract.Presenter {

    private static final String TAG = "CustomizePresenter";

    private CustomizeContract.View mView;
    private CustomizeContract.Model mModel;

    public CustomizePresenter(CustomizeContract.View mView, int id, Context context) {
        this.mView = mView;
        mModel = new CustomizeModel(id, context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mModel.getProductData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getProductData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String json = response.body().string();
//                    Log.d(TAG, "onResponse: " + json);
                    Commodity commodity = new Gson().fromJson(json, Commodity.class);
                    if (commodity != null && commodity.getCode().equals("OK") && commodity.getData() != null) {
                        mModel.setCommodityData(commodity.getData());
                        mView.showCommodityData(commodity.getData());
                        mView.showStyleData(mModel.getStyleSelectData(commodity.getData().getAttributes()));
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void settleProduct() {
        mView.showSettleFragment(mModel.getProductSettleData());
    }

    @Override
    public void updateStyleSelectNumber(int number) {
        mModel.updateStyleSelectNumber(number);
    }

    @Override
    public void updateStyleSelectDetail(String key, String value, int parameterId, boolean isSelected, boolean isCompleted) {
        mModel.updateStyleSelectDetail(key, value, parameterId, isSelected);
        if (isCompleted) {
            mView.showSelectDetail("已选择 " + mModel.getSelectDetail());
        } else {
            mView.showSelectDetail("选择 尺寸、颜色分类");
        }
    }

    @Override
    public void updateProductImage(String image) {
        mModel.setProductOrderImage(image);
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
