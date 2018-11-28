package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CreationFragment extends BaseMainFragment {

    public static CreationFragment newInstance() {
        Bundle bundle = new Bundle();
        CreationFragment fragment = new CreationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creation, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
