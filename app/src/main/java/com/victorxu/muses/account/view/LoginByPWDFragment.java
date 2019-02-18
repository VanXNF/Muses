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
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.core.view.MainFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

public class LoginByPWDFragment extends BaseFragment implements AccountContract.View {

    private AppCompatTextView mTextSkip;
    private AppCompatEditText mEditMobile;
    private AppCompatEditText mEditPassword;
    private AppCompatButton mBtnLogin;
    private AppCompatTextView mTextLogin;
    private AppCompatTextView mTextRegister;
    private AppCompatImageButton mBtnQQ;
    private AppCompatImageButton mBtnWeChat;
    private AppCompatImageButton mBtnWeiBo;

    private AccountContract.Presenter mPresenter;

    private AccountContract.LoginListener loginListener;

    public static LoginByPWDFragment newInstance() {
        return new LoginByPWDFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_password, container, false);
        mPresenter = new AccountPresenter(this, mActivity);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        hideSoftInput();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter = null;
        loginListener = null;
    }

    @Override
    public void initRootView(View view) {
        mTextSkip = view.findViewById(R.id.text_skip);
        mEditMobile = view.findViewById(R.id.edit_login_pwd_phone_number);
        mEditPassword = view.findViewById(R.id.edit_login_pwd_password);
        mBtnLogin = view.findViewById(R.id.button_login_pwd_login);
        mTextLogin = view.findViewById(R.id.text_login_by_verification_code);
        mTextRegister = view.findViewById(R.id.text_register_account);
        mBtnQQ = view.findViewById(R.id.button_qq);
        mBtnWeChat = view.findViewById(R.id.button_wechat);
        mBtnWeiBo = view.findViewById(R.id.button_weibo);

        mTextSkip.setOnClickListener(v -> popTo(MainFragment.class, false));
        mBtnLogin.setOnClickListener(v -> {
            String username = mEditMobile.getText().toString();
            String password = mEditPassword.getText().toString();
            mPresenter.doLoginByPWD(username, password);
        });

        mTextLogin.setOnClickListener(v -> {
            LoginByCodeFragment fragment = LoginByCodeFragment.newInstance();
            fragment.addLoginListener(loginListener);
            startWithPop(fragment);
        });
        mTextRegister.setOnClickListener(v -> {
            RegisterFragment fragment = RegisterFragment.newInstance();
            fragment.addLoginListener(loginListener);
            startWithPop(fragment);
        });
        // TODO: 2019/2/18 接入第三方
        mBtnQQ.setOnClickListener(v -> showToast("敬请期待"));
        mBtnWeChat.setOnClickListener(v -> showToast("敬请期待"));
        mBtnWeiBo.setOnClickListener(v -> showToast("敬请期待"));
    }

    @Override
    public void notifyLoginSuccess() {
        if (loginListener != null) {
            loginListener.onLoginSuccess();
            popTo(MainFragment.class, false);
        } else {
            throw new RuntimeException( " must implement AccountContract.LoginListener");
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

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.login_pwd_page_bar;
    }

    public void addLoginListener(AccountContract.LoginListener loginListener) {
        this.loginListener = loginListener;
    }
}
