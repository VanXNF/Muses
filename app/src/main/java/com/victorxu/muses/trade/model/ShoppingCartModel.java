package com.victorxu.muses.trade.model;


import android.content.Context;

import com.google.gson.Gson;
import com.victorxu.muses.gson.Collection;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.ShoppingCart;
import com.victorxu.muses.trade.view.entity.StyleSelectItem;
import com.victorxu.muses.trade.contract.ShoppingCartContract;
import com.victorxu.muses.trade.view.entity.ShoppingCartProduct;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

@SuppressWarnings("FieldCanBeLocal")
public class ShoppingCartModel implements ShoppingCartContract.Model {

    private final String SHOPPING_CART_API = "api/cart/list/";
    private final String SHOPPING_CART_ITEM_API = "/api/cart/";
    private final String COMMODITY_API_PREFIX = "api/commodity/";
    private final String FAVORITE_API = "api/favorite/commodity/";

    private Context context;
    private int userId;
    private List<ShoppingCartProduct> mData = new ArrayList<>();

    private Call mCallCart;
    private Call mCallProduct;
    private Call mCallDelete;
    private Call mCallUpdate;
    private Call mCallAdd;

    public ShoppingCartModel(Context context) {
        this.context = context;
    }

    @Override
    public int getUserId() {
        userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        return userId;
    }

    @Override
    public void getCartData(Callback callback) {
        mCallCart = HttpUtil.getRequest(SHOPPING_CART_API + String.valueOf(userId), callback);
    }

    @Override
    public void getProductData(int position, Callback callback) {
        int id = mData.get(position).getData().getCommodityId();
        mCallProduct = HttpUtil.getRequest(COMMODITY_API_PREFIX + String.valueOf(id), callback);
    }

    @Override
    public void deleteCartData(Callback callback) {
        boolean flag;
        do {
            flag = false;
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).isChecked()) {
                    deleteCartData(i, callback);
                    flag = true;
                    break;
                }
            }
        } while (flag);
    }

    @Override
    public void deleteCartData(int position, Callback callback) {
        int cartId = mData.get(position).getData().getId();
        mData.remove(position);
        mCallDelete = HttpUtil.deleteRequest(SHOPPING_CART_ITEM_API + String.valueOf(cartId), callback);
    }

    @Override
    public void updateCartData(int position, Callback callback) {
        int cartId = mData.get(position).getData().getId();
        String json = new Gson().toJson(mData.get(position).getData());
        mCallUpdate = HttpUtil.putRequest(SHOPPING_CART_ITEM_API + String.valueOf(cartId), json, callback);
    }

    @Override
    public void addCartDataToFavorite(Callback callback) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isChecked()) {
                addCartDataToFavorite(i, callback);
            }
        }
    }

    @Override
    public void addCartDataToFavorite(int position, Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        int commodityId = mData.get(position).getData().getCommodityId();
        Collection.CollectionBean entity = new Collection.CollectionBean();
        entity.setUserId(userId);
        entity.setCommodityId(commodityId);
        mCallAdd = HttpUtil.postRequest(FAVORITE_API, new Gson().toJson(entity), callback);
    }

    @Override
    public void updateData(int position, boolean isChecked) {
        mData.get(position).setChecked(isChecked);
    }

    @Override
    public void updateData(int position, int number) {
        mData.get(position).getData().setNumber(number);
    }

    @Override
    public void updateData(int position, String detail, String parameter) {
        mData.get(position).getData().setDetail(detail);
        mData.get(position).getData().setParameter(parameter);
    }

    @Override
    public void changeCartMode(boolean isEditMode) {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setEditedMode(isEditMode);
        }
    }

    @Override
    public void checkAllData(boolean isCheckedAll) {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setChecked(isCheckedAll);
        }
    }

    @Override
    public int getTotalPrice() {
        int sum = 0;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isChecked()) {
                sum += mData.get(i).getData().getCommodity().getDiscountPrice() * mData.get(i).getData().getNumber();
            }
        }
        return sum;
    }

    @Override
    public boolean checkDataStatus() {
        return mData.size() != 0;
    }

    @Override
    public List<ShoppingCartProduct> getShoppingCartData() {
        return mData;
    }

    @Override
    public void setShoppingCartData(List<ShoppingCart.CartItemBean> data) {
        mData.clear();
        for (int i = 0; i < data.size(); i++) {
            mData.add(new ShoppingCartProduct(data.get(i)));
        }
    }

    @Override
    public List<StyleSelectItem> getStyleSelectData(List<Commodity.CommodityDetail.AttributesBean> attributesBeans) {
        List<StyleSelectItem> styleSelectItems = new ArrayList<>();
        for (int i = 0; i < attributesBeans.size(); i++) {
            styleSelectItems.add(new StyleSelectItem(attributesBeans.get(i)));
        }
        return styleSelectItems;
    }

    @Override
    public List<ShoppingCart.CartItemBean> getCheckedData() {
        List<ShoppingCart.CartItemBean> checkedData = new ArrayList<>();
        for (ShoppingCartProduct data : mData) {
            if (data.isChecked()) {
                checkedData.add(data.getData());
            }
        }
        return checkedData;
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallCart);
        cancelCall(mCallProduct);
        cancelCall(mCallUpdate);
        cancelCall(mCallAdd);
        cancelCall(mCallDelete);
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }
}
