package com.victorxu.muses.product.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProductCommentFragment extends BaseFragment {

    private RelativeLayout mParentToolbar;
    private TabLayout mParentTabLayout;
    private ImmersionBar mImmersionBar;

    public static ProductCommentFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductCommentFragment fragment = new ProductCommentFragment();
        fragment.setArguments(bundle);
        return fragment;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_comment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mParentToolbar = ((ProductContainerFragment) getParentFragment()).getToolbar();
        mParentToolbar.setBackgroundColor(Color.argb( 255, 255,255,255));
        mParentTabLayout = ((ProductContainerFragment) getParentFragment()).getTabLayout();
        mParentTabLayout.setAlpha(1);
    }
}
