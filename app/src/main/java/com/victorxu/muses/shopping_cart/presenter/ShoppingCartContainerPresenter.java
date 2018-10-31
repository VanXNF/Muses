package com.victorxu.muses.shopping_cart.presenter;

import com.victorxu.muses.shopping_cart.contract.ShoppingCartContainerContract;
import com.victorxu.muses.shopping_cart.model.ShoppingCartContainerModel;

public class ShoppingCartContainerPresenter implements ShoppingCartContainerContract.Presenter {

    private ShoppingCartContainerContract.View mView;

    private ShoppingCartContainerContract.Model mModel;

    public ShoppingCartContainerPresenter(ShoppingCartContainerContract.View mView) {
        this.mView = mView;
        mModel = new ShoppingCartContainerModel();
    }

    @Override
    public boolean getCartStatus() {
        return mModel.isCartEmpty();
    }

    @Override
    public void changeCartViewFragment() {
        if (mModel.isCartEmpty()) {
            mView.showEmptyFragment();
        } else {
            mView.showCartFragment();
        }
    }
}
