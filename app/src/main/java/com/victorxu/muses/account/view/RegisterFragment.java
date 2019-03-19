package com.victorxu.muses.account.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.AccountContract;
import com.victorxu.muses.account.presenter.AccountPresenter;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.core.MainFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

public class RegisterFragment extends BaseMainFragment implements AccountContract.View {

    private AppCompatTextView mTextSkip;
    private AppCompatEditText mEditMobile;
    private AppCompatEditText mEditCode;
    private AppCompatButton mBtnCode;
    private AppCompatEditText mEditPassword;
    private AppCompatButton mBtnRegister;
    private AppCompatTextView mTextLogin;
    private AppCompatImageButton mBtnQQ;
    private AppCompatImageButton mBtnWeChat;
    private AppCompatImageButton mBtnWeiBo;

    private AccountContract.LoginListener loginListener;
    private AccountContract.Presenter mPresenter;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mPresenter = new AccountPresenter(this, mActivity);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter = null;
        loginListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        mPresenter = null;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        hideSoftInput();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.register_page_bar;
    }

    @Override
    public void initRootView(View view) {
        mTextSkip = view.findViewById(R.id.text_skip);
        mEditMobile = view.findViewById(R.id.edit_register_phone_number);
        mEditCode = view.findViewById(R.id.edit_register_verification_code);
        mEditPassword = view.findViewById(R.id.edit_register_password);
        mBtnCode = view.findViewById(R.id.button_register_verification_code);
        mBtnRegister = view.findViewById(R.id.button_register);
        mTextLogin = view.findViewById(R.id.text_register_already_has_account);
//        mBtnQQ = view.findViewById(R.id.button_qq);
//        mBtnWeChat = view.findViewById(R.id.button_wechat);
//        mBtnWeiBo = view.findViewById(R.id.button_weibo);

        mTextSkip.setVisibility(View.GONE);

        mTextSkip.setOnClickListener(v -> popTo(MainFragment.class, false));

        // TODO: 2019/2/18 验证码登录
        mBtnCode.setOnClickListener((v) -> {
            showToast("code 1234");
        });

        mBtnRegister.setOnClickListener((v) -> {
            String mobile = mEditMobile.getText().toString();
            String code = mEditCode.getText().toString();
            String pwd = mEditPassword.getText().toString();
            mPresenter.doRegister(mobile, pwd, code);
        });

        mTextLogin.setOnClickListener((v) -> {
            LoginByPWDFragment fragment = LoginByPWDFragment.newInstance();
            fragment.addLoginListener(loginListener);
            startWithPop(fragment);
        });
    }

    @Override
    public void notifyLoginSuccess() {
        if (loginListener != null) {
            loginListener.onLoginSuccess();
            popTo(MainFragment.class, false);
        } else {
            throw new RuntimeException(" must implement AccountContract.LoginListener");
        }
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    public void addLoginListener(AccountContract.LoginListener loginListener) {
        this.loginListener = loginListener;
    }
}
