package com.victorxu.muses.product.presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.contract.ProductContract;
import com.victorxu.muses.product.model.ProductModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProductPresenter implements ProductContract.Presenter {

    private static final String TAG = "ProductPresenter";

    private ProductContract.View mView;
    private ProductContract.Model mModel;

    public ProductPresenter(ProductContract.View mView, int id) {
        this.mView = mView;
        mModel = new ProductModel(id);
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
            public void onResponse(Call call, Response response) throws IOException {
                Commodity commodity = new Gson().fromJson(response.body().string(), Commodity.class);
                if (commodity != null && commodity.getCode().equals("OK") && commodity.getData() != null) {
                    mView.showBaseInfo(commodity.getData());
                    mView.showBanner(commodity.getData().getImageUrls());
                    mView.showProductDetail(commodity.getData().getDescription());
                } else {
                    Log.w(TAG, "onResponse: getProductData DATA ERROR");
                    mView.showToast(R.string.data_error_please_try_again);
                }
            }
        });
        mModel.getCommentData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getCommentData");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PageComment comment = new Gson().fromJson(response.body().string(), PageComment.class);
                if (comment != null && comment.getCode().equals("OK") && comment.getData() != null) {
                    mView.showEvaluation(comment.getData().getDataList());
                } else {
                    Log.w(TAG, "onResponse: getCommentData DATA ERROR");
                    mView.showToast(R.string.data_error_please_try_again);
                }

            }
        });
    }
}
