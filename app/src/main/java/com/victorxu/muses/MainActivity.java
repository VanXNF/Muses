package com.victorxu.muses;

import android.os.Bundle;

import com.victorxu.muses.base.BaseActivity;


import spa.lyh.cn.statusbarlightmode.ImmersionMode;

public class MainActivity extends BaseActivity {

    public ImmersionMode immersionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immersionMode = ImmersionMode.getInstance();
        setContentView(R.layout.search_bar);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        immersionMode.execImmersionMode(this);

    }
}
