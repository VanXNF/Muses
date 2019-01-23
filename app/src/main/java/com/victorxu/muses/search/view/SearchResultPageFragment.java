package com.victorxu.muses.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.custom.BlockHeaderView;
import com.victorxu.muses.product.view.ProductContainerFragment;
import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.search.presenter.SearchResultPresenter;
import com.victorxu.muses.search.view.adapter.ProductAdapter;
import com.victorxu.muses.search.view.entity.ProductItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultPageFragment extends BaseFragment implements SearchResultContract.View{


    private SearchResultContract.Presenter mPresenter;
    private TwinklingRefreshLayout mRefreshLayout;
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
        mRefreshLayout = view.findViewById(R.id.refresh_search_result);
        mRecycler = view.findViewById(R.id.page_recycler);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mPresenter.loadData();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    ((SearchResultFragment) getParentFragment()).startBrotherFragment(ProductContainerFragment.newInstance());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        });
        mRecycler.setAdapter(mAdapter);
        BlockHeaderView headerView = new BlockHeaderView(mActivity);
        mRefreshLayout.setHeaderView(headerView);
        mRefreshLayout.setMaxHeadHeight(140);
//        mRefreshLayout.setEnableLoadmore(false);

        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                mRefreshLayout.postDelayed(()->{
                    mPresenter.refreshData();
                    mRefreshLayout.finishRefreshing();
                },2000);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                mRefreshLayout.postDelayed(() -> {
                    mPresenter.loadMoreData();
                    mRefreshLayout.finishLoadmore();
                },2000);
            }
        });
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
        mRecycler.post(()->{
            mAdapter.setNewData(mData);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void loadMoreProduct(List<ProductItem> data, boolean isCompleted) {
        if (!isCompleted) {
            mData.addAll(data);
            mRecycler.post(()-> {
                mAdapter.setNewData(mData);
                mAdapter.notifyDataSetChanged();
                mAdapter.loadMoreComplete();
            });
        } else {
            mRefreshLayout.setEnableLoadmore(false);
        }
    }

//    @Override
//    public void onLoadMoreRequested() {
//        mPresenter.loadMoreData();
//    }
}
