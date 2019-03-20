package com.victorxu.muses.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.mine.view.adapter.OrderPagerFragmentAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class OrderFragment extends BaseFragment {

    private int position;
    private Toolbar mToolbar;
    private TabLayout mTabLayoutOrder;
    private ViewPager mPagerOrder;

    public static OrderFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("POSITION", position);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("POSITION");
        } else {
            position = 0;
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initListener();
    }

    private void initListener() {
        mToolbar.setNavigationOnClickListener(v -> mActivity.onBackPressed());
        mToolbar.inflateMenu(R.menu.menu_order);
        mToolbar.setOnMenuItemClickListener((MenuItem item) -> {
            if (item.getItemId() == R.id.menu_order_search) {
                // TODO: 2019/3/10 jump to search order
                showToast("敬请期待");
            }
            return false;
        });
        for (int i = 0; i < 5; i++) {
            mTabLayoutOrder.addTab(mTabLayoutOrder.newTab());
        }
        mPagerOrder.setOffscreenPageLimit(1);
        mPagerOrder.setAdapter(new OrderPagerFragmentAdapter(getChildFragmentManager(),
                getString(R.string.all_orders),
                getString(R.string.pending_payment),
                getString(R.string.to_be_delivered),
                getString(R.string.pending_receipt),
                getString(R.string.waiting_for_evaluation)));

        mTabLayoutOrder.setupWithViewPager(mPagerOrder);

        if (position < 0 || position > 4) {
            position = 0;
        }
        mTabLayoutOrder.getTabAt(position).select();
    }

    private void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.order_toolbar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        initRootView(view);
        return view;
    }

    private void initRootView(View view) {
        mToolbar = view.findViewById(R.id.order_toolbar);
        mTabLayoutOrder = view.findViewById(R.id.order_tab_layout);
        mPagerOrder = view.findViewById(R.id.order_view_pager);
    }
}
