package com.victorxu.muses.shopping_cart.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.shopping_cart.contract.ShoppingCartContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShoppingCartFragment extends BaseMainFragment implements ShoppingCartContract.View {

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

    }
}
