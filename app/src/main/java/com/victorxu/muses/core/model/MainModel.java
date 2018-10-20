package com.victorxu.muses.core.model;

import com.victorxu.muses.core.contract.MainContract;

public class MainModel implements MainContract.Model {

    @Override
    public boolean isLogin() {
        // TODO: 18-10-19 检测是否登录
        return false;
    }

    @Override
    public String getAccountName() {
        return null;
    }

    @Override
    public String getSecurityCode() {
        return null;
    }
}
