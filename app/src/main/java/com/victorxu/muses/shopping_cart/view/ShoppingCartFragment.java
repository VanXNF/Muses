package com.victorxu.muses.shopping_cart.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.presenter.ShoppingCartPresenter;
import com.victorxu.muses.shopping_cart.view.adapter.ShoppingCartAdapter;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;


public class ShoppingCartFragment extends BaseFragment implements ShoppingCartContract.View {

    private boolean mCartMode = false;
    private AppCompatTextView mCartModeToggle;
    private TwinklingRefreshLayout mRefreshLayout;
    private ShoppingCartAdapter mAdapter;
    private ShoppingCartPresenter mPresenter;
    private CheckBox mCheckAll;
    private AppCompatTextView mPrice;
    private AppCompatButton mSettleButton;
    private AppCompatButton mDeleteButton;
    private AppCompatButton mCollectButton;
    private SwipeMenuRecyclerView mRecycler;
    private LinearLayout mNormalModeContainer;
    private LinearLayout mEditModeContainer;
    private List<ShoppingCartProduct> mData;

    public static ShoppingCartFragment newInstance() {
        Bundle bundle = new Bundle();
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        mPresenter = new ShoppingCartPresenter(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecycler = view.findViewById(R.id.cart_recycler_product_cart);
        mRefreshLayout = view.findViewById(R.id.cart_refresh_container);
        mCheckAll = view.findViewById(R.id.cart_check_all);
        mPrice = view.findViewById(R.id.cart_text_total_price);
        mSettleButton = view.findViewById(R.id.cart_button_settle);
        mDeleteButton = view.findViewById(R.id.cart_button_delete);
        mCollectButton = view.findViewById(R.id.cart_button_collect);
        mCartModeToggle = view.findViewById(R.id.cart_text_toggle);
        mNormalModeContainer = view.findViewById(R.id.cart_normal_mode_container);
        mEditModeContainer = view.findViewById(R.id.cart_edit_mode_container);

        mRefreshLayout.setEnableLoadmore(false);

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
                mData.remove(adapterPosition);
                mPresenter.refreshPrice(mData);
            }
        });
        mRecycler.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.light_white), ViewGroup.LayoutParams.MATCH_PARENT,5));
        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        onLazyInitView(savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mCartModeToggle.setOnClickListener((v) -> {
            mCartMode = !mCartMode;
            mPresenter.changeCartMode(mCartMode);
        });

        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                mPresenter.refreshProduct();
                mPresenter.refreshPrice(mData);
                mCheckAll.setChecked(false);
                mRefreshLayout.finishRefreshing();
            }
        });

        mPresenter.loadProduct();
        mAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            boolean isNeedUpdateData = false;
            int id = v.getId();
            ShoppingCartProduct item = mData.get(position);
            switch (id) {
                case R.id.cart_image_add:
                    item.setNumber(item.getNumber() + 1);
                    isNeedUpdateData = true;
                    break;
                case R.id.cart_image_remove:
                    item.setNumber(item.getNumber() >= 2 ? item.getNumber() - 1 : 1);
                    isNeedUpdateData = true;
                    break;
                case R.id.cart_check_item:
                    item.setChecked(!item.isChecked());
                    isNeedUpdateData = true;
                    break;
                case R.id.cart_image_item:
                    // TODO: 18-10-31 跳转商品详情
                    break;
                case R.id.cart_attr_container_edit_mode:
                    // TODO: 18-10-31 弹出属性选择面板
                    break;
            }
            if (isNeedUpdateData) {
                mPresenter.refreshPrice(mData);
            }
        });
        mRecycler.setAdapter(mAdapter);
        mCheckAll.setOnClickListener((v) -> {
            mPresenter.refreshPrice(mData, mCheckAll.isChecked());
        });
        mDeleteButton.setOnClickListener((v)->{
            mPresenter.removeCheckedProduct(mData);
            mPresenter.refreshPrice(mData);
            mCheckAll.setChecked(false);
        });
        mSettleButton.setOnClickListener((v)->{
            // TODO: 18-10-31 结算金额
        });
        mCollectButton.setOnClickListener((v)->{
            // TODO: 18-10-31 收藏商品
        });
    }

    @Override
    public void loadCartItem(List<ShoppingCartProduct> data) {
        mData = data;
        mAdapter = new ShoppingCartAdapter(mData);
    }

    @Override
    public Context getViewContext() {
        return mActivity;
    }

    @Override
    public void refreshCartTotalPrice(String price) {
        mPrice.setText(price);
        refreshCartItem(mData);
    }

    @Override
    public void refreshCartItem(List<ShoppingCartProduct> data) {
        if (data.size() != 0) {
            mData = data;
            if (mAdapter != null) {
                post(()->{
                    mAdapter.setNewData(mData);
                    mAdapter.notifyDataSetChanged();
                });
            }
        } else {
            ((ShoppingCartContainerFragment) getParentFragment()).showEmptyFragment();
        }

    }

    @Override
    public void changeCartMode(List<ShoppingCartProduct> data) {
        mData = data;
        post(()->{
            mCartModeToggle.setText(mCartMode ? R.string.save : R.string.edit);
            mEditModeContainer.setVisibility(mCartMode ? View.VISIBLE : View.GONE);
            mNormalModeContainer.setVisibility(mCartMode ? View.GONE : View.VISIBLE);
        });
        refreshCartItem(mData);
    }

    public ShoppingCartPresenter getmPresenter() {
        return mPresenter;
    }
}
