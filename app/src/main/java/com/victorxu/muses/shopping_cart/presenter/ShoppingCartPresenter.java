package com.victorxu.muses.shopping_cart.presenter;

import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.model.ShoppingCartModel;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.util.List;

public class ShoppingCartPresenter implements ShoppingCartContract.Presenter {

    private ShoppingCartContract.Model mModel;
    private ShoppingCartContract.View mView;

    public ShoppingCartPresenter(ShoppingCartContract.View mView) {
        this.mView = mView;
        mModel = new ShoppingCartModel(mView.getViewContext());
    }

    @Override
    public void loadProduct() {
        mView.loadCartItem(mModel.loadData());
    }

    @Override
    public void refreshProduct() {
        mView.refreshCartItem(mModel.refreshData());
    }

    @Override
    public void removeCheckedProduct(List<ShoppingCartProduct> data) {
        mModel.getDataFromPresenter(data);
        mView.refreshCartItem(mModel.removeCheckedData());
    }

    @Override
    public void refreshPrice(List<ShoppingCartProduct> data) {
        mModel.getDataFromPresenter(data);
        mView.refreshCartTotalPrice(mModel.getTotalPrice());
    }

    @Override
    public void refreshPrice(List<ShoppingCartProduct> data, boolean isChooseAll) {
        mModel.getDataFromPresenter(data);
        mView.refreshCartTotalPrice(mModel.getTotalPrice(isChooseAll));
    }

    @Override
    public void changeCartMode(boolean isEditMode) {
        mView.changeCartMode(mModel.changeDataMode(isEditMode));
    }
}
