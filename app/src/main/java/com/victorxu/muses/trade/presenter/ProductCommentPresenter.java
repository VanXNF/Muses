package com.victorxu.muses.trade.presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.CommentCountStatus;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.trade.contract.ProductCommentContract;
import com.victorxu.muses.trade.model.ProductCommentModel;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
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
        mModel.getCommentCountData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getCommentCountData");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    CommentCountStatus status = new Gson().fromJson(response.body().string(), CommentCountStatus.class);
                    if (status.getCode().equals("OK")) {
                        List<String> data = new ArrayList<>();
                        List<String> tagNames = mModel.getTagData();
                        int image = status.getData().getWithImageCount();
                        int good = status.getData().getGoodCount();
                        int middle = status.getData().getMiddleCount();
                        int bad = status.getData().getBadCount();
                        int sum = good + middle + bad;

                        data.add(tagNames.get(0) + "(" + sum + ")");
                        data.add(tagNames.get(1) + "(" + image + ")");
                        data.add(tagNames.get(2) + "(" + good + ")");
                        data.add(tagNames.get(3) + "(" + middle + ")");
                        data.add(tagNames.get(4) + "(" + bad + ")");
                        String rate = new DecimalFormat("#.00").format(100.0f * good / sum) + "%";
                        mView.showTag(data, rate);
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                }
            }
        });
        mModel.setFilter(0);
        mModel.getCommentData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getCommentData");
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                    mView.showEmptyPage();
                    mView.hideLoading();
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    PageComment comment = new Gson().fromJson(response.body().string(), PageComment.class);
                    if (comment != null && comment.getCode().equals("OK")
                            && comment.getData().getTotalNum() != 0
                            && comment.getData().getDataList().size() != 0) {
                        mModel.setAllPages(comment.getData().getPageCount());
                        List<PageComment> pages = new ArrayList<>();
                        pages.add(comment);
                        mModel.setPageList(pages);
                        mView.hideEmptyPage();
                        mView.showComment(comment.getData().getDataList());
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (!mModel.checkDataStatus()) {
                        mView.showEmptyPage();
                    }
                    mView.hideLoading();
                }
            }
        });
    }

    @Override
    public void loadMoreDataToView() {
        mView.showLoadingMore();
        if (mModel.checkPageStatus()) {
            mModel.getMoreCommentData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getMoreCommentData");
                    if (!e.getMessage().equals("Socket closed")) {
                        mView.showToast(R.string.network_error_please_try_again);
                        mView.hideLoadingMore(false, false);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        PageComment comment = new Gson().fromJson(response.body().string(), PageComment.class);
                        if (comment != null && comment.getCode().equals("OK")
                                && comment.getData().getTotalNum() != 0 && comment.getData().getDataList() != null) {
                            mModel.setAllPages(comment.getData().getPageCount());
                            mModel.addPage(comment);
                            mView.showMoreComment(comment.getData().getDataList());
                            mView.hideLoadingMore(true, false);
                        } else {
                            mView.hideLoadingMore(false, false);
                        }
                    } catch (Exception e) {
                        mView.showToast(R.string.data_error_please_try_again);
                        e.printStackTrace();
                    }

                }
            });
        } else {
            mView.hideLoadingMore(true, true);
        }
    }

    @Override
    public void switchDataFilterMode(int filterId) {
        mModel.setFilter(filterId);
        mView.showLoading();
        mModel.getCommentData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getCommentData" + e.getMessage());
                if (!e.getMessage().equals("Socket closed")) {
                    mView.showToast(R.string.network_error_please_try_again);
                    mView.hideLoading();
                    mView.showEmptyPage();
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    PageComment comment = new Gson().fromJson(response.body().string(), PageComment.class);
                    if (comment != null && comment.getCode().equals("OK")
                            && comment.getData().getTotalNum() != 0 && comment.getData().getDataList().size() != 0) {
                        mModel.setAllPages(comment.getData().getPageCount());
                        List<PageComment> pages = new ArrayList<>();
                        pages.add(comment);
                        mModel.setPageList(pages);
                        mView.hideEmptyPage();
                        mView.showComment(comment.getData().getDataList());
                    } else {
                        mModel.setAllPages(0);
                        mModel.setPageList(new ArrayList<>());
                        mView.showEmptyPage();
                    }
                } catch (Exception e) {
                    mView.showToast(R.string.data_error_please_try_again);
                    e.printStackTrace();
                } finally {
                    if (!mModel.checkDataStatus()) {
                        mView.showEmptyPage();
                    }
                    mView.hideLoading();
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
