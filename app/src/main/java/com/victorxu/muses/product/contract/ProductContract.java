package com.victorxu.muses.product.contract;

public interface ProductContract {
    interface Model {
        void getProductData(okhttp3.Callback callback);
        void getCommentData(okhttp3.Callback callback);

    }

    interface View {
        void initRootView(android.view.View view);
        void showBanner();
        void showProductDetail(String htmlData);
        void showComment();
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
    }
}
