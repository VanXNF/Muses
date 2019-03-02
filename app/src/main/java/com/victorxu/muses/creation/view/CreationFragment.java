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
import com.victorxu.muses.creation.contract.CreationContract;
import com.victorxu.muses.creation.presenter.CreationPresenter;
import com.victorxu.muses.creation.view.adapter.FilterClassAdapter;
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

public class CreationFragment extends BaseMainFragment implements CreationContract.View {

    private AppCompatTextView mTextMyFilter;
    private AppCompatTextView mTextNewFilter;
    private SearchView mSearchFilter;
    private RecyclerView mRecyclerClass;
    private FilterClassAdapter mAdapterClass;
    private RecyclerView mRecyclerPopular;

    private List<FilterClass.FilterClassBean> mFilterClassData = new ArrayList<>();
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
            // TODO: 2019/3/2 jump to filter class detail page
        });
        mRecyclerClass.setAdapter(mAdapterClass);

        mRecyclerPopular.setLayoutManager(new LinearLayoutManager(mActivity));

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
    public void showPopularSearch() {

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
        return R.id.creation_page_bar;
    }
}
