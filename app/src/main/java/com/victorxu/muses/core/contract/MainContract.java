package com.victorxu.muses.core.contract;

import androidx.annotation.Nullable;

public interface MainContract {
    interface Model {

        boolean isLogin();

        String getAccountName();

        String getSecurityCode();
    }

    interface View {

        void loadMainFragment(@Nullable String accountName, @Nullable String securityCode);


    }

    interface Presenter {

        void startToMainFunction();

    }
}
