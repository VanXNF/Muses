package com.victorxu.muses.mine.contract;

import com.victorxu.muses.gson.Collection;

import java.util.List;

public interface CollectionContract {
    interface Model {
        void getCollectionData(okhttp3.Callback callback);
    }

    interface View {
        void initRootView(android.view.View view);
        void showLoading();
        void hideLoading();
        void showCollection(List<Collection.CollectionBean> data);
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
        void reloadDataToView();
    }
}
