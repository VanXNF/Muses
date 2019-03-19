package com.victorxu.muses.gallery.contract;

import com.victorxu.muses.gson.Banner;
import com.victorxu.muses.gson.ListCommodity;

import java.util.ArrayList;

public interface GalleryContract {
    interface Model {
        void resetFlags();

        boolean checkFlags();

        void getBannerData(okhttp3.Callback callback);

        void getRecommendData(int count, okhttp3.Callback callback);

        void getNewProductData(int count, okhttp3.Callback callback);

        void getHotProductData(int count, okhttp3.Callback callback);

        void cancelTask();
    }

    interface View {
        void initView(android.view.View view);

        void showBanner(ArrayList<Banner.BannerData> bannerData);

        void showRecommendSection(ArrayList<ListCommodity.CommodityListModel> recommendData);

        void showNewSection(ArrayList<ListCommodity.CommodityListModel> newProductData);

        void showHotSection(ArrayList<ListCommodity.CommodityListModel> hotProductData);

        void showLoading();

        void hideLoading();

        void showLoadMore();

        void hideLoadMore(boolean isCompeted, boolean isEnd);

        void showToast(Integer resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView();

        void loadMoreDataToView();

        void reloadDataToView();

        void destroy();
    }
}
