package com.victorxu.muses.shopping_cart.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.core.view.MainFragment;
import com.victorxu.muses.custom.AdvancedBottomSheetDialog;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.product.view.ProductFragment;
import com.victorxu.muses.product.view.adapter.StyleSelectAdapter;
import com.victorxu.muses.product.view.entity.StyleSelectItem;
import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.presenter.ShoppingCartPresenter;
import com.victorxu.muses.shopping_cart.view.adapter.ShoppingCartAdapter;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ShoppingCartFragment extends BaseMainFragment implements ShoppingCartContract.View {

    private boolean cartMode = false;
    private AppCompatTextView mCartModeToggle;
    private SmartRefreshLayout mRefreshLayout;
    private ShoppingCartAdapter mAdapter;
    private CheckBox mCheckAll;
    private AppCompatTextView mTextPrice;
    private AppCompatButton mBtnSettle;
    private AppCompatButton mBtnDelete;
    private AppCompatButton mBtnCollect;
    private SwipeMenuRecyclerView mRecycler;
    private AppCompatButton mBtnEmptyGo;
    private View mNormalModeSettleView;
    private View mEditModeSettleView;
    private View mCartBottomView;
    private View mEmptyView;
    private BottomSheetDialog mStyleDialog;
    private AppCompatImageView mStylePreviewImage;
    private AppCompatTextView mStylePreviewPriceText;
    private AppCompatTextView mStyleTipText;
    private RecyclerView mStyleRecycler;
    private StyleSelectAdapter mStyleAdapter;
    private AppCompatButton mStyleConfirmButton;

    private List<ShoppingCartProduct> mCartData = new ArrayList<>();
    private List<StyleSelectItem> mStyleSelectData = new ArrayList<>();
    private Map<String, Boolean> mSelectFlag = new HashMap<>();
    private Map<String, String> mSelectData = new HashMap<>();
    private int mLastPosition = -1;
    private int mPosition = -1;

    private ShoppingCartPresenter mPresenter;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        mPresenter = new ShoppingCartPresenter(this, mActivity);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPresenter.loadDataToView(false);
    }

    @Override
    public void initRootView(View view) {
        mRecycler = view.findViewById(R.id.cart_recycler_product_cart);
        mRefreshLayout = view.findViewById(R.id.cart_refresh_container);
        mCheckAll = view.findViewById(R.id.cart_check_all);
        mTextPrice = view.findViewById(R.id.cart_text_total_price);
        mBtnSettle = view.findViewById(R.id.cart_button_settle);
        mBtnDelete = view.findViewById(R.id.cart_button_delete);
        mBtnCollect = view.findViewById(R.id.cart_button_collect);
        mBtnEmptyGo = view.findViewById(R.id.empty_cart_button_go);
        mCartModeToggle = view.findViewById(R.id.cart_text_toggle);
        mNormalModeSettleView = view.findViewById(R.id.cart_normal_mode_container);
        mEditModeSettleView = view.findViewById(R.id.cart_edit_mode_container);
        mCartBottomView = view.findViewById(R.id.cart_bottom_container);
        mEmptyView = view.findViewById(R.id.cart_empty_container);

        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setRefreshHeader(new MaterialHeader(mActivity));
        mRefreshLayout.setOnRefreshListener((@NonNull RefreshLayout refreshLayout) -> mPresenter.reloadDataToView(cartMode));

        initBottomSheet();

        mAdapter = new ShoppingCartAdapter(mCartData);
        mAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            int id = v.getId();
            ShoppingCartProduct item = mCartData.get(position);
            switch (id) {
                case R.id.cart_image_add:
                    if (item.getData().getNumber() < 999) {
                        mPresenter.updateData(position, item.getData().getNumber() + 1);
                    }
                    break;
                case R.id.cart_image_remove:
                    if (item.getData().getNumber() > 1) {
                        mPresenter.updateData(position, item.getData().getNumber() - 1);
                    }
                    break;
                case R.id.cart_check_item:
                    mPresenter.updateData(position, !item.isChecked());
                    if (mCheckAll.isChecked()) {
                       mCheckAll.setChecked(false);
                    } else {
                        int count = 0;
                        for (int i = 0; i < mCartData.size(); i++) {
                            if (mCartData.get(i).isChecked()) {
                                count++;
                            }
                        }
                        if (count == mCartData.size()) {
                            mCheckAll.setChecked(true);
                        }
                    }
                    break;
                case R.id.cart_image_item:
                    ((MainFragment) getParentFragment()).startBrotherFragment(ProductFragment.newInstance(item.getData().getCommodityId()));
                    break;
                case R.id.cart_text_product_title:
                    ((MainFragment) getParentFragment()).startBrotherFragment(ProductFragment.newInstance(item.getData().getCommodityId()));
                    break;
                case R.id.cart_attr_container_edit_mode:
                    mLastPosition = mPosition;
                    mPosition = position;
                    if (mLastPosition != mPosition) {
                        mPresenter.loadStyleSelectData(mPosition);
                        mSelectData.clear();
                        mSelectFlag.put("尺寸", false);
                        mSelectFlag.put("颜色分类", false);
                    }
                    mStyleDialog.show();
                    break;
            }
        });

        mRecycler.setSwipeMenuCreator((SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) -> {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            SwipeMenuItem collectItem = new SwipeMenuItem(mActivity)
                    .setText(getResources().getString(R.string.collect))
                    .setHeight(height)
                    .setWidth(width)
                    .setTextColor(getResources().getColor(R.color.white))
                    .setBackgroundColor(getResources().getColor(R.color.dark_gray));
            rightMenu.addMenuItem(collectItem);
            SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity)
                    .setText(getResources().getString(R.string.delete))
                    .setHeight(height)
                    .setWidth(width)
                    .setTextColor(getResources().getColor(R.color.white))
                    .setBackgroundColor(getResources().getColor(R.color.dark_red));
            rightMenu.addMenuItem(deleteItem);
        });
        mRecycler.setSwipeMenuItemClickListener((SwipeMenuBridge menuBridge) -> {
            menuBridge.closeMenu();
            int adapterPosition = menuBridge.getAdapterPosition();
            int menuPosition = menuBridge.getPosition();
            if (menuPosition == 1) {
                mPresenter.removeDataFromView(adapterPosition);
            } else if (menuPosition == 0) {
                mPresenter.collectDataFromView(adapterPosition);
            }
        });
        mRecycler.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.light_white), ViewGroup.LayoutParams.MATCH_PARENT, 5));
        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecycler.setAdapter(mAdapter);

        mRecycler.setAutoLoadMore(false);

        mCartModeToggle.setOnClickListener((v) -> {
            cartMode = !cartMode;
            mPresenter.changeCartMode(cartMode);
        });

        mCheckAll.setOnClickListener((v) -> mPresenter.checkAllData(mCheckAll.isChecked()));
        mBtnDelete.setOnClickListener((v) -> {
            mPresenter.removeDataFromView();
            mCheckAll.setChecked(false);
        });
        mBtnSettle.setOnClickListener((v) -> {
            // TODO: 18-10-31 结算金额
        });
        mBtnCollect.setOnClickListener((v) -> mPresenter.collectDataFromView());

    }

    @Override
    public void showLoading() {
        mRefreshLayout.autoRefreshAnimationOnly();
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void showCartItem(List<ShoppingCartProduct> data) {
        mCartData.clear();
        mCartData.addAll(data);
        post(() -> {
            mAdapter.setNewData(mCartData);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showBottomSheet(List<StyleSelectItem> data) {
        mStyleSelectData.clear();
        mStyleSelectData.addAll(data);
        mStyleRecycler.post(() -> {
            if (mCartData != null) {
                GlideApp.with(mActivity)
                        .load(mCartData.get(mPosition).getData().getCommodity().getCoverImage())
                        .apply(RequestOptions.centerCropTransform())
                        .into(mStylePreviewImage);
                mStylePreviewPriceText.setText(String.valueOf(mCartData.get(mPosition).getData().getCommodity().getDiscountPrice()));
                mStyleTipText.setText("选择 尺寸、颜色分类");
            }
            mStyleAdapter.setNewData(mStyleSelectData);
            mStyleAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showPrice(String price) {
        post(() -> mTextPrice.setText(price));
    }

    @Override
    public void showShoppingCart() {
        post(() -> {
            mCartModeToggle.setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.VISIBLE);
            mCartBottomView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void switchCartMode(boolean isEditMode) {
        post(() -> {
            mCartModeToggle.setText(isEditMode ? R.string.save : R.string.edit);
            mEditModeSettleView.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
            mNormalModeSettleView.setVisibility(isEditMode ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    public void hideShoppingCart() {
        post(() -> {
            mCartModeToggle.setVisibility(View.GONE);
            mRecycler.setVisibility(View.GONE);
            mCartBottomView.setVisibility(View.GONE);
        });
    }

    @Override
    public void showEmptyView() {
        post(() -> mEmptyView.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideEmptyView() {
        post(() -> mEmptyView.setVisibility(View.GONE));
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

    private void initBottomSheet() {
        mStyleDialog = new AdvancedBottomSheetDialog(mActivity, 0.8f, 0.8f);
        View styleView = getLayoutInflater().inflate(R.layout.bottom_dialog_style, null);
        mStylePreviewImage = styleView.findViewById(R.id.bottom_sheet_product_preview_image);
        mStylePreviewPriceText = styleView.findViewById(R.id.bottom_sheet_product_preview_price);
        mStyleTipText = styleView.findViewById(R.id.bottom_sheet_product_preview_select_info);
        mStyleRecycler = styleView.findViewById(R.id.bottom_sheet_product_style_recycler_view);
        mStyleConfirmButton = styleView.findViewById(R.id.bottom_sheet_product_style_confirm);
        mStyleRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mStyleAdapter = new StyleSelectAdapter(mStyleSelectData);
        mStyleAdapter.setOnTagItemClickListener((int index, Commodity.CommodityDetail.AttributesBean.ParametersBean parameter, boolean isSelected) -> {
            String key;
            if (parameter.getImage() != null) {
                key = "颜色分类";
                if (isSelected) {
                    post(() -> GlideApp.with(mActivity)
                            .load(parameter.getImage())
                            .apply(RequestOptions.centerCropTransform())
                            .into(mStylePreviewImage)
                    );
                } else {
                    post(() ->
                            GlideApp.with(mActivity)
                                    .load(mCartData.get(mPosition).getData().getCommodity().getCoverImage())
                                    .apply(RequestOptions.centerCropTransform())
                                    .into(mStylePreviewImage)
                    );
                }
            } else {
                key = "尺寸";
            }
            mSelectFlag.put(key, isSelected);
            if (isSelected) {
                mSelectData.put(key, parameter.getValue());
            } else {
                mSelectData.remove(key);
            }
            if (checkSelectFlag()) {
                mStyleTipText.setText("已选择 " + getSelectDetail());
            } else {
                mStyleTipText.setText("请选择 尺寸、颜色分类");
            }
        });
//        mStyleAdapter.setOnNumberSelectListener((int number) -> mNumber = number);
        mStyleRecycler.setAdapter(mStyleAdapter);
        mStyleDialog.setContentView(styleView);
        mStyleConfirmButton.setOnClickListener((v -> {
            if (checkSelectFlag()) {
//                mPresenter.updateData(mPosition, mNumber);
                mPresenter.updateData(mPosition, getSelectDetail());
                mStyleDialog.cancel();
            } else {
                if (mSelectFlag.get("尺寸")) {
                    showToast("请选择颜色分类");
                } else {
                    showToast("请选择尺寸");
                }
            }
        }));
    }

    private boolean checkSelectFlag() {
        return !mSelectFlag.containsValue(false);
    }

    private String getSelectDetail() {
        String s = mSelectData.toString();
        s = s.substring(s.indexOf('{') + 1, s.lastIndexOf('}'));
        s = s.replace('=', ':');
        s = s.replace(", ", ";");
        return s;
    }
}
