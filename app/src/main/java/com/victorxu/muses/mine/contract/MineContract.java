package com.victorxu.muses.mine.contract;

import com.victorxu.muses.gson.UserStatus;

public interface MineContract {
    interface Model {
        UserStatus.UserBean getUserData();
        boolean checkUserStatus();
    }

    interface View {
        void initRootView(android.view.View view);
        void showBaseUserInfo(UserStatus.UserBean data);
        void goToLoginPage();
        void goToProfilePage();
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
        void goToAccount();
    }
}
