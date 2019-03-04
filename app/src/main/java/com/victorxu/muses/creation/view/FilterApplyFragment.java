package com.victorxu.muses.creation.view;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
//import com.victorxu.muses.bottomImagepicker.BottomPicker;
import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.creation.presenter.FilterApplyPresenter;
import com.victorxu.muses.custom.PinchImageView;
import com.victorxu.muses.glide.GlideApp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class FilterApplyFragment extends BaseFragment implements FilterApplyContract.View {

    private PinchImageView mImgDisplay;
    private AppCompatTextView mTextCancel;
    private AppCompatTextView mTextChoosePic;
    private AppCompatTextView mTextTweaker;
    private AppCompatTextView mTextExport;
//    private BottomPicker.Builder mBottomPicker;
    private FilterApplyPresenter mPresenter;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_apply, container, false);
        mPresenter = new FilterApplyPresenter(this);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                mPresenter.loadDataToView();
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
    public void initRootView(View view) {
        mImgDisplay = view.findViewById(R.id.filter_apply_display_image);
        mTextCancel = view.findViewById(R.id.filter_apply_cancel);
        mTextChoosePic = view.findViewById(R.id.filter_apply_choose_pic);
        mTextTweaker = view.findViewById(R.id.filter_apply_tweaker);
        mTextExport = view.findViewById(R.id.filter_apply_export);
//        mBottomPicker = BottomPicker.with(mActivity);
        mTextCancel.setOnClickListener(v -> mActivity.onBackPressed());
        mTextChoosePic.setOnClickListener(v -> {}
//            mBottomPicker.show((Uri uri) -> {
//                Log.d("CHOOSE_IMG", "initRootView: " + uri.toString());
//                showImage(uri.toString());
//            })
        );
        mTextTweaker.setOnClickListener(v -> {});
        mTextExport.setOnClickListener(v -> {});
//        mBottomPicker.show((Uri uri) -> {
//            Log.d("CHOOSE_IMG", "initRootView: " + uri.toString());
//            showImage(uri.toString());
//        });
    }

    @Override
    public void showImage(String url) {
        GlideApp.with(mActivity)
                .load(url)
                .into(mImgDisplay);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }
}
