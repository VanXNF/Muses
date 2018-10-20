package com.victorxu.muses.account.presenter;

import com.victorxu.muses.account.contract.AccountContract;

public class AccountPresenter implements AccountContract.Presenter {

    private AccountContract.View mView;

    public AccountPresenter(AccountContract.View mView) {
        this.mView = mView;
    }
}
