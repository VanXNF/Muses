package com.victorxu.muses.creation.contract;

import android.graphics.Bitmap;
import android.net.Uri;

public interface FilterApplyContract {
    interface Model {
        void uploadImageData(Uri uri, okhttp3.Callback callback);

        void uploadCommodityImageData(Uri uri, okhttp3.Callback callback);

        String getFilterUrl();

        void setFilterUrl(String url);
    }

    interface View {
        void initRootView(android.view.View view);

        void initListener();

        void showFilterImage(String url);

        void showCustomizePage(int id);

        void saveFilterImage();

        Uri saveTempImage(String filename, Bitmap bitmap);

        void deleteTempImage(Uri uri);

        void showLoading();

        void showLoadingDialog();

        void hideLoading();

        void hideLoadingDialog();

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void setListener();

        void saveData();

        void uploadData(Uri uri);

        void uploadArtData(Bitmap bitmap);
    }
}
