package com.victorxu.muses.search.presenter;

import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.search.model.SearchResultModel;

public class SearchResultPresenter implements SearchResultContract.Presenter {

    private SearchResultContract.View mView;
    private SearchResultContract.Model mModel = new SearchResultModel();

    public SearchResultPresenter(SearchResultContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadData() {
        mView.showProductList(mModel.getProductData());
    }

    @Override
    public void refreshData() {
        mView.refreshProductList(mModel.getProductData());
    }

    @Override
    public void loadMoreData() {
        mView.loadMoreProduct(mModel.getMoreData(), mModel.getDataStatus());
    }
}
