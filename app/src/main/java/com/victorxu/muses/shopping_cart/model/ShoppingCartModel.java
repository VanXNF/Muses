package com.victorxu.muses.shopping_cart.model;

import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartModel implements ShoppingCartContract.Model {

    private List<ShoppingCartProduct> data = new ArrayList<>();

    @Override
    public List<ShoppingCartProduct> loadData() {
        data = initTestData();
        return data;
    }

    private List<ShoppingCartProduct> initTestData() {
        List<ShoppingCartProduct> testData = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            testData.add(new ShoppingCartProduct("https://s1.ax1x.com/2018/04/02/CSWxf0.jpg", "便携保温杯", "银色 旋转波纹", "999", 1));
        }
        return testData;
    }
}
