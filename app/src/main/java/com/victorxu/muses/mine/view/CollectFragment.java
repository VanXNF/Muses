package com.victorxu.muses.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.gson.Collection;
import com.victorxu.muses.mine.contract.CollectionContract;
import com.victorxu.muses.mine.presenter.CollectionPresenter;
import com.victorxu.muses.mine.view.adapter.CollectionAdapter;
import com.victorxu.muses.product.view.ProductFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CollectFragment extends BaseFragment implements CollectionContract.View {

    private Toolbar mToolbar;
    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecycler;
    private CollectionAdapter mAdapter;

    private List<Collection.CollectionBean> mCollectionData = new ArrayList<>();

    private CollectionPresenter mPresenter;

    public static CollectFragment newInstance() {
        return new CollectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        mPresenter = new CollectionPresenter(this, mActivity);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPresenter.loadDataToView();
    }

    @Override
    public void initRootView(View view) {
        mToolbar = view.findViewById(R.id.collect_toolbar);
        mRefresh = view.findViewById(R.id.collect_refresh);
        mRecycler = view.findViewById(R.id.collect_recycler);

        mToolbar.setNavigationOnClickListener(v -> mActivity.onBackPressed());

        mRefresh.setOnRefreshListener(() -> mPresenter.reloadDataToView());

        mAdapter = new CollectionAdapter(mCollectionData);
//        mAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View v, int position) ->
//            start(ProductFragment.newInstance(mCollectionData.get(position).getCommodityId()))
//        );
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) ->
                start(ProductFragment.newInstance(mCollectionData.get(position).getCommodityId()))
        );

        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        mRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRefresh.setRefreshing(false);
    }

    @Override
    public void showCollection(List<Collection.CollectionBean> data) {
        mCollectionData.clear();
        mCollectionData.addAll(data);
        post(() -> {
            mAdapter.setNewData(mCollectionData);
            mAdapter.notifyDataSetChanged();
        });
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
        return R.id.collect_toolbar;
    }
}
