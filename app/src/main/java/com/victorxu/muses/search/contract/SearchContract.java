package com.victorxu.muses.search.contract;

import com.victorxu.muses.db.entity.HistoryKey;

import java.util.List;

public interface SearchContract {
    interface Model {
        void getHotKeyData(okhttp3.Callback callback);
        List<HistoryKey> getHistoryKeyData();
        void addHistoryKeyData(String key, String value);
        void removeHistoryKeyData(String key);
        void clearAllHistoryKeyData();
    }

    interface View {
        void initView(android.view.View view);
        void showHotKey();
        void hideHotKey();
        void showHistoryKey();
        void hideHistoryKey();
        void showLoading();
        void hideLoading();
        void showToast(Integer resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
        void loadMoreDataToView();
        void reloadDataToView();
    }
}
