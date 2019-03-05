package com.victorxu.muses.creation.contract;

import android.net.Uri;

public interface FilterApplyContract {
    interface Model {
        void uploadImageData(Uri uri, okhttp3.Callback callback);
        void setFilterUrl(String url);
        String getFilterUrl();
    }

    interface View {
        void initRootView(android.view.View view);
        void initListener();
        void showFilterImage(String url);
        void saveFilterImage();
        void showLoading();
        void hideLoading();
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void setListener();
        void saveData();
        void uploadData(Uri uri);
    }
}
