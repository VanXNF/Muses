package com.victorxu.muses.trade.view.entity;

import com.victorxu.muses.gson.ShoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SettleOrderBean implements Serializable {

    private List<Integer> cartIds;

    private List<ShoppingCart.CartItemBean> orderData;

    public SettleOrderBean(List<ShoppingCart.CartItemBean> orderData) {
        this.orderData = orderData;
        cartIds = new ArrayList<>();
        if (orderData != null) {
            for (ShoppingCart.CartItemBean data : orderData) {
                cartIds.add(data.getId());
            }
        }
    }

    public List<Integer> getCartIds() {
        return cartIds;
    }

    public List<ShoppingCart.CartItemBean> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<ShoppingCart.CartItemBean> orderData) {
        this.orderData = orderData;
    }
}
