package com.victorxu.muses.product.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;

import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.custom.scroll_view.GradationScrollView;

import com.victorxu.muses.search.view.adapter.SearchResultPagerFragmentAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.viewpager.widget.ViewPager;

public class ProductContainerFragment extends BaseSwipeBackFragment  {

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private RelativeLayout mToolbar;
    private GradationScrollView mScrollView;
    private List<Integer> mDefaultBannerData;
    private ImmersionBar mImmersionBar;

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
        View view = inflater.inflate(R.layout.fragment_product_container, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
//        mScrollView = view.findViewById(R.id.test_scrollview);
//        mScrollView.setScrollViewListener(this);
        mToolbar = view.findViewById(R.id.product_detail_toolbar);
//        mToolbar = view.findViewById(R.id.toolbar_product);
//        initToolbarNav(mToolbar);
        mTabLayout = view.findViewById(R.id.product_detail_tab);
        mTabLayout.setAlpha(0);
        mPager = view.findViewById(R.id.product_detail_view_pager);

        // TODO: 18-11-5 当前用于测试 toolbar 内 tab展示效果
        for (int i = 0; i < 4; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }

        mPager.setAdapter(new SearchResultPagerFragmentAdapter(getChildFragmentManager(),
                getString(R.string.complex),
                getString(R.string.newest),
                getString(R.string.hottest),
                getString(R.string.price)));
        mTabLayout.setupWithViewPager(mPager);
        // TODO: 18-11-6 测试结束 分割线

//        mBanner = view.findViewById(R.id.banner_product);
//        initDefaultBannerData();
//        mBanner.setImages(mDefaultBannerData)
//                .setImageLoader(new ImageLoader() {
//                    @Override
//                    public void displayImage(Context context, Object path, ImageView imageView) {
//                        RequestOptions options = RequestOptions
//                                .bitmapTransform(new RoundedCorners(
//                                        getResources().getInteger(R.integer.banner_image_radius)));
//                        Glide.with(context).load(path).into(imageView);
//                    }
//                })
//                .isAutoPlay(false)
//                .setIndicatorGravity(BannerConfig.RIGHT)
//                .start();

    }

//    private void initToolbarNav(Toolbar toolbar) {
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.inflateMenu(R.menu.product_deatil_menu);
//        toolbar.setOnMenuItemClickListener((MenuItem item) -> {
//            int id = item.getItemId();
//            switch (id) {
//                case R.id.product_detail_button_share:
//                    // TODO: 18-11-5 分享功能
//                    Toast.makeText(mActivity, "分享成功", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            return super.onOptionsItemSelected(item);
//        });
//        toolbar.setNavigationOnClickListener((v)-> {
//            mActivity.onBackPressed();
//        });
//
//    }


//    @Override
//    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldX, int oldY) {
//        if (y <= 0) {   //设置标题的背景颜色
//            mToolbar.setBackgroundColor(Color.argb( 0, 255,255,255));
////            mTabLayout.setBackgroundColor(Color.argb( 0, 144,151,166));
//            mTabLayout.setAlpha(0);
//        } else if (y <= mBanner.getHeight()) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
//            float scale = (float) y / mBanner.getHeight();
//            mTabLayout.setAlpha(scale);
//            float alpha = (255 * scale);
//            mToolbar.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
////            mTabLayout.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
//        } else {    //滑动到banner下面设置普通颜色
//            mToolbar.setBackgroundColor(Color.argb( 255, 255,255,255));
////            mTabLayout.setBackgroundColor(Color.argb( 255, 144,151,166));
//        }
//    }

//    private void initDefaultBannerData() {
//        mDefaultBannerData = new ArrayList<>();
//        mDefaultBannerData.add(R.drawable.banner_guide);
//        mDefaultBannerData.add(R.drawable.banner_dew);
//        mDefaultBannerData.add(R.drawable.banner_cubism);
//        mDefaultBannerData.add(R.drawable.banner_institute);
//    }
}
