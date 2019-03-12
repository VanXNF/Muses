package com.victorxu.muses.trade.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.victorxu.muses.trade.contract.SettleContract;
import com.victorxu.muses.trade.model.entity.SettleOrderEntity;
import com.victorxu.muses.trade.view.entity.CartSettleOrderBean;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class SettleModel implements SettleContract.Model {

    private final String DEFAULT_ADDRESS_API = "api/address/default/";
    private final String ORDER_API = "api/order/";
    private final String ORDER_DIRECT_API = "api/order/addDirectly";

    private Context context;
    private int addressId = 0;
    private List<Integer> cartIds = new ArrayList<>();
    private int orderId = 0;
    private ProductSettleOrderBean productInfo;

    public SettleModel(Context context) {
        this.context = context;
    }

    @Override
    public void getDefaultAddressData(Callback callback) {
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        HttpUtil.getRequest(DEFAULT_ADDRESS_API + String.valueOf(userId), callback);
    }

    @Override
    public void updateOrderStatus(Callback callback) {
        JsonObject object = new JsonObject();
        object.addProperty("status", 1);
        HttpUtil.putRequest(ORDER_API + String.valueOf(orderId), object.toString(), callback);
    }

    @Override
    public void updateCartOrderData(Callback callback) {
        SettleOrderEntity entity = new SettleOrderEntity();
        entity.setUserId((int) SharedPreferencesUtil.get(context, "UserId", 0));
        entity.setAddressId(addressId);
        entity.setCartIds(cartIds);
        Log.d("CART_ORDER", new Gson().toJson(entity));
        HttpUtil.postRequest(ORDER_API, new Gson().toJson(entity), callback);
    }

    @Override
    public void updateProductOrderData(Callback callback) {
        JsonObject object = new JsonObject();
        object.addProperty("addressId", addressId);
        object.addProperty("parameter", productInfo.getParameter());
        object.addProperty("commodityId", productInfo.getCommodityId());
        object.addProperty("userId", (int) SharedPreferencesUtil.get(context, "UserId", 0));
        object.addProperty("number", productInfo.getNumber());
        object.addProperty("message", "");
        object.addProperty("detail", productInfo.getDetail());
        HttpUtil.postRequest(ORDER_DIRECT_API, object.toString(), callback);
    }

    @Override
    public void updateAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public void updateCartIds(List<Integer> cartIds) {
        this.cartIds.clear();
        this.cartIds.addAll(cartIds);
    }

    @Override
    public void updateOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void updateProductInfo(ProductSettleOrderBean data) {
        productInfo = data;
    }

    @Override
    public String getOrderPrice(List<ProductSettleOrderBean> data) {
        int sum = 0;
        for (ProductSettleOrderBean item : data) {
            sum += Integer.parseInt(getOrderPrice(item));
        }
        return String.valueOf(sum);
    }

    @Override
    public String getOrderPrice(ProductSettleOrderBean data) {
        int sum = (Integer.parseInt(data.getPrice()) * Integer.parseInt(data.getNumber()));
        return String.valueOf(sum);
    }

    @Override
    public boolean checkAddressStatus() {
        return addressId != 0;
    }
}
