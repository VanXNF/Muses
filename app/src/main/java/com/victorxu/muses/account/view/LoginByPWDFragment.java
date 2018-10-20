package com.victorxu.muses.account.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.AccountContract;
import com.victorxu.muses.account.presenter.AccountPresenter;
import com.victorxu.muses.base.BaseSwipeBackFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class LoginByPWDFragment extends BaseSwipeBackFragment implements AccountContract.View {

    private AccountContract.Presenter mPresenter;
    private AppCompatButton mButtonLogin;

    public static LoginByPWDFragment newInstance() {
        Bundle bundle = new Bundle();
//        bundle.putChar();
        LoginByPWDFragment fragment = new LoginByPWDFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_password, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        mPresenter = new AccountPresenter(this);
        mButtonLogin = view.findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
