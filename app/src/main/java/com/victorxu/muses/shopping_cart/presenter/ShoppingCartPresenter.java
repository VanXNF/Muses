package com.victorxu.muses.shopping_cart.presenter;

import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.model.ShoppingCartModel;

public class ShoppingCartPresenter implements ShoppingCartContract.Presenter {

    private ShoppingCartContract.Model mModel = new ShoppingCartModel();
    private ShoppingCartContract.View mView;

    public ShoppingCartPresenter(ShoppingCartContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadData() {
        mView.refreshCartItem(mModel.loadData());
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void changeCartMode() {

    }
}
