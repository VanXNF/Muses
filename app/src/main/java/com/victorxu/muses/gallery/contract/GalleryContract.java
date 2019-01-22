package com.victorxu.muses.gallery.contract;

import com.victorxu.muses.gson.Banner;

import java.util.ArrayList;

public interface GalleryContract {
    interface Model {
        ArrayList<Banner.BannerData> getBannerData();
        void getRecommendData();
        void getNewProductData();
        void getHotProductData();
    }

    interface View {
        void initView(android.view.View view);
        void showBanner();
        void showRecommendSection();
        void showNewSection();
        void showHotSection();
        void showLoading();
        void hideLoading();
        void showLoadMore();
        void hideLoadMore();
        void showErrorPage();
        void hideErrorPage();
    }

    interface Presenter {
        void loadRootView();
        void loadDataToView();
        void refreshDataToView();
    }
}
