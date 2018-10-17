package com.victorxu.muses.gallery.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.internal.FlowLayout;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchFragment extends BaseMainFragment {

    private FlowLayout mFlowLayout;
    private LinearLayout mLinearLayout;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
//        args.putParcelable();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mFlowLayout = view.findViewById(R.id.historical_search_flow_layout);
        mLinearLayout = view.findViewById(R.id.delete_all);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlowLayout.removeAllViews();
            }
        });

    }

}
