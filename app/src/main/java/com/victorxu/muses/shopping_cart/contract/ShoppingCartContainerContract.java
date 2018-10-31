package com.victorxu.muses.shopping_cart.contract;

public interface ShoppingCartContainerContract {
    interface Model {

        boolean isCartEmpty();
    }

    interface View {

        void showCartFragment();

        void showEmptyFragment();
    }

    interface Presenter {

        boolean getCartStatus();

        void changeCartViewFragment();
    }
}
