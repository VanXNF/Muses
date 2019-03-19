package com.victorxu.muses.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.custom.SearchView;
import com.victorxu.muses.search.view.adapter.SearchResultPagerFragmentAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.ViewPager;

public class SearchResultFragment extends BaseFragment {

    private static final String ARG_KEYWORD = "keywords";

    private ViewPager mPager;
    private TabLayout mTabLayout;
    private SearchView mSearchView;
    private AppCompatTextView mSearchTextButton;
    private String keywords;

    public static SearchResultFragment newInstance(String keywords) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_KEYWORD, keywords);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            keywords = bundle.getString(ARG_KEYWORD);
        } else {
            keywords = null;
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPager.setAdapter(new SearchResultPagerFragmentAdapter(getChildFragmentManager(),
                getString(R.string.complex),
                getString(R.string.newest),
                getString(R.string.hottest),
                getString(R.string.price)));
        mTabLayout.setupWithViewPager(mPager);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.result_page_bar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSearchView = view.findViewById(R.id.search_result_bar);
        mSearchTextButton = view.findViewById(R.id.result_text_search);
        mPager = view.findViewById(R.id.view_pager_search_result);
        mPager.setOffscreenPageLimit(3);
        mTabLayout = view.findViewById(R.id.tab_search_result);
        for (int i = 0; i < 4; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mSearchView.setSearchViewHint(keywords);
        mSearchView.setOnSearchViewClickListener((View v) -> pop());
        mSearchTextButton.setOnClickListener((v) -> pop());
    }

    public String getKeywords() {
        return keywords;
    }

    public void startBrotherFragment(BaseFragment targetFragment) {
        start(targetFragment);
    }
}
