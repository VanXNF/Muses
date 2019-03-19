package com.victorxu.muses.search.contract;

import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.gson.HotKey;

import java.util.List;

public interface SearchContract {
    interface Model {
        void getHotKeyData(okhttp3.Callback callback);

        List<HistoryKey> getHistoryKeyData();

        void addHistoryKeyData(String name);

        void removeHistoryKeyData(String key);

        void clearAllHistoryKeyData();

        void cancelTask();
    }

    interface View {
        void initView(android.view.View view);

        void showHotKey(List<HotKey.Key> hotKeys);

        void hideHotKey();

        void showHistoryKey(List<HistoryKey> historyKeys);

        void hideHistoryKey();

        void showLoading();

        void hideLoading();

        void showToast(Integer resId);

        void showToast(CharSequence text);

        void goToSearch(String key);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView();

        void deleteHistoryData(String keyword);

        void deleteAllHistoryData();

        void reloadDataToView();

        void reloadHistoryDataToView();

        void goToSearch(String key);

        void destroy();
    }
}
