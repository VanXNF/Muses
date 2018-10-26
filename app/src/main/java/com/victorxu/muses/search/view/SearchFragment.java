package com.victorxu.muses.search.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.internal.FlowLayout;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.custom.search_view.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class SearchFragment extends BaseSwipeBackFragment {

    private SearchView mSearchView;
    private FlowLayout mFlowLayout;
    private LinearLayout mLinearLayout;
    private AppCompatTextView mTextSearch;

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
        return attachToSwipeBack(view);
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
        mSearchView = view.findViewById(R.id.search_bar);
        mTextSearch = view.findViewById(R.id.text_search);
        mTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mSearchView.getSearchViewText())) {
                    start(SearchResultFragment.newInstance(mSearchView.getSearchViewText()));
                } else {
                    Toast.makeText(mActivity, R.string.please_enter_keywords, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
