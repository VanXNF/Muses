package com.victorxu.muses.creation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.core.view.MainFragment;
import com.victorxu.muses.creation.contract.CreationContract;
import com.victorxu.muses.creation.presenter.CreationPresenter;
import com.victorxu.muses.creation.view.adapter.FilterClassAdapter;
import com.victorxu.muses.creation.view.adapter.PopularSearchAdapter;
import com.victorxu.muses.creation.view.entity.PopularSearchItem;
import com.victorxu.muses.custom.SearchView;
import com.victorxu.muses.gson.FilterClass;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Fade;

public class CreationFragment extends BaseMainFragment implements CreationContract.View {

    private AppCompatTextView mTextMyFilter;
    private AppCompatTextView mTextNewFilter;
    private SearchView mSearchFilter;
    private RecyclerView mRecyclerClass;
    private FilterClassAdapter mAdapterClass;
    private RecyclerView mRecyclerPopular;
    private PopularSearchAdapter mAdapterPopular;

    private List<FilterClass.FilterClassBean> mFilterClassData = new ArrayList<>();
    private List<PopularSearchItem> mPopularSearchData = new ArrayList<>();
    private CreationPresenter mPresenter;

    public static CreationFragment newInstance() {
        Bundle bundle = new Bundle();
        CreationFragment fragment = new CreationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creation, container, false);
        mPresenter = new CreationPresenter(this);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadDataToView();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.creation_page_bar;
    }

    @Override
    public void initRootView(View view) {
        mTextMyFilter = view.findViewById(R.id.creation_text_my_filter);
        mTextNewFilter = view.findViewById(R.id.creation_new_filter);
        mSearchFilter = view.findViewById(R.id.creation_search_bar);
        mRecyclerClass = view.findViewById(R.id.creation_filter_class_recycler_view);
        mRecyclerPopular = view.findViewById(R.id.creation_popular_recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 5);
        mRecyclerClass.setLayoutManager(layoutManager);
        mAdapterClass = new FilterClassAdapter(mFilterClassData);
        mAdapterClass.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            ((MainFragment) getParentFragment()).startBrotherFragment(FilterClassFragment.newInstance(
                    mFilterClassData.get(position).getCategoryName(),
                    FilterClassFragment.CATEGORY, mFilterClassData.get(position).getId()));
        });
        mRecyclerClass.setAdapter(mAdapterClass);

        mRecyclerPopular.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapterPopular = new PopularSearchAdapter(mPopularSearchData);
        mAdapterPopular.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) ->
                ((MainFragment) getParentFragment()).startBrotherFragment(FilterApplyFragment.newInstance(mPopularSearchData.get(position).getId()))
        );
        mRecyclerPopular.setAdapter(mAdapterPopular);

        mTextMyFilter.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(MyFilterFragment.newInstance())
        );

        mTextNewFilter.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(CreateFilterFragment.newInstance())
        );

        mSearchFilter.setOnSearchViewClickListener((View v) ->
                ((MainFragment) getParentFragment()).startBrotherFragment(SearchFilterFragment.newInstance())
        );
    }

    @Override
    public void showFilterClasses(List<FilterClass.FilterClassBean> data) {
        mFilterClassData.clear();
        mFilterClassData.addAll(data);
        post(() -> {
            mAdapterClass.setNewData(mFilterClassData);
            mAdapterClass.notifyDataSetChanged();
        });
    }

    @Override
    public void showPopularSearch(List<PopularSearchItem> data) {
        mPopularSearchData.clear();
        mPopularSearchData.addAll(data);
        post(() -> {
            mAdapterPopular.setNewData(mPopularSearchData);
            mAdapterPopular.notifyDataSetChanged();
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
}
