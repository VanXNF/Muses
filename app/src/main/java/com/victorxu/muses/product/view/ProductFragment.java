package com.victorxu.muses.product.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.custom.AdvancedBottomSheetDialog;
import com.victorxu.muses.custom.GradationScrollView;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.contract.ProductContract;
import com.victorxu.muses.product.presenter.ProductPresenter;
import com.victorxu.muses.product.view.adapter.PromotionAdapter;
import com.victorxu.muses.product.view.adapter.StyleSelectAdapter;
import com.victorxu.muses.product.view.entity.PromotionItem;
import com.victorxu.muses.product.view.entity.StyleSelectItem;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductFragment extends BaseSwipeBackFragment implements ProductContract.View {

    private static final String TAG = "ProductFragment";

    private View mToolBar;
    private AppCompatImageView mProductBack;
    private AppCompatImageView mProductShare;
    private TabLayout mTabLayout;
    private GradationScrollView mScrollView;
    private Banner mBanner;
    private AppCompatTextView mProductTitle;
    private AppCompatTextView mProductBrief;
    private AppCompatTextView mDiscountPrice;
    private AppCompatTextView mOriginPrice;
    private RecyclerView mPromotionRecycler;
    private View mAttrView;
    private AdvancedBottomSheetDialog mAttrDialog;
    private View mStyleView;
    private BottomSheetDialog mStyleDialog;
    private AppCompatImageView mStylePreviewImage;
    private AppCompatTextView mStylePreviewPriceText;
    private RecyclerView mStyleRecycler;
    private StyleSelectAdapter mStyleAdapter;
    private AppCompatImageView mEvaluationUserAvatar;
    private AppCompatTextView mEvaluationUserName;
    private AppCompatTextView mEvaluationDate;
    private AppCompatTextView mEvaluationContent;
    private AppCompatTextView mEvaluationOrderInfo;
    private View mSeeMoreEvaluationView;
    private View mSideDetail;
    private View mSideEvaluation;
    private WebView mWebView;
    private ProductPresenter mPresenter;

    private int id;
    private boolean isUp = true;
    private List<String> mBannerData = new ArrayList<>();
    private Commodity.CommodityDetail mCommodityData;
    private List<PageComment.PageCommentData.CommentModel> mCommentData = new ArrayList<>();
    private List<StyleSelectItem> mStyleSelectData = new ArrayList<>();

    public static ProductFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("ID");
        } else {
            id = 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        mPresenter = new ProductPresenter(this, id);
        mPresenter.loadRootView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPresenter.loadDataToView();
    }

    @Override
    public void initRootView(View view) {
        mToolBar = view.findViewById(R.id.product_page_bar);
        mProductBack = view.findViewById(R.id.product_back);
        mProductShare = view.findViewById(R.id.product_share);
        mTabLayout = view.findViewById(R.id.product_tab_layout);
        mBanner = view.findViewById(R.id.product_banner);
        mProductTitle = view.findViewById(R.id.product_title);
        mProductBrief = view.findViewById(R.id.product_brief);
        mDiscountPrice = view.findViewById(R.id.product_price);
        mOriginPrice = view.findViewById(R.id.product_price_origin);
        mPromotionRecycler = view.findViewById(R.id.product_promotion_recycler_view);
        mAttrView = view.findViewById(R.id.product_text_attr);
        mStyleView = view.findViewById(R.id.product_text_style);
        mEvaluationUserAvatar = view.findViewById(R.id.product_evaluation_image_avatar);
        mEvaluationUserName = view.findViewById(R.id.product_evaluation_text_username);
        mEvaluationDate = view.findViewById(R.id.product_evaluation_text_comment_date);
        mEvaluationContent = view.findViewById(R.id.product_evaluation_text_comment);
        mEvaluationOrderInfo = view.findViewById(R.id.product_evaluation_text_order_info);
        mSeeMoreEvaluationView = view.findViewById(R.id.product_evaluation_see_more_reviews);
        mSideDetail = view.findViewById(R.id.product_side_detail);
        mSideEvaluation = view.findViewById(R.id.product_side_evaluation);
        mWebView = view.findViewById(R.id.product_web_detail);
        mScrollView = view.findViewById(R.id.product_scrollview);

        mToolBar.setBackgroundColor(Color.argb(0, 255, 255, 255));
        mProductBack.setOnClickListener((v -> mActivity.onBackPressed()));

        mTabLayout.setAlpha(0);
        setTabClickListener();

        mOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        // TODO: 2019/2/4 add Bottomsheet
        mSeeMoreEvaluationView.setOnClickListener((v) -> {
            start(ProductCommentFragment.newInstance());
        });
        mStyleDialog = new AdvancedBottomSheetDialog(mActivity, 0.8f, 0.8f);

//        View view1 = getLayoutInflater().inflate(R.layout.bottom_dialog_attribute, null);
        View styleView = getLayoutInflater().inflate(R.layout.bottom_dialog_style, null);
        mStylePreviewImage = styleView.findViewById(R.id.bottom_sheet_product_preview_image);
        mStylePreviewPriceText = styleView.findViewById(R.id.bottom_sheet_product_preview_price);
        mStyleRecycler = styleView.findViewById(R.id.bottom_sheet_product_style_recycler_view);
        mStyleRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mStyleAdapter = new StyleSelectAdapter(mStyleSelectData);
        mStyleAdapter.setOnTagItemClickListener((int index, Commodity.CommodityDetail.AttributesBean.ParametersBean parameter) -> {
            if (parameter.getImage() != null) {
                post(() -> GlideApp.with(mActivity).load(parameter.getImage()).apply(RequestOptions.centerCropTransform()).into(mStylePreviewImage));
            }
        });
        mStyleRecycler.setAdapter(mStyleAdapter);
        mStyleDialog.setContentView(styleView);
        mAttrView.setOnClickListener((v) -> {
//            mAttrDialog.show();
        });
        mStyleView.setOnClickListener((v) -> {
            mStyleDialog.show();
        });


        mPromotionRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        ArrayList<PromotionItem> promotionItems = new ArrayList<>();
        promotionItems.add(new PromotionItem("全场满188包邮", false));
        PromotionAdapter mPromotionAdapter = new PromotionAdapter(promotionItems);
        mPromotionRecycler.setAdapter(mPromotionAdapter);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        setScrollViewListener();

    }

    @Override
    public void showBaseInfo(Commodity.CommodityDetail data) {
        mCommodityData = data;
        post(() -> {
            mProductTitle.setText(mCommodityData.getName());
            mProductBrief.setText(mCommodityData.getBrief());
            mDiscountPrice.setText(String.valueOf(mCommodityData.getDiscountPrice()));
            mOriginPrice.setText(String.valueOf(mCommodityData.getOriginalPrice()));
            if (mCommodityData.getDiscountPrice() == mCommodityData.getOriginalPrice()) {
                mOriginPrice.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showBanner(List<String> imageUrls) {
        mBannerData.clear();
        mBannerData.addAll(imageUrls);
        post(() ->
                mBanner.setImages(mBannerData)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                GlideApp.with(context)
                                        .load(path)
                                        .into(imageView);
                            }
                        })
                        .isAutoPlay(false)
                        .setIndicatorGravity(BannerConfig.RIGHT)
                        .start()
        );
    }

    @Override
    public void showProductDetail(String htmlData) {
        post(() -> mWebView.loadData(htmlData, "text/html", "UTF-8"));
    }

    @Override
    public void showEvaluation(List<PageComment.PageCommentData.CommentModel> commentData) {
        mCommentData.clear();
        mCommentData.addAll(commentData);
        PageComment.PageCommentData.CommentModel model = mCommentData.get((int) (Math.random() * mCommentData.size()));
        post(() -> {
            GlideApp.with(mActivity)
                    .load(model.getHead())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mEvaluationUserAvatar);
            mEvaluationUserName.setText(model.getUsername());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            mEvaluationDate.setText(sdf.format(new Date(model.getDate())));
            mEvaluationContent.setText(model.getContent());
            mEvaluationOrderInfo.setText(model.getCommodityInfo().split(" ")[0]);
        });
    }

    @Override
    public void showAttributeBottomSheet() {

    }

    @Override
    public void showStyleBottomSheet(List<StyleSelectItem> data) {
        mStyleSelectData.clear();
        mStyleSelectData = data;
        mStyleRecycler.post(() -> {
            if (mCommodityData != null) {
                GlideApp.with(mActivity)
                        .load(mCommodityData.getCoverImage())
                        .apply(RequestOptions.centerCropTransform())
                        .into(mStylePreviewImage);
                mStylePreviewPriceText.setText(String.valueOf(mCommodityData.getDiscountPrice()));
            }
            mStyleAdapter.setNewData(mStyleSelectData);
            mStyleAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.product_page_bar;
    }

    private void setScrollViewListener() {
        mScrollView.setScrollViewListener((GradationScrollView scrollView, int x, int y, int oldX, int oldY) -> {
            if (y <= 0) {   //设置标题的背景颜色
                mToolBar.setBackgroundColor(Color.argb(0, 255, 255, 255));
                mTabLayout.setAlpha(0);
                mProductBack.setAlpha(1.0f);
                mProductShare.setAlpha(1.0f);
                mProductBack.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.white)));
                mProductShare.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.white)));
                mProductBack.setBackgroundResource(R.drawable.icon_bg);
                mProductShare.setBackgroundResource(R.drawable.icon_bg);
            } else if (y <= mBanner.getHeight() - mToolBar.getHeight()) {
                if (y <= (mBanner.getHeight() - mToolBar.getHeight()) / 2.0) {
                    float scaleIcon = 2.0f * y / (mBanner.getHeight() - mToolBar.getHeight());
                    mProductBack.setAlpha(1.0f - scaleIcon);
                    mProductShare.setAlpha(1.0f - scaleIcon);
                    if (scaleIcon > 0.9f) {
                        if (!isUp) {
                            mProductBack.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.white)));
                            mProductShare.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.white)));
                            mProductBack.setBackgroundResource(R.drawable.icon_bg);
                            mProductShare.setBackgroundResource(R.drawable.icon_bg);
                            isUp = true;
                        }
                    }
                } else {
                    float scaleIcon = 2.0f * ((float) y / (mBanner.getHeight() - mToolBar.getHeight()) - 0.5f);
                    mProductBack.setAlpha(scaleIcon);
                    mProductShare.setAlpha(scaleIcon);
                    if (scaleIcon < 0.1f) {
                        if (isUp) {
                            mProductBack.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.black)));
                            mProductShare.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.black)));
                            mProductBack.setBackgroundResource(R.color.background_transparent);
                            mProductShare.setBackgroundResource(R.color.background_transparent);
                            isUp = false;
                        }
                    }
                }
                float scale = (float) y / (mBanner.getHeight() - mToolBar.getHeight());
                mTabLayout.setAlpha(scale);
                float alpha = (255 * scale);
                mToolBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            } else {
                mProductBack.setAlpha(1.0f);
                mProductShare.setAlpha(1.0f);
                mProductBack.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.black)));
                mProductShare.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.black)));
                mProductBack.setBackgroundResource(R.color.background_transparent);
                mProductShare.setBackgroundResource(R.color.background_transparent);
                mToolBar.setBackgroundColor(Color.argb(255, 255, 255, 255));
            }
//            根据位置自动改变 tab 选中状态
            if (y < mSideEvaluation.getTop() - mToolBar.getHeight()) {
                if (mTabLayout.getSelectedTabPosition() != 0) {
                    mTabLayout.getTabAt(0).select();
                }
            } else if (y >= mSideEvaluation.getTop() - mToolBar.getHeight() && y < mSideDetail.getTop() - mToolBar.getHeight()) {
                if (mTabLayout.getSelectedTabPosition() != 1) {
                    mTabLayout.getTabAt(1).select();
                }
            } else if (y >= mSideDetail.getTop() - mToolBar.getHeight()) {
                if (mTabLayout.getSelectedTabPosition() != 2) {
                    mTabLayout.getTabAt(2).select();
                }
            }
        });
    }

    private void setTabClickListener() {
        List<Integer> tabTitles = new ArrayList<>();
        tabTitles.add(R.string.product);
        tabTitles.add(R.string.comment);
        tabTitles.add(R.string.detail);

        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                TextView textView = new TextView(mActivity);
                textView.setText(tabTitles.get(i));
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                textView.setTextColor(getResources().getColor(R.color.black));
                tab.setCustomView(textView);
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                    tabView.setOnClickListener((v) -> {
                        int position = (int) tabView.getTag();
                        switch (position) {
                            case 0:
                                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                                break;
                            case 1:
                                mScrollView.smoothScrollTo(0, mSideEvaluation.getTop() - mToolBar.getHeight());
                                break;
                            case 2:
                                mScrollView.smoothScrollTo(0, mSideDetail.getTop() - mToolBar.getHeight());
                                break;
                        }
                    });
                }
            }
        }
    }

    private List<Integer> initDefaultBannerData() {
        List<Integer> mDefaultBannerData = new ArrayList<>();
        mDefaultBannerData.add(R.drawable.test_index_1);
        mDefaultBannerData.add(R.drawable.banner_guide);
        mDefaultBannerData.add(R.drawable.banner_dew);
        mDefaultBannerData.add(R.drawable.banner_cubism);
        mDefaultBannerData.add(R.drawable.banner_institute);
        return mDefaultBannerData;
    }
}
