package com.victorxu.muses.creation.contract;

public interface FilterApplyContract {
    interface Model {

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
    }
}
