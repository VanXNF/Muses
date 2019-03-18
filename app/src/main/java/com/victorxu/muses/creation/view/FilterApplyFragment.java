package com.victorxu.muses.creation.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gyf.barlibrary.ImmersionBar;
import com.mmga.metroloading.MetroLoadingView;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.creation.presenter.FilterApplyPresenter;
import com.victorxu.muses.custom.PinchImageView;
import com.victorxu.muses.custom.bottompicker.BottomPicker;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.trade.view.CustomizeFragment;
import com.victorxu.muses.util.CropUtil;
import com.victorxu.muses.util.FileUtil;
import com.victorxu.muses.util.ImageUtil;
import com.xw.repo.BubbleSeekBar;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;


public class FilterApplyFragment extends BaseFragment implements FilterApplyContract.View {

    private static final String TAG = "FilterApplyFragment";

    private PinchImageView mImgDisplay;
    private View mLoadingView;
    private AppCompatTextView mTextCancel;
    private AppCompatTextView mTextChoosePic;
    private AppCompatTextView mTextTweaker;
    private AppCompatTextView mTextExport;
    private View mViewSeekBar;
    private AppCompatTextView mTextSeekBarTitle;
    private BubbleSeekBar mSeekBar;
    private AppCompatTextView mTextSeekBarConfirm;
    private View mViewExportSave;
    private View mViewExportBuy;
    private View mViewTweakerAdjust;
    private BottomPicker.Builder mBottomPicker;
    private FilterApplyPresenter mPresenter;
    private AlertDialog mDialogLoading;

    private Bitmap mBitmapOrigin;
    private Bitmap mBitmapFilter;
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
        mViewSeekBar = view.findViewById(R.id.filter_apply_seek_bar_container);
        mTextSeekBarTitle = view.findViewById(R.id.filter_apply_text_seek_bar_title);
        mSeekBar = view.findViewById(R.id.filter_apply_seek_bar);
        mTextSeekBarConfirm = view.findViewById(R.id.filter_apply_text_seek_bar_confirm);
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
        View tweakerView = getLayoutInflater().inflate(R.layout.filter_apply_bottom_tweaker, null);
        BottomSheetDialog tweakerDialog = new BottomSheetDialog(mActivity);
        tweakerDialog.setContentView(tweakerView);
        mViewTweakerAdjust = tweakerView.findViewById(R.id.filter_apply_tweak_intensity);
        mViewTweakerAdjust.setOnClickListener(v -> {
            tweakerDialog.dismiss();
            post(() -> {
                mViewSeekBar.setVisibility(View.VISIBLE);
                mTextSeekBarTitle.setText(R.string.brush_intensity);
                mSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
                    @Override
                    public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                        mBitmapData = tweakStyleIntensity(progress);
                        mImgDisplay.setImageBitmap(mBitmapData);
                    }

                    @Override
                    public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                    }

                    @Override
                    public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                    }
                });
            });
        });
        mTextSeekBarConfirm.setOnClickListener(v -> post(() -> mViewSeekBar.setVisibility(View.GONE)));
        mTextTweaker.setOnClickListener(v -> {
            if (checkImageStatus()) {
                tweakerDialog.show();
            } else {
                showToast(R.string.please_choose_pic_first);
            }
        });

        View exportView = getLayoutInflater().inflate(R.layout.filter_apply_bottom_export, null);
        BottomSheetDialog exportDialog = new BottomSheetDialog(mActivity);
        exportDialog.setContentView(exportView);
        mViewExportSave = exportView.findViewById(R.id.filter_apply_bottom_save);
        mViewExportBuy = exportView.findViewById(R.id.filter_apply_bottom_buy);
        mViewExportSave.setOnClickListener(v -> {
            mPresenter.saveData();
            exportDialog.dismiss();
        });
        mViewExportBuy.setOnClickListener(v -> {
            mPresenter.uploadArtData(mBitmapData);
            exportDialog.dismiss();
        });
        mTextExport.setOnClickListener(v -> {
            if (checkImageStatus()) {
                exportDialog.show();
            } else {
                showToast(R.string.please_choose_pic_first);
            }
        });
        mTextChoosePic.callOnClick();
        initLoadingDialog();
    }

    @Override
    public void showFilterImage(String url) {
        post(() ->
                GlideApp.with(mActivity)
                        .asBitmap()
                        .override(mBitmapOrigin.getWidth(), mBitmapOrigin.getHeight())
                        .load(url)
                        .into(new BitmapImageViewTarget(mImgDisplay) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource,
                                                        @Nullable Transition<? super Bitmap> transition) {
                                mBitmapFilter = resource;
                                mBitmapData = resource;
                                mImgDisplay.setImageBitmap(mBitmapData);
                            }
                        })
        );
    }

    @Override
    public void showCustomizePage(int id) {
        start(CustomizeFragment.newInstance(id));
    }

    @Override
    public void saveFilterImage() {
        if (FileUtil.saveImageToGallery(mActivity,
                ImageUtil.createWaterMaskRightBottom(mActivity, mBitmapData,
                        BitmapFactory.decodeResource(getResources(), R.drawable.muses_art),
                        10, 10))) {
            showToast(R.string.save_success);
        } else {
            showToast(R.string.save_fail);
        }
    }

    @Override
    public Uri saveTempImage(String filename, Bitmap bitmap) {
        return FileUtil.saveImage(bitmap, filename);
    }

    @Override
    public void deleteTempImage(Uri uri) {
        FileUtil.deleteImage(uri);
    }

    @Override
    public void showLoading() {
        post(() -> mLoadingView.setVisibility(View.VISIBLE));
    }

    @Override
    public void showLoadingDialog() {
        post(() -> mDialogLoading.show());
    }

    @Override
    public void hideLoading() {
        post(() -> mLoadingView.setVisibility(View.GONE));
    }

    @Override
    public void hideLoadingDialog() {
        post(() -> mDialogLoading.dismiss());
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    private Bitmap tweakStyleIntensity(int number) {
        int width = mBitmapOrigin.getWidth();
        int height = mBitmapOrigin.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(mBitmapOrigin, 0, 0, null);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAlpha(number);
        canvas.drawBitmap(mBitmapFilter, 0, 0, paint);
        canvas.save();
        canvas.restore();
        return newBitmap;
    }

    private boolean checkImageStatus() {
        return mBitmapData != null;
    }

    private void initLoadingDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View dialogView = layoutInflater.inflate(R.layout.dialog_loading, null);
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mActivity);
        builder.setView(dialogView);
        AppCompatTextView txtTitle = dialogView.findViewById(R.id.dialog_title);
        MetroLoadingView loadingView = dialogView.findViewById(R.id.dialog_loading);
        builder.setCancelable(false);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.please_wait_we_are_create_image_now);

        mDialogLoading = builder.create();
        Window window = mDialogLoading.getWindow();
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangle_all_bg, null));
        mDialogLoading.setOnShowListener((DialogInterface dialog) -> loadingView.start());
        mDialogLoading.setOnDismissListener((DialogInterface dialog) -> loadingView.stop());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropUtil.CROP_CODE && resultCode == RESULT_OK) {
            Uri uri = UCrop.getOutput(data);
            mBitmapOrigin = BitmapFactory.decodeFile(uri.getPath());
            mPresenter.uploadData(uri);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_apply, container, false);
        mPresenter = new FilterApplyPresenter(this, id, mActivity);
        mPresenter.loadRootView(view);
        return view;
    }

    private Uri addTextMark(Uri uri) {
        try {
            File file = new File(uri.getPath());
            Bitmap src = ImageUtil.drawTextToRightBottom(mActivity,
                    BitmapFactory.decodeFile(uri.getPath()),
                    getString(R.string.app_name), 20,
                    20, 15);
            FileOutputStream fos = new FileOutputStream(file);
            src.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.parse(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
