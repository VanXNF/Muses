package com.victorxu.muses.mine.model;

import android.content.Context;

import com.victorxu.muses.gson.PageOrderStatus;
import com.victorxu.muses.mine.contract.OrderContract;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class OrderModel implements OrderContract.Model {

    private final String ORDER_LIST_SUFFIX_API = "list/";
    private final String ORDER_API = "api/order/";


    private int type;
    private Context context;
    private int currentPage = 1;
    private int allPages = 0;

    private List<PageOrderStatus.PageOrder.OrderBean> orders;

    public OrderModel(int type, Context context) {
        this.type = type;
        this.context = context;
        orders = new ArrayList<>();
    }

    @Override
    public void getOrderData(Callback callback) {
        getOrderData(1, callback);
    }

    @Override
    public void getOrderData(int page, Callback callback) {
        currentPage = page;
        int userId = (int) SharedPreferencesUtil.get(context, "UserId", 0);
        HttpUtil.getRequest(ORDER_API + ORDER_LIST_SUFFIX_API + String.valueOf(userId) + "/" + String.valueOf(type) + "/" + String.valueOf(currentPage), callback);
    }

    @Override
    public void getMoreOrderData(Callback callback) {
        getOrderData(currentPage + 1, callback);
    }

    @Override
    public void deleteOrderData(int position, Callback callback) {
        HttpUtil.deleteRequest(ORDER_API + String.valueOf(orders.get(position).getId()), callback);
        orders.remove(position);
    }

    @Override
    public List<PageOrderStatus.PageOrder.OrderBean> getLocalOrderData() {
        return orders;
    }

    @Override
    public void setLocalOrderData(List<PageOrderStatus.PageOrder.OrderBean> data) {
        orders.clear();
        orders.addAll(data);
    }

    @Override
    public void addLocalOrderData(List<PageOrderStatus.PageOrder.OrderBean> data) {
        orders.addAll(data);
    }

    @Override
    public boolean checkOrderDataStatus() {
        return orders.size() != 0;
    }

    @Override
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    @Override
    public boolean checkPageStatus() {
        return allPages != 0 && currentPage < allPages;
    }
}
