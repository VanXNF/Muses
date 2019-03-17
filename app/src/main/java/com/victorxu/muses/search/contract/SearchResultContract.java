package com.victorxu.muses.search.contract;

import com.victorxu.muses.gson.PageCommodity;
import com.victorxu.muses.search.view.entity.ProductItem;

import java.util.List;

public interface SearchResultContract {
    interface Model {

        void getProductData(okhttp3.Callback callback);

        void getProductData(int page, okhttp3.Callback callback);

        void getMoreProductData(okhttp3.Callback callback);

        void setAllPages(int allPages);

        void addPage(PageCommodity page);

        void setPageList(List<PageCommodity> data);

        void setIndex(int index);

        void setKeyword(String keyword);

        boolean checkDataStatus();

        boolean checkPageStatus();
    }

    interface View {
        void initView(android.view.View view);

        void showLoading();

        void showLoadingMore();

        void showProductList(List<PageCommodity.PageBean.CommodityListModel> data);

        void showMoreProduct(List<PageCommodity.PageBean.CommodityListModel> data);

        void hideLoading();

        void hideLoadingMore(boolean isCompeted, boolean isEnd);

        void showToast(Integer resId);

        void showToast(CharSequence text);

        void showEmptyPage();

        void hideEmptyPage();

        void showFailPage();

        void hideFailPage();
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadProductToView();

        void loadMoreProductToView();

        void reloadProductToView();
    }
}
