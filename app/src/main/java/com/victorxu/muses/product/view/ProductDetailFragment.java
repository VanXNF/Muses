package com.victorxu.muses.product.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;



import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;


import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;

import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.search.view.adapter.SearchResultPagerFragmentAdapter;

import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;




public class ProductDetailFragment extends BaseFragment {

    private RelativeLayout mParentToolbar;
    private TabLayout mParentTabLayout;
    private ImmersionBar mImmersionBar;

    public static ProductDetailFragment newInstance() {
        Bundle bundle = new Bundle();

        ProductDetailFragment fragment = new ProductDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mParentToolbar = ((ProductContainerFragment) getParentFragment()).getToolbar();
        mParentToolbar.setBackgroundColor(Color.argb( 255, 255,255,255));
        mParentTabLayout = ((ProductContainerFragment) getParentFragment()).getTabLayout();
        mParentTabLayout.setAlpha(1);
    }

}