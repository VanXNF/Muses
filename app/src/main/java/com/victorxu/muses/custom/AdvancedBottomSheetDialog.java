package com.victorxu.muses.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import androidx.annotation.NonNull;

public class AdvancedBottomSheetDialog extends BottomSheetDialog {

    // TODO: 2019/2/4 修改固定弹出高度为最大 

    private int mPeekHeight;
    private int mMaxHeight;
    private boolean mCreated;
    private Window mWindow;
    private BottomSheetBehavior mBottomSheetBehavior;

    public AdvancedBottomSheetDialog(@NonNull Context context, float peekScale, float maxScale) {
        super(context);
        init(peekScale, maxScale);
    }

    public AdvancedBottomSheetDialog(@NonNull Context context, int peekHeight, int maxHeight) {
        super(context);
        init(peekHeight, maxHeight);
    }

    public AdvancedBottomSheetDialog(@NonNull Context context, int theme,int peekHeight,int maxHeight) {
        super(context, theme);
        init(peekHeight, maxHeight);
    }

    public AdvancedBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener,int peekHeight,int maxHeight) {
        super(context, cancelable, cancelListener);
        init(peekHeight, maxHeight);
    }

    private void init(int peekHeight, int maxHeight) {
        mWindow = getWindow();
        mPeekHeight = peekHeight;
        mMaxHeight = maxHeight;
    }

    private void init(double peekScale, double maxScale) {
        mWindow = getWindow();
        Display display = mWindow.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mPeekHeight = (int) (size.y * peekScale);
        mMaxHeight = (int) (size.y * maxScale);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCreated = true;
        setPeekHeight();
        setMaxHeight();
        setBottomSheetCallback();
    }

    public void setPeekHeight(int peekHeight) {
        mPeekHeight = peekHeight;
        if (mCreated) {
            setPeekHeight();
        }
    }

    public void setMaxHeight(int height) {
        mMaxHeight = height;
        if (mCreated) {
            setMaxHeight();
        }
    }

    public void setBatterSwipeDismiss(boolean enabled) {
        if (enabled) {

        }
    }

    private void setPeekHeight() {
        if (mPeekHeight <= 0) {
            return;
        }

        if (getBottomSheetBehavior() != null) {
            mBottomSheetBehavior.setPeekHeight(mPeekHeight);
        }
    }

    private void setMaxHeight() {
        if (mMaxHeight <= 0) {
            return;
        }
        mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, mMaxHeight);
        mWindow.setGravity(Gravity.BOTTOM);
    }

    private BottomSheetBehavior getBottomSheetBehavior() {
        if (mBottomSheetBehavior != null) {
            return mBottomSheetBehavior;
        }

        View view = mWindow.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        // setContentView() 没有调用
        if (view == null) {
            return null;
        }
        view.setBackgroundColor(Color.TRANSPARENT);
        mBottomSheetBehavior = BottomSheetBehavior.from(view);
        return mBottomSheetBehavior;
    }

    private void setBottomSheetCallback() {
        if (getBottomSheetBehavior() != null) {
            mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetCallback);
        }
    }

    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet,
                                   @BottomSheetBehavior.State int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
                BottomSheetBehavior.from(bottomSheet).setState(
                        BottomSheetBehavior.STATE_COLLAPSED);
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };
}
