package com.victorxu.muses.mine.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.util.GlideCacheUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

public class SettingFragment extends BaseSwipeBackFragment {

    private Toolbar mToolbarSetting;
    private View mViewCleanCache;
    private AppCompatTextView mTextCacheSize;

    private GlideCacheUtil util;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initRootView(view);
        return attachToSwipeBack(view);
    }

    private void initRootView(View view) {
        mToolbarSetting = view.findViewById(R.id.setting_toolbar);
        mViewCleanCache = view.findViewById(R.id.setting_clean_cache);
        mTextCacheSize = view.findViewById(R.id.setting_clean_cache_size);

        mToolbarSetting.setNavigationOnClickListener((v) -> mActivity.onBackPressed());

        util = GlideCacheUtil.getInstance();
        mTextCacheSize.setText(util.getCacheSize(mActivity));
        mViewCleanCache.setOnClickListener(v -> showCacheDialog());
    }

    private void showCacheDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View dialogView = layoutInflater.inflate(R.layout.dialog_base, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setView(dialogView);

        AppCompatTextView txtTitle = dialogView.findViewById(R.id.dialog_title);
        AppCompatTextView txtConfirm = dialogView.findViewById(R.id.dialog_confirm);
        AppCompatTextView txtCancel = dialogView.findViewById(R.id.dialog_cancel);

        builder.setCancelable(true);
        txtTitle.setText(R.string.sure_to_clean_cache);

        AlertDialog alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        //这一句消除白块
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangle_all_bg, null));

        txtConfirm.setOnClickListener(v -> {
            post(() -> {
                util.clearImageDiskCache(mActivity);
                mTextCacheSize.setText(R.string.zero_byte);
            });
            alertDialog.dismiss();
        });
        txtCancel.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.setting_toolbar;
    }
}
