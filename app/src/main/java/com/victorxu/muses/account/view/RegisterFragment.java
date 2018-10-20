package com.victorxu.muses.account.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.AccountContract;
import com.victorxu.muses.base.BaseSwipeBackFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RegisterFragment extends BaseSwipeBackFragment {

    private AccountContract.LoginListener mLoginListener;

    public static RegisterFragment newInstance() {
        Bundle bundle = new Bundle();
//        bundle.putChar();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {

    }
}
