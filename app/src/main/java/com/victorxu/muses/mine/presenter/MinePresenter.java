package com.victorxu.muses.mine.presenter;

import android.content.Context;
import android.view.View;

import com.victorxu.muses.mine.contract.MineContract;
import com.victorxu.muses.mine.model.MineModel;

public class MinePresenter implements MineContract.Presenter {

    private MineContract.View mView;
    private MineContract.Model mModel;

    public MinePresenter(MineContract.View mView, Context context) {
        this.mView = mView;
        mModel = new MineModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        if (mModel.checkUserStatus()) {
            mView.showBaseUserInfo(mModel.getUserData());
        }
    }

    @Override
    public void goToAccount() {
        if (mModel.checkUserStatus()) {
            // TODO: 2019/2/20 跳转个人详情页
        } else {
            // TODO: 2019/2/20 跳转登录页
        }
    }
}
