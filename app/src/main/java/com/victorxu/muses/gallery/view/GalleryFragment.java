package com.victorxu.muses.gallery.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.victorxu.muses.core.view.MainFragment;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.custom.SearchView;
import com.victorxu.muses.gallery.contract.GalleryContract;
import com.victorxu.muses.gallery.presenter.GalleryPresenter;
import com.victorxu.muses.gallery.view.adapter.RecommendAdapter;
import com.victorxu.muses.gallery.view.entity.ProductItem;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.ListCommodity;
import com.victorxu.muses.message.view.MessageListFragment;
import com.victorxu.muses.product.view.ProductFragment;
import com.victorxu.muses.search.view.SearchFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class GalleryFragment extends BaseMainFragment implements GalleryContract.View {

    private Banner mBanner;
    private RecyclerView mRecommendRecycler;
    private SearchView mSearchView;
    private AppCompatImageButton mImageButton;
    private SmartRefreshLayout mRefreshLayout;

    private AppCompatImageView mTicketMarket;
    private GalleryPresenter mPresenter;
    private RecommendAdapter mRecommendAdapter;

    private ArrayList<ProductItem> mNewProductViews = new ArrayList<>();
    private ArrayList<ProductItem> mHotProductViews = new ArrayList<>();

    private View mFooterView;
    //    data
    private ArrayList<com.victorxu.muses.gson.Banner.BannerData> mBannerData = new ArrayList<>();
    private ArrayList<ListCommodity.CommodityListModel> mRecommendData = new ArrayList<>();
    private ArrayList<ListCommodity.CommodityListModel> mNewProductData = new ArrayList<>();
    private ArrayList<ListCommodity.CommodityListModel> mHotProductData = new ArrayList<>();

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mPresenter = new GalleryPresenter(this);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.loadDataToView();
        }
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.gallery_page_bar;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void initView(View view) {
//        Banner part
        mBanner = view.findViewById(R.id.banner_gallery);

//        Recommend part
        mRecommendRecycler = view.findViewById(R.id.recycler_recommend);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecommendRecycler.setLayoutManager(layoutManager);
        mRecommendAdapter = new RecommendAdapter(mRecommendData);
        mRecommendRecycler.setAdapter(mRecommendAdapter);

//        New Product Part
        mNewProductViews.add(new ProductItem(view.findViewById(R.id.image_new_product_title)));
        mNewProductViews.add(
                new ProductItem(view.findViewById(R.id.image_product_1_image),
                        view.findViewById(R.id.text_product_1_title),
                        view.findViewById(R.id.text_product_1_tag),
                        view.findViewById(R.id.text_product_1_price)));
        mNewProductViews.add(
                new ProductItem(view.findViewById(R.id.image_product_2_image),
                        view.findViewById(R.id.text_product_2_title),
                        view.findViewById(R.id.text_product_2_tag),
                        view.findViewById(R.id.text_product_2_price)));
        mNewProductViews.add(
                new ProductItem(view.findViewById(R.id.image_product_3_image),
                        view.findViewById(R.id.text_product_3_title),
                        view.findViewById(R.id.text_product_3_tag),
                        view.findViewById(R.id.text_product_3_price)));
        mNewProductViews.add(
                new ProductItem(view.findViewById(R.id.image_product_4_image),
                        view.findViewById(R.id.text_product_4_title),
                        view.findViewById(R.id.text_product_4_tag),
                        view.findViewById(R.id.text_product_4_price)));

//        Hot Product Part
        mHotProductViews.add(new ProductItem(view.findViewById(R.id.image_hot_product_title)));
        mHotProductViews.add(
                new ProductItem(view.findViewById(R.id.image_hot_product_1_image),
                        view.findViewById(R.id.text_hot_product_1_title),
                        view.findViewById(R.id.text_hot_product_1_tag),
                        view.findViewById(R.id.text_hot_product_1_price)));
        mHotProductViews.add(
                new ProductItem(view.findViewById(R.id.image_hot_product_2_image),
                        view.findViewById(R.id.text_hot_product_2_title),
                        view.findViewById(R.id.text_hot_product_2_tag),
                        view.findViewById(R.id.text_hot_product_2_price)));
        mHotProductViews.add(
                new ProductItem(view.findViewById(R.id.image_hot_product_3_image),
                        view.findViewById(R.id.text_hot_product_3_title),
                        view.findViewById(R.id.text_hot_product_3_tag),
                        view.findViewById(R.id.text_hot_product_3_price)));
        mHotProductViews.add(
                new ProductItem(view.findViewById(R.id.image_hot_product_4_image),
                        view.findViewById(R.id.text_hot_product_4_title),
                        view.findViewById(R.id.text_hot_product_4_tag),
                        view.findViewById(R.id.text_hot_product_4_price)));

//        Footer part
        mFooterView = view.findViewById(R.id.footer_section);
//        Search part
        mSearchView = view.findViewById(R.id.search_gallery);
        mSearchView.setOnSearchViewClickListener((View v) ->
                ((MainFragment) getParentFragment()).startBrotherFragment(SearchFragment.newInstance())
        );

        mImageButton = view.findViewById(R.id.button_customer_service);
        mImageButton.setOnClickListener((v) ->
                ((MainFragment) getParentFragment()).startBrotherFragment(MessageListFragment.newInstance())
        );

        mRefreshLayout = view.findViewById(R.id.refresh_gallery);
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setOnRefreshListener((@NonNull RefreshLayout refreshLayout) ->
                mPresenter.reloadDataToView()
        );
        mRefreshLayout.setOnLoadMoreListener((@NonNull RefreshLayout refreshLayout) ->
                mPresenter.loadMoreDataToView()
        );

        mTicketMarket = view.findViewById(R.id.classification_ticket_market);
        mTicketMarket.setOnClickListener((v) -> ((MainFragment) getParentFragment()).startBrotherFragment(TicketFragment.newInstance()));
    }

    @Override
    public void showBanner(ArrayList<com.victorxu.muses.gson.Banner.BannerData> bannerData) {
        mBannerData.clear();
        mBannerData.addAll(bannerData);
        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < mBannerData.size(); i++) {
            urls.add(mBannerData.get(i).getImageUrl());
        }
        post(() ->
                mBanner.setImages(urls)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                GlideApp.with(context)
                                        .load(path)
                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(getResources().getInteger(R.integer.banner_image_radius))))
                                        .into(imageView);
                            }
                        }).start()
        );

    }

    @Override
    public void showRecommendSection(ArrayList<ListCommodity.CommodityListModel> recommendData) {
        mRecommendData.clear();
        mRecommendData.addAll(recommendData);
        mRecommendAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) ->
                ((MainFragment) getParentFragment()).startBrotherFragment(ProductFragment.newInstance(mRecommendData.get(position).getId()))
        );
        mRecommendRecycler.post(() -> {
            mRecommendAdapter.setNewData(mRecommendData);
            mRecommendAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showNewSection(ArrayList<ListCommodity.CommodityListModel> newProductData) {
        mNewProductData.clear();
        mNewProductData.addAll(newProductData);
        post(() -> {
            for (int i = 0; i < mNewProductViews.size(); i++) {
                GlideApp.with(this).load(mNewProductData.get(i).getCoverImage()).into(mNewProductViews.get(i).getImageView());
                if (i != 0) {
                    mNewProductViews.get(i).getTitleText().setText(mNewProductData.get(i).getName());
                    mNewProductViews.get(i).getTagText().setText(mNewProductData.get(i).getBrief());
                    mNewProductViews.get(i).getPriceText().setText(String.valueOf(mNewProductData.get(i).getDiscountPrice()));
                }
                int id = mNewProductData.get(i).getId();
                mNewProductViews.get(i).getImageView().setOnClickListener((v) ->
                        ((MainFragment) getParentFragment()).startBrotherFragment(ProductFragment.newInstance(id))
                );
            }
        });

    }

    @Override
    public void showHotSection(ArrayList<ListCommodity.CommodityListModel> hotProductData) {
        mHotProductData.clear();
        mHotProductData.addAll(hotProductData);
        post(() -> {
            for (int i = 0; i < mHotProductViews.size(); i++) {
                GlideApp.with(this).load(mHotProductData.get(i).getCoverImage()).into(mHotProductViews.get(i).getImageView());
                if (i != 0) {
                    mHotProductViews.get(i).getTitleText().setText(mHotProductData.get(i).getName());
                    mHotProductViews.get(i).getTagText().setText(mHotProductData.get(i).getBrief());
                    mHotProductViews.get(i).getPriceText().setText(String.valueOf(mHotProductData.get(i).getDiscountPrice()));
                }
                int id = mHotProductData.get(i).getId();
                mHotProductViews.get(i).getImageView().setOnClickListener((v) ->
                        ((MainFragment) getParentFragment()).startBrotherFragment(ProductFragment.newInstance(id))
                );
            }
        });
    }

    @Override
    public void showLoading() {
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void hideLoading() {
        post(() -> {
            mRefreshLayout.finishRefresh(1000);
            mRefreshLayout.setEnableLoadMore(true);
            mFooterView.setVisibility(View.GONE);
        });
    }

    @Override
    public void showLoadMore() {
        mRefreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void hideLoadMore(boolean isCompeted, boolean isEnd) {
        mRefreshLayout.finishLoadMore(500);
        if (isEnd) {
            mFooterView.setVisibility(View.VISIBLE);
            mRefreshLayout.postDelayed(() -> mRefreshLayout.setEnableLoadMore(false), 500);
        }
    }

    @Override
    public void showToast(Integer resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }
}
