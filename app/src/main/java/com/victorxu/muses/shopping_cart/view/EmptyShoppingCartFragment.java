package com.victorxu.muses.shopping_cart.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.core.view.MainFragment;
import com.victorxu.muses.custom.bottom_bar.TabSelectedEvent;
import com.victorxu.muses.shopping_cart.presenter.ShoppingCartContainerPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class EmptyShoppingCartFragment extends BaseFragment {

    private ShoppingCartContainerFragment mHomeFragment;
    private AppCompatButton mGoButton;
    private TwinklingRefreshLayout mRefreshLayout;

    public static EmptyShoppingCartFragment newInstance() {
        Bundle args = new Bundle();
        EmptyShoppingCartFragment fragment = new EmptyShoppingCartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart_empty, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mGoButton = view.findViewById(R.id.empty_cart_button_go);
        mRefreshLayout = view.findViewById(R.id.empty_cart_refresh_layout);
        mHomeFragment = (ShoppingCartContainerFragment) getParentFragment();
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                if (!mHomeFragment.getmPresenter().getCartStatus()) {
                    mHomeFragment.showCartFragment();
                    mHomeFragment.findChildFragment(ShoppingCartFragment.class).getmPresenter().refreshProduct();
                }
                mRefreshLayout.finishRefreshing();
            }
        });
        mRefreshLayout.setEnableLoadmore(false);

        mGoButton.setOnClickListener((v)->{
            ((MainFragment) mHomeFragment.getParentFragment()).getmBottomBar().setCurrentItem(0);
        });
    }
}
