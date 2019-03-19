package com.victorxu.muses.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.gson.PageCommodity;
import com.victorxu.muses.trade.view.ProductFragment;
import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.search.presenter.SearchResultPresenter;
import com.victorxu.muses.search.view.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultPageFragment extends BaseFragment implements SearchResultContract.View {

    private int index = 0;
    private boolean isASC = false;

    private SearchResultContract.Presenter mPresenter;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecycler;
    private ProductAdapter mAdapter;
    private View mEmptyView;
    private View mErrorView;
    private List<PageCommodity.PageBean.CommodityListModel> mData = new ArrayList<>();


    public static SearchResultPageFragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        SearchResultPageFragment fragment = new SearchResultPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt("INDEX");
        } else {
            index = 0;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        mPresenter = null;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.loadProductToView();
        }
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result_page, container, false);
        mPresenter = new SearchResultPresenter(index, ((SearchResultFragment) getParentFragment()).getKeywords(), this);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_search_result);
        mEmptyView = view.findViewById(R.id.search_empty_view);
        mErrorView = view.findViewById(R.id.search_error_view);
        mRecycler = view.findViewById(R.id.page_recycler);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new ProductAdapter(mData);
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            try {
                ((SearchResultFragment) getParentFragment()).startBrotherFragment(ProductFragment.newInstance(mData.get(position).getId()));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        mAdapter.setOnLoadMoreListener(() -> mPresenter.loadMoreProductToView(), mRecycler);
        mRecycler.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener((@NonNull RefreshLayout refreshLayout) -> mPresenter.reloadProductToView());
        mRefreshLayout.setEnableAutoLoadMore(false);
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setRefreshHeader(new MaterialHeader(mActivity));
    }

    @Override
    public void showLoading() {
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void showProductList(List<PageCommodity.PageBean.CommodityListModel> data) {
        mData.clear();
        mData.addAll(data);
        mRecycler.post(() -> {
            mAdapter.setNewData(mData);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showMoreProduct(List<PageCommodity.PageBean.CommodityListModel> data) {
        mData.addAll(data);
        mRecycler.post(() -> {
            mAdapter.setNewData(mData);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh(500);
    }

    @Override
    public void hideLoadingMore(boolean isCompeted, boolean isEnd) {
        mRecycler.post(() -> {
            if (!isEnd) {
                if (isCompeted) {
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreFail();
                }
            } else {
                mAdapter.loadMoreEnd();
            }
        });
    }

    @Override
    public void showToast(Integer resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void showEmptyPage() {
        post(() -> mEmptyView.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideEmptyPage() {
        post(() -> mEmptyView.setVisibility(View.GONE));
    }

    @Override
    public void showFailPage() {
        post(() -> mErrorView.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideFailPage() {
        post(() -> mErrorView.setVisibility(View.GONE));
    }
}
