package com.victorxu.muses.core.contract;

import com.victorxu.muses.base.BaseFragment;

import androidx.annotation.Nullable;

public interface MainContract {
    interface Model {

        boolean isLogin();

        String getAccountName();

        String getSecurityCode();
    }

    interface View {

        void startToAccountFragment();

        void startBrotherFragment(BaseFragment targetFragment);

    }

    interface Presenter {


    }
}
