package com.victorxu.muses.trade.contract;

import com.victorxu.muses.gson.Address;
import com.victorxu.muses.gson.DefaultAddress;
import com.victorxu.muses.trade.view.entity.CartSettleOrderBean;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;

import java.util.List;

public interface SettleContract {
    interface Model {
        void getDefaultAddressData(okhttp3.Callback callback);

        void updateOrderStatus(okhttp3.Callback callback);

        void updateCartOrderData(okhttp3.Callback callback);

        void updateProductOrderData(okhttp3.Callback callback);

        void updateAddressId(int addressId);

        void updateCartIds(List<Integer> cartIds);

        void updateOrderId(int orderId);

        void updateProductInfo(ProductSettleOrderBean data);

        String getOrderPrice(List<ProductSettleOrderBean> data);

        String getOrderPrice(ProductSettleOrderBean data);

        boolean checkAddressStatus();

        void cancelTask();
    }

    interface View {
        void initRootView(android.view.View view);

        void showCartItemOfOrder(List<ProductSettleOrderBean> data);

        void showProductItemOfOrder(ProductSettleOrderBean data);

        void showAddress(Address.AddressBean data);

        void showAddress(DefaultAddress.DefaultAddressBean data);

        void showTotalPrice(String price);

        void showPayPage(String orderSN);

        void hidePayPage();

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDefaultAddress();

        void loadAddress(Address.AddressBean data);

        void loadCartItemOfCart(List<ProductSettleOrderBean> data);

        void loadCartItemOfCart(ProductSettleOrderBean data);

        void updateAddressId(int addressId);

        void updateCartIds(List<Integer> cartIds);

        void submitCartOrder();

        void submitProductOrder();

        void payOrder();

        void destroy();
    }
}
