package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.creation.contract.SearchFilterContract;
import com.victorxu.muses.creation.presenter.SearchFilterPresenter;
import com.victorxu.muses.creation.view.adapter.FilterAdapter;
import com.victorxu.muses.custom.SearchView;
import com.victorxu.muses.gson.PageFilter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFilterFragment extends BaseSwipeBackFragment implements SearchFilterContract.View {

    private SearchView mSearchViewFilter;
    private AppCompatTextView mTextSearch;
    private RecyclerView mRecyclerFilter;
    private View mEmptyViewSearch;

    private FilterAdapter mAdapterFilter;
    private List<PageFilter.FilterBean> mFilterData;

    private SearchFilterPresenter mPresenterSearch;

    public static SearchFilterFragment newInstance() {
        return new SearchFilterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_filter, container, false);
        mPresenterSearch = new SearchFilterPresenter(this);
        mPresenterSearch.loadRootView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void initRootView(View view) {
        mSearchViewFilter = view.findViewById(R.id.search_filter_search_bar);
        mTextSearch = view.findViewById(R.id.search_filter_text_search);
        mRecyclerFilter = view.findViewById(R.id.search_filter_recycler_view);
        mEmptyViewSearch = view.findViewById(R.id.search_filter_empty_page);

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        mRecyclerFilter.setLayoutManager(layoutManager);
        mFilterData = new ArrayList<>();
        mAdapterFilter = new FilterAdapter(mFilterData);
        mAdapterFilter.setOnLoadMoreListener(() -> mPresenterSearch.loadMoreDataToView(), mRecyclerFilter);
        mAdapterFilter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            hideSoftInput();
            start(FilterApplyFragment.newInstance(mFilterData.get(position).getUploadId()));
        });
        mRecyclerFilter.setAdapter(mAdapterFilter);

        mSearchViewFilter.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String keyword = mSearchViewFilter.getSearchViewText();
                mPresenterSearch.loadDataToView(keyword);
            }
        });

        mTextSearch.setOnClickListener((v) -> {
            String keyword = mSearchViewFilter.getSearchViewText();
            mPresenterSearch.loadDataToView(keyword);
        });
    }

    @Override
    public void showFilterData(List<PageFilter.FilterBean> data) {
        mFilterData.clear();
        mFilterData.addAll(data);
        post(() -> {
            mAdapterFilter.setNewData(mFilterData);
            mAdapterFilter.notifyDataSetChanged();
        });
    }

    @Override
    public void showMoreFilterData(List<PageFilter.FilterBean> data) {
        mFilterData.addAll(data);
        post(() -> {
            mAdapterFilter.setNewData(mFilterData);
            mAdapterFilter.notifyDataSetChanged();
        });
    }

    @Override
    public void showLoadMore() {

    }

    @Override
    public void hideLoadMore(boolean isCompleted, boolean isEnd) {
        post(() -> {
            if (isEnd) {
                mAdapterFilter.loadMoreEnd();
            } else {
                if (isCompleted) {
                    mAdapterFilter.loadMoreComplete();
                } else {
                    mAdapterFilter.loadMoreFail();
                }
            }
        });
    }

    @Override
    public void showEmptyPage() {
        post(() -> mEmptyViewSearch.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideEmptyPage() {
        post(() -> mEmptyViewSearch.setVisibility(View.GONE));
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.search_filter_page_bar;
    }
}
