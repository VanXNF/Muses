package com.victorxu.muses.custom.bottompicker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.victorxu.muses.R;

public class SquareFrameLayout extends FrameLayout {


    private static boolean mMatchHeightToWidth;
    private static boolean mMatchWidthToHeight;

    public SquareFrameLayout(Context context) {
        super(context);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BottomPickerSquareView,
                0, 0);

        try {
            mMatchHeightToWidth = a.getBoolean(R.styleable.BottomPickerSquareView_matchHeightToWidth, false);
            mMatchWidthToHeight = a.getBoolean(R.styleable.BottomPickerSquareView_matchWidthToHeight, false);
        } finally {
            a.recycle();
        }
    }

    //Squares the thumbnail
    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mMatchHeightToWidth){
            setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
        } else if(mMatchWidthToHeight){
            setMeasuredDimension(heightMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        if(mMatchHeightToWidth){
            super.onSizeChanged(w, w,oldw,oldh);
        } else if(mMatchWidthToHeight){
            super.onSizeChanged(h, h,oldw,oldh);
        }

    }
}
