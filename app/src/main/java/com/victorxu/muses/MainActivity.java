package com.victorxu.muses;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;

import com.victorxu.muses.base.BaseActivity;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.custom.view.BottomBar;
import com.victorxu.muses.custom.view.BottomBarTab;
import com.victorxu.muses.gallery.view.GalleryFragment;
import com.victorxu.muses.gallery.view.SearchFragment;
import com.victorxu.muses.gallery.view.TabSelectedEvent;


public class MainActivity extends BaseActivity implements BaseMainFragment.OnBackToFirstListener {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private BaseFragment[] mFragments = new BaseFragment[4];

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseFragment firstFragment = findFragment(GalleryFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = GalleryFragment.newInstance();
            mFragments[SECOND] = SearchFragment.newInstance();
            mFragments[THIRD] = SearchFragment.newInstance();
            mFragments[FOURTH] = SearchFragment.newInstance();

            loadMultipleRootFragment(R.id.frame_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(SearchFragment.class);
            mFragments[THIRD] = findFragment(SearchFragment.class);
            mFragments[FOURTH] = findFragment(SearchFragment.class);
        }

        initView();
    }

    private void initView() {
        mBottomBar = findViewById(R.id.main_bottom_bar);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.gallery))
                .addItem(new BottomBarTab(this, R.drawable.creation))
                .addItem(new BottomBarTab(this, R.drawable.cart))
                .addItem(new BottomBarTab(this, R.drawable.mine));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                final BaseFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof GalleryFragment) {
                        currentFragment.popToChild(GalleryFragment.class, false);
                    } else if (currentFragment instanceof SearchFragment) {
                        currentFragment.popToChild(SearchFragment.class, false);
                    }
//                    else if (currentFragment instanceof ZhihuThirdFragment) {
//                        currentFragment.popToChild(ShopFragment.class, false);
//                    } else if (currentFragment instanceof ZhihuFourthFragment) {
//                        currentFragment.popToChild(MeFragment.class, false);
//                    }
                    return;
                }


                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
                }
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }


}
