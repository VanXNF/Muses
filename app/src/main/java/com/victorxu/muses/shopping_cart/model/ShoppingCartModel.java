package com.victorxu.muses.shopping_cart.model;

import android.content.Context;

import com.victorxu.muses.R;
import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartModel implements ShoppingCartContract.Model {

    private List<ShoppingCartProduct> data;
    private Context context;

    public ShoppingCartModel(Context context) {
        this.context = context;
    }

    @Override
    public List<ShoppingCartProduct> loadData() {
        data = initTestData();
        return data;
    }

    @Override
    public List<ShoppingCartProduct> refreshData() {
        return initTestData();
    }

    @Override
    public List<ShoppingCartProduct> removeCheckedData() {
        List<ShoppingCartProduct> newData = new ArrayList<>();
        for (ShoppingCartProduct product : data) {
            if (!product.isChecked()) {
                newData.add(product);
            }
        }
        data.clear();
        data.addAll(newData);
        return data;
    }


    @Override
    public List<ShoppingCartProduct> changeDataMode(boolean isEditMode) {
        for (ShoppingCartProduct product : data) {
            product.setEditedMode(isEditMode);
        }
        return data;
    }

    @Override
    public void getDataFromPresenter(List<ShoppingCartProduct> data) {
        this.data = data;
    }

    @Override
    public String getTotalPrice() {
        double totalPrice = 0;
        for (ShoppingCartProduct product : data) {
            if (product.isChecked()) {
                totalPrice += Double.parseDouble(product.getPrice()) * product.getNumber();
            }
        }
        return totalPrice == 0 ? context.getResources().getString(R.string.zero) : String.valueOf(totalPrice);
    }

    @Override
    public String getTotalPrice(boolean isChooseAll) {
        double totalPrice = 0;
        for (ShoppingCartProduct product : data) {
            product.setChecked(isChooseAll);
            if (product.isChecked()) {
                totalPrice += Double.parseDouble(product.getPrice()) * product.getNumber();
            }
        }
        return totalPrice == 0 ? context.getResources().getString(R.string.zero) : String.valueOf(totalPrice);
    }

    private List<ShoppingCartProduct> initTestData() {
        List<ShoppingCartProduct> testData = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            testData.add(new ShoppingCartProduct("https://s1.ax1x.com/2018/04/02/CSWxf0.jpg", "便携保温杯", "银色 旋转波纹", "999", 1));
        }
        return testData;
    }
}
