package com.victorxu.muses.product.presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.contract.ProductCommentContract;
import com.victorxu.muses.product.model.ProductCommentModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProductCommentPresenter implements ProductCommentContract.Presenter {

    private static final String TAG = "ProductCommentPresenter";

    private ProductCommentContract.View mView;
    private ProductCommentContract.Model mModel;

    public ProductCommentPresenter(ProductCommentContract.View mView, int id) {
        this.mView = mView;
        mModel = new ProductCommentModel(id);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mView.showLoading();
        mView.showTag(mModel.getTagData());
        mModel.getCommentData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getCommentData");
                mView.showToast(R.string.network_error_please_try_again);
                mView.hideLoading();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PageComment comment = new Gson().fromJson(response.body().string(), PageComment.class);
                if (comment != null && comment.getCode().equals("OK")
                        && comment.getData().getTotalNum() != 0 && comment.getData().getDataList().size() != 0) {
                    mModel.setAllPages(comment.getData().getPageCount());
                    List<PageComment> pages = new ArrayList<>();
                    pages.add(comment);
                    mModel.setPageList(pages);
                    mView.showComment(comment.getData().getDataList());
                } else {
                    Log.w(TAG, "onResponse: getCommentData DATA ERROR");
                    mView.showToast(R.string.data_error_please_try_again);
                }
                mView.hideLoading();
            }
        });
    }

    @Override
    public void loadMoreDataToView() {
        mView.showLoadingMore();
        if (mModel.getAllPages() != 0 && mModel.getCurrentPage() < mModel.getAllPages()) {
            mModel.getMoreCommentData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getMoreCommentData");
                    mView.showToast(R.string.network_error_please_try_again);
                    mView.hideLoadingMore(false, false);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    PageComment comment = new Gson().fromJson(response.body().string(), PageComment.class);
                    if (comment != null && comment.getCode().equals("OK")
                            && comment.getData().getTotalNum() != 0 && comment.getData().getDataList() != null) {
                        mModel.setAllPages(comment.getData().getPageCount());
                        mModel.addPage(comment);
                        mView.showMoreComment(comment.getData().getDataList());
                        mView.hideLoadingMore(true, false);
                    } else {
                        Log.w(TAG, "onResponse: getCommentData DATA ERROR");
                        mView.showToast(R.string.data_error_please_try_again);
                        mView.hideLoadingMore(false, false);
                    }
                }
            });
        } else {
            mView.hideLoadingMore(true, true);
        }
    }
}