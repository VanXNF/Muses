package com.victorxu.muses.creation.contract;

import android.net.Uri;

public interface FilterApplyContract {
    interface Model {
        void uploadImageData(Uri uri, okhttp3.Callback callback);
    }

    interface View {
        void initRootView(android.view.View view);
        void showImage(String url);
        void showLoading();
        void hideLoading();
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
        void uploadData(Uri uri);
    }
}
