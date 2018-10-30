package com.victorxu.muses.shopping_cart.view;

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
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;
import com.victorxu.muses.shopping_cart.presenter.ShoppingCartPresenter;
import com.victorxu.muses.shopping_cart.view.adapter.ShoppingCartAdapter;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ShoppingCartFragment extends BaseMainFragment implements ShoppingCartContract.View {

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
        LinearLayout normalModeContainer = view.findViewById(R.id.cart_normal_mode_container);
        LinearLayout editModeContainer = view.findViewById(R.id.cart_edit_mode_container);
        mCartModeToggle.setOnClickListener((v) -> {
            if (mCartMode) {
                for (ShoppingCartProduct product : mData) {
                    product.setEditedMode(false);
                }
                view.post(() -> {
                    mCartModeToggle.setText(R.string.edit);
                    editModeContainer.setVisibility(View.GONE);
                    normalModeContainer.setVisibility(View.VISIBLE);
                    mAdapter.setNewData(mData);
                    mAdapter.notifyDataSetChanged();
                });
                mCartMode = false;
            } else {
                for (ShoppingCartProduct product : mData) {
                    product.setEditedMode(true);
                }
                view.post(() -> {
                    mCartModeToggle.setText(R.string.save);
                    normalModeContainer.setVisibility(View.GONE);
                    editModeContainer.setVisibility(View.VISIBLE);
                    mAdapter.setNewData(mData);
                    mAdapter.notifyDataSetChanged();
                });
                mCartMode = true;
            }
        });
        mPresenter = new ShoppingCartPresenter(this);
        mPresenter.loadData();

        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                mPresenter.loadData();
                mRefreshLayout.finishRefreshing();
            }
        });
        mRefreshLayout.setEnableLoadmore(false);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                boolean isNeedUpdateData = false;
                int id = view.getId();
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
                }
                if (isNeedUpdateData) {
                    mRecycler.post(()->{
                        double totalPrice = 0;
                        for (ShoppingCartProduct product : mData) {
                            if (product.isChecked()) {
                                totalPrice += Double.parseDouble(product.getPrice()) * product.getNumber();
                            }
                        }
                        mPrice.setText(String.valueOf(totalPrice));
                        mAdapter.setNewData(mData);
                        mAdapter.notifyDataSetChanged();
                    });
                }

            }
        });
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {

            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
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
            }
        };
        mRecycler.setSwipeMenuCreator(mSwipeMenuCreator);
        mRecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition();
                int menuPosition = menuBridge.getPosition();
                if (menuPosition == 1) {
                    if (mData.get(adapterPosition).isChecked()) {
                        double currentPrice = Double.parseDouble(mPrice.getText().toString());
                        if (currentPrice != 0) {
                            currentPrice -= Double.parseDouble(mData.get(adapterPosition).getPrice()) * mData.get(adapterPosition).getNumber();
                        }
                        mPrice.setText(String.valueOf(currentPrice));
                    }
                    mData.remove(adapterPosition);
                }
                post(()->{
                    mAdapter.setNewData(mData);
                    mAdapter.notifyDataSetChanged();
                });
            }
        });
        mRecycler.setAdapter(mAdapter);
//        mRecycler.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.light_gray)));
        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));


        mCheckAll.setOnClickListener((v) ->
            post(()->{
                double totalPrice = 0;
                for (ShoppingCartProduct product : mData) {
                    product.setChecked(mCheckAll.isChecked());
                    if (product.isChecked()) {
                        totalPrice += Double.parseDouble(product.getPrice()) * product.getNumber();
                    }
                }
                mPrice.setText(String.valueOf(totalPrice));
                mAdapter.setNewData(mData);
                mAdapter.notifyDataSetChanged();
            })
        );

        mDeleteButton.setOnClickListener((v)->{
            List<ShoppingCartProduct> newData = new ArrayList<>();
            for (ShoppingCartProduct product : mData) {
                if (!product.isChecked()) {
                    newData.add(product);
                }
            }
            mData.clear();
            mData.addAll(newData);
            mRecycler.post(()->{
                mPrice.setText(getString(R.string.zero));
                mAdapter.setNewData(mData);
                mAdapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    public void refreshCartItem(List<ShoppingCartProduct> data) {
        mData = data;
        if (mAdapter != null) {
            post(()->{
                mAdapter.setNewData(mData);
                mAdapter.notifyDataSetChanged();
            });
        } else {
            mAdapter = new ShoppingCartAdapter(mData);
        }
    }
}
