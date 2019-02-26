package com.victorxu.muses.mine.presenter;

import android.content.Context;
import android.view.View;

import com.victorxu.muses.mine.contract.AddressContract;
import com.victorxu.muses.mine.model.AddressModel;

public class AddressPresenter implements AddressContract.Presenter {

    private AddressContract.View mView;
    private AddressContract.Model mModel;

    public AddressPresenter(AddressContract.View mView, Context context) {
        this.mView = mView;
        mModel = new AddressModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {

    }

    @Override
    public void reloadDataToView() {

    }

    @Override
    public void addAddress() {

    }

    @Override
    public void deleteAddress(int position) {

    }

    @Override
    public void updateAddress(int position) {

    }
}
