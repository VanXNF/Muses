package com.victorxu.muses.trade.contract;

import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.ShoppingCart;
import com.victorxu.muses.product.view.entity.StyleSelectItem;
import com.victorxu.muses.trade.view.entity.ShoppingCartProduct;

import java.util.List;

public interface ShoppingCartContract {
    interface Model {
        int getUserId();

        void getCartData(okhttp3.Callback callback);

        void getProductData(int position, okhttp3.Callback callback);

        void deleteCartData(okhttp3.Callback callback);

        void deleteCartData(int position, okhttp3.Callback callback);

        void updateCartData(int position, okhttp3.Callback callback);

        void addCartDataToFavorite(okhttp3.Callback callback);

        void addCartDataToFavorite(int position, okhttp3.Callback callback);

        void updateData(int position, boolean isChecked);

        void updateData(int position, int number);

        void updateData(int position, String detail, String parameter);

        void changeCartMode(boolean isEditMode);

        void checkAllData(boolean isCheckedAll);

        int getTotalPrice();

        boolean checkDataStatus();

        List<ShoppingCartProduct> getShoppingCartData();

        void setShoppingCartData(List<ShoppingCart.CartItemBean> data);

        List<StyleSelectItem> getStyleSelectData(List<Commodity.CommodityDetail.AttributesBean> attributesBeans);

        List<ShoppingCart.CartItemBean> getCheckedData();
    }

    interface View {
        void initRootView(android.view.View view);

        void showLoading();

        void hideLoading();

        void showCartItem(List<ShoppingCartProduct> data);

        void showBottomSheet(List<StyleSelectItem> data);

        void showPrice(String price);

        void showShoppingCart();

        void switchCartMode(boolean isEditMode);

        void hideShoppingCart();

        void showEmptyView();

        void hideEmptyView();

        void showSettleFragment(List<ShoppingCart.CartItemBean> data);

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView(boolean isEditMode);

        void reloadDataToView(boolean isEditMode);

        void loadStyleSelectData(int position);

        void updateData(int position, boolean isChecked);

        void updateData(int position, int number);

        void updateData(int position, String detail, String parameter);

        void removeDataFromView();

        void removeDataFromView(int position);

        void collectDataFromView();

        void collectDataFromView(int position);

        void changeCartMode(boolean isEditMode);

        void checkAllData(boolean isCheckedAll);

        void settleShoppingCart();
    }
}
