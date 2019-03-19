package com.victorxu.muses;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;


import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.account.contract.AccountContract;
import com.victorxu.muses.core.MainFragment;
import com.victorxu.muses.base.BaseActivity;
import com.victorxu.muses.util.SharedPreferencesUtil;

public class MainActivity extends BaseActivity implements AccountContract.LoginListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImmersionBar.with(this).init();
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.frame_container, MainFragment.newInstance());
        }
    }

    @Override
    public void onLoginSuccess() {
        String userName = (String) SharedPreferencesUtil.get(this, "UserName", "");
        if (!TextUtils.isEmpty(userName)) {
            post(() -> Toast.makeText(this,getText(R.string.welcome) + userName, Toast.LENGTH_SHORT).show());
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
