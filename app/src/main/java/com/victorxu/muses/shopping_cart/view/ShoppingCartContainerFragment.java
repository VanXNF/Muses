package com.victorxu.muses.shopping_cart.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.shopping_cart.contract.ShoppingCartContainerContract;
import com.victorxu.muses.shopping_cart.presenter.ShoppingCartContainerPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShoppingCartContainerFragment extends BaseMainFragment implements ShoppingCartContainerContract.View {

    private boolean isEmpty = true;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    private ShoppingCartContainerPresenter mPresenter;

    private BaseFragment[] mFragments = new BaseFragment[2];

    public static ShoppingCartContainerFragment newInstance() {
        Bundle args = new Bundle();
        ShoppingCartContainerFragment fragment = new ShoppingCartContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart_container, container, false);
        mPresenter = new ShoppingCartContainerPresenter(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        isEmpty = mPresenter.getCartStatus();
        mPresenter.changeCartViewFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseFragment firstFragment = findChildFragment(ShoppingCartFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = ShoppingCartFragment.newInstance();
            mFragments[SECOND] = EmptyShoppingCartFragment.newInstance();

            loadMultipleRootFragment(R.id.cart_frame_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(EmptyShoppingCartFragment.class);
        }
    }

    @Override
    public void showCartFragment() {
        showHideFragment(mFragments[FIRST], mFragments[SECOND]);
    }

    @Override
    public void showEmptyFragment() {
        showHideFragment(mFragments[SECOND], mFragments[FIRST]);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public ShoppingCartContainerPresenter getmPresenter() {
        return mPresenter;
    }
}
