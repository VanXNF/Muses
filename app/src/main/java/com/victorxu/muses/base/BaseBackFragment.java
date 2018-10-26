package com.victorxu.muses.base;

import android.os.Bundle;
import android.view.View;

import com.victorxu.muses.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public class BaseBackFragment extends BaseSwipeBackFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setParallaxOffset(0.5f);
    }

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
    }

}
