package com.victorxu.muses.shopping_cart.contract;

import android.content.Context;

import com.victorxu.muses.gson.ShoppingCart;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.util.List;

public interface ShoppingCartContract {
    interface Model {
        int getUserId();
        void getCartData(okhttp3.Callback callback);
        void deleteCartData(int cartId, okhttp3.Callback callback);
        void updateCartData(int cartId, int position, okhttp3.Callback callback);
        void updateData(int position, boolean isChecked);
        void updateData(int position, int number);
        void updateData(int position, String detail);
        int getTotalPrice();
        boolean checkDataStatus();
        void changeCartMode(boolean isEditMode);
        void checkAllData(boolean isCheckedAll);
        void setShoppingCartData(List<ShoppingCart.CartItemBean> data);
        List<ShoppingCartProduct> getShoppingCartData();
    }

    interface View {
        void initRootView(android.view.View view);
        void showLoading();
        void hideLoading();
        void showCartItem(List<ShoppingCartProduct> data);
        void showPrice(String price);
        void showShoppingCart();
        void switchCartMode(boolean isEditMode);
        void hideShoppingCart();
        void showEmptyView();
        void hideEmptyView();
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView(boolean isEditMode);
        void reloadDataToView(boolean isEditMode);
        void updateData(int position, boolean isChecked);
        void updateData(int position, int number);
        void updateData(int position, String detail);
        void removeDataFromView(int cartId);
        void removeDataFromView(List<Integer> cartIds);
        void changeCartMode(boolean isEditMode);
        void checkAllData(boolean isCheckedAll);
    }
}
