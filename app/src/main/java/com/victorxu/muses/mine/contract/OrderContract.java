package com.victorxu.muses.mine.contract;

import com.victorxu.muses.gson.PageOrderStatus;

import java.util.List;

public interface OrderContract {
    interface Model {
        void getOrderData(okhttp3.Callback callback);

        void getOrderData(int page, okhttp3.Callback callback);

        void getMoreOrderData(okhttp3.Callback callback);

        void deleteOrderData(int position, okhttp3.Callback callback);

        void setLocalOrderData(List<PageOrderStatus.PageOrder.OrderBean> data);

        void addLocalOrderData(List<PageOrderStatus.PageOrder.OrderBean> data);

        List<PageOrderStatus.PageOrder.OrderBean> getLocalOrderData();

        boolean checkOrderDataStatus();

        void setAllPages(int allPages);

        boolean checkPageStatus();
    }

    interface View {
        void initRootView(android.view.View view);

        void showOrder(List<PageOrderStatus.PageOrder.OrderBean> data);

        void showMoreOrder(List<PageOrderStatus.PageOrder.OrderBean> data);

        void showLoading();

        void hideLoading();

        void showLoadMore();

        void hideLoadMore(boolean isCompleted, boolean isEnd);

        void showEmptyPage();

        void hideEmptyPage();

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView();

        void reloadDataToView();

        void loadMoreDataToView();

        void cancelOrder(int position);
    }
}
