package com.victorxu.muses.trade.view.entity;

import com.victorxu.muses.gson.ShoppingCart;

public class ShoppingCartProduct {

    private boolean isChecked;
    private boolean isEditedMode;
    private ShoppingCart.CartItemBean data;

    public ShoppingCartProduct(ShoppingCart.CartItemBean data) {
        this.data = data;
        this.isChecked = false;
        this.isEditedMode = false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isEditedMode() {
        return isEditedMode;
    }

    public void setEditedMode(boolean editedMode) {
        isEditedMode = editedMode;
    }

    public ShoppingCart.CartItemBean getData() {
        return data;
    }

    public void setData(ShoppingCart.CartItemBean data) {
        this.data = data;
    }
}
