package com.victorxu.muses.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MineFragment extends BaseMainFragment {

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
//        args.putParcelable();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
