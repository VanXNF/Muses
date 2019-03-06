package com.victorxu.muses.creation.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.creation.presenter.FilterApplyPresenter;
import com.victorxu.muses.custom.PinchImageView;
import com.victorxu.muses.custom.bottompicker.BottomPicker;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.util.CropUtil;
import com.victorxu.muses.util.FileUtil;
import com.yalantis.ucrop.UCrop;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


public class FilterApplyFragment extends BaseFragment implements FilterApplyContract.View {

    private PinchImageView mImgDisplay;
    private View mLoadingView;
    private AppCompatTextView mTextCancel;
    private AppCompatTextView mTextChoosePic;
    private AppCompatTextView mTextTweaker;
    private AppCompatTextView mTextExport;
    private View mViewExportSave;
    private BottomPicker.Builder mBottomPicker;
    private FilterApplyPresenter mPresenter;

    private Bitmap mBitmapData;
    private int id;

    public static FilterApplyFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        FilterApplyFragment fragment = new FilterApplyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("ID");
        } else {
            showToast(R.string.data_error_please_try_again);
            pop();
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                mPresenter.setListener();
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
        return R.id.filter_apply_page_bar;
    }

    @Override
    public void initRootView(View view) {
        mImgDisplay = view.findViewById(R.id.filter_apply_display_image);
        mLoadingView = view.findViewById(R.id.filter_apply_loading_view);
        mTextCancel = view.findViewById(R.id.filter_apply_cancel);
        mTextChoosePic = view.findViewById(R.id.filter_apply_choose_pic);
        mTextTweaker = view.findViewById(R.id.filter_apply_tweaker);
        mTextExport = view.findViewById(R.id.filter_apply_export);
    }

    @Override
    public void initListener() {
        mBottomPicker = BottomPicker.with(mActivity);
        mTextCancel.setOnClickListener(v -> {
            FileUtil.deleteTempFile();
            mActivity.onBackPressed();
        });
        mTextChoosePic.setOnClickListener(v ->
                        mBottomPicker.show((Uri uri) -> {
//                    Log.d("PIC", "initRootView: " + uri.toString());
                            CropUtil.startUCrop(mActivity, this, uri.getPath(), CropUtil.CROP_CODE
                                    , CropUtil.MAX_WIDTH, CropUtil.MAX_HEIGHT);
                        })
        );
        mTextTweaker.setOnClickListener(v -> {

        });

        View exportView = getLayoutInflater().inflate(R.layout.filter_apply_bottom_export, null);
        mViewExportSave = exportView.findViewById(R.id.filter_apply_bottom_save);
        mViewExportSave.setOnClickListener(v -> mPresenter.saveData());
        BottomSheetDialog dialog = new BottomSheetDialog(mActivity);
        dialog.setContentView(exportView);
        mTextExport.setOnClickListener(v -> dialog.show());
        mTextChoosePic.callOnClick();
    }

    @Override
    public void showFilterImage(String url) {
        post(() ->
                GlideApp.with(mActivity)
                        .asBitmap()
                        .load(url)
                        .into(new BitmapImageViewTarget(mImgDisplay) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource,
                                                        @Nullable Transition<? super Bitmap> transition) {
                                mBitmapData = resource;
                                mImgDisplay.setImageBitmap(mBitmapData);
                            }
                        })
        );
    }

    @Override
    public void saveFilterImage() {
        FileUtil.saveImageToGallery(mActivity, mBitmapData);
    }

    @Override
    public void showLoading() {
        post(() -> mLoadingView.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideLoading() {
        post(() -> mLoadingView.setVisibility(View.GONE));
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropUtil.CROP_CODE && resultCode == RESULT_OK) {
            Uri resultUri = UCrop.getOutput(data);
            mPresenter.uploadData(resultUri);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_apply, container, false);
        mPresenter = new FilterApplyPresenter(this, id);
        mPresenter.loadRootView(view);
        return view;
    }
}
