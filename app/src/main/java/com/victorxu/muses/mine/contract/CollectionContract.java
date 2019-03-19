package com.victorxu.muses.mine.contract;

import com.victorxu.muses.gson.Collection;

import java.util.List;

public interface CollectionContract {
    interface Model {
        void getCollectionData(okhttp3.Callback callback);

        void removeFromFavorite(int position, okhttp3.Callback callback);

        List<Collection.CollectionBean> getCollectionData();

        //        void removeAllFromFavorite(okhttp3.Callback callback);
        void setCollectionData(List<Collection.CollectionBean> data);

        void cancelTask();
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

        void removeFavorite(int position);
//        void removeAllFavorite();

        void destroy();
    }
}
