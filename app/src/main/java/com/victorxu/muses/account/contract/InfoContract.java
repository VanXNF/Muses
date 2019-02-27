package com.victorxu.muses.account.contract;

import com.victorxu.muses.gson.UserInfo;

public interface InfoContract {
    interface Model {
        void getUserInfoData(okhttp3.Callback callback);
        void updateUserInfo(okhttp3.Callback callback);
        void updateUserPassword(String oldPassword, String newPassword, okhttp3.Callback callback);
        void removeLocalUserData();
        void updateCacheData();
        void setLocalUserInfoData(UserInfo.UserInfoBean data);
        UserInfo.UserInfoBean getLocalUserInfoData();
    }

    interface View {
        void initRootView(android.view.View view);
        void showUserInfo(UserInfo.UserInfoBean data);
        void showNickname(String nickname);
        void showBirthday(long timestamp);
        void showGender(int gender);
        void showEmail(String email);
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
        void updatePassword(String oldPassword, String newPassword);
        void updateNickname(String name);
        void updateBirthday(long timestamp);
        void updateGender(int gender);
        void updateEmail(String email);
        void quit();
    }
}
