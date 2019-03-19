package com.victorxu.muses.creation.view;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.creation.contract.FilterCreateContract;
import com.victorxu.muses.creation.presenter.FilterCreatePresenter;
import com.victorxu.muses.custom.bottompicker.BottomPicker;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.util.CropUtil;
import com.victorxu.muses.util.FileUtil;
import com.xw.repo.BubbleSeekBar;
import com.yalantis.ucrop.UCrop;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

public class FilterCreateFragment extends BaseSwipeBackFragment implements FilterCreateContract.View {

    private AppCompatImageView mImgDisplay;
    private AppCompatEditText mEditFilterName;
    private BubbleSeekBar mSeekerBrushSize;
    private BubbleSeekBar mSeekerBrushIntensity;
    private BubbleSeekBar mSeekerSmooth;
    private AppCompatImageView mImgBack;
    private FloatingActionButton mFABtnAction;
    private FilterCreateContract.Presenter mPresenter;

    public static FilterCreateFragment newInstance() {
        return new FilterCreateFragment();
    }

    @Override
    public void initRootView(View view) {
        mImgDisplay = view.findViewById(R.id.create_filter_image_display);
        mEditFilterName = view.findViewById(R.id.create_filter_edit_name);
        mSeekerBrushSize = view.findViewById(R.id.create_filter_brush_size);
        mSeekerBrushIntensity = view.findViewById(R.id.create_filter_brush_intensity);
        mSeekerSmooth = view.findViewById(R.id.create_filter_smooth);
        mImgBack = view.findViewById(R.id.create_filter_image_back);
        mFABtnAction = view.findViewById(R.id.create_filter_float_button_action);
    }

    @Override
    public void initListener() {
        mImgBack.setOnClickListener(v -> {
            hideSoftInput();
            mActivity.onBackPressed();
        });
        mFABtnAction.setOnClickListener(v -> {
            mEditFilterName.clearFocus();
            hideSoftInput();
            mPresenter.uploadFilter(mEditFilterName.getText().toString(),
                    mSeekerBrushSize.getProgress(), mSeekerBrushIntensity.getProgress(),
                    mSeekerSmooth.getProgress());
        });
        mImgDisplay.setOnClickListener(v -> {
            mEditFilterName.clearFocus();
            hideSoftInput();
            showPicker();
        });

    }

    @Override
    public void showImage(String url) {
        post(() -> {
            GlideApp.with(mActivity)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(mImgDisplay);
            mFABtnAction.setImageResource(R.drawable.ic_cloud_upload_white_24dp);
        });
    }

    @Override
    public void showPicker() {
        BottomPicker.with(mActivity)
                .show((Uri uri) -> CropUtil.startUCrop(mActivity, this, uri.getPath(),
                        CropUtil.CROP_CODE, CropUtil.MAX_WIDTH, CropUtil.MAX_HEIGHT));
    }

    @Override
    public void quit() {
        pop();
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
    public void onDestroy() {
        hideSoftInput();
        FileUtil.deleteTempFile();
        super.onDestroy();
        mPresenter.destroy();
        mPresenter = null;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                mPresenter.loadListener();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                showToast(R.string.can_not_use_this_function_without_permission);
                pop();
            }
        };
        TedPermission.with(mActivity)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(getString(R.string.if_you_reject_permission_you_can_not_use_this_service_please_turn_on_permissions_at_Setting_Permission))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.create_filter_page_bar;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropUtil.CROP_CODE && resultCode == RESULT_OK) {
            mPresenter.updateImageUri(UCrop.getOutput(data));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_filter, container, false);
        mPresenter = new FilterCreatePresenter(this, mActivity);
        mPresenter.loadRootView(view);
        return attachToSwipeBack(view);
    }
}
