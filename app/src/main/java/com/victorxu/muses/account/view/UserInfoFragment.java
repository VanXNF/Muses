package com.victorxu.muses.account.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserInfoFragment extends BaseFragment {

    public static UserInfoFragment newInstance() {
        // TODO: 2019/2/21 缺少获取个人信息 与 修改个人信息的 api
        return new UserInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
