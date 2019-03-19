package com.victorxu.muses.core;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.MainActivity;
import com.victorxu.muses.R;
import com.victorxu.muses.account.view.LoginByPWDFragment;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.creation.view.CreationFragment;
import com.victorxu.muses.custom.BottomBar;
import com.victorxu.muses.custom.BottomBarTab;
import com.victorxu.muses.custom.BottomTabSelectedEvent;
import com.victorxu.muses.gallery.view.GalleryFragment;
import com.victorxu.muses.mine.view.MineFragment;
import com.victorxu.muses.trade.view.ShoppingCartFragment;
import com.victorxu.muses.util.SharedPreferencesUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;

public class MainFragment extends BaseFragment {

    private static final int REQ_MAIN_FRAGMENT = 10;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FORTH = 3;

    private BaseFragment[] mFragments = new BaseFragment[4];
    private BottomBar mBottomBar;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseFragment firstFragment = findChildFragment(GalleryFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = GalleryFragment.newInstance();
            mFragments[SECOND] = CreationFragment.newInstance();
            mFragments[THIRD] = ShoppingCartFragment.newInstance();
            mFragments[FORTH] = MineFragment.newInstance();

            loadMultipleRootFragment(R.id.tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FORTH]);
        } else {
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(CreationFragment.class);
            mFragments[THIRD] = findChildFragment(ShoppingCartFragment.class);
            mFragments[FORTH] = findChildFragment(MineFragment.class);
        }
        if (TextUtils.isEmpty((String) SharedPreferencesUtil.get(mActivity, "UserToken", "")) ||
                (int) SharedPreferencesUtil.get(mActivity, "UserId", 0) == 0) {
            startToAccountFragment();
        }
    }

    private void initView(View view) {
        mBottomBar = view.findViewById(R.id.bottomBar);

        mBottomBar
                .addItem(new BottomBarTab(mActivity, R.drawable.gallery, getString(R.string.gallery)))
                .addItem(new BottomBarTab(mActivity, R.drawable.creation, getString(R.string.creation)))
                .addItem(new BottomBarTab(mActivity, R.drawable.cart, getString(R.string.shop_cart)))
                .addItem(new BottomBarTab(mActivity, R.drawable.mine, getString(R.string.mine)));

        // 模拟未读消息
//        mBottomBar.getItem(FIRST).setUnreadCount(9);
//
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

//                BottomBarTab tab = mBottomBar.getItem(FIRST);
//                if (position == FIRST) {
//                    tab.setUnreadCount(0);
//                } else {
//                    tab.setUnreadCount(tab.getUnreadCount() + 1);
//                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                EventBusActivityScope.getDefault(mActivity).post(new BottomTabSelectedEvent(position));
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MAIN_FRAGMENT && resultCode == RESULT_OK) {
            // TODO: 18-10-19 获取子fragment结果

        }
    }

    public void startToAccountFragment() {
//        RegisterFragment fragment = RegisterFragment.newInstance();
        LoginByPWDFragment  fragment = LoginByPWDFragment.newInstance();
        fragment.addLoginListener((MainActivity) getActivity());
        startBrotherFragment(fragment);
    }

    public void startBrotherFragment(BaseFragment targetFragment) {
        start(targetFragment);
    }

    public BottomBar getBottomBar() {
        return mBottomBar;
    }
}
