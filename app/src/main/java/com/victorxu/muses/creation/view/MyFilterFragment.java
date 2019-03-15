package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MyFilterFragment extends BaseFragment {

    private Toolbar mToolbarMyFilter;
    private TabLayout mTabLayoutMyFilter;
    private ViewPager mPagerMyFilter;

    public static MyFilterFragment newInstance() {
        return new MyFilterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_filter, container, false);
        initRootView(view);
        return view;
    }

    private void initRootView(View view) {
        mToolbarMyFilter = view.findViewById(R.id.my_filter_toolbar);
        mTabLayoutMyFilter = view.findViewById(R.id.my_filter_tab_layout);
        mPagerMyFilter = view.findViewById(R.id.my_filter_view_pager);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initRootListener();
    }

    private void initRootListener() {
        mToolbarMyFilter.setNavigationOnClickListener(v -> mActivity.onBackPressed());
        for (int i = 0; i < 2; i++) {
            mTabLayoutMyFilter.addTab(mTabLayoutMyFilter.newTab());
        }
        mPagerMyFilter.setOffscreenPageLimit(2);
        mPagerMyFilter.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return MyFilterPageFragment.newInstance(MyFilterPageFragment.TYPE_UNFINISHED);
                    case 1:
                        return MyFilterPageFragment.newInstance(MyFilterPageFragment.TYPE_FINISHED);
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.unfinished);
                    case 1:
                        return getString(R.string.finished);
                }
                return null;
            }
        });

        mTabLayoutMyFilter.setupWithViewPager(mPagerMyFilter);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.my_filter_toolbar;
    }
}
