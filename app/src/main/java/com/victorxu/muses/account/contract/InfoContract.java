package com.victorxu.muses.account.contract;

import com.victorxu.muses.gson.UserInfo;

public interface InfoContract {
    interface Model {
        void getUserInfoData(okhttp3.Callback callback);
        void removeLocalUserData();
    }

    interface View {
        void initRootView(android.view.View view);
        void showUserInfo(UserInfo.UserInfoBean data);
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
        void quit();
    }
}
