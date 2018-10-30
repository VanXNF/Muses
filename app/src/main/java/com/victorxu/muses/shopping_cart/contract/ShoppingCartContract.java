package com.victorxu.muses.shopping_cart.contract;

import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.util.List;

public interface ShoppingCartContract {
    interface Model {

        List<ShoppingCartProduct> loadData();

    }

    interface View {

        void refreshCartItem(List<ShoppingCartProduct> data);
    }

    interface Presenter {

        void loadData();

        void refreshData();

        void changeCartMode();
    }
}
