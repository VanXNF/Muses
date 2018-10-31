package com.victorxu.muses.shopping_cart.contract;

import android.content.Context;

import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.util.List;

public interface ShoppingCartContract {
    interface Model {

        List<ShoppingCartProduct> loadData();

        List<ShoppingCartProduct> refreshData();

        List<ShoppingCartProduct> removeCheckedData();

        List<ShoppingCartProduct> changeDataMode(boolean isEditMode);

        void getDataFromPresenter(List<ShoppingCartProduct> data);

        String getTotalPrice();

        String getTotalPrice(boolean isChooseAll);

    }

    interface View {

        Context getViewContext();

        void refreshCartTotalPrice(String price);

        void loadCartItem(List<ShoppingCartProduct> data);

        void refreshCartItem(List<ShoppingCartProduct> data);

        void changeCartMode(List<ShoppingCartProduct> data);
    }

    interface Presenter {

        void loadProduct();

        void refreshProduct();

        void removeCheckedProduct(List<ShoppingCartProduct> data);

        void refreshPrice(List<ShoppingCartProduct> data);

        void refreshPrice(List<ShoppingCartProduct> data, boolean isChooseAll);

        void changeCartMode(boolean isEditMode);
    }
}
