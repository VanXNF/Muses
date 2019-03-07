package com.victorxu.muses.creation.contract;

import android.net.Uri;

public interface FilterCreateContract {
    interface Model {
        Uri getFilterUri();

        void setFilterUri(Uri uri);

        void uploadFilter(String filterName, int brushSize, int brushIntensity, int smooth, okhttp3.Callback callback);
    }

    interface View {
        void initRootView(android.view.View view);

        void initListener();

        void showImage(String url);

        void showPicker();

        void quit();

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadListener();

        void updateImageUri(Uri uri);

        void uploadFilter(String filterName, int brushSize, int brushIntensity, int smooth);
    }
}
