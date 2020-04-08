package com.victorxu.muses.mine.view;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.util.GlideCacheUtil;
import com.victorxu.muses.util.HttpUtil;
import com.victorxu.muses.util.SharedPreferencesUtil;

public class SettingFragment extends BaseSwipeBackFragment {

    private Toolbar mToolbarSetting;
    private View mViewCleanCache;
    private View mViewChangeAddress;
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
        mViewChangeAddress = view.findViewById(R.id.setting_change_address);

        mToolbarSetting.setNavigationOnClickListener((v) -> mActivity.onBackPressed());

        util = GlideCacheUtil.getInstance();
        mTextCacheSize.setText(util.getCacheSize(mActivity));
        mViewCleanCache.setOnClickListener(v -> showCacheDialog());
        mViewChangeAddress.setOnClickListener(v -> showServiceAddressDialog());
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

    private void showServiceAddressDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View dialogView = layoutInflater.inflate(R.layout.dialog_service_address, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setView(dialogView);

        AppCompatEditText etWeb = dialogView.findViewById(R.id.et_web_address_dialog);
        AppCompatEditText etTransfer = dialogView.findViewById(R.id.et_filter_address_dialog);
        AppCompatEditText etTrain = dialogView.findViewById(R.id.et_train_address_dialog);
        AppCompatTextView txtRestore = dialogView.findViewById(R.id.tv_restore_dialog);
        AppCompatTextView txtConfirm = dialogView.findViewById(R.id.tv_confirm_dialog);
        AppCompatTextView txtCancel = dialogView.findViewById(R.id.tv_cancel_dialog);
        builder.setCancelable(true);

        AlertDialog alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        //这一句消除白块
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangle_all_bg, null));
        window.setGravity(Gravity.CENTER);

        etWeb.setText(getAddress("web"));
        etTransfer.setText(getAddress("transfer"));
        etTrain.setText(getAddress("train"));
        txtRestore.setOnClickListener(v -> {
            SharedPreferencesUtil.remove(mActivity, "web");
            SharedPreferencesUtil.remove(mActivity, "transfer");
            SharedPreferencesUtil.remove(mActivity, "train");
            alertDialog.dismiss();
        });
        txtConfirm.setOnClickListener(v -> {
            String web = String.valueOf(etWeb.getText());
            String transfer = String.valueOf(etTransfer.getText());
            String train = String.valueOf(etTrain.getText());
            if (web.isEmpty()) {
                web = HttpUtil.WEB_SERVER;
            }
            if (transfer.isEmpty()) {
                transfer = HttpUtil.FILTER_TRANSFER_SERVER;
            }
            if (train.isEmpty()) {
                train = HttpUtil.FILTER_TRAIN_SERVER;
            }
            SharedPreferencesUtil.put(mActivity, "web", web);
            SharedPreferencesUtil.put(mActivity, "transfer", transfer);
            SharedPreferencesUtil.put(mActivity, "train", train);
            alertDialog.dismiss();
        });
        txtCancel.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }

    private String getAddress(String key) {
        String value = String.valueOf(SharedPreferencesUtil.get(mActivity, key, "null"));
        if (value.equals("null")) {
            switch (key) {
                case "web":
                    value = HttpUtil.WEB_SERVER;
                    break;
                case "transfer":
                    value = HttpUtil.FILTER_TRANSFER_SERVER;
                    break;
                case "train":
                    value = HttpUtil.FILTER_TRAIN_SERVER;
                    break;
            }
        }
        return value;
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
