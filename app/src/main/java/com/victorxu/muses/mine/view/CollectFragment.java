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
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CollectFragment extends BaseFragment implements CollectionContract.View {

    private Toolbar mToolbar;
    private SwipeRefreshLayout mRefresh;
    private SwipeMenuRecyclerView mRecycler;
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

        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) ->
                start(ProductFragment.newInstance(mCollectionData.get(position).getCommodityId()))
        );

        mRecycler.setSwipeMenuCreator((SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) -> {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity)
                    .setText(getResources().getString(R.string.delete))
                    .setHeight(height)
                    .setWidth(width)
                    .setTextColor(getResources().getColor(R.color.white))
                    .setBackgroundColor(getResources().getColor(R.color.dark_red));
            rightMenu.addMenuItem(deleteItem);
        });
        mRecycler.setSwipeMenuItemClickListener((SwipeMenuBridge menuBridge) -> {
            menuBridge.closeMenu();
            int adapterPosition = menuBridge.getAdapterPosition();
            int menuPosition = menuBridge.getPosition();
            if (menuPosition == 0) {
                mPresenter.removeFavorite(adapterPosition);
            }
        });
        mRecycler.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.light_white), ViewGroup.LayoutParams.MATCH_PARENT, 5));
        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecycler.setAdapter(mAdapter);

        mRecycler.setAutoLoadMore(false);
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
