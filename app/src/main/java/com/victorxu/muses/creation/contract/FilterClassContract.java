package com.victorxu.muses.creation.contract;

import com.victorxu.muses.gson.PageFilter;

import java.util.List;

public interface FilterClassContract {
    interface Model {
        void getFilterData(okhttp3.Callback callback);
        void getFilterData(int page, okhttp3.Callback callback);
        void getMoreFilterData(okhttp3.Callback callback);
        void setAllPages(int allPages);
        int getCurrentPage();
        int getAllPages();
    }

    interface View {
        void initRootView(android.view.View view);
        void showFilter(List<PageFilter.FilterBean> data);
        void showMoreFilter(List<PageFilter.FilterBean> data);
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
        void loadMoreDataToView();
    }
}
