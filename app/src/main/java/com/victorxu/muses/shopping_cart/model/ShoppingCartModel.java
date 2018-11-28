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
        testData.add(new ShoppingCartProduct("https://img.alicdn.com/imgextra/i2/1779099327/TB24wCWpN9YBuNjy0FfXXXIsVXa_!!1779099327.jpg_430x430q90.jpg", "三联北欧创意油画大气壁画油画挂画", "麻棉油画布肌理+环保画框", "358.00", 2));
        testData.add(new ShoppingCartProduct("https://img.alicdn.com/imgextra/i4/2122078663/O1CN012DrhaXqQtvjYMdJ_!!2122078663.jpg_430x430q90.jpg", "客厅装饰画沙发背景墙挂画新中式办公室", "独立", "418.00", 1));
        testData.add(new ShoppingCartProduct("https://img.alicdn.com/imgextra/i1/1675711085/O1CN011JsyCTbDzUR7HRD_!!0-item_pic.jpg_430x430q90.jpg", "现代简约餐厅墙面装饰壁画", "多尺寸组合 深色木框", "286.00", 2));

//        for (int i = 0; i < 5 ; i++) {
//            testData.add(new ShoppingCartProduct("https://s1.ax1x.com/2018/04/02/CSWxf0.jpg", "便携保温杯", "银色 旋转波纹", "999", 1));
//        }
        return testData;
    }
}
