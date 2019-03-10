package com.victorxu.muses.trade.contract;

import com.victorxu.muses.gson.Address;
import com.victorxu.muses.gson.DefaultAddress;
import com.victorxu.muses.gson.ShoppingCart;

import java.util.List;

public interface SettleContract {
    interface Model {
        void getDefaultAddressData(okhttp3.Callback callback);

        void updateOrderData(okhttp3.Callback callback);

        void updateAddressId(int addressId);

        void updateCartIds(List<Integer> cartIds);

        String getOrderPrice(List<ShoppingCart.CartItemBean> data);

        boolean checkAddressStatus();
    }

    interface View {
        void initRootView(android.view.View view);

        void showCartItemOfOrder(List<ShoppingCart.CartItemBean> data);

        void showAddress(Address.AddressBean data);

        void showAddress(DefaultAddress.DefaultAddressBean data);

        void showTotalPrice(String price);

        void showPayPage();

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDefaultAddress();

        void loadAddress(Address.AddressBean data);

        void loadCartItemOfCart(List<ShoppingCart.CartItemBean> data);

        void updateAddressId(int addressId);

        void updateCartIds(List<Integer> cartIds);

        void submitOrder();
    }
}
