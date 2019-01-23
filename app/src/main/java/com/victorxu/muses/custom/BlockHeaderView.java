package com.victorxu.muses.custom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.victorxu.muses.R;
import com.victorxu.muses.custom.BlockLoadView;

public class BlockHeaderView implements IHeaderView {

    private Context context;
    private View rootView;
    private BlockLoadView loadView;

    public BlockHeaderView(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        rootView = LayoutInflater.from(context).inflate(R.layout.loading_header, null);
        loadView = rootView.findViewById(R.id.block_header);
        loadView.setShadow(true);
        loadView.setViewColor(Color.RED);
        loadView.setShadowColor(Color.GRAY);
    }



    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) rootView.setVisibility(View.GONE);
        if (fraction > 1f) rootView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) rootView.setVisibility(View.GONE);
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
//        loadView.startAnim();
    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
//        loadView.stopAnim();
        animEndListener.onAnimEnd();
    }

    @Override
    public void reset() {
        rootView.setVisibility(View.GONE);
    }


}
