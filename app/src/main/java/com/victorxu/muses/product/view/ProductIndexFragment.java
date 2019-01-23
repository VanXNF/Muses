package com.victorxu.muses.product.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.victorxu.muses.custom.GradationScrollView;
import com.victorxu.muses.product.view.adapter.EvaluationAdapter;
import com.victorxu.muses.product.view.adapter.PromotionAdapter;
import com.victorxu.muses.product.view.entity.EvaluationItem;
import com.victorxu.muses.product.view.entity.PromotionItem;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductIndexFragment extends BaseFragment implements GradationScrollView.ScrollViewListener {

    private RelativeLayout mParentToolbar;
    private TabLayout mParentTabLayout;
    private Banner mBanner;
    private GradationScrollView mScrollView;
    private List<Integer> mDefaultBannerData;
    private AppCompatTextView mTextPrice;
    private AppCompatTextView mTextOriginPrice;
    private ImmersionBar mImmersionBar;
    private RecyclerView mPromotionRecycler;
    private RecyclerView mEvaluationRecycler;
    private PromotionAdapter mPromotionAdapter;
    private EvaluationAdapter mEvaluationAdapter;

    public static ProductIndexFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductIndexFragment fragment = new ProductIndexFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_index, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mParentToolbar = ((ProductContainerFragment) getParentFragment()).getToolbar();
        mParentToolbar.setBackgroundColor(Color.argb( 0, 255,255,255));
        mParentTabLayout = ((ProductContainerFragment) getParentFragment()).getTabLayout();
        mParentTabLayout.setAlpha(0);
        mScrollView = view.findViewById(R.id.product_index_scrollview);
        mScrollView.setScrollViewListener(this);
        mTextPrice = view.findViewById(R.id.product_index_price);
        mTextOriginPrice = view.findViewById(R.id.product_index_price_origin);
        mTextOriginPrice.setPaintFlags(mTextOriginPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mBanner = view.findViewById(R.id.product_index_banner);
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

        mPromotionRecycler = view.findViewById(R.id.product_index_promotion_recycler_view);
        mPromotionRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        ArrayList<PromotionItem> promotionItems = new ArrayList<>();
        promotionItems.add(new PromotionItem("10元现金券满·200减30"));
        promotionItems.add(new PromotionItem("全场满188包邮"));
        mPromotionAdapter = new PromotionAdapter(promotionItems);
        mPromotionRecycler.setAdapter(mPromotionAdapter);

        mEvaluationRecycler = view.findViewById(R.id.product_index_comment_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mEvaluationRecycler.setLayoutManager(linearLayoutManager);
        mEvaluationAdapter = new EvaluationAdapter(initTestEvaluationData());
        mEvaluationRecycler.setAdapter(mEvaluationAdapter);

    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldX, int oldY) {
        if (y <= 0) {   //设置标题的背景颜色
            mParentToolbar.setBackgroundColor(Color.argb( 0, 255,255,255));
            mParentTabLayout.setAlpha(0);
        } else if (y <= mBanner.getHeight() - mParentToolbar.getHeight()) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / (mBanner.getHeight() - mParentToolbar.getHeight());
            mParentTabLayout.setAlpha(scale);
            float alpha = (255 * scale);
            mParentToolbar.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
        } else {    //滑动到banner下面设置普通颜色
            mParentToolbar.setBackgroundColor(Color.argb( 255, 255,255,255));
        }
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

    private void initDefaultBannerData() {
        mDefaultBannerData = new ArrayList<>();
        mDefaultBannerData.add(R.drawable.test_index_1);
        mDefaultBannerData.add(R.drawable.banner_guide);
        mDefaultBannerData.add(R.drawable.banner_dew);
        mDefaultBannerData.add(R.drawable.banner_cubism);
        mDefaultBannerData.add(R.drawable.banner_institute);
    }

    private ArrayList<EvaluationItem> initTestEvaluationData() {
        ArrayList<EvaluationItem> evaluationItems = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://s1.ax1x.com/2018/03/30/9vze8e.jpg");
        urls.add("https://s1.ax1x.com/2018/03/30/9vzmgH.jpg");
        urls.add("https://s1.ax1x.com/2018/03/30/9vzE4O.jpg");
        for (int i = 0; i < 4; i++) {
            evaluationItems.add(new EvaluationItem("https://s1.ax1x.com/2018/03/30/9vxcnI.jpg",
                    "夏朗拿度", "2018-11-15", "51", "99+",
                    "这款耳机主打低音，一直很喜欢索尼耳机的柔和。这款耳机低音不哄耳。振膜很给力，耳机响起来...", urls));
        }
        return evaluationItems;
    }
}
