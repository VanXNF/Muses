package com.victorxu.muses.creation.contract;

import com.victorxu.muses.gson.PageFilter;
import com.victorxu.muses.gson.UnfinishedFilterStatus;

import java.util.List;

public interface MyFilterContract {
    interface Model {
        void getFilterData(okhttp3.Callback callback);

        void getFilterData(int page, okhttp3.Callback callback);

        void getMoreFilterData(okhttp3.Callback callback);

        void setLocalUnfinishedData(List<UnfinishedFilterStatus.UnfinishedFilterBean> data);

        void setLocalFinishedData(List<PageFilter.FilterBean> data);

        void addLocalUnfinishedData(List<UnfinishedFilterStatus.UnfinishedFilterBean> data);

        void addLocalFinishedData(List<PageFilter.FilterBean> data);

        void setAllPages(int allPages);

        boolean checkDataStatus();

        boolean checkPageStatus();
    }

    interface View {
        void initRootView(android.view.View view);

        void showUnfinishedFilter(List<UnfinishedFilterStatus.UnfinishedFilterBean> data);

        void showFinishedFilter(List<PageFilter.FilterBean> data);

        void showMoreUnfinishedFilter(List<UnfinishedFilterStatus.UnfinishedFilterBean> data);

        void showMoreFinishedFilter(List<PageFilter.FilterBean> data);

        void showLoadMore();

        void hideLoadMore(boolean isCompleted, boolean isEnd);

        void showEmptyPage();

        void hideEmptyPage();

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView(int type);

        void loadMoreDataToView(int type);
    }
}
