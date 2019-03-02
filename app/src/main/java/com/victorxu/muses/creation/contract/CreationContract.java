package com.victorxu.muses.creation.contract;

import com.victorxu.muses.gson.FilterClass;

import java.util.List;

public interface CreationContract {
    interface Model {
        void getFilterClassData(okhttp3.Callback callback);
    }

    interface View {
        void initRootView(android.view.View view);
        void showFilterClasses(List<FilterClass.FilterClassBean> data);
        void showPopularSearch();
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
    }
}
