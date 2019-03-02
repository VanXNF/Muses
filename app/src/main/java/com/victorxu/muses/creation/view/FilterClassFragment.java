package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victorxu.muses.base.BaseSwipeBackFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FilterClassFragment extends BaseSwipeBackFragment {

    public static FilterClassFragment newInstance() {
        return new FilterClassFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
