package com.victorxu.muses.trade.view.entity;

import com.victorxu.muses.gson.ShoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartSettleOrderBean implements Serializable {

    private List<Integer> cartIds;

    private List<ProductSettleOrderBean> orderData;

    public CartSettleOrderBean(List<ShoppingCart.CartItemBean> orderData) {
        convertCartBeanToSettleBean(orderData);
    }

    private void convertCartBeanToSettleBean(List<ShoppingCart.CartItemBean> orderData) {
        this.orderData = new ArrayList<>();
        cartIds = new ArrayList<>();
        for (ShoppingCart.CartItemBean data : orderData) {
            this.orderData.add(new ProductSettleOrderBean(
                    data.getCommodityId(),
                    data.getImage(),
                    data.getCommodity().getName(),
                    data.getDetail(),
                    data.getParameter(),
                    String.valueOf(data.getCommodity().getDiscountPrice()),
                    String.valueOf(data.getNumber())));
            cartIds.add(data.getId());
        }
    }

    public List<Integer> getCartIds() {
        return cartIds;
    }

    public List<ProductSettleOrderBean> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<ProductSettleOrderBean> orderData) {
        this.orderData = orderData;
    }
}
