package com.victorxu.muses;

import android.os.Bundle;

import com.victorxu.muses.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);
    }
}
