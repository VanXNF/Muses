package com.victorxu.muses.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victorxu.muses.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mTitle;
    private LinearLayout mLinearLayout;
    private Context mContext;
    private int mTabPosition = -1;

    public BottomBarTab(Context context, @DrawableRes int icon, @StringRes int title) {
        this(context, null, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, int title) {
        this(context, attrs, 0, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, int title) {
        super(context, attrs, defStyleAttr);
        init(context, icon, title);
    }

    private void init(Context context, Integer icon, Integer titleId) {

        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackground(drawable);
        typedArray.recycle();

        mLinearLayout = new LinearLayout(context);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setGravity(Gravity.CENTER);
        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size, size);
        params.gravity = Gravity.CENTER;
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);
        mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselected));
        mLinearLayout.addView(mIcon);
        mTitle = new TextView(context);
        LayoutParams textParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER;
        mTitle.setText(titleId);
        mTitle.setTextSize(12);
        mTitle.setLayoutParams(textParam);
        mTitle.setTextColor(ContextCompat.getColor(context, R.color.tab_unselected));
        mLinearLayout.addView(mTitle);

        addView(mLinearLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
            mTitle.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselected));
            mTitle.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselected));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
