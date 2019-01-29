package com.victorxu.muses.search.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.custom.SearchView;
import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.gson.HotKey;
import com.victorxu.muses.search.contract.SearchContract;
import com.victorxu.muses.search.presenter.SearchPresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

@SuppressWarnings("FieldCanBeLocal")
public class SearchFragment extends BaseSwipeBackFragment implements SearchContract.View {

    private SearchView mSearchView;
    private TagFlowLayout mHotKeyFlowLayout;
    private TagFlowLayout mHistoryFlowLayout;
    private View mHotKeyContainerView;
    private View mHistoryKeyContainerView;
    private View mDeleteHistoryView;
    private AppCompatTextView mTextSearch;
    private SearchPresenter mPresenter;

    private List<HotKey.Key> mHotKeyData = new ArrayList<>();
    private List<HistoryKey> mHistoryKeyData = new ArrayList<>();

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (mPresenter != null) {
            mPresenter.reloadHistoryDataToView();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mPresenter = new SearchPresenter(this);
        mPresenter.loadRootView(view);
        mPresenter.loadDataToView();
        return attachToSwipeBack(view);
    }


    @Override
    public void initView(View view) {
        mHotKeyContainerView = view.findViewById(R.id.hot_search_container);
        mHistoryKeyContainerView = view.findViewById(R.id.search_history_container);
        mHotKeyFlowLayout = view.findViewById(R.id.popular_search_tag_flow_layout);
        mHistoryFlowLayout = view.findViewById(R.id.history_search_tag_flow_layout);

        mHotKeyFlowLayout.setOnTagClickListener((View v, int position, FlowLayout parent) -> {
            mPresenter.goToSearch(mHotKeyData.get(position).getKeyword());
            return true;
        });

        mHistoryFlowLayout.setOnTagClickListener((View v, int position, FlowLayout parent) -> {
            mPresenter.goToSearch(mHistoryKeyData.get(position).getName());
            return true;
        });

        mDeleteHistoryView = view.findViewById(R.id.delete_all);
        mDeleteHistoryView.setOnClickListener((v) -> mPresenter.deleteAllHistoryData());

        mSearchView = view.findViewById(R.id.search_page_search_bar);
        mTextSearch = view.findViewById(R.id.search_page_search_button);
        mTextSearch.setOnClickListener((View v) -> {
            if (!TextUtils.isEmpty(mSearchView.getSearchViewText())) {
                mPresenter.goToSearch(mSearchView.getSearchViewText());
            } else {
                showToast(R.string.please_enter_keywords);
            }
        });

    }

    @Override
    public void showHotKey(List<HotKey.Key> hotKeys) {
        mHotKeyData.clear();
        mHotKeyData.addAll(hotKeys);
        mHotKeyContainerView.post(() -> {
            mHotKeyContainerView.setVisibility(View.VISIBLE);
            mHotKeyFlowLayout.setAdapter(new TagAdapter<HotKey.Key>(mHotKeyData) {
                @Override
                public View getView(FlowLayout parent, int position, HotKey.Key key) {
                    Chip chip = (Chip) LayoutInflater.from(mActivity).inflate(R.layout.item_tag, parent, false);
                    chip.setText(key.getKeyword());
                    return chip;
                }
            });
        });
    }

    @Override
    public void hideHotKey() {
        mHotKeyData.clear();
        mHotKeyFlowLayout.removeAllViews();
        mHotKeyContainerView.setVisibility(View.GONE);
    }

    @Override
    public void showHistoryKey(List<HistoryKey> historyKeys) {
        mHistoryKeyData.clear();
        if (historyKeys != null) {
            mHistoryKeyData.addAll(historyKeys);
        }
        mHistoryKeyContainerView.post(() -> {
            mHistoryKeyContainerView.setVisibility(View.VISIBLE);
            mHistoryFlowLayout.setAdapter(new TagAdapter<HistoryKey>(mHistoryKeyData) {
                @Override
                public View getView(FlowLayout parent, int position, HistoryKey historyKey) {
                    Chip chip = (Chip) LayoutInflater.from(mActivity).inflate(R.layout.item_tag, parent, false);
                    chip.setText(historyKey.getName());
                    return chip;
                }
            });
        });
    }

    @Override
    public void hideHistoryKey() {
        mHistoryKeyData.clear();
        mHistoryFlowLayout.removeAllViews();
        mHistoryKeyContainerView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(Integer resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToSearch(String key) {
        mSearchView.setSearchViewText(key);
        start(SearchResultFragment.newInstance(key));
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.search_page_bar;
    }
}
