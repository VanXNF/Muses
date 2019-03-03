package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FilterApplyFragment extends BaseFragment {

    private int id;

    public static FilterApplyFragment newInstance(int id) {
        Bundle bundle = new Bundle();

        FilterApplyFragment fragment = new FilterApplyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            // TODO: 2019/3/3 get Args
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_apply, container, false);

        return view;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }
}
