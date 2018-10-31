package com.victorxu.muses.core.presenter;

import com.victorxu.muses.core.contract.MainContract;
import com.victorxu.muses.core.model.MainModel;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel = new MainModel();

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }


}
