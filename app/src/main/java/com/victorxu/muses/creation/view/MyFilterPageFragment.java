package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.creation.contract.MyFilterContract;
import com.victorxu.muses.creation.presenter.MyFilterPresenter;
import com.victorxu.muses.creation.view.adapter.FilterAdapter;
import com.victorxu.muses.creation.view.adapter.UnFinishedFilterAdapter;
import com.victorxu.muses.gson.PageFilter;
import com.victorxu.muses.gson.UnfinishedFilterStatus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyFilterPageFragment extends BaseFragment implements MyFilterContract.View {

    public static final int TYPE_UNFINISHED = -1;
    public static final int TYPE_FINISHED = 1;

    private int type;

    private View mEmptyViewFilter;
    private RecyclerView mRecyclerFilter;
    private FilterAdapter mAdapterFilter;
    private List<PageFilter.FilterBean> mFilterData;
    private UnFinishedFilterAdapter mAdapterUnfinished;
    private List<UnfinishedFilterStatus.UnfinishedFilterBean> mUnfinishedData;
    private MyFilterPresenter mPresenterMyFilter;

    public static MyFilterPageFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        MyFilterPageFragment fragment = new MyFilterPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("TYPE");
        } else {
            type = TYPE_UNFINISHED;
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenterMyFilter.loadDataToView(type);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_filter_page, container, false);
        mPresenterMyFilter = new MyFilterPresenter(this, mActivity, type);
        mPresenterMyFilter.loadRootView(view);
        return view;
    }

    @Override
    public void initRootView(View view) {
        mRecyclerFilter = view.findViewById(R.id.my_filter_page_recycler_view);
        mEmptyViewFilter = view.findViewById(R.id.my_filter_page_empty_view);

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        mRecyclerFilter.setLayoutManager(layoutManager);
        switch (type) {
            case TYPE_UNFINISHED:
                mUnfinishedData = new ArrayList<>();
                mAdapterUnfinished = new UnFinishedFilterAdapter(mUnfinishedData);
                mAdapterUnfinished.setEnableLoadMore(false);
                mAdapterUnfinished.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) -> {
                    showToast(R.string.filter_do_not_finished_please_wait);
                });
                mRecyclerFilter.setAdapter(mAdapterUnfinished);
                break;
            case TYPE_FINISHED:
                mFilterData = new ArrayList<>();
                mAdapterFilter = new FilterAdapter(mFilterData);
                mAdapterFilter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) ->
                        ((MyFilterFragment) getParentFragment()).start(FilterApplyFragment.newInstance(mFilterData.get(position).getUploadId()))
                );
                mAdapterFilter.setOnLoadMoreListener(() -> mPresenterMyFilter.loadMoreDataToView(TYPE_FINISHED), mRecyclerFilter);
                mRecyclerFilter.setAdapter(mAdapterFilter);
                break;
        }
    }

    @Override
    public void showUnfinishedFilter(List<UnfinishedFilterStatus.UnfinishedFilterBean> data) {
        mUnfinishedData.clear();
        mUnfinishedData.addAll(data);
        post(() -> {
            mAdapterUnfinished.setNewData(mUnfinishedData);
            mAdapterUnfinished.notifyDataSetChanged();
        });
    }

    @Override
    public void showFinishedFilter(List<PageFilter.FilterBean> data) {
        mFilterData.clear();
        mFilterData.addAll(data);
        post(() -> {
            mAdapterFilter.setNewData(mFilterData);
            mAdapterFilter.notifyDataSetChanged();
        });
    }

    @Override
    public void showMoreUnfinishedFilter(List<UnfinishedFilterStatus.UnfinishedFilterBean> data) {
        mUnfinishedData.addAll(data);
        post(() -> {
            mAdapterUnfinished.addData(data);
            mAdapterUnfinished.notifyDataSetChanged();
        });
    }

    @Override
    public void showMoreFinishedFilter(List<PageFilter.FilterBean> data) {
        mFilterData.addAll(data);
        post(() -> {
            mAdapterFilter.addData(data);
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
        post(() -> mEmptyViewFilter.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideEmptyPage() {
        post(() -> mEmptyViewFilter.setVisibility(View.GONE));
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }
}
