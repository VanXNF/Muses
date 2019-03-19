package com.victorxu.muses.account.contract;

import com.victorxu.muses.gson.UserStatus;

import okhttp3.Callback;

public interface AccountContract {
    interface LoginListener {
        void onLoginSuccess();
    }

    interface Model {
        void doLoginByPWD(String username, String password, okhttp3.Callback callback);

        void doLoginByCode(String mobile, String code, Callback callback);

        void doRegister(String mobile, String password, String code, okhttp3.Callback callback);

        boolean saveUserInfo(UserStatus.UserBean userBean);

        void cancelTask();
    }

    interface View {
        void initRootView(android.view.View view);

        void notifyLoginSuccess();

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void doLoginByPWD(String username, String password);

        void doLoginByCode(String mobile, String code);

        void doRegister(String mobile, String password, String code);

        void destroy();
    }
}
