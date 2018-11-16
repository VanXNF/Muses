package com.victorxu.muses.product.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.RelativeLayout;
import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;

import com.victorxu.muses.base.BaseSwipeBackFragment;

import com.victorxu.muses.product.view.adapter.ProductPagerFragmentAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

public class ProductContainerFragment extends BaseSwipeBackFragment  {


    private ViewPager mPager;
    protected RelativeLayout toolbar;
    private TabLayout tabLayout;
    private ImmersionBar mImmersionBar;
    private AppCompatImageView mImageBackButton;
    private AppCompatImageView mImageShareButton;

    public static ProductContainerFragment newInstance() {
        Bundle bundle = new Bundle();

        ProductContainerFragment fragment = new ProductContainerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setParallaxOffset(0.5f);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View titleBar = view.findViewById(R.id.product_container_toolbar);
        if (titleBar != null)
            ImmersionBar.setTitleBar(mActivity, titleBar);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mImmersionBar = ImmersionBar.with(this).transparentStatusBar().fitsSystemWindows(false);
        mImmersionBar.init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_container, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        toolbar = view.findViewById(R.id.product_container_toolbar);
        tabLayout = view.findViewById(R.id.product_container_tab);
        mPager = view.findViewById(R.id.product_container_view_pager);
        mImageBackButton = view.findViewById(R.id.product_container_back);
        mImageShareButton = view.findViewById(R.id.product_container_share);

        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        mPager.setAdapter(new ProductPagerFragmentAdapter(getChildFragmentManager(),
                getString(R.string.product),
                getString(R.string.detail),
                getString(R.string.comment)));
        tabLayout.setupWithViewPager(mPager);

        mImageBackButton.setOnClickListener((v) -> {
            mActivity.onBackPressed();
        });
        
        mImageShareButton.setOnClickListener((v) -> {
            // TODO: 18-11-14 分享功能
        });

    }

    public RelativeLayout getToolbar() {
        return toolbar;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
