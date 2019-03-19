package com.victorxu.muses.trade.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;

import com.victorxu.muses.custom.PinchImageView;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.trade.contract.CustomizeContract;
import com.victorxu.muses.trade.presenter.CustomizePresenter;
import com.victorxu.muses.trade.view.adapter.StyleSelectAdapter;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;
import com.victorxu.muses.trade.view.entity.StyleSelectItem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomizeFragment extends BaseSwipeBackFragment implements CustomizeContract.View {

    private Toolbar mToolbarCustomize;
    private PinchImageView mImgCustomize;
    private AppCompatTextView mTextCustomizeDetail;
    private AppCompatTextView mTextCustomizePrice;
    private RecyclerView mRecyclerCustomize;
    private AppCompatButton mBtnConfirm;
    private StyleSelectAdapter mStyleAdapter;

    private Commodity.CommodityDetail mCommodityData;
    private List<StyleSelectItem> mStyleSelectData = new ArrayList<>();
    private Map<String, Boolean> mSelectFlag = new HashMap<>();

    private int id;
    private CustomizeContract.Presenter mPresenter;

    public static CustomizeFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        CustomizeFragment fragment = new CustomizeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        mPresenter = null;
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
        return R.id.customize_toolbar;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("ID");
        } else {
            showToast(R.string.data_error_please_try_again);
            pop();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customize, container, false);
        mPresenter = new CustomizePresenter(this, id, mActivity);
        mPresenter.loadRootView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void initRootView(View view) {
        mToolbarCustomize = view.findViewById(R.id.customize_toolbar);
        mImgCustomize = view.findViewById(R.id.customize_image_display);
        mTextCustomizeDetail = view.findViewById(R.id.customize_text_select_info);
        mTextCustomizePrice = view.findViewById(R.id.customize_text_price);
        mRecyclerCustomize = view.findViewById(R.id.customize_recycler_view);
        mBtnConfirm = view.findViewById(R.id.customize_btn_confirm);

        mToolbarCustomize.setNavigationOnClickListener(v -> mActivity.onBackPressed());

        mSelectFlag.put("尺寸", false);
        mSelectFlag.put("颜色分类", false);

        mRecyclerCustomize.setLayoutManager(new LinearLayoutManager(mActivity));
        mStyleAdapter = new StyleSelectAdapter(mStyleSelectData);
        mStyleAdapter.setOnTagItemClickListener((int index, Commodity.CommodityDetail.AttributesBean.ParametersBean parameter, boolean isSelected) -> {
            if (parameter.getImage() != null) {
                if (isSelected) {
                    post(() -> GlideApp.with(mActivity)
                            .load(parameter.getImage())
                            .apply(RequestOptions.centerCropTransform())
                            .into(mImgCustomize)
                    );
                    mPresenter.updateProductImage(parameter.getImage());
                } else {
                    post(() ->
                            GlideApp.with(mActivity)
                                    .load(mCommodityData.getCoverImage())
                                    .apply(RequestOptions.centerCropTransform())
                                    .into(mImgCustomize)
                    );
                }
                mSelectFlag.put("颜色分类", isSelected);
                mPresenter.updateStyleSelectDetail("颜色分类", parameter.getValue(), parameter.getId(), isSelected, checkSelectFlag());
            } else {
                mSelectFlag.put("尺寸", isSelected);
                mPresenter.updateStyleSelectDetail("尺寸", parameter.getValue(), parameter.getId(), isSelected, checkSelectFlag());
            }
        });
        mStyleAdapter.setOnNumberSelectListener((int number) -> mPresenter.updateStyleSelectNumber(number));
        mRecyclerCustomize.setAdapter(mStyleAdapter);
        mBtnConfirm.setOnClickListener((v -> {
            if (checkSelectFlag()) {
                mPresenter.settleProduct();
            } else {
                if (mSelectFlag.get("尺寸")) {
                    showToast("请选择颜色分类");
                } else {
                    showToast("请选择尺寸");
                }
            }
        }));
    }

    @Override
    public void showCommodityData(Commodity.CommodityDetail data) {
        mCommodityData = data;
        post(() -> {
            mTextCustomizePrice.setText(String.valueOf(mCommodityData.getDiscountPrice()));
            GlideApp.with(mActivity)
                    .load(mCommodityData.getCoverImage())
                    .apply(RequestOptions.centerCropTransform())
                    .into(mImgCustomize);
        });
    }

    @Override
    public void showStyleData(List<StyleSelectItem> data) {
        mStyleSelectData.clear();
        mStyleSelectData.addAll(data);
        post(() -> {
            mStyleAdapter.setNewData(mStyleSelectData);
            mStyleAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showSelectDetail(String detail) {
        post(() -> mTextCustomizeDetail.setText(detail));
    }

    @Override
    public void showSettleFragment(ProductSettleOrderBean data) {
        startWithPop(SettleFragment.newInstance(data, SettleFragment.TYPE_CUSTOMIZE));
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    private boolean checkSelectFlag() {
        return !mSelectFlag.containsValue(false);
    }
}
