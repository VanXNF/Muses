package com.victorxu.muses.product.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseBackFragment;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;


public class ProductDetailFragment extends BaseBackFragment {

    private Banner mBanner;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private List<Integer> mDefaultBannerData;
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
        Bundle bundle = getArguments();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View titleBar = view.findViewById(R.id.toolbar_product);
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
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mCollapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar_product);
        mToolbar = view.findViewById(R.id.toolbar_product);
        initToolbarNav(mToolbar);
        mBanner = view.findViewById(R.id.banner_product);
        initDefaultBannerData();
        mBanner.setImages(mDefaultBannerData)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        RequestOptions options = RequestOptions
                                .bitmapTransform(new RoundedCorners(
                                        getResources().getInteger(R.integer.banner_image_radius)));
                        Glide.with(context).load(path).into(imageView);
                    }
                })
                .isAutoPlay(false)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start();

    }

    private void initDefaultBannerData() {
        mDefaultBannerData = new ArrayList<>();
        mDefaultBannerData.add(R.drawable.banner_guide);
        mDefaultBannerData.add(R.drawable.banner_dew);
        mDefaultBannerData.add(R.drawable.banner_cubism);
        mDefaultBannerData.add(R.drawable.banner_institute);
    }
}
