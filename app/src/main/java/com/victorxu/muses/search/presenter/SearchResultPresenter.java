package com.victorxu.muses.search.presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.PageCommodity;
import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.search.model.SearchResultModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultPresenter implements SearchResultContract.Presenter {

    private static final String TAG = "SearchResultPresenter";

    private SearchResultContract.View mView;
    private SearchResultContract.Model mModel;

    public SearchResultPresenter(int index, String keyword, SearchResultContract.View mView) {
        this.mView = mView;
        mModel = new SearchResultModel();
        mModel.setIndex(index);
        mModel.setKeyword(keyword);
    }

    @Override
    public void loadRootView(View view) {
        mView.initView(view);
    }

    @Override
    public void loadProductToView() {
        mView.showLoading();
        mModel.getProductData(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: getProductData");
                mView.hideLoading();
                mView.showToast(R.string.network_error_please_try_again);
                if (mModel.getPageList().size() == 0) {
                    mView.showFailPage();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PageCommodity commodity = new Gson().fromJson(response.body().string(), PageCommodity.class);
                if (commodity != null && commodity.getCode().equals("OK") && commodity.getPageData().getTotalNum() != 0) {
                    List<PageCommodity> pages = new ArrayList<>();
                    pages.add(commodity);
                    mModel.setPageList(pages);
                    mModel.setAllPages(commodity.getPageData().getPageCount());
                    mView.showProductList(commodity.getPageData().getCommodityList());
                    Log.d(TAG, "onResponse: getProductData");
                } else {
                    mView.showToast(R.string.data_error_please_try_again);
                    if (mModel.getPageList().size() == 0) {
                        mView.showEmptyPage();
                    }
                    Log.w(TAG, "onResponse: getProductData DATA ERROR");
                }
                mView.hideLoading();
            }
        });
    }

    @Override
    public void loadMoreProductToView() {
        mView.showLoadingMore();
        if (mModel.getAllPages() != 0 && mModel.getCurrentPage() < mModel.getAllPages()) {
            mModel.getMoreProductData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: getMoreProductData");
                    mView.hideLoadingMore(false, false);
                    mView.showToast(R.string.network_error_please_try_again);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    PageCommodity commodity = new Gson().fromJson(response.body().string(), PageCommodity.class);
                    if (commodity != null && commodity.getCode().equals("OK") && commodity.getPageData().getTotalNum() != 0) {
                        mModel.addPage(commodity);
                        mModel.setAllPages(commodity.getPageData().getPageCount());
                        mView.showMoreProduct(commodity.getPageData().getCommodityList());
                        mView.hideLoadingMore(true, false);
                        Log.d(TAG, "onResponse: getMoreProductData");
                    } else {
                        mView.showToast(R.string.data_error_please_try_again);
                        mView.hideLoadingMore(false, false);
                        Log.w(TAG, "onResponse: getProductData DATA ERROR");
                    }
                }
            });
        } else {
            mView.hideLoadingMore(true, true);
        }
    }

    @Override
    public void reloadProductToView() {
        loadProductToView();
    }
}
