package com.victorxu.muses.core.view;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import com.victorxu.muses.MainFragment;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseActivity;
import com.victorxu.muses.core.contract.MainContract;
import com.victorxu.muses.core.presenter.MainPresenter;


public class MainActivity extends BaseActivity implements MainContract.View {

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.startToMainFunction();
    }

    @Override
    public void loadMainFragment(@NonNull String accountName, @NonNull String securityCode) {
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.frame_container, MainFragment.newInstance());
        }
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
