package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.creation.contract.FilterClassContract;
import com.victorxu.muses.creation.presenter.FilterClassPresenter;
import com.victorxu.muses.creation.view.adapter.FilterAdapter;
import com.victorxu.muses.gson.PageFilter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FilterClassFragment extends BaseSwipeBackFragment implements FilterClassContract.View {

    public static final String CATEGORY = "category";
    public static final String USER = "user";

    private String title;
    private String key;
    private int id;
    private List<PageFilter.FilterBean> mFilterData = new ArrayList<>();
    private FilterAdapter mAdapterFilter;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerFilter;
    private View mViewEmpty;
    private FilterClassPresenter mPresenter;

    public static FilterClassFragment newInstance(String title, String key, int id) {
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        bundle.putInt("ID", id);
        bundle.putString("KEY", key);
        FilterClassFragment fragment = new FilterClassFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("TITLE");
            id = bundle.getInt("ID");
            key = bundle.getString("KEY");
        } else {
            showToast(R.string.data_error_please_try_again);
            pop();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_class_detail, container, false);
        mPresenter = new FilterClassPresenter(this, key, id);
        mPresenter.loadRootView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mPresenter.loadDataToView();
    }

    @Override
    public void initRootView(View view) {
        mToolbar = view.findViewById(R.id.filter_class_detail_toolbar);
        mRecyclerFilter = view.findViewById(R.id.filter_class_detail_recycler_view);
        mViewEmpty = view.findViewById(R.id.filter_class_detail_empty_view);
        mToolbar.setTitle(title);
        mToolbar.setNavigationOnClickListener(v -> mActivity.onBackPressed());

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        mRecyclerFilter.setLayoutManager(layoutManager);
        mAdapterFilter = new FilterAdapter(mFilterData);
        mAdapterFilter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) ->
            start(FilterApplyFragment.newInstance(mFilterData.get(position).getUploadId()))
        );
        mAdapterFilter.setEnableLoadMore(true);
        mAdapterFilter.disableLoadMoreIfNotFullPage(mRecyclerFilter);
        mAdapterFilter.setOnLoadMoreListener(() -> mPresenter.loadMoreDataToView(), mRecyclerFilter);
        mRecyclerFilter.setAdapter(mAdapterFilter);
    }

    @Override
    public void showFilter(List<PageFilter.FilterBean> data) {
        mFilterData.clear();
        mFilterData.addAll(data);
        post(() -> {
            mAdapterFilter.setNewData(mFilterData);
            mAdapterFilter.notifyDataSetChanged();
        });
    }

    @Override
    public void showMoreFilter(List<PageFilter.FilterBean> data) {
        mFilterData.addAll(data);
        post(() -> {
            mAdapterFilter.addData(data);
            mAdapterFilter.notifyDataSetChanged();
        });
    }

    @Override
    public void showLoadMore() {}

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
        post(() -> mViewEmpty.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideEmptyPage() {
        post(() -> mViewEmpty.setVisibility(View.GONE));
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
        return R.id.filter_class_detail_toolbar;
    }
}
