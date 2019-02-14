package com.victorxu.muses.product.presenter;

import android.view.View;

import com.victorxu.muses.product.contract.ProductCommentContract;
import com.victorxu.muses.product.model.ProductCommentModel;

public class ProductCommentPresenter implements ProductCommentContract.Presenter {

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

    }
}
