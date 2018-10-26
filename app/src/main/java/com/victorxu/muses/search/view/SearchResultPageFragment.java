package com.victorxu.muses.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.product.view.ProductDetailFragment;
import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.search.presenter.SearchResultPresenter;
import com.victorxu.muses.search.view.adapter.ProductAdapter;
import com.victorxu.muses.search.view.entity.ProductItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SearchResultPageFragment extends BaseFragment implements SearchResultContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    private SearchResultContract.Presenter mPresenter;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecycler;
    private ProductAdapter mAdapter;
    private List<ProductItem> mData;


    public static SearchResultPageFragment newInstance() {
        Bundle bundle = new Bundle();

        SearchResultPageFragment fragment = new SearchResultPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result_page, container, false);
        mPresenter = new SearchResultPresenter(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecycler = view.findViewById(R.id.page_recycler);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mPresenter.loadData();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, mData.get(position).getmTitle(), Toast.LENGTH_SHORT).show();
                ((SearchResultFragment) getParentFragment()).startBrotherFragment(ProductDetailFragment.newInstance());
            }
        });
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showProductList(List<ProductItem> data) {
        mData = data;
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter = new ProductAdapter(mData);
        }
    }

    @Override
    public void refreshProductList(List<ProductItem> data) {
        mData = data;
        post(() -> {
            mAdapter.setNewData(mData);
            mAdapter.notifyDataSetChanged();
            mRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void loadMoreProduct(List<ProductItem> data, boolean isCompleted) {
        if (!isCompleted) {
            mData.addAll(data);
            mAdapter.enableLoadMoreEndClick(true);
            mRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.setNewData(mData);
                    mAdapter.notifyDataSetChanged();
                    mAdapter.loadMoreComplete();
                }
            }, 500);
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreData();
    }
}
