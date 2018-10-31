package com.victorxu.muses.shopping_cart.model;

import com.victorxu.muses.shopping_cart.contract.ShoppingCartContainerContract;

public class ShoppingCartContainerModel implements ShoppingCartContainerContract.Model {

    
    
    @Override
    public boolean isCartEmpty() {
        // TODO: 18-10-31 查询服务器该用户是否有购物车数据 
        return false;
    }
}
