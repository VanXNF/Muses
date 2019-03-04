package com.victorxu.muses.creation.presenter;

import android.view.View;

import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.creation.model.FilterApplyModel;

public class FilterApplyPresenter implements FilterApplyContract.Presenter {

    private FilterApplyContract.View mView;
    private FilterApplyContract.Model mModel;

    public FilterApplyPresenter(FilterApplyContract.View mView) {
        this.mView = mView;
        mModel = new FilterApplyModel();
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {

    }
}
