package com.victorxu.muses.gallery.presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gallery.contract.GalleryContract;
import com.victorxu.muses.gallery.model.GalleryModel;
import com.victorxu.muses.gson.Banner;
import com.victorxu.muses.gson.ListCommodity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class GalleryPresenter implements GalleryContract.Presenter {

    private static final String TAG = "GalleryPresenter";

    private GalleryContract.View mView;
    private GalleryContract.Model mModel = new GalleryModel();

    public GalleryPresenter(GalleryContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadRootView(View view) {
        mView.initView(view);
    }

    @Override
    public void loadDataToView() {
        mView.showLoading();
        mModel.getBannerData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!e.getMessage().equals("Socket closed")) {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                    mView.showToast(R.string.network_error_please_try_again);
                }
                Log.e(TAG, "onFailure: getBannerData");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Banner banner = new Gson().fromJson(response.body().string(), Banner.class);
                    if (banner.getCode().equals("OK") && banner.getData().size() != 0) {
                        mView.showBanner((ArrayList<Banner.BannerData>) banner.getData());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                }
            }
        });
        mModel.getRecommendData(3, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!e.getMessage().equals("Socket closed")) {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                    mView.showToast(R.string.network_error_please_try_again);
                }
                Log.d(TAG, "onFailure: getRecommendData");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ListCommodity commodity = new Gson().fromJson(response.body().string(), ListCommodity.class);
                    if (commodity.getCode().equals("OK") && commodity.getCommodityList().size() != 0) {
                        mView.showRecommendSection((ArrayList<ListCommodity.CommodityListModel>) commodity.getCommodityList());
                        Log.d(TAG, "onResponse: getRecommendData");
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                }

            }
        });
        mModel.getNewProductData(5, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!e.getMessage().equals("Socket closed")) {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                    mView.showToast(R.string.network_error_please_try_again);
                }
                Log.d(TAG, "onFailure: getNewProductData");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ListCommodity commodity = new Gson().fromJson(response.body().string(), ListCommodity.class);
                    if (commodity.getCode().equals("OK") && commodity.getCommodityList().size() != 0) {
                        mView.showNewSection((ArrayList<ListCommodity.CommodityListModel>) commodity.getCommodityList());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                }

            }
        });
        mModel.getHotProductData(5, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!e.getMessage().equals("Socket closed")) {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                    mView.showToast(R.string.network_error_please_try_again);
                }
                Log.d(TAG, "onFailure: getHotProductData");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ListCommodity commodity = new Gson().fromJson(response.body().string(), ListCommodity.class);
                    if (commodity.getCode().equals("OK") && commodity.getCommodityList().size() != 0) {
                        mView.showHotSection((ArrayList<ListCommodity.CommodityListModel>) commodity.getCommodityList());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (mModel.checkFlags()) {
                        mModel.resetFlags();
                        mView.hideLoading();
                    }
                }
            }
        });
    }

    @Override
    public void loadMoreDataToView() {
        mView.showLoadMore();
        mView.hideLoadMore(true, true);
    }

    @Override
    public void reloadDataToView() {
        loadDataToView();
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
