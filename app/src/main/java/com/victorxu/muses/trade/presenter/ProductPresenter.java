package com.victorxu.muses.trade.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.gson.Status;
import com.victorxu.muses.trade.contract.ProductContract;
import com.victorxu.muses.trade.model.ProductModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProductPresenter implements ProductContract.Presenter {

    private static final String TAG = "ProductPresenter";

    private ProductContract.View mView;
    private ProductContract.Model mModel;

    public ProductPresenter(ProductContract.View mView, int id, Context context) {
        this.mView = mView;
        mModel = new ProductModel(id, context);
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
                        mView.showBaseInfo(commodity.getData());
                        mView.showBanner(commodity.getData().getImageUrls());
                        mView.showProductDetail(commodity.getData().getDescription());
                        mView.showAttributeBottomSheet(mModel.getAttributeInfoData(commodity.getData().getInformation()));
                        mView.showStyleBottomSheet(mModel.getStyleSelectData(commodity.getData().getAttributes()));
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
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
            public void onResponse(Call call, Response response) {
                try {
                    String json = response.body().string();
//                    Log.d(TAG, "onResponse: " + json);
                    PageComment comment = new Gson().fromJson(json, PageComment.class);
                    if (comment != null && comment.getCode().equals("OK") && comment.getData().getDataList() != null) {
                        mView.showEvaluation(comment.getData().getDataList());
                    } else {
                        mView.showEmptyEvaluation();
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
        mModel.checkFavoriteStatus(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: checkFavoriteStatus");
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    Status status = new Gson().fromJson(response.body().string(), Status.class);
                    if (status.getCode().equals("ERROR") && status.getData() != null) {
                        mModel.setFavoriteId(((Double) status.getData()).intValue());
                        mView.showFavorite(true);
//                        Log.d(TAG, "onResponse: ALREADY COLLECT");
                    } else {
                        mView.showFavorite(false);
//                        Log.d(TAG, "onResponse: CAN COLLECT");
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void addToCart() {
        if (mModel.checkUserStatus()) {
            mModel.addProductDataToCart(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: delete");
                    mView.showToast(R.string.network_error_please_try_again);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        Status status = new Gson().fromJson(response.body().string(), Status.class);
                        if (status.getCode().equals("OK")) {
                            mView.showToast(R.string.add_shopping_cart_success);
                        } else {
                            mView.showToast(status.getMessage());
                        }
                    } catch (Exception e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void buyNow() {
        if (mModel.checkUserStatus()) {
            mView.showSettleFragment(mModel.getProductSettleData());
        }
    }

    @Override
    public void addToFavorite() {
        if (mModel.checkUserStatus()) {
            mModel.addProductDataToFavorite(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getCollectionCountData");
                    mView.showToast(R.string.network_error_please_try_again);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        String json = response.body().string();
                        Status status = new Gson().fromJson(json, Status.class);
                        if (status.getCode().equals("OK") && status.getData() != null) {
                            mModel.setFavoriteId(((Double) status.getData()).intValue());
                            mView.showFavorite(true);
                        }
                    } catch (Exception e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void removeFromFavorite() {
        if (mModel.checkUserStatus()) {
            mModel.removeProductDataFromFavorite(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: removeProductDataFromFavorite");
                    mView.showToast(R.string.network_error_please_try_again);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        Status status = new Gson().fromJson(response.body().string(), Status.class);
                        if (status.getCode().equals("ERROR")) {
                            mView.showToast(status.getMessage());
                        } else {
                            mView.showFavorite(false);
                        }
                    } catch (Exception e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }
                }
            });
        }
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
